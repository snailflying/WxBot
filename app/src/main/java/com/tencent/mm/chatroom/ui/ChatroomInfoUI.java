//package com.tencent.mm.chatroom.ui;
//
//import android.app.ProgressDialog;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.os.Looper;
//import android.os.Message;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//
//import com.tencent.mm.ah.f;
//import com.tencent.mm.chatroom.ui.a.g;
//import com.tencent.mm.chatroom.ui.a.i;
//import com.tencent.mm.chatroom.ui.a.k;
//import com.tencent.mm.chatroom.ui.preference.RoomCardPreference;
//import com.tencent.mm.chatroom.ui.preference.SignaturePreference;
//import com.tencent.mm.h.a.hg;
//import com.tencent.mm.h.a.kf;
//import com.tencent.mm.h.a.ru;
//import com.tencent.mm.h.a.sq;
//import com.tencent.mm.kernel.j;
//import com.tencent.mm.model.bd;
//import com.tencent.mm.model.l;
//import com.tencent.mm.model.m;
//import com.tencent.mm.model.q;
//import com.tencent.mm.model.s;
//import com.tencent.mm.plugin.appbrand.ui.widget.AppBrandLoadIconPreference;
//import com.tencent.mm.plugin.report.kvdata.IMBehavior;
//import com.tencent.mm.plugin.report.kvdata.IMBehaviorChattingOP;
//import com.tencent.mm.pluginsdk.ui.applet.ContactListExpandPreference;
//import com.tencent.mm.pluginsdk.ui.d;
//import com.tencent.mm.pluginsdk.wallet.e;
//import com.tencent.mm.protocal.c.ats;
//import com.tencent.mm.protocal.c.bml;
//import com.tencent.mm.protocal.c.su;
//import com.tencent.mm.sdk.b.c;
//import com.tencent.mm.sdk.e.m.b;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.ai;
//import com.tencent.mm.sdk.platformtools.am;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.x;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.storage.u;
//import com.tencent.mm.ui.base.h;
//import com.tencent.mm.ui.base.p;
//import com.tencent.mm.ui.base.preference.CheckBoxPreference;
//import com.tencent.mm.ui.base.preference.MMPreference;
//import com.tencent.mm.ui.base.preference.NormalIconPreference;
//import com.tencent.mm.ui.base.preference.Preference;
//
//import junit.framework.Assert;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//@j
//public class ChatroomInfoUI extends MMPreference implements f, com.tencent.mm.bf.a, com.tencent.mm.sdk.e.j.a, b {
//    private static boolean dnY = false;
//    private boolean bRz;
//    private String dmT;
//    private CheckBoxPreference dnA;
//    private Preference dnB;
//    private AppBrandLoadIconPreference dnC;
//    private SharedPreferences dnD = null;
//    private boolean dnE;
//    private boolean dnF;
//    private boolean dnG = false;
//    private boolean dnH = false;
//    private int dnI;
//    private boolean dnJ;
//    private boolean dnK = false;
//    private u dnL = null;
//    private int dnM = -1;
//    private boolean dnN = true;
//    private boolean dnO = false;
//    private boolean dnP = false;
//    private boolean dnQ = false;
//    private d dnR = new d(new OnScrollListener() {
//        public final void onScrollStateChanged(AbsListView absListView, int i) {
//        }
//
//        public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
//        }
//    });
//    boolean dnS = false;
//    private c dnT = new c<hg>() {
//        {
//            this.udX = hg.class.getName().hashCode();
//        }
//
//        public final /* synthetic */ boolean a(com.tencent.mm.sdk.b.b bVar) {
//            if (((hg) bVar) instanceof hg) {
//                ChatroomInfoUI.this.xi();
//            }
//            return false;
//        }
//    };
//    int dnU = -1;
//    private p dnV = null;
//    private String dnW = "";
//    private String dnX;
//    private String dnZ = null;
//    private ProgressDialog dnm = null;
//    private com.tencent.mm.ui.base.preference.f dnn;
//    private boolean dno;
//    private ad dnp;
//    private RoomCardPreference dnq;
//    private SignaturePreference dnr;
//    private Preference dns;
//    private NormalIconPreference dnt;
//    private NormalIconPreference dnu;
//    private ContactListExpandPreference dnv;
//    private CheckBoxPreference dnw;
//    private CheckBoxPreference dnx;
//    private CheckBoxPreference dny;
//    private SignaturePreference dnz;
//    private com.tencent.mm.pluginsdk.c.b doa = new com.tencent.mm.pluginsdk.c.b() {
//        public final void a(int i, int i2, String str, com.tencent.mm.sdk.b.b bVar) {
//            if (bVar instanceof kf) {
//                kf kfVar = (kf) bVar;
//                y.i("MicroMsg.ChatroomInfoUI", "onSceneEnd: errType = " + i + " errCode = " + i2 + " errMsg = " + str);
//                if (ChatroomInfoUI.this.dnm != null) {
//                    ChatroomInfoUI.this.dnm.dismiss();
//                }
//                if (i == 0 && i2 == 0) {
//                    if (i == 0 && i2 == 0 && ChatroomInfoUI.this.dnv != null) {
//                        ArrayList E = ChatroomInfoUI.E(kfVar.bTl.bSX);
//                        ContactListExpandPreference m = ChatroomInfoUI.this.dnv;
//                        if (m.sdh != null) {
//                            com.tencent.mm.pluginsdk.ui.applet.j jVar = m.sdh.scu;
//                            jVar.ab(E);
//                            jVar.notifyChanged();
//                        }
//                        if (ChatroomInfoUI.this.dnn != null) {
//                            ChatroomInfoUI.this.dnn.notifyDataSetChanged();
//                        }
//                        ChatroomInfoUI.this.setMMTitle(ChatroomInfoUI.this.getResources().getQuantityString(g.room_lbsroom_member_title, E.size(), new Object[]{Integer.valueOf(E.size())}));
//                    }
//                    ChatroomInfoUI.this.updateTitle();
//                } else if (i2 == -21) {
//                    h.a(ChatroomInfoUI.this, ChatroomInfoUI.this.getString(i.room_lbsroom_auto_quit), ChatroomInfoUI.this.getString(i.app_tip), new OnClickListener() {
//                        public final void onClick(DialogInterface dialogInterface, int i) {
//                            ChatroomInfoUI.this.finish();
//                        }
//                    });
//                } else {
//                    h.b(ChatroomInfoUI.this, ChatroomInfoUI.this.getString(i.room_lbsroom_member_loading_failed), ChatroomInfoUI.this.getString(i.app_tip), true);
//                }
//            }
//        }
//    };
//    private am dob = null;
//    private int doc = 0;
//    private ah handler = new ah(Looper.getMainLooper()) {
//        public final void handleMessage(Message message) {
//            ChatroomInfoUI.a(ChatroomInfoUI.this);
//        }
//    };
//    private boolean isDeleteCancel = false;
//
//    static class a implements OnCancelListener {
//        a() {
//        }
//
//        public final void onCancel(DialogInterface dialogInterface) {
//            ChatroomInfoUI.dnY = true;
//        }
//    }
//
//    static /* synthetic */ void v(ChatroomInfoUI chatroomInfoUI) {
//        com.tencent.mm.plugin.report.service.h.nFQ.f(14553, Integer.valueOf(4), Integer.valueOf(2), chatroomInfoUI.dmT);
//        long j = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).bhO().Hz(chatroomInfoUI.dmT).field_msgSvrId;
//        su suVar = new su();
//        suVar.sQs = new bml().YI(bk.pm(chatroomInfoUI.dmT));
//        suVar.ndp = j;
//        ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fv().b(new com.tencent.mm.plugin.messenger.foundation.a.a.i.a(8, suVar));
//        chatroomInfoUI.isDeleteCancel = false;
//        chatroomInfoUI.getString(i.app_tip);
//        final p b = h.b((Context) chatroomInfoUI, chatroomInfoUI.getString(i.app_waiting), true, new OnCancelListener() {
//            public final void onCancel(DialogInterface dialogInterface) {
//                ChatroomInfoUI.this.isDeleteCancel = true;
//            }
//        });
//        if (bk.bl(!chatroomInfoUI.isDeleteCancel ? e.Xe(chatroomInfoUI.dnp.field_username) : null)) {
//            chatroomInfoUI.bj(true);
//            return;
//        }
//        b.dismiss();
//        Object[] objArr = new Object[]{r0};
//        h.a(chatroomInfoUI, false, chatroomInfoUI.getString(i.wallet_clear_exit_groupchat_note, objArr), null, chatroomInfoUI.getString(i.goto_conversation), chatroomInfoUI.getString(i.room_del_quit), new OnClickListener() {
//            public final void onClick(DialogInterface dialogInterface, int i) {
//                com.tencent.mm.plugin.report.service.h.nFQ.f(14553, Integer.valueOf(4), Integer.valueOf(4), ChatroomInfoUI.this.dmT);
//                ChatroomInfoUI.this.isDeleteCancel = true;
//                if (ChatroomInfoUI.this.dnO) {
//                    ChatroomInfoUI.this.finish();
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.putExtra("Chat_User", ChatroomInfoUI.this.dnp.field_username);
//                intent.addFlags(67108864);
//                com.tencent.mm.br.d.e(ChatroomInfoUI.this, ".ui.chatting.ChattingUI", intent);
//            }
//        }, new OnClickListener() {
//            final /* synthetic */ boolean doj = true;
//
//            public final void onClick(DialogInterface dialogInterface, int i) {
//                com.tencent.mm.plugin.report.service.h.nFQ.f(14553, Integer.valueOf(4), Integer.valueOf(3), ChatroomInfoUI.this.dmT);
//                b.show();
//                ChatroomInfoUI.this.isDeleteCancel = false;
//                ChatroomInfoUI.this.bj(this.doj);
//            }
//        }, -1, a.b.alert_btn_color_warn);
//    }
//
//    static /* synthetic */ void z(ChatroomInfoUI chatroomInfoUI) {
//        chatroomInfoUI.updateTitle();
//        if (chatroomInfoUI.dnL != null) {
//            chatroomInfoUI.dnI = chatroomInfoUI.dnL.MN().size();
//        }
//        if ((!chatroomInfoUI.dnG && chatroomInfoUI.dnI >= com.tencent.mm.pluginsdk.ui.applet.j.scD) || (chatroomInfoUI.dnG && chatroomInfoUI.dnI >= com.tencent.mm.pluginsdk.ui.applet.j.scD - 1)) {
//            chatroomInfoUI.dnn.bJ("see_room_member", false);
//            chatroomInfoUI.dns.setTitle(chatroomInfoUI.getString(i.see_member_selector_btn));
//        }
//    }
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        com.tencent.mm.kernel.g.DO().dJT.a(480, (f) this);
//        com.tencent.mm.pluginsdk.c.b.a(kf.class.getName(), this.doa);
//        com.tencent.mm.vending.b.b cqo = this.dnT.cqo();
//        Assert.assertNotNull(this);
//        keep(cqo);
//        com.tencent.mm.kernel.g.DP().Dz().a(this);
//        ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().c(this);
//        if (com.tencent.mm.bf.g.eEV != null) {
//            com.tencent.mm.bf.g.eEV.a(this);
//        }
//        this.dmT = getIntent().getStringExtra("RoomInfo_Id");
//        this.dnp = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(this.dmT);
//        this.dnN = true;
//        this.bRz = getIntent().getBooleanExtra("Is_Chatroom", true);
//        this.dno = getIntent().getBooleanExtra("Is_Lbsroom", false);
//        this.dnO = getIntent().getBooleanExtra("fromChatting", false);
//        this.dnP = getIntent().getBooleanExtra("isShowSetMuteAnimation", false);
//        this.dnW = getPackageName() + "_preferences";
//        if (this.bRz) {
//            this.dnL = ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().in(this.dmT);
//            this.dnH = this.dnL.aaM(q.Gj());
//        }
//        initView();
//        if (this.bRz) {
//            final AnonymousClass22 anonymousClass22 = new com.tencent.mm.model.am.b.a() {
//                public final void m(final String str, boolean z) {
//                    if (z && ChatroomInfoUI.this.dmT.equals(str)) {
//                        com.tencent.mm.kernel.g.DS().O(new Runnable() {
//                            public final void run() {
//                                ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().m(str, System.currentTimeMillis());
//                            }
//
//                            public final String toString() {
//                                return super.toString() + "|getContactCallBack";
//                            }
//                        });
//                    }
//                }
//            };
//            if (this.dnL == null) {
//                com.tencent.mm.model.am.a.dVy.a(this.dmT, "", anonymousClass22);
//            } else if (System.currentTimeMillis() - this.dnL.field_modifytime >= 86400000) {
//                com.tencent.mm.kernel.g.DS().O(new Runnable() {
//                    public final void run() {
//                        com.tencent.mm.model.am.a.dVy.a(ChatroomInfoUI.this.dnL.field_chatroomname, "", anonymousClass22);
//                    }
//
//                    public final String toString() {
//                        return super.toString() + "|getContactCallBack2";
//                    }
//                });
//            }
//        }
//    }
//
//    private void xi() {
//        y.i("MicroMsg.ChatroomInfoUI", "[doChatroomDetailCgi] :%s", this.dmT);
//        com.tencent.mm.roomsdk.a.c.a eM = com.tencent.mm.roomsdk.a.b.YK(this.dmT).eM(this.dmT);
//        eM.b(new com.tencent.mm.roomsdk.a.b.b() {
//            public final /* synthetic */ void a(int i, int i2, String str, a aVar) {
//                if (ChatroomInfoUI.this.dnL != null) {
//                    ChatroomInfoUI.this.dnI = ChatroomInfoUI.this.dnL.MN().size();
//                }
//                if ((!ChatroomInfoUI.this.xw() && ChatroomInfoUI.this.dnI >= com.tencent.mm.pluginsdk.ui.applet.j.scD) || (ChatroomInfoUI.this.xw() && ChatroomInfoUI.this.dnI >= com.tencent.mm.pluginsdk.ui.applet.j.scD - 1)) {
//                    ChatroomInfoUI.this.dnn.bJ("see_room_member", false);
//                    ChatroomInfoUI.this.dns.setTitle(ChatroomInfoUI.this.getString(i.see_member_selector_btn));
//                }
//                ChatroomInfoUI.this.xs();
//                ChatroomInfoUI.this.xt();
//                ChatroomInfoUI.this.updateTitle();
//            }
//        });
//        eM.cpz();
//    }
//
//    /* JADX WARNING: Removed duplicated region for block: B:11:0x0066  */
//    /* JADX WARNING: Removed duplicated region for block: B:14:0x0070  */
//    /* JADX WARNING: Removed duplicated region for block: B:17:0x0084  */
//    /* JADX WARNING: Removed duplicated region for block: B:33:? A:{SYNTHETIC, RETURN} */
//    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c1  */
//    public void onResume() {
//        /*
//        r8 = this;
//        r7 = 1;
//        r1 = 0;
//        super.onResume();
//        r0 = r8.dnn;
//        r0.notifyDataSetChanged();
//        r0 = r8.dnL;
//        if (r0 == 0) goto L_0x00ce;
//    L_0x000e:
//        r0 = r8.dnL;
//        r2 = r0.ctT();
//        if (r2 == 0) goto L_0x00ce;
//    L_0x0016:
//        r0 = r8.dnL;
//        r0 = r0.field_chatroomname;
//        r3 = r2.dtJ;
//        r0 = com.tencent.mm.model.m.G(r0, r3);
//        r3 = "MicroMsg.ChatroomInfoUI";
//        r4 = "roomId:%s newVer:%s localVer:%s owner:%s";
//        r5 = 4;
//        r5 = new java.lang.Object[r5];
//        r6 = r8.dnL;
//        r6 = r6.field_chatroomname;
//        r5[r1] = r6;
//        r2 = r2.dtJ;
//        r2 = java.lang.Integer.valueOf(r2);
//        r5[r7] = r2;
//        r2 = 2;
//        r6 = r8.dnL;
//        r6 = r6.field_chatroomVersion;
//        r6 = java.lang.Integer.valueOf(r6);
//        r5[r2] = r6;
//        r2 = 3;
//        r6 = r8.dnL;
//        r6 = r6.field_roomowner;
//        r5[r2] = r6;
//        com.tencent.mm.sdk.platformtools.y.i(r3, r4, r5);
//    L_0x004c:
//        r2 = r8.dmT;
//        r2 = com.tencent.mm.model.m.gF(r2);
//        if (r2 != 0) goto L_0x0056;
//    L_0x0054:
//        if (r0 == 0) goto L_0x0059;
//    L_0x0056:
//        r8.xi();
//    L_0x0059:
//        r8.xs();
//        r8.updateTitle();
//        r8.xr();
//        r0 = r8.bRz;
//        if (r0 == 0) goto L_0x006c;
//    L_0x0066:
//        r8.xt();
//        r8.xq();
//    L_0x006c:
//        r0 = r8.dnN;
//        if (r0 == 0) goto L_0x0075;
//    L_0x0070:
//        r8.xp();
//        r8.dnN = r1;
//    L_0x0075:
//        r0 = r8.getIntent();
//        r2 = "need_matte_high_light_item";
//        r0 = r0.getStringExtra(r2);
//        r2 = r8.dnS;
//        if (r2 != 0) goto L_0x008f;
//    L_0x0084:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r0);
//        if (r2 != 0) goto L_0x008d;
//    L_0x008a:
//        r8.eO(r0);
//    L_0x008d:
//        r8.dnS = r7;
//    L_0x008f:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r0);
//        if (r2 != 0) goto L_0x00aa;
//    L_0x0095:
//        r2 = "room_notify_new_notice";
//        r0 = r0.equals(r2);
//        if (r0 == 0) goto L_0x00aa;
//    L_0x009e:
//        r0 = r8.dnS;
//        if (r0 != 0) goto L_0x00aa;
//    L_0x00a2:
//        r0 = "room_card";
//        r8.eO(r0);
//        r8.dnS = r7;
//    L_0x00aa:
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r2 = "showSetMuteAnimation isShowSetMuteAnimation[%b]";
//        r3 = new java.lang.Object[r7];
//        r4 = r8.dnP;
//        r4 = java.lang.Boolean.valueOf(r4);
//        r3[r1] = r4;
//        com.tencent.mm.sdk.platformtools.y.d(r0, r2, r3);
//        r0 = r8.dnP;
//        if (r0 == 0) goto L_0x00cd;
//    L_0x00c1:
//        r8.dnP = r1;
//        r0 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$38;
//        r0.<init>();
//        r2 = 50;
//        com.tencent.mm.sdk.platformtools.ai.l(r0, r2);
//    L_0x00cd:
//        return;
//    L_0x00ce:
//        r0 = r1;
//        goto L_0x004c;
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.chatroom.ui.ChatroomInfoUI.onResume():void");
//    }
//
//    public void onConfigurationChanged(Configuration configuration) {
//        super.onConfigurationChanged(configuration);
//        this.dnv.notifyChanged();
//    }
//
//    private void eO(String str) {
//        final int adf = this.dnn.adf(str);
//        this.lwE.smoothScrollToPosition(adf);
//        new ah().postDelayed(new Runnable() {
//            public final void run() {
//                View a = ((com.tencent.mm.ui.base.preference.a) ChatroomInfoUI.this.dnn).a(adf, ChatroomInfoUI.this.lwE);
//                if (a != null) {
//                    com.tencent.mm.ui.g.a.a(ChatroomInfoUI.this.mController.uMN, a);
//                }
//            }
//        }, 10);
//    }
//
//    public void onPause() {
//        super.onPause();
//        if (this.dnK && this.bRz && this.dnL != null) {
//            m.a(this.dmT, this.dnL, this.dnE);
//        }
//    }
//
//    public void onDestroy() {
//        if (com.tencent.mm.bf.g.eEV != null) {
//            com.tencent.mm.bf.g.eEV.a(this);
//        }
//        com.tencent.mm.kernel.g.DP().Dz().b(this);
//        com.tencent.mm.ui.g.a.dismiss();
//        com.tencent.mm.kernel.g.DO().dJT.b(480, (f) this);
//        com.tencent.mm.sdk.b.a.udP.d(this.dnT);
//        com.tencent.mm.pluginsdk.c.b.b(kf.class.getName(), this.doa);
//        if (com.tencent.mm.kernel.g.DK()) {
//            ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().b(this);
//            ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().d(this);
//        }
//        if (com.tencent.mm.bf.g.eEV != null) {
//            com.tencent.mm.bf.g.eEV.b(this);
//        }
//        if (this.dnC != null) {
//            AppBrandLoadIconPreference.onDestroy();
//        }
//        if (this.dob != null) {
//            this.dob.stopTimer();
//        }
//        super.onDestroy();
//    }
//
//    protected void onActivityResult(int i, int i2, Intent intent) {
//        super.onActivityResult(i, i2, intent);
//        String stringExtra;
//        switch (i) {
//            case 1:
//                if (intent != null) {
//                    final String stringExtra2 = intent.getStringExtra("Select_Contact");
//                    u in = ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().in(this.dmT);
//                    if (in == null) {
//                        return;
//                    }
//                    if (in.ctQ() != 2 || xw()) {
//                        c(stringExtra2, null, i.adding_room_mem);
//                        return;
//                    }
//                    com.tencent.mm.ui.widget.a.e.a aeG = new com.tencent.mm.ui.widget.a.e.a(this).aeG(getString(i.request_to_owner_tip));
//                    aeG.wnv = getString(i.app_send);
//                    aeG = aeG.nY(true).l(Boolean.valueOf(true));
//                    aeG.wnw = getString(i.app_cancel);
//                    aeG.aeH(getString(i.reason_invite_hint)).c(new com.tencent.mm.ui.widget.a.e.d() {
//                        public final void b(boolean z, String str) {
//                            if (z) {
//                                ChatroomInfoUI.this.c(stringExtra2, str, i.invitting_room_mem);
//                            }
//                        }
//                    }).show();
//                    return;
//                }
//                return;
//            case 2:
//                if (i2 == -1) {
//                    finish();
//                    return;
//                }
//                return;
//            case 4:
//                if (i2 == -1) {
//                    stringExtra = intent.getStringExtra("room_name");
//                    if (!bk.bl(stringExtra)) {
//                        com.tencent.mm.sdk.b.a.udP.m(new sq());
//                        this.dnp.dk(stringExtra);
//                        ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().U(this.dnp);
//                        xt();
//                        return;
//                    }
//                    return;
//                }
//                return;
//            case 5:
//                if (i2 == 0) {
//                    this.dnU = -1;
//                    return;
//                }
//                return;
//            case 6:
//                if (i2 == -1) {
//                    xs();
//                    return;
//                }
//                return;
//            case 7:
//                if (intent != null) {
//                    stringExtra = intent.getStringExtra("Select_Contact");
//                    if (stringExtra != null && !stringExtra.equals("")) {
////                        final com.tencent.mm.roomsdk.a.c.a a = com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(this.dmT, G, str2);
//                        final com.tencent.mm.roomsdk.a.c.a a = com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(this.dmT, bk.G(stringExtra.split(",")), 0);
////                        a.d(new com.tencent.mm.roomsdk.a.b.c() {
////                            public final /* synthetic */ void a(int i, int i2, String str, a aVar) {
////                                com.tencent.mm.roomsdk.a.b.c cVar = (com.tencent.mm.roomsdk.a.b.c) aVar;
////                                com.tencent.mm.i.a eI = com.tencent.mm.i.a.eI(str);
////                                if (eI != null) {
////                                    eI.a(ChatroomInfoUI.this, null, null);
////                                } else if (i == 0 && i2 == 0) {
////                                    if (i == 0 && i2 == 0) {
//                                        ChatroomInfoUI.a(ChatroomInfoUI.this, i2, cVar, str);
////                                        ChatroomInfoUI.this.xv();
////                                    }
////                                    ChatroomInfoUI.z(ChatroomInfoUI.this);
////                                } else {
////                                    ChatroomInfoUI.a(ChatroomInfoUI.this, i2, cVar, str);
////                                    ChatroomInfoUI.this.xv();
////                                }
////                            }
////                        });
//                        a.b(new com.tencent.mm.roomsdk.a.b.b() {
//                            public final /* synthetic */ void a(int i, int i2, String str, a aVar) {
//                                ChatroomInfoUI.this.xv();
//                                ChatroomInfoUI.z(ChatroomInfoUI.this);
//                            }
//                        });
//                        a.c(new com.tencent.mm.roomsdk.a.b.b() {
//                            public final /* synthetic */ void a(int i, int i2, String str, a aVar) {
//                                com.tencent.mm.i.a eI = com.tencent.mm.i.a.eI(str);
//                                if (eI != null) {
//                                    eI.a(ChatroomInfoUI.this, null, null);
//                                    return;
//                                }
//                                if (i2 == -66) {
//                                    h.b(ChatroomInfoUI.this, ChatroomInfoUI.this.getString(i.del_room_mem_err), ChatroomInfoUI.this.getString(i.app_tip), true);
//                                } else {
//                                    h.b(ChatroomInfoUI.this, bk.aM(str, ChatroomInfoUI.this.getString(i.del_room_mem_err_2)), ChatroomInfoUI.this.getString(i.app_tip), true);
//                                }
//                                ChatroomInfoUI.this.xv();
//                                ChatroomInfoUI.z(ChatroomInfoUI.this);
//                                y.i("MicroMsg.ActionCallbackFunc", "[delChatroomMember] onResult errType:%s errCode:%s", Integer.valueOf(i), Integer.valueOf(i2));
//                            }
//                        });
//                        getString(i.app_tip);
//                        a.a(this, getString(i.room_del_member), true, new OnCancelListener() {
//                            public final void onCancel(DialogInterface dialogInterface) {
//                                a.cancel();
//                            }
//                        });
//                        return;
//                    }
//                    return;
//                }
//                return;
//            default:
//                return;
//        }
//    }
//
//    public final int xj() {
//        return k.roominfo_pref;
//    }
//
//    /* JADX WARNING: Removed duplicated region for block: B:55:0x0282  */
//    /* JADX WARNING: Removed duplicated region for block: B:90:0x040c  */
//    /* JADX WARNING: Removed duplicated region for block: B:58:0x02b4  */
//    protected final void initView() {
//        /*
//        r6 = this;
//        r5 = 2;
//        r1 = 1;
//        r2 = 0;
//        r0 = r6.vdd;
//        r6.dnn = r0;
//        r0 = r6.bRz;
//        if (r0 == 0) goto L_0x043c;
//    L_0x000b:
//        r0 = r6.dnI;
//        if (r0 != 0) goto L_0x0335;
//    L_0x000f:
//        r0 = com.tencent.mm.chatroom.ui.a.i.roominfo_name;
//        r0 = r6.getString(r0);
//        r6.setMMTitle(r0);
//    L_0x0018:
//        r0 = 0;
//        r3 = r6.dnL;
//        if (r3 == 0) goto L_0x002d;
//    L_0x001d:
//        r0 = r6.dnL;
//        r0 = r0.field_roomowner;
//        r3 = r6.dnL;
//        r3 = r3.MN();
//        r3 = r3.size();
//        r6.dnI = r3;
//    L_0x002d:
//        r3 = com.tencent.mm.sdk.platformtools.bk.bl(r0);
//        if (r3 == 0) goto L_0x0362;
//    L_0x0033:
//        r6.dnF = r2;
//        r6.dnG = r2;
//        r6.dnH = r2;
//    L_0x0039:
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r3 = "initBaseChatRoomView()";
//        com.tencent.mm.sdk.platformtools.y.d(r0, r3);
//        r0 = r6.dnn;
//        r3 = "roominfo_contact_anchor";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.pluginsdk.ui.applet.ContactListExpandPreference) r0;
//        r6.dnv = r0;
//        r0 = r6.dnv;
//        r3 = r6.dnn;
//        r4 = r6.dnv;
//        r4 = r4.mKey;
//        r0.a(r3, r4);
//        r0 = r6.dnn;
//        r3 = "del_selector_btn";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.ui.base.preference.NormalIconPreference) r0;
//        r6.dnu = r0;
//        r0 = r6.dnn;
//        r3 = "add_selector_btn";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.ui.base.preference.NormalIconPreference) r0;
//        r6.dnt = r0;
//        r0 = r6.dnn;
//        r3 = "room_name";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.chatroom.ui.preference.SignaturePreference) r0;
//        r6.dnr = r0;
//        r0 = r6.dnn;
//        r3 = "room_card";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.chatroom.ui.preference.RoomCardPreference) r0;
//        r6.dnq = r0;
//        r0 = r6.dnn;
//        r3 = "see_room_member";
//        r0 = r0.add(r3);
//        r6.dns = r0;
//        r0 = r6.dnn;
//        r3 = "room_notify_new_msg";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.ui.base.preference.CheckBoxPreference) r0;
//        r6.dnw = r0;
//        r0 = r6.dnn;
//        r3 = "room_save_to_contact";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.ui.base.preference.CheckBoxPreference) r0;
//        r6.dnx = r0;
//        r0 = r6.dnn;
//        r3 = "room_placed_to_the_top";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.ui.base.preference.CheckBoxPreference) r0;
//        r6.dny = r0;
//        r0 = r6.dnn;
//        r3 = "room_nickname";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.chatroom.ui.preference.SignaturePreference) r0;
//        r6.dnz = r0;
//        r0 = r6.dnn;
//        r3 = "manage_room";
//        r0 = r0.add(r3);
//        r6.dnB = r0;
//        r0 = r6.dnv;
//        r0 = r0.lS(r1);
//        r0 = r0.lT(r1);
//        r0.cmQ();
//        r0 = com.tencent.mm.plugin.chatroom.a.c.class;
//        r0 = com.tencent.mm.kernel.g.r(r0);
//        r0 = (com.tencent.mm.plugin.chatroom.a.c) r0;
//        r3 = r6.dmT;
//        r0 = r0.zl(r3);
//        r3 = r6.xw();
//        if (r3 != 0) goto L_0x0373;
//    L_0x00f9:
//        r3 = r6.dnn;
//        r4 = "manage_room";
//        r3.bJ(r4, r1);
//        r3 = r6.dnn;
//        r4 = "room_manager_view";
//        if (r0 != 0) goto L_0x0370;
//    L_0x0108:
//        r0 = r1;
//    L_0x0109:
//        r3.bJ(r4, r0);
//    L_0x010c:
//        r0 = r6.dmT;
//        r0 = com.tencent.mm.model.s.hb(r0);
//        if (r0 == 0) goto L_0x011c;
//    L_0x0114:
//        r0 = r6.dnn;
//        r3 = "manage_room";
//        r0.bJ(r3, r1);
//    L_0x011c:
//        r0 = r6.dnL;
//        if (r0 == 0) goto L_0x013b;
//    L_0x0120:
//        r0 = r6.dnv;
//        r3 = r6.dnL;
//        r3 = r3.field_roomowner;
//        r0.WI(r3);
//        r0 = r6.dnL;
//        r0 = r0.field_roomowner;
//        r0 = com.tencent.mm.sdk.platformtools.bk.bl(r0);
//        if (r0 != 0) goto L_0x013b;
//    L_0x0133:
//        r0 = r6.dnv;
//        r0 = r0.sdh;
//        r0 = r0.scu;
//        r0.scW = r1;
//    L_0x013b:
//        r0 = r6.dnv;
//        r0.cmO();
//        r0 = r6.dnv;
//        r0.cmR();
//        r0 = r6.dnn;
//        r3 = "add_selector_btn";
//        r0.bJ(r3, r1);
//        r0 = r6.dnn;
//        r3 = "del_selector_btn";
//        r0.bJ(r3, r1);
//        r0 = r6.dnn;
//        r3 = "room_name";
//        r0.bJ(r3, r2);
//        r0 = r6.dnn;
//        r3 = "room_qr_code";
//        r0.bJ(r3, r2);
//        r0 = r6.dnn;
//        r3 = "chatroom_info_chexboxes";
//        r0.bJ(r3, r1);
//        r0 = r6.dnn;
//        r3 = "room_card";
//        r0.bJ(r3, r2);
//        r0 = r6.dnn;
//        r3 = "room_upgrade_entry";
//        r0.bJ(r3, r1);
//        r0 = r6.xw();
//        if (r0 != 0) goto L_0x0189;
//    L_0x0183:
//        r0 = r6.dnI;
//        r3 = com.tencent.mm.pluginsdk.ui.applet.j.scD;
//        if (r0 >= r3) goto L_0x0197;
//    L_0x0189:
//        r0 = r6.xw();
//        if (r0 == 0) goto L_0x0397;
//    L_0x018f:
//        r0 = r6.dnI;
//        r3 = com.tencent.mm.pluginsdk.ui.applet.j.scD;
//        r3 = r3 + -1;
//        if (r0 < r3) goto L_0x0397;
//    L_0x0197:
//        r0 = r6.dnn;
//        r3 = "see_room_member";
//        r0.bJ(r3, r2);
//        r0 = r6.dns;
//        r3 = com.tencent.mm.chatroom.ui.a.i.see_member_selector_btn;
//        r3 = r6.getString(r3);
//        r0.setTitle(r3);
//    L_0x01aa:
//        r0 = r6.dmT;
//        r0 = com.tencent.mm.storage.ad.aaV(r0);
//        if (r0 != 0) goto L_0x03a1;
//    L_0x01b2:
//        r0 = r6.dnn;
//        r3 = "room_openim_about";
//        r0.bJ(r3, r1);
//    L_0x01ba:
//        r0 = r6.dnn;
//        r1 = "chat_room_app_brand";
//        r0 = r0.add(r1);
//        r0 = (com.tencent.mm.plugin.appbrand.ui.widget.AppBrandLoadIconPreference) r0;
//        r6.dnC = r0;
//        r0 = r6.dnC;
//        r1 = r6.dmT;
//        r0.drJ = r1;
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r1 = "updatePlaceTop()";
//        com.tencent.mm.sdk.platformtools.y.d(r0, r1);
//        r0 = r6.dnD;
//        if (r0 != 0) goto L_0x01f8;
//    L_0x01da:
//        r0 = new java.lang.StringBuilder;
//        r0.<init>();
//        r1 = r6.getPackageName();
//        r0 = r0.append(r1);
//        r1 = "_preferences";
//        r0 = r0.append(r1);
//        r0 = r0.toString();
//        r0 = r6.getSharedPreferences(r0, r2);
//        r6.dnD = r0;
//    L_0x01f8:
//        r0 = r6.dny;
//        if (r0 == 0) goto L_0x0224;
//    L_0x01fc:
//        r0 = r6.dnp;
//        if (r0 == 0) goto L_0x03dd;
//    L_0x0200:
//        r0 = r6.dnD;
//        r1 = r0.edit();
//        r3 = "room_placed_to_the_top";
//        r0 = com.tencent.mm.plugin.messenger.foundation.a.j.class;
//        r0 = com.tencent.mm.kernel.g.r(r0);
//        r0 = (com.tencent.mm.plugin.messenger.foundation.a.j) r0;
//        r0 = r0.FB();
//        r4 = r6.dnp;
//        r4 = r4.field_username;
//        r0 = r0.abD(r4);
//        r0 = r1.putBoolean(r3, r0);
//        r0.commit();
//    L_0x0224:
//        r0 = r6.dnn;
//        r0.notifyDataSetChanged();
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r1 = "updateSaveToContact()";
//        com.tencent.mm.sdk.platformtools.y.d(r0, r1);
//        r0 = r6.dnD;
//        if (r0 != 0) goto L_0x0254;
//    L_0x0236:
//        r0 = new java.lang.StringBuilder;
//        r0.<init>();
//        r1 = r6.getPackageName();
//        r0 = r0.append(r1);
//        r1 = "_preferences";
//        r0 = r0.append(r1);
//        r0 = r0.toString();
//        r0 = r6.getSharedPreferences(r0, r2);
//        r6.dnD = r0;
//    L_0x0254:
//        r0 = r6.dnx;
//        if (r0 == 0) goto L_0x0405;
//    L_0x0258:
//        r0 = com.tencent.mm.plugin.messenger.foundation.a.j.class;
//        r0 = com.tencent.mm.kernel.g.r(r0);
//        r0 = (com.tencent.mm.plugin.messenger.foundation.a.j) r0;
//        r0 = r0.Fw();
//        r1 = r6.dmT;
//        r0 = r0.abl(r1);
//        if (r0 != 0) goto L_0x03ef;
//    L_0x026c:
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r1 = "contact == null !!!";
//        com.tencent.mm.sdk.platformtools.y.e(r0, r1);
//    L_0x0275:
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r1 = "updateDisplayNickname()";
//        com.tencent.mm.sdk.platformtools.y.d(r0, r1);
//        r0 = r6.dnD;
//        if (r0 != 0) goto L_0x02a0;
//    L_0x0282:
//        r0 = new java.lang.StringBuilder;
//        r0.<init>();
//        r1 = r6.getPackageName();
//        r0 = r0.append(r1);
//        r1 = "_preferences";
//        r0 = r0.append(r1);
//        r0 = r0.toString();
//        r0 = r6.getSharedPreferences(r0, r2);
//        r6.dnD = r0;
//    L_0x02a0:
//        r0 = com.tencent.mm.plugin.chatroom.a.c.class;
//        r0 = com.tencent.mm.kernel.g.r(r0);
//        r0 = (com.tencent.mm.plugin.chatroom.a.c) r0;
//        r0 = r0.FF();
//        r1 = r6.dmT;
//        r0 = r0.in(r1);
//        if (r0 != 0) goto L_0x040c;
//    L_0x02b4:
//        r0 = "MicroMsg.ChatroomInfoUI";
//        r1 = "members == null !!!";
//        com.tencent.mm.sdk.platformtools.y.e(r0, r1);
//    L_0x02bd:
//        r0 = com.tencent.mm.plugin.messenger.foundation.a.j.class;
//        r0 = com.tencent.mm.kernel.g.r(r0);
//        r0 = (com.tencent.mm.plugin.messenger.foundation.a.j) r0;
//        r0 = r0.bhO();
//        r1 = r6.dmT;
//        r0 = r0.HS(r1);
//        r6.dnM = r0;
//        r0 = com.tencent.mm.plugin.messenger.foundation.a.j.class;
//        r0 = com.tencent.mm.kernel.g.r(r0);
//        r0 = (com.tencent.mm.plugin.messenger.foundation.a.j) r0;
//        r0 = r0.bhO();
//        r1 = r6.dmT;
//        r0 = r0.HS(r1);
//        r6.dnM = r0;
//    L_0x02e5:
//        r0 = r6.dnv;
//        if (r0 == 0) goto L_0x032c;
//    L_0x02e9:
//        r0 = r6.bRz;
//        if (r0 != 0) goto L_0x02fb;
//    L_0x02ed:
//        r0 = r6.dno;
//        if (r0 == 0) goto L_0x02fb;
//    L_0x02f1:
//        r0 = r6.dnv;
//        r1 = new java.util.ArrayList;
//        r1.<init>();
//        r0.ac(r1);
//    L_0x02fb:
//        r0 = r6.lwE;
//        r1 = r6.dnR;
//        r0.setOnScrollListener(r1);
//        r0 = r6.dnv;
//        r1 = r6.dnR;
//        r0.a(r1);
//        r0 = r6.dnv;
//        r1 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$45;
//        r1.<init>();
//        r0.a(r1);
//        r0 = r6.dnv;
//        r1 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$2;
//        r1.<init>();
//        r2 = r0.sdh;
//        if (r2 == 0) goto L_0x0322;
//    L_0x031e:
//        r0 = r0.sdh;
//        r0.nuB = r1;
//    L_0x0322:
//        r0 = r6.dnv;
//        r1 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$3;
//        r1.<init>();
//        r0.a(r1);
//    L_0x032c:
//        r0 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$4;
//        r0.<init>();
//        r6.setBackBtn(r0);
//        return;
//    L_0x0335:
//        r0 = r6.dnL;
//        if (r0 == 0) goto L_0x0345;
//    L_0x0339:
//        r0 = r6.dnL;
//        r0 = r0.MN();
//        r0 = r0.size();
//        r6.dnI = r0;
//    L_0x0345:
//        r0 = com.tencent.mm.chatroom.ui.a.i.fmt_chatting_title_group;
//        r3 = new java.lang.Object[r5];
//        r4 = com.tencent.mm.chatroom.ui.a.i.roominfo_name;
//        r4 = r6.getString(r4);
//        r3[r2] = r4;
//        r4 = r6.dnI;
//        r4 = java.lang.Integer.valueOf(r4);
//        r3[r1] = r4;
//        r0 = r6.getString(r0, r3);
//        r6.setMMTitle(r0);
//        goto L_0x0018;
//    L_0x0362:
//        r6.dnF = r1;
//        r3 = com.tencent.mm.model.q.Gj();
//        r0 = r0.equals(r3);
//        r6.dnG = r0;
//        goto L_0x0039;
//    L_0x0370:
//        r0 = r2;
//        goto L_0x0109;
//    L_0x0373:
//        r0 = r6.dnn;
//        r3 = "room_manager_view";
//        r0.bJ(r3, r1);
//        r0 = r6.dmT;
//        r0 = com.tencent.mm.model.m.gM(r0);
//        if (r0 <= r5) goto L_0x038d;
//    L_0x0383:
//        r0 = r6.dnn;
//        r3 = "manage_room";
//        r0.bJ(r3, r2);
//        goto L_0x010c;
//    L_0x038d:
//        r0 = r6.dnn;
//        r3 = "manage_room";
//        r0.bJ(r3, r1);
//        goto L_0x010c;
//    L_0x0397:
//        r0 = r6.dnn;
//        r3 = "see_room_member";
//        r0.bJ(r3, r1);
//        goto L_0x01aa;
//    L_0x03a1:
//        r0 = r6.dnn;
//        r1 = "room_openim_about";
//        r0.bJ(r1, r2);
//        r0 = com.tencent.mm.chatroom.ui.a.i.room_openim_about;
//        r0 = r6.getString(r0);
//        r1 = com.tencent.mm.chatroom.ui.a.i.room_openim_about_href;
//        r1 = r6.getString(r1);
//        r3 = r0.lastIndexOf(r1);
//        if (r3 < 0) goto L_0x01ba;
//    L_0x03bb:
//        r1 = r1.length();
//        r1 = r1 + r3;
//        r4 = new android.text.SpannableStringBuilder;
//        r4.<init>(r0);
//        r0 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$5;
//        r0.<init>();
//        r5 = 18;
//        r4.setSpan(r0, r3, r1, r5);
//        r0 = r6.dnn;
//        r1 = "room_openim_about";
//        r0 = r0.add(r1);
//        r0.setTitle(r4);
//        goto L_0x01ba;
//    L_0x03dd:
//        r0 = r6.dnD;
//        r0 = r0.edit();
//        r1 = "room_placed_to_the_top";
//        r0 = r0.putBoolean(r1, r2);
//        r0.commit();
//        goto L_0x0224;
//    L_0x03ef:
//        r1 = r6.dnD;
//        r1 = r1.edit();
//        r3 = "room_save_to_contact";
//        r0 = r0.field_type;
//        r0 = com.tencent.mm.n.a.gR(r0);
//        r0 = r1.putBoolean(r3, r0);
//        r0.commit();
//    L_0x0405:
//        r0 = r6.dnn;
//        r0.notifyDataSetChanged();
//        goto L_0x0275;
//    L_0x040c:
//        r0 = r0.ctS();
//        r6.dnE = r0;
//        r0 = r6.dnn;
//        r1 = "room_msg_show_username";
//        r0 = r0.add(r1);
//        r0 = (com.tencent.mm.ui.base.preference.CheckBoxPreference) r0;
//        r6.dnA = r0;
//        r0 = r6.dnA;
//        if (r0 == 0) goto L_0x0435;
//    L_0x0423:
//        r0 = r6.dnD;
//        r0 = r0.edit();
//        r1 = "room_msg_show_username";
//        r2 = r6.dnE;
//        r0 = r0.putBoolean(r1, r2);
//        r0.commit();
//    L_0x0435:
//        r0 = r6.dnn;
//        r0.notifyDataSetChanged();
//        goto L_0x02bd;
//    L_0x043c:
//        r0 = r6.dno;
//        if (r0 == 0) goto L_0x02e5;
//    L_0x0440:
//        r6.dnG = r2;
//        r6.dnH = r2;
//        r0 = com.tencent.mm.chatroom.ui.a.i.room_lbsroom_member_title_init;
//        r0 = r6.getString(r0);
//        r6.setMMTitle(r0);
//        r0 = r6.dnn;
//        r3 = "roominfo_contact_anchor";
//        r0 = r0.add(r3);
//        r0 = (com.tencent.mm.pluginsdk.ui.applet.ContactListExpandPreference) r0;
//        r6.dnv = r0;
//        r0 = r6.dnv;
//        r3 = r6.dnn;
//        r4 = r6.dnv;
//        r4 = r4.mKey;
//        r0.a(r3, r4);
//        r0 = r6.dnv;
//        r0 = r0.lS(r2);
//        r0.lT(r2);
//        r0 = r6.dnv;
//        r2 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$6;
//        r2.<init>();
//        r0.a(r2);
//        r0 = r6.dnn;
//        r0.removeAll();
//        r0 = r6.dnn;
//        r2 = new com.tencent.mm.ui.base.preference.PreferenceCategory;
//        r2.<init>(r6);
//        r0.a(r2);
//        r0 = r6.dnn;
//        r2 = r6.dnv;
//        r0.a(r2);
//        r0 = r6.dmT;
//        r2 = new com.tencent.mm.h.a.kf;
//        r2.<init>();
//        r3 = r2.bTk;
//        r3.bRo = r0;
//        r0 = com.tencent.mm.sdk.b.a.udP;
//        r0.m(r2);
//        r0 = com.tencent.mm.chatroom.ui.a.i.app_tip;
//        r6.getString(r0);
//        r0 = com.tencent.mm.chatroom.ui.a.i.room_lbsroom_member_loading;
//        r0 = r6.getString(r0);
//        r3 = new com.tencent.mm.chatroom.ui.ChatroomInfoUI$35;
//        r3.<init>(r2);
//        r0 = com.tencent.mm.ui.base.h.b(r6, r0, r1, r3);
//        r6.dnm = r0;
//        r0 = r6.dnn;
//        r0.notifyDataSetChanged();
//        goto L_0x02e5;
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.chatroom.ui.ChatroomInfoUI.initView():void");
//    }
//
//    public final boolean a(com.tencent.mm.ui.base.preference.f fVar, Preference preference) {
//        int i = true;
//        String str = preference.mKey;
//        int i2;
//        Intent intent;
//        com.tencent.mm.h.b.a.m mVar;
//        Intent intent2;
//        boolean aaM;
//        com.tencent.mm.modelstat.b bVar;
//        String str2;
//        boolean z;
//        IMBehavior iMBehavior;
//        String xk;
//        if (str.equals("room_name")) {
//            str = com.tencent.mm.m.g.AA().getValue("ChatRoomOwnerModTopic");
//            if (bk.bl(str)) {
//                i2 = 0;
//            } else {
//                i2 = bk.ZR(str);
//            }
//            if (bk.bl(this.dnL.field_roomowner) || i2 <= 0 || xw() || i2 >= this.dnI) {
//                str = "";
//                if (xu()) {
//                    str = this.dnp.Bq();
//                }
//                intent = new Intent();
//                intent.setClass(this, ModRemarkRoomNameUI.class);
//                intent.putExtra("room_name", str);
//                intent.putExtra("RoomInfo_Id", this.dmT);
//                startActivityForResult(intent, 4);
//                com.tencent.mm.plugin.report.service.h.nFQ.a(219, 3, 1, true);
//            } else {
//                h.a((Context) this, getString(i.room_too_many_member_to_mod_topic, new Object[]{xn()}), null, getString(i.app_i_known), new OnClickListener() {
//                    public final void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//            }
//        } else if (str.equals("room_upgrade_entry")) {
//            ba(this);
//        } else if (str.equals("room_qr_code")) {
//            if (s.hb(this.dmT)) {
//                mVar = new com.tencent.mm.h.b.a.m();
//                mVar.ciU = this.dmT;
//                mVar.ciV = 6;
//                mVar.QX();
//            }
//            com.tencent.mm.plugin.report.service.h.nFQ.a(219, 5, 1, true);
//            intent2 = new Intent();
//            intent2.putExtra("from_userName", this.dmT);
//            com.tencent.mm.br.d.b(this, "setting", ".ui.setting.SelfQRCodeUI", intent2);
//        } else if (str.equals("room_card")) {
//            aaM = this.dnL.aaM(q.Gj());
//            if (!bk.bl(m.gO(this.dmT)) || xw()) {
//                intent = new Intent();
//                intent.setClass(this, RoomCardUI.class);
//                intent.putExtra("RoomInfo_Id", this.dmT);
//                intent.putExtra("room_name", this.dnq.dtD.toString());
//                this.dnI = m.gK(this.dmT).size();
//                intent.putExtra("room_member_count", this.dnI);
//                intent.putExtra("room_owner_name", xn());
//                intent.putExtra("room_notice", m.gO(this.dmT));
//                intent.putExtra("room_notice_publish_time", m.gQ(this.dmT));
//                intent.putExtra("room_notice_editor", m.gP(this.dmT));
//                intent.putExtra("Is_RoomOwner", this.dnG);
//                intent.putExtra("Is_RoomManager", aaM);
//                startActivityForResult(intent, 6);
//            } else {
//                h.a((Context) this, getString(i.room_card_only_can_edit_notice_by_owner), null, getString(i.room_card_only_can_edit_notice_by_owner_knowned), null, null, null);
//            }
//        } else if (str.equals("room_notify_new_msg")) {
//            this.dnJ = !this.dnJ;
//            if (this.bRz) {
//                int i3 = this.dnJ ? 0 : 1;
//                com.tencent.mm.roomsdk.a.b.YK(this.dmT).z(this.dmT, i3).cpz();
//                this.dnp = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(this.dmT);
//                this.dnp.fq(i3);
//                ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().a(this.dmT, this.dnp);
//                if (this.dnQ) {
//                    com.tencent.mm.plugin.report.service.h.nFQ.h(869, this.dnJ ? 14 : 15, 1);
//                }
//            }
//            bVar = com.tencent.mm.modelstat.b.eBD;
//            str2 = this.dmT;
//            z = this.dnJ;
//            if (bVar.Rm() && bVar.cd(str2)) {
//                iMBehavior = new IMBehavior();
//                iMBehavior.opType = 1;
//                iMBehavior.chattingOp = new IMBehaviorChattingOP();
//                IMBehaviorChattingOP iMBehaviorChattingOP = iMBehavior.chattingOp;
//                if (!z) {
//                    i = 2;
//                }
//                iMBehaviorChattingOP.changeNotifyStatus = i;
//                synchronized (bVar.lock) {
//                    bVar.eBC.oplist_.add(iMBehavior);
//                }
//            }
//            xr();
//        } else if (str.equals("room_save_to_contact")) {
//            if (this.dnD == null) {
//                this.dnD = getSharedPreferences(getPackageName() + "_preferences", 0);
//            }
//            ad abl = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(this.dmT);
//            if (abl != null) {
//                z = com.tencent.mm.n.a.gR(abl.field_type);
//                this.dnD.edit().putBoolean("room_save_to_contact", !z).commit();
//                if (z) {
//                    abl.AI();
//                    com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(abl, false);
//                    h.bC(this, getString(i.room_remove_from_group_card_ok));
//                    com.tencent.mm.modelstat.b.eBD.O(this.dmT, false);
//                } else {
//                    com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(abl, true);
//                    h.bC(this, getString(i.room_save_to_group_card_ok));
//                    com.tencent.mm.modelstat.b.eBD.O(this.dmT, true);
//                }
//                this.dnn.notifyDataSetChanged();
//            }
//        } else if (str.equals("room_placed_to_the_top")) {
//            SharedPreferences sharedPreferences = getSharedPreferences(this.dnW, 0);
//            if (this.dnp != null) {
//                if (((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).FB().abD(this.dnp.field_username)) {
//                    s.u(this.dnp.field_username, true);
//                    com.tencent.mm.modelstat.b.eBD.c(false, this.dmT, false);
//                } else {
//                    s.t(this.dnp.field_username, true);
//                    com.tencent.mm.modelstat.b.eBD.c(false, this.dmT, true);
//                }
//                sharedPreferences.edit().putBoolean("room_placed_to_the_top", ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).FB().abD(this.dnp.field_username)).commit();
//            }
//        } else if (str.equals("room_nickname")) {
//            xk = xk();
//            if (bk.bl(xk)) {
//                xk = q.Gl();
//            }
//            h.a(this.mController.uMN, getString(i.room_my_displayname), xk, getString(i.room_edit_my_nick_tips), 32, new h.b() {
//                public final boolean m(CharSequence charSequence) {
//                    String charSequence2 = charSequence == null ? "" : charSequence.toString();
//                    String Ak = com.tencent.mm.m.b.Ak();
//                    if (bk.bl(Ak) || !charSequence2.matches(".*[" + Ak + "].*")) {
//                        if (!(charSequence2 == null || charSequence2.equals(xk))) {
//                            ChatroomInfoUI.a(ChatroomInfoUI.this, charSequence2);
//                        }
//                        return true;
//                    }
//                    h.bC(ChatroomInfoUI.this.mController.uMN, ChatroomInfoUI.this.getString(i.invalid_input_character_toast, new Object[]{Ak}));
//                    return false;
//                }
//            });
//        } else if (str.equals("room_msg_show_username")) {
//            getSharedPreferences(this.dnW, 0).edit().putBoolean("room_msg_show_username", !this.dnE).commit();
//            this.dnE = !this.dnE;
//            this.dnK = true;
//        } else if (str.equals("room_set_chatting_background")) {
//            if (s.hb(this.dmT)) {
//                mVar = new com.tencent.mm.h.b.a.m();
//                mVar.ciU = this.dmT;
//                mVar.ciV = 9;
//                mVar.QX();
//            }
//            intent2 = new Intent();
//            intent2.putExtra("isApplyToAll", false);
//            intent2.putExtra("username", this.dnp.field_username);
//            com.tencent.mm.br.d.b((Context) this, "setting", ".ui.setting.SettingsChattingBackgroundUI", intent2, 2);
//        } else if (str.equals("room_search_chatting_content")) {
//            if (s.hb(this.dmT)) {
//                mVar = new com.tencent.mm.h.b.a.m();
//                mVar.ciU = this.dmT;
//                mVar.ciV = 8;
//                mVar.QX();
//            }
//            intent2 = new Intent();
//            intent2.putExtra("detail_username", this.dmT);
//            com.tencent.mm.plugin.fts.a.d.b(this, ".ui.FTSChattingConvUI", intent2);
//            if (this.dnL == null || this.dnL.MN() == null) {
//                i2 = 0;
//            } else {
//                i2 = this.dnL.MN().size();
//            }
//            com.tencent.mm.plugin.report.service.h.nFQ.f(14569, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(i2), Integer.valueOf(1));
//        } else if (str.equals("room_clear_chatting_history")) {
//            if (s.hb(this.dmT)) {
//                mVar = new com.tencent.mm.h.b.a.m();
//                mVar.ciU = this.dmT;
//                mVar.ciV = 11;
//                mVar.QX();
//            }
//            h.a(this.mController.uMN, getString(i.fmt_delcontactmsg_confirm_group), "", getString(i.app_clear), getString(i.app_cancel), new OnClickListener() {
//                public final void onClick(DialogInterface dialogInterface, int i) {
//                    String str;
//                    com.tencent.mm.plugin.report.service.h.nFQ.f(14553, Integer.valueOf(3), Integer.valueOf(2), ChatroomInfoUI.this.dmT);
//                    ChatroomInfoUI.dnY = false;
//                    Context context = ChatroomInfoUI.this;
//                    ChatroomInfoUI.this.getString(i.app_tip);
//                    final ProgressDialog b = h.b(context, ChatroomInfoUI.this.getString(i.app_waiting), true, new a());
//                    if (ChatroomInfoUI.dnY) {
//                        str = null;
//                    } else {
//                        str = e.Xe(ChatroomInfoUI.this.dnp.field_username);
//                    }
//                    if (bk.bl(str)) {
//                        bd.a(ChatroomInfoUI.this.dnp.field_username, new com.tencent.mm.model.bd.a(b) {
//                            public final boolean xz() {
//                                return ChatroomInfoUI.dnY;
//                            }
//
//                            public final void xA() {
//                                if (r3 != null) {
//                                    r3.dismiss();
//                                }
//                            }
//                        });
//                        return;
//                    }
//                    b.dismiss();
//                    h.a(ChatroomInfoUI.this, false, ChatroomInfoUI.this.getString(i.wallet_clear_chatting_history_note, new Object[]{str}), null, ChatroomInfoUI.this.getString(i.goto_conversation), ChatroomInfoUI.this.getString(i.clear_chat_history), new OnClickListener() {
//                        public final void onClick(DialogInterface dialogInterface, int i) {
//                            com.tencent.mm.plugin.report.service.h.nFQ.f(14553, Integer.valueOf(3), Integer.valueOf(4), ChatroomInfoUI.this.dmT);
//                            ChatroomInfoUI.this.isDeleteCancel = true;
//                            if (ChatroomInfoUI.this.dnO) {
//                                ChatroomInfoUI.this.finish();
//                                return;
//                            }
//                            Intent intent = new Intent();
//                            intent.putExtra("Chat_User", ChatroomInfoUI.this.dnp.field_username);
//                            intent.addFlags(67108864);
//                            com.tencent.mm.br.d.e(ChatroomInfoUI.this, ".ui.chatting.ChattingUI", intent);
//                        }
//                    }, new OnClickListener() {
//                        public final void onClick(DialogInterface dialogInterface, int i) {
//                            com.tencent.mm.plugin.report.service.h.nFQ.f(14553, Integer.valueOf(3), Integer.valueOf(3), ChatroomInfoUI.this.dmT);
//                            b.show();
//                            ChatroomInfoUI.this.isDeleteCancel = false;
//                            bd.a(ChatroomInfoUI.this.dnp.field_username, /* anonymous class already generated */);
//                        }
//                    }, -1, a.b.alert_btn_color_warn);
//                }
//            }, null, a.b.alert_btn_color_warn);
//        } else if (str.equals("room_report_it")) {
//            if (s.hb(this.dmT)) {
//                mVar = new com.tencent.mm.h.b.a.m();
//                mVar.ciU = this.dmT;
//                mVar.ciV = 10;
//                mVar.QX();
//            }
//            intent2 = new Intent();
//            intent2.putExtra("k_username", this.dmT);
//            intent2.putExtra("showShare", false);
//            intent2.putExtra("rawUrl", String.format("https://weixin110.qq.com/security/readtemplate?t=weixin_report/w_type&scene=%d#wechat_redirect", new Object[]{Integer.valueOf(36)}));
//            com.tencent.mm.br.d.b(this, "webview", ".ui.tools.WebViewUI", intent2);
//            bVar = com.tencent.mm.modelstat.b.eBD;
//            str2 = this.dmT;
//            if (bVar.Rm() && bVar.cd(str2)) {
//                iMBehavior = new IMBehavior();
//                iMBehavior.opType = 1;
//                iMBehavior.chattingOp = new IMBehaviorChattingOP();
//                iMBehavior.chattingOp.expose = 1;
//                synchronized (bVar.lock) {
//                    bVar.eBC.oplist_.add(iMBehavior);
//                }
//            }
//        } else if (str.equals("room_del_quit")) {
//            y.d("MicroMsg.ChatroomInfoUI", " quit " + this.dmT);
//            ru ruVar = new ru();
//            ruVar.cbq.cbs = true;
//            com.tencent.mm.sdk.b.a.udP.m(ruVar);
//            aaM = !bk.bl(this.dmT) && this.dmT.equals(ruVar.cbr.cbu);
//            if (aaM) {
//                y.d("MicroMsg.ChatroomInfoUI", " quit talkroom" + this.dmT);
//            } else if (this.dnG && this.dnL.MN().size() > 2) {
//                h.a((Context) this, "", new String[]{getString(i.room_owner_delete_direct)}, getString(i.app_cancel), new h.c() {
//                    public final void gl(int i) {
//                        switch (i) {
//                            case 0:
//                                y.d("MicroMsg.ChatroomInfoUI", "dz[dealQuitChatRoom owner click room_owner_delete_direct]");
//                                ChatroomInfoUI.v(ChatroomInfoUI.this);
//                                ((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).Is(ChatroomInfoUI.this.dmT);
//                                return;
//                            default:
//                                y.d("MicroMsg.ChatroomInfoUI", "dz[dealQuitChatRoom owner click cancel]");
//                                return;
//                        }
//                    }
//                });
//            }
//            h.a(this.mController.uMN, getString(i.del_room_mem_comfirm), "", getString(i.app_ok), getString(i.app_cancel), new OnClickListener() {
//                public final void onClick(DialogInterface dialogInterface, int i) {
//                    if (ChatroomInfoUI.this.dmT == null || ChatroomInfoUI.this.dmT.length() <= 0) {
//                        y.e("MicroMsg.ChatroomInfoUI", "quitChatRoom : invalid args");
//                        return;
//                    }
//                    if (aaM) {
//                        ru ruVar = new ru();
//                        ruVar.cbq.cbt = true;
//                        com.tencent.mm.sdk.b.a.udP.m(ruVar);
//                    }
//                    if (((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abn(ChatroomInfoUI.this.dmT)) {
//                        ChatroomInfoUI.v(ChatroomInfoUI.this);
//                        ((com.tencent.mm.plugin.multitalk.a.a) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.multitalk.a.a.class)).Is(ChatroomInfoUI.this.dmT);
//                        return;
//                    }
//                    y.e("MicroMsg.ChatroomInfoUI", "quitChatRoom : room[" + ChatroomInfoUI.this.dmT + "] is not exist");
//                }
//            }, null, a.b.alert_btn_color_warn);
//        } else if (str.equals("add_selector_btn")) {
//            xl();
//        } else if (str.equals("del_selector_btn")) {
//            xm();
//        } else if (str.equals("see_room_member")) {
//            intent = new Intent();
//            intent.setClass(this.mController.uMN, SeeRoomMemberUI.class);
//            intent.putExtra("Block_list", q.Gj());
//            List gK = m.gK(this.dmT);
//            if (gK != null) {
//                this.dnI = gK.size();
//            }
//            intent.putExtra("Chatroom_member_list", bk.c(gK, ","));
//            intent.putExtra("RoomInfo_Id", this.dmT);
//            intent.putExtra("room_owner_name", this.dnL.field_roomowner);
//            intent.putExtra("Is_RoomOwner", this.dnG);
//            intent.putExtra("room_member_count", this.dnI);
//            intent.putExtra("Add_address_titile", getString(i.room_see_room_member));
//            if (this.bRz) {
//                intent.putExtra("Contact_Scene", 14);
//            } else if (this.dno) {
//                intent.putExtra("Contact_Scene", 44);
//                if (!q.gS(this.dnp.field_username)) {
//                    intent.putExtra("Contact_IsLBSFriend", true);
//                }
//            }
//            xk = "offset";
//            View childAt = this.lwE.getChildAt(0);
//            intent.putExtra(xk, childAt == null ? 0 : -childAt.getTop());
//            intent.putExtra("first_pos", this.lwE.getFirstVisiblePosition());
//            intent.putExtra("room_name", this.dnp.field_username);
//            startActivityForResult(intent, 5);
//            this.dnU = 5;
//        } else if (str.equals("manage_room")) {
//            intent2 = new Intent();
//            intent2.setClass(this.mController.uMN, ManageChatroomUI.class);
//            intent2.putExtra("RoomInfo_Id", this.dmT);
//            intent2.putExtra("room_owner_name", this.dnL.field_roomowner);
//            startActivity(intent2);
//        } else if (str.equals("chat_room_app_brand")) {
//            if (s.hb(this.dmT)) {
//                mVar = new com.tencent.mm.h.b.a.m();
//                mVar.ciU = this.dmT;
//                mVar.ciV = 7;
//                mVar.QX();
//            }
//            intent2 = new Intent();
//            intent2.putExtra("Chat_User", this.dmT);
//            com.tencent.mm.br.d.e(this, ".ui.chatting.gallery.AppBrandHistoryListUI", intent2);
//            com.tencent.mm.plugin.report.service.h.nFQ.a(219, 25, 1, true);
//        } else if (str.equals("room_manager_view")) {
//            intent2 = new Intent();
//            intent2.setClass(this.mController.uMN, SeeRoomOwnerManagerUI.class);
//            intent2.putExtra("RoomInfo_Id", this.dmT);
//            intent2.putExtra("room_owner_name", this.dnL.field_roomowner);
//            startActivity(intent2);
//        }
//        return false;
//    }
//
//    private String xk() {
//        if (this.dnL == null) {
//            return "";
//        }
//        return this.dnL.field_selfDisplayName;
//    }
//
//    private void bj(boolean z) {
//        bd.a(this.dmT, new com.tencent.mm.model.bd.a() {
//            public final boolean xz() {
//                return ChatroomInfoUI.this.isDeleteCancel;
//            }
//
//            public final void xA() {
//                if (ChatroomInfoUI.this.dnm != null) {
//                    ChatroomInfoUI.this.dnm.dismiss();
//                }
//            }
//        });
//        ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fv().b(new com.tencent.mm.chatroom.e.a(this.dmT));
//        ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).FB().abu(this.dmT);
//        com.tencent.mm.roomsdk.a.b.YK(this.dmT).eN(this.dmT).cpz();
//        m.gI(this.dmT);
//        if (!z) {
//            this.handler.post(new Runnable() {
//                public final void run() {
//                    ((com.tencent.mm.plugin.zero.b.b) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.zero.b.b.class)).Pm().iT(7);
//                }
//            });
//        }
//        Intent intent = new Intent();
//        intent.addFlags(67108864);
//        intent.setComponent(new ComponentName(getPackageName(), ae.cqQ() + ".ui.LauncherUI"));
//        startActivity(intent);
//        finish();
//    }
//
//    private void xl() {
//        if (this.bRz) {
//            com.tencent.mm.plugin.report.service.h.nFQ.a(219, 7, 1, true);
//            List gK = m.gK(this.dmT);
//            String c = bk.c(gK, ",");
//            if (gK != null) {
//                this.dnI = gK.size();
//            }
//            Intent intent = new Intent();
//            intent.putExtra("titile", getString(i.address_title_select_contact));
//            intent.putExtra("list_type", 1);
//            intent.putExtra("list_attr", gg(com.tencent.mm.ui.contact.s.vMs));
//            intent.putExtra("always_select_contact", c);
//            intent.putExtra("scene", 3);
//            intent.putExtra("KBlockOpenImFav", s.ha(this.dmT));
//            com.tencent.mm.br.d.c((Context) this, ".ui.contact.SelectContactUI", intent, 1);
//            return;
//        }
//        com.tencent.mm.plugin.report.service.h.nFQ.aC(10170, "1");
//        LinkedList linkedList = new LinkedList();
//        linkedList.add(this.dmT);
//        linkedList.add(q.Gj());
//        String c2 = bk.c(linkedList, ",");
//        Intent intent2 = new Intent();
//        intent2.putExtra("titile", getString(i.address_title_launch_chatting));
//        intent2.putExtra("list_type", 0);
//        intent2.putExtra("list_attr", gg(com.tencent.mm.ui.contact.s.vMt));
//        intent2.putExtra("always_select_contact", c2);
//        com.tencent.mm.br.d.e(this, ".ui.contact.SelectContactUI", intent2);
//    }
//
//    private int gg(int i) {
//        if (!com.tencent.mm.ui.contact.s.cHQ() || this.dnp == null) {
//            return i;
//        }
//        String str = this.dnp.field_username;
//        if (ad.aaU(str) || ad.aaV(str)) {
//            return i | 16777216;
//        }
//        return i & -16777217;
//    }
//
//    private void xm() {
//        com.tencent.mm.plugin.report.service.h.nFQ.a(219, 8, 1, true);
//        List gK = m.gK(this.dmT);
//        String c = bk.c(gK, ",");
//        this.dnI = gK.size();
//        Intent intent = new Intent();
//        intent.putExtra("RoomInfo_Id", this.dmT);
//        intent.putExtra("Is_Chatroom", true);
//        intent.putExtra("Chatroom_member_list", c);
//        intent.putExtra("room_member_count", this.dnI);
//        intent.putExtra("Is_RoomOwner", this.dnG);
//        intent.putExtra("list_attr", com.tencent.mm.ui.contact.s.vMs);
//        intent.putExtra("room_name", this.dnp.field_username);
//        intent.putExtra("room_owner_name", this.dnL.field_roomowner);
//        intent.setClass(this, SelectDelMemberUI.class);
//        startActivityForResult(intent, 7);
//    }
//
//    private void c(String str, String str2, int i) {
//        boolean z = false;
//        if (!s.ha(this.dmT) || l.gA(str)) {
//            if (bk.pm(q.Gj()).equals(str)) {
//                z = true;
//            } else {
//                List gK = m.gK(this.dmT);
//                if (gK != null) {
//                    boolean z2;
//                    Iterator it = gK.iterator();
//                    while (true) {
//                        z2 = z;
//                        if (!it.hasNext()) {
//                            break;
//                        } else if (((String) it.next()).equals(str)) {
//                            z = true;
//                        } else {
//                            z = z2;
//                        }
//                    }
//                    z = z2;
//                }
//            }
//            if (z) {
//                h.b((Context) this, getString(i.add_room_mem_memberExits), getString(i.app_tip), true);
//                return;
//            }
//            List G = bk.G(str.split(","));
//            if (G != null) {
//                final com.tencent.mm.roomsdk.a.c.a a = com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(this.dmT, G, str2);
//                a.d(new com.tencent.mm.roomsdk.a.b.c() {
//                    public final /* synthetic */ void a(int i, int i2, String str, a aVar) {
//                        com.tencent.mm.roomsdk.a.b.c cVar = (com.tencent.mm.roomsdk.a.b.c) aVar;
//                        com.tencent.mm.i.a eI = com.tencent.mm.i.a.eI(str);
//                        if (eI != null) {
//                            eI.a(ChatroomInfoUI.this, null, null);
//                        } else if (i == 0 && i2 == 0) {
//                            if (i == 0 && i2 == 0) {
//                                ChatroomInfoUI.a(ChatroomInfoUI.this, i2, cVar, str);
//                                ChatroomInfoUI.this.xv();
//                            }
//                            ChatroomInfoUI.z(ChatroomInfoUI.this);
//                        } else {
//                            ChatroomInfoUI.a(ChatroomInfoUI.this, i2, cVar, str);
//                            ChatroomInfoUI.this.xv();
//                        }
//                    }
//                });
//                getString(i.app_tip);
//                a.a(this, getString(i), true, new OnCancelListener() {
//                    public final void onCancel(DialogInterface dialogInterface) {
//                        a.cancel();
//                    }
//                });
//                return;
//            }
//            return;
//        }
//        h.b((Context) this, getString(i.room_member_only_support_weixin), getString(i.app_tip), true);
//    }
//
//    private String xn() {
//        String str;
//        String str2 = null;
//        ad abl = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(this.dnL.field_roomowner);
//        if (abl == null || ((int) abl.dBe) <= 0) {
//            str = null;
//        } else {
//            str = abl.field_conRemark;
//        }
//        if (bk.bl(str)) {
//            str = this.dnL.field_roomowner;
//            if (this.dnL != null) {
//                str2 = this.dnL.gV(str);
//            }
//        } else {
//            str2 = str;
//        }
//        if (bk.bl(str2) && abl != null && ((int) abl.dBe) > 0) {
//            str2 = abl.Bp();
//        }
//        if (bk.bl(str2)) {
//            return this.dnL.field_roomowner;
//        }
//        return str2;
//    }
//
//    private static boolean xo() {
//        return bk.getInt(com.tencent.mm.m.g.AA().getValue("ChatroomGlobalSwitch"), 1) == 1;
//    }
//
//    private void ba(Context context) {
//        if (context != null && xo()) {
//            Intent intent = new Intent();
//            intent.putExtra("rawUrl", getString(i.chatroom_how_to_upgrade, new Object[]{x.cqJ()}));
//            intent.putExtra("geta8key_username", q.Gj());
//            intent.putExtra("showShare", false);
//            com.tencent.mm.br.d.b(this, "webview", "com.tencent.mm.plugin.webview.ui.tools.WebViewUI", intent);
//        }
//    }
//
//    private void d(String str, List<String> list) {
//        if (list != null && list.size() > 0) {
//            LinkedList linkedList = new LinkedList();
//            for (int i = 0; i < list.size(); i++) {
//                linkedList.add(list.get(i));
//            }
//            l.a(str, linkedList, getString(i.chatroom_sys_msg_invite_error_tip), true, "weixin://findfriend/verifycontact/" + str + "/");
//        }
//    }
//
//    private void updateTitle() {
//        if (this.bRz) {
//            this.dnI = m.gM(this.dmT);
//            if (this.dnI == 0) {
//                setMMTitle(getString(i.roominfo_name));
//                return;
//            }
//            setMMTitle(getString(i.fmt_chatting_title_group, new Object[]{getString(i.roominfo_name), Integer.valueOf(this.dnI)}));
//        }
//    }
//
//    private void xp() {
//        if (this.dnv == null) {
//            return;
//        }
//        if (this.bRz) {
//            com.tencent.mm.kernel.g.DS().O(new Runnable() {
//                public final void run() {
//                    List list;
//                    String str;
//                    ChatroomInfoUI.this.dnZ = ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().iq(ChatroomInfoUI.this.dmT);
//                    List gK = m.gK(ChatroomInfoUI.this.dmT);
//                    if (gK != null) {
//                        ChatroomInfoUI.this.dnI = gK.size();
//                        list = gK;
//                    } else {
//                        LinkedList linkedList = new LinkedList();
//                        ChatroomInfoUI.this.dnI = 0;
//                        Object list2 = linkedList;
//                    }
//                    LinkedList linkedList2 = new LinkedList();
//                    for (String str2 : r1) {
//                        if (ChatroomInfoUI.this.dnL.aaM(str2) || ChatroomInfoUI.this.dnL.aaP(str2)) {
//                            linkedList2.add(str2);
//                        }
//                    }
//                    if (ChatroomInfoUI.this.dnI > com.tencent.mm.pluginsdk.ui.applet.j.scD + 1 && r1 != null) {
//                        list2 = r1.subList(0, com.tencent.mm.pluginsdk.ui.applet.j.scD + 1);
//                        Iterator it = linkedList2.iterator();
//                        while (it.hasNext()) {
//                            str2 = (String) it.next();
//                            if (!list2.contains(str2)) {
//                                list2.add(0, str2);
//                            }
//                        }
//                    }
//                    ai.d(new Runnable() {
//                        public final void run() {
//                            if (ChatroomInfoUI.this.dnI <= 1) {
//                                ChatroomInfoUI.this.dnn.bJ("del_selector_btn", true);
//                                ChatroomInfoUI.this.dnv.lS(true).lT(false).cmQ();
//                            } else {
//                                ChatroomInfoUI.this.dnv.lS(true).lT(ChatroomInfoUI.this.xw()).cmQ();
//                            }
//                            ChatroomInfoUI.this.dnv.s(ChatroomInfoUI.this.dmT, list2);
//                        }
//                    });
//                }
//            });
//            return;
//        }
//        LinkedList linkedList = new LinkedList();
//        linkedList.add(this.dmT);
//        this.dnv.s(this.dmT, linkedList);
//    }
//
//    private void xq() {
//        if (this.dnp != null) {
//            CharSequence xk = xk();
//            if (bk.bl(xk)) {
//                xk = q.Gl();
//            }
//            if (bk.bl(xk)) {
//                this.dnz.setSummary((CharSequence) "");
//            } else {
//                SignaturePreference signaturePreference = this.dnz;
//                if (xk.length() <= 0) {
//                    xk = getString(i.settings_signature_empty);
//                }
//                signaturePreference.setSummary((CharSequence) com.tencent.mm.pluginsdk.ui.d.j.b((Context) this, xk));
//            }
//            if (this.dnn != null) {
//                this.dnn.notifyDataSetChanged();
//            }
//        }
//    }
//
//    private void xr() {
//        if (this.dnD == null) {
//            this.dnD = getSharedPreferences(getPackageName() + "_preferences", 0);
//        }
//        if (this.bRz) {
//            this.dnJ = this.dnp.cCy == 0;
//        } else if (!this.dno) {
//            this.dnJ = this.dnp.Bj();
//        }
//        if (this.dnJ) {
//            setTitleMuteIconVisibility(0);
//            if (this.dnw != null) {
//                this.dnD.edit().putBoolean("room_notify_new_msg", true).commit();
//            }
//        } else {
//            setTitleMuteIconVisibility(8);
//            if (this.dnw != null) {
//                this.dnD.edit().putBoolean("room_notify_new_msg", false).commit();
//            }
//        }
//        this.dnn.notifyDataSetChanged();
//    }
//
//    private void xs() {
//        if (this.dnp != null && this.dnq != null) {
//            CharSequence gO = m.gO(this.dmT);
//            if (gO == null || gO.length() <= 0) {
//                this.dnq.bHj = false;
//            } else {
//                this.dnq.bHj = true;
//                this.dnq.dtE = com.tencent.mm.pluginsdk.ui.d.j.b((Context) this, gO);
//            }
//            this.dnp = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(this.dmT);
//            if (xu()) {
//                gO = this.dnp.Bq();
//                RoomCardPreference roomCardPreference = this.dnq;
//                if (gO.length() <= 0) {
//                    gO = getString(i.settings_signature_empty);
//                }
//                roomCardPreference.dtD = com.tencent.mm.pluginsdk.ui.d.j.b((Context) this, gO);
//            } else {
//                this.dnq.dtD = getString(i.room_has_no_topic);
//            }
//            this.dnn.notifyDataSetChanged();
//        }
//    }
//
//    private void xt() {
//        if (this.dnp != null && this.dnr != null) {
//            this.dnp = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(this.dmT);
//            if (xu()) {
//                CharSequence Bq = this.dnp.Bq();
//                SignaturePreference signaturePreference = this.dnr;
//                if (Bq.length() <= 0) {
//                    Bq = getString(i.settings_signature_empty);
//                }
//                signaturePreference.setSummary((CharSequence) com.tencent.mm.pluginsdk.ui.d.j.b((Context) this, Bq));
//                if (this.dnn != null) {
//                    this.dnn.notifyDataSetChanged();
//                    return;
//                }
//                return;
//            }
//            this.dnr.setSummary((CharSequence) getString(i.room_has_no_topic));
//        }
//    }
//
//    private boolean xu() {
//        String str = this.dnp.field_nickname;
//        return !bk.bl(str) && str.length() <= 50;
//    }
//
//    public static ArrayList<ad> E(List<ats> list) {
//        ArrayList<ad> arrayList = new ArrayList();
//        if (list == null) {
//            return arrayList;
//        }
//        for (ats ats : list) {
//            ad adVar = new ad();
//            adVar.setUsername(ats.hPY);
//            adVar.dk(ats.hRf);
//            arrayList.add(adVar);
//        }
//        return arrayList;
//    }
//
//    private static List<String> F(List<String> list) {
//        LinkedList linkedList = new LinkedList();
//        if (!com.tencent.mm.kernel.g.DK()) {
//            return linkedList;
//        }
//        if (list == null) {
//            return linkedList;
//        }
//        for (Object obj : list) {
//            Object obj2;
//            ad abl = ((com.tencent.mm.plugin.messenger.foundation.a.j) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.messenger.foundation.a.j.class)).Fw().abl(obj2);
//            if (!(abl == null || ((int) abl.dBe) == 0)) {
//                obj2 = abl.Bq();
//            }
//            linkedList.add(obj2);
//        }
//        return linkedList;
//    }
//
//    public void onSceneEnd(int i, int i2, String str, com.tencent.mm.ah.m mVar) {
//        y.i("MicroMsg.ChatroomInfoUI", "onSceneEnd: errType = " + i + " errCode = " + i2 + " errMsg = " + str);
//        y.d("MicroMsg.ChatroomInfoUI", "pre is " + this.dnI);
//        this.dnI = m.gM(this.dmT);
//        y.d("MicroMsg.ChatroomInfoUI", "now is " + this.dnI);
//        if (this.dnm != null) {
//            this.dnm.dismiss();
//        }
//    }
//
//    private void xv() {
//        if (this.dnv != null) {
//            if (this.bRz) {
//                xp();
//            } else if (!this.dno) {
//                LinkedList linkedList = new LinkedList();
//                linkedList.add(this.dmT);
//                this.dnv.s(this.dmT, linkedList);
//            }
//            this.dnv.notifyChanged();
//        }
//        boolean zl = ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).zl(this.dmT);
//        if (xw()) {
//            this.dnn.bJ("room_manager_view", true);
//            if (m.gM(this.dmT) > 2) {
//                this.dnn.bJ("manage_room", false);
//            } else {
//                this.dnn.bJ("manage_room", true);
//            }
//        } else {
//            this.dnn.bJ("manage_room", true);
//            com.tencent.mm.ui.base.preference.f fVar = this.dnn;
//            String str = "room_manager_view";
//            if (zl) {
//                zl = false;
//            } else {
//                zl = true;
//            }
//            fVar.bJ(str, zl);
//        }
//        if ((xw() || this.dnI < com.tencent.mm.pluginsdk.ui.applet.j.scD) && (!xw() || this.dnI < com.tencent.mm.pluginsdk.ui.applet.j.scD - 1)) {
//            this.dnn.bJ("see_room_member", true);
//        } else {
//            this.dnn.bJ("see_room_member", false);
//            this.dns.setTitle(getString(i.see_member_selector_btn));
//        }
//        if (s.hb(this.dmT)) {
//            this.dnn.bJ("manage_room", true);
//            this.dnn.bJ("room_manager_view", true);
//        }
//        this.dnn.notifyDataSetChanged();
//    }
//
//    public final void a(int i, com.tencent.mm.sdk.e.m mVar, Object obj) {
//        if (obj == null || !(obj instanceof String)) {
//            y.d("MicroMsg.ChatroomInfoUI", "onNotifyChange obj not String event:%d stg:%s obj:%s", Integer.valueOf(i), mVar, obj);
//            return;
//        }
//        a((String) obj, null);
//    }
//
//    public final void a(String str, com.tencent.mm.sdk.e.l lVar) {
//        if (!bk.bl(str)) {
//            if (s.fn(str)) {
//                y.d("MicroMsg.ChatroomInfoUI", "event:" + str);
//                if (this.bRz && str.equals(this.dmT)) {
//                    com.tencent.mm.kernel.g.DS().O(new Runnable() {
//                        public final void run() {
//                            ChatroomInfoUI.this.dnL = ((com.tencent.mm.plugin.chatroom.a.c) com.tencent.mm.kernel.g.r(com.tencent.mm.plugin.chatroom.a.c.class)).FF().in(ChatroomInfoUI.this.dmT);
//                            if (ChatroomInfoUI.this.dnL == null) {
//                                y.e("MicroMsg.ChatroomInfoUI", "member is null");
//                            } else if (bk.bl(ChatroomInfoUI.this.dnL.field_roomowner)) {
//                                y.e("MicroMsg.ChatroomInfoUI", "roomowner is null");
//                            } else {
//                                ChatroomInfoUI.this.handler.sendEmptyMessage(0);
//                                ChatroomInfoUI.this.dnG = ChatroomInfoUI.this.dnL.field_roomowner.equals(q.Gj());
//                                ChatroomInfoUI.this.dnH = ChatroomInfoUI.this.dnL.aaM(q.Gj());
//                                ChatroomInfoUI.this.dnv.WI(ChatroomInfoUI.this.dnL.field_roomowner);
//                            }
//                        }
//
//                        public final String toString() {
//                            return super.toString() + "|onNotifyChange";
//                        }
//                    });
//                }
//                xv();
//                return;
//            }
//            y.d("MicroMsg.ChatroomInfoUI", "event:" + str + " cancel");
//        }
//    }
//
//    public final void i(String str, String str2, String str3) {
//        if (str.equals(this.dmT) && this.dnv != null) {
//            this.dnv.notifyChanged();
//        }
//    }
//
//    public final com.tencent.mm.ui.base.preference.h a(SharedPreferences sharedPreferences) {
//        return new com.tencent.mm.ui.base.preference.a(this, sharedPreferences);
//    }
//
//    private boolean xw() {
//        return this.dnL != null && this.dnL.xw();
//    }
//}