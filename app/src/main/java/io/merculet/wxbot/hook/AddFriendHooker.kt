package io.merculet.wxbot.hook

import android.content.ContentValues
import android.text.TextUtils
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal
import com.gh0u1l5.wechatmagician.spellbook.base.Hooker
import com.gh0u1l5.wechatmagician.spellbook.base.HookerProvider
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.*
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory


/**
 * @Description 自动同意添加好友
 * 通过诺监听数据库插入操作,判断是否是新增好友,如果是,则hook调用SayHiWithSnsPermissionUI中au.Dk().a(oVar, 0)方法,即可自动添加
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2019/1/2 5:10 PM
 * @Version 1.0.0
 */
object AddFriendHooker : HookerProvider {

    override fun provideStaticHookers(): List<Hooker>? = listOf(addFriend())

    private fun addFriend(): Hooker = Hooker {
        LogUtil.log("AddFriendHooker addFriend ~")
        findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", WechatGlobal.wxLoader, "insert", String::class.java, String::class.java, ContentValues::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam?) {
                LogUtil.log("开始劫持了~")
                LogUtil.log("参数1 = " + param!!.args[0])
                LogUtil.log("参数2 = " + param.args[1])
                LogUtil.log("参数3 = " + param.args[2])
            }

            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val contentValues = param.args[2] as ContentValues
                val tableName = param.args[0] as String
                LogUtil.log("劫持结束了~")
                LogUtil.log("参数1 = " + param.args[0])
                LogUtil.log("参数2 = " + param.args[1])

                //判断新好友
                if (!TextUtils.isEmpty(tableName) && tableName == "fmessage_msginfo") {
                    val isSend = contentValues.getAsInteger("isSend")
                    val type = contentValues.getAsInteger("type")
                    val talker = contentValues.getAsString("talker")
                    val content = contentValues.getAsString("content")
                    val msgcontent = contentValues.getAsString("msgContent")    //参数msgcontent = encryptTalker=v1_cdf8a7b3da023da5307395352cd08500f7b4150729e300531a9da91d1f296ba7@stranger msgContent=<msg fromusername="zzxm88" encryptusername="v1_cdf8a7b3da023da5307395352cd08500f7b4150729e300531a9da91d1f296ba7@stranger" fromnickname="明" content="我是明" fullpy="ming" shortpy="M" imagestatus="3" scene="30" country="DE" province="Hamburg" city="" sign="" percard="1" sex="1" alias="xm________________" weibo="" weibonickname="" albumflag="0" albumstyle="0" albumbgimgid="912895298764800_912895298764800" snsflag="48" snsbgimgid="" snsbgobjectid="0" mhash="1b3dad158913a69d934caaadde76a00f" mfullhash="1b3dad158913a69d934caaadde76a00f" bigheadimgurl="http://wx.qlogo.cn/mmhead/ver_1/OGlGw9icNc0W5md9kfaC54hRN6zsxxLicIiboXkjYTSUs6FEhvV56QLz5icRLdkbicSsZCktcxiaQKT0qZsSEk6MYwng/0" smallheadimgurl="http://wx.qlogo.cn/mmhead/ver_1/OGlGw9icNc0W5md9kfaC54hRN6zsxxLicIiboXkjYTSUs6FEhvV56QLz5icRLdkbicSsZCktcxiaQKT0qZsSEk6MYwng/96" ticket="v2_c9ded7a183c8bc8c587756da612cea8fadc77558e98239c10d8bb1ff590f5a0088bdd7ef3ae4f6e99acc93e160c2baec@stranger" opcode="2" googlecontact="" qrticket="" chatroomusername="" sourceusername="" sourcenickname=""><brandlist count="0" ver="698050163"></brandlist></msg> chatroomName= svrId=5540230629124514099 createTime=1526281451000 talker=zzxm88 type=1 isSend=0
                    LogUtil.log("AddFriendHooker talker: $talker \n content: $content \n isSend: $isSend \n type: $type \n msgcontent: $msgcontent")
                    if (isSend == 0 && type == 1) {
                        val newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        val parse = newDocumentBuilder.parse(ByteArrayInputStream(msgcontent.toByteArray()))
                        val ticket = parse.firstChild.attributes.getNamedItem("ticket").nodeValue
                        LogUtil.log("ticket: $ticket")
                        val requestCaller = callStaticMethod(findClass("com.tencent.mm.model.au", WechatGlobal.wxLoader), "Dk")
//                        val luckyMoneyRequest = newInstance(findClass("com.tencent.mm.pluginsdk.model.m", WechatGlobal.wxLoader), talker, ticket, 6, 0.toByte())
//                        val requestCaller = callStaticMethod(Classes.Find_mm_model_au, "Dk")    //Dk方法无法定位,写死   //获取返回值为com.tencent.mm.ah.p
                        val luckyMoneyRequest = newInstance(Classes.Find_plugin_sdk_model_m, talker, ticket, 6, 0.toByte()) // 即com.tencent.mm.pluginsdk.model.m中构造public m(String str, String str2, int i, byte b)
                        LogUtil.log("luckyMoneyRequest: $luckyMoneyRequest -- requestCaller: $requestCaller")
                        //通过Dk方法的返回值(对象),再调用该对象的a()方法
                        callMethod(requestCaller, "a", luckyMoneyRequest, 0)    //即com.tencent.mm.ah.p 中public final boolean a(m mVar, int i)   定位a方法需要的class太多,暂时写死
                    }
                }

                //同意添加好友调用的此方法
//                findAndHookConstructor("com.tencent.mm.pluginsdk.model.m", WechatGlobal.wxLoader, C.String, C.String, C.Int, C.Byte,
//                        object : XC_MethodHook() {
//                            @Throws(JSONException::class)
//                            override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
//                                LogUtil.log("postmsg-function com.tencent.mm.pluginsdk.model.m#m[int,str,str,int]")
//                                LogUtil.log("postmsg-param: " + param.args[0])    //luxiao680766
//                                LogUtil.log("postmsg-param: " + param.args[1])    //ticket: v2_e610feb3d7d44e5d373e99e278f1b8774b24365844b3db72f31ed5581ba09862c7c622b31229de08759cd9cc9f44d37c21c3b633756c7b4b93c8c10f4493f7d4@stranger
//                                LogUtil.log("postmsg-param: " + param.args[2])    //6
//                                LogUtil.log("postmsg-param: " + param.args[3])    //0
//                                LogUtil.log("postmsg-result: " + param.result)    //null
//                            }
//                        }
//                )
            }
        })
    }

}