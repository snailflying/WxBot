//package com.tencent.mm.ui.contact;
//
//import android.app.Activity;
//import com.tencent.liteav.network.TXCStreamUploader;
//import com.tencent.mm.R;
//import com.tencent.mm.ag.h;
//import com.tencent.mm.ag.o;
//import com.tencent.mm.ai.d;
//import com.tencent.mm.ai.f;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.model.au;
//import com.tencent.mm.model.c;
//import com.tencent.mm.openim.a.b;
//import com.tencent.mm.openim.a.b.a;
//import com.tencent.mm.sdk.platformtools.ai;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.storage.ad;
//import java.util.List;
//
//public final class z {
//    public static String adW(String str) {
//        h kp = o.Kh().kp(str);
//        au.Hx();
//        ad abl = c.Fw().abl(str);
//        String aK;
//        if (ad.aaU(str)) {
//            String str2 = abl.field_openImAppid;
//            String c = ((b) g.r(b.class)).c(abl.field_openImAppid, "openim_desc_icon", a.TYPE_URL);
//            aK = ((b) g.r(b.class)).aK(abl.field_openImAppid, abl.field_descWordingId);
//            return String.format(" <msg bigheadimgurl=\"%s\" smallheadimgurl=\"%s\" username=\"%s\" nickname=\"%s\" sex=\"%d\"  openimappid=\"%s\" openimdesc=\"%s\" openimdescicon=\"%s\"/>", new Object[]{bk.ZP(kp.JX()), bk.ZP(kp.JY()), bk.ZP(abl.field_username), bk.ZP(abl.Bp()), Integer.valueOf(abl.sex), str2, bk.ZP(aK), bk.ZP(c)});
//        }
//        int i = abl.cCq;
//        if (kp != null) {
//            i = kp.cCq;
//        }
//        d kX = abl.cua() ? f.kX(abl.field_username) : null;
//        String str3 = "<msg username=\"%s\" nickname=\"%s\" alias=\"%s\" fullpy=\"%s\" shortpy=\"%s\" imagestatus=\"%d\" scene=\"17\" province=\"%s\" city=\"%s\" sign=\"%s\" percard=\"%d\" sex=\"%d\" certflag=\"%d\" certinfo=\"%s\" certinfoext=\"\" brandIconUrl=\"%s\" brandHomeUrl=\"\" brandSubscriptConfigUrl=\"\" brandFlags=\"\" regionCode=\"%s\"/>";
//        Object[] objArr = new Object[15];
//        objArr[0] = bk.ZP(abl.field_username);
//        objArr[1] = bk.ZP(abl.Bp());
//        objArr[2] = bk.ZP(abl.vk());
//        objArr[3] = bk.ZP(abl.Bp());
//        objArr[4] = bk.ZP(abl.vn());
//        objArr[5] = Integer.valueOf(i);
//        objArr[6] = bk.ZP(abl.getProvince());
//        objArr[7] = bk.ZP(abl.getCity());
//        objArr[8] = bk.ZP(abl.signature);
//        objArr[9] = Integer.valueOf(abl.cCz);
//        objArr[10] = Integer.valueOf(abl.sex);
//        objArr[11] = Integer.valueOf(abl.field_verifyFlag);
//        objArr[12] = bk.ZP(abl.cCF);
//        if (kX == null) {
//            aK = "";
//        } else {
//            aK = bk.ZP(kX.field_brandIconURL);
//        }
//        objArr[13] = aK;
//        objArr[14] = bk.ZP(abl.cCG);
//        return String.format(str3, objArr);
//    }
//
//    public static void a(final Activity activity, String str, List<String> list) {
//        for (String str2 : list) {
//            au.Dk().a(new com.tencent.mm.modelmulti.h(str, adW(str2), ad.aaU(str2) ? 66 : 42), 0);
//        }
//        com.tencent.mm.ui.widget.snackbar.b.h(activity, activity.getString(R.l.has_send));
//        com.tencent.mm.plugin.report.service.h.nFQ.f((int) TXCStreamUploader.TXE_UPLOAD_INFO_NET_BUSY, str, Integer.valueOf(3), Integer.valueOf(list.size()));
//        activity.setResult(-1);
//        ai.l(new Runnable() {
//            public final void run() {
//                activity.finish();
//            }
//        }, 1800);
//    }
//}