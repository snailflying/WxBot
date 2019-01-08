//package com.tencent.mm.roomsdk.a.c;
//
//import android.content.Context;
//import android.content.DialogInterface.OnCancelListener;
//import com.tencent.mm.ah.f;
//import com.tencent.mm.ah.m;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.roomsdk.a.a;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.ui.base.h;
//
//public final class b extends a {
//    f eeo = new f() {
//        public final void onSceneEnd(int i, int i2, String str, m mVar) {
//            g.DQ();
//            g.DO().dJT.b(mVar.getType(), b.this.eeo);
//            if (b.this.tipDialog != null) {
//                b.this.tipDialog.dismiss();
//            }
//            if (b.this.ubv.equals(mVar)) {
//                if (mVar instanceof a) {
//                    ((a) mVar).a(b.this.ubu);
//                }
//                if (b.this.ubu != null) {
//                    b.this.ubu.a(i, i2, str, b.this.ubu);
//                }
//                if (i == 0 && i2 == 0) {
//                    if (b.this.ubs != null) {
//                        b.this.ubs.a(i, i2, str, b.this.ubs);
//                    }
//                } else if (b.this.ubt != null) {
//                    b.this.ubt.a(i, i2, str, b.this.ubt);
//                }
//            }
//        }
//    };
//    public m ubv;
//
//    public b(boolean z) {
//        this.ubr = z;
//    }
//
//    public final void cancel() {
//        if (this.ubv != null) {
//            g.DQ();
//            g.DO().dJT.c(this.ubv);
//            g.DQ();
//            g.DO().dJT.b(this.ubv.getType(), this.eeo);
//        }
//    }
//
//    public final void cpz() {
//        if (this.ubv != null) {
//            y.i("MicroMsg.RoomCallbackFactory", "request scene %s", this.ubv);
//            if (!(this.tipDialog == null && this.ubs == null && this.ubt == null && this.ubu == null)) {
//                g.DQ();
//                g.DO().dJT.a(this.ubv.getType(), this.eeo);
//            }
//            g.DQ();
//            g.DO().dJT.a(this.ubv, 0);
//        }
//    }
//
//    public final void a(Context context, String str, boolean z, OnCancelListener onCancelListener) {
//        if (this.ubv != null) {
//            this.tipDialog = h.b(context, str, z, onCancelListener);
//            cpz();
//        }
//    }
//}