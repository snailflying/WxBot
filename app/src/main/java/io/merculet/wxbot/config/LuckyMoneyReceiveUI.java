//package io.merculet.wxbot.config;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout.LayoutParams;
//import android.widget.TextView;
//import com.tencent.mm.ah.m;
//import com.tencent.mm.kernel.j;
//import com.tencent.mm.model.q;
//import com.tencent.mm.modelstat.d;
//import com.tencent.mm.plugin.luckymoney.b.ad;
//import com.tencent.mm.plugin.luckymoney.b.ag;
//import com.tencent.mm.plugin.luckymoney.b.w;
//import com.tencent.mm.plugin.normsg.a.b;
//import com.tencent.mm.plugin.wallet_core.model.o;
//import com.tencent.mm.plugin.wallet_core.model.y;
//import com.tencent.mm.plugin.wxpay.a.e;
//import com.tencent.mm.plugin.wxpay.a.f;
//import com.tencent.mm.plugin.wxpay.a.g;
//import com.tencent.mm.plugin.wxpay.a.i;
//import com.tencent.mm.sdk.platformtools.BackwardSupportUtil;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.x;
//import com.tencent.mm.ui.base.a;
//import com.tencent.mm.ui.base.h;
//import com.tencent.mm.ui.base.p;
//import com.tencent.mm.wallet_core.ui.WalletTextView;
//import java.io.IOException;
//
//@a(3)
//@j
//public class LuckyMoneyReceiveUI extends LuckyMoneyBaseUI {
//    private TextView iKV;
//    private long iaq = 0;
//    private String kPX;
//    private ImageView lLV;
//    private TextView lMM;
//    Button lMN;
//    private View lMO;
//    private ImageView lMP;
//    private TextView lMQ;
//    private View lMR;
//    private ImageView lOR;
//    ag lRQ;
//    private String lUA;
//    private String lUy;
//    private y lXz = null;
//    private TextView lly;
//    private int maxSize = 0;
//    private int textSize = 0;
//    private p tipDialog = null;
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        this.lUA = getIntent().getStringExtra("key_native_url");
//        com.tencent.mm.sdk.platformtools.y.i("MicroMsg.LuckyMoneyReceiveUI", "nativeUrl= " + bk.pm(this.lUA));
//        initView();
//        Uri parse = Uri.parse(bk.pm(this.lUA));
//        try {
//            this.lUy = parse.getQueryParameter("sendid");
//        } catch (Exception e) {
//        }
//        this.lXz = o.bVp().QC(this.lUA);
//        if (this.lXz != null && this.lXz.field_receiveAmount > 0 && bk.co(this.lXz.field_receiveTime) < 86400000) {
//            com.tencent.mm.sdk.platformtools.y.i("MicroMsg.LuckyMoneyReceiveUI", "use cache this item %s %s", Long.valueOf(this.lXz.field_receiveTime), bk.pm(this.lUA));
//            Intent intent = new Intent();
//            intent.setClass(this.mController.uMN, LuckyMoneyDetailUI.class);
//            intent.putExtra("key_native_url", this.lXz.field_mNativeUrl);
//            intent.putExtra("key_sendid", this.lUy);
//            startActivity(intent);
//            finish();
//        } else if (bk.bl(this.lUy)) {
//            finish();
//            com.tencent.mm.sdk.platformtools.y.w("MicroMsg.LuckyMoneyReceiveUI", "sendid null & finish");
//        } else {
//            int i = bk.getInt(parse.getQueryParameter("channelid"), 1);
//            this.kPX = parse.getQueryParameter("sendusername");
//            b(new ag(i, this.lUy, this.lUA, getIntent().getIntExtra("key_way", 0), "v1.0"), false);
//            if (this.tipDialog != null) {
//                this.tipDialog.show();
//            }
//        }
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        if (this.tipDialog != null && this.tipDialog.isShowing()) {
//            this.tipDialog.dismiss();
//        }
//    }
//
//    protected void onResume() {
//        super.onResume();
//        this.iaq = bk.UX();
//    }
//
//    protected void onPause() {
//        super.onPause();
//        d.h("LuckyMoneyReceiveUI", this.iaq, bk.UX());
//    }
//
//    protected final void initView() {
//        this.lMR = findViewById(f.lucky_money_receive_ll);
//        this.lLV = (ImageView) findViewById(f.lucky_money_receive_sender_avatar);
//        this.lMM = (TextView) findViewById(f.lucky_money_receive_sender_nickname);
//        this.iKV = (TextView) findViewById(f.lucky_money_receive_tips);
//        this.lly = (TextView) findViewById(f.lucky_money_receive_wishing);
//        this.lMN = (Button) findViewById(f.lucky_money_recieve_open);
//        this.lMQ = (TextView) findViewById(f.lucky_money_recieve_check_detail);
//        this.lMO = findViewById(f.lucky_money_recieve_check_detail_ll);
//        this.lMP = (ImageView) findViewById(f.lucky_money_bottom_decoration);
//        this.lOR = (ImageView) findViewById(f.lucky_money_recieve_close_btn);
//        this.lOR.setOnClickListener(new OnClickListener() {
//            public final void onClick(View view) {
//                LuckyMoneyReceiveUI.this.finish();
//            }
//        });
//        this.maxSize = (int) (((float) com.tencent.mm.cb.a.ab(this.mController.uMN, com.tencent.mm.plugin.wxpay.a.d.lucky_money_goldstyle_envelop_wishing_textsize)) * 1.375f);
//        this.textSize = com.tencent.mm.cb.a.aa(this.mController.uMN, com.tencent.mm.plugin.wxpay.a.d.lucky_money_goldstyle_envelop_wishing_textsize);
//        this.textSize = this.textSize > this.maxSize ? this.maxSize : this.textSize;
//        this.lly.setTextSize(0, (float) this.textSize);
//        this.mController.contentView.setVisibility(8);
//        this.tipDialog = h.b(this.mController.uMN, getString(i.loading_tips), true, new OnCancelListener() {
//            public final void onCancel(DialogInterface dialogInterface) {
//                if (LuckyMoneyReceiveUI.this.tipDialog != null && LuckyMoneyReceiveUI.this.tipDialog.isShowing()) {
//                    LuckyMoneyReceiveUI.this.tipDialog.dismiss();
//                }
//                LuckyMoneyReceiveUI.this.lUq.bfH();
//                if (LuckyMoneyReceiveUI.this.mController.contentView.getVisibility() == 8 || LuckyMoneyReceiveUI.this.mController.contentView.getVisibility() == 4) {
//                    com.tencent.mm.sdk.platformtools.y.i("MicroMsg.LuckyMoneyReceiveUI", "user cancel & finish");
//                    LuckyMoneyReceiveUI.this.finish();
//                }
//            }
//        });
//        bfv();
//    }
//
//    private void bfv() {
//        if (this.lMN != null) {
//            b.mGK.vs(this.lMN.getId());
//            String d = x.d(ae.getContext().getSharedPreferences(ae.cqR(), 0));
//            if (d == null || d.length() <= 0 || !(d.equals("zh_CN") || d.equals("zh_TW") || d.equals("zh_HK"))) {
//                this.lMN.setBackgroundResource(e.lucky_money_send_btn);
//                this.lMN.setText(i.lucky_money_open_title);
//            }
//        }
//    }
//
//    protected final int getLayoutId() {
//        return g.lucky_money_receive_ui;
//    }
//
//    public final boolean c(int i, int i2, String str, m mVar) {
//        int i3 = 0;
//        if (!(mVar instanceof ag)) {
//            if (mVar instanceof ad) {
//                com.tencent.mm.plugin.luckymoney.b.o.d(this.lMN);
//                bfv();
//                if (i == 0 && i2 == 0) {
//                    final ad adVar = (ad) mVar;
//                    if (adVar.lQQ != null) {
//                        i3 = 1;
//                    }
//                    if (i3 != 0 && adVar.lQQ.a((Activity) this, new com.tencent.mm.wallet_core.c.e() {
//                        public final void bgs() {
//                            LuckyMoneyReceiveUI.this.a(adVar);
//                        }
//                    })) {
//                        return true;
//                    }
//                    a(adVar);
//                    return true;
//                } else if (i2 == 416) {
//                    if (this.tipDialog != null && this.tipDialog.isShowing()) {
//                        this.tipDialog.hide();
//                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putString("realname_verify_process_jump_activity", ".ui.LuckyMoneyReceiveUI");
//                    bundle.putString("realname_verify_process_jump_plugin", "luckymoney");
//                    this.lMN.setBackgroundResource(e.festival_lucky_money_open_btn);
//                    AnonymousClass7 anonymousClass7 = new DialogInterface.OnClickListener() {
//                        public final void onClick(DialogInterface dialogInterface, int i) {
//                        }
//                    };
//                    return com.tencent.mm.plugin.luckymoney.b.o.a(this, i2, mVar, bundle, false, null, 1003);
//                }
//            } else if (mVar instanceof w) {
//                if (this.tipDialog != null && this.tipDialog.isShowing()) {
//                    this.tipDialog.hide();
//                }
//                if (i == 0 && i2 == 0) {
//                    w wVar = (w) mVar;
//                    Intent intent = new Intent();
//                    intent.setClass(this.mController.uMN, LuckyMoneyDetailUI.class);
//                    try {
//                        intent.putExtra("key_detail_info", wVar.lQZ.toByteArray());
//                        intent.putExtra("key_jump_from", 2);
//                    } catch (IOException e) {
//                        com.tencent.mm.sdk.platformtools.y.w("MicroMsg.LuckyMoneyReceiveUI", "luckyMoneyDetail.toByteArray() fail! " + e.getLocalizedMessage());
//                    }
//                    intent.putExtra("key_native_url", this.lUA);
//                    intent.putExtra("key_sendid", this.lUy);
//                    startActivity(intent);
//                    finish();
//                    return true;
//                }
//                h.bC(this, str);
//                finish();
//                return true;
//            }
//            return false;
//        } else if (i == 0 && i2 == 0) {
//            this.lRQ = (ag) mVar;
//            com.tencent.mm.plugin.report.service.h.nFQ.f(11701, Integer.valueOf(5), Integer.valueOf(tu(this.lRQ.lPR)), Integer.valueOf(bgr()), Integer.valueOf(0), Integer.valueOf(1));
//            y yVar = new y();
//            yVar.field_mNativeUrl = this.lUA;
//            yVar.field_hbType = this.lRQ.lPR;
//            yVar.field_hbStatus = this.lRQ.cec;
//            yVar.field_receiveStatus = this.lRQ.ced;
//            o.bVp().a(yVar);
//            if (this.lRQ.ced == 2) {
//                b(new w(this.lUy, 11, 0, this.lUA, "v1.0"), false);
//                return true;
//            }
//            boolean z;
//            if (this.tipDialog != null && this.tipDialog.isShowing()) {
//                this.tipDialog.hide();
//            }
//            com.tencent.mm.plugin.luckymoney.b.o.a(this.lLV, this.lRQ.lPV, this.lRQ.lQp);
//            com.tencent.mm.plugin.luckymoney.b.o.a(this.mController.uMN, this.lMM, this.lRQ.lRI);
//            if (this.lRQ.ced == 1 || this.lRQ.cec == 4 || this.lRQ.cec == 5 || this.lRQ.cec == 1) {
//                this.lMN.setBackgroundResource(e.lucky_money_receive_open_disabled_btn);
//                this.lMN.setText(i.lucky_money_open);
//                this.lMN.setOnClickListener(null);
//                this.lMN.setVisibility(8);
//                if (bk.bl(this.lRQ.lNs)) {
//                    this.iKV.setVisibility(8);
//                } else {
//                    this.iKV.setText(this.lRQ.lNs);
//                    this.iKV.setVisibility(0);
//                }
//                this.lly.setText(this.lRQ.lPS);
//                LayoutParams layoutParams = (LayoutParams) this.lMO.getLayoutParams();
//                layoutParams.bottomMargin = BackwardSupportUtil.b.b(this.mController.uMN, 30.0f);
//                this.lMO.setLayoutParams(layoutParams);
//                z = true;
//            } else {
//                if (!bk.bl(this.lRQ.lPS)) {
//                    this.iKV.setText(this.lRQ.lPS);
//                    this.iKV.setVisibility(0);
//                }
//                if (!bk.bl(this.lRQ.lLm)) {
//                    com.tencent.mm.plugin.luckymoney.b.o.a(this.mController.uMN, this.lly, this.lRQ.lLm);
//                    this.lly.setVisibility(0);
//                }
//                this.lMN.setOnClickListener(new OnClickListener() {
//                    public final void onClick(View view) {
//                        com.tencent.mm.plugin.report.service.h.nFQ.f(11701, Integer.valueOf(5), Integer.valueOf(LuckyMoneyReceiveUI.tu(LuckyMoneyReceiveUI.this.lRQ.lPR)), Integer.valueOf(LuckyMoneyReceiveUI.this.bgr()), Integer.valueOf(0), Integer.valueOf(2));
//                        LuckyMoneyReceiveUI luckyMoneyReceiveUI = LuckyMoneyReceiveUI.this;
//                        luckyMoneyReceiveUI.b(new ad(luckyMoneyReceiveUI.lRQ.msgType, luckyMoneyReceiveUI.lRQ.bvj, luckyMoneyReceiveUI.lRQ.lMg, luckyMoneyReceiveUI.lRQ.ceb, com.tencent.mm.plugin.luckymoney.b.o.bfL(), q.Gl(), luckyMoneyReceiveUI.getIntent().getStringExtra("key_username"), "v1.0", luckyMoneyReceiveUI.lRQ.lRM), false);
//                        com.tencent.mm.plugin.luckymoney.b.o.c(luckyMoneyReceiveUI.lMN);
//                    }
//                });
//                z = false;
//            }
//            if (q.Gj().equals(this.kPX) || (this.lRQ.lPR == 1 && z)) {
//                if (this.lRQ.lPR == 1) {
//                    this.lMQ.setText(i.lucky_money_detail_luck);
//                }
//                this.lMO.setVisibility(0);
//                this.lMO.setOnClickListener(new OnClickListener() {
//                    public final void onClick(View view) {
//                        if (z) {
//                            com.tencent.mm.plugin.report.service.h.nFQ.f(11701, Integer.valueOf(6), Integer.valueOf(LuckyMoneyReceiveUI.tu(LuckyMoneyReceiveUI.this.lRQ.lPR)), Integer.valueOf(LuckyMoneyReceiveUI.this.bgr()), Integer.valueOf(0), Integer.valueOf(1));
//                        }
//                        Intent intent = new Intent();
//                        intent.setClass(LuckyMoneyReceiveUI.this.mController.uMN, LuckyMoneyDetailUI.class);
//                        intent.putExtra("key_native_url", LuckyMoneyReceiveUI.this.lRQ.ceb);
//                        intent.putExtra("key_sendid", LuckyMoneyReceiveUI.this.lRQ.lMg);
//                        LuckyMoneyReceiveUI.this.startActivity(intent);
//                        LuckyMoneyReceiveUI.this.finish();
//                    }
//                });
//                this.lMP.setVisibility(8);
//            } else {
//                this.lMO.setVisibility(8);
//                this.lMP.setVisibility(0);
//            }
//            com.tencent.mm.plugin.luckymoney.b.o.a(this.lMR, null);
//            this.mController.contentView.setVisibility(0);
//            if (this.lRQ.cec != 5 || yVar.field_receiveAmount <= 0) {
//                return true;
//            }
//            this.lMP.setVisibility(8);
//            this.lMO.setVisibility(0);
//            this.iKV.setVisibility(0);
//            this.iKV.setText(i.lucky_money_send_for_you);
//            this.lly.setVisibility(8);
//            findViewById(f.lucky_money_receive_money).setVisibility(0);
//            ((WalletTextView) findViewById(f.lucky_money_detail_amount)).setText(com.tencent.mm.wallet_core.ui.e.A(((double) yVar.field_receiveAmount) / 100.0d));
//            this.lMO.setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    Intent intent = new Intent();
//                    intent.setClass(LuckyMoneyReceiveUI.this.mController.uMN, LuckyMoneyDetailUI.class);
//                    intent.putExtra("key_native_url", LuckyMoneyReceiveUI.this.lRQ.ceb);
//                    intent.putExtra("key_sendid", LuckyMoneyReceiveUI.this.lRQ.lMg);
//                    LuckyMoneyReceiveUI.this.startActivity(intent);
//                    LuckyMoneyReceiveUI.this.finish();
//                }
//            });
//            return true;
//        } else {
//            if (this.tipDialog != null && this.tipDialog.isShowing()) {
//                this.tipDialog.hide();
//            }
//            return false;
//        }
//    }
//
//    private void a(final ad adVar) {
//        com.tencent.mm.plugin.luckymoney.b.f fVar = adVar.lQZ;
//        if (fVar.ced == 2) {
//            Intent intent = new Intent();
//            intent.setClass(this.mController.uMN, LuckyMoneyDetailUI.class);
//            try {
//                intent.putExtra("key_detail_info", adVar.lQZ.toByteArray());
//                intent.putExtra("key_jump_from", 2);
//            } catch (IOException e) {
//                com.tencent.mm.sdk.platformtools.y.w("MicroMsg.LuckyMoneyReceiveUI", "luckyMoneyDetail.toByteArray() fail! " + e.getLocalizedMessage());
//            }
//            intent.putExtra("key_native_url", adVar.ceb);
//            intent.putExtra("key_sendid", adVar.lMg);
//            if (com.tencent.mm.m.g.AA().getInt("PlayCoinSound", 0) > 0) {
//                intent.putExtra("play_sound", true);
//            }
//            if (adVar.lMW != null) {
//                intent.putExtra("key_realname_guide_helper", adVar.lMW);
//            }
//            startActivity(intent);
//            y yVar = new y();
//            yVar.field_mNativeUrl = this.lUA;
//            yVar.field_hbType = fVar.lPR;
//            yVar.field_receiveAmount = fVar.ceq;
//            yVar.field_receiveTime = System.currentTimeMillis();
//            yVar.field_hbStatus = fVar.cec;
//            yVar.field_receiveStatus = fVar.ced;
//            if (yVar.field_receiveAmount > 0) {
//                o.bVp().a(yVar);
//            }
//            finish();
//            return;
//        }
//        this.lMN.setBackgroundResource(e.lucky_money_receive_open_disabled_btn);
//        this.lMN.setText(i.lucky_money_open);
//        this.lMN.setOnClickListener(null);
//        this.lMN.setVisibility(8);
//        if (bk.bl(fVar.lNs)) {
//            this.iKV.setVisibility(8);
//        } else {
//            this.iKV.setText(fVar.lNs);
//            this.iKV.setVisibility(0);
//        }
//        this.lly.setText(fVar.lPS);
//        LayoutParams layoutParams = (LayoutParams) this.lMO.getLayoutParams();
//        layoutParams.bottomMargin = BackwardSupportUtil.b.b(this.mController.uMN, 30.0f);
//        this.lMO.setLayoutParams(layoutParams);
//        if (q.Gj().equals(this.kPX) || fVar.lPR == 1) {
//            this.lMO.setVisibility(0);
//            this.lMO.setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    Intent intent = new Intent();
//                    if (adVar.lMW != null) {
//                        intent.putExtra("key_realname_guide_helper", adVar.lMW);
//                    }
//                    intent.setClass(LuckyMoneyReceiveUI.this.mController.uMN, LuckyMoneyDetailUI.class);
//                    intent.putExtra("key_native_url", adVar.ceb);
//                    intent.putExtra("key_sendid", adVar.lMg);
//                    LuckyMoneyReceiveUI.this.startActivity(intent);
//                    LuckyMoneyReceiveUI.this.finish();
//                }
//            });
//            this.lMP.setVisibility(8);
//            return;
//        }
//        this.lMO.setVisibility(8);
//        this.lMP.setVisibility(0);
//    }
//
//    private int bgr() {
//        if (q.Gj().equals(this.kPX)) {
//            return 1;
//        }
//        return 0;
//    }
//
//    private static int tu(int i) {
//        if (i == 1) {
//            return 1;
//        }
//        if (i == 0) {
//            return 2;
//        }
//        return 0;
//    }
//}