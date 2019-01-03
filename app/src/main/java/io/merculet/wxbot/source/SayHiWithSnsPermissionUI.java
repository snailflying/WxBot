//package io.merculet.wxbot.config;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextPaint;
//import android.text.method.LinkMovementMethod;
//import android.text.style.ClickableSpan;
//import android.view.KeyEvent;
//import android.view.MenuItem;
//import android.view.MenuItem.OnMenuItemClickListener;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnFocusChangeListener;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.tencent.mm.R;
//import com.tencent.mm.ah.f;
//import com.tencent.mm.br.d;
//import com.tencent.mm.h.a.qo;
//import com.tencent.mm.model.c;
//import com.tencent.mm.model.q;
//import com.tencent.mm.openim.b.o;
//import com.tencent.mm.openim.b.p;
//import com.tencent.mm.plugin.appbrand.jsapi.i.i;
//import com.tencent.mm.plugin.report.service.h;
//import com.tencent.mm.pluginsdk.ui.d.j;
//import com.tencent.mm.pluginsdk.ui.d.k;
//import com.tencent.mm.sdk.e.m;
//import com.tencent.mm.sdk.e.m.b;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.ad;
//import com.tencent.mm.storage.ax;
//import com.tencent.mm.storage.bv;
//import com.tencent.mm.ui.MMActivity;
//import com.tencent.mm.ui.base.MMTagPanel;
//import com.tencent.mm.ui.s;
//import com.tencent.mm.ui.widget.MMSwitchBtn;
//import com.tencent.mm.ui.x;
//import com.tencent.tmassistantsdk.openSDK.QQDownloader.DownloadResult;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import org.xwalk.core.XWalkUpdater;
//
///**
// * @Description 添加好友  点完成即添加
// * @Author sean
// * @Email xiao.lu@magicwindow.cn
// * @Date 2019/1/3 11:48 AM
// * @Version
// */
//public class SayHiWithSnsPermissionUI extends MMActivity implements f {
//    private String aVr;
//    private String aVs;
//    private String chatroomName;
//    private ProgressDialog dnm = null;
//    private int fhj;
//    private int[] fmY = new int[8];
//    private String lCb;
//    private String mCz;
//    private EditText mZb;
//    private EditText mZc;
//    private View mZd;
//    private TextView mZe;
//    private MMSwitchBtn mZf;
//    private boolean mZg;
//    private boolean mZh;
//    private boolean mZi;
//    private TextView mZj;
//    private MMTagPanel mZk;
//    private List<String> mZl;
//    private b mZm = new b() {
//        public final void a(int i, m mVar, Object obj) {
//            SayHiWithSnsPermissionUI.this.bsQ();
//        }
//    };
//    private CharSequence mZn = null;
//    private String userName;
//
//    private class a extends ClickableSpan {
//        public String fGK;
//
//        public a(String str) {
//            this.fGK = str;
//        }
//
//        public final void onClick(View view) {
//            SayHiWithSnsPermissionUI.this.mZc.setText(j.a(SayHiWithSnsPermissionUI.this, bk.pm(this.fGK), SayHiWithSnsPermissionUI.this.mZc.getTextSize()));
//            SayHiWithSnsPermissionUI.this.mZc.setSelection(SayHiWithSnsPermissionUI.this.mZc.getText().length());
//            SayHiWithSnsPermissionUI.this.mZd.setVisibility(8);
//            SayHiWithSnsPermissionUI.this.fmY[4] = 1;
//        }
//
//        public final void updateDrawState(TextPaint textPaint) {
//            textPaint.setColor(SayHiWithSnsPermissionUI.this.getResources().getColor(R.e.blue_text_color));
//            textPaint.setUnderlineText(false);
//        }
//    }
//
//    static /* synthetic */ void c(SayHiWithSnsPermissionUI sayHiWithSnsPermissionUI) {
//        Intent intent = new Intent();
//        intent.putExtra("label_id_list", sayHiWithSnsPermissionUI.lCb);
//        if (sayHiWithSnsPermissionUI.mZl != null) {
//            intent.putStringArrayListExtra("label_str_list", (ArrayList) sayHiWithSnsPermissionUI.mZl);
//        }
//        intent.putExtra("label_username", sayHiWithSnsPermissionUI.userName);
//        intent.putExtra("is_stranger", true);
//        d.b(sayHiWithSnsPermissionUI, "label", ".ui.ContactLabelUI", intent);
//    }
//
//    static /* synthetic */ String h(SayHiWithSnsPermissionUI sayHiWithSnsPermissionUI) {
//        String trim = sayHiWithSnsPermissionUI.mZb.getText().toString().trim();
//        return trim.length() <= 50 ? trim : trim.substring(0, 50);
//    }
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        initView();
//    }
//
//    protected final int getLayoutId() {
//        return R.i.say_hi_with_sns_permission;
//    }
//
//    protected void onResume() {
//        if (this.mZi) {
//            au.Hx();
//            c.Fw().a(this.mZm);
//            bsQ();
//        }
//        au.Dk().a(30, (f) this);
//        au.Dk().a((int) i.CTRL_INDEX, (f) this);
//        au.Dk().a(853, (f) this);
//        super.onResume();
//    }
//
//    public void onStop() {
//        au.Dk().b(30, (f) this);
//        au.Dk().b((int) i.CTRL_INDEX, (f) this);
//        au.Dk().b(853, (f) this);
//        if (this.mZi) {
//            au.Hx();
//            c.Fw().b(this.mZm);
//        }
//        super.onStop();
//    }
//
//    protected void onDestroy() {
//        h.nFQ.f(14036, Integer.valueOf(this.fmY[0]), Integer.valueOf(this.fmY[1]), Integer.valueOf(this.fmY[2]), Integer.valueOf(this.fmY[3]), Integer.valueOf(this.fmY[4]), Integer.valueOf(this.fmY[5]), Integer.valueOf(this.fmY[6]), Integer.valueOf(this.fmY[7]));
//        super.onDestroy();
//    }
//
//    protected final void initView() {
//        String string;
//        String str;
//        this.mZf = (MMSwitchBtn) findViewById(R.h.not_allow_see_permission).findViewById(R.h.checkbox);
//        this.mZf.setCheck(false);
//        this.mZg = getIntent().getBooleanExtra("sayhi_with_sns_perm_send_verify", false);
//        this.mZh = getIntent().getBooleanExtra("sayhi_with_sns_perm_add_remark", false);
//        this.mZi = getIntent().getBooleanExtra("sayhi_with_sns_perm_set_label", false);
//        this.userName = getIntent().getStringExtra("Contact_User");
//        this.fhj = getIntent().getIntExtra("Contact_Scene", 9);
//        this.chatroomName = getIntent().getStringExtra("room_name");
//        this.aVs = getIntent().getStringExtra("Contact_RemarkName");
//        this.aVr = getIntent().getStringExtra("Contact_Nick");
//        this.mCz = getIntent().getStringExtra("Contact_RoomNickname");
//        this.mZd = findViewById(R.h.mod_remark_recommend_name_area);
//        this.mZe = (TextView) findViewById(R.h.mod_remark_recommend_tip);
//        setMMTitle(getString(R.l.app_name));
//        if (ad.aaU(this.userName)) {
//            View findViewById = findViewById(R.h.not_allow_see_permission_layout);
//            if (findViewById != null) {
//                findViewById.setVisibility(8);
//            }
//        }
//        if (this.mZg) {
//            setMMTitle(getString(R.l.sendrequest_title));
//            this.mZb = (EditText) findViewById(R.h.say_hi_content);
//            this.mZb.setMinHeight(this.mController.uMN.getResources().getDimensionPixelSize(R.f.MMClearEditTextMinHeight));
//            com.tencent.mm.ui.tools.a.c.d(this.mZb).Ig(100).a(null);
//            this.mZb.setFilters(com.tencent.mm.pluginsdk.ui.tools.h.smJ);
//            ((LinearLayout) this.mZb.getParent()).setVisibility(0);
//            au.Hx();
//            CharSequence charSequence = (String) c.Dz().get(294913, null);
//            String Gl = q.Gl();
//            if (Gl == null) {
//                Gl = "";
//            }
//            string = getString(R.l.sendgreeting_content);
//            if (string.length() + Gl.length() > 50) {
//                Gl = Gl.substring(0, 50 - string.length());
//            }
//            this.mZn = j.a(this.mController.uMN, String.format(string, new Object[]{Gl}), this.mZb.getTextSize());
//            if (bk.bl(charSequence)) {
//                this.mZb.setText(this.mZn);
//            } else {
//                this.mZb.setText(j.a(this.mController.uMN, charSequence, this.mZb.getTextSize()));
//            }
//            this.mZb.requestFocus();
//            this.mZb.postDelayed(new Runnable() {
//                public final void run() {
//                    SayHiWithSnsPermissionUI.this.showVKB();
//                }
//            }, 100);
//        }
//        if (this.mZh) {
//            this.mZc = (EditText) findViewById(R.h.say_hi_remark);
//            ((LinearLayout) this.mZc.getParent()).setVisibility(0);
//            if (!this.mZg) {
//                this.mZc.clearFocus();
//            }
//            this.mZc.setMinHeight(this.mController.uMN.getResources().getDimensionPixelSize(R.f.MMClearEditTextMinHeight));
//            com.tencent.mm.ui.tools.a.c.d(this.mZc).Ig(100).a(null);
//            this.mZc.setFilters(com.tencent.mm.pluginsdk.ui.tools.h.smJ);
//            if (!this.mZg) {
//                this.fmY[0] = 1;
//                setMMTitle(getString(R.l.contact_verify_title));
//                LayoutParams layoutParams = (LayoutParams) ((View) this.mZc.getParent()).getLayoutParams();
//                layoutParams.setMargins(layoutParams.leftMargin, 0, layoutParams.rightMargin, layoutParams.bottomMargin);
//            }
//            if (bk.bl(this.aVs)) {
//                k kVar;
//                if (!bk.bl(this.aVr)) {
//                    this.mZc.setHint(j.a(this.mController.uMN, this.aVr, this.mZc.getTextSize()));
//                    this.mZc.setOnFocusChangeListener(new OnFocusChangeListener() {
//                        public final void onFocusChange(View view, boolean z) {
//                            if (z && !bk.L(SayHiWithSnsPermissionUI.this.mZc.getHint()) && bk.L(SayHiWithSnsPermissionUI.this.mZc.getText())) {
//                                SayHiWithSnsPermissionUI.this.mZc.setText(SayHiWithSnsPermissionUI.this.mZc.getHint());
//                                SayHiWithSnsPermissionUI.this.mZc.setOnFocusChangeListener(null);
//                                SayHiWithSnsPermissionUI.this.mZc.setHint(null);
//                            }
//                        }
//                    });
//                }
//                boolean z;
//                switch (this.fhj) {
//                    case 8:
//                    case 14:
//                        if (!bk.bl(this.mCz) && !this.mCz.equals(this.mZc.getText().toString())) {
//                            this.mZd.setVisibility(0);
//                            this.mZe.setText(j.a((Context) this, bk.pm(getString(R.l.contact_info_set_reamrk_chatroom_name, new Object[]{this.mCz})), this.mZe.getTextSize()));
//                            k kVar2 = new k(getString(R.l.write_contact_remark));
//                            kVar2.setSpan(new a(this.mCz), 0, kVar2.length(), 17);
//                            this.mZe.append(" ");
//                            this.mZe.append(kVar2);
//                            this.mZe.setMovementMethod(LinkMovementMethod.getInstance());
//                            this.fmY[3] = 2;
//                            z = true;
//                            break;
//                        }
//                        z = false;
//                        break;
//                        break;
//                    case 10:
//                    case 11:
//                    case 13:
//                        com.tencent.mm.plugin.account.friend.a.a pH = com.tencent.mm.plugin.account.b.getAddrUploadStg().pH(this.userName);
//                        if (pH != null && !bk.bl(pH.Wx()) && !pH.Wx().equals(this.mZc.getText().toString())) {
//                            this.mZd.setVisibility(0);
//                            this.mZe.setText(j.a((Context) this, bk.pm(getString(R.l.contact_info_set_reamrk_mobile_name, new Object[]{pH.Wx()})), this.mZe.getTextSize()));
//                            kVar = new k(getString(R.l.write_contact_remark));
//                            kVar.setSpan(new a(pH.Wx()), 0, kVar.length(), 17);
//                            this.mZe.append(" ");
//                            this.mZe.append(kVar);
//                            this.mZe.setMovementMethod(LinkMovementMethod.getInstance());
//                            this.fmY[3] = 1;
//                            z = true;
//                            break;
//                        }
//                        z = false;
//                        break;
//                        break;
//                    default:
//                        z = false;
//                        break;
//                }
//                if (!(this.mZg || z)) {
//                    ax abU = com.tencent.mm.bh.d.RX().abU(this.userName);
//                    if (abU != null) {
//                        com.tencent.mm.pluginsdk.ui.preference.b a = com.tencent.mm.pluginsdk.ui.preference.b.a((Context) this, abU);
//                        if (!(bk.bl(a.dTF) || a.dTF.equals(getString(R.l.fmessage_from_verify_digest_tip)))) {
//                            string = getString(R.l.sendgreeting_content).substring(0, getString(R.l.sendgreeting_content).indexOf("%s"));
//                            str = a.dTF;
//                            if (a.dTF.startsWith(string)) {
//                                str = a.dTF.substring(string.length());
//                            }
//                            this.mZd.setVisibility(0);
//                            this.mZe.setText(j.a((Context) this, bk.pm(getString(R.l.contact_info_set_remark_verify_info, new Object[]{a.dTF})), this.mZe.getTextSize()));
//                            kVar = new k(getString(R.l.write_contact_remark));
//                            kVar.setSpan(new a(str), 0, kVar.length(), 17);
//                            this.mZe.append(" ");
//                            this.mZe.append(kVar);
//                            this.mZe.setMovementMethod(LinkMovementMethod.getInstance());
//                            this.fmY[3] = 3;
//                        }
//                    }
//                }
//            } else {
//                this.mZc.setText(j.a(this.mController.uMN, this.aVs, this.mZc.getTextSize()));
//                this.fmY[6] = 1;
//            }
//        }
//        if (ad.aaU(this.userName)) {
//            this.mZi = false;
//        }
//        if (this.mZi) {
//            this.mZj = (TextView) findViewById(R.h.mod_label_edit_text);
//            this.mZk = (MMTagPanel) findViewById(R.h.contact_label_display_panel);
//            this.mZk.setPanelClickable(false);
//            ((LinearLayout) ((FrameLayout) this.mZk.getParent()).getParent()).setVisibility(0);
//            this.mZj.setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    SayHiWithSnsPermissionUI.c(SayHiWithSnsPermissionUI.this);
//                }
//            });
//            this.mZk.setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    SayHiWithSnsPermissionUI.c(SayHiWithSnsPermissionUI.this);
//                }
//            });
//        }
//        bsQ();
//        if (!bk.bl(this.lCb)) {
//            this.fmY[7] = 1;
//        }
//        str = getString(R.l.app_send);
//        if (!this.mZg) {
//            str = getString(R.l.app_finish);
//        }
//        a(0, str, (OnMenuItemClickListener) new OnMenuItemClickListener() {
//            public final boolean onMenuItemClick(MenuItem menuItem) {
//                if (SayHiWithSnsPermissionUI.this.dnm == null || !SayHiWithSnsPermissionUI.this.dnm.isShowing()) {
//                    final com.tencent.mm.ah.m oVar;
//                    SayHiWithSnsPermissionUI sayHiWithSnsPermissionUI;
//                    Context context;
//                    if (SayHiWithSnsPermissionUI.this.mZg) {
//                        int i;
//                        LinkedList linkedList = new LinkedList();
//                        linkedList.add(SayHiWithSnsPermissionUI.this.userName);
//                        LinkedList linkedList2 = new LinkedList();
//                        linkedList2.add(Integer.valueOf(SayHiWithSnsPermissionUI.this.fhj));
//                        String h = SayHiWithSnsPermissionUI.h(SayHiWithSnsPermissionUI.this);
//                        HashMap hashMap = new HashMap();
//                        if (SayHiWithSnsPermissionUI.this.mZf.wjS) {
//                            i = 1;
//                        } else {
//                            i = 0;
//                        }
//                        hashMap.put(SayHiWithSnsPermissionUI.this.userName, Integer.valueOf(i));
//                        y.d("MicroMsg.SayHiWithSnsPermissionUI", "select sns permission, %s", Integer.valueOf(i));
//                        if (ad.aaU(SayHiWithSnsPermissionUI.this.userName)) {
//                            oVar = new o(SayHiWithSnsPermissionUI.this.userName, h, SayHiWithSnsPermissionUI.this.getIntent().getStringExtra(com.tencent.mm.ui.e.a.uHO));
//                            au.Dk().a(oVar, 0);
//                            sayHiWithSnsPermissionUI = SayHiWithSnsPermissionUI.this;
//                            context = SayHiWithSnsPermissionUI.this.mController.uMN;
//                            SayHiWithSnsPermissionUI.this.getString(R.l.app_tip);
//                            sayHiWithSnsPermissionUI.dnm = com.tencent.mm.ui.base.h.b(context, SayHiWithSnsPermissionUI.this.getString(R.l.sendrequest_sending), true, new OnCancelListener() {
//                                public final void onCancel(DialogInterface dialogInterface) {
//                                    au.Dk().c(oVar);
//                                }
//                            });
//                        } else {
//                            final com.tencent.mm.ah.m mVar = new com.tencent.mm.pluginsdk.model.m(linkedList, linkedList2, h, "", hashMap, SayHiWithSnsPermissionUI.this.chatroomName);
//                            String stringExtra = SayHiWithSnsPermissionUI.this.getIntent().getStringExtra("source_from_user_name");
//                            String stringExtra2 = SayHiWithSnsPermissionUI.this.getIntent().getStringExtra("source_from_nick_name");
//                            if (!bk.bl(stringExtra)) {
//                                mVar.fP(stringExtra, stringExtra2);
//                            }
//                            au.Dk().a(mVar, 0);
//                            SayHiWithSnsPermissionUI sayHiWithSnsPermissionUI2 = SayHiWithSnsPermissionUI.this;
//                            context = SayHiWithSnsPermissionUI.this.mController.uMN;
//                            SayHiWithSnsPermissionUI.this.getString(R.l.app_tip);
//                            sayHiWithSnsPermissionUI2.dnm = com.tencent.mm.ui.base.h.b(context, SayHiWithSnsPermissionUI.this.getString(R.l.sendrequest_sending), true, new OnCancelListener() {
//                                public final void onCancel(DialogInterface dialogInterface) {
//                                    au.Dk().c(mVar);
//                                }
//                            });
//                        }
//                    } else if (SayHiWithSnsPermissionUI.this.mZh) {
//                        String stringExtra3 = SayHiWithSnsPermissionUI.this.getIntent().getStringExtra("Verify_ticket");
//                        if (ad.aaU(SayHiWithSnsPermissionUI.this.userName)) {
//                            oVar = new p(SayHiWithSnsPermissionUI.this.userName, stringExtra3);
//                            au.Dk().a(oVar, 0);
//                            sayHiWithSnsPermissionUI = SayHiWithSnsPermissionUI.this;
//                            context = SayHiWithSnsPermissionUI.this.mController.uMN;
//                            SayHiWithSnsPermissionUI.this.getString(R.l.app_tip);
//                            sayHiWithSnsPermissionUI.dnm = com.tencent.mm.ui.base.h.b(context, SayHiWithSnsPermissionUI.this.getString(R.l.contact_info_dealing_verify), true, new OnCancelListener() {
//                                public final void onCancel(DialogInterface dialogInterface) {
//                                    au.Dk().c(oVar);
//                                }
//                            });
//                        } else {
//                            //自动同意添加还有关键代码au.Dk().a(oVar, 0)调用此方法即可
//                            oVar = new com.tencent.mm.pluginsdk.model.m(SayHiWithSnsPermissionUI.this.userName, stringExtra3, SayHiWithSnsPermissionUI.this.fhj, (byte) 0);
//                            au.Dk().a(oVar, 0);   //调用此方法
//                            sayHiWithSnsPermissionUI = SayHiWithSnsPermissionUI.this;
//                            context = SayHiWithSnsPermissionUI.this.mController.uMN;
//                            SayHiWithSnsPermissionUI.this.getString(R.l.app_tip);
//                            sayHiWithSnsPermissionUI.dnm = com.tencent.mm.ui.base.h.b(context, SayHiWithSnsPermissionUI.this.getString(R.l.contact_info_dealing_verify), true, new OnCancelListener() {
//                                public final void onCancel(DialogInterface dialogInterface) {
//                                    au.Dk().c(oVar);
//                                }
//                            });
//                        }
//                    }
//                }
//                return false;
//            }
//        }, s.b.GREEN);
//        setBackBtn(new OnMenuItemClickListener() {
//            public final boolean onMenuItemClick(MenuItem menuItem) {
//                if (SayHiWithSnsPermissionUI.this.mZh) {
//                    SayHiWithSnsPermissionUI.this.fmY[1] = 1;
//                }
//                SayHiWithSnsPermissionUI.this.finish();
//                return true;
//            }
//        });
//    }
//
//    private void bsQ() {
//        au.Hx();
//        bv Id = c.Fx().Id(this.userName);
//        if (Id != null) {
//            this.lCb = Id.field_contactLabels;
//            this.mZl = (ArrayList) com.tencent.mm.plugin.label.a.a.bdA().Gm(this.lCb);
//        }
//        if (!this.mZi) {
//            return;
//        }
//        if (bk.bl(this.lCb)) {
//            this.mZk.setVisibility(4);
//            this.mZj.setVisibility(0);
//            return;
//        }
//        this.mZk.setVisibility(0);
//        this.mZj.setVisibility(4);
//        this.mZk.a(this.mZl, this.mZl);
//    }
//
//    public void onSceneEnd(int i, int i2, String str, com.tencent.mm.ah.m mVar) {
//        long currentTimeMillis = System.currentTimeMillis();
//        y.i("MicroMsg.SayHiWithSnsPermissionUI", "onSceneEnd: errType = " + i + " errCode = " + i2 + " errMsg = " + str);
//        try {
//            String trim;
//            if (this.dnm != null) {
//                this.dnm.dismiss();
//                this.dnm = null;
//            }
//            if (this.mZg) {
//                int i3;
//                trim = this.mZb.getText().toString().trim();
//                if (bk.bl(trim) || trim.equals(this.mZn)) {
//                    au.Hx();
//                    c.Dz().o(294913, "");
//                } else {
//                    au.Hx();
//                    c.Dz().o(294913, trim);
//                }
//                switch (i2) {
//                    case -34:
//                    case DownloadResult.CODE_CONNECTION_EXCEPTION /*-24*/:
//                        Toast.makeText(this, R.l.fmessage_request_too_offen, 0).show();
//                        i3 = 1;
//                        break;
//                    case DownloadResult.CODE_CLIENT_PROTOCOL_EXCEPTION /*-22*/:
//                        Toast.makeText(this, R.l.sendrequest_send_fail, 0).show();
//                        i3 = 1;
//                        break;
//                    default:
//                        i3 = 0;
//                        break;
//                }
//                if (i3 != 0) {
//                    y.i("MicroMsg.SayHiWithSnsPermissionUI", "[onCreate] %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//                    return;
//                }
//            }
//            if (i == 0 && i2 == 0) {
//                com.tencent.mm.ui.base.h.bC(this, getString(R.l.sendrequest_send_success));
//                if (this.mZh) {
//                    int i4;
//                    trim = "";
//                    if (this.mZc.getText() != null) {
//                        trim = this.mZc.getText().toString();
//                    }
//                    if (!bk.bl(trim) && trim.length() > 50) {
//                        trim = trim.substring(0, 50);
//                    }
//                    if (!bk.bl(trim)) {
//                        au.Hx();
//                        ad abl = c.Fw().abl(this.userName);
//                        abl.df(trim);
//                        au.Hx();
//                        bv Id = c.Fx().Id(this.userName);
//                        Id.field_encryptUsername = this.userName;
//                        Id.field_conRemark = trim;
//                        au.Hx();
//                        c.Fx().a(Id);
//                        au.Hx();
//                        c.Fw().U(abl);
//                        this.fmY[2] = 1;
//                        if (!(bk.bl(this.aVr) || trim.equals(this.aVr))) {
//                            this.fmY[5] = 1;
//                        }
//                    } else if (bk.bl(this.aVr)) {
//                        this.fmY[2] = 2;
//                    } else {
//                        this.fmY[2] = 0;
//                    }
//                    List linkedList = new LinkedList();
//                    if (mVar instanceof com.tencent.mm.pluginsdk.model.m) {
//                        i4 = ((com.tencent.mm.pluginsdk.model.m) mVar).bNb;
//                        linkedList = ((com.tencent.mm.pluginsdk.model.m) mVar).rTv;
//                    } else {
//                        if (mVar instanceof p) {
//                            linkedList.add(((p) mVar).ePQ);
//                        }
//                        i4 = 0;
//                    }
//                    if (i4 == 3 || (mVar instanceof p)) {
//                        ax abU = com.tencent.mm.bh.d.RX().abU(this.userName);
//                        au.Hx();
//                        ad abl2 = c.Fw().abl(this.userName);
//                        if (linkedList != null && linkedList.contains(this.userName)) {
//                            if (((int) abl2.dBe) == 0) {
//                                abl2 = com.tencent.mm.pluginsdk.ui.preference.b.a(abU);
//                                au.Hx();
//                                if (!c.Fw().V(abl2)) {
//                                    y.e("MicroMsg.SayHiWithSnsPermissionUI", "canAddContact fail, insert fail");
//                                }
//                            }
//                            com.tencent.mm.model.s.q(abl2);
//                            au.getNotification().wp();
//                            com.tencent.mm.bh.d.RY().du(this.userName, 1);
//                            com.tencent.mm.pluginsdk.ui.preference.b.bh(this.userName, this.fhj);
//                        }
//                        au.Hx();
//                        abl2 = c.Fw().abl(this.userName);
//                        qo qoVar = new qo();
//                        qoVar.bZP.bZR = true;
//                        qoVar.bZP.bZQ = false;
//                        qoVar.bZP.username = this.userName;
//                        com.tencent.mm.sdk.b.a.udP.m(qoVar);
//                        if (this.mZf.wjS) {
//                            com.tencent.mm.model.s.k(abl2);
//                        } else {
//                            com.tencent.mm.model.s.l(abl2);
//                        }
//                        if (getIntent().getBooleanExtra("sayhi_with_jump_to_profile", false)) {
//                            Intent intent = new Intent();
//                            intent.putExtra("friend_message_transfer_username", this.userName);
//                            intent.setAction("friend_message_accept_" + this.userName);
//                            intent.putExtra(x.FLAG_OVERRIDE_ENTER_ANIMATION, R.a.slide_right_in);
//                            intent.putExtra(x.FLAG_OVERRIDE_EXIT_ANIMATION, R.a.slide_left_out);
//                            d.b(this, "subapp", ".ui.friend.FMessageTransferUI", intent);
//                        }
//                    }
//                }
//                getIntent().putExtra("CONTACT_INFO_UI_SOURCE", 7);
//                h.nFQ.f(16055, Integer.valueOf(1), Integer.valueOf(com.tencent.mm.plugin.profile.b.S(getIntent())));
//                setResult(-1, getIntent());
//                finish();
//            } else if (i == 4 && i2 == -24 && !bk.bl(str)) {
//                Toast.makeText(this, str, 1).show();
//            } else if (i != 4 || (!(i2 == -2 || i2 == XWalkUpdater.ERROR_SET_VERNUM) || bk.bl(str))) {
//                Toast.makeText(this, R.l.sendrequest_send_fail, 0).show();
//            } else {
//                com.tencent.mm.ui.base.h.a((Context) this, str, getString(R.l.app_tip), getString(R.l.app_ok), null);
//            }
//            y.i("MicroMsg.SayHiWithSnsPermissionUI", "[onCreate] %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//        } catch (Exception e) {
//            y.e("MicroMsg.SayHiWithSnsPermissionUI", "exception in onSceneEnd : " + e.getMessage());
//            y.i("MicroMsg.SayHiWithSnsPermissionUI", "[onCreate] %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//        } catch (Throwable th) {
//            y.i("MicroMsg.SayHiWithSnsPermissionUI", "[onCreate] %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
//        }
//    }
//
//    public boolean onKeyDown(int i, KeyEvent keyEvent) {
//        if (i != 4) {
//            return super.onKeyDown(i, keyEvent);
//        }
//        this.fmY[0] = 1;
//        finish();
//        return true;
//    }
//}