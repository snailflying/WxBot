package io.merculet.wxbot

/**
 * Created by Administrator on 2018/4/27 0027.
 */

import android.annotation.SuppressLint
import android.content.ContentValues
import android.text.TextUtils
import android.util.Log
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.*
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import java.io.*
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class TestHook : IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        val pkgname = lpparam.packageName
        if (FILTER_PKGNAME == pkgname) {

            LogUtil.log("TestHook: ---------------" + lpparam.packageName)
            var hookMessageListenerClass: Class<*>? = null
            hookMessageListenerClass = lpparam.classLoader.loadClass("com.tencent.mm.ac.o")
            var hookMessageListenerClass2: Class<*>? = null
            hookMessageListenerClass2 = lpparam.classLoader.loadClass("com.tencent.mm.ac.e")
            var hookclass1: Class<*>? = null
            hookclass1 = lpparam.classLoader.loadClass("com.tencent.mm.modelmulti.i")

            client = DatagramSocket()
            initudp()
            //SharedPreferences sp =  Context.getSharedPreferences("auth_info_key_prefs", Context.MODE_PRIVATE);

//            Thread(Runnable {
//                while (true) {
//                    if (isInit!!) {
//                        try {
//                            val bytes = ByteArray(2048)
//                            val packet = DatagramPacket(bytes, bytes.size)
//                            client!!.receive(packet)
//                            val reply = String(bytes, 0, packet.length)
//                            LogUtil.log(reply)
//                            postmsg(globalloader, reply.split("-----".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0], reply.split("-----".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                            LogUtil.log(e.toString())
//                            //client.close();
//                        }
//
//                    }
//
//
//                }
//            }).start()
//            XposedHelpers.findAndHookMethod("com.tencent.mm.bu.a", lpparam.classLoader, "a",
//                    String::class.java,
//                    String::class.java,
//                    String::class.java,
//                    Long::class.javaPrimitiveType,
//                    String::class.java,
//                    HashMap::class.java,
//                    Boolean::class.javaPrimitiveType,
//
//                    object : XC_MethodHook() {
//                        @Throws(Throwable::class)
//                        override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
//
//                            LogUtil.log("参数1  " + param!!.args[0])
//                            LogUtil.log("参数2  " + param.args[1])
//                            LogUtil.log("参数3  " + param.args[2])
//                            LogUtil.log("参数4  " + param.args[3])
//                            LogUtil.log("参数5  " + param.args[4])
//                            LogUtil.log("参数6  " + param.args[5])
//                            LogUtil.log("参数7  " + param.args[6])
//                        }
//                    })

            LogUtil.log("TestHook: --------------findAndHookMethod")
            XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", lpparam.classLoader, "insert", String::class.java, String::class.java, ContentValues::class.java, object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                    LogUtil.log("开始劫持了~")
                    LogUtil.log("参数1 = " + param!!.args[0])
                    LogUtil.log("参数2 = " + param.args[1])
                    LogUtil.log("参数3 = " + param.args[2])
                }

                @Throws(Throwable::class)
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                    val contentValues = param!!.args[2] as ContentValues
                    val tableName = param.args[0] as String
                    LogUtil.log("劫持结束了~")
                    LogUtil.log("参数1 = " + param.args[0])
                    LogUtil.log("参数2 = " + param.args[1])
                    //判断新消息
                    if (!TextUtils.isEmpty(tableName) && tableName == "message") {
                        val isSend = contentValues.getAsInteger("isSend")
                        val type = contentValues.getAsInteger("type")
                        val status = contentValues.getAsInteger("status")
                        val talker = contentValues.getAsString("talker")
                        val content = contentValues.getAsString("content")

                        if (isSend == 0 && status == 3 && type == 1) { // type 1.文本 3.图片 48.位置
                            //群组 发言 talker=5885584579@chatroom content=zzxm88:text
                            //群组@ 发言 talker=5885584579@chatroom content=zzxm88:@嘻嘻  text
                            if ((!isInit!!)) {
                                initudp()
                            }


                            globalloader = lpparam.classLoader

                            postmsgbeta(lpparam.classLoader, talker, content)

                        }

                    }
                    //判断新好友
                    if (!TextUtils.isEmpty(tableName) && tableName == "fmessage_msginfo") {
                        val isSend = contentValues.getAsInteger("isSend")
                        val type = contentValues.getAsInteger("type")
                        val talker = contentValues.getAsString("talker")
                        val content = contentValues.getAsString("content")
                        val msgcontent = contentValues.getAsString("msgContent")
                        if (isSend == 0 && type == 1) {
                            /*
                            参数3 = encryptTalker=v1_cdf8a7b3da023da5307395352cd08500f7b4150729e300531a9da91d1f296ba7@stranger msgContent=<msg fromusername="zzxm88" encryptusername="v1_cdf8a7b3da023da5307395352cd08500f7b4150729e300531a9da91d1f296ba7@stranger" fromnickname="明" content="我是明" fullpy="ming" shortpy="M" imagestatus="3" scene="30" country="DE" province="Hamburg" city="" sign="" percard="1" sex="1" alias="xm________________" weibo="" weibonickname="" albumflag="0" albumstyle="0" albumbgimgid="912895298764800_912895298764800" snsflag="48" snsbgimgid="" snsbgobjectid="0" mhash="1b3dad158913a69d934caaadde76a00f" mfullhash="1b3dad158913a69d934caaadde76a00f" bigheadimgurl="http://wx.qlogo.cn/mmhead/ver_1/OGlGw9icNc0W5md9kfaC54hRN6zsxxLicIiboXkjYTSUs6FEhvV56QLz5icRLdkbicSsZCktcxiaQKT0qZsSEk6MYwng/0" smallheadimgurl="http://wx.qlogo.cn/mmhead/ver_1/OGlGw9icNc0W5md9kfaC54hRN6zsxxLicIiboXkjYTSUs6FEhvV56QLz5icRLdkbicSsZCktcxiaQKT0qZsSEk6MYwng/96" ticket="v2_c9ded7a183c8bc8c587756da612cea8fadc77558e98239c10d8bb1ff590f5a0088bdd7ef3ae4f6e99acc93e160c2baec@stranger" opcode="2" googlecontact="" qrticket="" chatroomusername="" sourceusername="" sourcenickname=""><brandlist count="0" ver="698050163"></brandlist></msg> chatroomName= svrId=5540230629124514099 createTime=1526281451000 talker=zzxm88 type=1 isSend=0
                            */


                            val newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            val parse = newDocumentBuilder.parse(ByteArrayInputStream(msgcontent.toByteArray()))

                            val ticket = parse.firstChild.attributes.getNamedItem("ticket").nodeValue

                            requestCaller = callStaticMethod(findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv")
                            val luckyMoneyRequest = newInstance(findClass("com.tencent.mm.pluginsdk.model.m", lpparam.classLoader), 3, talker, ticket, 30)
                            callMethod(requestCaller, "a", luckyMoneyRequest, 0)
                            postmsgbeta(lpparam.classLoader, talker, "newfriend")
                        }

                    }

                }
            })

            /*
//2018-05-07
            findAndHookMethod("com.tencent.mm.plugin.luckymoney.b.ac", lpparam.classLoader, "a", int.class, String.class, JSONObject.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {

                            Log.i("luckmoney",JSON.toJSONString(param.args));
                            Log.i("luckmoney",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            findAndHookMethod("com.tencent.mm.modelmulti.i", lpparam.classLoader, "a", String.class,Object.class,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","string object int");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            findAndHookMethod("com.tencent.mm.modelmulti.i", lpparam.classLoader, "a", String.class,Object.class,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","string object int");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.network.e");
            hookMessageListenerClass2 =lpparam.classLoader.loadClass("com.tencent.mm.ac.e");
            findAndHookMethod("com.tencent.mm.modelmulti.i", lpparam.classLoader, "a", hookMessageListenerClass,hookMessageListenerClass2, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.network.e com.tencent.mm.ac.e");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            XposedHelpers.findAndHookConstructor("com.tencent.mm.modelmulti.i", lpparam.classLoader,  String.class,String.class,int.class,int.class,Object.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.modelmulti.i S S i i O ");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            XposedHelpers.findAndHookConstructor("com.tencent.mm.modelmulti.i", lpparam.classLoader,  String.class,String.class,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.modelmulti.i S S ");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            XposedHelpers.findAndHookConstructor("com.tencent.mm.modelmulti.i", lpparam.classLoader,  new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.modelmulti.i ");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            XposedHelpers.findAndHookConstructor("com.tencent.mm.modelmulti.i", lpparam.classLoader,  long.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.modelmulti.i long ");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );


            findAndHookMethod("com.tencent.mm.z.au", lpparam.classLoader, "Dv",  new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.z.au#Dv");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            //--------------
            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.o");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", hookMessageListenerClass,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.o");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", hookMessageListenerClass,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.o");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", int.class,String.class,int.class,boolean.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );

            //--------------
            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.storage.az");
            findAndHookMethod("com.tencent.mm.ui.chatting.af", lpparam.classLoader, "aH", hookMessageListenerClass, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ui.chatting.af#aH");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            findAndHookMethod("com.tencent.mm.ui.chatting.af", lpparam.classLoader, "aI", hookMessageListenerClass, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ui.chatting.af#aI");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            findAndHookMethod("com.tencent.mm.ui.chatting.af", lpparam.classLoader, "aJ", hookMessageListenerClass, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ui.chatting.af#aJ");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            findAndHookMethod("com.tencent.mm.ui.transmit.d", lpparam.classLoader, "D", String.class,String.class,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ui.transmit.d#D");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            findAndHookMethod("com.tencent.mm.ui.transmit.d", lpparam.classLoader, "l", String.class,String.class,boolean.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ui.transmit.d#l");
                            Log.i("postmsg-param",JSON.toJSONString(param.args));
                            Log.i("postmsg-result",JSON.toJSONString(param.getResult()));
                        }
                    }
            );
            //这里是为了解决app多dex进行hook的问题，Xposed默认是hook主dex
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ClassLoader cl = ((Context)param.args[0]).getClassLoader();
                    Class<?> hookclass = null;
                    try {
                        hookclass = cl.loadClass("dalvik.system.DexFile");
                    } catch (Exception e) {
                        return;
                    }
                    //Log.i("shuchu",JSON.toJSONString(classArray2));
                    XposedHelpers.findAndHookMethod(hookclass, "loadClass", String.class, ClassLoader.class, new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            //hookClassInfo((String)param.args[0], (ClassLoader)param.args[1]);
                            //classArray2.add(JSON.toJSONString(param));
                            //Log.i("shuchu",JSON.toJSONString(param));
                            super.beforeHookedMethod(param);
                        }
                    });

                    XposedHelpers.findAndHookMethod(hookclass, "loadClassBinaryName", String.class, ClassLoader.class, List.class,new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            //hookClassInfo((String)param.args[0], (ClassLoader)param.args[1]);
                            //classArray2.add(JSON.toJSONString(param));
                            //Log.i("shuchu",JSON.toJSONString(param));
                            super.beforeHookedMethod(param);
                        }
                    });

                    XposedHelpers.findAndHookMethod(hookclass, "defineClass", String.class, ClassLoader.class, long.class, List.class,new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            //hookClassInfo((String)param.args[0], (ClassLoader)param.args[1]);
                            //classArray2.add(JSON.toJSONString(param));
                            //Log.i("shuchu",JSON.toJSONString(param));
                            super.beforeHookedMethod(param);
                        }
                    });

                }
            });
*/


            /*hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.o");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", int.class,String.class,int.class,boolean.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a[int,str,int,bool]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-param",""+param.args[3]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );

            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.e");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", int.class,hookMessageListenerClass, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a[int,e]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );

            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.l");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", hookMessageListenerClass,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a[l,int]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );
            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.l");
            findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a", int.class,int.class,String.class,hookMessageListenerClass, new XC_MethodHook() {
                            protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-param","com.tencent.mm.ac.o#a[int,int,str,l]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-param",""+param.args[3]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );


            hookMessageListenerClass =lpparam.classLoader.loadClass("com.tencent.mm.ac.l");

            findAndHookConstructor("com.tencent.mm.pluginsdk.model.m", lpparam.classLoader,  int.class,String.class,String.class,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-function","com.tencent.mm.pluginsdk.model.m#m[int,str,str,int]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-param",""+param.args[3]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );
            findAndHookConstructor("com.tencent.mm.pluginsdk.model.m", lpparam.classLoader,  String.class,String.class,int.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-function","com.tencent.mm.pluginsdk.model.m#m[str,str,int]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );
            hookMessageListenerClass =lpparam.classLoader.loadClass("java.util.List");
            findAndHookConstructor("com.tencent.mm.pluginsdk.model.m", lpparam.classLoader,  int.class,hookMessageListenerClass,hookMessageListenerClass,String.class,String.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-function","com.tencent.mm.pluginsdk.model.m#m[int,list,list,str,str]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-param",""+param.args[3]);
                            Log.i("postmsg-param",""+param.args[4]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );
            hookMessageListenerClass2 =lpparam.classLoader.loadClass("java.util.Map");
            findAndHookConstructor("com.tencent.mm.pluginsdk.model.m", lpparam.classLoader,  int.class,hookMessageListenerClass,hookMessageListenerClass,String.class,String.class,hookMessageListenerClass2,String.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-function","com.tencent.mm.pluginsdk.model.m#m[int,list,list,str,str,map,str]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-param",""+param.args[3]);
                            Log.i("postmsg-param",""+param.args[4]);
                            Log.i("postmsg-param",""+param.args[5]);
                            Log.i("postmsg-param",""+param.args[6]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );
            findAndHookConstructor("com.tencent.mm.pluginsdk.model.m", lpparam.classLoader,  int.class,hookMessageListenerClass,hookMessageListenerClass,hookMessageListenerClass,String.class,String.class,hookMessageListenerClass2,String.class,String.class, new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws JSONException {
                            Log.i("postmsg-function","com.tencent.mm.pluginsdk.model.m#m[int,list,list,list,str,str,map,str,str]");
                            Log.i("postmsg-param",""+param.args[0]);
                            Log.i("postmsg-param",""+param.args[1]);
                            Log.i("postmsg-param",""+param.args[2]);
                            Log.i("postmsg-param",""+param.args[3]);
                            Log.i("postmsg-param",""+param.args[4]);
                            Log.i("postmsg-param",""+param.args[5]);
                            Log.i("postmsg-param",""+param.args[6]);
                            Log.i("postmsg-param",""+param.args[7]);
                            Log.i("postmsg-param",""+param.args[8]);
                            Log.i("postmsg-result",""+param.getResult());
                        }
                    }
            );*/
        }


    }

    fun postmsgbeta(test: ClassLoader, t: String, c: String) {
        try {
            val data = "$t-----$c".toByteArray()
            val address = InetAddress.getByName("127.0.0.1")
            val port = 14895
            val packet = DatagramPacket(data, data.size, address, port)
            client!!.send(packet)
            Log.i("udp client push", "$t-----$c")
        } catch (e: IOException) {
            Log.e("udp ", e.toString())

        }

    }

    fun postmsg(test: ClassLoader?, t: String, c: String) {
        requestCaller = callStaticMethod(findClass("com.tencent.mm.z.au", globalloader), "Dv")
        val luckyMoneyRequest = newInstance(findClass("com.tencent.mm.modelmulti.i", globalloader),
                t, c, 1, 0, null)
        callMethod(requestCaller, "a", luckyMoneyRequest, 0)

    }

    fun initudp() {
        //初始化设置Socket
        try {
            val data = "initwechatbot".toByteArray()
            val address = InetAddress.getByName("127.0.0.1")
            val port = 14895
            val packet = DatagramPacket(data, data.size, address, port)
            client!!.send(packet)
            isInit = true
        } catch (ex: IOException) {
            ex.printStackTrace()
            LogUtil.log(ex.toString())
        }


    }

    companion object {
        var methodSignSet: MutableSet<String> = Collections.synchronizedSet(HashSet())
        var callMethodSignSet: MutableSet<String> = Collections.synchronizedSet(HashSet())
        private val classArray = arrayListOf<String>()
        private val classArray2 = arrayListOf<String>()
        private val FILTER_PKGNAME = "com.tencent.mm"
        private var requestCaller: Any? = null

        //初始化设置Socket
        private var client: DatagramSocket? = null
        var isInit: Boolean? = false

        private val `in`: BufferedReader? = null
        private val out: PrintWriter? = null
        private var globalloader: ClassLoader? = null

        /**
         * 获取dex路径
         * @param classLoader
         * @return
         */
        fun getDexPath(classLoader: ClassLoader): String? {
            try {
                val field = classLoader.javaClass.superclass!!.getDeclaredField("pathList")
                field.isAccessible = true
                val objPathList = field.get(classLoader)
                val elementsField = objPathList.javaClass.getDeclaredField("dexElements")
                elementsField.isAccessible = true
                val elements = elementsField.get(objPathList) as Array<Any>
                for (obj in elements) {
                    val fileF = obj.javaClass.getDeclaredField("file")
                    fileF.isAccessible = true
                    val file = fileF.get(obj) as File
                    return file.absolutePath
                }
            } catch (e: Exception) {
            }

            return null
        }

        private fun hookClassInfo(className: String, classLoader: ClassLoader) {
            //过滤系统类名前缀
            if (TextUtils.isEmpty(className)) {
                return
            }
            if (className.startsWith("android.")) {
                return
            }
            if (className.startsWith("java.")) {
                return
            }
            if (!className.startsWith("com.tencent.mm")) {
                return
            }

            if (classArray.contains(className)) {
                return
            }
            classArray.add(className)
            //Log.i("classname",className+"---"+classLoader);
            //if (true){
            //    return ;
            //}
            //利用反射获取一个类的所有方法
            try {
                val clazz = XposedHelpers.findClassIfExists(className, classLoader)
                //这里获取类的所有方法，但是无法获取父类的方法，不过这里没必要关系父类的方法
                //如果要关心，那么需要调用getMethods方法即可

                val allMethods = clazz.declaredMethods
                for (method in allMethods) {
                    val paramTypes = method.parameterTypes
                    val methodName = method.name
                    val param = arrayOfNulls<Any>(paramTypes.size + 1)
                    for (i in paramTypes.indices) {
                        param[i] = paramTypes[i]
                    }
                    val signStr = getMethodSign(method)
                    if (TextUtils.isEmpty(signStr) || isFilterMethod(signStr)) {
                        continue
                    }

                    //开始构造Hook的方法信息
                    param[paramTypes.size] = object : XC_MethodHook() {
                        @Throws(Throwable::class)
                        override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                            val methodSign = getMethodSign(param)
                            if (!TextUtils.isEmpty(methodSign) && !callMethodSignSet.contains(methodSign)) {
                                //这里因为会打印日志，所以会出现app的ANR情况
                                LogUtil.log("call-->" + methodSign!!)
                                //这里还可以把方法的参数值打印出来，不过如果应用过大，这里会出现ANR
                                for (i in param!!.args.indices) {
                                    LogUtil.log("==>arg" + i + ":" + param.args[i])
                                }
                                callMethodSignSet.add(methodSign)
                            }
                            super.afterHookedMethod(param)
                        }
                    }

                    //开始进行Hook操作，注意这里有一个问题，如果一个Hook的方法数过多，会出现OOM的错误，这个是Xposed工具的问题
                    if (!TextUtils.isEmpty(signStr) && !methodSignSet.contains(signStr)) {
                        //这里因为会打印日志，所以会出现app的ANR情况
                        LogUtil.log("all-->" + signStr!!)
                        methodSignSet.add(signStr)
                        XposedHelpers.findAndHookMethod(className, classLoader, methodName, *param)
                    }
                }
            } catch (e: Exception) {
                LogUtil.log("" + e.toString())
            }

        }

        /**
         * 获取方法的签名信息
         * @param param
         * @return
         */
        private fun getMethodSign(param: XC_MethodHook.MethodHookParam?): String? {
            try {
                val methodSign = StringBuilder()
                methodSign.append(Modifier.toString(param!!.method.modifiers) + " ")
                val result = param.result
                if (result == null) {
                    methodSign.append("void ")
                } else {
                    methodSign.append(result.javaClass.canonicalName!! + " ")
                }
                methodSign.append(param.method.declaringClass.canonicalName + "." + param.method.name + "(")
                for (i in param.args.indices) {
                    //这里有一个问题：如果方法的参数值为null,那么这里就会报错! 得想个办法如何获取到参数类型？
                    if (param.args[i] == null) {
                        methodSign.append("?")
                    } else {
                        methodSign.append(param.args[i].javaClass.canonicalName)
                    }
                    if (i < param.args.size - 1) {
                        methodSign.append(",")
                    }
                }
                methodSign.append(")")
                return methodSign.toString()
            } catch (e: Exception) {
                return null
            }

        }

        /**
         * 获取方法的签名信息
         * public final native String xxx(java.lang.String,int) 类似于这种类型
         * @param method
         * @return
         */
        private fun getMethodSign(method: Method): String? {
            try {
                //如果这个方法是继承父类的方法，也需要做过滤
                val methodClass = method.declaringClass.canonicalName
                if (methodClass!!.startsWith("android.") || methodClass.startsWith("java.")) {
                    return null
                }
                val methodSign = StringBuilder()
                val paramTypes = method.parameterTypes
                val returnTypes = method.returnType
                methodSign.append(Modifier.toString(method.modifiers) + " ")
                methodSign.append(returnTypes.canonicalName!! + " ")
                methodSign.append(methodClass + "." + method.name + "(")
                for (i in paramTypes.indices) {
                    methodSign.append(paramTypes[i].canonicalName)
                    if (i < paramTypes.size - 1) {
                        methodSign.append(",")
                    }
                }
                methodSign.append(")")
                return methodSign.toString()
            } catch (e: Exception) {
                return null
            }

        }

        /**
         * 过滤Object对象自带的几个方法，这些方法可以不用做处理
         * @param methodSign
         * @return
         */
        private fun isFilterMethod(methodSign: String?): Boolean {
            if ("public final void java.lang.Object.wait()" == methodSign) {
                return true
            }
            if ("public final void java.lang.Object.wait(long,int)" == methodSign) {
                return true
            }
            if ("public final native java.lang.Object.wait(long)" == methodSign) {
                return true
            }
            if ("public boolean java.lang.Object.equals(java.lang.Object)" == methodSign) {
                return true
            }
            if ("public java.lang.String java.lang.Object.toString()" == methodSign) {
                return true
            }
            if ("public native int java.lang.Object.hashCode()" == methodSign) {
                return true
            }
            if ("public final native java.lang.Class java.lang.Object.getClass()" == methodSign) {
                return true
            }
            if ("public final native void java.lang.Object.notify()" == methodSign) {
                return true
            }
            return "public final native void java.lang.Object.notifyAll()" == methodSign
        }

        /**
         * 跨进程读取数据，会显示失败的，这个方法是无效的，因为methodSignSet数据可能跨进程读取失败
         * @return
         */
        @SuppressLint("SdCardPath")
        fun dumpAllMethodInfo(): Boolean {
            LogUtil.log("all method size:" + methodSignSet.size)
            if (methodSignSet.size == 0) {
                return false
            }
            var fw: FileWriter? = null
            var bw: BufferedWriter? = null
            try {
                fw = FileWriter("/sdcard/" + FILTER_PKGNAME + "_allmethod.txt")
                bw = BufferedWriter(fw)
                for (methodStr in methodSignSet) {
                    bw.write(methodStr)
                    bw.newLine()
                }
                return true
            } catch (e: Exception) {
                LogUtil.log("dump all method error:" + Log.getStackTraceString(e))
                return false
            } finally {
                try {
                    fw?.close()
                    bw?.close()
                } catch (e: Exception) {
                }

            }
        }

        /**
         * 跨进程读取数据失败
         * @return
         */
        @SuppressLint("SdCardPath")
        fun dumpCallMethodInfo(): Boolean {
            LogUtil.log("call method size:" + callMethodSignSet.size)
            if (callMethodSignSet.size == 0) {
                return false
            }
            var fw: FileWriter? = null
            var bw: BufferedWriter? = null
            try {
                fw = FileWriter("/sdcard/" + FILTER_PKGNAME + "_callmethod.txt")
                bw = BufferedWriter(fw)
                for (methodStr in callMethodSignSet) {
                    bw.write(methodStr)
                    bw.newLine()
                }
                return true
            } catch (e: Exception) {
                LogUtil.log("dump call method error:" + Log.getStackTraceString(e))
                return false
            } finally {
                try {
                    fw?.close()
                    bw?.close()
                } catch (e: Exception) {
                }

            }
        }
    }

}
