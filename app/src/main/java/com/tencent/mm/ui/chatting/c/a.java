//package com.tencent.mm.ui.chatting.c;
//
//import android.content.Context;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.Intent;
//import android.view.View;
//import android.widget.ListView;
//import com.tencent.mm.model.q;
//import com.tencent.mm.model.s;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.ui.chatting.ag;
//import com.tencent.mm.ui.chatting.b.v;
//import com.tencent.mm.ui.chatting.b.w;
//import com.tencent.mm.ui.chatting.e;
//import com.tencent.mm.ui.chatting.f.b;
//import com.tencent.xweb.util.c;
//
//public final class a {
//    private static ah vtv = new ah();
//    public boolean euf = false;
//    public ad pSb;
//    public ag vtA;
//    public com.tencent.mm.ui.chatting.ah vtB;
//    public String vtC;
//    public com.tencent.mm.ui.chatting.f.a vtw = new com.tencent.mm.ui.chatting.f.a(this);
//    public b vtx = new b(this);
//    public String vty;
//    public e vtz;
//
//    public a(e eVar, ag agVar, com.tencent.mm.ui.chatting.ah ahVar) {
//        this.vtz = eVar;
//        this.vtA = agVar;
//        this.vtB = ahVar;
//    }
//
//    public final String getTalkerUserName() {
//        if (this.pSb == null) {
//            return "";
//        }
//        return bk.aM(this.pSb.field_username, "");
//    }
//
//    public final void ab(ad adVar) {
//        this.pSb = adVar;
//        this.vtC = c.o((adVar.field_username + System.currentTimeMillis()).getBytes());
//    }
//
//    public final String cFB() {
//        if (this.vty == null) {
//            this.vty = q.Gj();
//            this.vty = ad.hd(getTalkerUserName()) ? ad.aaZ(this.vty) : this.vty;
//        }
//        return this.vty;
//    }
//
//    public final String cDP() {
//        return bk.aM(((com.tencent.mm.ui.chatting.b.b.c) ac(com.tencent.mm.ui.chatting.b.b.c.class)).cDP(), this.pSb.field_nickname);
//    }
//
//    public final View findViewById(int i) {
//        return this.vtz.findViewById(i);
//    }
//
//    public final boolean cFC() {
//        String talkerUserName = getTalkerUserName();
//        return ad.hd(talkerUserName) || ad.aaT(talkerUserName) || ad.aaR(talkerUserName) || s.hA(talkerUserName);
//    }
//
//    public final boolean cFD() {
//        return s.fn(getTalkerUserName()) || s.hb(getTalkerUserName());
//    }
//
//    public final boolean cFE() {
//        return s.fn(getTalkerUserName()) || s.hb(getTalkerUserName()) || ((com.tencent.mm.ui.chatting.b.b.c) ac(com.tencent.mm.ui.chatting.b.b.c.class)).cDH();
//    }
//
//    public final boolean cFF() {
//        if (!this.pSb.cua() && !s.hR(getTalkerUserName()) && !ad.aaR(getTalkerUserName())) {
//            return true;
//        }
//        if (((com.tencent.mm.ui.chatting.b.b.c) ac(com.tencent.mm.ui.chatting.b.b.c.class)).cDG()) {
//            return true;
//        }
//        return false;
//    }
//
//    public static ah cFG() {
//        return vtv;
//    }
//
//    public final void startActivity(Intent intent) {
//        this.vtz.startActivity(intent);
//    }
//
//    public final void a(Intent intent, int i, com.tencent.mm.br.d.a aVar) {
//        this.vtx.a(intent, i, aVar);
//    }
//
//    @Deprecated
//    public final ListView getListView() {
//        return this.vtA.getListView();
//    }
//
//    public final void GU(int i) {
//        this.vtA.GU(i);
//    }
//
//    public final int getFirstVisiblePosition() {
//        return this.vtA.getFirstVisiblePosition();
//    }
//
//    public final int getLastVisiblePosition() {
//        return this.vtA.getLastVisiblePosition();
//    }
//
//    public final int getHeaderViewsCount() {
//        return this.vtA.getHeaderViewsCount();
//    }
//
//    public final View getChildAt(int i) {
//        return this.vtA.getChildAt(i);
//    }
//
//    public final void d(Context context, String str, OnCancelListener onCancelListener) {
//        y.i("MicroMsg.ChattingContext", "[showDialog]");
//        this.vtA.c(context, str, onCancelListener);
//    }
//
//    public final void dismissDialog() {
//        y.i("MicroMsg.ChattingContext", "[dismissDialog]");
//        this.vtA.dismissDialog();
//    }
//
//    public final void XM() {
//        y.i("MicroMsg.ChattingContext", "[hideVKB]");
//        this.vtA.hideVKB();
//    }
//
//    public final void axW() {
//        y.i("MicroMsg.ChattingContext", "[notifyDataSetChange]");
//        this.vtA.axW();
//    }
//
//    public final void GT(int i) {
//        y.i("MicroMsg.ChattingContext", "[setSelection] pos:%s isSmooth:%s", Integer.valueOf(i), Boolean.valueOf(false));
//        this.vtA.GT(i);
//    }
//
//    public final void fx(int i, int i2) {
//        y.i("MicroMsg.ChattingContext", "[setSelectionFromTop] pos:%s offset:%s isSmooth:%s", Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(false));
//        this.vtA.fx(i, i2);
//    }
//
//    public final void cCW() {
//        y.i("MicroMsg.ChattingContext", "[scrollToLast] force:%b", Boolean.valueOf(true));
//        this.vtA.cCW();
//    }
//
//    public final void bE(int i) {
//        y.i("MicroMsg.ChattingContext", "[scrollToLast] position:%d", Integer.valueOf(i));
//        this.vtA.bE(i);
//    }
//
//    public final void setKeepScreenOn(boolean z) {
//        y.i("MicroMsg.ChattingContext", "[setKeepScreenOn] force:%b", Boolean.valueOf(z));
//        this.vtA.setKeepScreenOn(z);
//    }
//
//    public final void np(boolean z) {
//        y.i("MicroMsg.ChattingContext", "[showOptionMenu] menuID:%d show:%s", Integer.valueOf(2), Boolean.valueOf(z));
//        this.vtA.showOptionMenu(2, z);
//    }
//
//    public final void showOptionMenu(boolean z) {
//        y.i("MicroMsg.ChattingContext", "[showOptionMenu] show:%s", Boolean.valueOf(z));
//        this.vtA.showOptionMenu(z);
//    }
//
//    public final void Hg(int i) {
//        y.i("MicroMsg.ChattingContext", "[updateOptionMenuIcon] menuID:%d iconID:%s", Integer.valueOf(2), Integer.valueOf(i));
//        this.vtA.updateOptionMenuIcon(2, i);
//    }
//
//    public final <T extends v, V extends Class<T>> T ac(V v) {
//        com.tencent.mm.ui.chatting.f.a aVar = this.vtw;
//        if (!v.isInterface()) {
//            throw new RuntimeException("[get] " + v + " is not a interface!");
//        } else if (aVar.vyt.containsKey(v)) {
//            return (v) aVar.vyt.get(v);
//        } else {
//            y.e("MicroMsg.ChattingComponentManager", "[get] " + v + " is never register! activity:%s", aVar.byx.vtz.getContext().getClass().getSimpleName());
//            return null;
//        }
//    }
//
//    public final void a(Class<? extends v> cls, v vVar) {
//        com.tencent.mm.ui.chatting.f.a aVar = this.vtw;
//        if (aVar.vyt.put(cls, vVar) != null) {
//            y.w("MicroMsg.ChattingComponentManager", "[register] %s has register", cls);
//        }
//        if (vVar instanceof w) {
//            long currentTimeMillis = System.currentTimeMillis();
//            ((w) vVar).a(aVar.byx);
//            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
//            y.i("MicroMsg.ChattingComponentManager", "[install] listener:%s cost:%sms", vVar.getClass().getName(), Long.valueOf(currentTimeMillis2));
//        }
//    }
//}