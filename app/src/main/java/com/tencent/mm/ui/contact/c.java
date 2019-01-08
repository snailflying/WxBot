//package com.tencent.mm.ui.contact;
//
//import android.database.Cursor;
//import android.database.MergeCursor;
//import android.util.SparseArray;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.model.s;
//import com.tencent.mm.plugin.messenger.foundation.a.j;
//import com.tencent.mm.plugin.selectcontact.a.h;
//import com.tencent.mm.sdk.e.m;
//import com.tencent.mm.sdk.e.m.b;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.ui.contact.a.d;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public final class c extends o implements b {
//    private Cursor eYd;
//    private int nCS;
//    private a vJA;
//    private int vJB;
//    private int vJC;
//    private int vJD;
//    private int vJE;
//    private int vJF;
//    private int vJG;
//    private HashMap<String, Integer> vJH;
//    private SparseArray<String> vJI;
//
//    public static class a {
//        public String vIs = "@all.contact.without.chatroom";
//        public boolean vJJ = false;
//        public boolean vJK = false;
//        public boolean vJL = false;
//        public boolean vJM = false;
//        public boolean vJN = false;
//        public boolean vJO = false;
//        public String vJP;
//        public String vJQ;
//        public boolean vJR = false;
//        public String vJS = "";
//        public String vJT = "";
//    }
//
//    public c(MMBaseSelectContactUI mMBaseSelectContactUI, List<String> list, boolean z, boolean z2, a aVar, boolean z3) {
//        super((l) mMBaseSelectContactUI, (List) list, z, z2, z3);
//        this.vJB = Integer.MAX_VALUE;
//        this.nCS = Integer.MAX_VALUE;
//        this.vJC = Integer.MAX_VALUE;
//        this.vJD = Integer.MAX_VALUE;
//        this.vJE = Integer.MAX_VALUE;
//        this.vJF = Integer.MAX_VALUE;
//        this.vJG = Integer.MAX_VALUE;
//        this.vJH = null;
//        this.vJI = null;
//        y.i("MicroMsg.AlphabetContactAdapter", "create!");
//        if (aVar != null) {
//            this.vJA = aVar;
//        } else {
//            this.vJA = new a();
//        }
//        g.DQ();
//        ((j) g.r(j.class)).Fw().a(this);
//        xO();
//    }
//
//    public c(MMBaseSelectContactUI mMBaseSelectContactUI, List<String> list, boolean z, a aVar) {
//        this(mMBaseSelectContactUI, list, true, z, aVar, false);
//    }
//
//    private void xO() {
//        if (this.eYd != null) {
//            this.eYd.close();
//            this.eYd = null;
//        }
//        this.vJB = Integer.MAX_VALUE;
//        this.nCS = Integer.MAX_VALUE;
//        this.vJC = Integer.MAX_VALUE;
//        this.vJD = Integer.MAX_VALUE;
//        this.vJE = Integer.MAX_VALUE;
//        this.vJG = Integer.MAX_VALUE;
//        if (this.vJH != null) {
//            this.vJH.clear();
//        } else {
//            this.vJH = new HashMap();
//        }
//        if (this.vJI != null) {
//            this.vJI.clear();
//        } else {
//            this.vJI = new SparseArray();
//        }
//        ArrayList arrayList = new ArrayList();
//        int count;
//        int i;
//        Object dA;
//        String[] M;
//        if (this.vJA.vJN) {
//            g.DQ();
//            Cursor i2 = ((j) g.r(j.class)).Fw().i(bk.G(this.vJA.vJQ.split(",")), false);
//            arrayList.add(i2);
//            count = i2.getCount();
//            y.d("MicroMsg.AlphabetContactAdapter", "ap: recent like count %d", Integer.valueOf(count));
//            if (count > 0) {
//                this.vJG = 0;
//                count = (count + 1) + 0;
//                bf(this.vJG, "☆");
//                i = count;
//            } else {
//                this.vJG = Integer.MAX_VALUE;
//                i = 0;
//            }
//            if (this.vJA.vIs == "@all.contact.without.chatroom.without.openim.without.openimfavour") {
//                g.DQ();
//                dA = ((j) g.r(j.class)).Fw().dA(bk.G(this.vJA.vJP.split(",")));
//            } else {
//                g.DQ();
//                dA = ((j) g.r(j.class)).Fw().dz(bk.G(this.vJA.vJP.split(",")));
//            }
//            arrayList.add(dA);
//            count = dA.getCount();
//            if (count > 0) {
//                this.vJC = i;
//                i += count + 1;
//                bf(this.vJC, "☆");
//            } else {
//                this.vJC = Integer.MAX_VALUE;
//            }
//            ArrayList G = bk.G(this.vJA.vJP.split(","));
//            g.DQ();
//            arrayList.add(((j) g.r(j.class)).Fw().i(G, true));
//            M = s.M(G);
//            int[] L = s.L(G);
//            if (!(M == null || L == null)) {
//                for (count = 0; count < M.length; count++) {
//                    if (count < L.length) {
//                        bf(L[count] + i, M[count]);
//                        i++;
//                    }
//                }
//                i2.getCount();
//            }
//        } else {
//            Cursor dv;
//            if (this.vJA.vJO) {
//                this.vJF = 0;
//                bf(this.vJF, "nonLimit");
//                i = 1;
//            } else {
//                i = 0;
//            }
//            if (this.vJA.vJR && this.vJA.vJS != null && !bk.bl(this.vJA.vJS)) {
//                ArrayList G2 = bk.G(this.vJA.vJS.split(";"));
//                g.DQ();
//                dv = ((j) g.r(j.class)).Fw().dv(G2);
//                arrayList.add(dv);
//                count = dv.getCount();
//                if (count > 0) {
//                    this.vJB = i;
//                    i += count + 1;
//                    bf(this.vJB, "↑");
//                } else {
//                    this.vJB = Integer.MAX_VALUE;
//                }
//            } else if (this.vJA.vJJ) {
//                List ek = g.ek(this.dru);
//                if (ek.size() == 0) {
//                    g.DQ();
//                    dA = ((j) g.r(j.class)).Fw().cuz();
//                } else {
//                    g.DQ();
//                    dA = ((j) g.r(j.class)).Fw().dv(ek);
//                }
//                arrayList.add(dA);
//                count = dA.getCount();
//                if (count > 0) {
//                    this.nCS = i;
//                    i += count + 1;
//                    bf(this.nCS, "↑");
//                } else {
//                    this.nCS = Integer.MAX_VALUE;
//                }
//            }
//            if (this.vJA.vJK) {
//                if (this.vJA.vIs == "@all.contact.without.chatroom.without.openim.without.openimfavour") {
//                    g.DQ();
//                    dA = ((j) g.r(j.class)).Fw().dC(this.dru);
//                } else {
//                    g.DQ();
//                    dA = ((j) g.r(j.class)).Fw().dB(this.dru);
//                }
//                arrayList.add(dA);
//                count = dA.getCount();
//                if (count > 0) {
//                    this.vJC = i;
//                    i += count + 1;
//                    bf(this.vJC, "☆");
//                } else {
//                    this.vJC = Integer.MAX_VALUE;
//                }
//            }
//            g.DQ();
//            Cursor b = ((j) g.r(j.class)).Fw().b(this.vJA.vIs, "", this.dru);
//            arrayList.add(b);
//            M = s.a(this.vJA.vIs, "", "", this.dru);
//            int[] b2 = s.b(this.vJA.vIs, "", this.dru, "");
//            if (!(M == null || b2 == null)) {
//                int i3 = i;
//                for (count = 0; count < M.length; count++) {
//                    if (count < b2.length) {
//                        bf(b2[count] + i3, M[count]);
//                        i3++;
//                    }
//                }
//                i += b.getCount() + M.length;
//            }
//            if (this.vJA.vJL) {
//                g.DQ();
//                dv = ((j) g.r(j.class)).Fw().c("@all.chatroom.contact", "", this.dru);
//                arrayList.add(dv);
//                count = dv.getCount();
//                if (count > 0) {
//                    this.vJD = i;
//                    i += count + 1;
//                    bf(this.vJD, this.vLI.getActivity().getString(h.address_chatroom_contact_nick));
//                } else {
//                    this.vJD = Integer.MAX_VALUE;
//                }
//            }
//            if (this.vJA.vJM) {
//                g.DQ();
//                dv = ((j) g.r(j.class)).Fw().c("@verify.contact", "", this.dru);
//                arrayList.add(dv);
//                if (dv.getCount() > 0) {
//                    this.vJE = i;
//                    bf(this.vJE, this.vLI.getActivity().getString(h.select_contact_official_accounts_title));
//                } else {
//                    this.vJE = Integer.MAX_VALUE;
//                }
//            }
//        }
//        y.d("MicroMsg.AlphabetContactAdapter", "headerPosMap=%s", this.vJH.toString());
//        this.eYd = new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
//        notifyDataSetChanged();
//    }
//
//    private void bf(int i, String str) {
//        this.vJH.put(str, Integer.valueOf(i));
//        this.vJI.put(i, str);
//    }
//
//    public final int adP(String str) {
//        if (str.equals("↑")) {
//            return 0;
//        }
//        if (this.vJH == null || !this.vJH.containsKey(str)) {
//            return -1;
//        }
//        return ((Integer) this.vJH.get(str)).intValue() + this.vLI.getContentLV().getHeaderViewsCount();
//    }
//
//    public final int getCount() {
//        return this.eYd.getCount() + this.vJH.size();
//    }
//
//    protected final com.tencent.mm.ui.contact.a.a jQ(int i) {
//        if (i == this.vJG) {
//            return bg(i, this.vLI.getActivity().getString(h.select_contact_wechat_sport_recent_like_contact_name));
//        }
//        if (i == this.vJB) {
//            return bg(i, this.vJA.vJT);
//        }
//        if (i == this.nCS) {
//            return bg(i, this.vLI.getActivity().getString(h.select_contact_near_contact_catalog_name));
//        }
//        if (i == this.vJC) {
//            return bg(i, this.vLI.getActivity().getString(h.select_contact_favour_contact_catalog_name));
//        }
//        if (i == this.vJD) {
//            return bg(i, this.vLI.getActivity().getString(h.address_chatroom_contact_nick));
//        }
//        if (i == this.vJE) {
//            return bg(i, this.vLI.getActivity().getString(h.select_contact_official_accounts_title));
//        }
//        if (i == this.vJF) {
//            return new com.tencent.mm.ui.contact.a.j(i);
//        }
//        if (this.vJI.indexOfKey(i) >= 0) {
//            return bg(i, (String) this.vJI.get(i));
//        }
//        int i2 = i;
//        int i3 = 0;
//        while (i3 <= this.vJI.size()) {
//            if (this.vJI.indexOfKey(i2) >= 0) {
//                i3++;
//            }
//            i2--;
//            if (i2 < 0) {
//                break;
//            }
//        }
//        if (this.eYd.moveToPosition(i - i3)) {
//            y.d("MicroMsg.AlphabetContactAdapter", "create contact item position=%d | index=%d", Integer.valueOf(i), Integer.valueOf(i - i3));
//            ad adVar = new ad();
//            adVar.d(this.eYd);
//            com.tencent.mm.ui.contact.a.a dVar = new d(i);
//            dVar.dnp = adVar;
//            dVar.vLJ = bBJ();
//            dVar.vLK = this.vLK;
//            return dVar;
//        }
//        y.i("MicroMsg.AlphabetContactAdapter", "create contact item error: position=%d | index=%d", Integer.valueOf(i), Integer.valueOf(i - i3));
//        return null;
//    }
//
//    private static com.tencent.mm.ui.contact.a.a bg(int i, String str) {
//        com.tencent.mm.ui.contact.a.g gVar = new com.tencent.mm.ui.contact.a.g(i);
//        gVar.kDu = str;
//        return gVar;
//    }
//
//    public final void finish() {
//        super.finish();
//        y.i("MicroMsg.AlphabetContactAdapter", "finish!");
//        if (this.eYd != null) {
//            this.eYd.close();
//            this.eYd = null;
//        }
//        g.DQ();
//        ((j) g.r(j.class)).Fw().b(this);
//    }
//
//    public final void a(int i, m mVar, Object obj) {
//        xO();
//        notifyDataSetChanged();
//    }
//
//    protected final boolean c(com.tencent.mm.ui.contact.a.a aVar) {
//        int i = aVar.position + 1;
//        int[] iArr = new int[]{this.vJG, this.vJB, this.nCS, this.vJC, this.vJD, this.vJE};
//        for (int i2 = 0; i2 < 6; i2++) {
//            if (i == iArr[i2]) {
//                return true;
//            }
//        }
//        if (this.vJI.indexOfKey(i) < 0) {
//            return false;
//        }
//        return true;
//    }
//}