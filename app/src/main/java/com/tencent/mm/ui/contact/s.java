//package com.tencent.mm.ui.contact;
//
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.plugin.zero.b.a;
//import com.tencent.mm.sdk.platformtools.y;
//import java.util.HashSet;
//
//public final class s {
//    public static final int vMA = v(vMs, WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT);
//    public static final int vMq = v(256, 16, 1, 2, 4);
//    public static final int vMr = v(16, 1, 2, 4, 64, 16384);
//    public static int vMs = v(16, 1, 2, 4, 64, 16384, 16777216);
//    public static int vMt = v(16, 1, 2, 4, 64, 4096, 16777216);
//    public static final int vMu = v(vMq, 64, 16384, 4096, 16777216);
//    public static final int vMv = v(16, 2, 16384, 4);
//    public static final int vMw = v(vMq, 16384, 64, 4096, 16777216);
//    public static final int vMx = v(vMq, 16384, 64, WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, 8192, 16777216);
//    public static final int vMy = v(16, 32, 1, 4, 2, 64);
//    public static final int vMz = v(1, 2, 4, 64, 256, 16384);
//
//    public static HashSet<String> cHO() {
//        HashSet hashSet = new HashSet();
//        hashSet.add("weixin");
//        return hashSet;
//    }
//
//    public static void h(HashSet<String> hashSet) {
//        hashSet.remove("filehelper");
//    }
//
//    public static HashSet<String> cHP() {
//        HashSet hashSet = new HashSet();
//        hashSet.add("officialaccounts");
//        hashSet.add("newsapp");
//        for (Object add : com.tencent.mm.model.s.dVc) {
//            hashSet.add(add);
//        }
//        hashSet.add("weibo");
//        hashSet.add("qqmail");
//        hashSet.add("fmessage");
//        hashSet.add("tmessage");
//        hashSet.add("floatbottle");
//        hashSet.add("lbsapp");
//        hashSet.add("shakeapp");
//        hashSet.add("medianote");
//        hashSet.add("qqfriend");
//        hashSet.add("readerapp");
//        hashSet.add("newsapp");
//        hashSet.add("blogapp");
//        hashSet.add("facebookapp");
//        hashSet.add("masssendapp");
//        hashSet.add("meishiapp");
//        hashSet.add("feedsapp");
//        hashSet.add("voipapp");
//        hashSet.add("filehelper");
//        hashSet.add("officialaccounts");
//        hashSet.add("helper_entry");
//        hashSet.add("pc_share");
//        hashSet.add("cardpackage");
//        hashSet.add("voicevoipapp");
//        hashSet.add("voiceinputapp");
//        hashSet.add("linkedinplugin");
//        hashSet.add("appbrandcustomerservicemsg");
//        return hashSet;
//    }
//
//    public static boolean fA(int i, int i2) {
//        return (i & i2) > 0;
//    }
//
//    public static int fB(int i, int i2) {
//        return (i2 ^ -1) & i;
//    }
//
//    public static int v(int... iArr) {
//        int i = 0;
//        int i2 = 0;
//        while (i < iArr.length) {
//            i++;
//            i2 = iArr[i] | i2;
//        }
//        return i2;
//    }
//
//    public static void init() {
//        if (cHQ()) {
//            if (!fA(vMs, 16777216)) {
//                vMs = v(vMs, 16777216);
//            }
//            if (!fA(vMt, 16777216)) {
//                vMt = v(vMt, 16777216);
//                return;
//            }
//            return;
//        }
//        if (fA(vMs, 16777216)) {
//            vMs &= -16777217;
//        }
//        if (fA(vMt, 16777216)) {
//            vMt &= -16777217;
//        }
//    }
//
//    public static boolean cHQ() {
//        y.i("MMSelectContactLogic", "config_val %s ", Integer.valueOf(((a) g.r(a.class)).AA().getInt("ShowOpenImInGroup", 0)));
//        return ((a) g.r(a.class)).AA().getInt("ShowOpenImInGroup", 0) != 0;
//    }
//}