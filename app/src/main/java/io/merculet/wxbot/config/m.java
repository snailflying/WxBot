//package io.merculet.wxbot.config;
//
//import com.tencent.mm.model.bg;
//import com.tencent.mm.network.e;
//import com.tencent.mm.network.k;
//import com.tencent.mm.network.q;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import junit.framework.Assert;
//
//public abstract class m {
//    public boolean aSd = false;
//    public e edc;
//    public long edd = bk.UZ();
//    public int ede = -1;
//    private s edf;
//    f edg;
//    public boolean edh;
//    public q edi;
//    private int limit = -99;
//    int priority = 0;
//
//    protected enum a {
//        EStatusCheckFailed,
//        EReachMaxLimit
//    }
//
//    protected enum b {
//        EUnchecked,
//        EOk,
//        EFailed
//    }
//
//    public abstract int a(e eVar, f fVar);
//
//    public abstract int getType();
//
//    public final void reset() {
//        this.edd = bk.UZ();
//        this.ede = -1;
//        this.limit = -99;
//    }
//
//    public void a(a aVar) {
//    }
//
//    public b b(q qVar) {
//        return b.EUnchecked;
//    }
//
//    public boolean Kx() {
//        return false;
//    }
//
//    public int Ka() {
//        return 1;
//    }
//
//    public boolean Ky() {
//        return this.limit <= 0;
//    }
//
//    public boolean Kz() {
//        return false;
//    }
//
//    public final void c(e eVar) {
//        this.edd = bk.UZ();
//        this.edc = eVar;
//    }
//
//    public boolean KA() {
//        return Ka() == 1;
//    }
//
//    public int a(e eVar, final q qVar, k kVar) {
//        c(eVar);
//        this.edi = qVar;
//        final k a = bg.a(kVar);
//        int HT = bg.HT();
//        if (HT != 0) {
//            return HT;
//        }
//        if (this.limit == -99) {
//            this.limit = Ka();
//            y.i("MicroMsg.NetSceneBase", "initilized security limit count to " + this.limit);
//        }
//        if (Ka() > 1) {
//            switch (b(qVar)) {
//                case EUnchecked:
//                    Assert.assertTrue("scene security verification not passed, type=" + qVar.getType() + ", uri=" + qVar.getUri() + ", CHECK NOW", false);
//                    break;
//                case EFailed:
//                    y.e("MicroMsg.NetSceneBase", "scene security verification not passed, type=" + qVar.getType() + ", uri=" + qVar.getUri());
//                    this.limit--;
//                    a(a.EStatusCheckFailed);
//                    this.ede = -1;
//                    return this.ede;
//                case EOk:
//                    break;
//                default:
//                    Assert.assertTrue("invalid security verification status", false);
//                    break;
//            }
//        }
//        if (Ky()) {
//            y.e("MicroMsg.NetSceneBase", "dispatch failed, scene limited for security, current limit=" + Ka());
//            a(a.EReachMaxLimit);
//            this.ede = -1;
//            return this.ede;
//        }
//        this.limit--;
//        u uVar = new u(qVar);
//        if (!(this.edf == null || Kz())) {
//            this.edf.cancel();
//        }
//        this.edf = new s(qVar, a, this, this.edg, eVar);
//        this.ede = eVar.a(uVar, this.edf);
//        y.i("MicroMsg.NetSceneBase", "dispatcher send, %s", Integer.valueOf(this.ede));
//        if (this.ede < 0) {
//            y.i("MicroMsg.NetSceneBase", "dispatcher send, %s, ThreadID:%s, getType:%s", Integer.valueOf(this.ede), Long.valueOf(Thread.currentThread().getId()), Integer.valueOf(qVar.getType()));
//            new ah().post(new Runnable() {
//                public final void run() {
//                    y.i("MicroMsg.NetSceneBase", "dispatcher send, %s, ThreadID:%s, getType:%s", Integer.valueOf(m.this.ede), Long.valueOf(Thread.currentThread().getId()), Integer.valueOf(qVar.getType()));
//                    a.a(-1, 3, -1, "send to network failed", qVar, null);
//                    y.i("MicroMsg.NetSceneBase", "dispatcher send, %s, ThreadID:%s, getType:%s", Integer.valueOf(m.this.ede), Long.valueOf(Thread.currentThread().getId()), Integer.valueOf(qVar.getType()));
//                }
//            });
//            return 99999999;
//        }
//        s sVar = this.edf;
//        sVar.handler.postDelayed(sVar.edV, 330000);
//        return this.ede;
//    }
//
//    public final q KB() {
//        return this.edi;
//    }
//
//    public final int KC() {
//        return this.edi == null ? 0 : this.edi.hashCode();
//    }
//
//    public void cancel() {
//        y.i("MicroMsg.NetSceneBase", "cancel: %d, hash:%d, type:%d", Integer.valueOf(this.ede), Integer.valueOf(hashCode()), Integer.valueOf(getType()));
//        this.aSd = true;
//        if (this.edf != null) {
//            this.edf.cancel();
//        }
//        if (this.ede != -1 && this.edc != null) {
//            int i = this.ede;
//            this.ede = -1;
//            this.edc.cancel(i);
//        }
//    }
//
//    public boolean a(m mVar) {
//        return false;
//    }
//
//    public boolean b(m mVar) {
//        return false;
//    }
//
//    public String getInfo() {
//        return "";
//    }
//}