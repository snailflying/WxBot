//package io.merculet.wxbot.config;
//
//import android.app.Notification;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.os.SystemClock;
//import com.ydscience.fakemomo.entity.StateSwitch;
//import de.robv.android.xposed.IXposedHookLoadPackage;
//import de.robv.android.xposed.XC_MethodHook;
//import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
//import de.robv.android.xposed.XposedBridge;
//import de.robv.android.xposed.XposedHelpers;
//import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
//import java.util.List;
//
//public class HookUtils implements IXposedHookLoadPackage {
//    private static String a = "";
//
//    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
//        new StringBuilder("当前包名为").append(loadPackageParam.packageName);
//        if (loadPackageParam.packageName.equals("com.tencent.mm")) {
//            Context context = (Context) XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread", null), "currentActivityThread", new Object[0]), "getSystemContext", new Object[0]);
//            String str = context.getPackageManager().getPackageInfo(loadPackageParam.packageName, 0).versionName;
//            StringBuilder stringBuilder = new StringBuilder("wechat version");
//            stringBuilder.append(str);
//            XposedBridge.log(stringBuilder.toString());
//            a = str;
//            f.a(str);
//            c cVar = new c(context);
//            XposedBridge.hookAllConstructors(XposedHelpers.findClass(f.d, loadPackageParam.classLoader), new XC_MethodHook() {
//                protected final void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    c.this.b = methodHookParam.thisObject;
//                }
//            });
//            XposedBridge.hookAllConstructors(XposedHelpers.findClass(f.f, loadPackageParam.classLoader), new XC_MethodHook() {
//                protected final void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    if (c.this.g) {
//                        c.this.g = false;
//                        if (d.a()) {
//                            String str = (String) methodHookParam.args[1];
//                            String substring = str.substring(0, str.indexOf("&"));
//                            str = str.substring(str.indexOf("&") + 1, str.length());
//                            methodHookParam.args[0] = substring;
//                            methodHookParam.args[1] = str;
//                        }
//                    }
//                }
//            });
//            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", loadPackageParam.classLoader, "onCreate", new Object[]{Bundle.class, new XC_MethodHook() {
//                protected final void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    super.afterHookedMethod(methodHookParam);
//                }
//            }});
//            XposedHelpers.findAndHookMethod(f.a, loadPackageParam.classLoader, f.b, new Object[]{f.c, new XC_MethodHook() {
//                protected final void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    super.afterHookedMethod(methodHookParam);
//                    if (c.this.h) {
//                        c.a(c.this);
//                    }
//                    int i = 0;
//                    String str = (String) XposedHelpers.getObjectField(methodHookParam.args[0], "field_content");
//                    String str2 = (String) XposedHelpers.getObjectField(methodHookParam.args[0], "field_talker");
//                    int intValue = ((Integer) XposedHelpers.getObjectField(methodHookParam.args[0], "field_type")).intValue();
//                    StringBuilder stringBuilder = new StringBuilder("receive msg is ");
//                    stringBuilder.append(str);
//                    stringBuilder.append(" contact username is  ");
//                    stringBuilder.append(str2);
//                    stringBuilder.append(" field_bizChatId ");
//                    XposedBridge.log(stringBuilder.toString());
//                    c.this.f.delete(0, c.this.f.length());
//                    c.this.f.append(str2);
//                    c.this.f.append("&");
//                    if (d.a() && !str2.startsWith("gh_")) {
//                        StateSwitch e = d.e();
//                        if (e != null) {
//                            i = e.isReplyOrder();
//                        }
//                        if (i != 0) {
//                            List f = d.f();
//                            if (f.size() > 0 && f.contains(str2)) {
//                                c.a(c.this, intValue, str2, str);
//                            }
//                            return;
//                        }
//                        c.a(c.this, intValue, str2, str);
//                    }
//                }
//            }});
//            XposedBridge.hookAllMethods(XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabase", loadPackageParam.classLoader), "openDatabase", new XC_MethodHook() {
//                protected final void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    super.afterHookedMethod(methodHookParam);
//                    if (methodHookParam.args[0].toString().contains("EnMicroMsg.db")) {
//                        c.this.c = methodHookParam.getResult();
//                        if (c.this.i) {
//                            c.a(c.this);
//                        }
//                    }
//                }
//            });
//            cVar.k = XposedHelpers.findClass(f.i, loadPackageParam.classLoader);
//            cVar.j = XposedHelpers.findClass(f.j, loadPackageParam.classLoader);
//            XposedHelpers.findAndHookMethod(f.g, loadPackageParam.classLoader, f.h, new Object[]{Notification.class, Integer.TYPE, PendingIntent.class, String.class, String.class, String.class, Bitmap.class, Integer.TYPE, String.class, PendingIntent.class, Integer.TYPE, String.class, PendingIntent.class, String.class, new XC_MethodHook() {
//                protected final void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    super.afterHookedMethod(methodHookParam);
//                    StringBuilder stringBuilder = new StringBuilder("receive agree ask ");
//                    stringBuilder.append(methodHookParam.args[3]);
//                    stringBuilder.append("  ");
//                    stringBuilder.append(methodHookParam.args[4]);
//                    XposedBridge.log(stringBuilder.toString());
//                    StateSwitch e = d.e();
//                    if (e != null ? e.isAddState() : false) {
//                        c cVar = c.this;
//                        cVar.l.execute(new Runnable() {
//                            public final void run() {
//                                com.ydscience.fakemomo.b.c.a(c.this.c, c.this.j, c.this.k);
//                                if (com.ydscience.fakemomo.b.c.a != null) {
//                                    r3 = new Object[7];
//                                    r3[1] = new String[]{"talker", "contentFromUsername", "contentNickname", "isNew", "state", "fmsgIsSend", "fmsgType", "recvFmsgType", "displayName", "contentVerifyContent", "encryptTalker", "fmsgContent", "contentPhoneNumMD5", "contentFullPhoneNumMD5", "addScene"};
//                                    r3[2] = null;
//                                    r3[3] = null;
//                                    r3[4] = null;
//                                    r3[5] = null;
//                                    r3[6] = null;
//                                    Object callMethod = XposedHelpers.callMethod(com.ydscience.fakemomo.b.c.a, "query", r3);
//                                    if (callMethod != null) {
//                                        while (((Boolean) XposedHelpers.callMethod(callMethod, "moveToNext", new Object[0])).booleanValue()) {
//                                            Object[] objArr = new Object[1];
//                                            objArr[0] = XposedHelpers.callMethod(callMethod, "getColumnIndex", new Object[]{"talker"});
//                                            String str = (String) XposedHelpers.callMethod(callMethod, "getString", objArr);
//                                            r3 = new Object[1];
//                                            r3[0] = XposedHelpers.callMethod(callMethod, "getColumnIndex", new Object[]{"fmsgContent"});
//                                            String str2 = (String) XposedHelpers.callMethod(callMethod, "getString", r3);
//                                            Object[] objArr2 = new Object[1];
//                                            objArr2[0] = XposedHelpers.callMethod(callMethod, "getColumnIndex", new Object[]{"recvFmsgType"});
//                                            int intValue = ((Integer) XposedHelpers.callMethod(callMethod, "getInt", objArr2)).intValue();
//                                            Object[] objArr3 = new Object[1];
//                                            objArr3[0] = XposedHelpers.callMethod(callMethod, "getColumnIndex", new Object[]{"fmsgType"});
//                                            int intValue2 = ((Integer) XposedHelpers.callMethod(callMethod, "getInt", objArr3)).intValue();
//                                            Object[] objArr4 = new Object[1];
//                                            objArr4[0] = XposedHelpers.callMethod(callMethod, "getColumnIndex", new Object[]{"addScene"});
//                                            int intValue3 = ((Integer) XposedHelpers.callMethod(callMethod, "getInt", objArr4)).intValue();
//                                            if (intValue2 == 1 && intValue == 1) {
//                                                str2 = str2.substring(str2.indexOf("ticket") + 8, str2.indexOf("opcode") - 2);
//                                                if (!(com.ydscience.fakemomo.b.c.c == null || com.ydscience.fakemomo.b.c.b == null)) {
//                                                    Object newInstance = XposedHelpers.newInstance(com.ydscience.fakemomo.b.c.b, new Object[]{str, str2, Integer.valueOf(intValue3)});
//                                                    XposedHelpers.callMethod(XposedHelpers.callStaticMethod(com.ydscience.fakemomo.b.c.c, f.k, new Object[0]), f.l, new Object[]{newInstance, Integer.valueOf(0)});
//                                                }
//                                                SystemClock.sleep(1000);
//                                                if (com.ydscience.fakemomo.b.c.a != null) {
//                                                    objArr2 = new Object[3];
//                                                    objArr2[0] = "fmessage_conversation";
//                                                    objArr2[1] = "talker = ?";
//                                                    objArr2[2] = new String[]{str};
//                                                    XposedHelpers.callMethod(com.ydscience.fakemomo.b.c.a, "delete", objArr2);
//                                                }
//                                            }
//                                        }
//                                        XposedHelpers.callMethod(callMethod, "close", new Object[0]);
//                                    }
//                                }
//                            }
//                        });
//                    }
//                }
//            }});
//            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", loadPackageParam.classLoader, "onCreate", new Object[]{Bundle.class, new XC_MethodHook() {
//                protected final void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    c cVar = c.this;
//                    cVar.l.execute(new Runnable() {
//                        public final void run() {
//                            f.n = com.ydscience.fakemomo.b.c.a(c.this.c);
//                        }
//                    });
//                }
//            }});
//        }
//    }
//}