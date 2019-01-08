//package com.tencent.mm.ui.contact;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.MenuItem.OnMenuItemClickListener;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//import android.view.animation.Animation.AnimationListener;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.tencent.map.lib.gl.model.GLIcon;
//import com.tencent.mm.R;
//import com.tencent.mm.ah.f;
//import com.tencent.mm.ah.m;
//import com.tencent.mm.as.l;
//import com.tencent.mm.br.d;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.model.au;
//import com.tencent.mm.model.q;
//import com.tencent.mm.model.r;
//import com.tencent.mm.model.s;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.platformtools.ah;
//import com.tencent.mm.roomsdk.a.b;
//import com.tencent.mm.roomsdk.a.b.c;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ai;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.ui.base.h;
//import com.tencent.mm.ui.chatting.ChattingUI;
//import com.tencent.mm.ui.contact.a.j;
//
//import junit.framework.Assert;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//public class SelectContactUI extends MMBaseSelectContactUI implements f {
//    private int bQU;
//    private ProgressDialog dnm;
//    private List<String> dru;
//    private HashSet<String> eXT;
//    private String imV;
//    private String mSource;
//    private String title;
//    private int vIY;
//    private HashSet<String> vMY;
//    private String vNA;
//    private int vNa;
//    private boolean vNb = true;
//    private a vNc = new a();
//    private TextView vNn;
//    private TextView vNo;
//    private TextView vNp;
//    private TextView vNq;
//    private TextView vNr;
//    private boolean vNs;
//    private boolean vNt;
//    private String vNu;
//    private String vNv;
//    private boolean vNw;
//    private boolean vNx;
//    private boolean vNy;
//    private boolean vNz = false;
//
//    static class a {
//        TextView gSy;
//        private AlphaAnimation vNH;
//        private AlphaAnimation vNI;
//        boolean vNJ = false;
//
//        a() {
//        }
//
//        public final void d(final Activity activity, int i, int i2) {
//            if (i2 > 0 && i == i2 + 1 && !this.vNJ) {
//                if (this.gSy == null) {
//                    this.gSy = (TextView) activity.findViewById(R.h.select_contact_float_tips);
//                }
//                if (this.gSy.getVisibility() != 0) {
//                    this.gSy.setText(R.l.room_add_member_tips);
//                    this.gSy.setOnClickListener(new OnClickListener() {
//                        public final void onClick(View view) {
//                            a.this.vNJ = true;
//                            a.this.ac(activity);
//                        }
//                    });
//                    if (this.vNH == null) {
//                        this.vNH = new AlphaAnimation(0.0f, 1.0f);
//                    }
//                    this.vNH.setDuration(300);
//                    if (this.vNI != null) {
//                        this.vNI.cancel();
//                    }
//                    this.gSy.setVisibility(0);
//                    this.gSy.startAnimation(this.vNH);
//                }
//            } else if (i < i2) {
//                ac(activity);
//                this.vNJ = false;
//            }
//        }
//
//        final void ac(Activity activity) {
//            if (this.gSy == null) {
//                this.gSy = (TextView) activity.findViewById(R.h.select_contact_float_tips);
//            }
//            if (this.gSy.getVisibility() != 8) {
//                if (this.vNI == null) {
//                    this.vNI = new AlphaAnimation(1.0f, 0.0f);
//                    this.vNI.setAnimationListener(new AnimationListener() {
//                        public final void onAnimationStart(Animation animation) {
//                        }
//
//                        public final void onAnimationRepeat(Animation animation) {
//                        }
//
//                        public final void onAnimationEnd(Animation animation) {
//                            a.this.gSy.setVisibility(8);
//                        }
//                    });
//                }
//                this.vNI.setDuration(300);
//                if (this.vNH != null) {
//                    this.vNH.cancel();
//                }
//                this.gSy.startAnimation(this.vNI);
//            }
//        }
//    }
//
//    static /* synthetic */ void a(SelectContactUI selectContactUI, int i, int i2, c cVar, String str, boolean z) {
//        String str2 = "";
//        String str3 = "";
//        String string = ae.getContext().getString(R.l.chatroom_sys_msg_invite_split);
//        if (!z) {
//            if (cVar != null && !bk.bl(cVar.svS)) {
//                h.b((Context) selectContactUI, cVar.svS, "", true);
//                return;
//            } else if (!(z || bk.bl(str))) {
//                h.b((Context) selectContactUI, str, "", true);
//                return;
//            }
//        }
//        if (i2 == -23) {
//            str2 = selectContactUI.getString(R.l.room_member_toomuch_tip);
//            str3 = selectContactUI.getString(R.l.room_member_toomuch);
//        }
//        List list = cVar.bST;
//        List list2 = cVar.dmN;
//        if (list == null || list.size() <= 0 || (list.size() != cVar.bRQ && (list2 == null || list2.size() <= 0 || cVar.bRQ != list.size() + list2.size()))) {
//            String str4;
//            list = cVar.dmN;
//            if (list == null || list.size() <= 0 || cVar.bRQ != list.size()) {
//                str4 = str3;
//            } else {
//                str2 = selectContactUI.getString(R.l.launchchatting_create_chatroom_fail);
//                str4 = str3 + selectContactUI.getString(R.l.fmt_in_blacklist, new Object[]{bk.c(F(list), string)});
//            }
//            List<String> list22 = cVar.bSQ;
//            if (list22 != null && list22.size() > 0) {
//                boolean z2;
//                for (String str32 : list22) {
//                    if (ad.aaU(str32)) {
//                        str2 = selectContactUI.getString(R.l.launchchatting_create_chatroom_fail);
//                        str4 = selectContactUI.getString(R.l.launchchatting_create_chatroom_openim_fail);
//                        z2 = true;
//                        break;
//                    }
//                }
//                z2 = false;
//                if (!z2) {
//                    str2 = selectContactUI.getString(R.l.launchchatting_create_chatroom_fail);
//                    str4 = str4 + selectContactUI.getString(R.l.fmt_invalid_username, new Object[]{bk.c(F(list22), string)});
//                }
//            }
//            if (str2 == null || str2.length() <= 0) {
//                Toast.makeText(selectContactUI, selectContactUI.getString(R.l.fmt_create_chatroom_err, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}), 0).show();
//                return;
//            } else {
//                h.b((Context) selectContactUI, str4, str2, true);
//                return;
//            }
//        }
//        final LinkedList linkedList = new LinkedList();
//        for (int i3 = 0; i3 < list.size(); i3++) {
//            linkedList.add(list.get(i3));
//        }
//        Assert.assertTrue(linkedList.size() > 0);
//        str32 = ae.getContext().getString(R.l.chatroom_sys_msg_invite_split);
//        ArrayList arrayList = new ArrayList();
//        arrayList.addAll(linkedList);
//        arrayList.addAll(list22);
//        Object[] objArr = new Object[]{bk.c(F(arrayList), str32)};
//        h.a((Context) selectContactUI, selectContactUI.getString(R.l.fmt_need_verify_multiuser, objArr), selectContactUI.getString(R.l.launchchatting_create_chatroom_fail), selectContactUI.getString(R.l.fmt_need_verify_alert_confirm), selectContactUI.getString(R.l.fmt_need_verify_alert_cancel), true, new DialogInterface.OnClickListener() {
//            public final void onClick(DialogInterface dialogInterface, int i) {
//                SelectContactUI.a(SelectContactUI.this, linkedList);
//            }
//        }, null);
//    }
//
//    static /* synthetic */ void e(SelectContactUI selectContactUI) {
//        ArrayList<String> nB = selectContactUI.nB(false);
//        nB.remove(q.Gj());
//        if (nB.size() == 1) {
//            selectContactUI.vNx = false;
//            selectContactUI.finish();
//            selectContactUI.a(ChattingUI.class, new Intent().putExtra("Chat_User", (String) nB.get(0)));
//            return;
//        }
//        boolean z;
//        for (String aaU : nB) {
//            if (ad.aaU(aaU)) {
//                z = false;
//                break;
//            }
//        }
//        z = true;
//        if (z) {
//            selectContactUI.a(b.YK("@chatroom").b("", nB));
//        } else if (s.cHQ()) {
//            selectContactUI.vNx = false;
//            g.DQ();
//            g.DP().Dz().getBoolean(com.tencent.mm.storage.ac.a.USERINFO_OPENIM_SELECT_ALERT_ID_BOOLEAN, false);
//            selectContactUI.vNx = true;
//            selectContactUI.a(b.YK("@im.chatroom").b("", nB));
//        } else {
//            h.a((Context) selectContactUI, selectContactUI.getString(R.l.share_openim_forbiden_create_room_waring), "", null);
//            selectContactUI.vNx = false;
//        }
//    }
//
//    protected final void xK() {
//        super.xK();
//        this.vNa = getIntent().getIntExtra("list_attr", s.vMq);
//        this.title = getIntent().getStringExtra("titile");
//        this.imV = getIntent().getStringExtra("sub_title");
//        this.vIY = getIntent().getIntExtra("list_type", -1);
//        this.vNb = getIntent().getBooleanExtra("show_too_many_member", true);
//        this.mSource = getIntent().getStringExtra("label_source");
//        if (s.fA(this.vNa, 256) && s.GV().size() == 0) {
//            s.fB(this.vNa, 256);
//        }
//        this.vNs = getIntent().getBooleanExtra("Add_SendCard", false);
//        this.vNt = getIntent().getBooleanExtra("recommend_friends", false);
//        if (this.vNs || this.vNt) {
//            this.vNu = bk.aM(getIntent().getStringExtra("be_send_card_name"), "");
//            this.vNv = bk.aM(getIntent().getStringExtra("received_card_name"), "");
//        }
//        this.vNw = getIntent().getBooleanExtra("Forbid_SelectChatRoom", false);
//        this.dru = new ArrayList();
//        this.eXT = new HashSet();
//        this.vMY = new HashSet();
//        String stringExtra = getIntent().getStringExtra("always_select_contact");
//        if (!bk.bl(stringExtra)) {
//            this.vMY.addAll(bk.G(stringExtra.split(",")));
//        }
//        stringExtra = getIntent().getStringExtra("already_select_contact");
//        if (!bk.bl(stringExtra)) {
//            this.eXT.addAll(bk.G(stringExtra.split(",")));
//        }
//        HashSet hashSet = new HashSet();
//        String stringExtra2 = getIntent().getStringExtra("block_contact");
//        if (!bk.bl(stringExtra2)) {
//            hashSet.addAll(bk.G(stringExtra2.split(",")));
//        }
//        HashSet hashSet2 = new HashSet(hashSet);
//        hashSet2.addAll(s.cHO());
//        hashSet2.addAll(s.cHP());
//        if (this.vNs) {
//            hashSet2.removeAll(s.cHO());
//        }
//        this.dru.addAll(hashSet2);
//        cHR();
//    }
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        au.Dk().a(30, (f) this);
//        au.Dk().a(138, (f) this);
//        y.i("MicroMsg.SelectContactUI", "create!");
//        if (!bk.bl(this.imV)) {
//            setMMSubTitle(this.imV);
//        }
//        if (s.fA(this.vNa, 64)) {
//            a(1, getString(R.l.app_ok), (OnMenuItemClickListener) new OnMenuItemClickListener() {
//                public final boolean onMenuItemClick(MenuItem menuItem) {
//                    ArrayList c = SelectContactUI.this.nB(s.fA(SelectContactUI.this.vNa, 8192));
//                    c.remove(q.Gj());
//                    boolean z = s.fA(SelectContactUI.this.vNa, 4096) && c.size() > 1;
//                    if (!z) {
//                        return SelectContactUI.this.em(SelectContactUI.this.nA(s.fA(SelectContactUI.this.vNa, 8192)));
//                    }
//                    if (!SelectContactUI.this.vNx) {
//                        SelectContactUI.this.vNx = true;
//                        SelectContactUI.e(SelectContactUI.this);
//                    }
//                    y.i("MicroMsg.SelectContactUI", "Create the chatroom");
//                    return true;
//                }
//            }, com.tencent.mm.ui.s.b.GREEN);
//        }
//        xU();
//        setBackBtn(new OnMenuItemClickListener() {
//            public final boolean onMenuItemClick(MenuItem menuItem) {
//                SelectContactUI.this.XM();
//                SelectContactUI.this.finish();
//                if (!SelectContactUI.this.getIntent().getBooleanExtra("stay_in_wechat", true)) {
//                    ai.l(new Runnable() {
//                        public final void run() {
//                            SelectContactUI.this.moveTaskToBack(true);
//                        }
//                    }, 80);
//                }
//                return true;
//            }
//        });
//        if (this.vIY != 15) {
//            Iterator it = this.eXT.iterator();
//            while (it.hasNext()) {
//                this.mbR.bz((String) it.next(), false);
//            }
//        }
//    }
//
//    protected void onDestroy() {
//        au.Dk().b(30, (f) this);
//        au.Dk().b(138, (f) this);
//        super.onDestroy();
//    }
//
//    public final void jP(int i) {
//        int headerViewsCount = i - getContentLV().getHeaderViewsCount();
//        if (headerViewsCount < 0) {
//            y.i("MicroMsg.SelectContactUI", "offsetPosition is Smaller than 0, offsetPosition=%d | position=%s", Integer.valueOf(headerViewsCount), Integer.valueOf(i));
//            return;
//        }
//        com.tencent.mm.ui.contact.a.a HK = cHE().getItem(headerViewsCount);
//        if (HK == null) {
//            return;
//        }
//        if (HK instanceof j) {
//            if (s.fA(this.vNa, 16384)) {
//                y.i("MicroMsg.SelectContactUI", "handleClickNonSelect, return the result");
//                Intent intent = new Intent();
//                String str = "";
//                intent.putExtra("Select_Contact", str);
//                intent.putExtra("Select_Conv_User", str);
//                intent.putExtra("Select_Contact", str);
//                setResult(-1, intent);
//                finish();
//            }
//        } else if (HK.dnp != null && HK.dnp.field_deleteFlag != 1) {
//            String str2 = HK.dnp.field_username;
//            y.i("MicroMsg.SelectContactUI", "ClickUser=%s", str2);
//            if (s.fA(this.vNa, 64)) {
//                if (!s.fA(this.vNa, WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT) || this.eXT.size() < getIntent().getIntExtra("max_limit_num", Integer.MAX_VALUE)) {
//                    adU(str2);
//                } else if (!this.vMY.contains(str2)) {
//                    cHK();
//                    if (this.eXT.contains(str2)) {
//                        this.mbR.Wv(str2);
//                        this.eXT.remove(str2);
//                    } else {
//                        str2 = getIntent().getStringExtra("too_many_member_tip_string");
//                        if (bk.bl(str2)) {
//                            str2 = getString(R.l.select_contact_num_limit_tips, new Object[]{Integer.valueOf(getIntent().getIntExtra("max_limit_num", 10))});
//                        }
//                        h.a(this.mController.uMN, str2, getString(R.l.app_remind), new DialogInterface.OnClickListener() {
//                            public final void onClick(DialogInterface dialogInterface, int i) {
//                            }
//                        });
//                    }
//                }
//                xU();
//                cHR();
//                cHF().notifyDataSetChanged();
//                return;
//            }
//            em(bk.G(new String[]{str2}));
//        }
//    }
//
//    private void cHR() {
//        int i = 0;
//        if (!this.vNb) {
//            return;
//        }
//        if (this.vIY == 1 || this.vIY == 0) {
//            int size = this.vMY != null ? this.vMY.size() : 0;
//            if (this.eXT != null) {
//                i = this.eXT.size();
//            }
//            this.vNc.d(this, size + i, bk.ZR(com.tencent.mm.m.g.AA().getValue("ChatRoomInviteStartCount")));
//        }
//    }
//
//    protected final boolean VC() {
//        return false;
//    }
//
//    protected final boolean VD() {
//        return true;
//    }
//
//    protected final String VE() {
//        return this.title;
//    }
//
//    protected final o VF() {
//        boolean z;
//        boolean z2 = false;
//        com.tencent.mm.ui.contact.c.a aVar = new com.tencent.mm.ui.contact.c.a();
//        aVar.vJK = s.fA(this.vNa, 16);
//        aVar.vJJ = s.fA(this.vNa, 32);
//        aVar.vJL = !s.fA(this.vNa, 4);
//        if (s.fA(this.vNa, 1)) {
//            z = false;
//        } else {
//            z = true;
//        }
//        aVar.vJM = z;
//        aVar.vJN = s.fA(this.vNa, 128);
//        aVar.vJO = s.fA(this.vNa, 1048576);
//        aVar.vJR = s.fA(this.vNa, 256);
//        if (aVar.vJR) {
//            aVar.vJS = getIntent().getStringExtra("custom_contact");
//        }
//        if (aVar.vJN) {
//            this.vNz = true;
//            aVar.vJP = getIntent().getStringExtra("wechat_sport_contact");
//            aVar.vJQ = getIntent().getStringExtra("wechat_sport_recent_like");
//            this.vNA = aVar.vJP;
//        }
//        if (s.cHQ()) {
//            aVar.vIs = "@all.contact.without.chatroom.without.openim";
//        } else {
//            aVar.vIs = "@all.contact.without.chatroom.without.openim.without.openimfavour";
//        }
//        if (this.scene == 6 || this.scene == 5) {
//            aVar.vIs = "@all.contact.without.chatroom.without.openim.without.openimfavour";
//        }
//        if (getIntent().getBooleanExtra("KBlockOpenImFav", false)) {
//            aVar.vIs = "@all.contact.without.chatroom.without.openim.without.openimfavour";
//        }
//        List list = this.dru;
//        boolean fA = s.fA(this.vNa, 1);
//        boolean fA2 = s.fA(this.vNa, 64);
//        if (this.vIY == 15) {
//            z2 = true;
//        }
//        return new c(this, list, fA, fA2, aVar, z2);
//    }
//
//    protected final m VG() {
//        if (this.vNz) {
//            return new r(this, this.dru, s.fA(this.vNa, 64), this.vNA);
//        }
//        return new q(this, this.dru, s.fA(this.vNa, 64), this.scene);
//    }
//
//    protected final void a(ListView listView, int i) {
//        String string;
//        super.a(listView, i);
//        if (s.fA(this.vNa, 256)) {
//            if (this.vNn == null) {
//                AnonymousClass10 anonymousClass10 = new OnClickListener() {
//                    public final void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.setClassName(SelectContactUI.this, "com.tencent.mm.ui.contact.GroupCardSelectUI");
//                        intent.putExtra("group_select_type", true);
//                        boolean fA = s.fA(SelectContactUI.this.vNa, 16384);
//                        intent.putExtra("group_select_need_result", fA);
//                        if (!fA) {
//                            SelectContactUI.this.startActivity(intent);
//                        } else if (SelectContactUI.this.vIY == 14) {
//                            intent.putExtra("group_multi_select", true);
//                            intent.putExtra("already_select_contact", ah.c(SelectContactUI.this.nA(true), ","));
//                            intent.putExtra("max_limit_num", SelectContactUI.this.getIntent().getIntExtra("max_limit_num", 9));
//                            SelectContactUI.this.startActivityForResult(intent, 4);
//                        } else {
//                            SelectContactUI.this.startActivityForResult(intent, 0);
//                        }
//                    }
//                };
//                if (this.vIY == 14) {
//                    string = getString(R.l.address_select_group_card);
//                } else {
//                    string = getString(R.l.address_history_group_card);
//                }
//                this.vNn = a(listView, anonymousClass10, string);
//            }
//            this.vNn.setVisibility(i);
//        }
//        if (s.fA(this.vNa, 512)) {
//            if (this.vNo == null) {
//                this.vNo = a(listView, new OnClickListener() {
//                    public final void onClick(View view) {
//                        com.tencent.mm.plugin.report.service.h.nFQ.f(11140, Integer.valueOf(0));
//                        d.x(SelectContactUI.this, "pwdgroup", ".ui.FacingCreateChatRoomAllInOneUI");
//                    }
//                }, getString(R.l.find_friends_create_pwdgroup));
//            }
//            this.vNo.setVisibility(i);
//        }
//        if (s.fA(this.vNa, 1024)) {
//            if (this.vNp == null) {
//                this.vNp = a(listView, new OnClickListener() {
//                    public final void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.setClassName(SelectContactUI.this, "com.tencent.mm.ui.contact.GroupCardSelectUI");
//                        intent.putExtra("group_select_type", false);
//                        SelectContactUI.this.startActivityForResult(intent, 1);
//                    }
//                }, getString(R.l.address_history_group_card_import));
//            }
//            this.vNp.setVisibility(i);
//        }
//        if (s.fA(this.vNa, 2048)) {
//            if (this.vNq == null) {
//                this.vNq = a(listView, new OnClickListener() {
//                    public final void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.putExtra("list_attr", 16384);
//                        d.b(SelectContactUI.this, "brandservice", ".ui.BrandServiceIndexUI", intent, 2);
//                    }
//                }, getString(R.l.address_official_accounts_title));
//            }
//            this.vNq.setVisibility(i);
//            this.vNq.setTextSize(16.0f * com.tencent.mm.cb.a.cJ(this.vNq.getContext()));
//        }
//        if (s.fA(this.vNa, 16777216) && this.vNr == null) {
//            au.Hx();
//            y.i("MicroMsg.SelectContactUI", "setOpenIMHeaderView %s", Integer.valueOf(com.tencent.mm.model.c.Fw().cul().size()));
//            if (com.tencent.mm.model.c.Fw().cul().size() != 0) {
//                this.vNr = a(listView, new OnClickListener() {
//                    public final void onClick(View view) {
//                        Intent intent = new Intent(SelectContactUI.this.getIntent());
//                        intent.setClass(SelectContactUI.this.mController.uMN, OpenIMSelectContactUI.class);
//                        intent.removeExtra("titile");
//                        intent.putExtra("openim_appid", "3552365301");
//                        ArrayList arrayList = new ArrayList();
//                        Iterator it = SelectContactUI.this.eXT.iterator();
//                        while (it.hasNext()) {
//                            String str = (String) it.next();
//                            if (ad.aaU(str)) {
//                                arrayList.add(str);
//                            }
//                        }
//                        intent.putExtra("already_select_contact", bk.c(arrayList, ","));
//                        SelectContactUI.this.startActivityForResult(intent, 5);
//                    }
//                }, "");
//                string = ((com.tencent.mm.openim.a.b) g.r(com.tencent.mm.openim.a.b.class)).a("3552365301", "openim_acct_type_title", com.tencent.mm.openim.a.b.a.TYPE_WORDING);
//                this.vNr.setVisibility(i);
//                this.vNr.setText(string);
//            }
//        }
//        if (s.fA(this.vNa, 16777216) && this.vNr != null) {
//            this.vNr.setBackgroundResource(R.g.comm_list_item_selector_no_divider);
//        } else if (s.fA(this.vNa, 2048)) {
//            if (this.vNq != null) {
//                this.vNq.setBackgroundResource(R.g.comm_list_item_selector_no_divider);
//            }
//        } else if (s.fA(this.vNa, 1024)) {
//            if (this.vNp != null) {
//                this.vNp.setBackgroundResource(R.g.comm_list_item_selector_no_divider);
//            }
//        } else if (s.fA(this.vNa, 512)) {
//            if (this.vNo != null) {
//                this.vNo.setBackgroundResource(R.g.comm_list_item_selector_no_divider);
//            }
//        } else if (s.fA(this.vNa, 256) && this.vNn != null) {
//            this.vNn.setBackgroundResource(R.g.comm_list_item_selector_no_divider);
//        }
//    }
//
//    private TextView a(ListView listView, OnClickListener onClickListener, String str) {
//        View inflate = com.tencent.mm.ui.y.gt(this).inflate(R.i.group_card_item, null);
//        inflate.setOnClickListener(onClickListener);
//        TextView textView = (TextView) inflate.findViewById(R.h.content_tv);
//        textView.setText(str);
//        listView.addHeaderView(inflate);
//        return textView;
//    }
//
//    private boolean em(final List<String> list) {
//        y.i("MicroMsg.SelectContactUI", "handleSelect %s", list);
//        if (!s.fA(this.vNa, GLIcon.RIGHT)) {
//            return en(list);
//        }
//        String string;
//        if (list != null) {
//            if (list.size() == 1) {
//                string = getString(R.l.select_contact_confirm_tips, new Object[]{r.gV((String) list.get(0))});
//            } else if (list.size() > 1) {
//                String string2 = getString(R.l.app_seperator_marker);
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < list.size(); i++) {
//                    if (i == 3) {
//                        stringBuilder.append("...");
//                        break;
//                    }
//                    stringBuilder.append(r.gV((String) list.get(i)));
//                    if (i < list.size() - 1) {
//                        stringBuilder.append(string2);
//                    }
//                }
//                string = getString(R.l.select_contact_confirm_tips, new Object[]{stringBuilder.toString()});
//            }
//            this.vNy = true;
//            h.a(this.mController.uMN, string, null, true, new DialogInterface.OnClickListener() {
//                public final void onClick(DialogInterface dialogInterface, int i) {
//                    SelectContactUI.this.vNy = SelectContactUI.this.en(list);
//                }
//            }, new DialogInterface.OnClickListener() {
//                public final void onClick(DialogInterface dialogInterface, int i) {
//                    SelectContactUI.this.vNy = false;
//                }
//            });
//            return this.vNy;
//        }
//        string = null;
//        this.vNy = true;
//        h.a(this.mController.uMN, string, null, true, /* anonymous class already generated */, /* anonymous class already generated */);
//        return this.vNy;
//    }
//
//    private boolean en(List<String> list) {
//        boolean z = false;
//        boolean fn;
//        Intent intent;
//        ArrayList nA;
//        Intent intent2;
//        if (getIntent().getBooleanExtra("Add_SendCard", false)) { //其他人分析是在这里响应,传值到com.tencent.mm.chatroom.ui.ChatroomInfoUI
//            if (bk.bl(this.vNu)) {
//                this.vNu = bk.c(list, ",");
//            } else if (bk.bl(this.vNv)) {
//                this.vNv = bk.c(list, ",");
//            } else {
//                y.e("MicroMsg.SelectContactUI", "send card occur error: send:%s | receive:%s", this.vNu, this.vNv);
//                return false;
//            }
//            fn = s.fn(this.vNv);
//            intent = new Intent();
//            intent.putExtra("be_send_card_name", this.vNu);
//            intent.putExtra("received_card_name", this.vNv);
//            intent.putExtra("Is_Chatroom", fn);
//            setResult(-1, intent);
//            finish();
//        } else if (getIntent().getBooleanExtra("snsPostWhoCanSee", false)) {
//            y.i("MicroMsg.SelectContactUI", "sns post who can see scene,users=%s", list.toString());
//            ArrayList nA2 = nA(false);
//            nA2.remove(q.Gj());
//            boolean z2 = false;
//            for (String str : list) {
//                if (!bk.bl(str)) {
//                    if (!(nA2.contains(str) || q.Gj().equals(str))) {
//                        au.Hx();
//                        ad abl = com.tencent.mm.model.c.Fw().abl(str);
//                        if (!(abl == null || ((int) abl.dBe) == 0 || !com.tencent.mm.n.a.gR(abl.field_type))) {
//                            adU(str);
//                            nA2.add(str);
//                            fn = true;
//                            z2 = fn;
//                        }
//                    }
//                }
//                fn = z2;
//                z2 = fn;
//            }
//            if (z2) {
//                xU();
//                cHE().notifyDataSetChanged();
//            } else {
//                nA = nA(false);
//                nA.remove(q.Gj());
//                final String c = bk.c(nA, ",");
//                if (bk.dk(nA)) {
//                    intent2 = new Intent();
//                    intent2.putExtra("Select_Contact", c);
//                    intent2.putExtra("Select_Conv_User", c);
//                    intent2.putExtra("Select_Contact", c);
//                    intent2.putExtra("Select_Contacts_To_Create_New_Label", c);
//                    setResult(0, intent2);
//                    finish();
//                } else {
//                    h.a(this.mController.uMN, true, getString(R.l.label_selected_contact_save_label), "", getString(R.l.label_selected_contact_save_btn), getString(R.l.label_selected_contact_cancel_btn), new DialogInterface.OnClickListener() {
//                        public final void onClick(DialogInterface dialogInterface, int i) {
//                            y.i("MicroMsg.SelectContactUI", "return the result,and create new label");
//                            Intent intent = new Intent();
//                            intent.putExtra("Select_Contact", c);
//                            intent.putExtra("Select_Conv_User", c);
//                            intent.putExtra("Select_Contact", c);
//                            intent.putExtra("Select_Contacts_To_Create_New_Label", c);
//                            SelectContactUI.this.setResult(-1, intent);
//                            SelectContactUI.this.finish();
//                        }
//                    }, new DialogInterface.OnClickListener() {
//                        public final void onClick(DialogInterface dialogInterface, int i) {
//                            y.i("MicroMsg.SelectContactUI", "return the result");
//                            Intent intent = new Intent();
//                            intent.putExtra("Select_Contact", c);
//                            intent.putExtra("Select_Conv_User", c);
//                            intent.putExtra("Select_Contact", c);
//                            SelectContactUI.this.setResult(-1, intent);
//                            SelectContactUI.this.finish();
//                        }
//                    });
//                }
//            }
//        } else if (getIntent().getBooleanExtra("recommend_friends", false)) {
//            y.i("MicroMsg.SelectContactUI", "Recommend Friends");
//            z.a(this.mController.uMN, this.vNv, list);
//        } else if (s.fA(this.vNa, 16384)) {
//            y.i("MicroMsg.SelectContactUI", "return the result");
//            intent2 = new Intent();
//            String c2 = bk.c(list, ",");
//            intent2.putExtra("Select_Contact", c2);
//            intent2.putExtra("Select_Conv_User", c2);
//            intent2.putExtra("Select_Contact", c2);
//            intent2.putExtra("label_source", this.mSource);
//            c2 = "Is_Chatroom";
//            if (this.bQU == 1) {
//                z = true;
//            }
//            intent2.putExtra(c2, z);
//            setResult(-1, intent2);
//            finish();
//        } else if (getIntent().getBooleanExtra("shareImage", false)) {
//            y.i("MicroMsg.SelectContactUI", "Share Image");
//            nA = nA(true);
//            nA.remove(q.Gj());
//            if (nA.size() > 0) {
//                this.dnm = h.b(this.mController.uMN, getString(R.l.main_sending), false, null);
//                au.DS().O(new Runnable() {
//                    public final void run() {
//                        String Gj = q.Gj();
//                        Iterator it = nA.iterator();
//                        int i = 0;
//                        String str = null;
//                        while (it.hasNext()) {
//                            String str2 = (String) it.next();
//                            y.d("MicroMsg.SelectContactUI", "toSend, %s", str2);
//                            int i2 = i + (s.fn(str2) ? 1 : 0);
//                            m lVar = new l(4, Gj, str2, SelectContactUI.this.getIntent().getStringExtra("shareImagePath"), 0, null, 0, "", str, true, R.g.chat_img_template);
//                            au.Dk().a(lVar, 0);
//                            if (lVar.bFH != null) {
//                                str = lVar.bFH.field_imgPath;
//                            }
//                            i = i2;
//                        }
//                        SelectContactUI.this.dnm.dismiss();
//                        Intent intent = new Intent();
//                        intent.putStringArrayListExtra("Select_Contact", nA);
//                        SelectContactUI.this.setResult(-1, intent);
//                        com.tencent.mm.plugin.report.service.h.nFQ.f(11048, Integer.valueOf(1), Integer.valueOf(nA.size() - i), Integer.valueOf(i));
//                        SelectContactUI.this.finish();
//                    }
//                });
//            }
//        } else if (list.size() > 0) {
//            y.i("MicroMsg.SelectContactUI", "Launch ChattingUI: users=%s", list.toString());
//            finish();
//            intent = new Intent();
//            intent.setClass(this, ChattingUI.class);
//            intent.putExtra("Chat_User", (String) list.get(0));
//            intent.addFlags(67108864);
//            startActivity(intent);
//        } else {
//            y.e("MicroMsg.SelectContactUI", "unkown action: User=%s", list.toString());
//        }
//        return true;
//    }
//
//    private void xU() {
//        if (!s.fA(this.vNa, 64) || this.eXT.size() <= 0) {
//            updateOptionMenuText(1, getString(R.l.app_ok));
//            enableOptionMenu(1, false);
//            return;
//        }
//        updateOptionMenuText(1, getString(R.l.app_ok) + "(" + this.eXT.size() + ")");
//        int intExtra = getIntent().getIntExtra("min_limit_num", 0);
//        if (!s.fA(this.vNa, 262144) || this.eXT.size() >= intExtra) {
//            enableOptionMenu(1, true);
//        } else {
//            enableOptionMenu(1, false);
//        }
//    }
//
//    private ArrayList<String> nA(boolean z) {
//        ArrayList arrayList = new ArrayList();
//        HashSet hashSet = new HashSet();
//        Iterator it = this.eXT.iterator();
//        while (it.hasNext()) {
//            String str = (String) it.next();
//            if (z || !s.hi(str)) {
//                hashSet.add(str);
//            } else {
//                List<String> gL = com.tencent.mm.model.m.gL(str);
//                if (gL != null) {
//                    for (String str2 : gL) {
//                        hashSet.add(str2);
//                    }
//                }
//            }
//        }
//        arrayList.addAll(hashSet);
//        return arrayList;
//    }
//
//    private ArrayList<String> nB(boolean z) {
//        ArrayList arrayList = new ArrayList();
//        HashSet hashSet = new HashSet();
//        Iterator it = this.eXT.iterator();
//        while (it.hasNext()) {
//            String str = (String) it.next();
//            if (z || !s.hi(str)) {
//                hashSet.add(str);
//            } else {
//                List<String> gL = com.tencent.mm.model.m.gL(str);
//                if (gL != null) {
//                    for (String str2 : gL) {
//                        hashSet.add(str2);
//                    }
//                }
//            }
//        }
//        hashSet.addAll(this.vMY);
//        arrayList.addAll(hashSet);
//        return arrayList;
//    }
//
//    private void a(final com.tencent.mm.roomsdk.a.c.a aVar) {
//        aVar.d(new c() {
//            public final /* synthetic */ void a(int i, int i2, String str, a aVar) {
//                c cVar = (c) aVar;
//                SelectContactUI.this.vNx = false;
//                if (!com.tencent.mm.ui.w.a.a(SelectContactUI.this, i, i2, str, 4)) {
//                    if (i == 0 && i2 == 0 && !bk.bl(this.chatroomName)) {
//                        if (aVar.cpy()) {
//                            com.tencent.mm.model.l.a(this.chatroomName, cVar.dmM, SelectContactUI.this.getString(R.l.chatroom_sys_msg_invite), false, "");
//                        }
//                        List list = cVar.bST;
//                        if (list != null && list.size() > 0) {
//                            LinkedList linkedList = new LinkedList();
//                            for (int i3 = 0; i3 < list.size(); i3++) {
//                                linkedList.add(list.get(i3));
//                            }
//                            String str2 = "weixin://findfriend/verifycontact/" + this.chatroomName + "/";
//                            if (aVar.cpy()) {
//                                com.tencent.mm.model.l.a(this.chatroomName, linkedList, SelectContactUI.this.getString(R.l.chatroom_sys_msg_invite_error_tip), true, str2);
//                            }
//                        }
//                        SelectContactUI.this.em(bk.G(new String[]{this.chatroomName}));
//                        return;
//                    }
//                    SelectContactUI.a(SelectContactUI.this, i, i2, cVar, str, aVar.cpy());
//                }
//            }
//        });
//        getString(R.l.app_tip);
//        aVar.a(this, getString(R.l.launchchatting_creating_chatroom), true, new OnCancelListener() {
//            public final void onCancel(DialogInterface dialogInterface) {
//                SelectContactUI.this.vNx = false;
//                aVar.cancel();
//            }
//        });
//    }
//
//    public void onSceneEnd(int i, int i2, String str, m mVar) {
//        if (this.dnm != null) {
//            this.dnm.dismiss();
//            this.dnm = null;
//        }
//        if (!bk.bU((Context) this) || com.tencent.mm.ui.w.a.a(this, i, i2, str, 4)) {
//            return;
//        }
//        if (i == 0 && i2 == 0) {
//            switch (mVar.getType()) {
//                case 138:
//                    cHE().notifyDataSetChanged();
//                    return;
//                default:
//                    return;
//            }
//        } else if (i == 4 && i2 == -24 && !bk.bl(str)) {
//            Toast.makeText(this, str, 1).show();
//        }
//    }
//
//    public final boolean a(com.tencent.mm.ui.contact.a.a aVar) {
//        if (aVar.vLJ && aVar.dnp != null) {
//            return this.eXT.contains(aVar.dnp.field_username);
//        }
//        if (aVar.vLK && aVar.dnp != null) {
//            return this.eXT.contains(aVar.dnp.field_username);
//        }
//        if (aVar instanceof j) {
//            return this.eXT.isEmpty();
//        }
//        return false;
//    }
//
//    public final boolean b(com.tencent.mm.ui.contact.a.a aVar) {
//        if (!aVar.vLJ || aVar.dnp == null) {
//            return false;
//        }
//        return this.vMY.contains(aVar.dnp.field_username);
//    }
//
//    public final int[] bgw() {
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(Integer.valueOf(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT));
//        arrayList.add(Integer.valueOf(131081));
//        if (!s.fA(this.vNa, 1)) {
//            arrayList.add(Integer.valueOf(131076));
//        }
//        if (!s.fA(this.vNa, 4)) {
//            arrayList.add(Integer.valueOf(131075));
//        }
//        int[] iArr = new int[arrayList.size()];
//        int i = 0;
//        while (true) {
//            int i2 = i;
//            if (i2 >= arrayList.size()) {
//                return iArr;
//            }
//            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
//            i = i2 + 1;
//        }
//    }
//
//    public final void pw(String str) {
//        this.eXT.remove(str);
//        cHE().notifyDataSetChanged();
//        xU();
//    }
//
//    private void adU(String str) {
//        if (!this.vMY.contains(str)) {
//            cHK();
//            this.mbR.Wv(str);
//            if (this.eXT.contains(str)) {
//                this.eXT.remove(str);
//            } else {
//                this.eXT.add(str);
//            }
//        }
//    }
//
//    private static List<String> F(List<String> list) {
//        LinkedList linkedList = new LinkedList();
//        if (!au.DK()) {
//            return linkedList;
//        }
//        if (list == null) {
//            return linkedList;
//        }
//        for (Object obj : list) {
//            Object obj2;
//            au.Hx();
//            ad abl = com.tencent.mm.model.c.Fw().abl(obj2);
//            if (!(abl == null || ((int) abl.dBe) == 0)) {
//                obj2 = abl.Bq();
//            }
//            linkedList.add(obj2);
//        }
//        return linkedList;
//    }
//
//    protected void onActivityResult(int i, int i2, Intent intent) {
//        super.onActivityResult(i, i2, intent);
//        this.bQU = i;
//        y.i("MicroMsg.SelectContactUI", "requestCode=%d | resultCode=%d", Integer.valueOf(i), Integer.valueOf(i2));
//        if (i2 == -1) {
//            String stringExtra;
//            Iterator it;
//            switch (i) {
//                case 0:
//                    if (!bk.bl(intent.getStringExtra("Select_Conv_User"))) {
//                        em(bk.G(new String[]{intent.getStringExtra("Select_Conv_User")}));
//                        return;
//                    }
//                    return;
//                case 1:
//                    stringExtra = intent.getStringExtra("Select_Contact");
//                    if (!bk.bl(stringExtra)) {
//                        em(bk.G(stringExtra.split(",")));
//                        return;
//                    }
//                    return;
//                case 2:
//                    if (intent != null) {
//                        if (!bk.bl(intent.getStringExtra("Select_Contact"))) {
//                            em(bk.G(new String[]{intent.getStringExtra("Select_Contact")}));
//                            return;
//                        }
//                        return;
//                    }
//                    return;
//                case 3:
//                    stringExtra = intent.getStringExtra("Select_Contact");
//                    if (bk.bl(stringExtra)) {
//                        y.i("MicroMsg.SelectContactUI", "GET_LABEL_USERS return usernames is null or empty");
//                        return;
//                    }
//                    y.i("MicroMsg.SelectContactUI", "GET_LABEL_USERS select username=%s", stringExtra);
//                    if (s.fA(this.vNa, 64)) {
//                        for (Object obj : stringExtra.split(",")) {
//                            if (this.eXT.add(obj)) {
//                                this.mbR.Wv(obj);
//                            }
//                        }
//                        xU();
//                        cHE().notifyDataSetChanged();
//                        return;
//                    }
//                    em(bk.G(new String[]{stringExtra}));
//                    return;
//                case 4:
//                    String stringExtra2 = intent.getStringExtra("Select_Conv_User");
//                    Iterator it2 = this.eXT.iterator();
//                    while (it2.hasNext()) {
//                        this.mbR.Ww((String) it2.next());
//                    }
//                    this.eXT.clear();
//                    if (!bk.bl(stringExtra2)) {
//                        this.eXT.addAll(bk.G(stringExtra2.split(",")));
//                    }
//                    it = this.eXT.iterator();
//                    while (it.hasNext()) {
//                        this.mbR.bz((String) it.next(), false);
//                    }
//                    xU();
//                    return;
//                case 5:
//                    if (intent != null) {
//                        String stringExtra3 = intent.getStringExtra("Select_Contact");
//                        String stringExtra4 = intent.getStringExtra("Cancel_Select_Contact");
//                        List<String> gg = bk.bl(stringExtra3) ? null : bk.gg(stringExtra3, ",");
//                        List<String> gg2 = bk.bl(stringExtra4) ? null : bk.gg(stringExtra4, ",");
//                        if (gg != null) {
//                            if (this.mbR != null) {
//                                for (String stringExtra5 : gg) {
//                                    if (!this.eXT.contains(stringExtra5)) {
//                                        this.mbR.Wv(stringExtra5);
//                                    }
//                                }
//                            }
//                            this.eXT.addAll(gg);
//                        }
//                        if (gg2 != null) {
//                            if (this.mbR != null) {
//                                for (String stringExtra52 : gg2) {
//                                    this.mbR.Wv(stringExtra52);
//                                }
//                            }
//                            this.eXT.removeAll(gg2);
//                        }
//                        cHE().notifyDataSetChanged();
//                        if (getIntent().getBooleanExtra("Add_SendCard", false)) {
//                            em(bk.G(new String[]{stringExtra3}));
//                        }
//                    }
//                    xU();
//                    return;
//                default:
//                    return;
//            }
//        }
//    }
//
//    protected final boolean bhB() {
//        if (this.vNz) {
//            return false;
//        }
//        return true;
//    }
//
//    protected final void Ha(String str) {
//        com.tencent.mm.plugin.report.service.h.nFQ.f(11225, Integer.valueOf(1), Integer.valueOf(0));
//        Intent intent = new Intent();
//        intent.setClassName(this, "com.tencent.mm.ui.contact.SelectLabelContactUI");
//        intent.putExtra("label", str);
//        HashSet hashSet = new HashSet();
//        hashSet.addAll(nA(s.fA(this.vNa, 8192)));
//        hashSet.addAll(this.vMY);
//        intent.putExtra("always_select_contact", bk.c(new ArrayList(hashSet), ","));
//        intent.putExtra("always_select_contact", bk.c(new ArrayList(hashSet), ","));
//        if (s.fA(this.vNa, 64)) {
//            intent.putExtra("list_attr", s.v(16384, 64, WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT));
//            if (this.vIY == 14) {
//                intent.putExtra("max_limit_num", getIntent().getIntExtra("max_limit_num", Integer.MAX_VALUE));
//            }
//        } else {
//            intent.putExtra("list_attr", 16384);
//        }
//        startActivityForResult(intent, 3);
//    }
//}