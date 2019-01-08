//package com.tencent.mm.roomsdk.a;
//
//import com.tencent.mm.roomsdk.a.a.a;
//import com.tencent.mm.sdk.platformtools.y;
//import java.util.HashMap;
//
//public final class b {
//    private static b ubp = new b();
//    private HashMap<String, a> ubo = new HashMap();
//
//    public static a YK(String str) {
//        int indexOf = str.indexOf("@");
//        if (indexOf < 0) {
//            y.e("MicroMsg.RoomServiceFactory", "get NotNullChatRoom %s", str);
//            return new com.tencent.mm.roomsdk.a.a.b();
//        }
//        String substring = str.substring(indexOf);
//        b bVar = ubp;
//        a aVar = bVar.ubo.containsKey(substring) ? (a) bVar.ubo.get(substring) : null;
//        if (aVar == null) {
//            return new com.tencent.mm.roomsdk.a.a.b();
//        }
//        return aVar;
//    }
//
//    public static void a(String str, a aVar) {
//        Object str2;
//        int indexOf = str2.indexOf("@");
//        if (indexOf >= 0) {
//            str2 = str2.substring(indexOf);
//        }
//        b bVar = ubp;
//        if (!bVar.ubo.containsKey(str2)) {
//            bVar.ubo.put(str2, aVar);
//        }
//    }
//}