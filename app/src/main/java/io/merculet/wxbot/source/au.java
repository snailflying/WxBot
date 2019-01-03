//package io.merculet.wxbot.config;
//
//import android.database.Cursor;
//import com.tencent.mm.ah.t;
//import com.tencent.mm.ai.o;
//import com.tencent.mm.compatible.b.f;
//import com.tencent.mm.h.a.z;
//import com.tencent.mm.kernel.api.c;
//import com.tencent.mm.kernel.api.e;
//import com.tencent.mm.model.b.d;
//import com.tencent.mm.modelstat.q;
//import com.tencent.mm.platformtools.g;
//import com.tencent.mm.plugin.appbrand.jsapi.audio.m;
//import com.tencent.mm.plugin.messenger.foundation.a.j;
//import com.tencent.mm.plugin.report.service.h;
//import com.tencent.mm.pointers.PInt;
//import com.tencent.mm.pointers.PString;
//import com.tencent.mm.protocal.c.aed;
//import com.tencent.mm.protocal.c.qh;
//import com.tencent.mm.protocal.k;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ai;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.storage.ak;
//import com.tencent.mm.storage.bd;
//import com.tencent.mm.storage.be;
//import com.tencent.mm.storage.be.b;
//import com.tencent.mm.storage.bi;
//import com.tencent.mm.storage.l;
//import com.tencent.mm.storage.n;
//import com.tencent.mm.storage.p;
//import com.tencent.smtt.utils.TbsLog;
//import com.tencent.ttpic.util.VideoMaterialUtil;
//import com.tencent.wxmm.v2helper;
//import java.util.HashMap;
//import junit.framework.Assert;
//
//public final class au {
//    private static au dVF = null;
//    private static final bu dVH = new bu();
//    private final ak dVE;
//    private f dVG = null;
//    private final int dVI = 1;
//    final com.tencent.mm.model.c.a dVJ;
//    private by dVK = new by();
//    private bq dVL = new bq();
//    private e dVM = new e();
//    private cb dVN = new cb();
//    private v dVO = new v();
//    private com.tencent.mm.storage.bd.a dVP = new com.tencent.mm.storage.bd.a() {
//        public final void a(bd bdVar, ad adVar) {
//            String str = adVar.field_username;
//            if (ad.hd(adVar.field_username)) {
//                adVar.setUsername(ad.aaZ(adVar.field_username));
//            }
//            if (bk.bl(adVar.vn())) {
//                adVar.dl(g.oZ(adVar.field_nickname));
//            }
//            if (bk.bl(adVar.vo())) {
//                adVar.dm(g.oY(adVar.field_nickname));
//            }
//            if (bk.bl(adVar.field_conRemarkPYShort)) {
//                adVar.dp(g.oZ(adVar.field_conRemark));
//            }
//            if (bk.bl(adVar.field_conRemarkPYFull)) {
//                adVar.m1do(g.oY(adVar.field_conRemark));
//            }
//            if (s.e(adVar)) {
//                adVar.fg(43);
//                adVar.dl(g.oZ(adVar.Bp()));
//                adVar.dm(g.oY(adVar.Bp()));
//                adVar.m1do(g.oY(adVar.Bq()));
//                adVar.dp(adVar.Bq());
//                return;
//            }
//            if (s.hU(str)) {
//                ad adVar2;
//                adVar.AH();
//                adVar.fl(4);
//                adVar.fg(33);
//                if (adVar == null) {
//                    adVar2 = new ad();
//                } else {
//                    adVar2 = adVar;
//                }
//                adVar2.setUsername(str);
//                adVar2.AH();
//                aa.y(adVar2);
//                adVar2.AQ();
//            }
//            if (adVar.Bi()) {
//                adVar.fg(adVar.AE());
//            }
//            if (s.hK(str)) {
//                y.i("MicroMsg.MMCore", "update official account helper showhead %d", Integer.valueOf(31));
//                adVar.fg(31);
//            }
//            if (adVar.Bg()) {
//                au.Hx();
//                c.FB().d(new String[]{str}, "@blacklist");
//            }
//            y.d("MicroMsg.MMCore", "onPreInsertContact2: %s, %s", adVar.field_username, adVar.vn());
//        }
//    };
//    private com.tencent.mm.storage.be.a dVQ = new com.tencent.mm.storage.be.a() {
//        public final void a(ak akVar, be beVar) {
//            if (akVar != null) {
//                if (!ad.aaR(akVar.field_username) && !ad.aaT(akVar.field_username) && !ad.hd(akVar.field_username) && !s.hl(akVar.field_username) && !ad.aaU(akVar.field_username)) {
//                    return;
//                }
//                if (ad.hd(akVar.field_username)) {
//                    int i;
//                    ak akVar2;
//                    ak abv = beVar.abv("floatbottle");
//                    if (abv == null) {
//                        i = 1;
//                        akVar2 = new ak("floatbottle");
//                    } else {
//                        boolean i2 = false;
//                        akVar2 = abv;
//                    }
//                    akVar2.fz(1);
//                    akVar2.fy(k.Gf());
//                    au.Hx();
//                    bi HC = c.Fy().HC(" and not ( type = 10000 and isSend != 2 ) ");
//                    if (HC == null || HC.field_msgId <= 0) {
//                        akVar2.cuB();
//                    } else {
//                        akVar2.ai(HC);
//                        akVar2.setContent(ad.aaZ(HC.field_talker) + VideoMaterialUtil.FRAMES_ID_SEPARATOR_3D + HC.field_content);
//                        akVar2.dP(Integer.toString(HC.getType()));
//                        b tt = beVar.tt();
//                        if (tt != null) {
//                            PString pString = new PString();
//                            PString pString2 = new PString();
//                            PInt pInt = new PInt();
//                            HC.ec("floatbottle");
//                            HC.setContent(akVar2.field_content);
//                            tt.a(HC, pString, pString2, pInt, false);
//                            akVar2.dQ(pString.value);
//                            akVar2.dR(pString2.value);
//                            akVar2.fB(pInt.value);
//                        }
//                    }
//                    if (i2 != 0) {
//                        beVar.d(akVar2);
//                    } else {
//                        beVar.a(akVar2, akVar2.field_username);
//                    }
//                } else if (!ad.aaR(akVar.field_username) && !ad.aaT(akVar.field_username)) {
//                    com.tencent.mm.kernel.g.r(o.class);
//                    p.b(akVar, beVar);
//                    if ("@blacklist".equals(akVar.field_parentRef)) {
//                        au.Hx();
//                        ad abl = c.Fw().abl(akVar.field_username);
//                        if (abl != null && !bk.bl(abl.field_username) && !abl.Bg()) {
//                            au.Hx();
//                            c.FB().d(new String[]{akVar.field_username}, "");
//                        }
//                    }
//                }
//            }
//        }
//    };
//    private com.tencent.mm.storage.be.a dVR = new com.tencent.mm.storage.be.a() {
//        public final void a(ak akVar, be beVar) {
//            if (akVar == null) {
//            }
//        }
//    };
//    private f dVS = new f();
//    private final c dVv;
//
//    class a implements com.tencent.mm.kernel.a.b.b, com.tencent.mm.kernel.api.a, c, e, com.tencent.mm.kernel.c.a {
//        a() {
//        }
//
//        public void onAccountInitialized(com.tencent.mm.kernel.e.c cVar) {
//            if (cVar.dKL) {
//                com.tencent.mm.y.c.BS().v(262145, false);
//            }
//            h.nFQ.a(598, 13, 1, false);
//            long currentTimeMillis = System.currentTimeMillis();
//            y.i("MicroMsg.MMCore", "summerasyncinit onAccountInitialized start tid[%d]", Long.valueOf(Thread.currentThread().getId()));
//            c a = au.this.dVv;
//            com.tencent.mm.cf.h hVar = com.tencent.mm.kernel.g.DP().dKu;
//            com.tencent.mm.cf.h hVar2 = com.tencent.mm.kernel.g.DP().dKv;
//            com.tencent.mm.plugin.messenger.foundation.a.a.g bhO = ((j) com.tencent.mm.kernel.g.r(j.class)).bhO();
//            bhO.a(new com.tencent.mm.storage.au(bhO));
//            com.tencent.mm.storage.e hVar3 = new com.tencent.mm.storage.h(bhO);
//            a.dUj = hVar3;
//            bhO.a(hVar3);
//            a.dUh = new bp(hVar, ((j) com.tencent.mm.kernel.g.r(j.class)).FB());
//            a.dUi = new bo(hVar, ((j) com.tencent.mm.kernel.g.r(j.class)).Fw());
//            a.dUk = new com.tencent.mm.model.b.c();
//            a.dUl = new d();
//            a.dUp = new com.tencent.mm.model.b.b();
//            a.dUm = new com.tencent.mm.storage.j(hVar);
//            a.dUn = new n(hVar);
//            a.dUo = new l(hVar);
//            h.nFQ.a(598, 14, 1, false);
//            y.i("MicroMsg.MMCore", "summerasyncinit onAccountInitialized done initDB take[%d]ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//            com.tencent.mm.model.c.a aVar = au.this.dVJ;
//            au.this.dVv;
//            aVar.bG(cVar.dKL);
//            h.nFQ.a(598, 15, 1, false);
//            y.i("MicroMsg.MMCore", "summerasyncinit onAccountInitialized done onAccountPostReset take[%d]ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//            aVar = au.this.dVJ;
//            com.tencent.mm.compatible.util.f.zF();
//            h.nFQ.a(598, 16, 1, false);
//            y.i("MicroMsg.MMCore", "summerasyncinit onAccountInitialized done all take[%d]ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//        }
//
//        public void onAccountRelease() {
//        }
//
//        public void parallelsDependency() {
//            com.tencent.mm.kernel.a.b.a.a(this, c.class).ao(com.tencent.mm.kernel.g.t(com.tencent.mm.plugin.messenger.foundation.a.p.class));
//        }
//
//        public void onDataBaseOpened(com.tencent.mm.cf.h hVar, com.tencent.mm.cf.h hVar2) {
//            com.tencent.mm.model.c.a aVar = au.this.dVJ;
//            au.this.dVv;
//        }
//
//        public void onDataBaseClosed(com.tencent.mm.cf.h hVar, com.tencent.mm.cf.h hVar2) {
//        }
//
//        public HashMap<Integer, com.tencent.mm.cf.h.d> collectDatabaseFactory() {
//            y.i("MicroMsg.MMCore", "collectDatabaseFactory ");
//            HashMap hashMap = new HashMap();
//            hashMap.putAll(c.dgp);
//            return hashMap;
//        }
//    }
//
//    public static boolean Hp() {
//        return dVF == null;
//    }
//
//    public static void a(com.tencent.mm.network.n nVar) {
//        com.tencent.mm.kernel.g.DQ();
//        com.tencent.mm.kernel.g.DO().a(nVar);
//    }
//
//    public static void b(com.tencent.mm.network.n nVar) {
//        com.tencent.mm.kernel.g.DQ();
//        com.tencent.mm.kernel.g.DO().b(nVar);
//    }
//
//    public static void a(aj ajVar) {
//        com.tencent.mm.kernel.b.a(ajVar);
//    }
//
//    public static void gc(String str) {
//        com.tencent.mm.kernel.a.gc(str);
//    }
//
//    public static bu Hq() {
//        Hu();
//        return dVH;
//    }
//
//    public static boolean Hr() {
//        return com.tencent.mm.kernel.a.CV();
//    }
//
//    public static void Hs() {
//        com.tencent.mm.kernel.a.bF(false);
//    }
//
//    public static void a(ak akVar, com.tencent.mm.ah.p.a aVar) {
//        dVF = new au(akVar, aVar);
//        com.tencent.mm.modelstat.o.bE(ae.getContext());
//    }
//
//    public static al getNotification() {
//        return Hu().dVE.getNotification();
//    }
//
//    public static ad tu() {
//        return Hu().dVE.tu();
//    }
//
//    private au(ak akVar, com.tencent.mm.ah.p.a aVar) {
//        this.dVE = akVar;
//        this.dVJ = new com.tencent.mm.model.c.a() {
//            public final void bG(boolean z) {
//                ad adVar;
//                y.i("MicroMsg.MMCore", "summeranrt2 onAccountPostReset tid:%d stack[%s]", Long.valueOf(Thread.currentThread().getId()), bk.csb());
//                au.b(au.this);
//                long currentTimeMillis = System.currentTimeMillis();
//                au.Hx();
//                long eV = c.Dv().eV(Thread.currentThread().getId());
//                au.this.dVv;
//                au.Ht();
//                if (z) {
//                    c.Fw().abr("readerapp");
//                    c.Dz().o(256, Boolean.valueOf(true));
//                    ab.He();
//                    ab.He();
//                    Cursor cup = c.Fw().cup();
//                    if (cup.moveToFirst()) {
//                        do {
//                            adVar = new ad();
//                            adVar.d(cup);
//                            adVar.AT();
//                            c.Fw().a(adVar.field_username, adVar);
//                        } while (cup.moveToNext());
//                    }
//                    cup.close();
//                    bf.iO("ver" + com.tencent.mm.protocal.d.spa);
//                }
//                aa aaVar = new aa(au.this.dVv);
//                if (z) {
//                    aa.x(c.Fw().abl("filehelper"));
//                    String Gj = q.Gj();
//                    if (!bk.bl(Gj)) {
//                        adVar = c.Fw().abl(Gj);
//                        if (((int) adVar.dBe) == 0) {
//                            adVar.setUsername(Gj);
//                            adVar.AH();
//                            au.Hx();
//                            c.Fw().V(adVar);
//                        } else if (!com.tencent.mm.n.a.gR(adVar.field_type)) {
//                            adVar.AH();
//                            au.Hx();
//                            c.Fw().a(Gj, adVar);
//                        }
//                    }
//                    aa.a(z, "qqmail", false);
//                    aa.a(z, "floatbottle", false);
//                    aa.a(z, "shakeapp", false);
//                    aa.a(z, "lbsapp", false);
//                    aa.a(z, "medianote", false);
//                    aa.a(z, "newsapp", true);
//                    c.FB().abu("blogapp");
//                    c.Fy().HG("blogapp");
//                    c.Fw().abr("blogapp");
//                    aa.a(z, "facebookapp", true);
//                    aa.a(z, "qqfriend", false);
//                    aa.a(z, "masssendapp", true);
//                    aa.a(z, "feedsapp", true);
//                    c.FB().abu("tmessage");
//                    c.Fw().abr("tmessage");
//                    adVar = c.Fw().abl("voip");
//                    com.tencent.mm.n.a abl = c.Fw().abl("voipapp");
//                    if (abl == null) {
//                        abl = new ad();
//                    }
//                    if (!(adVar == null || ((int) adVar.dBe) == 0)) {
//                        c.Fw().abr("voip");
//                    }
//                    if (((int) abl.dBe) == 0) {
//                        abl.setUsername("voipapp");
//                        abl.AH();
//                        aa.y(abl);
//                        abl.fl(4);
//                        abl.AQ();
//                        c.Fw().W(abl);
//                    } else if (z) {
//                        abl.AQ();
//                        c.Fw().a("voipapp", abl);
//                    }
//                    abl = c.Fw().abl("officialaccounts");
//                    if (abl == null) {
//                        abl = new ad();
//                    }
//                    y.i("MicroMsg.HardCodeHelper", "hardcodeOfficialAccounts:update[%B], contactID[%d]", Boolean.valueOf(z), Integer.valueOf((int) abl.dBe));
//                    if (((int) abl.dBe) == 0) {
//                        abl.setUsername("officialaccounts");
//                        abl.AI();
//                        aa.y(abl);
//                        abl.fl(3);
//                        c.Fw().W(abl);
//                    } else if (z) {
//                        y.i("MicroMsg.HardCodeHelper", "do update hardcode official accounts");
//                        abl.AI();
//                        c.Fw().a("officialaccounts", abl);
//                    }
//                    adVar = c.Fw().abl("voipaudio");
//                    abl = c.Fw().abl("voicevoipapp");
//                    if (abl == null) {
//                        abl = new ad();
//                    }
//                    if (!(adVar == null || ((int) adVar.dBe) == 0)) {
//                        c.Fw().abr("voipaudio");
//                    }
//                    if (((int) abl.dBe) == 0) {
//                        abl.setUsername("voicevoipapp");
//                        abl.AH();
//                        aa.y(abl);
//                        abl.fl(4);
//                        abl.AQ();
//                        c.Fw().W(abl);
//                    } else if (z) {
//                        abl.AQ();
//                        c.Fw().a("voicevoipapp", abl);
//                    }
//                    aa.a(z, "voiceinputapp", false);
//                    aa.a(z, "linkedinplugin", false);
//                    abl = c.Fw().abl("notifymessage");
//                    if (abl == null) {
//                        abl = new ad();
//                    }
//                    y.i("MicroMsg.HardCodeHelper", "hardcodeOfficialAccounts:update[%B], contactID[%d]", Boolean.valueOf(z), Integer.valueOf((int) abl.dBe));
//                    if (((int) abl.dBe) == 0) {
//                        abl.setUsername("notifymessage");
//                        abl.AI();
//                        aa.y(abl);
//                        abl.fl(3);
//                        c.Fw().W(abl);
//                    } else if (z) {
//                        y.i("MicroMsg.HardCodeHelper", "do update hardcode official accounts");
//                        abl.AI();
//                        c.Fw().a("notifymessage", abl);
//                    }
//                    abl = c.Fw().abl("appbrandcustomerservicemsg");
//                    if (abl == null) {
//                        abl = new ad();
//                    }
//                    y.i("MicroMsg.HardCodeHelper", "hardcodeAppBrandServiceMessage:update[%B], contactID[%d]", Boolean.valueOf(z), Integer.valueOf((int) abl.dBe));
//                    if (((int) abl.dBe) == 0) {
//                        abl.setUsername("appbrandcustomerservicemsg");
//                        abl.AI();
//                        aa.y(abl);
//                        abl.setType(0);
//                        abl.fl(3);
//                        c.Fw().W(abl);
//                    } else if (z) {
//                        y.i("MicroMsg.HardCodeHelper", "do update app brand service message accunt");
//                        abl.AI();
//                        c.Fw().a("appbrandcustomerservicemsg", abl);
//                    }
//                    aa.a(z, "downloaderapp", true);
//                    if (z) {
//                        au.Hx();
//                        c.FB().abu("Weixin");
//                        au.Hx();
//                        c.Fw().abr("Weixin");
//                    }
//                }
//                au.Hx();
//                c.Dv().hI(eV);
//                y.i("MicroMsg.MMCore", "summeranrt dkwt set forceManual :%b, tid[%d], take[%s]ms, stack[%s]", Boolean.valueOf(z), Long.valueOf(Thread.currentThread().getId()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), bk.csb());
//                au.this.dVv;
//                c.FB().a(au.Hu().dVE.tt());
//            }
//        };
//        com.tencent.mm.kernel.g.DQ();
//        com.tencent.mm.kernel.g.a(a.class, new a());
//        com.tencent.mm.kernel.g.DQ().dKW.aj(aVar);
//        this.dVv = new c();
//        h.nFQ.a(99, 142, 1, false);
//        t.a(new com.tencent.mm.plugin.zero.a.e() {
//            public final byte[] a(k.d dVar, int i) {
//                switch (i) {
//                    case 107:
//                        return ((com.tencent.mm.plugin.account.friend.a.u.a) dVar).fge.sBt.tFM.toByteArray();
//                    case 145:
//                        return ((com.tencent.mm.protocal.m.a) dVar).spS.sBt.tFM.toByteArray();
//                    case v2helper.EMethodSetIosMicAbCheckOff /*429*/:
//                        return ((com.tencent.mm.plugin.account.friend.a.t.a) dVar).fgc.sBt.tFM.toByteArray();
//                    case m.CTRL_INDEX /*481*/:
//                        return ((com.tencent.mm.plugin.account.friend.a.r.a) dVar).ffZ.sBt.tFM.toByteArray();
//                    case com.tencent.mm.plugin.appbrand.jsapi.storage.j.CTRL_INDEX /*499*/:
//                        return ((com.tencent.mm.plugin.report.b.c.a) dVar).nES.sBt.tFM.toByteArray();
//                    case 572:
//                        return ((com.tencent.mm.plugin.account.friend.a.s.a) dVar).fgb.sBt.tFM.toByteArray();
//                    case 616:
//                        return ((com.tencent.mm.model.ay.a) dVar).dWa.sBt.tFM.toByteArray();
//                    case 617:
//                        return ((com.tencent.mm.model.ba.a) dVar).dWe.sBt.tFM.toByteArray();
//                    case 618:
//                        return ((com.tencent.mm.model.az.a) dVar).dWc.sBt.tFM.toByteArray();
//                    case 694:
//                        return ((com.tencent.mm.plugin.report.b.b.a) dVar).nES.sBt.tFM.toByteArray();
//                    case 722:
//                        return ((com.tencent.mm.protocal.p.a) dVar).sqa.sSQ.sBt.tFM.toByteArray();
//                    case 987:
//                    case TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR /*997*/:
//                        return ((qh) ((com.tencent.mm.ah.b.b) dVar).ecN).sNZ.toByteArray();
//                    case 989:
//                        return ((aed) ((com.tencent.mm.ah.b.b) dVar).ecN).sNZ.toByteArray();
//                    case 1000:
//                        return ((com.tencent.mm.protocal.j.a) dVar).spC;
//                    default:
//                        return null;
//                }
//            }
//        });
//        com.tencent.mm.kernel.g.DQ();
//        com.tencent.mm.kernel.b DO = com.tencent.mm.kernel.g.DO();
//        DO.dJS.aj(new com.tencent.mm.kernel.api.d() {
//            public final void b(com.tencent.mm.network.e eVar) {
//                q.e(eVar);
//                q.f(eVar);
//            }
//        });
//    }
//
//    public static void Ht() {
//    }
//
//    private static au Hu() {
//        Assert.assertNotNull("MMCore not initialized by MMApplication", dVF);
//        return dVF;
//    }
//
//    public static com.tencent.mm.storage.y Hv() {
//        com.tencent.mm.kernel.g.DQ();
//        return com.tencent.mm.kernel.g.DP().dKo;
//    }
//
//    public static ai DS() {
//        return com.tencent.mm.kernel.g.DS();
//    }
//
//    public static bx getSysCmdMsgExtension() {
//        return ((com.tencent.mm.plugin.messenger.foundation.a.p) com.tencent.mm.kernel.g.t(com.tencent.mm.plugin.messenger.foundation.a.p.class)).getSysCmdMsgExtension();
//    }
//
//    public static String Df() {
//        return com.tencent.mm.kernel.a.Df();
//    }
//
//    public static void Hw() {
//        com.tencent.mm.sdk.b.a.udP.m(new z());
//    }
//
//    public static c Hx() {
//        c cVar = Hu().dVv;
//        Assert.assertTrue("MMCore has not been initialize ?", cVar != null);
//        return cVar;
//    }
//
//    public static com.tencent.mm.ah.p Dk() {  //见p类
//        com.tencent.mm.kernel.g.DQ();
//        return com.tencent.mm.kernel.g.DO().dJT;
//    }
//
//    public static f Hy() {
//        return f.yi();
//    }
//
//    public static boolean Hz() {
//        com.tencent.mm.kernel.g.DN();
//        return com.tencent.mm.kernel.a.Db();
//    }
//
//    public static boolean DK() {
//        if (ae.cqV()) {
//            return com.tencent.mm.kernel.g.DK();
//        }
//        return false;
//    }
//
//    public static boolean CW() {
//        return com.tencent.mm.kernel.a.CW();
//    }
//
//    public static void hold() {
//        com.tencent.mm.kernel.a.hold();
//    }
//
//    public static void unhold() {
//        com.tencent.mm.kernel.a.unhold();
//    }
//
//    public static String CM() {
//        com.tencent.mm.kernel.g.DQ();
//        com.tencent.mm.kernel.g.DN();
//        return com.tencent.mm.kernel.a.CM();
//    }
//
//    public static f HA() {
//        return Hu().dVS;
//    }
//}