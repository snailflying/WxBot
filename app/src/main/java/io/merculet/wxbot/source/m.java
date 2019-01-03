//package io.merculet.wxbot.config;
//
//import android.text.TextUtils;
//import com.tencent.mm.ah.b;
//import com.tencent.mm.ah.b.a;
//import com.tencent.mm.ah.f;
//import com.tencent.mm.network.e;
//import com.tencent.mm.network.k;
//import com.tencent.mm.network.q;
//import com.tencent.mm.protocal.c.bmk;
//import com.tencent.mm.protocal.c.cdb;
//import com.tencent.mm.protocal.c.cdd;
//import com.tencent.mm.protocal.c.cde;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.g;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import junit.framework.Assert;
//
//public class m extends com.tencent.mm.ah.m implements k {
//    public int bNb;
//    public final b dmK;
//    private f dmL;
//    public String fgB;
//    public List<String> rTv;
//    public String rTw;
//    private List<String> rTx;
//
//    private m(String str, String str2, int i) {
//        this.rTv = null;
//        this.bNb = 0;
//        this.rTx = null;
//        Assert.assertTrue("This NetSceneVerifyUser init MUST use opcode == MM_VERIFYUSER_VERIFYOK", true);
//        this.rTv = new LinkedList();
//        this.rTv.add(str);
//        this.bNb = 3;
//        a aVar = new a();
//        aVar.ecH = new cdd();
//        aVar.ecI = new cde();
//        aVar.uri = "/cgi-bin/micromsg-bin/verifyuser";
//        aVar.ecG = 137;
//        aVar.ecJ = 44;
//        aVar.ecK = 1000000044;
//        this.dmK = aVar.Kt();
//        cdd cdd = (cdd) this.dmK.ecE.ecN;
//        cdd.syV = 3;
//        cdd.tgV = "";
//        LinkedList linkedList = new LinkedList();
//        cdb cdb = new cdb();
//        cdb.nFs = str;
//        cdb.tRS = str2;
//        cdb.tac = com.tencent.mm.plugin.c.a.YT().Ga().aaE(str);
//        cdb.tFA = null;
//        linkedList.add(cdb);
//        cdd.tRZ = linkedList;
//        cdd.tRY = linkedList.size();
//        linkedList = new LinkedList();
//        linkedList.add(Integer.valueOf(i));
//        cdd.tSb = linkedList;
//        cdd.tSa = linkedList.size();
//        cdd.tpt = new bmk().bs(com.tencent.mm.plugin.normsg.a.b.mGK.boP());
//        y.i("MicroMsg.NetSceneVerifyUser.dkverify", "dkverify scene:%d user:%d ticket:%s anti:%s", Integer.valueOf(cdd.tRZ.size()), Integer.valueOf(cdd.tSb.size()), str2, cdb.tac);
//    }
//
//    public m(int i, List<String> list, List<Integer> list2, List<String> list3, String str, String str2, Map<String, Integer> map, String str3, String str4) {
//        List list32;
//        int i2;
//        int i3;
//        int i4;
//        this.rTv = null;
//        this.bNb = 0;
//        this.rTx = null;
//        Assert.assertTrue("This NetSceneVerifyUser init NEVER use opcode == MM_VERIFYUSER_VERIFYOK", i != 3);
//        this.bNb = i;
//        this.rTv = list;
//        if (list32 == null || list32.size() == 0) {
//            list32 = new LinkedList();
//        }
//        a aVar = new a();
//        aVar.ecH = new cdd();
//        aVar.ecI = new cde();
//        aVar.uri = "/cgi-bin/micromsg-bin/verifyuser";
//        aVar.ecG = 137;
//        aVar.ecJ = 44;
//        aVar.ecK = 1000000044;
//        this.dmK = aVar.Kt();
//        if (list32 != null && list32.size() > 0) {
//            if (list32.size() == list.size()) {
//                i2 = 0;
//                while (true) {
//                    i3 = i2;
//                    if (i3 >= list32.size()) {
//                        break;
//                    }
//                    com.tencent.mm.plugin.c.a.YT().Ga().z((String) list.get(i3), 2147483633, (String) list32.get(i3));
//                    i2 = i3 + 1;
//                }
//            } else {
//                y.e("MicroMsg.NetSceneVerifyUser.dkverify", "dkverify Error lstAntispamTicket:%d lstVerifyUser:%d", Integer.valueOf(list32.size()), Integer.valueOf(list.size()));
//            }
//        }
//        if (i == 2) {
//            i2 = 0;
//            while (true) {
//                i4 = i2;
//                if (i4 >= list.size()) {
//                    break;
//                }
//                list32.add(bk.aM(com.tencent.mm.plugin.c.a.YT().Ga().aaE((String) list.get(i4)), ""));
//                i2 = i4 + 1;
//            }
//        }
//        cdd cdd = (cdd) this.dmK.ecE.ecN;
//        cdd.syV = i;
//        cdd.tgV = str;
//        this.rTw = str;
//        LinkedList linkedList = new LinkedList();
//        i4 = 0;
//        while (true) {
//            i3 = i4;
//            if (i3 < list.size()) {
//                String str5;
//                cdb cdb = new cdb();
//                cdb.nFs = (String) list.get(i3);
//                if (str2 == null) {
//                    str5 = "";
//                } else {
//                    str5 = str2;
//                }
//                cdb.tRS = str5;
//                g Ga = com.tencent.mm.plugin.c.a.YT().Ga();
//                String str6 = cdb.nFs;
//                ((Integer) list2.get(i3)).intValue();
//                cdb.tac = bk.aM(Ga.aaE(str6), "");
//                if (TextUtils.isEmpty(cdb.tac) && list32 != null && list32.size() > i3) {
//                    cdb.tac = (String) list32.get(i3);
//                }
//                cdb.tFA = str3;
//                if (map != null) {
//                    if (map.containsKey(cdb.nFs)) {
//                        cdb.tRT = ((Integer) map.get(cdb.nFs)).intValue();
//                    }
//                }
//                cdb.tRX = str4;
//                y.i("MicroMsg.NetSceneVerifyUser.dkverify", "dkverify op:%s idx:%s user:%s anti:%s chatroom:%s, reportInfo:%s", Integer.valueOf(this.bNb), Integer.valueOf(i3), cdb.nFs, cdb.tac, str3, str4);
//                linkedList.add(cdb);
//                i4 = i3 + 1;
//            } else {
//                cdd.tRZ = linkedList;
//                cdd.tRY = linkedList.size();
//                LinkedList linkedList2 = new LinkedList();
//                linkedList2.addAll(list2);
//                cdd.tSb = linkedList2;
//                cdd.tSa = linkedList2.size();
//                cdd.tpt = new bmk().bs(com.tencent.mm.plugin.normsg.a.b.mGK.boP());
//                y.i("MicroMsg.NetSceneVerifyUser.dkverify", "dkverify op:%d scene:%d user:%d antitickets:%s chatroom:%s stack:%s", Integer.valueOf(this.bNb), Integer.valueOf(cdd.tRZ.size()), Integer.valueOf(cdd.tSb.size()), bk.c(list32, ","), str3, bk.csb());
//                return;
//            }
//        }
//    }
//
//    public m(List<String> list, List<Integer> list2, String str, String str2, Map<String, Integer> map, String str3) {
//        this(2, list, list2, null, str, str2, map, str3, "");
//    }
//
//    public m(String str, String str2, int i, byte b) {
//        this(str, str2, i);
//    }
//
//    public m(int i, List<String> list, List<Integer> list2, String str, String str2) {
//        this(i, list, list2, null, str, str2, null, null, "");
//    }
//
//    public final void fP(String str, String str2) {
//        Iterator it = ((cdd) this.dmK.ecE.ecN).tRZ.iterator();
//        while (it.hasNext()) {
//            cdb cdb = (cdb) it.next();
//            cdb.tRU = str;
//            cdb.tRV = str2;
//        }
//    }
//
//    public final String ckC() {
//        if (this.dmK == null || this.dmK.ecF == null) {
//            return "";
//        }
//        return ((cde) this.dmK.ecF.ecN).hPY;
//    }
//
//    public final int a(e eVar, f fVar) {
//        this.dmL = fVar;
//        return a(eVar, this.dmK, this);
//    }
//
//    public final int ckD() {
//        return this.bNb;
//    }
//
//    public final int getType() {
//        return 30;
//    }
//
//    public final void a(int i, int i2, int i3, String str, q qVar, byte[] bArr) {
//        if (!(i2 == 0 && i3 == 0)) {
//            y.e("MicroMsg.NetSceneVerifyUser.dkverify", "errType:%d, errCode:%d", Integer.valueOf(i2), Integer.valueOf(i3));
//        }
//        this.dmL.onSceneEnd(i2, i3, str, this);
//    }
//}