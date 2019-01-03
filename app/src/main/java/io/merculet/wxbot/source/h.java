//package io.merculet.wxbot.config;
//
//import com.tencent.mars.cdn.CdnLogic;
//import com.tencent.mm.ah.b;
//import com.tencent.mm.ah.b.a;
//import com.tencent.mm.ah.f;
//import com.tencent.mm.ah.m;
//import com.tencent.mm.h.a.pf;
//import com.tencent.mm.h.a.ph;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.model.bd;
//import com.tencent.mm.model.o;
//import com.tencent.mm.modelsimple.ab;
//import com.tencent.mm.network.e;
//import com.tencent.mm.network.k;
//import com.tencent.mm.platformtools.ae;
//import com.tencent.mm.plugin.appbrand.jsapi.audio.d;
//import com.tencent.mm.plugin.appbrand.jsapi.storage.c;
//import com.tencent.mm.plugin.messenger.foundation.a.j;
//import com.tencent.mm.plugin.messenger.foundation.a.q;
//import com.tencent.mm.protocal.c.awn;
//import com.tencent.mm.protocal.c.awo;
//import com.tencent.mm.protocal.c.bml;
//import com.tencent.mm.protocal.c.bpc;
//import com.tencent.mm.protocal.c.bpd;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.bi;
//import com.tencent.wcdb.database.SQLiteException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map.Entry;
//import junit.framework.Assert;
//
//public final class h extends m implements k {
//    private static final List<Object> esN = new ArrayList();
//    public long bIt;
//    private b dmK;
//    private f dmL;
//    private final List<bi> esO = new LinkedList();
//    private int esP = 3;
//    private boolean esQ = false;
//    private final List<bi> esR = new ArrayList();
//    private bi esS = null;
//    public com.tencent.mm.plugin.messenger.foundation.a.m esT = null;
//
//    static /* synthetic */ void R(List list) {
//        int i = 0;
//        while (true) {
//            int i2 = i;
//            if (i2 < list.size()) {
//                u((bi) list.get(i2));
//                i = i2 + 1;
//            } else {
//                return;
//            }
//        }
//    }
//
//    static /* synthetic */ void S(List list) {
//        int i = 0;
//        while (true) {
//            int i2 = i;
//            if (i2 < list.size()) {
//                t((bi) list.get(i2));
//                i = i2 + 1;
//            } else {
//                return;
//            }
//        }
//    }
//
//    public h(String str, String str2, int i, int i2, Object obj) {
//        y.d("MicroMsg.NetSceneSendMsg", "dktext :%s", bk.csb());
//        if (!bk.bl(str)) {
//            bi biVar = new bi();
//            biVar.setStatus(1);
//            biVar.ec(str);
//            biVar.bg(bd.iK(str));
//            biVar.fA(1);
//            biVar.setContent(str2);
//            biVar.setType(i);
//            String a = a(((q) g.r(q.class)).v(biVar), obj, i2);
//            if (!bk.bl(a)) {
//                biVar.cY(a);
//                y.d("MicroMsg.NetSceneSendMsg", "NetSceneSendMsg:MsgSource:%s", biVar.czr);
//            }
//            try {
//                this.bIt = ((j) g.r(j.class)).bhO().b(biVar, true);
//                if (this.bIt == -1) {
//                    com.tencent.mm.plugin.report.f.nEG.a(111, 255, 1, false);
//                }
//                Assert.assertTrue(this.bIt != -1);
//                y.i("MicroMsg.NetSceneSendMsg", "new msg inserted to db , local id = " + this.bIt);
//            } catch (SQLiteException e) {
//                com.tencent.mm.plugin.report.f.nEG.a(111, 255, 1, false);
//                throw e;
//            }
//        }
//    }
//
//    public h(String str, String str2, int i) {
//        boolean z = false;
//        y.d("MicroMsg.NetSceneSendMsg", "dktext :%s", bk.csb());
//        if (!bk.bl(str)) {
//            bi biVar = new bi();
//            biVar.setStatus(1);
//            biVar.ec(str);
//            biVar.bg(bd.iK(str));
//            biVar.fA(1);
//            biVar.setContent(str2);
//            biVar.setType(i);
//            String v = ((q) g.r(q.class)).v(biVar);
//            if (!bk.bl(v)) {
//                biVar.cY(v);
//                y.d("MicroMsg.NetSceneSendMsg", "NetSceneSendMsg:MsgSource:%s", biVar.czr);
//            }
//            try {
//                this.bIt = ((j) g.r(j.class)).bhO().b(biVar, true);
//                if (this.bIt == -1) {
//                    com.tencent.mm.plugin.report.f.nEG.a(111, 255, 1, false);
//                }
//                if (this.bIt != -1) {
//                    z = true;
//                }
//                Assert.assertTrue(z);
//                y.i("MicroMsg.NetSceneSendMsg", "new msg inserted to db , local id = " + this.bIt);
//            } catch (SQLiteException e) {
//                com.tencent.mm.plugin.report.f.nEG.a(111, 255, 1, false);
//                throw e;
//            }
//        }
//    }
//
//    public h(long j) {
//        y.i("MicroMsg.NetSceneSendMsg", "resend msg , local id = " + j);
//        this.bIt = j;
//        this.esS = ((j) g.r(j.class)).bhO().fd(j);
//        if (this.esS == null) {
//            y.e("MicroMsg.NetSceneSendMsg", "resend msg , msg is null localid:%d", Long.valueOf(j));
//        }
//    }
//
//    public h() {
//        y.d("MicroMsg.NetSceneSendMsg", "dktext :%s", bk.csb());
//        y.i("MicroMsg.NetSceneSendMsg", "empty msg sender created");
//    }
//
//    public final boolean Kx() {
//        return true;
//    }
//
//    protected final int Ka() {
//        return 10;
//    }
//
//    public final boolean Ky() {
//        boolean Ky = super.Ky();
//        if (Ky) {
//            com.tencent.mm.plugin.report.f.nEG.a(111, 254, 1, false);
//        }
//        return Ky;
//    }
//
//    protected final b b(com.tencent.mm.network.q qVar) {
//        return this.esO.size() > 0 ? b.EOk : b.EFailed;
//    }
//
//    private static String a(String str, Object obj, int i) {
//        String str2 = "MicroMsg.NetSceneSendMsg";
//        String str3 = "[mergeMsgSource] rawSource:%s args is null:%s flag:%s";
//        Object[] objArr = new Object[3];
//        objArr[0] = str;
//        objArr[1] = Boolean.valueOf(obj == null);
//        objArr[2] = Integer.valueOf(i);
//        y.i(str2, str3, objArr);
//        if (!bk.bl(str) && !str.startsWith("<msgsource>")) {
//            y.w("MicroMsg.NetSceneSendMsg", "[mergeMsgSource] WTF the msgsource is right? %s", str);
//            return str;
//        } else if (i != d.CTRL_INDEX || !(obj instanceof HashMap)) {
//            return str;
//        } else {
//            StringBuffer stringBuffer = new StringBuffer();
//            if (bk.bl(str)) {
//                stringBuffer.append("<msgsource>");
//            }
//            for (Entry entry : ((HashMap) obj).entrySet()) {
//                str2 = (String) entry.getValue();
//                String str4 = (String) entry.getKey();
//                if (bk.bl(str2) || bk.bl(str4)) {
//                    y.w("MicroMsg.NetSceneSendMsg", "%s %s", str4, str2);
//                } else {
//                    stringBuffer.append("<").append(str4).append(">");
//                    stringBuffer.append(str2);
//                    stringBuffer.append("</").append(str4).append(">");
//                }
//            }
//            if (!bk.bl(str)) {
//                return str.replace("<msgsource>", "<msgsource>" + stringBuffer.toString());
//            }
//            stringBuffer.append("</msgsource>");
//            return stringBuffer.toString();
//        }
//    }
//
//    public final int a(e eVar, f fVar) {
//        List bia;
//        this.dmL = fVar;
//        a aVar = new a();
//        aVar.ecH = new bpc();
//        aVar.ecI = new bpd();
//        aVar.uri = "/cgi-bin/micromsg-bin/newsendmsg";
//        aVar.ecG = c.CTRL_INDEX;
//        aVar.ecJ = 237;
//        aVar.ecK = 1000000237;
//        this.dmK = aVar.Kt();
//        bpc bpc = (bpc) this.dmK.ecE.ecN;
//        if (this.esS == null) {
//            bia = ((j) g.r(j.class)).bhO().bia();
//        } else {
//            if (this.esS.field_status != 5) {
//                y.w("MicroMsg.NetSceneSendMsg", "msg:%d status:%d should not be resend !", Long.valueOf(this.esS.field_msgId), Integer.valueOf(this.esS.field_status));
//            }
//            this.esS.setStatus(1);
//            ((j) g.r(j.class)).bhO().a(this.bIt, this.esS);
//            ArrayList arrayList = new ArrayList();
//            arrayList.add(this.esS);
//            this.esS = null;
//            Object bia2 = arrayList;
//        }
//        if (bia2.size() == 0) {
//            y.w("MicroMsg.NetSceneSendMsg", "no sending message");
//            return -2;
//        }
//        this.esO.clear();
//        for (int i = 0; i < bia2.size(); i++) {
//            bi biVar = (bi) bia2.get(i);
//            if (biVar.field_isSend == 1) {
//                awn awn = new awn();
//                awn.svG = new bml().YI(biVar.field_talker);
//                awn.mPL = (int) (biVar.field_createTime / 1000);
//                awn.hQR = biVar.getType();
//                awn.kVs = biVar.field_content;
//                awn.ttf = o.l(com.tencent.mm.model.q.Gj(), biVar.field_createTime).hashCode();
//                if (this.esT == null) {
//                    this.esT = ((q) g.r(q.class)).Pk();
//                }
//                y.i("MicroMsg.NetSceneSendMsg", "using message source assembler %s", this.esT);
//                this.esT.a(awn, biVar);
//                y.i("MicroMsg.NetSceneSendMsg", "reqCmd.MsgSource:%s", awn.svK);
//                bpc.hPT.add(awn);
//                bpc.hPS = bpc.hPT.size();
//                this.esO.add(biVar);
//            }
//        }
//        int a = a(eVar, this.dmK, this);
//        if (a >= 0) {
//            return a;
//        }
//        y.i("MicroMsg.NetSceneSendMsg", "mark all failed. do scene %d", Integer.valueOf(a));
//        Pg();
//        return a;
//    }
//
//    public final int getType() {
//        return c.CTRL_INDEX;
//    }
//
//    private void iR(int i) {
//        if (this.esO == null) {
//            y.e("MicroMsg.NetSceneSendMsg", "publishMsgSendFailEvent, sendingList is null");
//        } else if (i >= this.esO.size() || i < 0) {
//            y.e("MicroMsg.NetSceneSendMsg", "publishMsgSendFailEvent, index:%d, sendingList.size:%d", Integer.valueOf(i), Integer.valueOf(this.esO.size()));
//        } else {
//            t((bi) this.esO.get(i));
//        }
//    }
//
//    private static void t(bi biVar) {
//        pf pfVar = new pf();
//        pfVar.bYS.bFH = biVar;
//        com.tencent.mm.sdk.b.a.udP.m(pfVar);
//        y.d("MicroMsg.NetSceneSendMsg", "publishMsgSendFailEvent for msgId:%d", Long.valueOf(biVar.field_msgId));
//    }
//
//    public final void a(int i, int i2, int i3, String str, com.tencent.mm.network.q qVar, byte[] bArr) {
//        int i4;
//        if (i2 == 0 && i3 == 0) {
//            LinkedList linkedList = ((bpd) this.dmK.ecF.ecN).hPT;
//            ArrayList arrayList = new ArrayList();
//            if (this.esO.size() == linkedList.size()) {
//                i4 = 0;
//                while (true) {
//                    int i5 = i4;
//                    if (i5 >= linkedList.size()) {
//                        y.i("MicroMsg.NetSceneSendMsg", "summerdktext total  [%d]msgs sent successfully, [%d]msgs need verifypsw", Integer.valueOf(i5 - this.esR.size()), Integer.valueOf(this.esR.size()));
//                        break;
//                    }
//                    awo awo = (awo) linkedList.get(i5);
//                    if (awo.sze != 0 || ae.eTw) {
//                        com.tencent.mm.plugin.report.f.nEG.a(111, 252, 1, false);
//                        if (awo.sze == -49 || ae.eTw) {
//                            y.i("MicroMsg.NetSceneSendMsg", "summerdktext send msg failed: item ret code[%d], index[%d], testVerifyPsw[%b], retryVerifyCount[%d]", Integer.valueOf(awo.sze), Integer.valueOf(i5), Boolean.valueOf(ae.eTw), Integer.valueOf(this.esP));
//                            if (this.esQ) {
//                                this.esR.add((bi) this.esO.get(i5));
//                            } else if (this.esP < 0) {
//                                iS(i5);
//                                this.dmL.onSceneEnd(4, awo.sze, str, this);
//                                iR(i5);
//                                return;
//                            } else {
//                                this.esQ = true;
//                                this.esP--;
//                                this.esR.add((bi) this.esO.get(i5));
//                                final String str2 = str;
//                                g.DS().O(new Runnable() {
//                                    public final void run() {
//                                        new ab(5, "", "", "", "", false, 1, false).a(h.this.edc, new f() {
//                                            public final void onSceneEnd(int i, int i2, String str, m mVar) {
//                                                mVar.edh = true;
//                                                y.i("MicroMsg.NetSceneSendMsg", "summerdktext verifypsw onSceneEnd[%d, %d] needVerifyPswList size[%d] errMsg[%s] verifyingPsw[%b], retryVerifyCount[%d]", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(h.this.esR.size()), str, Boolean.valueOf(h.this.esQ), Integer.valueOf(h.this.esP));
//                                                if (i == 0 && i2 == 0) {
//                                                    h.this.esR.clear();
//                                                    h.this.mE(str);
//                                                } else {
//                                                    h.R(h.this.esR);
//                                                    h.this.dmL.onSceneEnd(4, -49, str2, h.this);
//                                                    h.S(h.this.esR);
//                                                    h.this.esR.clear();
//                                                }
//                                                h.this.esQ = false;
//                                                ae.eTw = false;
//                                            }
//                                        });
//                                    }
//                                });
//                            }
//                        } else {
//                            iS(i5);
//                            this.dmL.onSceneEnd(4, awo.sze, str, this);
//                            iR(i5);
//                            return;
//                        }
//                    }
//                    long j = ((bi) this.esO.get(i5)).field_msgId;
//                    y.i("MicroMsg.NetSceneSendMsg", "msg local id = " + j + ", SvrId = " + awo.ndp + " sent successfully!");
//                    bi fd = ((j) g.r(j.class)).bhO().fd(j);
//                    fd.bf(awo.ndp);
//                    y.d("MicroMsg.NetSceneSendMsg", "dkmsgid  set svrmsgid %d -> %d", Long.valueOf(awo.ndp), Integer.valueOf(ae.eSQ));
//                    if (CdnLogic.kMediaTypeFavoriteBigFile == ae.eSP && ae.eSQ != 0) {
//                        fd.bf((long) ae.eSQ);
//                        ae.eSQ = 0;
//                    }
//                    fd.setStatus(2);
//                    ((j) g.r(j.class)).bhO().a(j, fd);
//                    if (this.esO == null) {
//                        y.e("MicroMsg.NetSceneSendMsg", "publishMsgSendSuccessEvent, sendingList is null");
//                    } else if (i5 >= this.esO.size() || i5 < 0) {
//                        y.e("MicroMsg.NetSceneSendMsg", "publishMsgSendSuccessEvent, index:%d, sendingList.size:%d", Integer.valueOf(i5), Integer.valueOf(this.esO.size()));
//                    } else {
//                        y.d("MicroMsg.NetSceneSendMsg", "publishMsgSendSuccessEvent for msgId:%d", Long.valueOf(((bi) this.esO.get(i5)).field_msgId));
//                        bi fd2 = ((j) g.r(j.class)).bhO().fd(j);
//                        ph phVar = new ph();
//                        phVar.bYU.bFH = fd2;
//                        com.tencent.mm.sdk.b.a.udP.m(phVar);
//                    }
//                    arrayList.add(Integer.valueOf(i5));
//                    if (1 == awo.hQR) {
//                        com.tencent.mm.plugin.report.f.nEG.a(11942, true, false, Long.valueOf(awo.ndp));
//                        com.tencent.mm.plugin.report.f.nEG.a(11945, false, true, Long.valueOf(awo.ndp));
//                        com.tencent.mm.plugin.report.f.nEG.a(11946, false, false, Long.valueOf(awo.ndp));
//                        com.tencent.mm.plugin.report.f.nEG.a(90, 0, 1, false);
//                        com.tencent.mm.plugin.report.f.nEG.a(90, 1, 1, true);
//                    }
//                    i4 = i5 + 1;
//                }
//            }
//            y.d("MicroMsg.NetSceneSendMsg", "summerdktext send finish, continue send SENDING msg verifyingPsw[%b]", Boolean.valueOf(this.esQ));
//            if (this.esQ) {
//                this.dmL.onSceneEnd(i2, i3, str, this);
//                return;
//            } else {
//                mE(str);
//                return;
//            }
//        }
//        y.i("MicroMsg.NetSceneSendMsg", "mark all failed. onGYNetEnd. errType:%d errCode:%d", Integer.valueOf(i2), Integer.valueOf(i3));
//        com.tencent.mm.plugin.report.f.nEG.a(111, (long) (i2 + 40), 1, true);
//        com.tencent.mm.plugin.report.f.nEG.a(111, 253, 1, false);
//        if (i2 == 3 || i2 == 9 || i2 == 7 || i2 == 8 || i2 == 1) {
//            this.dmL.onSceneEnd(i2, i3, str, this);
//            y.e("MicroMsg.NetSceneSendMsg", "Message delivery failed due to network reasons.");
//            return;
//        }
//        Pg();
//        this.dmL.onSceneEnd(i2, i3, str, this);
//        for (i4 = 0; i4 < this.esO.size(); i4++) {
//            iR(i4);
//        }
//        y.d("MicroMsg.NetSceneSendMsg", "send fail, continue send SENDING msg");
//        mE(str);
//    }
//
//    private void mE(String str) {
//        y.d("MicroMsg.NetSceneSendMsg", "continue send msg in list");
//        int a = a(this.edc, this.dmL);
//        if (a == -2) {
//            this.dmL.onSceneEnd(0, 0, str, this);
//        } else if (a < 0) {
//            this.dmL.onSceneEnd(3, -1, str, this);
//        }
//    }
//
//    private void Pg() {
//        for (int i = 0; i < this.esO.size(); i++) {
//            iS(i);
//        }
//    }
//
//    private void iS(int i) {
//        y.d("MicroMsg.NetSceneSendMsg", "markMsgFailed for id:%d", Long.valueOf(((bi) this.esO.get(i)).field_msgId));
//        u(r0);
//    }
//
//    private static void u(bi biVar) {
//        biVar.setStatus(5);
//        com.tencent.mm.plugin.report.f.nEG.a(111, 30, 1, true);
//        ((j) g.r(j.class)).bhO().a(biVar.field_msgId, biVar);
//        Iterator it = esN.iterator();
//        while (it.hasNext()) {
//            it.next();
//            String str = biVar.field_talker;
//            str = biVar.field_content;
//        }
//    }
//}