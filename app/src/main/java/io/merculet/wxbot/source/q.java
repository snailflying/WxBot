//package io.merculet.wxbot.config;
//
//import android.annotation.SuppressLint;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
//import android.media.ToneGenerator;
//import android.os.Looper;
//import android.os.Vibrator;
//import android.text.TextUtils;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.widget.Toast;
//import com.tencent.mm.R;
//import com.tencent.mm.ah.j;
//import com.tencent.mm.compatible.util.e;
//import com.tencent.mm.h.a.ne;
//import com.tencent.mm.h.a.tc;
//import com.tencent.mm.h.a.td;
//import com.tencent.mm.m.g;
//import com.tencent.mm.model.m;
//import com.tencent.mm.model.s;
//import com.tencent.mm.opensdk.modelmsg.WXFileObject;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.plugin.report.service.h;
//import com.tencent.mm.plugin.subapp.c.i;
//import com.tencent.mm.pluginsdk.model.app.f;
//import com.tencent.mm.pluginsdk.ui.chat.AppPanel;
//import com.tencent.mm.pluginsdk.ui.chat.ChatFooter;
//import com.tencent.mm.pluginsdk.ui.chat.b;
//import com.tencent.mm.pluginsdk.ui.tools.NewFileExplorerUI;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ag;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.am;
//import com.tencent.mm.sdk.platformtools.am.a;
//import com.tencent.mm.sdk.platformtools.aq;
//import com.tencent.mm.sdk.platformtools.au;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ac;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.ui.base.l;
//import com.tencent.mm.ui.base.n;
//import com.tencent.mm.ui.base.n.c;
//import com.tencent.mm.ui.chatting.b.b.aa;
//import com.tencent.mm.ui.chatting.b.b.ai;
//import com.tencent.mm.ui.chatting.b.b.t;
//import com.tencent.mm.ui.chatting.b.b.z;
//import com.tencent.mm.ui.widget.a.d;
//import com.tencent.smtt.sdk.TbsListener.ErrorCode;
//import com.tencent.tmassistantsdk.openSDK.TMQQDownloaderOpenSDKConst;
//import com.tencent.ttpic.baseutils.FileUtils;
//import com.tenpay.android.wechat.PayuSecureEncrypt;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public final class q implements b {
//    public static boolean vjI = true;
//    private ad dnp;
//    private final am eNb = new am(new a() {
//        public final boolean tC() {
//            q.this.maV.Ek(q.this.vjJ.getMaxAmplitude());
//            return true;
//        }
//    }, true);
//    private final am ibI = new am(new a() {
//        public final boolean tC() {
//            long uy = q.this.vjJ.uy();
//            y.d("MicroMsg.ChattingFooterEventImpl", "ms " + uy);
//            if (uy >= 50000 && uy <= 60000) {
//                if (!q.this.vjR) {
//                    bk.fO(q.this.uPh.vtz.getContext());
//                    q.this.vjR = true;
//                }
//                int i = (int) ((60000 - uy) / 1000);
//                q.this.maV.setRecordNormalWording(q.this.uPh.vtz.getMMResources().getQuantityString(R.j.chatting_rcd_time_limit, i, new Object[]{Integer.valueOf(i)}));
//            }
//            if (uy < 60000) {
//                return true;
//            }
//            y.v("MicroMsg.ChattingFooterEventImpl", "record stop on countdown");
//            q.this.cCH();
//            q.this.maV.aRj();
//            au.G(q.this.uPh.vtz.getContext(), R.l.time_limit);
//            return false;
//        }
//    }, true);
//    private final j.a ibK = new j.a() {
//        public final void onError() {
//            q.this.vjJ.reset();
//            q.this.eNb.stopTimer();
//            q.this.ibI.stopTimer();
//            ag.Zn("keep_app_silent");
//            q.this.maV.aRj();
//            ((ai) q.this.uPh.ac(ai.class)).cFx().cBX();
//            y.v("MicroMsg.ChattingFooterEventImpl", "record stop on error");
//            q.this.uPh.vtz.enableOptionMenu(true);
//            q.this.uPh.vtz.enableBackMenu(true);
//            Toast.makeText(q.this.uPh.vtz.getContext(), q.this.uPh.vtz.getContext().getString(R.l.chatting_rcd_err), 0).show();
//        }
//    };
//    private ToneGenerator ibo;
//    private Vibrator ibr;
//    private Object lock = new Object();
//    private ChatFooter maV;
//    private AppPanel.a mbH = new AppPanel.a() {
//        d mYD = null;
//
//        public final void bhm() {
//            if (!com.tencent.mm.r.a.bi(q.this.uPh.vtz.getContext())) {
//                tc tcVar = new tc();
//                com.tencent.mm.sdk.b.a.udP.m(tcVar);
//                if (q.this.getTalkerUserName().equals(tcVar.ccF.talker) || !(tcVar.ccF.ccH || tcVar.ccF.ccI)) {
//                    if (1 == g.AA().getInt("EnableVoiceVoipFromPlugin", 0)) {
//                        boolean z = (ad.aaU(q.this.uPh.pSb.field_username) || s.hb(q.this.uPh.pSb.field_username)) ? (((com.tencent.mm.openim.a.b) com.tencent.mm.kernel.g.r(com.tencent.mm.openim.a.b.class)).oS(q.this.uPh.pSb.field_openImAppid) & 8192) == 0 : false;
//                        this.mYD = new d(q.this.uPh.vtz.getContext(), 1, false);
//                        this.mYD.phH = new c() {
//                            public final void a(l lVar) {
//                                lVar.ak(2, R.l.app_field_voip, R.k.sharemore_videovoip);
//                                if (!z) {
//                                    lVar.ak(1, R.l.app_field_voipaudio, R.k.sharemore_voipvoice);
//                                }
//                            }
//                        };
//                        this.mYD.phI = new n.d() {
//                            public final void onMMMenuItemSelected(MenuItem menuItem, int i) {
//                                switch (menuItem.getItemId()) {
//                                    case 1:
//                                        q.this.bsG();
//                                        return;
//                                    case 2:
//                                        q.this.bsH();
//                                        return;
//                                    default:
//                                        return;
//                                }
//                            }
//                        };
//                        this.mYD.cfU();
//                    } else {
//                        q.this.cCK();
//                    }
//                    h.nFQ.f(11033, Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(0));
//                    return;
//                }
//                Toast.makeText(q.this.uPh.vtz.getContext(), tcVar.ccF.ccG ? R.l.cannot_use_voip_bcz_video_talking : R.l.cannot_use_voip_bcz_voice_talking, 0).show();
//                y.i("MicroMsg.ChattingFooterEventImpl", "voip is running, can't do this");
//            }
//        }
//
//        public final void bhn() {
//            if (!com.tencent.mm.r.a.bi(q.this.uPh.vtz.getContext())) {
//                tc tcVar = new tc();
//                com.tencent.mm.sdk.b.a.udP.m(tcVar);
//                if (q.this.getTalkerUserName().equals(tcVar.ccF.talker) || !(tcVar.ccF.ccH || tcVar.ccF.ccI)) {
//                    q.this.cCJ();
//                    return;
//                }
//                Toast.makeText(q.this.uPh.vtz.getContext(), tcVar.ccF.ccG ? R.l.cannot_use_voip_bcz_video_talking : R.l.cannot_use_voip_bcz_voice_talking, 0).show();
//                y.i("MicroMsg.ChattingFooterEventImpl", "voip is running, can't do this");
//            }
//        }
//
//        public final void bho() {
//            if (!com.tencent.mm.r.a.bk(q.this.uPh.vtz.getContext()) && !com.tencent.mm.r.a.bi(q.this.uPh.vtz.getContext())) {
//                y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(q.this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 81, "", "")), bk.csb(), q.this.uPh.vtz.getContext());
//                if (com.tencent.mm.pluginsdk.permission.a.a(q.this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 81, "", "")) {
//                    q.this.cCL();
//                }
//            }
//        }
//
//        public final void bhp() {
//            ((t) q.this.uPh.ac(t.class)).cEY();
//        }
//
//        public final void bhq() {
//            ((com.tencent.mm.ui.chatting.b.b.d) q.this.uPh.ac(com.tencent.mm.ui.chatting.b.b.d.class)).cDW();
//        }
//
//        public final void tA(int i) {
//            switch (i) {
//                case 0:
//                    File file = new File(e.dzD);
//                    if (file.exists() || file.mkdir()) {
//                        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkcamera[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(q.this.uPh.vtz.getContext(), "android.permission.CAMERA", 20, "", "")), bk.csb(), q.this.uPh.vtz.getContext());
//                        if (com.tencent.mm.pluginsdk.permission.a.a(q.this.uPh.vtz.getContext(), "android.permission.CAMERA", 20, "", "")) {
//                            q.this.cCM();
//                            return;
//                        }
//                        return;
//                    }
//                    Toast.makeText(q.this.uPh.vtz.getContext(), q.this.uPh.vtz.getMMResources().getString(R.l.chatting_toast_sdk_fail), 1).show();
//                    return;
//                case 1:
//                    String string = q.this.uPh.vtz.getContext().getSharedPreferences(ae.cqR(), 0).getString("gallery", "1");
//                    com.tencent.mm.plugin.report.service.g.wI(19);
//                    if (string.equalsIgnoreCase("0")) {
//                        com.tencent.mm.pluginsdk.ui.tools.l.r(q.this.uPh.vtz);
//                    } else {
//                        string = q.this.uPh.cFB();
//                        String talkerUserName = q.this.uPh.getTalkerUserName();
//                        if (q.this.vjO && q.vjI) {
//                            com.tencent.mm.pluginsdk.ui.tools.l.b(q.this.uPh.vtz, 3, string, talkerUserName);
//                        } else if (ad.gr(talkerUserName)) {
//                            com.tencent.mm.pluginsdk.ui.tools.l.a(q.this.uPh.vtz, 12, string, talkerUserName);
//                        } else {
//                            com.tencent.mm.pluginsdk.ui.tools.l.a(q.this.uPh.vtz, 3, string, talkerUserName);
//                        }
//                    }
//                    h.nFQ.f(13822, Integer.valueOf(2), Integer.valueOf(1));
//                    com.tencent.mm.sdk.platformtools.ai.l(new Runnable() {
//                        public final void run() {
//                            q.this.maV.setBottomPanelVisibility(8);
//                        }
//                    }, 1000);
//                    return;
//                default:
//                    return;
//            }
//        }
//
//        public final void b(f fVar) {
//            ((com.tencent.mm.ui.chatting.b.b.a) q.this.uPh.ac(com.tencent.mm.ui.chatting.b.b.a.class)).n(fVar);
//        }
//
//        public final void bhr() {
//            com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "subapp", ".ui.openapi.AddAppUI", new Intent());
//        }
//
//        public final void bhs() {
//            Intent intent = new Intent();
//            ArrayList arrayList = new ArrayList();
//            arrayList.add(TMQQDownloaderOpenSDKConst.VERIFYTYPE_ALL);
//            if (com.tencent.mm.ai.f.eW(q.this.dnp.field_username)) {
//                arrayList.add("4");
//                arrayList.add("7");
//                arrayList.add("9");
//                arrayList.add(PayuSecureEncrypt.ENCRYPT_VERSION_DEFAULT);
//                arrayList.add("11");
//                arrayList.add("12");
//                arrayList.add("13");
//                arrayList.add("15");
//                arrayList.add("16");
//                arrayList.add("17");
//                arrayList.add("18");
//            }
//            intent.putExtra("key_to_user", q.this.dnp.field_username);
//            intent.putExtra("key_fav_item_id", TextUtils.join(",", arrayList));
//            com.tencent.mm.plugin.fav.a.b.a(q.this.uPh.vtz.getContext(), ".ui.FavSelectUI", intent);
//            h.nFQ.f(14103, Integer.valueOf(1));
//        }
//
//        public final void bht() {
//            Intent intent = new Intent();
//            intent.putExtra("service_app_talker_user", q.this.getTalkerUserName());
//            com.tencent.mm.br.d.a(q.this.uPh.vtz, "subapp", ".ui.openapi.ServiceAppUI", intent, (int) ErrorCode.UNLZMA_FAIURE);
//        }
//
//        public final void bhu() {
//            Intent intent = new Intent();
//            intent.putExtra("download_entrance_scene", 17);
//            intent.putExtra("preceding_scence", 13);
//            com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "emoji", ".ui.v2.EmojiStoreV2UI", intent);
//            h.nFQ.f(12065, Integer.valueOf(4));
//        }
//
//        public final void tB(int i) {
//            if (!com.tencent.mm.r.a.bj(q.this.uPh.vtz.getContext()) && !com.tencent.mm.r.a.bk(q.this.uPh.vtz.getContext()) && !com.tencent.mm.r.a.bi(q.this.uPh.vtz.getContext())) {
//                q.this.GP(i);
//            }
//        }
//
//        public final void bhv() {
//            h.nFQ.f(12097, Integer.valueOf(11), Integer.valueOf(0), Long.valueOf(System.currentTimeMillis()));
//            h.nFQ.f(11850, Integer.valueOf(4), Integer.valueOf(1));
//            Intent intent;
//            if (q.this.vjM) {
//                h.nFQ.f(11701, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(2), Integer.valueOf(2));
//                intent = new Intent();
//                int gM = m.gM(q.this.getTalkerUserName());
//                intent.putExtra("key_way", 1);
//                intent.putExtra("key_chatroom_num", gM);
//                intent.putExtra("key_type", 1);
//                intent.putExtra("key_from", 1);
//                intent.putExtra("key_username", q.this.getTalkerUserName());
//                intent.putExtra("pay_channel", 14);
//                com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "luckymoney", ".ui.LuckyMoneyPrepareUI", intent);
//                return;
//            }
//            com.tencent.mm.model.au.Hx();
//            Integer num = (Integer) com.tencent.mm.model.c.Dz().get(ac.a.USERINFO_LUCKY_MONEY_NEWYEAR_SWITCH_INT_SYNC, Integer.valueOf(0));
//            com.tencent.mm.model.au.Hx();
//            Integer num2 = (Integer) com.tencent.mm.model.c.Dz().get(ac.a.USERINFO_LUCKY_MONEY_NEWYEAR_LOCAL_SWITCH_INT, Integer.valueOf(0));
//            if (num.intValue() == 1 || num2.intValue() == 1) {
//                if ((com.tencent.mm.model.q.Gq() == 0 ? 1 : 0) != 0) {
//                    com.tencent.mm.ui.base.h.a(q.this.uPh.vtz.getContext(), null, new String[]{q.this.uPh.vtz.getMMResources().getString(R.l.chatting_lucky_money_new_year), q.this.uPh.vtz.getMMResources().getString(R.l.chatting_lucky_money_short_title)}, null, new com.tencent.mm.ui.base.h.c() {
//                        public final void gl(int i) {
//                            Intent intent = new Intent();
//                            switch (i) {
//                                case 0:
//                                    h.nFQ.f(11701, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(3));
//                                    intent.putExtra("key_username", q.this.getTalkerUserName());
//                                    intent.putExtra("key_way", 0);
//                                    intent.putExtra("pay_channel", 11);
//                                    com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "luckymoney", ".ui.LuckyMoneyNewYearSendUI", intent);
//                                    return;
//                                case 1:
//                                    h.nFQ.f(11701, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1));
//                                    intent.putExtra("key_way", 0);
//                                    intent.putExtra("key_type", 0);
//                                    intent.putExtra("key_from", 1);
//                                    intent.putExtra("key_username", q.this.getTalkerUserName());
//                                    intent.putExtra("pay_channel", 11);
//                                    com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "luckymoney", ".ui.LuckyMoneyPrepareUI", intent);
//                                    return;
//                                default:
//                                    return;
//                            }
//                        }
//                    });
//                    return;
//                }
//            }
//            h.nFQ.f(11701, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1));
//            intent = new Intent();
//            intent.putExtra("key_way", 0);
//            intent.putExtra("key_type", 0);
//            intent.putExtra("key_from", 1);
//            intent.putExtra("key_username", q.this.getTalkerUserName());
//            intent.putExtra("pay_channel", 11);
//            com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "luckymoney", ".ui.LuckyMoneyPrepareUI", intent);
//        }
//
//        public final void bhw() {
//            com.tencent.mm.model.au.Hx();
//            com.tencent.mm.model.c.Dz().o(81, Boolean.valueOf(false));
//            if (aq.isNetworkConnected(q.this.uPh.vtz.getContext())) {
//                q.this.cCI();
//            } else {
//                com.tencent.mm.bf.e.a(q.this.uPh.vtz.getContext(), R.l.voip_net_unavailable, null);
//            }
//        }
//
//        public final void bhx() {
//            Intent intent = new Intent();
//            intent.putExtra("enterprise_scene", 4);
//            intent.putExtra("enterprise_biz_name", q.this.getTalkerUserName());
//            intent.putExtra("biz_chat_chat_id", ((com.tencent.mm.ui.chatting.b.b.c) q.this.uPh.ac(com.tencent.mm.ui.chatting.b.b.c.class)).cDS());
//            com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "brandservice", ".ui.EnterpriseBizContactPlainListUI", intent);
//        }
//
//        public final void bhy() {
//            if (q.this.vjM) {
//                Intent intent = new Intent();
//                intent.putExtra("enter_scene", 1);
//                intent.putExtra("chatroom_name", q.this.getTalkerUserName());
//                com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "aa", ".ui.LaunchAAUI", intent);
//            }
//        }
//
//        public final void bhz() {
//            h.nFQ.f(14523, Integer.valueOf(0));
//            Intent intent = new Intent();
//            intent.setClass(q.this.uPh.vtz.getContext(), NewFileExplorerUI.class);
//            intent.putExtra("TO_USER", q.this.vjK);
//            q.this.uPh.a(intent, ErrorCode.HOST_CONTEXT_IS_NULL, new com.tencent.mm.br.d.a() {
//                public final void onActivityResult(int i, int i2, Intent intent) {
//                    if (i == ErrorCode.HOST_CONTEXT_IS_NULL) {
//                        q.a(q.this, q.this.uPh, i2, intent);
//                    }
//                }
//            });
//        }
//    };
//    private final j.b mba = new j.b() {
//        public final void Ku() {
//            q.this.maV.cni();
//        }
//    };
//    com.tencent.mm.ui.chatting.c.a uPh;
//    private boolean vgB;
//    private j vjJ;
//    private String vjK;
//    private String vjL;
//    private boolean vjM;
//    private boolean vjN;
//    public boolean vjO;
//    private volatile boolean vjP = false;
//    private volatile boolean vjQ = false;
//    private boolean vjR = false;
//
//    static /* synthetic */ void a(q qVar, com.tencent.mm.ui.chatting.c.a aVar, int i, Intent intent) {
//        if (i == -1 && intent != null) {
//            String str;
//            ((z) aVar.ac(z.class)).g(217, i, intent);
//            ArrayList stringArrayListExtra = intent.getStringArrayListExtra("selected_file_lst");
//            int gM = aVar.cFE() ? m.gM(qVar.vjK) : 0;
//            Iterator it = stringArrayListExtra.iterator();
//            while (it.hasNext()) {
//                String str2 = (String) it.next();
//                WXFileObject wXFileObject = new WXFileObject();
//                wXFileObject.setFilePath(str2);
//                WXMediaMessage wXMediaMessage = new WXMediaMessage();
//                wXMediaMessage.mediaObject = wXFileObject;
//                File file = new File(str2);
//                wXMediaMessage.title = file.getName();
//                wXMediaMessage.description = bk.cm(file.length());
//                com.tencent.mm.pluginsdk.model.app.l.a(wXMediaMessage, "", "", qVar.vjK, 4, null);
//                int lastIndexOf = file.getName().lastIndexOf(".");
//                str = "";
//                if (lastIndexOf >= 0 && lastIndexOf < file.getName().length() - 1) {
//                    str = file.getName().substring(lastIndexOf + 1);
//                }
//                h hVar = h.nFQ;
//                Object[] objArr = new Object[5];
//                objArr[0] = Long.valueOf(file.length());
//                objArr[1] = Integer.valueOf(0);
//                objArr[2] = Integer.valueOf(aVar.cFE() ? 1 : 0);
//                objArr[3] = Integer.valueOf(gM);
//                objArr[4] = str;
//                hVar.f(14986, objArr);
//            }
//            str = intent.getStringExtra("with_text_content");
//            if (!bk.bl(str)) {
//                com.tencent.mm.plugin.messenger.a.g.bhI().dO(str, qVar.vjK);
//            }
//        }
//    }
//
//    static /* synthetic */ void i(q qVar) {
//        qVar.uPh.vtz.enableOptionMenu(false);
//        qVar.uPh.vtz.enableBackMenu(false);
//        if (qVar.vjJ != null) {
//            ag.Zm("keep_app_silent");
//            qVar.maV.setRecordNormalWording(qVar.uPh.vtz.getMMResources().getString(R.l.chatfooter_cancel_rcd));
//            qVar.vjJ.cE(qVar.getTalkerUserName());
//            qVar.vjL = qVar.vjJ.getFileName();
//            qVar.vjJ.a(qVar.mba);
//            qVar.ibr.vibrate(50);
//            qVar.uPh.axW();
//            qVar.cCG();
//            qVar.vjJ.a(qVar.ibK);
//            return;
//        }
//        y.e("MicroMsg.ChattingFooterEventImpl", "startRecording recorder is null and stop recod");
//    }
//
//    public q(com.tencent.mm.ui.chatting.c.a aVar, ChatFooter chatFooter, String str) {
//        this.uPh = aVar;
//        this.maV = chatFooter;
//        this.vjK = str;
//        com.tencent.mm.model.au.Hx();
//        this.dnp = com.tencent.mm.model.c.Fw().abl(str);
//        this.vjM = s.fn(this.uPh.getTalkerUserName());
//        this.vjN = s.hf(this.uPh.getTalkerUserName());
//        boolean z = this.vjM || this.vjN;
//        this.vgB = z;
//        this.ibr = (Vibrator) this.uPh.vtz.getContext().getSystemService("vibrator");
//        if (s.hH(this.vjK)) {
//            this.vjJ = new i();
//            y.i("MicroMsg.ChattingFooterEventImpl", "initRecorder new VoiceRemindRecorder().");
//        } else {
//            String str2 = this.vjK;
//            if (s.hS(str2)) {
//                z = true;
//            } else {
//                ad abl = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(str2);
//                if (abl != null && abl.cua()) {
//                    com.tencent.mm.ai.d kX = com.tencent.mm.ai.f.kX(abl.field_username);
//                    if (kX != null) {
//                        com.tencent.mm.ai.d.b bS = kX.bS(false);
//                        if (bS != null) {
//                            if (bS.efa != null) {
//                                bS.efe = "1".equals(bS.efa.optString("CanReceiveSpeexVoice"));
//                            }
//                            if (bS.efe) {
//                                y.i("MicroMsg.BizInfoStorageLogic", "bizinfo name=" + abl.field_username + " canReceiveSpeexVoice");
//                                z = true;
//                            }
//                        }
//                    }
//                }
//                z = false;
//            }
//            if (z) {
//                this.vjJ = new com.tencent.mm.f.b.h(this.uPh.vtz.getContext(), true);
//                y.i("MicroMsg.ChattingFooterEventImpl", "initRecorder new SceneVoiceRecorder, use Speex");
//            } else {
//                this.vjJ = new com.tencent.mm.f.b.h(this.uPh.vtz.getContext(), false);
//                y.i("MicroMsg.ChattingFooterEventImpl", "initRecorder new SceneVoiceRecorder, not use Speex");
//            }
//        }
//        this.vjJ.a(this.mba);
//        this.vjJ.a(this.ibK);
//        chatFooter.setAppPanelListener(this.mbH);
//    }
//
//    public final boolean bhf() {
//        synchronized (this.lock) {
//            this.vjP = true;
//        }
//        if (this.vjQ) {
//            this.vjQ = false;
//            releaseWakeLock();
//            if (cCH()) {
//                this.maV.aRj();
//                y.i("MicroMsg.ChattingFooterEventImpl", "record stop on stop request resetRcdStatus");
//            } else {
//                this.maV.cnh();
//                y.i("MicroMsg.ChattingFooterEventImpl", "record stop on stop request setRcdTooShort");
//            }
//            ((ai) this.uPh.ac(ai.class)).cFx().cBX();
//            ((aa) this.uPh.ac(aa.class)).Hf(4);
//            ((aa) this.uPh.ac(aa.class)).stopSignalling();
//            GO(1);
//            ((ai) this.uPh.ac(ai.class)).a(this.uPh.vtz, false);
//            return true;
//        }
//        y.i("MicroMsg.ChattingFooterEventImpl", "jacks in voice rcd stop but not begin.");
//        return false;
//    }
//
//    public final boolean bhh() {
//        y.v("MicroMsg.ChattingFooterEventImpl", "record cancel on cancel request");
//        synchronized (this.lock) {
//            this.vjP = true;
//        }
//        if (this.vjQ) {
//            this.vjQ = false;
//            releaseWakeLock();
//            this.uPh.vtz.enableOptionMenu(true);
//            this.uPh.vtz.enableBackMenu(true);
//            if (this.vjJ != null) {
//                this.vjJ.cancel();
//                this.eNb.stopTimer();
//                this.ibI.stopTimer();
//            }
//            this.maV.aRj();
//            ((ai) this.uPh.ac(ai.class)).cFx().cBX();
//            ((aa) this.uPh.ac(aa.class)).Hf(4);
//            ((aa) this.uPh.ac(aa.class)).stopSignalling();
//            GO(1);
//            ((ai) this.uPh.ac(ai.class)).a(this.uPh.vtz, false);
//            return true;
//        }
//        y.i("MicroMsg.ChattingFooterEventImpl", "jacks in voice rcd stop but not begin.");
//        return false;
//    }
//
//    public final boolean bhi() {
//        if (com.tencent.mm.r.a.bk(this.uPh.vtz.getContext()) || com.tencent.mm.r.a.bi(this.uPh.vtz.getContext())) {
//            y.d("MicroMsg.ChattingFooterEventImpl", "voip is running, cann't record voice");
//            return false;
//        }
//        com.tencent.mm.model.au.Hx();
//        if (com.tencent.mm.model.c.isSDCardAvailable()) {
//            if (!com.tencent.mm.compatible.f.b.zA()) {
//                com.tencent.mm.ui.base.h.a(this.uPh.vtz.getContext(), this.uPh.vtz.getMMResources().getString(R.l.app_special_no_record_audio_permission), this.uPh.vtz.getMMResources().getString(R.l.app_need_audio_title), this.uPh.vtz.getMMResources().getString(R.l.app_need_show_settings_button), true, new OnClickListener() {
//                    public final void onClick(DialogInterface dialogInterface, int i) {
//                        com.tencent.mm.compatible.f.b.bf(q.this.uPh.vtz.getContext());
//                    }
//                });
//            }
//            if (this.vjJ != null) {
//                if (this.ibo == null) {
//                    try {
//                        this.ibo = new ToneGenerator(3, (int) ((((float) this.uPh.vtz.getStreamMaxVolume(3)) / ((float) this.uPh.vtz.getStreamVolume(3))) * 100.0f));
//                        y.i("MicroMsg.ChattingFooterEventImpl", "init tone");
//                    } catch (Exception e) {
//                        y.e("MicroMsg.ChattingFooterEventImpl", "init tone failed");
//                    }
//                }
//                if (this.ibo != null) {
//                    this.ibo.startTone(24);
//                    y.i("MicroMsg.ChattingFooterEventImpl", "start tone");
//                }
//                this.uPh.getListView().postDelayed(new Runnable() {
//                    public final void run() {
//                        if (q.this.ibo != null) {
//                            q.this.ibo.stopTone();
//                            y.i("MicroMsg.ChattingFooterEventImpl", "stopTone");
//                        }
//                    }
//                }, 200);
//            }
//            synchronized (this.lock) {
//                this.vjP = false;
//            }
//            new ah(Looper.myLooper()).postDelayed(new Runnable() {
//                public final void run() {
//                    synchronized (q.this.lock) {
//                        if (q.this.vjP) {
//                            y.i("MicroMsg.ChattingFooterEventImpl", "jacks already stop before begin!!");
//                            return;
//                        }
//                        q.this.vjQ = true;
//                        q.this.eNb.S(100, 100);
//                        q.this.vjR = false;
//                        q.this.ibI.S(200, 200);
//                        q.this.maV.Ej(q.this.uPh.getListView().getHeight());
//                        d cFx = ((ai) q.this.uPh.ac(ai.class)).cFx();
//                        cFx.isRecording = true;
//                        cFx.cCb();
//                        cFx.cBW();
//                        q.i(q.this);
//                        q.this.uPh.setKeepScreenOn(true);
//                        ((aa) q.this.uPh.ac(aa.class)).Hf(3);
//                        ((aa) q.this.uPh.ac(aa.class)).keepSignalling();
//                        q.GO(0);
//                        ((ai) q.this.uPh.ac(ai.class)).a(q.this.uPh.vtz, true);
//                    }
//                }
//            }, 200);
//            return true;
//        }
//        com.tencent.mm.ui.base.s.gM(this.uPh.vtz.getContext());
//        y.e("MicroMsg.ChattingFooterEventImpl", "onVoiceRcdStartRequest isSDCardAvailable() failed and return.");
//        return false;
//    }
//
//    public final boolean GU(String str) {
//        cCG();
//        cCF();
//        return ((com.tencent.mm.ui.chatting.b.b.ac) this.uPh.ac(com.tencent.mm.ui.chatting.b.b.ac.class)).adw(str);
//    }
//
//    public final void bhj() {
//        cCG();
//        cCF();
//    }
//
//    public final void bhk() {
//        cCG();
//        cCF();
//    }
//
//    public final void D(MotionEvent motionEvent) {
//        if (motionEvent == null || motionEvent.getAction() == 1) {
//            cCG();
//            cCF();
//        }
//    }
//
//    private void cCF() {
//        com.tencent.mm.ui.chatting.c.a aVar = this.uPh;
//        y.i("MicroMsg.ChattingContext", "[smoothScrollBy] dis:%s duration:%s", Integer.valueOf(0), Integer.valueOf(0));
//        aVar.vtA.cCX();
//    }
//
//    @SuppressLint({"NewApi"})
//    @Deprecated
//    private void cCG() {
//        com.tencent.mm.ui.chatting.c.a aVar = this.uPh;
//        y.i("MicroMsg.ChattingContext", "[scrollToLast] force:%b", Boolean.valueOf(true));
//        aVar.vtA.nk(true);
//    }
//
//    private void releaseWakeLock() {
//        this.uPh.setKeepScreenOn(false);
//    }
//
//    /* JADX WARNING: Removed duplicated region for block: B:13:0x0048  */
//    private boolean cCH() {
//        /*
//        r7 = this;
//        r6 = 2;
//        r2 = 0;
//        r1 = 1;
//        r0 = r7.uPh;
//        r0 = r0.vtz;
//        r0.enableOptionMenu(r1);
//        r0 = r7.uPh;
//        r0 = r0.vtz;
//        r0.enableBackMenu(r1);
//        r0 = r7.vjJ;
//        if (r0 == 0) goto L_0x00ca;
//    L_0x0015:
//        r0 = r7.vjJ;
//        r0 = r0.isRecording();
//        if (r0 == 0) goto L_0x00b1;
//    L_0x001d:
//        r0 = r7.dnp;
//        r0 = r0.field_username;
//        r3 = "medianote";
//        r0 = r0.equals(r3);
//        if (r0 == 0) goto L_0x00af;
//    L_0x002a:
//        r0 = com.tencent.mm.model.q.Gn();
//        r0 = r0 & 16384;
//        if (r0 != 0) goto L_0x00af;
//    L_0x0032:
//        r0 = r1;
//    L_0x0033:
//        if (r0 == 0) goto L_0x00b1;
//    L_0x0035:
//        r0 = r1;
//    L_0x0036:
//        r3 = r7.vjJ;
//        r3 = r3.un();
//        r4 = r7.eNb;
//        r4.stopTimer();
//        r4 = r7.ibI;
//        r4.stopTimer();
//        if (r0 == 0) goto L_0x00a7;
//    L_0x0048:
//        r0 = new com.tencent.mm.storage.bi;
//        r0.<init>();
//        r4 = "medianote";
//        r0.ec(r4);
//        r4 = 34;
//        r0.setType(r4);
//        r0.fA(r1);
//        r1 = r7.vjL;
//        r0.ed(r1);
//        r0.setStatus(r6);
//        r1 = com.tencent.mm.model.q.Gj();
//        r4 = r7.vjJ;
//        r4 = r4.ux();
//        r4 = (long) r4;
//        r1 = com.tencent.mm.modelvoice.n.d(r1, r4, r2);
//        r0.setContent(r1);
//        r1 = "medianote";
//        r4 = com.tencent.mm.model.bd.iK(r1);
//        r0.bg(r4);
//        r1 = r7.vjJ;
//        r1 = r1.uA();
//        if (r1 != r6) goto L_0x008d;
//    L_0x0087:
//        r1 = "SOURCE_SILK_FILE";
//        r0.cY(r1);
//    L_0x008d:
//        com.tencent.mm.model.au.Hx();
//        r1 = com.tencent.mm.model.c.Fy();
//        r0 = r1.T(r0);
//        r4 = 0;
//        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
//        if (r2 > 0) goto L_0x00b3;
//    L_0x009e:
//        r0 = "MicroMsg.ChattingFooterEventImpl";
//        r1 = "insertLocalMsg fail";
//        com.tencent.mm.sdk.platformtools.y.e(r0, r1);
//    L_0x00a7:
//        r0 = "keep_app_silent";
//        com.tencent.mm.sdk.platformtools.ag.Zn(r0);
//        r2 = r3;
//    L_0x00ae:
//        return r2;
//    L_0x00af:
//        r0 = r2;
//        goto L_0x0033;
//    L_0x00b1:
//        r0 = r2;
//        goto L_0x0036;
//    L_0x00b3:
//        r2 = "MicroMsg.ChattingFooterEventImpl";
//        r4 = new java.lang.StringBuilder;
//        r5 = "insertLocalMsg success, msgId = ";
//        r4.<init>(r5);
//        r0 = r4.append(r0);
//        r0 = r0.toString();
//        com.tencent.mm.sdk.platformtools.y.i(r2, r0);
//        goto L_0x00a7;
//    L_0x00ca:
//        r0 = "MicroMsg.ChattingFooterEventImpl";
//        r1 = "stopRecording recorder == null";
//        com.tencent.mm.sdk.platformtools.y.i(r0, r1);
//        goto L_0x00ae;
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.ui.chatting.q.cCH():boolean");
//    }
//
//    public final String getTalkerUserName() {
//        if (this.dnp == null || !ad.hd(this.dnp.field_username)) {
//            return this.dnp == null ? null : this.dnp.field_username;
//        } else {
//            return this.vjK;
//        }
//    }
//
//    public final void release() {
//        if (this.ibo != null) {
//            y.i("MicroMsg.ChattingFooterEventImpl", "release");
//            this.ibo.release();
//            this.ibo = null;
//        }
//    }
//
//    public final void onPause() {
//        if (this.ibo != null) {
//            y.i("MicroMsg.ChattingFooterEventImpl", "release");
//            this.ibo.release();
//            this.ibo = null;
//        }
//        cCH();
//        this.eNb.stopTimer();
//        this.ibI.stopTimer();
//    }
//
//    public final void hq(boolean z) {
//        if (z) {
//            ((aa) this.uPh.ac(aa.class)).keepSignalling();
//        } else {
//            ((aa) this.uPh.ac(aa.class)).stopSignalling();
//        }
//    }
//
//    private static void GO(int i) {
//        ne neVar = new ne();
//        neVar.bWW.state = i;
//        com.tencent.mm.sdk.b.a.udP.m(neVar);
//    }
//
//    public final void cCI() {
//        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkCamera[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 22, "", "")), bk.csb(), this.uPh.vtz.getContext());
//        if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 22, "", "")) {
//            y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 22, "", "")), bk.csb(), this.uPh.vtz.getContext());
//            if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 22, "", "")) {
//                y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk");
//                Intent intent = new Intent();
//                if (com.tencent.mm.bg.d.eEX.nA(getTalkerUserName())) {
//                    y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk, but now is in share location!");
//                    Toast.makeText(ae.getContext(), R.l.in_share_location_tip, 0).show();
//                    return;
//                }
//                if (getTalkerUserName() != null) {
//                    int i;
//                    List<String> Iw = ((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).Iw(getTalkerUserName());
//                    com.tencent.mm.model.au.Hx();
//                    String str = (String) com.tencent.mm.model.c.Dz().get(2, null);
//                    boolean It = ((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).It(getTalkerUserName());
//                    for (String str2 : Iw) {
//                        if (str != null && str.equals(str2)) {
//                            i = 1;
//                            break;
//                        }
//                    }
//                    i = 0;
//                    if (Iw.size() < 9 || i != 0) {
//                        if (i != 0) {
//                            if (((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).bkD()) {
//                                y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk, but already in it!");
//                                Toast.makeText(ae.getContext(), ae.getContext().getString(R.l.multitalk_exit_tip), 0).show();
//                                return;
//                            }
//                            y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk, already inlist, but in fact not multitalking now!");
//                        }
//                        if (((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).bkB()) {
//                            y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk, but already in VoIP or multitalk!");
//                            Toast.makeText(ae.getContext(), ae.getContext().getString(R.l.multitalk_exit_tip), 0).show();
//                            return;
//                        } else if (It) {
//                            y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk, but this group already in multitalk, alter take in or not tips!");
//                            com.tencent.mm.ui.base.h.a(this.uPh.vtz.getContext(), this.uPh.vtz.getMMResources().getString(R.l.multitalk_talking_chose_enter), "", this.uPh.vtz.getMMResources().getString(R.l.multitalk_enter), this.uPh.vtz.getMMResources().getString(R.l.multitalk_cancel), new OnClickListener() {
//                                public final void onClick(DialogInterface dialogInterface, int i) {
//                                    if (!((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).It(q.this.getTalkerUserName())) {
//                                        Toast.makeText(ae.getContext(), ae.getContext().getString(R.l.multitalk_system_master_multi_finish_msg), 0).show();
//                                    } else if (!((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).Iz(q.this.getTalkerUserName())) {
//                                        Toast.makeText(ae.getContext(), ae.getContext().getString(R.l.multitalk_error_300), 0).show();
//                                    }
//                                }
//                            }, null);
//                            return;
//                        }
//                    }
//                    y.i("MicroMsg.ChattingFooterEventImpl", "onEnterMultiTalk, but > max 9 members!");
//                    Toast.makeText(ae.getContext(), ae.getContext().getString(R.l.multitalk_members_reach_max_limit, new Object[]{Integer.valueOf(9)}), 0).show();
//                    return;
//                }
//                intent.putExtra("chatroomName", getTalkerUserName());
//                intent.putExtra("key_need_gallery", true);
//                intent.putExtra("titile", this.uPh.vtz.getMMResources().getString(R.l.multitalk_select_contact));
//                com.tencent.mm.br.d.b(this.uPh.vtz.getContext(), "multitalk", ".ui.MultiTalkSelectContactUI", intent);
//            }
//        }
//    }
//
//    public final void GP(final int i) {
//        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkCamera[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 18, "", "")), bk.csb(), this.uPh.vtz.getContext());
//        if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 18, "", "")) {
//            y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 18, "", "")), bk.csb(), this.uPh.vtz.getContext());
//            if (!com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 18, "", "")) {
//                return;
//            }
//            if (com.tencent.mm.sdk.a.b.cqk() || com.tencent.mm.platformtools.ae.eTI) {
//                com.tencent.mm.ui.tools.j jVar = new com.tencent.mm.ui.tools.j(this.uPh.vtz.getContext());
//                jVar.phH = new c() {
//                    public final void a(l lVar) {
//                        lVar.e(1, q.this.uPh.vtz.getMMResources().getString(R.l.app_field_mmsight));
//                        lVar.e(5, "拍摄参数设置面板");
//                    }
//                };
//                jVar.phI = new n.d() {
//                    public final void onMMMenuItemSelected(MenuItem menuItem, int i) {
//                        switch (menuItem.getItemId()) {
//                            case 1:
//                                Intent intent = new Intent();
//                                h.nFQ.f(13822, Integer.valueOf(1), Integer.valueOf(1));
//                                com.tencent.mm.pluginsdk.ui.tools.l.a(q.this.uPh.vtz, new Intent(), q.this.getTalkerUserName(), i);
//                                return;
//                            case 5:
//                                com.tencent.mm.br.d.b(q.this.uPh.vtz.getContext(), "mmsight", ".ui.SightSettingsUI", new Intent());
//                                return;
//                            default:
//                                return;
//                        }
//                    }
//                };
//                jVar.bJQ();
//            } else if (!com.tencent.mm.platformtools.ae.eTJ) {
//                h.nFQ.f(13822, Integer.valueOf(1), Integer.valueOf(1));
//                com.tencent.mm.pluginsdk.ui.tools.l.a(this.uPh.vtz, new Intent(), getTalkerUserName(), i);
//            }
//        }
//    }
//
//    public final void cCJ() {
//        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 83, "", "")), bk.csb(), this.uPh.vtz.getContext());
//        if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 83, "", "")) {
//            if (this.maV.cnD()) {
//                this.maV.setBottomPanelVisibility(8);
//            }
//            td tdVar = new td();
//            tdVar.ccJ.bNb = 5;
//            tdVar.ccJ.talker = getTalkerUserName();
//            tdVar.ccJ.context = this.uPh.vtz.getContext();
//            tdVar.ccJ.ccE = 3;
//            com.tencent.mm.sdk.b.a.udP.m(tdVar);
//            h.nFQ.f(11033, Integer.valueOf(4), Integer.valueOf(2), Integer.valueOf(0));
//        }
//    }
//
//    public final void cCK() {
//        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkCamera[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 21, "", "")), bk.csb(), this.uPh.vtz.getContext());
//        if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 21, "", "")) {
//            y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 21, "", "")), bk.csb(), this.uPh.vtz.getContext());
//            if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 21, "", "")) {
//                if (this.maV.cnD()) {
//                    this.maV.setBottomPanelVisibility(8);
//                }
//                td tdVar = new td();
//                tdVar.ccJ.bNb = 5;
//                tdVar.ccJ.talker = getTalkerUserName();
//                tdVar.ccJ.context = this.uPh.vtz.getContext();
//                tdVar.ccJ.ccE = 2;
//                com.tencent.mm.sdk.b.a.udP.m(tdVar);
//            }
//        }
//    }
//
//    public final void bsG() {
//        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 82, "", "")), bk.csb(), this.uPh.vtz.getContext());
//        if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 82, "", "")) {
//            td tdVar = new td();
//            tdVar.ccJ.bNb = 5;
//            tdVar.ccJ.talker = getTalkerUserName();
//            tdVar.ccJ.context = this.uPh.vtz.getContext();
//            tdVar.ccJ.ccE = 4;
//            if (this.maV.cnD()) {
//                this.maV.setBottomPanelVisibility(8);
//            }
//            com.tencent.mm.sdk.b.a.udP.m(tdVar);
//        }
//    }
//
//    public final void bsH() {
//        y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkCamera[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 19, "", "")), bk.csb(), this.uPh.vtz.getContext());
//        if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.CAMERA", 19, "", "")) {
//            y.i("MicroMsg.ChattingFooterEventImpl", "summerper checkPermission checkmicrophone[%b], stack[%s], activity[%s]", Boolean.valueOf(com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 19, "", "")), bk.csb(), this.uPh.vtz.getContext());
//            if (com.tencent.mm.pluginsdk.permission.a.a(this.uPh.vtz.getContext(), "android.permission.RECORD_AUDIO", 19, "", "")) {
//                td tdVar = new td();
//                tdVar.ccJ.bNb = 5;
//                tdVar.ccJ.talker = getTalkerUserName();
//                tdVar.ccJ.context = this.uPh.vtz.getContext();
//                tdVar.ccJ.ccE = 2;
//                if (this.maV.cnD()) {
//                    this.maV.setBottomPanelVisibility(8);
//                }
//                com.tencent.mm.sdk.b.a.udP.m(tdVar);
//            }
//        }
//    }
//
//    public final void cCL() {
//        ((com.tencent.mm.ui.chatting.b.b.ae) this.uPh.ac(com.tencent.mm.ui.chatting.b.b.ae.class)).nn(false);
//        this.maV.setBottomPanelVisibility(8);
//    }
//
//    public final void cCM() {
//        if (!com.tencent.mm.pluginsdk.ui.tools.l.a(this.uPh.vtz, e.dzD, "microMsg." + System.currentTimeMillis() + FileUtils.PIC_POSTFIX_JPEG)) {
//            Toast.makeText(this.uPh.vtz.getContext(), this.uPh.vtz.getMMResources().getString(R.l.selectcameraapp_none), 1).show();
//        }
//    }
//}