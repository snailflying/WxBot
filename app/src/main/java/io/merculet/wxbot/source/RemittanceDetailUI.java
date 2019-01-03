//package io.merculet.wxbot.config;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.MenuItem.OnMenuItemClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.tencent.mm.ah.m;
//import com.tencent.mm.br.d;
//import com.tencent.mm.h.a.nl;
//import com.tencent.mm.plugin.remittance.model.u;
//import com.tencent.mm.plugin.remittance.model.x;
//import com.tencent.mm.plugin.wxpay.a.e;
//import com.tencent.mm.plugin.wxpay.a.f;
//import com.tencent.mm.plugin.wxpay.a.g;
//import com.tencent.mm.plugin.wxpay.a.i;
//import com.tencent.mm.sdk.b.a;
//import com.tencent.mm.sdk.b.b;
//import com.tencent.mm.sdk.b.c;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.ui.base.h;
//import com.tencent.mm.wallet_core.ui.WalletBaseUI;
//
//public class RemittanceDetailUI extends WalletBaseUI {
//    public TextView lUL = null;
//    private String mPo;
//    private String mPv;
//    private String nAq = null;
//    private int nAr;
//    public String nAx = null;
//    public ImageView nCV = null;
//    public TextView nCW = null;
//    public TextView nCX = null;
//    public Button nCY = null;
//    public TextView nCZ = null;
//    public TextView nDa = null;
//    public int nDb;
//    public int nDc;
//    public String nDd = null;
//    public String nDe = null;
//    protected String nDf = null;
//    public int nDg = 3;
//    protected boolean nDh = false;
//    protected int nDi;
//    private int nDj;
//    private u nDk;
//    private c<nl> nDl = new c<nl>() {
//        {
//            this.udX = nl.class.getName().hashCode();
//        }
//
//        public final /* synthetic */ boolean a(b bVar) {
//            final nl nlVar = (nl) bVar;
//            if (!(bk.bl(nlVar.bXc.bMY) || bk.bl(nlVar.bXc.bQR))) {
//                h.a(RemittanceDetailUI.this, RemittanceDetailUI.this.getString(i.remittance_detail_refund_tips, new Object[]{RemittanceDetailUI.aT(RemittanceDetailUI.this.mPv, false)}), RemittanceDetailUI.this.getString(i.app_remind), RemittanceDetailUI.this.getString(i.remittance_detail_refuse_btn_text), RemittanceDetailUI.this.getString(i.app_cancel), new OnClickListener() {
//                    public final void onClick(DialogInterface dialogInterface, int i) {
//                        m uVar = new u(nlVar.bXc.bMY, nlVar.bXc.bQR, nlVar.bXc.bXd, "refuse", nlVar.bXc.bXe, nlVar.bXc.bXf);
//                        uVar.dIA = "RemittanceProcess";
//                        RemittanceDetailUI.this.a(uVar, true, true);
//                    }
//                }, new OnClickListener() {
//                    public final void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//            }
//            return false;
//        }
//    };
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        this.nDb = getIntent().getIntExtra("invalid_time", 0);
//        this.nDc = getIntent().getIntExtra("appmsg_type", 0);
//        this.nDd = getIntent().getStringExtra("transaction_id");
//        this.nDf = getIntent().getStringExtra("bill_id");
//        this.nAx = getIntent().getStringExtra("transfer_id");
//        this.nDe = getIntent().getStringExtra("sender_name");
//        this.nDg = getIntent().getIntExtra("effective_date", 3);
//        this.nDh = getIntent().getBooleanExtra("is_sender", false);
//        this.nDi = getIntent().getIntExtra("total_fee", 0);
//        initView();
//        wE(0);
//        getIntent();
//        a.udP.c(this.nDl);
//    }
//
//    public void onDestroy() {
//        a.udP.d(this.nDl);
//        super.onDestroy();
//    }
//
//    public void wE(int i) {
//        a(new x(i, this.nDd, this.nAx, this.nDb), true, false);
//    }
//
//    protected final void initView() {
//        setMMTitle(i.remittance_result_title);
//        this.nCV = (ImageView) findViewById(f.remittance_detail_desc_icon);
//        this.nCW = (TextView) findViewById(f.remittance_detail_result_desc);
//        this.nCX = (TextView) findViewById(f.remittance_detail_fee);
//        this.nCY = (Button) findViewById(f.remittance_detail_collect_btn);
//        this.lUL = (TextView) findViewById(f.remittance_detail_main_desc);
//        this.nCZ = (TextView) findViewById(f.remittance_detail_time_desc_one);
//        this.nDa = (TextView) findViewById(f.remittance_detail_time_desc_two);
//    }
//
//    public void wF(int i) {
//        m hVar;
//        if (getIntent().getBooleanExtra("is_sender", false)) {
//            hVar = new com.tencent.mm.plugin.order.model.h(this.nDd, this.nDf, i);
//        } else {
//            hVar = new com.tencent.mm.plugin.order.model.h(this.nAx, this.nDf, i);
//        }
//        hVar.dIA = "RemittanceProcess";
//        a(hVar, true, true);
//    }
//
//    public void T(Intent intent) {
//        d.b(this, "remittance", ".ui.RemittanceResendMsgUI", intent);
//    }
//
//    public void bwG() {
//        m uVar = new u(this.nDd, this.nAx, this.nDi, "confirm", this.nDe, this.nDb);
//        uVar.dIA = "RemittanceProcess";
//        a(uVar, true, true);
//    }
//
//    public void bwH() {
//        m uVar = new u(this.nDd, this.nAx, this.nDi, "refuse", this.nDe, this.nDb);
//        uVar.dIA = "RemittanceProcess";
//        a(uVar, true, true);
//    }
//
//    private void bws() {
//        if (this.nDj == 0 || this.nAr != 1 || bk.bl(this.nAq)) {
//            y.i("MicroMsg.RemittanceDetailUI", "flag: %d, descUrl empty: %B", Integer.valueOf(this.nDj), Boolean.valueOf(bk.bl(this.nAq)));
//            this.mController.removeAllOptionMenu();
//            return;
//        }
//        addIconOptionMenu(0, e.wallet_qanda_icon, new OnMenuItemClickListener() {
//            public final boolean onMenuItemClick(MenuItem menuItem) {
//                com.tencent.mm.wallet_core.ui.e.l(RemittanceDetailUI.this.mController.uMN, RemittanceDetailUI.this.nAq, false);
//                return false;
//            }
//        });
//    }
//
//    /* JADX WARNING: Removed duplicated region for block: B:130:0x075e  */
//    /* JADX WARNING: Removed duplicated region for block: B:126:0x072f  */
//    public boolean c(int r12, int r13, java.lang.String r14, com.tencent.mm.ah.m r15) {
//        /*
//        r11 = this;
//        r0 = r15 instanceof com.tencent.mm.plugin.remittance.model.x;
//        if (r0 == 0) goto L_0x08b7;
//    L_0x0004:
//        r0 = r15;
//        r0 = (com.tencent.mm.plugin.remittance.model.x) r0;
//        if (r12 != 0) goto L_0x0933;
//    L_0x0009:
//        if (r13 != 0) goto L_0x0933;
//    L_0x000b:
//        if (r0 == 0) goto L_0x00d4;
//    L_0x000d:
//        r1 = r0.llS;
//        if (r1 == 0) goto L_0x0170;
//    L_0x0011:
//        r1 = com.tencent.mm.model.q.Gj();
//        r11.mPv = r1;
//        r1 = r11.nDe;
//        r11.mPo = r1;
//    L_0x001b:
//        r1 = com.tencent.mm.model.q.Gj();
//        r2 = r11.mPo;
//        r2 = r1.equals(r2);
//        r1 = r11.nCY;
//        r3 = 8;
//        r1.setVisibility(r3);
//        r1 = r11.nCX;
//        r4 = r0.iHP;
//        r3 = r0.mOZ;
//        r3 = com.tencent.mm.wallet_core.ui.e.d(r4, r3);
//        r1.setText(r3);
//        r1 = r0.nzv;
//        r3 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r3 != 0) goto L_0x005c;
//    L_0x0041:
//        r3 = "%s";
//        r3 = r1.contains(r3);
//        if (r3 == 0) goto L_0x005c;
//    L_0x004a:
//        r1 = r1.trim();
//        r3 = "%s";
//        r4 = r11.mPo;
//        r5 = 1;
//        r4 = aT(r4, r5);
//        r1 = r1.replace(r3, r4);
//    L_0x005c:
//        r3 = r0.nzw;
//        r4 = new android.os.Bundle;
//        r4.<init>();
//        r5 = "transaction_id";
//        r6 = r11.nDd;
//        r4.putString(r5, r6);
//        r5 = "receiver_name";
//        r6 = r11.mPo;
//        r4.putString(r5, r6);
//        r5 = "transfer_id";
//        r6 = r11.nAx;
//        r4.putString(r5, r6);
//        r5 = "total_fee";
//        r6 = r11.nDi;
//        r4.putInt(r5, r6);
//        r5 = "sender_name";
//        r6 = r11.nDe;
//        r4.putString(r5, r6);
//        r5 = "invalid_time";
//        r6 = r11.nDb;
//        r4.putInt(r5, r6);
//        r5 = r11.lUL;
//        r6 = 1;
//        r5.setClickable(r6);
//        r5 = r11.nCW;
//        r6 = 1;
//        r5.setClickable(r6);
//        r5 = r0.status;
//        r6 = "MicroMsg.RemittanceDetailUI";
//        r7 = "status: %d";
//        r8 = 1;
//        r8 = new java.lang.Object[r8];
//        r9 = 0;
//        r10 = java.lang.Integer.valueOf(r5);
//        r8[r9] = r10;
//        com.tencent.mm.sdk.platformtools.y.d(r6, r7, r8);
//        switch(r5) {
//            case 2000: goto L_0x017c;
//            case 2001: goto L_0x037b;
//            case 2002: goto L_0x0479;
//            case 2003: goto L_0x0479;
//            case 2004: goto L_0x060a;
//            case 2005: goto L_0x0768;
//            default: goto L_0x00b7;
//        };
//    L_0x00b7:
//        r11.finish();
//    L_0x00ba:
//        r1 = r0.nzy;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x00d4;
//    L_0x00c2:
//        r1 = com.tencent.mm.plugin.wxpay.a.f.banner_tips;
//        r1 = r11.findViewById(r1);
//        r1 = (android.widget.TextView) r1;
//        r2 = "7";
//        r3 = r0.nzy;
//        r4 = r0.nzz;
//        com.tencent.mm.wallet_core.ui.e.a(r1, r2, r3, r4);
//    L_0x00d4:
//        r1 = r0.nzx;
//        r11.nDj = r1;
//        com.tencent.mm.kernel.g.DQ();
//        r1 = com.tencent.mm.kernel.g.DP();
//        r1 = r1.Dz();
//        r2 = com.tencent.mm.storage.ac.a.USERINFO_DELAY_TRANSFER_DESC_URL_STRING;
//        r3 = "";
//        r1 = r1.get(r2, r3);
//        r1 = (java.lang.String) r1;
//        r11.nAq = r1;
//        com.tencent.mm.kernel.g.DQ();
//        r1 = com.tencent.mm.kernel.g.DP();
//        r1 = r1.Dz();
//        r2 = com.tencent.mm.storage.ac.a.USERINFO_DELAY_TRANSFER_DESC_URL_FLAG_INT;
//        r3 = 0;
//        r3 = java.lang.Integer.valueOf(r3);
//        r1 = r1.get(r2, r3);
//        r1 = (java.lang.Integer) r1;
//        r1 = r1.intValue();
//        r11.nAr = r1;
//        r1 = r11.nAq;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 == 0) goto L_0x0898;
//    L_0x0116:
//        r1 = 1;
//        r2 = r11.wCh;
//        com.tencent.mm.plugin.wallet_core.c.ae.a(r1, r2);
//    L_0x011c:
//        r11.bws();
//        r0 = r0.mPH;
//        r1 = 1;
//        if (r0 != r1) goto L_0x016e;
//    L_0x0124:
//        com.tencent.mm.kernel.g.DQ();
//        r0 = com.tencent.mm.kernel.g.DP();
//        r0 = r0.Dz();
//        r1 = 327729; // 0x50031 float:4.59246E-40 double:1.619196E-318;
//        r2 = "0";
//        r0 = r0.get(r1, r2);
//        r0 = (java.lang.String) r0;
//        r1 = "0";
//        r0 = r0.equals(r1);
//        if (r0 == 0) goto L_0x08a4;
//    L_0x0144:
//        r0 = r11.mController;
//        r1 = r0.uMN;
//        r0 = com.tencent.mm.model.q.Gw();
//        if (r0 == 0) goto L_0x08a0;
//    L_0x014e:
//        r0 = com.tencent.mm.plugin.wxpay.a.i.chatting_item_appmsg_remittance_collect_tips_payu;
//    L_0x0150:
//        r2 = com.tencent.mm.plugin.wxpay.a.i.wallet_remittance_collect_title;
//        r3 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$10;
//        r3.<init>();
//        com.tencent.mm.ui.base.h.a(r1, r0, r2, r3);
//        com.tencent.mm.kernel.g.DQ();
//        r0 = com.tencent.mm.kernel.g.DP();
//        r0 = r0.Dz();
//        r1 = "1";
//        r2 = 327729; // 0x50031 float:4.59246E-40 double:1.619196E-318;
//        r0.o(r2, r1);
//    L_0x016e:
//        r0 = 1;
//    L_0x016f:
//        return r0;
//    L_0x0170:
//        r1 = r11.nDe;
//        r11.mPv = r1;
//        r1 = com.tencent.mm.model.q.Gj();
//        r11.mPo = r1;
//        goto L_0x001b;
//    L_0x017c:
//        if (r2 != 0) goto L_0x0269;
//    L_0x017e:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r2 != 0) goto L_0x01eb;
//    L_0x0184:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r2 != 0) goto L_0x01eb;
//    L_0x018a:
//        r2 = r11.nCW;
//        r5 = r11.nCW;
//        r5 = r5.getTextSize();
//        r5 = (int) r5;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r5, r4);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.nCW;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//    L_0x01be:
//        r1 = r11.nCV;
//        r2 = com.tencent.mm.plugin.wxpay.a.h.remittance_wait;
//        r1.setImageResource(r2);
//        r1 = r11.nCZ;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_transfer_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzr;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nCZ;
//        r2 = 0;
//        r1.setVisibility(r2);
//        r1 = r11.nDa;
//        r2 = 8;
//        r1.setVisibility(r2);
//        goto L_0x00ba;
//    L_0x01eb:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_wait_sender;
//        r2 = 1;
//        r2 = new java.lang.Object[r2];
//        r3 = 0;
//        r4 = r11.mPo;
//        r5 = 1;
//        r4 = aT(r4, r5);
//        r2[r3] = r4;
//        r1 = r11.getString(r1, r2);
//        r2 = r11.nCW;
//        r3 = r11.nCW;
//        r3 = r3.getTextSize();
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r3);
//        r2.setText(r1);
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_sender_tips;
//        r2 = 1;
//        r2 = new java.lang.Object[r2];
//        r3 = 0;
//        r4 = r11.nDg;
//        r4 = java.lang.Integer.valueOf(r4);
//        r2[r3] = r4;
//        r1 = r11.getString(r1, r2);
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_resend_transfer_msg;
//        r2 = r11.getString(r2);
//        r3 = new android.text.SpannableString;
//        r4 = new java.lang.StringBuilder;
//        r4.<init>();
//        r4 = r4.append(r1);
//        r4 = r4.append(r2);
//        r4 = r4.toString();
//        r3.<init>(r4);
//        r4 = new com.tencent.mm.plugin.order.c.a;
//        r4.<init>(r11);
//        r5 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$3;
//        r5.<init>();
//        r4.mRJ = r5;
//        r5 = r1.length();
//        r1 = r1.length();
//        r2 = r2.length();
//        r1 = r1 + r2;
//        r2 = 33;
//        r3.setSpan(r4, r5, r1, r2);
//        r1 = r11.lUL;
//        r2 = android.text.method.LinkMovementMethod.getInstance();
//        r1.setMovementMethod(r2);
//        r1 = r11.lUL;
//        r1.setText(r3);
//        goto L_0x01be;
//    L_0x0269:
//        r2 = r11.nCV;
//        r5 = com.tencent.mm.plugin.wxpay.a.h.remittance_wait;
//        r2.setImageResource(r5);
//        r2 = r11.nCY;
//        r5 = 0;
//        r2.setVisibility(r5);
//        r2 = r11.nCY;
//        r5 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$4;
//        r5.<init>();
//        r2.setOnClickListener(r5);
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r2 != 0) goto L_0x02c2;
//    L_0x0286:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r2 != 0) goto L_0x02c2;
//    L_0x028c:
//        r2 = r11.nCW;
//        r5 = r11.nCW;
//        r5 = r5.getTextSize();
//        r5 = (int) r5;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r5, r4);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.nCW;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        goto L_0x01be;
//    L_0x02c2:
//        r1 = r11.nCW;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_wait_receiver;
//        r1.setText(r2);
//        r1 = r0.nzx;
//        if (r1 == 0) goto L_0x0367;
//    L_0x02cd:
//        r1 = r0.nzw;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x0321;
//    L_0x02d5:
//        r1 = r0.nzw;
//    L_0x02d7:
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_refuse_action;
//        r2 = r11.getString(r2);
//        r3 = new android.text.SpannableString;
//        r4 = new java.lang.StringBuilder;
//        r4.<init>();
//        r4 = r4.append(r1);
//        r4 = r4.append(r2);
//        r4 = r4.toString();
//        r3.<init>(r4);
//        r4 = new com.tencent.mm.plugin.order.c.a;
//        r4.<init>(r11);
//        r5 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$5;
//        r5.<init>(r0);
//        r4.mRJ = r5;
//        r5 = r1.length();
//        r1 = r1.length();
//        r2 = r2.length();
//        r1 = r1 + r2;
//        r2 = 33;
//        r3.setSpan(r4, r5, r1, r2);
//        r1 = r11.lUL;
//        r2 = android.text.method.LinkMovementMethod.getInstance();
//        r1.setMovementMethod(r2);
//        r1 = r11.lUL;
//        r1.setText(r3);
//        goto L_0x01be;
//    L_0x0321:
//        r1 = "MicroMsg.RemittanceDetailUI";
//        r2 = "use hardcode wording";
//        com.tencent.mm.sdk.platformtools.y.i(r1, r2);
//        r1 = r0.nzx;
//        r2 = 1;
//        if (r1 != r2) goto L_0x0363;
//    L_0x032f:
//        r1 = "24";
//    L_0x0332:
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_reveiver_tips1;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r3[r4] = r1;
//        r1 = r11.getString(r2, r3);
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_reveiver_tips2;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r11.nDg;
//        r5 = java.lang.Integer.valueOf(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r3 = new java.lang.StringBuilder;
//        r3.<init>();
//        r1 = r3.append(r1);
//        r1 = r1.append(r2);
//        r1 = r1.toString();
//        goto L_0x02d7;
//    L_0x0363:
//        r1 = "2";
//        goto L_0x0332;
//    L_0x0367:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_reveiver_tips2;
//        r2 = 1;
//        r2 = new java.lang.Object[r2];
//        r3 = 0;
//        r4 = r11.nDg;
//        r4 = java.lang.Integer.valueOf(r4);
//        r2[r3] = r4;
//        r1 = r11.getString(r1, r2);
//        goto L_0x02d7;
//    L_0x037b:
//        r5 = r11.nCV;
//        r6 = com.tencent.mm.plugin.wxpay.a.h.remittance_received;
//        r5.setImageResource(r6);
//        r5 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r5 != 0) goto L_0x03fe;
//    L_0x0388:
//        r5 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r5 != 0) goto L_0x03fe;
//    L_0x038e:
//        r2 = r11.nCW;
//        r5 = r11.nCW;
//        r5 = r5.getTextSize();
//        r5 = (int) r5;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r5, r4);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.nCW;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//    L_0x03c2:
//        r1 = r11.nCZ;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_transfer_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzr;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nCZ;
//        r2 = 0;
//        r1.setVisibility(r2);
//        r1 = r11.nDa;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_receive_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzu;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nDa;
//        r2 = 0;
//        r1.setVisibility(r2);
//        goto L_0x00ba;
//    L_0x03fe:
//        if (r2 == 0) goto L_0x043d;
//    L_0x0400:
//        r1 = r11.nCW;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_collected;
//        r1.setText(r2);
//        r1 = new android.text.SpannableStringBuilder;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_check_ballance;
//        r2 = r11.getString(r2);
//        r1.<init>(r2);
//        r2 = new com.tencent.mm.plugin.order.c.a;
//        r2.<init>(r11);
//        r3 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$6;
//        r3.<init>();
//        r2.mRJ = r3;
//        r3 = 0;
//        r4 = r1.length();
//        r5 = 18;
//        r1.setSpan(r2, r3, r4, r5);
//        r2 = r11.lUL;
//        r3 = android.text.method.LinkMovementMethod.getInstance();
//        r2.setMovementMethod(r3);
//        r2 = r11.lUL;
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = 0;
//        r1.setVisibility(r2);
//        goto L_0x03c2;
//    L_0x043d:
//        r1 = new java.lang.StringBuilder;
//        r1.<init>();
//        r2 = r11.mPo;
//        r3 = 1;
//        r2 = aT(r2, r3);
//        r1 = r1.append(r2);
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_collected;
//        r2 = r11.getString(r2);
//        r1 = r1.append(r2);
//        r1 = r1.toString();
//        r2 = r11.nCW;
//        r3 = r11.nCW;
//        r3 = r3.getTextSize();
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r3);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_sender_success_tips;
//        r1.setText(r2);
//        r1 = r11.lUL;
//        r2 = 0;
//        r1.setVisibility(r2);
//        goto L_0x03c2;
//    L_0x0479:
//        r6 = 2003; // 0x7d3 float:2.807E-42 double:9.896E-321;
//        if (r5 != r6) goto L_0x0512;
//    L_0x047d:
//        if (r2 != 0) goto L_0x0512;
//    L_0x047f:
//        r5 = r11.nCV;
//        r6 = com.tencent.mm.plugin.wxpay.a.h.remittance_timed_out;
//        r5.setImageResource(r6);
//        r5 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r5 != 0) goto L_0x050a;
//    L_0x048c:
//        r5 = r11.nCW;
//        r6 = r11.nCW;
//        r6 = r6.getTextSize();
//        r6 = (int) r6;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r6, r4);
//        r5.setText(r1);
//        r1 = r11.nCW;
//        r5 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r5.<init>();
//        r1.setOnTouchListener(r5);
//    L_0x04a6:
//        if (r2 != 0) goto L_0x0601;
//    L_0x04a8:
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r1 != 0) goto L_0x0575;
//    L_0x04ae:
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//    L_0x04c8:
//        r1 = r11.lUL;
//        r2 = 0;
//        r1.setVisibility(r2);
//    L_0x04ce:
//        r1 = r11.nCZ;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_transfer_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzr;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nCZ;
//        r2 = 0;
//        r1.setVisibility(r2);
//        r1 = r11.nDa;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_refund_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzu;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nDa;
//        r2 = 0;
//        r1.setVisibility(r2);
//        goto L_0x00ba;
//    L_0x050a:
//        r1 = r11.nCW;
//        r5 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_timeout_refunded;
//        r1.setText(r5);
//        goto L_0x04a6;
//    L_0x0512:
//        r5 = r11.nCV;
//        r6 = com.tencent.mm.plugin.wxpay.a.h.remittance_refunded;
//        r5.setImageResource(r6);
//        r5 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r5 != 0) goto L_0x053b;
//    L_0x051f:
//        r5 = r11.nCW;
//        r6 = r11.nCW;
//        r6 = r6.getTextSize();
//        r6 = (int) r6;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r6, r4);
//        r5.setText(r1);
//        r1 = r11.nCW;
//        r5 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r5.<init>();
//        r1.setOnTouchListener(r5);
//        goto L_0x04a6;
//    L_0x053b:
//        if (r2 == 0) goto L_0x0546;
//    L_0x053d:
//        r1 = r11.nCW;
//        r5 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_refunded;
//        r1.setText(r5);
//        goto L_0x04a6;
//    L_0x0546:
//        r1 = new java.lang.StringBuilder;
//        r1.<init>();
//        r5 = r11.mPo;
//        r6 = 1;
//        r5 = aT(r5, r6);
//        r1 = r1.append(r5);
//        r5 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_refunded;
//        r5 = r11.getString(r5);
//        r1 = r1.append(r5);
//        r1 = r1.toString();
//        r5 = r11.nCW;
//        r6 = r11.nCW;
//        r6 = r6.getTextSize();
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r6);
//        r5.setText(r1);
//        goto L_0x04a6;
//    L_0x0575:
//        r1 = "CFT";
//        r2 = r0.mPx;
//        r1 = r1.equals(r2);
//        if (r1 != 0) goto L_0x058b;
//    L_0x0580:
//        r1 = "LQT";
//        r2 = r0.mPx;
//        r1 = r1.equals(r2);
//        if (r1 == 0) goto L_0x05f8;
//    L_0x058b:
//        r1 = "LQT";
//        r2 = r0.mPx;
//        r3 = r1.equals(r2);
//        if (r3 == 0) goto L_0x05e9;
//    L_0x0596:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_refund_to_lqt;
//        r1 = r11.getString(r1);
//        r2 = r1;
//    L_0x059d:
//        if (r3 == 0) goto L_0x05f1;
//    L_0x059f:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_check_lqt;
//        r1 = r11.getString(r1);
//    L_0x05a5:
//        r4 = new android.text.SpannableString;
//        r5 = new java.lang.StringBuilder;
//        r5.<init>();
//        r5 = r5.append(r2);
//        r5 = r5.append(r1);
//        r5 = r5.toString();
//        r4.<init>(r5);
//        r5 = new com.tencent.mm.plugin.order.c.a;
//        r5.<init>(r11);
//        r6 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$7;
//        r6.<init>(r3);
//        r5.mRJ = r6;
//        r3 = r2.length();
//        r2 = r2.length();
//        r1 = r1.length();
//        r1 = r1 + r2;
//        r2 = 33;
//        r4.setSpan(r5, r3, r1, r2);
//        r1 = r11.lUL;
//        r2 = android.text.method.LinkMovementMethod.getInstance();
//        r1.setMovementMethod(r2);
//        r1 = r11.lUL;
//        r1.setText(r4);
//        goto L_0x04c8;
//    L_0x05e9:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_refund_to_ballance;
//        r1 = r11.getString(r1);
//        r2 = r1;
//        goto L_0x059d;
//    L_0x05f1:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_check_ballance;
//        r1 = r11.getString(r1);
//        goto L_0x05a5;
//    L_0x05f8:
//        r1 = r11.lUL;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_refund_to_bankcard;
//        r1.setText(r2);
//        goto L_0x04c8;
//    L_0x0601:
//        r1 = r11.lUL;
//        r2 = 8;
//        r1.setVisibility(r2);
//        goto L_0x04ce;
//    L_0x060a:
//        if (r2 == 0) goto L_0x06be;
//    L_0x060c:
//        r2 = r11.nCV;
//        r5 = com.tencent.mm.plugin.wxpay.a.h.remittance_wait;
//        r2.setImageResource(r5);
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r2 != 0) goto L_0x068f;
//    L_0x0619:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r2 != 0) goto L_0x068f;
//    L_0x061f:
//        r2 = r11.nCW;
//        r5 = r11.nCW;
//        r5 = r5.getTextSize();
//        r5 = (int) r5;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r5, r4);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.nCW;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//    L_0x0653:
//        r1 = r11.nCZ;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_transfer_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzr;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nCZ;
//        r2 = 0;
//        r1.setVisibility(r2);
//        r1 = r11.nDa;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_receive_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzu;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nDa;
//        r2 = 0;
//        r1.setVisibility(r2);
//        goto L_0x00ba;
//    L_0x068f:
//        r1 = r0.nzv;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x06ae;
//    L_0x0697:
//        r1 = r11.nCW;
//        r2 = r0.nzv;
//        r1.setText(r2);
//    L_0x069e:
//        r1 = r0.nzw;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x06b6;
//    L_0x06a6:
//        r1 = r11.lUL;
//        r2 = r0.nzw;
//        r1.setText(r2);
//        goto L_0x0653;
//    L_0x06ae:
//        r1 = r11.nCW;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_receiver_waited;
//        r1.setText(r2);
//        goto L_0x069e;
//    L_0x06b6:
//        r1 = r11.lUL;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_receiver_wait_tips;
//        r1.setText(r2);
//        goto L_0x0653;
//    L_0x06be:
//        r2 = r11.nCV;
//        r5 = com.tencent.mm.plugin.wxpay.a.h.remittance_wait;
//        r2.setImageResource(r5);
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r2 != 0) goto L_0x0707;
//    L_0x06cb:
//        r2 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r2 != 0) goto L_0x0707;
//    L_0x06d1:
//        r2 = r11.nCW;
//        r5 = r11.nCW;
//        r5 = r5.getTextSize();
//        r5 = (int) r5;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r5, r4);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.nCW;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        goto L_0x0653;
//    L_0x0707:
//        r1 = r0.nzv;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x0745;
//    L_0x070f:
//        r1 = r11.nCW;	 Catch:{ Exception -> 0x0738 }
//        r2 = r0.nzv;	 Catch:{ Exception -> 0x0738 }
//        r3 = 1;
//        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0738 }
//        r4 = 0;
//        r5 = r11.mPo;	 Catch:{ Exception -> 0x0738 }
//        r6 = 1;
//        r5 = aT(r5, r6);	 Catch:{ Exception -> 0x0738 }
//        r3[r4] = r5;	 Catch:{ Exception -> 0x0738 }
//        r2 = java.lang.String.format(r2, r3);	 Catch:{ Exception -> 0x0738 }
//        r1.setText(r2);	 Catch:{ Exception -> 0x0738 }
//    L_0x0727:
//        r1 = r0.nzw;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x075e;
//    L_0x072f:
//        r1 = r11.lUL;
//        r2 = r0.nzw;
//        r1.setText(r2);
//        goto L_0x0653;
//    L_0x0738:
//        r1 = move-exception;
//        r2 = "MicroMsg.RemittanceDetailUI";
//        r3 = "";
//        r4 = 0;
//        r4 = new java.lang.Object[r4];
//        com.tencent.mm.sdk.platformtools.y.printErrStackTrace(r2, r1, r3, r4);
//    L_0x0745:
//        r1 = r11.nCW;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_sender_waited;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r11.mPo;
//        r6 = 1;
//        r5 = aT(r5, r6);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        goto L_0x0727;
//    L_0x075e:
//        r1 = r11.lUL;
//        r2 = "";
//        r1.setText(r2);
//        goto L_0x0653;
//    L_0x0768:
//        r5 = r11.nCV;
//        r6 = com.tencent.mm.plugin.wxpay.a.h.remittance_cancle;
//        r5.setImageResource(r6);
//        r5 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r5 != 0) goto L_0x07ef;
//    L_0x0775:
//        r5 = com.tencent.mm.sdk.platformtools.bk.bl(r3);
//        if (r5 != 0) goto L_0x07ef;
//    L_0x077b:
//        r2 = r11.nCW;
//        r5 = r11.nCW;
//        r5 = r5.getTextSize();
//        r5 = (int) r5;
//        r1 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r1, r5, r4);
//        r2.setText(r1);
//        r1 = r11.lUL;
//        r2 = r11.lUL;
//        r2 = r2.getTextSize();
//        r2 = (int) r2;
//        r2 = com.tencent.mm.pluginsdk.ui.d.j.a(r11, r3, r2, r4);
//        r1.setText(r2);
//        r1 = r11.nCW;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//        r1 = r11.lUL;
//        r2 = new com.tencent.mm.pluginsdk.ui.d.m;
//        r2.<init>();
//        r1.setOnTouchListener(r2);
//    L_0x07af:
//        r1 = r11.nCZ;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_transfer_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzr;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nCZ;
//        r2 = 0;
//        r1.setVisibility(r2);
//        r1 = r0.nzu;
//        if (r1 <= 0) goto L_0x088f;
//    L_0x07d0:
//        r1 = r11.nDa;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_cancel_time;
//        r3 = 1;
//        r3 = new java.lang.Object[r3];
//        r4 = 0;
//        r5 = r0.nzu;
//        r5 = com.tencent.mm.wallet_core.ui.e.hP(r5);
//        r3[r4] = r5;
//        r2 = r11.getString(r2, r3);
//        r1.setText(r2);
//        r1 = r11.nDa;
//        r2 = 0;
//        r1.setVisibility(r2);
//        goto L_0x00ba;
//    L_0x07ef:
//        r1 = r0.nzv;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x0863;
//    L_0x07f7:
//        r1 = r11.nCW;
//        r3 = r0.nzv;
//        r1.setText(r3);
//    L_0x07fe:
//        r1 = r0.nzw;
//        r1 = com.tencent.mm.sdk.platformtools.bk.bl(r1);
//        if (r1 != 0) goto L_0x087b;
//    L_0x0806:
//        r1 = "CFT";
//        r2 = r0.mPx;
//        r1 = r1.equals(r2);
//        if (r1 != 0) goto L_0x081c;
//    L_0x0811:
//        r1 = "LQT";
//        r2 = r0.mPx;
//        r1 = r1.equals(r2);
//        if (r1 == 0) goto L_0x0872;
//    L_0x081c:
//        r1 = "LQT";
//        r2 = r0.mPx;
//        r2 = r1.equals(r2);
//        r3 = r0.nzw;
//        if (r2 == 0) goto L_0x086b;
//    L_0x0829:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_check_lqt;
//        r1 = r11.getString(r1);
//    L_0x082f:
//        r4 = new android.text.SpannableStringBuilder;
//        r4.<init>();
//        r4.append(r3);
//        r4.append(r1);
//        r1 = new com.tencent.mm.plugin.order.c.a;
//        r1.<init>(r11);
//        r5 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$8;
//        r5.<init>(r2);
//        r1.mRJ = r5;
//        r2 = r3.length();
//        r3 = r4.length();
//        r5 = 18;
//        r4.setSpan(r1, r2, r3, r5);
//        r1 = r11.lUL;
//        r2 = android.text.method.LinkMovementMethod.getInstance();
//        r1.setMovementMethod(r2);
//        r1 = r11.lUL;
//        r1.setText(r4);
//        goto L_0x07af;
//    L_0x0863:
//        r1 = r11.nCW;
//        r3 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_result_canceled;
//        r1.setText(r3);
//        goto L_0x07fe;
//    L_0x086b:
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_check_ballance;
//        r1 = r11.getString(r1);
//        goto L_0x082f;
//    L_0x0872:
//        r1 = r11.lUL;
//        r2 = r0.nzw;
//        r1.setText(r2);
//        goto L_0x07af;
//    L_0x087b:
//        if (r2 == 0) goto L_0x0886;
//    L_0x087d:
//        r1 = r11.lUL;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_receiver_cancel_tips;
//        r1.setText(r2);
//        goto L_0x07af;
//    L_0x0886:
//        r1 = r11.lUL;
//        r2 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_sender_cancel_tips;
//        r1.setText(r2);
//        goto L_0x07af;
//    L_0x088f:
//        r1 = r11.nDa;
//        r2 = 8;
//        r1.setVisibility(r2);
//        goto L_0x00ba;
//    L_0x0898:
//        r1 = 0;
//        r2 = r11.wCh;
//        com.tencent.mm.plugin.wallet_core.c.ae.a(r1, r2);
//        goto L_0x011c;
//    L_0x08a0:
//        r0 = com.tencent.mm.plugin.wxpay.a.i.wallet_remittance_collect_tips;
//        goto L_0x0150;
//    L_0x08a4:
//        r0 = r11.nDj;
//        if (r0 != 0) goto L_0x016e;
//    L_0x08a8:
//        r0 = r11.mController;
//        r0 = r0.uMN;
//        r1 = com.tencent.mm.plugin.wxpay.a.i.remittance_detail_msg_collect_suc;
//        r1 = r11.getString(r1);
//        com.tencent.mm.ui.base.h.bC(r0, r1);
//        goto L_0x016e;
//    L_0x08b7:
//        r0 = r15 instanceof com.tencent.mm.plugin.remittance.model.u;
//        if (r0 == 0) goto L_0x090e;
//    L_0x08bb:
//        if (r12 != 0) goto L_0x08e5;
//    L_0x08bd:
//        if (r13 != 0) goto L_0x08e5;
//    L_0x08bf:
//        r15 = (com.tencent.mm.plugin.remittance.model.u) r15;
//        r11.nDk = r15;
//        r0 = r11.nDk;
//        r0 = r0.cMv();
//        if (r0 == 0) goto L_0x08dd;
//    L_0x08cb:
//        r0 = r11.nDk;
//        r0 = r0.lQQ;
//        r1 = new com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI$2;
//        r1.<init>();
//        r0 = r0.a(r11, r1);
//        if (r0 == 0) goto L_0x08dd;
//    L_0x08da:
//        r0 = 1;
//        goto L_0x016f;
//    L_0x08dd:
//        r0 = r11.nDk;
//        r11.a(r0);
//        r0 = 1;
//        goto L_0x016f;
//    L_0x08e5:
//        r0 = 416; // 0x1a0 float:5.83E-43 double:2.055E-321;
//        if (r13 != r0) goto L_0x0908;
//    L_0x08e9:
//        r0 = new android.os.Bundle;
//        r0.<init>();
//        r1 = "realname_verify_process_jump_plugin";
//        r2 = "remittance";
//        r0.putString(r1, r2);
//        r1 = "realname_verify_process_jump_activity";
//        r2 = ".ui.RemittanceDetailUI";
//        r0.putString(r1, r2);
//        r1 = 1008; // 0x3f0 float:1.413E-42 double:4.98E-321;
//        r0 = com.tencent.mm.plugin.wallet_core.id_verify.util.a.a(r11, r13, r15, r0, r1);
//        goto L_0x016f;
//    L_0x0908:
//        r11.aB(r13, r14);
//        r0 = 1;
//        goto L_0x016f;
//    L_0x090e:
//        r0 = r15 instanceof com.tencent.mm.plugin.wallet_core.c.ae;
//        if (r0 == 0) goto L_0x0933;
//    L_0x0912:
//        if (r12 != 0) goto L_0x0929;
//    L_0x0914:
//        if (r13 != 0) goto L_0x0929;
//    L_0x0916:
//        r0 = r15;
//        r0 = (com.tencent.mm.plugin.wallet_core.c.ae) r0;
//        r0 = r0.qrl;
//        r11.nAq = r0;
//        r15 = (com.tencent.mm.plugin.wallet_core.c.ae) r15;
//        r0 = r15.qrm;
//        r11.nAr = r0;
//        r11.bws();
//    L_0x0926:
//        r0 = 1;
//        goto L_0x016f;
//    L_0x0929:
//        r0 = "MicroMsg.RemittanceDetailUI";
//        r1 = "net error";
//        com.tencent.mm.sdk.platformtools.y.i(r0, r1);
//        goto L_0x0926;
//    L_0x0933:
//        r0 = r15 instanceof com.tencent.mm.plugin.wallet_core.id_verify.model.e;
//        if (r0 == 0) goto L_0x093a;
//    L_0x0937:
//        r0 = 1;
//        goto L_0x016f;
//    L_0x093a:
//        r0 = 0;
//        goto L_0x016f;
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.plugin.remittance.ui.RemittanceDetailUI.c(int, int, java.lang.String, com.tencent.mm.ah.m):boolean");
//    }
//
//    private void a(u uVar) {
//        if ("confirm".equals(uVar.nyy)) {
//            wE(1);
//        } else {
//            aB(0, getResources().getString(i.remittance_detail_msg_refuse_suc));
//        }
//    }
//
//    protected final int getLayoutId() {
//        return g.remittance_detail_ui;
//    }
//
//    public final void aB(int i, String str) {
//        Intent intent = new Intent();
//        intent.putExtra("result_msg", str);
//        setResult(i, intent);
//        finish();
//    }
//
//    public void finish() {
//        Bundle bundle = new Bundle();
//        bundle.putString("realname_verify_process_jump_plugin", "remittance");
//        bundle.putString("realname_verify_process_jump_activity", ".ui.RemittanceDetailUI");
//        if (this.nDk == null || !com.tencent.mm.plugin.wallet_core.id_verify.util.a.a((Activity) this, this.nDk, bundle, true, 1008)) {
//            super.finish();
//        } else {
//            this.nDk = null;
//        }
//    }
//
//    public static String aT(String str, boolean z) {
//        String gV = com.tencent.mm.wallet_core.ui.e.gV(str);
//        if (gV == null) {
//            return "";
//        }
//        if (gV.length() <= 10 || !z) {
//            return gV;
//        }
//        return gV.substring(0, 8) + "...";
//    }
//}