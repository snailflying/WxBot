//package io.merculet.wxbot.config;
//
//import android.os.Looper;
//import android.os.Message;
//import com.eclipsesource.v8.Platform;
//import com.tencent.mm.hardcoder.WXHardCoderJNI;
//import com.tencent.mm.network.e;
//import com.tencent.mm.sdk.a.b;
//import com.tencent.mm.sdk.platformtools.ListenerInstanceMonitor;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.ai;
//import com.tencent.mm.sdk.platformtools.am;
//import com.tencent.mm.sdk.platformtools.aq;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import java.util.Vector;
//import junit.framework.Assert;
//
//public final class p implements f {
//    private static int edH = 1;
//    private static p edw = null;
//    private Vector<m> edA = new Vector();
//    private final Map<Integer, Set<f>> edB = new HashMap();
//    public Boolean edC = null;
//    private final a edD;
//    private long edE = 21600000;
//    private boolean edF = false;
//    private am edG = new am(Looper.getMainLooper(), new com.tencent.mm.sdk.platformtools.am.a() {
//        public final boolean tC() {
//            boolean z = false;
//            if (p.this.edD == null) {
//                return false;
//            }
//            y.v("MicroMsg.NetSceneQueue", "onQueueIdle, running=%d, waiting=%d, foreground=%b", Integer.valueOf(p.this.edz.size()), Integer.valueOf(p.this.edA.size()), Boolean.valueOf(p.this.foreground));
//            a b = p.this.edD;
//            p pVar = p.this;
//            if (p.this.edF && p.this.edz.isEmpty() && p.this.edA.isEmpty()) {
//                z = true;
//            }
//            b.a(pVar, z);
//            return true;
//        }
//    }, true);
//    public e edx;
//    public ai edy = null;
//    private Vector<m> edz = new Vector();
//    public boolean foreground = false;
//    private final ah handler = new ah(Looper.getMainLooper()) {
//        public final void handleMessage(Message message) {
//            p.this.a((m) message.obj, 0);
//        }
//    };
//    private final Object lock = new Object();
//
//    /* renamed from: com.tencent.mm.ah.p$4 */
//    class AnonymousClass4 implements Runnable {
//        final /* synthetic */ int edJ;
//
//        public AnonymousClass4(int i) {
//            this.edJ = i;
//        }
//
//        public final void run() {
//            p.b(p.this, this.edJ);
//        }
//
//        public final String toString() {
//            return super.toString() + "|cancelImp_" + this.edJ;
//        }
//    }
//
//    public interface a {
//        void a(p pVar);
//
//        void a(p pVar, boolean z);
//    }
//
//    public final void bM(boolean z) {
//        this.edF = z;
//        if (this.edF) {
//            y.e("MicroMsg.NetSceneQueue", "the working process is ready to be killed");
//            am amVar = this.edG;
//            long j = this.edE;
//            amVar.S(j, j);
//            return;
//        }
//        this.edG.stopTimer();
//    }
//
//    public final void a(int i, String str, int i2, boolean z) {
//        if (this.edx == null) {
//            y.e("MicroMsg.NetSceneQueue", "logUtil autoAuth  == null");
//        } else {
//            this.edx.a(i, str, i2, z);
//        }
//    }
//
//    public final void bN(boolean z) {
//        this.foreground = z;
//        this.edC = Boolean.valueOf(z);
//        b.bN(z);
//        WXHardCoderJNI.foreground = z;
//        if (this.edx == null) {
//            y.e("MicroMsg.NetSceneQueue", "setForeground autoAuth  == null");
//        } else {
//            this.edx.bO(z);
//        }
//    }
//
//    public final int KG() {
//        try {
//            if (this.edx != null && this.edx.KS() != null) {
//                return this.edx.KS().TV();
//            }
//            y.e("MicroMsg.NetSceneQueue", "[arthurdan.getNetworkStatus] Notice!!! autoAuth and autoAuth.getNetworkEvent() is null!!!!");
//            if (aq.isConnected(ae.getContext())) {
//                return 6;
//            }
//            return 0;
//        } catch (Exception e) {
//            y.e("MicroMsg.NetSceneQueue", "exception:%s", bk.j(e));
//        }
//    }
//
//    public final boolean KH() {
//        if (this.edx != null) {
//            return this.edx.KH();
//        }
//        return true;
//    }
//
//    public final String getNetworkServerIp() {
//        if (this.edx != null) {
//            return this.edx.getNetworkServerIp();
//        }
//        return Platform.UNKNOWN;
//    }
//
//    private p(a aVar) {
//        this.edD = aVar;
//    }
//
//    public final void d(e eVar) {
//        this.edx = eVar;
//        eVar.bO(this.foreground);
//        KK();
//    }
//
//    public static p a(a aVar) {
//        if (edw == null) {
//            edw = new p(aVar);
//        }
//        return edw;
//    }
//
//    public final void a(int i, f fVar) {
//        synchronized (this.edB) {
//            if (!this.edB.containsKey(Integer.valueOf(i))) {
//                this.edB.put(Integer.valueOf(i), new HashSet());
//            }
//            if (!((Set) this.edB.get(Integer.valueOf(i))).contains(fVar) && ((Set) this.edB.get(Integer.valueOf(i))).add(fVar)) {
//                ListenerInstanceMonitor.bP(fVar);
//            }
//        }
//    }
//
//    public final void b(int i, f fVar) {
//        synchronized (this.edB) {
//            try {
//                if (this.edB.get(Integer.valueOf(i)) != null && ((Set) this.edB.get(Integer.valueOf(i))).remove(fVar)) {
//                    ListenerInstanceMonitor.bQ(fVar);
//                }
//            } catch (Exception e) {
//            }
//        }
//    }
//
//    public final void reset() {
//        if (this.edx != null) {
//            this.edx.reset();
//        }
//        KI();
//        Vector<m> vector = this.edA;
//        this.edA = new Vector();
//        for (m mVar : vector) {
//            y.i("MicroMsg.NetSceneQueue", "reset::cancel scene " + mVar.getType());
//            mVar.cancel();
//            b(3, -1, "doScene failed clearWaitingQueue", mVar);
//        }
//        vector.clear();
//    }
//
//    public final void KI() {
//        Vector<m> vector = this.edz;
//        this.edz = new Vector();
//        for (m mVar : vector) {
//            y.i("MicroMsg.NetSceneQueue", "reset::cancel scene " + mVar.getType());
//            mVar.cancel();
//            b(3, -1, "doScene failed clearRunningQueue", mVar);
//        }
//        vector.clear();
//    }
//
//    public final void KJ() {
//        y.i("MicroMsg.NetSceneQueue", "resetDispatcher");
//        if (this.edx != null) {
//            this.edx.reset();
//            this.edx = null;
//        }
//    }
//
//    public final e Do() {
//        return this.edx;
//    }
//
//    public final void cancel(final int i) {
//        y.l("MicroMsg.NetSceneQueue", "cancel sceneHashCode:%d", Integer.valueOf(i));
//        this.edy.O(new Runnable() {
//            public final void run() {
//                p.a(p.this, i);
//            }
//
//            public final String toString() {
//                return super.toString() + "|cancelImp_" + i;
//            }
//        });
//    }
//
//    public final void c(m mVar) {
//        if (mVar != null) {
//            y.l("MicroMsg.NetSceneQueue", "cancel sceneHashCode:%d", Integer.valueOf(mVar.hashCode()));
//            mVar.cancel();
//            synchronized (this.lock) {
//                this.edA.remove(mVar);
//                this.edz.remove(mVar);
//            }
//        }
//    }
//
//    public final boolean d(m mVar) {
//        return a(mVar, 0);
//    }
//
//    /**
//     * com.tencent.mm.ah.p
//     * @param mVar  com.tencent.mm.pluginsdk.model.m
//     * @param i
//     * @return
//     */
//    public final boolean a(m mVar, int i) {
//        boolean z = mVar != null || i >= 0;
//        Assert.assertTrue(z);
//        String str = "worker thread has not been set";
//        if (this.edy != null) {
//            z = true;
//        } else {
//            z = false;
//        }
//        Assert.assertTrue(str, z);
//        if (!e(mVar)) {
//            return false;
//        }
//        b(mVar, i);
//        return true;
//    }
//
//    private void b(final m mVar, int i) {
//        boolean KL = KL();
//        int size = this.edz.size();
//        String str = "MicroMsg.NetSceneQueue";
//        String str2 = "doSceneImp start: mmcgi type:%d hash[%d,%d] run:%d wait:%d afterSec:%d canDo:%b autoauth:%d";
//        Object[] objArr = new Object[8];
//        objArr[0] = Integer.valueOf(mVar.getType());
//        objArr[1] = Integer.valueOf(mVar.hashCode());
//        objArr[2] = Integer.valueOf(mVar.KC());
//        objArr[3] = Integer.valueOf(size);
//        objArr[4] = Integer.valueOf(this.edA.size());
//        objArr[5] = Integer.valueOf(i);
//        objArr[6] = Boolean.valueOf(KL);
//        objArr[7] = Integer.valueOf(this.edx == null ? 0 : this.edx.hashCode());
//        y.i(str, str2, objArr);
//        if (i == 0 && KL && this.edx != null) {
//            synchronized (this.lock) {
//                this.edz.add(mVar);
//                if (size == this.edz.size()) {
//                    y.w("MicroMsg.NetSceneQueue", "doSceneImp mmcgi  Add to runningQueue wrong  type:%d hash:%d run:[%d ,%d] wait:%d ", Integer.valueOf(mVar.getType()), Integer.valueOf(mVar.hashCode()), Integer.valueOf(size), Integer.valueOf(this.edz.size()), Integer.valueOf(this.edA.size()));
//                }
//            }
//            this.edy.O(new Runnable() {
//                public final void run() {
//                    int i;
//                    String str;
//                    String str2;
//                    Object[] objArr;
//                    int i2 = 0;
//                    mVar.edg = p.this;
//                    if (mVar.aSd || p.this.edx == null) {
//                        i = 0;
//                    } else {
//                        i = mVar.a(p.this.edx, p.this);
//                        if (i >= 0) {
//                            str = "MicroMsg.NetSceneQueue";
//                            str2 = "On doscene  mmcgi type:%d hash[%d,%d] run:%d wait:%d ret:%d autoauth:%d";
//                            objArr = new Object[7];
//                            objArr[0] = Integer.valueOf(mVar.getType());
//                            objArr[1] = Integer.valueOf(mVar.hashCode());
//                            objArr[2] = Integer.valueOf(mVar.KC());
//                            objArr[3] = Integer.valueOf(p.this.edz.size());
//                            objArr[4] = Integer.valueOf(p.this.edA.size());
//                            objArr[5] = Integer.valueOf(i);
//                            if (p.this.edx == null) {
//                                i = 0;
//                            } else {
//                                i = p.this.edx.hashCode();
//                            }
//                            objArr[6] = Integer.valueOf(i);
//                            y.i(str, str2, objArr);
//                            mVar.edh = false;
//                            return;
//                        }
//                    }
//                    str = "MicroMsg.NetSceneQueue";
//                    str2 = "doscene mmcgi Failed type:%d hash[%d,%d] cancel[%b] run:%d wait:%d ret:%d autoauth:%d";
//                    objArr = new Object[8];
//                    objArr[0] = Integer.valueOf(mVar.getType());
//                    objArr[1] = Integer.valueOf(mVar.hashCode());
//                    objArr[2] = Integer.valueOf(mVar.KC());
//                    objArr[3] = Boolean.valueOf(mVar.aSd);
//                    objArr[4] = Integer.valueOf(p.this.edz.size());
//                    objArr[5] = Integer.valueOf(p.this.edA.size());
//                    objArr[6] = Integer.valueOf(i);
//                    if (p.this.edx != null) {
//                        i2 = p.this.edx.hashCode();
//                    }
//                    objArr[7] = Integer.valueOf(i2);
//                    y.w(str, str2, objArr);
//                    mVar.edg = null;
//                    synchronized (p.this.lock) {
//                        p.this.edz.remove(mVar);
//                    }
//                    if (!mVar.aSd) {
//                        p.this.handler.post(new Runnable() {
//                            public final void run() {
//                                p.this.onSceneEnd(3, -1, "doScene failed", mVar);
//                            }
//                        });
//                    }
//                }
//
//                public final String toString() {
//                    return super.toString() + "|doSceneImp_" + mVar + "_type=" + mVar.getType();
//                }
//            });
//        } else if (i > 0) {
//            Message obtain = Message.obtain();
//            obtain.obj = mVar;
//            this.handler.sendMessageDelayed(obtain, (long) i);
//            y.i("MicroMsg.NetSceneQueue", "timed: type=" + mVar.getType() + " id=" + mVar.hashCode() + " cur_after_sec=" + i);
//        } else {
//            y.i("MicroMsg.NetSceneQueue", "waited: type=" + mVar.getType() + " id=" + mVar.hashCode() + " cur_waiting_cnt=" + this.edA.size());
//            synchronized (this.lock) {
//                this.edA.add(mVar);
//            }
//            y.i("MicroMsg.NetSceneQueue", "waitingQueue_size = " + this.edA.size());
//        }
//        if (this.edx != null) {
//            edH = 1;
//        } else if (this.edD == null) {
//            y.e("MicroMsg.NetSceneQueue", "prepare dispatcher failed, queue idle:%s", this.edD);
//        } else {
//            this.edD.a(this);
//            am amVar = new am(Looper.getMainLooper(), new com.tencent.mm.sdk.platformtools.am.a() {
//                private long edM = 10;
//
//                public final boolean tC() {
//                    if (p.this.edx == null) {
//                        long j = this.edM;
//                        this.edM = j - 1;
//                        if (j > 0) {
//                            return true;
//                        }
//                    }
//                    p.this.KK();
//                    return false;
//                }
//            }, true);
//            long j = (long) (edH * 100);
//            amVar.S(j, j);
//            if (edH < 512) {
//                edH *= 2;
//            }
//        }
//    }
//
//    public final void onSceneEnd(int i, int i2, String str, m mVar) {
//        int i3 = 0;
//        mVar.edh = true;
//        synchronized (this.lock) {
//            this.edz.remove(mVar);
//        }
//        String str2 = "MicroMsg.NetSceneQueue";
//        String str3 = "onSceneEnd mmcgi type:%d hash[%d,%d] run:%d wait:%d autoauth:%d [%d,%d,%s]";
//        Object[] objArr = new Object[9];
//        objArr[0] = Integer.valueOf(mVar.getType());
//        objArr[1] = Integer.valueOf(mVar.hashCode());
//        objArr[2] = Integer.valueOf(mVar.KC());
//        objArr[3] = Integer.valueOf(this.edz.size());
//        objArr[4] = Integer.valueOf(this.edA.size());
//        if (this.edx != null) {
//            i3 = this.edx.hashCode();
//        }
//        objArr[5] = Integer.valueOf(i3);
//        objArr[6] = Integer.valueOf(i);
//        objArr[7] = Integer.valueOf(i2);
//        objArr[8] = str;
//        y.i(str2, str3, objArr);
//        KK();
//        b(i, i2, str, mVar);
//        if (this.edF && this.edz.isEmpty() && this.edA.isEmpty()) {
//            am amVar = this.edG;
//            long j = this.edE;
//            amVar.S(j, j);
//        }
//    }
//
//    private void b(int i, int i2, String str, m mVar) {
//        final m mVar2 = mVar;
//        final int i3 = i;
//        final int i4 = i2;
//        final String str2 = str;
//        this.handler.post(new Runnable() {
//            public final void run() {
//                HashSet<f> hashSet;
//                Set set = (Set) p.this.edB.get(Integer.valueOf(mVar2.getType()));
//                if (set != null && set.size() > 0) {
//                    hashSet = new HashSet();
//                    hashSet.addAll(set);
//                    for (f fVar : hashSet) {
//                        if (fVar != null && set.contains(fVar)) {
//                            fVar.onSceneEnd(i3, i4, str2, mVar2);
//                        }
//                    }
//                }
//                set = (Set) p.this.edB.get(Integer.valueOf(-1));
//                if (set != null && set.size() > 0) {
//                    hashSet = new HashSet();
//                    hashSet.addAll(set);
//                    for (f fVar2 : hashSet) {
//                        if (fVar2 != null && set.contains(fVar2)) {
//                            fVar2.onSceneEnd(i3, i4, str2, mVar2);
//                        }
//                    }
//                }
//            }
//        });
//    }
//
//    private void KK() {
//        synchronized (this.lock) {
//            if (this.edA.size() > 0) {
//                m mVar = (m) this.edA.get(0);
//                int i = mVar.priority;
//                int i2 = 1;
//                m mVar2 = mVar;
//                while (i2 < this.edA.size()) {
//                    if (((m) this.edA.get(i2)).priority > i) {
//                        this.edA.get(i2);
//                        if (KL()) {
//                            mVar = (m) this.edA.get(i2);
//                            i = mVar.priority;
//                            i2++;
//                            mVar2 = mVar;
//                        }
//                    }
//                    mVar = mVar2;
//                    i2++;
//                    mVar2 = mVar;
//                }
//                this.edA.remove(mVar2);
//                y.i("MicroMsg.NetSceneQueue", "waiting2running waitingQueue_size = " + this.edA.size());
//                b(mVar2, 0);
//            }
//        }
//    }
//
//    private boolean KL() {
//        if (this.edz.size() >= 50) {
//            return false;
//        }
//        return true;
//    }
//
//    private boolean e(m mVar) {
//        int type = mVar.getType();
//        if (mVar.Kx()) {
//            synchronized (this.lock) {
//                m mVar2;
//                Iterator it = this.edz.iterator();
//                while (it.hasNext()) {
//                    mVar2 = (m) it.next();
//                    if (mVar2.getType() == type) {
//                        y.i("MicroMsg.NetSceneQueue", "forbid in running: type=" + mVar.getType() + " id=" + mVar.hashCode() + " cur_running_cnt=" + this.edz.size());
//                        if (mVar.b(mVar2)) {
//                            return true;
//                        } else if (mVar.a(mVar2)) {
//                            y.e("MicroMsg.NetSceneQueue", "forbid in running diagnostic: type=" + mVar.getType() + " id=" + mVar.hashCode() + " cur_running_cnt=" + this.edz.size() + " ---" + mVar2.hashCode());
//                            if (!this.foreground) {
//                                y.e("MicroMsg.NetSceneQueue", "forbid in running diagnostic: type=" + mVar.getType() + "acinfo[" + mVar2.getInfo() + "] scinfo[" + mVar.getInfo() + "]");
//                                y.cqL();
//                                Assert.assertTrue("NetsceneQueue forbid in running diagnostic: type=" + mVar.getType(), false);
//                            }
//                            c(mVar2);
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//                it = this.edA.iterator();
//                while (it.hasNext()) {
//                    mVar2 = (m) it.next();
//                    if (mVar2.getType() == type) {
//                        y.i("MicroMsg.NetSceneQueue", "forbid in waiting: type=" + mVar.getType() + " id=" + mVar.hashCode() + " cur_waiting_cnt=" + this.edA.size());
//                        if (mVar.b(mVar2)) {
//                            return true;
//                        } else if (mVar.a(mVar2)) {
//                            y.e("MicroMsg.NetSceneQueue", "forbid in waiting diagnostic: type=" + mVar.getType() + " id=" + mVar.hashCode() + " cur_waiting_cnt=" + this.edA.size() + " ---" + mVar2.hashCode());
//                            if (!this.foreground) {
//                                y.cqL();
//                                Assert.assertTrue("NetsceneQueue forbid in waiting diagnostic: type=" + mVar.getType(), false);
//                            }
//                            c(mVar2);
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }
//}