//package io.merculet.wxbot.config;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.PorterDuff.Mode;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.media.AudioManager;
//import android.net.Uri;
//import android.os.Build.VERSION;
//import android.os.IBinder;
//import android.os.Looper;
//import android.os.PowerManager;
//import android.os.PowerManager.WakeLock;
//import android.os.SystemClock;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.text.SpannableString;
//import android.text.style.ForegroundColorSpan;
//import android.util.DisplayMetrics;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MenuItem.OnMenuItemClickListener;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnLongClickListener;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.FrameLayout;
//import android.widget.FrameLayout.LayoutParams;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import com.samsung.android.sdk.look.airbutton.SlookAirButtonRecentMediaAdapter;
//import com.tencent.mm.ac.a.d;
//import com.tencent.mm.ac.a.e;
//import com.tencent.mm.ac.a.f;
//import com.tencent.mm.ac.a.g;
//import com.tencent.mm.ac.a.h;
//import com.tencent.mm.ac.a.j;
//import com.tencent.mm.ac.a.k;
//import com.tencent.mm.h.a.aj;
//import com.tencent.mm.h.a.sh;
//import com.tencent.mm.sdk.b.c;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.an;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.x;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.ui.tools.n;
//import com.tencent.smtt.sdk.WebView;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Locale;
//
//public abstract class s {
//    private static boolean uMQ = false;
//    public static final int uNf = h.actionbar_title_single_text;
//    private static byte[] uNi = new byte[]{(byte) 0};
//    private static WakeLock wakeLock = null;
//    public int aTI = this.uMZ;
//    String className;
//    public View contentView;
//    private n drn;
//    AudioManager eJZ;
//    private LayoutInflater fhz;
//    private int kIR = 0;
//    public ActionBar mActionBar;
//    public Context mContext;
//    boolean nST = false;
//    protected ah ntK = new ah(Looper.getMainLooper());
//    private final long peu = 300;
//    private long pev = SystemClock.elapsedRealtime();
//    private int uGL = 0;
//    public ImageButton uGM;
//    TextView uGO;
//    LinkedList<a> uGV = new LinkedList();
//    View uMA;
//    private TextView uMB;
//    FrameLayout uMC;
//    public boolean uMD = true;
//    private String uME = " ";
//    private int uMF = 0;
//    private int uMG = 0;
//    private com.tencent.mm.ui.widget.a uMH = null;
//    private com.tencent.mm.ui.widget.a uMI = null;
//    private com.tencent.mm.ui.widget.a uMJ = null;
//    private com.tencent.mm.ui.widget.a uMK = null;
//    private boolean uML = false;
//    boolean uMM = false;
//    public AppCompatActivity uMN;
//    private boolean uMO;
//    private a uMP = new a();
//    private ArrayList<Dialog> uMR;
//    public TextView uMS;
//    private View uMT;
//    public View uMU;
//    public ImageView uMV;
//    TextView uMW;
//    int uMX = 0;
//    private ImageView uMY;
//    private int uMZ;
//    protected boolean uMo = false;
//    private View uMy;
//    public View uMz;
//    private int uNa;
//    private int uNb = 0;
//    private boolean uNc = false;
//    private c uNd = new c<sh>() {
//        {
//            this.udX = sh.class.getName().hashCode();
//        }
//
//        public final /* synthetic */ boolean a(com.tencent.mm.sdk.b.b bVar) {
//            com.tencent.mm.h.a.sh.a aVar = ((sh) bVar).cbS;
//            if (aVar.bGv == 2) {
//                String str = aVar.bGy;
//                int i = aVar.position;
//                y.i("MicroMsg.MMActivityController", "summerdiz E_BR_SHOWTYPE_TXTSTRIPE callback position[%d] noticeId[%s]", Integer.valueOf(i), str);
//                if (i <= 0 || i == s.this.uNb) {
//                    s.this.a(aVar.bGx, aVar.aoL, aVar.url, aVar.desc, str, i, false);
//                } else {
//                    y.i("MicroMsg.MMActivityController", "summerdiz E_BR_SHOWTYPE_TXTSTRIPE callback position not match[%d, %d] ignore display", Integer.valueOf(i), Integer.valueOf(s.this.uNb));
//                }
//            }
//            return false;
//        }
//    };
//    private int uNe = h.actionbar_title;
//    private int uNg = -1;
//    public int uNh = 0;
//    private a uNj;
//    private MenuItem uNk;
//    private Runnable uNl = new Runnable() {
//        public final void run() {
//            s.this.uMN.getWindow().setFlags(1024, 1024);
//            if (s.this.mActionBar != null) {
//                s.this.mActionBar.hide();
//            }
//        }
//    };
//    private Runnable uNm = new Runnable() {
//        public final void run() {
//            if (s.this.mActionBar != null) {
//                s.this.mActionBar.show();
//            }
//        }
//    };
//
//    /* renamed from: com.tencent.mm.ui.s$3 */
//    class AnonymousClass3 implements OnClickListener {
//        final /* synthetic */ Runnable uNp;
//        final /* synthetic */ Runnable uNq;
//
//        AnonymousClass3(Runnable runnable, Runnable runnable2) {
//            this.uNp = runnable;
//            this.uNq = runnable2;
//        }
//
//        public final void onClick(View view) {
//            if (SystemClock.elapsedRealtime() - s.this.pev < 300) {
//                if (this.uNp != null) {
//                    this.uNp.run();
//                }
//            } else if (this.uNq != null) {
//                this.uNq.run();
//            }
//            s.this.pev = SystemClock.elapsedRealtime();
//        }
//    }
//
//    public static final class a {
//        boolean aoL = true;
//        boolean bIU = true;
//        OnMenuItemClickListener fkl;
//        OnLongClickListener kfL;
//        String text;
//        int textColor;
//        int uHd = -1;
//        int uHe;
//        View uHf;
//        View uHg;
//        Drawable uNt;
//        b uNu = b.NORMAL;
//        boolean uNv = false;
//    }
//
//    public enum b {
//        NORMAL,
//        GREEN,
//        RED,
//        BLACK,
//        TRANSPARENT,
//        TRANSPARENT_RED_TEXT,
//        TRANSPARENT_GREEN_TEXT,
//        GOLDEN,
//        TRANSPARENT_GOLD_TEXT,
//        TRANSPARENT_GB_GREEN_TEXT,
//        TRANSPARENT_BG_BLACK_TEXT
//    }
//
//    protected abstract String bdI();
//
//    protected abstract boolean czg();
//
//    protected abstract void dealContentView(View view);
//
//    protected abstract String getClassName();
//
//    protected abstract int getLayoutId();
//
//    protected abstract View getLayoutView();
//
//    protected abstract void onCreateBeforeSetContentView();
//
//    public abstract void onKeyboardStateChanged();
//
//    static /* synthetic */ boolean f(s sVar) {
//        return (sVar.uMN.getWindow().getAttributes().flags & 1024) != 0;
//    }
//
//    static /* synthetic */ void g(s sVar) {
//        sVar.uNh = 2;
//        sVar.onKeyboardStateChanged();
//    }
//
//    static /* synthetic */ void h(s sVar) {
//        sVar.uNh = 1;
//        sVar.onKeyboardStateChanged();
//    }
//
//    private void a(int i, final boolean z, final String str, final String str2, final String str3, final int i2, boolean z2) {
//        y.i("MicroMsg.MMActivityController", "initNotifyView viewid[%d], visible[%b], uithread[%b], noticeid[%s], position[%d], notifyView[%s]", Integer.valueOf(i), Boolean.valueOf(z), Boolean.valueOf(z2), str3, Integer.valueOf(i2), this.uMA);
//        if (!needShowIdcError()) {
//            return;
//        }
//        if (!z && this.uMA == null) {
//            return;
//        }
//        if (this.mActionBar == null || this.mActionBar.isShowing()) {
//            if (this.uMC == null) {
//                this.uMC = (FrameLayout) this.contentView.findViewById(g.mm_content_fl);
//            }
//            if (this.uMA != null) {
//                this.uMC.removeView(this.uMA);
//            }
//            int i3 = h.mmnotify_view;
//            if (i <= 0) {
//                i = i3;
//            }
//            this.uMA = this.fhz.inflate(i, null);
//            this.uMB = (TextView) this.uMA.findViewById(g.notify_text);
//            this.uMA.findViewById(g.notify_btn).setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    aj ajVar = new aj();
//                    ajVar.bGr.type = 1;
//                    ajVar.bGr.bGt = str3;
//                    ajVar.bGr.position = i2;
//                    com.tencent.mm.sdk.b.a.udP.m(ajVar);
//                    s.this.uMA.setVisibility(8);
//                }
//            });
//            this.uMA.setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    if (str != null) {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        intent.setData(Uri.parse(str));
//                        s.this.mContext.startActivity(intent);
//                    }
//                }
//            });
//            LayoutParams layoutParams = new LayoutParams(-1, -2);
//            if (this.uNc && VERSION.SDK_INT >= 16) {
//                layoutParams.setMargins(0, com.tencent.mm.sdk.platformtools.BackwardSupportUtil.b.b(this.uMN, 48.0f), 0, 0);
//                y.i("MicroMsg.MMActivityController", "summerdiz initNotifyView [%d, %d]", Integer.valueOf(layoutParams.height), Integer.valueOf(layoutParams.topMargin));
//            }
//            this.uMC.addView(this.uMA, this.uMC.getChildCount(), layoutParams);
//            if (this.uMA == null) {
//                return;
//            }
//            if (z2) {
//                this.uMA.setVisibility(z ? 0 : 8);
//                String string = this.mContext.getString(k.idc_url);
//                if (bk.bl(str2)) {
//                    str2 = this.mContext.getString(k.disaster_default_tips_default);
//                }
//                if (str != null) {
//                    SpannableString spannableString = new SpannableString(str2 + string);
//                    spannableString.setSpan(new ForegroundColorSpan(-10119449), str2.length(), string.length() + str2.length(), 33);
//                    this.uMB.setText(spannableString);
//                } else {
//                    y.i("MicroMsg.MMActivityController", "summerdiz url is null 1");
//                    this.uMB.setText(str2);
//                }
//                this.uMA.invalidate();
//                this.uMC.invalidate();
//                return;
//            }
//            this.uMA.post(new Runnable() {
//                public final void run() {
//                    s.this.uMA.setVisibility(z ? 0 : 8);
//                    String string = s.this.mContext.getString(k.idc_url);
//                    String string2 = bk.bl(str2) ? s.this.mContext.getString(k.disaster_default_tips_default) : str2;
//                    if (str != null) {
//                        SpannableString spannableString = new SpannableString(string2 + string);
//                        spannableString.setSpan(new ForegroundColorSpan(-10119449), string2.length(), string2.length() + string.length(), 33);
//                        s.this.uMB.setText(spannableString);
//                    } else {
//                        y.i("MicroMsg.MMActivityController", "summerdiz url is null 2");
//                        s.this.uMB.setText(string2);
//                    }
//                    s.this.uMA.invalidate();
//                    s.this.uMC.invalidate();
//                }
//            });
//            return;
//        }
//        y.i("MicroMsg.MMActivityController", "initNotifyView mActionBar not showing");
//    }
//
//    private View findViewById(int i) {
//        View findViewById = this.contentView.findViewById(i);
//        return findViewById != null ? findViewById : this.uMN.findViewById(i);
//    }
//
//    public final void setBackGroundColorResource(int i) {
//        if (this.contentView != null) {
//            if (this.uMC == null) {
//                this.uMC = (FrameLayout) this.contentView.findViewById(g.mm_content_fl);
//            }
//            this.uMC.setBackgroundResource(i);
//            this.uMz.setBackgroundResource(i);
//        }
//    }
//
//    public final void ap(int i, boolean z) {
//        this.uNb = i;
//        this.uNc = z;
//    }
//
//    public static Locale initLanguage(Context context) {
//        return by(context, x.d(context.getSharedPreferences(ae.cqR(), 0)));
//    }
//
//    public static Locale by(Context context, String str) {
//        if (str.equals("language_default")) {
//            x.a(context, Locale.ENGLISH);
//            return Locale.getDefault();
//        }
//        Locale Zi = x.Zi(str);
//        x.a(context, Zi);
//        return Zi;
//    }
//
//    public ActionBar getSupportActionBar() {
//        return this.uMN.getSupportActionBar();
//    }
//
//    public final void a(Context context, AppCompatActivity appCompatActivity) {
//        this.mContext = appCompatActivity;
//        this.uMN = appCompatActivity;
//        boolean z = ae.getContext().getSharedPreferences(ae.cqR() + "_redesign", 4).getBoolean("dark_actionbar_init", false);
//        boolean z2 = ae.getContext().getSharedPreferences(ae.cqR() + "_redesign", 4).getBoolean("dark_actionbar", false);
//        if (!(z || z2)) {
//            ae.getContext().getSharedPreferences(ae.cqR() + "_redesign", 4).edit().putBoolean("dark_actionbar", true).commit();
//            ae.getContext().getSharedPreferences(ae.cqR() + "_redesign", 4).edit().putBoolean("dark_actionbar_init", true).commit();
//        }
//        onCreateBeforeSetContentView();
//        this.className = getClass().getName();
//        ai.be(3, this.className);
//        initLanguage(context);
//        this.eJZ = (AudioManager) this.mContext.getSystemService(SlookAirButtonRecentMediaAdapter.AUDIO_TYPE);
//        int layoutId = getLayoutId();
//        this.fhz = LayoutInflater.from(this.mContext);
//        this.contentView = com.tencent.mm.kiss.a.b.ED().a(appCompatActivity, "R.layout.mm_activity", h.mm_activity);
//        this.uMy = this.contentView.findViewById(g.mm_trans_layer);
//        this.uMC = (FrameLayout) this.contentView.findViewById(g.mm_content_fl);
//        this.kIR = this.mContext.getResources().getDimensionPixelSize(e.SmallBtnPadding);
//        if (layoutId != -1) {
//            this.uMz = getLayoutView();
//            if (this.uMz == null) {
//                this.uMz = this.fhz.inflate(getLayoutId(), null);
//            } else if (this.uMz.getParent() != null) {
//                ((ViewGroup) this.uMz.getParent()).removeView(this.uMz);
//            }
//            this.uMC.addView(this.uMz, 0);
//        }
//        dealContentView(this.contentView);
//        if (czg()) {
//            int dimensionPixelSize;
//            ak.g(ak.a(this.uMN.getWindow(), this.uMz), this.uMz);
//            ((ViewGroup) this.uMz.getParent()).removeView(this.uMz);
//            ((ViewGroup) this.uMN.getWindow().getDecorView()).addView(this.uMz, 0);
//            layoutId = com.tencent.mm.cb.a.fromDPToPix(this.mContext, 25);
//            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
//            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
//                dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(e.DefaultActionbarHeightLand);
//            } else {
//                dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(e.DefaultActionbarHeightPort);
//            }
//            this.uMz.setPadding(this.uMz.getPaddingLeft(), dimensionPixelSize + (layoutId + this.uMz.getPaddingTop()), this.uMz.getPaddingRight(), this.uMz.getPaddingBottom());
//        }
//        this.mActionBar = getSupportActionBar();
//        y.d("MicroMsg.MMActivityController", "onCreate, before.");
//        X(appCompatActivity);
//        this.uMZ = ap.ab(this.mContext, e.DefaultActionbarHeightPort);
//        this.uNa = ap.ab(this.mContext, e.SmallActionbarHeight);
//        if (this.uMC == null || !(this.uMC instanceof LayoutListenerView)) {
//            y.w("MicroMsg.MMActivityController", "layoutListenerView is not right");
//        } else {
//            ((LayoutListenerView) this.uMC).setOnResizedListener(new LayoutListenerView.c() {
//                private final int uNr = com.tencent.mm.cb.a.fromDPToPix(s.this.mContext, 100);
//
//                public final void onSizeChanged(int i, int i2, int i3, int i4) {
//                    if (!s.f(s.this) && i != 0 && i2 != 0 && i3 != 0 && i4 != 0 && i == i3) {
//                        if (i2 > i4 && i2 - i4 > this.uNr) {
//                            s.g(s.this);
//                        } else if (i4 > i2 && i4 - i2 > this.uNr) {
//                            s.h(s.this);
//                        }
//                    }
//                }
//            });
//        }
//        aj ajVar = new aj();
//        ajVar.bGr.type = 2;
//        ajVar.bGr.position = this.uNb;
//        com.tencent.mm.sdk.b.a.udP.m(ajVar);
//        if (ajVar.bGs.bGv == 2) {
//            long currentTimeMillis = System.currentTimeMillis();
//            String str = ajVar.bGs.bGy;
//            int i = ajVar.bGs.position;
//            y.i("MicroMsg.MMActivityController", "summerdiz E_BR_SHOWTYPE_TXTSTRIPE onCreate position[%d], noticeId[%s] stack[%s]", Integer.valueOf(i), str, bk.csb());
//            if (i <= 0 || i == this.uNb) {
//                a(ajVar.bGs.bGx, ajVar.bGs.aoL, ajVar.bGs.url, ajVar.bGs.desc, str, i, true);
//            } else {
//                y.i("MicroMsg.MMActivityController", "summerdiz E_BR_SHOWTYPE_TXTSTRIPE onCreate position not match[%d, %d] ignore display", Integer.valueOf(i), Integer.valueOf(this.uNb));
//            }
//            y.v("MicroMsg.INIT", "KEVIN MMActivity onCreate initNotifyView:" + (System.currentTimeMillis() - currentTimeMillis));
//        }
//        if (VERSION.SDK_INT >= 21) {
//            if (this.uGL == 0) {
//                this.uGL = czv();
//            }
//            Window window = appCompatActivity.getWindow();
//            window.clearFlags(201326592);
//            window.addFlags(Integer.MIN_VALUE);
//            l(appCompatActivity, this.uGL);
//            czC();
//        }
//    }
//
//    public boolean noActionBar() {
//        return false;
//    }
//
//    public final int czv() {
//        boolean z = ae.getContext().getSharedPreferences(ae.cqR() + "_redesign", 4).getBoolean("dark_actionbar", false);
//        this.nST = z;
//        if (this.uGL == 0) {
//            if (z) {
//                this.uGL = this.mContext.getResources().getColor(d.dark_actionbar_color);
//            } else {
//                this.uGL = this.mContext.getResources().getColor(d.normal_actionbar_color);
//            }
//        }
//        return this.uGL;
//    }
//
//    public final void X(Activity activity) {
//        if (this.mActionBar != null && !noActionBar()) {
//            y.d("MicroMsg.MMActivityController", "onCreate, after");
//            if (this.uGL == 0) {
//                this.uGL = czv();
//            }
//            this.nST = an.Gf(this.uGL);
//            ta(this.uGL);
//            this.mActionBar.setLogo(new ColorDrawable(this.uMN.getResources().getColor(17170445)));
//            this.mActionBar.dZ();
//            this.mActionBar.setDisplayHomeAsUpEnabled(false);
//            this.mActionBar.dY();
//            this.mActionBar.ea();
//            this.mActionBar.setIcon(f.transparent_background);
//            if (this.uNg == -1) {
//                this.mActionBar.setCustomView(y.gt(this.uMN).inflate(this.uNe, new LinearLayout(this.uMN), false));
//            } else {
//                this.mActionBar.setCustomView(y.gt(this.uMN).inflate(this.uNg, new LinearLayout(this.uMN), false));
//            }
//            this.uMS = (TextView) findViewById(16908308);
//            this.uMW = (TextView) findViewById(16908309);
//            this.uMT = findViewById(g.title_ll);
//            this.uMU = findViewById(g.actionbar_up_indicator);
//            this.uMV = (ImageView) findViewById(g.actionbar_up_indicator_btn);
//            if (this.uMV != null) {
//                this.uMV.setContentDescription(this.uMN.getString(k.app_back));
//            }
//            if (this.uMS != null) {
//                this.uMS.setText(k.app_name);
//            }
//            if (activity.getClass().getName() == "WebViewUI") {
//                if (this.uMV != null) {
//                    this.uMV.setVisibility(0);
//                }
//                if (this.uMU != null) {
//                    this.uMU.setVisibility(0);
//                }
//            } else if (activity instanceof MMActivity) {
//                if (this.uMV != null) {
//                    this.uMV.setVisibility(0);
//                }
//                if (this.uMU != null) {
//                    this.uMU.setVisibility(0);
//                }
//                if (this.uMS != null) {
//                    this.uMS.setVisibility(0);
//                }
//            } else {
//                if (this.uMV != null) {
//                    this.uMV.setVisibility(8);
//                }
//                if (this.uMU != null) {
//                    this.uMU.setVisibility(8);
//                }
//            }
//            czB();
//        }
//    }
//
//    public final void setScreenEnable(boolean z) {
//        boolean z2 = true;
//        this.uMD = z;
//        if (this.uMy == null && this.contentView != null) {
//            this.uMy = this.contentView.findViewById(g.mm_trans_layer);
//        }
//        if (this.uMy == null) {
//            y.e("MicroMsg.MMActivityController", "jacks error npe translayer !");
//            return;
//        }
//        this.uMy.setFocusable(!z);
//        View view = this.uMy;
//        if (z) {
//            z2 = false;
//        }
//        view.setFocusableInTouchMode(z2);
//        if (z) {
//            synchronized (uNi) {
//                if (wakeLock == null || !wakeLock.isHeld()) {
//                    y.w("MicroMsg.MMActivityController", "repeatedly release screen off wakelock from object: %s, drop this call.", toString());
//                } else {
//                    wakeLock.release();
//                    y.i("MicroMsg.MMActivityController", "after release screen off wakelock from object: %s, isHeld: %s", toString(), Boolean.valueOf(wakeLock.isHeld()));
//                }
//            }
//            return;
//        }
//        AppCompatActivity appCompatActivity = this.uMN;
//        synchronized (uNi) {
//            if (wakeLock == null) {
//                wakeLock = ((PowerManager) appCompatActivity.getSystemService("power")).newWakeLock(32, "screen Lock");
//            }
//            if (wakeLock.isHeld()) {
//                y.w("MicroMsg.MMActivityController", "repeatedly acquire screen off wakelock from object: %s, drop this call.", toString());
//            } else {
//                wakeLock.acquire();
//                y.i("MicroMsg.MMActivityController", "after acquire screen off wakelock from object: %s, isHeld: %s", toString(), Boolean.valueOf(wakeLock.isHeld()));
//            }
//        }
//    }
//
//    public static boolean czw() {
//        boolean isHeld;
//        synchronized (uNi) {
//            isHeld = wakeLock != null ? wakeLock.isHeld() : false;
//        }
//        return isHeld;
//    }
//
//    protected static int getForceOrientation() {
//        return -1;
//    }
//
//    public final void onStart() {
//        this.uMo = this.mContext.getSharedPreferences(ae.cqR(), 0).getBoolean("settings_landscape_mode", false);
//        if (this.uMo) {
//            this.uMN.setRequestedOrientation(-1);
//        } else {
//            this.uMN.setRequestedOrientation(1);
//        }
//    }
//
//    public final boolean getLandscapeMode() {
//        return this.uMo;
//    }
//
//    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
//        if (i != 82 || keyEvent.getAction() != 1) {
//            return false;
//        }
//        if (this.uNj == null || !this.uNj.bIU) {
//            return true;
//        }
//        a(this.uNk, this.uNj);
//        return true;
//    }
//
//    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
//        com.tencent.mm.compatible.b.f.ym();
//        com.tencent.mm.compatible.b.f.ym();
//        if (!this.uMO || this.drn == null || !this.drn.onKeyDown(i, keyEvent)) {
//            return false;
//        }
//        y.d("MicroMsg.MMActivityController", "match search view on key down");
//        if (ae.getContext().getSharedPreferences(ae.cqR() + "_redesign", 4).getBoolean("dark_actionbar", false)) {
//            ta(this.uMN.getResources().getColor(d.dark_actionbar_color));
//        } else {
//            ta(this.uMN.getResources().getColor(d.normal_actionbar_color));
//        }
//        return true;
//    }
//
//    public boolean needShowIdcError() {
//        return true;
//    }
//
//    public final void onResume() {
//        activateBroadcast(true);
//        com.tencent.mm.sdk.b.a.udP.c(this.uNd);
//        aj ajVar = new aj();
//        ajVar.bGr.type = 2;
//        ajVar.bGr.position = this.uNb;
//        com.tencent.mm.sdk.b.a.udP.m(ajVar);
//        if (ajVar.bGs.bGv == 2) {
//            long currentTimeMillis = System.currentTimeMillis();
//            String str = ajVar.bGs.bGy;
//            int i = ajVar.bGs.position;
//            y.i("MicroMsg.MMActivityController", "summerdiz E_BR_SHOWTYPE_TXTSTRIPE onResume position[%d], noticeId[%s]", Integer.valueOf(i), str);
//            if (i <= 0 || i == this.uNb) {
//                a(ajVar.bGs.bGx, ajVar.bGs.aoL, ajVar.bGs.url, ajVar.bGs.desc, str, i, true);
//            } else {
//                y.i("MicroMsg.MMActivityController", "summerdiz E_BR_SHOWTYPE_TXTSTRIPE onResume position not match[%d, %d] ignore display", Integer.valueOf(i), Integer.valueOf(this.uNb));
//            }
//            y.v("MicroMsg.INIT", "KEVIN MMActivity onResume initNotifyView:" + (System.currentTimeMillis() - currentTimeMillis));
//        }
//    }
//
//    public final void addDialog(Dialog dialog) {
//        if (dialog != null) {
//            if (this.uMR == null) {
//                this.uMR = new ArrayList();
//            }
//            this.uMR.add(dialog);
//        }
//    }
//
//    public final void onDestroy() {
//        if (this.uMR != null) {
//            int size = this.uMR.size();
//            for (int i = 0; i < size; i++) {
//                Dialog dialog = (Dialog) this.uMR.get(i);
//                if (dialog != null && dialog.isShowing()) {
//                    dialog.dismiss();
//                }
//            }
//            this.uMR.clear();
//            this.uMR = null;
//        }
//        if (com.tencent.mm.protocal.d.spd || com.tencent.mm.sdk.a.b.cqk()) {
//            an.fC(this.mContext);
//        }
//    }
//
//    public final void activateBroadcast(boolean z) {
//        if (uMQ || !z) {
//            com.tencent.mm.ui.base.x.b(z, new Intent().putExtra("classname", getClassName() + bdI()));
//        } else {
//            com.tencent.mm.ui.base.x.b(z, new Intent().putExtra("classname", getClassName()).putExtra("main_process", false));
//        }
//    }
//
//    public static void czj() {
//        uMQ = true;
//    }
//
//    public final void onPause() {
//        activateBroadcast(false);
//        com.tencent.mm.sdk.b.a.udP.d(this.uNd);
//        if (com.tencent.mm.protocal.d.spd || com.tencent.mm.sdk.a.b.cqk()) {
//            an.fD(this.mContext);
//        }
//    }
//
//    public final boolean onCreateOptionsMenu(Menu menu) {
//        y.d("MicroMsg.MMActivityController", "on create option menu, menuCache size:%d", Integer.valueOf(this.uGV.size()));
//        if (this.mActionBar == null || this.uGV.size() == 0) {
//            y.w("MicroMsg.MMActivityController", "error, mActionBar is null or cache size:%d", Integer.valueOf(this.uGV.size()));
//            return false;
//        }
//        this.uNj = null;
//        this.uNk = null;
//        if (this.mActionBar.getHeight() == 0) {
//            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
//            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
//                this.mContext.getResources().getDimensionPixelSize(e.DefaultActionbarHeightLand);
//            } else {
//                this.mContext.getResources().getDimensionPixelSize(e.DefaultActionbarHeightPort);
//            }
//        }
//        Iterator it = this.uGV.iterator();
//        while (it.hasNext()) {
//            final a aVar = (a) it.next();
//            String str;
//            if (aVar.uHd == 16908332) {
//                y.v("MicroMsg.MMActivityController", "match back option menu, continue");
//            } else if (aVar.uHd == g.menu_search) {
//                boolean z;
//                str = "MicroMsg.MMActivityController";
//                String str2 = "match search menu, enable search view[%B], search view helper is null[%B]";
//                Object[] objArr = new Object[2];
//                objArr[0] = Boolean.valueOf(this.uMO);
//                if (this.drn == null) {
//                    z = true;
//                } else {
//                    z = false;
//                }
//                objArr[1] = Boolean.valueOf(z);
//                y.v(str, str2, objArr);
//                if (this.uMO && this.drn != null) {
//                    this.drn.a(this.uMN, menu);
//                }
//            } else {
//                final MenuItem add = menu.add(0, aVar.uHd, 0, aVar.text);
//                str = getClass().getName();
//                if (add == null) {
//                    y.w("MicroMsg.MenuItemUtil", "fixTitleCondensed fail, item is null");
//                } else if (add.getTitleCondensed() == null) {
//                    y.w("MicroMsg.MenuItemUtil", "%s title condensed is null, fix it", str);
//                    add.setTitleCondensed("");
//                } else if (!(add.getTitleCondensed() instanceof String)) {
//                    y.w("MicroMsg.MenuItemUtil", "%s title condensed is not String type, cur type[%s], cur value[%s], fix it", str, add.getTitleCondensed().getClass().getName(), add.getTitleCondensed());
//                    add.setTitleCondensed(add.getTitleCondensed().toString());
//                }
//                AnonymousClass11 anonymousClass11 = new OnClickListener() {
//                    public final void onClick(View view) {
//                        s.this.a(add, aVar);
//                    }
//                };
//                AnonymousClass12 anonymousClass12 = new OnLongClickListener() {
//                    public final boolean onLongClick(View view) {
//                        return s.a(s.this, view, aVar);
//                    }
//                };
//                if (aVar.uHe == 0 && aVar.uNt == null) {
//                    if (aVar.uHf == null) {
//                        aVar.uHf = View.inflate(this.mContext, h.action_option_view, null);
//                    }
//                    this.uGO = (TextView) aVar.uHf.findViewById(g.action_option_text);
//                    this.uGO.setVisibility(0);
//                    if (aVar.uNu == b.RED || aVar.uNu == b.TRANSPARENT_RED_TEXT) {
//                        this.uGO.setTextColor(com.tencent.mm.cb.a.h(this.uMN, d.red_text_color_selector));
//                    } else if (aVar.uNu == b.GREEN || aVar.uNu == b.TRANSPARENT_GREEN_TEXT) {
//                        this.uGO.setTextColor(com.tencent.mm.cb.a.h(this.uMN, d.green_text_color_selector));
//                    } else {
//                        if (aVar.uNu != b.BLACK) {
//                            if (aVar.uNu == b.TRANSPARENT_GOLD_TEXT) {
//                                this.uGO.setTextColor(this.uMN.getResources().getColor(d.lucky_money_goldstyle_text_color));
//                            } else if (aVar.uNu == b.GOLDEN) {
//                                this.uGO.setTextColor(-2601405);
//                            } else if (this.uMX != 0) {
//                                this.uGO.setTextColor(this.uMX);
//                            } else if (this.nST) {
//                                this.uGO.setTextColor(com.tencent.mm.cb.a.h(this.uMN, d.white_text_color_selector));
//                            }
//                        }
//                        this.uGO.setTextColor(com.tencent.mm.cb.a.h(this.uMN, d.black_text_color_selector));
//                    }
//                    this.uGO.setText(aVar.text);
//                    if (aVar.textColor != 0) {
//                        this.uGO.setTextColor(aVar.textColor);
//                    }
//                    this.uGO.setOnClickListener(anonymousClass11);
//                    this.uGO.setOnLongClickListener(anonymousClass12);
//                    this.uGO.setEnabled(aVar.bIU);
//                    android.support.v4.view.f.a(add, aVar.uHf);
//                } else {
//                    if (aVar.uHg == null) {
//                        aVar.uHg = View.inflate(this.mContext, h.action_option_view, null);
//                    }
//                    ImageView imageView = (ImageView) aVar.uHg.findViewById(g.divider);
//                    if (imageView != null) {
//                        imageView.setVisibility(8);
//                    }
//                    this.uGM = (ImageButton) aVar.uHg.findViewById(g.action_option_icon);
//                    this.uGM.setVisibility(0);
//                    if (aVar.uNt != null) {
//                        this.uGM.setImageDrawable(aVar.uNt);
//                    } else {
//                        this.uGM.setImageResource(aVar.uHe);
//                    }
//                    if (this.nST) {
//                        if (this.uMX != 0) {
//                            this.uGM.getDrawable().setColorFilter(this.uMX, Mode.SRC_ATOP);
//                        } else {
//                            this.uGM.getDrawable().setColorFilter(-1, Mode.SRC_ATOP);
//                        }
//                    } else if (this.uMX != 0) {
//                        this.uGM.getDrawable().setColorFilter(this.uMX, Mode.SRC_ATOP);
//                    } else {
//                        this.uGM.getDrawable().setColorFilter(WebView.NIGHT_MODE_COLOR, Mode.SRC_ATOP);
//                    }
//                    this.uGM.setOnClickListener(anonymousClass11);
//                    this.uGM.setEnabled(aVar.bIU);
//                    this.uGM.setContentDescription(aVar.text);
//                    if (aVar.kfL != null) {
//                        this.uGM.setOnLongClickListener(anonymousClass12);
//                    }
//                    this.uMY = (ImageView) aVar.uHg.findViewById(g.dot_iv);
//                    if (this.uMY != null) {
//                        if (aVar.uNv) {
//                            this.uMY.setVisibility(0);
//                        } else {
//                            this.uMY.setVisibility(8);
//                        }
//                    }
//                    android.support.v4.view.f.a(add, aVar.uHg);
//                }
//                add.setEnabled(aVar.bIU);
//                add.setVisible(aVar.aoL);
//                android.support.v4.view.f.a(add, 2);
//                if (aVar.uHe == f.mm_title_btn_menu) {
//                    this.uNj = aVar;
//                    this.uNk = add;
//                }
//                y.v("MicroMsg.MMActivityController", "set %d %s option menu enable %B, visible %B", Integer.valueOf(aVar.uHd), aVar.text, Boolean.valueOf(aVar.bIU), Boolean.valueOf(aVar.aoL));
//            }
//        }
//        return true;
//    }
//
//    public final boolean onPrepareOptionsMenu(Menu menu) {
//        y.d("MicroMsg.MMActivityController", "on prepare option menu");
//        if (this.uMO && this.drn != null) {
//            this.drn.a(this.uMN, menu);
//        }
//        return true;
//    }
//
//    public final boolean onOptionsItemSelected(MenuItem menuItem) {
//        y.v("MicroMsg.MMActivityController", "on options item selected");
//        if (!this.uMD) {
//            y.w("MicroMsg.MMActivityController", "onOptionsItemSelected screen not enable.");
//            return true;
//        } else if (menuItem.getItemId() == this.uMP.uHd && this.uMP.bIU) {
//            a(menuItem, this.uMP);
//            return true;
//        } else {
//            Iterator it = this.uGV.iterator();
//            while (it.hasNext()) {
//                a aVar = (a) it.next();
//                if (menuItem.getItemId() == aVar.uHd) { //aVar.uHd == 0
//                    y.d("MicroMsg.MMActivityController", "on option menu %d click", Integer.valueOf(menuItem.getItemId()));
//                    a(menuItem, aVar);
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//
//    public final boolean callBackMenu() {
//        if (this.uMP == null || !this.uMP.bIU) {
//            return false;
//        }
//        a(null, this.uMP);
//        return true;
//    }
//
//    private void a(MenuItem menuItem, a aVar) {
//        if (!this.uMD) {
//            y.w("MicroMsg.MMActivityController", "callMenuCallback screen not enable.");
//        } else if (aVar.fkl != null) {
//            aVar.fkl.onMenuItemClick(menuItem);
//        }
//    }
//
//    public final void fullScreenNoTitleBar(boolean z) {
//        if (z) {
//            if (this.mActionBar != null) {
//                this.mActionBar.hide();
//            }
//            this.ntK.removeCallbacks(this.uNm);
//            this.ntK.removeCallbacks(this.uNl);
//            this.ntK.postDelayed(this.uNl, 256);
//            return;
//        }
//        this.uMN.getWindow().clearFlags(1024);
//        this.ntK.removeCallbacks(this.uNl);
//        this.ntK.removeCallbacks(this.uNm);
//        this.ntK.postDelayed(this.uNm, 256);
//    }
//
//    public final void czx() {
//        if (this.mActionBar != null) {
//            this.mActionBar.hide();
//        }
//        this.ntK.removeCallbacks(this.uNm);
//        this.ntK.removeCallbacks(this.uNl);
//        this.ntK.postDelayed(this.uNl, 0);
//    }
//
//    public final void setTitleVisibility(int i) {
//        if (this.mActionBar != null) {
//            if (i == 0) {
//                this.mActionBar.show();
//                l(this.uMN, this.uGL);
//                return;
//            }
//            this.mActionBar.hide();
//            l(this.uMN, this.uMN.getResources().getColor(d.black));
//        }
//    }
//
//    public final void setMMTitle(String str) {
//        if (this.mActionBar != null) {
//            this.uME = str;
//            if (com.tencent.mm.cb.a.fh(this.uMN)) {
//                this.uMS.setTextSize(0, ((float) com.tencent.mm.cb.a.ab(this.uMN, e.ActionBarTextSize)) * com.tencent.mm.cb.a.ff(this.uMN));
//            }
//            czA();
//            updateDescription(str);
//        }
//    }
//
//    public final void N(CharSequence charSequence) {
//        if (this.mActionBar != null) {
//            this.uME = charSequence.toString();
//            this.uMS.setText(charSequence);
//            if (com.tencent.mm.cb.a.fh(this.uMN)) {
//                this.uMS.setTextSize(0, ((float) com.tencent.mm.cb.a.ab(this.uMN, e.ActionBarTextSize)) * com.tencent.mm.cb.a.ff(this.uMN));
//            }
//            updateDescription(charSequence.toString());
//        }
//    }
//
//    protected final void updateDescription(String str) {
//        com.tencent.mm.ui.a.a cAj = a.uRw;
//        AppCompatActivity appCompatActivity = this.uMN;
//        if (!cAj.cAi() && !bk.bl(str) && appCompatActivity != null) {
//            appCompatActivity.getWindow().getDecorView().setContentDescription(appCompatActivity.getString(k.common_enter_activity) + str);
//        }
//    }
//
//    public final void setMMTitle(int i) {
//        setMMTitle(this.mContext.getString(i));
//    }
//
//    public final void setMMSubTitle(String str) {
//        if (this.mActionBar != null) {
//            if (str == null) {
//                this.uMW.setVisibility(8);
//                return;
//            }
//            this.uMW.setText(str);
//            if (com.tencent.mm.cb.a.fh(this.uMN)) {
//                this.uMW.setTextSize(1, 14.0f);
//                this.uMS.setTextSize(1, 18.0f);
//            }
//            this.uMW.setVisibility(0);
//            updateDescription(str);
//        }
//    }
//
//    public final void setMMSubTitle(int i) {
//        if (this.mActionBar != null) {
//            this.uMW.setText(this.mContext.getString(i));
//            if (com.tencent.mm.cb.a.fh(this.uMN)) {
//                this.uMW.setTextSize(1, 14.0f);
//                this.uMS.setTextSize(1, 18.0f);
//            }
//            this.uMW.setVisibility(0);
//            updateDescription(this.mContext.getString(i));
//        }
//    }
//
//    public final void setTitleLogo(int i, int i2) {
//        if (this.mActionBar != null) {
//            if (i == 0) {
//                this.uMF = 0;
//                this.uMH = null;
//            } else if (this.uMF != i) {
//                this.uMF = i;
//                this.uMH = fj(this.mContext.getResources().getDimensionPixelSize(e.BigTextSize), this.uMF);
//            }
//            if (i2 == 0) {
//                this.uMG = 0;
//                this.uMI = null;
//            } else if (this.uMG != i2) {
//                this.uMG = i2;
//                this.uMI = fj(this.mContext.getResources().getDimensionPixelSize(e.BigTextSize), this.uMG);
//            }
//            czA();
//        }
//    }
//
//    public static void czy() {
//    }
//
//    public static void czz() {
//    }
//
//    private com.tencent.mm.ui.widget.a fj(int i, int i2) {
//        Drawable drawable = this.mContext.getResources().getDrawable(i2);
//        if (this.nST) {
//            drawable = an.an(this.uMN, i2);
//        }
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        com.tencent.mm.ui.widget.a aVar = new com.tencent.mm.ui.widget.a(drawable);
//        aVar.wjc = (drawable.getIntrinsicHeight() - i) / 2;
//        return aVar;
//    }
//
//    final void czA() {
//        int i;
//        int i2;
//        int i3;
//        int i4;
//        String str = "%s";
//        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(e.ActionBarTextSize);
//        if (this.uMF != 0) {
//            str = "# " + str;
//        }
//        if (this.uMG != 0) {
//            str = str + " #";
//            i = 1;
//        } else {
//            i = 0;
//        }
//        if (this.uML) {
//            str = str + " #";
//            i += 2;
//            i2 = 1;
//        } else {
//            i2 = 0;
//        }
//        if (this.uMM) {
//            str = str + " #";
//            i2 += 2;
//            i3 = 1;
//            i4 = i + 2;
//        } else {
//            i3 = 0;
//            i4 = i;
//        }
//        String format = String.format(str, new Object[]{this.uME});
//        y.v("MicroMsg.MMActivityController", "span title format %s", str);
//        CharSequence c = com.tencent.mm.ui.e.c.b.c(format, dimensionPixelSize);
//        if (c instanceof SpannableString) {
//            int length;
//            SpannableString spannableString = (SpannableString) c;
//            if (this.uMF != 0) {
//                spannableString.setSpan(this.uMH, 0, 1, 33);
//            }
//            if (this.uMG != 0) {
//                length = spannableString.length() - i4;
//                spannableString.setSpan(this.uMI, length, length + 1, 33);
//            }
//            if (this.uML) {
//                if (this.uMJ == null) {
//                    this.uMJ = fj(dimensionPixelSize, j.chat_mute_notify_title_icon);
//                }
//                length = spannableString.length() - i2;
//                spannableString.setSpan(this.uMJ, length, length + 1, 33);
//            }
//            if (this.uMM) {
//                if (this.uMK == null) {
//                    this.uMK = fj(dimensionPixelSize, j.chat_phone_notify_title_icon);
//                }
//                length = spannableString.length() - i3;
//                spannableString.setSpan(this.uMK, length, length + 1, 33);
//            }
//        }
//        this.uMS.setText(c);
//    }
//
//    public final void showHomeBtn(boolean z) {
//        if (this.mActionBar != null) {
//            this.mActionBar.setDisplayHomeAsUpEnabled(z);
//            if (this.uMU != null) {
//                this.uMU.setVisibility(z ? 0 : 8);
//            }
//        }
//    }
//
//    public void supportInvalidateOptionsMenu() {
//        this.uMN.supportInvalidateOptionsMenu();
//    }
//
//    public boolean interceptSupportInvalidateOptionsMenu() {
//        return false;
//    }
//
//    public final void enableBackMenu(boolean z) {
//        if (this.uMP != null && this.uMP.bIU != z) {
//            this.uMP.bIU = z;
//            supportInvalidateOptionsMenu();
//        }
//    }
//
//    final void c(boolean z, int i, boolean z2) {
//        boolean z3;
//        boolean z4;
//        Iterator it;
//        a aVar;
//        if (z) {
//            it = this.uGV.iterator();
//            z3 = false;
//            while (it.hasNext()) {
//                aVar = (a) it.next();
//                if (aVar.bIU != z2) {
//                    aVar.bIU = z2;
//                    z4 = true;
//                } else {
//                    z4 = z3;
//                }
//                z3 = z4;
//            }
//        } else {
//            it = this.uGV.iterator();
//            z3 = false;
//            while (it.hasNext()) {
//                aVar = (a) it.next();
//                if (aVar.uHd != i || aVar.bIU == z2) {
//                    z4 = z3;
//                } else {
//                    aVar.bIU = z2;
//                    z4 = true;
//                }
//                z3 = z4;
//            }
//        }
//        if (!(this.drn == null ? false : this.drn.wel)) {
//            supportInvalidateOptionsMenu();
//        }
//        y.v("MicroMsg.MMActivityController", "enable option menu, target id %d, changed %B, searching %B", Integer.valueOf(i), Boolean.valueOf(z3), Boolean.valueOf(z4));
//    }
//
//    final void d(boolean z, int i, boolean z2) {
//        boolean z3;
//        boolean z4;
//        Iterator it;
//        a aVar;
//        if (z) {
//            it = this.uGV.iterator();
//            z3 = false;
//            while (it.hasNext()) {
//                aVar = (a) it.next();
//                if (aVar.aoL != z2) {
//                    aVar.aoL = z2;
//                    z4 = true;
//                } else {
//                    z4 = z3;
//                }
//                z3 = z4;
//            }
//        } else {
//            it = this.uGV.iterator();
//            z3 = false;
//            while (it.hasNext()) {
//                aVar = (a) it.next();
//                if (aVar.uHd != i || aVar.aoL == z2) {
//                    z4 = z3;
//                } else {
//                    aVar.aoL = z2;
//                    z4 = true;
//                }
//                z3 = z4;
//            }
//        }
//        z4 = this.drn == null ? false : this.drn.wel;
//        if (z3 && !z4) {
//            supportInvalidateOptionsMenu();
//        }
//        y.i("MicroMsg.MMActivityController", "show option menu, target id %d, changed %B, searching %B", Integer.valueOf(i), Boolean.valueOf(z3), Boolean.valueOf(z4));
//    }
//
//    public final void addSearchMenu(boolean z, n nVar) {
//        y.v("MicroMsg.MMActivityController", "add search menu");
//        a aVar = new a();
//        aVar.uHd = g.menu_search;
//        aVar.text = this.mContext.getString(k.app_search);
//        aVar.uHe = j.actionbar_icon_dark_search;
//        aVar.fkl = null;
//        aVar.kfL = null;
//        removeOptionMenu(aVar.uHd);
//        this.uGV.add(0, aVar);
//        this.uMO = z;
//        this.drn = nVar;
//        supportInvalidateOptionsMenu();
//    }
//
//    public final void addTextOptionMenu(int i, String str, OnMenuItemClickListener onMenuItemClickListener) {
//        a(i, 0, str, false, onMenuItemClickListener, null, b.NORMAL);
//    }
//
//    public final void addTextOptionMenu(int i, String str, OnMenuItemClickListener onMenuItemClickListener, OnLongClickListener onLongClickListener, b bVar) {
//        a(i, 0, str, false, onMenuItemClickListener, onLongClickListener, bVar);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, OnMenuItemClickListener onMenuItemClickListener) {
//        a(i, i2, "", false, onMenuItemClickListener, null, b.NORMAL);
//    }
//
//    public final void addIconOptionMenu(int i, String str, int i2, OnMenuItemClickListener onMenuItemClickListener) {
//        a(i, i2, str, false, onMenuItemClickListener, null, b.NORMAL);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, int i3, OnMenuItemClickListener onMenuItemClickListener) {
//        a(i, i3, this.mContext.getString(i2), false, onMenuItemClickListener, null, b.NORMAL);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, int i3, boolean z, OnMenuItemClickListener onMenuItemClickListener) {
//        a(i, i3, this.mContext.getString(i2), z, onMenuItemClickListener, null, b.NORMAL);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, OnMenuItemClickListener onMenuItemClickListener, OnLongClickListener onLongClickListener) {
//        a(i, i2, "", false, onMenuItemClickListener, onLongClickListener, b.NORMAL);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, int i3, OnMenuItemClickListener onMenuItemClickListener, OnLongClickListener onLongClickListener) {
//        a(i, i3, this.mContext.getString(i2), false, onMenuItemClickListener, onLongClickListener, b.NORMAL);
//    }
//
//    public final void updateOptionMenuText(int i, String str) {
//        a FU = FU(i);
//        if (FU != null && !bk.aM(str, "").equals(FU.text)) {
//            FU.text = str;
//            supportInvalidateOptionsMenu();
//        }
//    }
//
//    public final void updateOptionMenuListener(int i, OnMenuItemClickListener onMenuItemClickListener, OnLongClickListener onLongClickListener) {
//        a FU = FU(i);
//        if (FU != null) {
//            FU.fkl = onMenuItemClickListener;
//            FU.kfL = onLongClickListener;
//        }
//    }
//
//    public final void setTitleBarDoubleClickListener(final Runnable runnable) {
//        if (this.mActionBar != null && runnable != null) {
//            this.mActionBar.getCustomView().setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    if (SystemClock.elapsedRealtime() - s.this.pev < 300) {
//                        runnable.run();
//                    }
//                    s.this.pev = SystemClock.elapsedRealtime();
//                }
//            });
//        }
//    }
//
//    public final void setBackBtn(final OnMenuItemClickListener onMenuItemClickListener, int i) {
//        if (this.mActionBar != null) {
//            if (onMenuItemClickListener == null) {
//                this.mActionBar.setDisplayHomeAsUpEnabled(false);
//            } else {
//                this.mActionBar.setDisplayHomeAsUpEnabled(false);
//                if (this.uMU != null) {
//                    this.uMU.setOnClickListener(new OnClickListener() {
//                        public final void onClick(View view) {
//                            onMenuItemClickListener.onMenuItemClick(null);
//                        }
//                    });
//                }
//            }
//            if (i == 0) {
//                i = j.actionbar_icon_dark_back;
//            }
//            if (!(this.uMV == null || i == 0)) {
//                this.uMV.setImageResource(i);
//            }
//            this.uMP.uHd = 16908332;
//            this.uMP.fkl = onMenuItemClickListener;
//        }
//    }
//
//    public final void removeAllOptionMenu() {
//        if (!this.uGV.isEmpty()) {
//            this.uGV.clear();
//            supportInvalidateOptionsMenu();
//        }
//    }
//
//    final boolean FT(int i) {
//        for (int i2 = 0; i2 < this.uGV.size(); i2++) {
//            if (((a) this.uGV.get(i2)).uHd == i) {
//                y.d("MicroMsg.MMActivityController", "match menu, id %d, remove it", Integer.valueOf(i));
//                this.uGV.remove(i2);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public final boolean removeOptionMenu(int i) {
//        for (int i2 = 0; i2 < this.uGV.size(); i2++) {
//            if (((a) this.uGV.get(i2)).uHd == i) {
//                y.d("MicroMsg.MMActivityController", "match menu, id %d, remove it", Integer.valueOf(i));
//                this.uGV.remove(i2);
//                supportInvalidateOptionsMenu();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    final void a(int i, int i2, String str, boolean z, OnMenuItemClickListener onMenuItemClickListener, OnLongClickListener onLongClickListener, b bVar) {
//        a aVar = new a();
//        aVar.uHd = i;
//        aVar.uHe = i2;
//        aVar.text = str;
//        aVar.fkl = onMenuItemClickListener;
//        aVar.kfL = onLongClickListener;
//        aVar.uNu = bVar;
//        aVar.uNv = z;
//        if (aVar.uHe == f.mm_title_btn_menu && bk.bl(str)) {
//            aVar.text = this.mContext.getString(k.app_more);
//        }
//        FT(aVar.uHd);
//        this.uGV.add(aVar);
//        supportInvalidateOptionsMenu();
//    }
//
//    public final a FU(int i) {
//        Iterator it = this.uGV.iterator();
//        while (it.hasNext()) {
//            a aVar = (a) it.next();
//            if (aVar.uHd == i) {
//                return aVar;
//            }
//        }
//        return null;
//    }
//
//    public final CharSequence getMMTitle() {
//        if (this.mActionBar == null) {
//            return null;
//        }
//        return this.uME != null ? this.uME : this.mActionBar.getTitle();
//    }
//
//    public final void hideTitleView() {
//        boolean z = true;
//        String str = "MicroMsg.MMActivityController";
//        String str2 = "hideTitleView hasTitle:%b";
//        Object[] objArr = new Object[1];
//        if (this.mActionBar == null) {
//            z = false;
//        }
//        objArr[0] = Boolean.valueOf(z);
//        y.v(str, str2, objArr);
//        if (this.mActionBar != null) {
//            this.mActionBar.hide();
//        }
//    }
//
//    public final void showTitleView() {
//        boolean z = true;
//        String str = "MicroMsg.MMActivityController";
//        String str2 = "showTitleView hasTitle:%b";
//        Object[] objArr = new Object[1];
//        if (this.mActionBar == null) {
//            z = false;
//        }
//        objArr[0] = Boolean.valueOf(z);
//        y.v(str, str2, objArr);
//        if (this.mActionBar != null) {
//            this.mActionBar.show();
//        }
//    }
//
//    public final boolean isTitleShowing() {
//        boolean z = true;
//        String str = "MicroMsg.MMActivityController";
//        String str2 = "isTitleShowing hasTitle:%b";
//        Object[] objArr = new Object[1];
//        if (this.mActionBar == null) {
//            z = false;
//        }
//        objArr[0] = Boolean.valueOf(z);
//        y.v(str, str2, objArr);
//        if (this.mActionBar == null) {
//            return false;
//        }
//        return this.mActionBar.isShowing();
//    }
//
//    public final int getTitleLocation() {
//        if (this.mActionBar == null) {
//            return 0;
//        }
//        return this.mActionBar.getHeight();
//    }
//
//    public final void setTitleMuteIconVisibility(int i) {
//        this.uML = i == 0;
//        czA();
//    }
//
//    public final boolean hideVKB() {
//        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
//        if (inputMethodManager == null) {
//            return false;
//        }
//        View currentFocus = this.uMN.getCurrentFocus();
//        if (currentFocus == null) {
//            return false;
//        }
//        IBinder windowToken = currentFocus.getWindowToken();
//        if (windowToken == null) {
//            return false;
//        }
//        boolean hideSoftInputFromWindow;
//        try {
//            hideSoftInputFromWindow = inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
//        } catch (IllegalArgumentException e) {
//            y.e("MicroMsg.MMActivityController", "hide VKB exception %s", e);
//            hideSoftInputFromWindow = false;
//        }
//        y.v("MicroMsg.MMActivityController", "hide VKB result %B", Boolean.valueOf(hideSoftInputFromWindow));
//        return hideSoftInputFromWindow;
//    }
//
//    public final boolean hideVKB(View view) {
//        if (view == null) {
//            return false;
//        }
//        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
//        if (inputMethodManager == null) {
//            return false;
//        }
//        IBinder windowToken = view.getWindowToken();
//        if (windowToken == null) {
//            return false;
//        }
//        boolean hideSoftInputFromWindow;
//        try {
//            hideSoftInputFromWindow = inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
//        } catch (IllegalArgumentException e) {
//            y.e("MicroMsg.MMActivityController", "hide VKB(View) exception %s", e);
//            hideSoftInputFromWindow = false;
//        }
//        return hideSoftInputFromWindow;
//    }
//
//    public final void showVKB() {
//        AppCompatActivity appCompatActivity = this.uMN;
//        InputMethodManager inputMethodManager = (InputMethodManager) appCompatActivity.getSystemService("input_method");
//        if (inputMethodManager != null) {
//            View currentFocus = appCompatActivity.getCurrentFocus();
//            if (currentFocus != null && currentFocus.getWindowToken() != null) {
//                inputMethodManager.toggleSoftInput(0, 2);
//            }
//        }
//    }
//
//    final void czB() {
//        if (this.nST) {
//            if (this.uMV != null) {
//                this.uMV.setColorFilter(-1, Mode.SRC_ATOP);
//                this.uMU.setBackgroundResource(f.selected_bg_dark);
//            }
//            if (this.uMS != null && this.uMS.getVisibility() == 0) {
//                this.uMS.setTextColor(this.mContext.getResources().getColor(d.white_text_color));
//            }
//            if (this.uMW != null && this.uMW.getVisibility() == 0) {
//                this.uMW.setTextColor(this.mContext.getResources().getColor(d.white_text_color));
//            }
//            if (this.uGM == null || this.uGM.getVisibility() != 0) {
//                if (this.uGO != null && this.uGO.getVisibility() == 0) {
//                    this.uGO.setTextColor(com.tencent.mm.cb.a.h(this.mContext, d.white_text_color_selector));
//                    return;
//                }
//                return;
//            } else if (this.uGM.getDrawable() != null) {
//                this.uGM.getDrawable().setColorFilter(-1, Mode.SRC_ATOP);
//                return;
//            } else {
//                this.uGM.setColorFilter(-1, Mode.SRC_ATOP);
//                return;
//            }
//        }
//        if (this.uMV != null) {
//            this.uMV.setColorFilter(WebView.NIGHT_MODE_COLOR, Mode.SRC_ATOP);
//            this.uMU.setBackgroundResource(f.actionbar_menu_selector);
//        }
//        if (this.uMS != null && this.uMS.getVisibility() == 0) {
//            this.uMS.setTextColor(this.mContext.getResources().getColor(d.black_text_color));
//        }
//        if (this.uMW != null && this.uMW.getVisibility() == 0) {
//            this.uMW.setTextColor(this.mContext.getResources().getColor(d.black_text_color));
//        }
//        if (this.uGM == null || this.uGM.getVisibility() != 0) {
//            if (this.uGO != null && this.uGO.getVisibility() == 0) {
//                this.uGO.setTextColor(com.tencent.mm.cb.a.h(this.mContext, d.black_text_color_selector));
//            }
//        } else if (this.uGM.getDrawable() != null) {
//            this.uGM.getDrawable().setColorFilter(WebView.NIGHT_MODE_COLOR, Mode.SRC_ATOP);
//        } else {
//            this.uGM.setColorFilter(WebView.NIGHT_MODE_COLOR, Mode.SRC_ATOP);
//        }
//    }
//
//    final void czC() {
//        if (VERSION.SDK_INT >= 23) {
//            View decorView = this.uMN.getWindow().getDecorView();
//            int systemUiVisibility = decorView.getSystemUiVisibility();
//            y.d("MicroMsg.MMActivityController", "dancy test actionbar isDark:%s", Boolean.valueOf(this.nST));
//            if (this.nST) {
//                systemUiVisibility &= -8193;
//            } else {
//                systemUiVisibility |= 8192;
//            }
//            decorView.setSystemUiVisibility(systemUiVisibility);
//        }
//    }
//
//    public final void ta(int i) {
//        if (this.mActionBar != null) {
//            this.uGL = i;
//            this.nST = an.Gf(this.uGL);
//            this.mActionBar.setBackgroundDrawable(new ColorDrawable(this.uGL));
//            l(this.uMN, this.uGL);
//            czC();
//            czB();
//        }
//    }
//
//    @TargetApi(21)
//    private static void l(Activity activity, int i) {
//        if (VERSION.SDK_INT >= 21) {
//            if (VERSION.SDK_INT < 23 || com.tencent.mm.compatible.util.h.zL()) {
//                i = VERSION.SDK_INT >= 21 ? an.n(activity.getResources().getColor(d.statusbar_fg_drak_color), i) : 0;
//            }
//            activity.getWindow().setStatusBarColor(i);
//        }
//    }
//}