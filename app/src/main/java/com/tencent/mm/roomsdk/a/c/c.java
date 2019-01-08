//package com.tencent.mm.roomsdk.a.c;
//
//import android.content.Context;
//import android.content.DialogInterface.OnCancelListener;
//import com.tencent.mm.h.a.lr;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.plugin.messenger.foundation.a.a.h.a;
//import com.tencent.mm.plugin.messenger.foundation.a.a.i;
//import com.tencent.mm.plugin.messenger.foundation.a.j;
//import com.tencent.mm.roomsdk.a.b.e;
//import com.tencent.mm.sdk.b.b;
//import com.tencent.mm.sdk.platformtools.ai;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.ui.base.h;
//
//public final class c extends a {
//    com.tencent.mm.sdk.b.c dpQ = new com.tencent.mm.sdk.b.c<lr>() {
//        {
//            this.udX = lr.class.getName().hashCode();
//        }
//
//        public final /* synthetic */ boolean a(b bVar) {
//            final lr lrVar = (lr) bVar;
//            c.this.dpQ.dead();
//            ai.d(new Runnable() {
//                public final void run() {
//                    if (!c.this.uby) {
//                        c.this.uby = true;
//                        String str = lrVar.bUK.bUL;
//                        String str2 = lrVar.bUK.bUM;
//                        int i = lrVar.bUK.ret;
//                        if (c.this.ubu != null) {
//                            if (c.this.ubu instanceof e) {
//                                e eVar = (e) c.this.ubu;
//                                eVar.title = str;
//                                eVar.ret = i;
//                                eVar.content = str2;
//                            }
//                            c.this.ubu.a(0, i, "", c.this.ubu);
//                        }
//                        if (c.this.tipDialog != null) {
//                            c.this.tipDialog.dismiss();
//                        }
//                    }
//                }
//            });
//            return false;
//        }
//    };
//    public i.b ubx;
//    boolean uby = false;
//    a ubz = new a() {
//        public final void n(int i, String str, String str2) {
//            ((j) g.r(j.class)).Fv().b(c.this.ubx.getCmdId(), c.this.ubz);
//            if (!c.this.uby) {
//                c.this.uby = true;
//                if (c.this.ubu != null) {
//                    if (c.this.ubu instanceof e) {
//                        e eVar = (e) c.this.ubu;
//                        eVar.ret = i;
//                        eVar.title = str;
//                        eVar.content = str2;
//                    }
//                    c.this.ubu.a(0, i, "", c.this.ubu);
//                }
//                if (c.this.tipDialog != null) {
//                    c.this.tipDialog.dismiss();
//                }
//            }
//        }
//    };
//
//    public c(boolean z) {
//        this.ubr = z;
//    }
//
//    public final void cancel() {
//        ((j) g.r(j.class)).Fv().c(this.ubx);
//        ((j) g.r(j.class)).Fv().b(this.ubx.getCmdId(), this.ubz);
//        this.dpQ.dead();
//    }
//
//    public final void cpz() {
//        y.i("MicroMsg.RoomCallbackFactory", "request oplog %s", this.ubx);
//        if (!(this.ubs == null && this.ubt == null && this.ubu == null)) {
//            this.dpQ.cqo();
//            ((j) g.r(j.class)).Fv().a(this.ubx.getCmdId(), this.ubz);
//        }
//        ((j) g.r(j.class)).Fv().b(this.ubx);
//    }
//
//    public final void a(Context context, String str, boolean z, OnCancelListener onCancelListener) {
//        this.tipDialog = h.b(context, str, z, onCancelListener);
//        cpz();
//    }
//}