//package io.merculet.wxbot.config;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.graphics.PorterDuff.Mode;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.KeyCharacterMap;
//import android.view.KeyEvent;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MenuItem.OnMenuItemClickListener;
//import android.view.View;
//import android.view.View.OnLongClickListener;
//import android.view.ViewConfiguration;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.FrameLayout;
//import android.widget.FrameLayout.LayoutParams;
//import com.eclipsesource.v8.Platform;
//import com.tencent.mm.compatible.loader.c;
//import com.tencent.mm.plugin.appbrand.widget.input.ac;
//import com.tencent.mm.sdk.platformtools.ae;
//import com.tencent.mm.sdk.platformtools.ah;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.t;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.ui.s.AnonymousClass3;
//import com.tencent.mm.ui.statusbar.b;
//import com.tencent.mm.ui.statusbar.d;
//import com.tencent.mm.ui.tools.n;
//import com.tencent.smtt.sdk.WebView;
//import java.lang.reflect.Method;
//import java.util.Iterator;
//import java.util.Locale;
//
//@com.tencent.mm.ui.base.a(0)
//public abstract class MMActivity extends MMFragmentActivity implements ac {
//    private static String uMt;
//    String className;
//    public a gJb = null;
//    public s mController = new s() {
//        protected final int getLayoutId() {
//            return MMActivity.this.getLayoutId();
//        }
//
//        protected final void dealContentView(View view) {
//            MMActivity.this.dealContentView(view);
//        }
//
//        protected final String bdI() {
//            return MMActivity.this.bdI();
//        }
//
//        protected final View getLayoutView() {
//            return MMActivity.getLayoutView();
//        }
//
//        public final void onKeyboardStateChanged() {
//            MMActivity.this.onKeyboardStateChanged();
//        }
//
//        protected final void onCreateBeforeSetContentView() {
//            MMActivity.this.onCreateBeforeSetContentView();
//        }
//
//        protected final String getClassName() {
//            return MMActivity.this.getClass().getName();
//        }
//
//        protected final boolean czg() {
//            return MMActivity.this.czg();
//        }
//
//        public final boolean noActionBar() {
//            return MMActivity.this.noActionBar();
//        }
//
//        public final boolean needShowIdcError() {
//            return MMActivity.this.needShowIdcError();
//        }
//    };
//    public boolean uMo = false;
//    public boolean uMp = false;
//    private ViewGroup uMq = null;
//    public boolean uMr = false;
//    private View uMs;
//    private long uMu = 0;
//    private long uMv = 0;
//    private long uMw = 0;
//
//    public interface a {
//        void c(int i, int i2, Intent intent);
//    }
//
//    public abstract int getLayoutId();
//
//    @Deprecated
//    public void initView() {
//    }
//
//    public final void setBackGroundColorResource(int i) {
//        this.mController.setBackGroundColorResource(i);
//    }
//
//    public static Locale initLanguage(Context context) {
//        return s.initLanguage(context);
//    }
//
//    public static Locale by(Context context, String str) {
//        return s.by(context, str);
//    }
//
//    public void onCreateBeforeSetContentView() {
//    }
//
//    public boolean czg() {
//        return false;
//    }
//
//    private static String czh() {
//        long totalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        return String.format("Runtime: [%s:%s:%s]", new Object[]{Long.valueOf(Runtime.getRuntime().totalMemory()), Long.valueOf(Runtime.getRuntime().freeMemory()), Long.valueOf(totalMemory)});
//    }
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        this.mController.a(getApplicationContext(), (AppCompatActivity) this);
//        y.i("MicroMsg.MMActivity", "checktask onCreate:%s#0x%x, taskid:%d, task:%s appendMemLog:%s", getClass().getSimpleName(), Integer.valueOf(hashCode()), Integer.valueOf(getTaskId()), bk.fW(this), czh());
//        initNavigationSwipeBack();
//    }
//
//    public void dealContentView(View view) {
//        setContentView(view);
//    }
//
//    public boolean noActionBar() {
//        return false;
//    }
//
//    public void onKeyboardStateChanged() {
//    }
//
//    public void onSaveInstanceState(Bundle bundle) {
//        if (VERSION.SDK_INT < 11) {
//            super.onSaveInstanceState(bundle);
//        }
//    }
//
//    public void setScreenEnable(boolean z) {
//        this.mController.setScreenEnable(z);
//    }
//
//    public final View getContentView() {
//        return this.mController.contentView;
//    }
//
//    public void vN(int i) {
//        this.mController.contentView.setVisibility(i);
//        if (i == 0) {
//            this.mController.showTitleView();
//        } else {
//            this.mController.hideTitleView();
//        }
//    }
//
//    public void onSwipeBack() {
//        if (!Wt()) {
//            this.mController.callBackMenu();
//        }
//        super.onSwipeBack();
//    }
//
//    public void initSwipeBack() {
//        super.initSwipeBack();
//        if (getSwipeBackLayout() != null && getSwipeBackLayout().getChildCount() > 0) {
//            View childAt = getSwipeBackLayout().getChildAt(0);
//            getSwipeBackLayout().removeView(childAt);
//            if (this.uMp) {
//                this.uMq = new b(this);
//            } else {
//                this.uMq = new FrameLayout(this);
//            }
//            this.uMq.addView(childAt, new LayoutParams(-1, -1));
//            getSwipeBackLayout().addView(this.uMq);
//            getSwipeBackLayout().setContentView(this.uMq);
//        }
//    }
//
//    public boolean Wt() {
//        return false;
//    }
//
//    public int getForceOrientation() {
//        return -1;
//    }
//
//    public void ahA() {
//        if (getForceOrientation() == -1) {
//            this.uMo = getSharedPreferences(ae.cqR(), 0).getBoolean("settings_landscape_mode", false);
//            if (this.uMo) {
//                setRequestedOrientation(-1);
//                return;
//            } else {
//                setRequestedOrientation(1);
//                return;
//            }
//        }
//        setRequestedOrientation(getForceOrientation());
//    }
//
//    public void onStart() {
//        ahA();
//        super.onStart();
//    }
//
//    @TargetApi(17)
//    public boolean onKeyUp(int i, KeyEvent keyEvent) {
//        boolean z = true;
//        if (this.mController.onKeyUp(i, keyEvent)) {
//            return z;
//        }
//        try {
//            return super.onKeyUp(i, keyEvent);
//        } catch (Exception e) {
//            y.printErrStackTrace("MicroMsg.MMActivity", e, "java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState", new Object[0]);
//            return z;
//        }
//    }
//
//    public boolean onKeyDown(int i, KeyEvent keyEvent) {
//        if (this.mController.onKeyDown(i, keyEvent)) {
//            return true;
//        }
//        return super.onKeyDown(i, keyEvent);
//    }
//
//    public boolean needShowIdcError() {
//        return true;
//    }
//
//    public void onResume() {
//        long currentTimeMillis = System.currentTimeMillis();
//        ai.be(1, this.className);
//        super.onResume();
//        y.v("MicroMsg.INIT", "KEVIN MMActivity super.onResume " + (System.currentTimeMillis() - currentTimeMillis));
//        this.mController.onResume();
//        y.v("MicroMsg.INIT", "KEVIN MMActivity onResume :%dms, hash:#0x%x", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(hashCode()));
//        if (this.uMv > this.uMu) {
//            this.uMw += this.uMv - this.uMu;
//        }
//        this.uMu = bk.UZ();
//        this.uMv = 0;
//    }
//
//    public void addDialog(Dialog dialog) {
//        this.mController.addDialog(dialog);
//    }
//
//    public final AppCompatActivity czi() {
//        return this.mController.uMN;
//    }
//
//    public void onDestroy() {
//        y.i("MicroMsg.MMActivity", "checktask onDestroy:%s#0x%x task:%s appendMemLog:%s", getClass().getSimpleName(), Integer.valueOf(hashCode()), bk.fW(this), czh());
//        super.onDestroy();
//        this.mController.onDestroy();
//        this.uMr = true;
//    }
//
//    public static void czj() {
//        s.czj();
//    }
//
//    public void onPause() {
//        long currentTimeMillis = System.currentTimeMillis();
//        ai.be(2, this.className);
//        super.onPause();
//        this.mController.onPause();
//        boolean isFinishing = isFinishing();
//        y.v("MicroMsg.INIT", "KEVIN MMActivity onPause: %d ms, isFinishing %B, hash:#0x%x", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(isFinishing), Integer.valueOf(hashCode()));
//        this.uMv = bk.UZ();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (this.mController.onCreateOptionsMenu(menu)) {
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        this.mController.onPrepareOptionsMenu(menu);
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        return this.mController.onOptionsItemSelected(menuItem);
//    }
//
//    public String bdI() {
//        return "";
//    }
//
//    protected static View getLayoutView() {
//        return null;
//    }
//
//    public final void fullScreenNoTitleBar(boolean z) {
//        this.mController.fullScreenNoTitleBar(z);
//    }
//
//    public final void setTitleVisibility(int i) {
//        this.mController.setTitleVisibility(i);
//    }
//
//    public void oX(int i) {
//        s sVar = this.mController;
//        if (sVar.mActionBar != null) {
//            sVar.uMS.setTextColor(i);
//        }
//    }
//
//    public void setMMTitle(String str) {
//        this.mController.setMMTitle(str);
//    }
//
//    public final void N(CharSequence charSequence) {
//        this.mController.N(charSequence);
//    }
//
//    public void setMMTitle(int i) {
//        this.mController.setMMTitle(i);
//    }
//
//    public void setMMSubTitle(String str) {
//        this.mController.setMMSubTitle(str);
//    }
//
//    public final void setMMSubTitle(int i) {
//        this.mController.setMMSubTitle(i);
//    }
//
//    public final void FP(int i) {
//        this.mController.uMS.setVisibility(i);
//    }
//
//    public final void czk() {
//        s sVar = this.mController;
//        if (VERSION.SDK_INT >= 23) {
//            View decorView = sVar.uMN.getWindow().getDecorView();
//            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
//        }
//    }
//
//    public final void FQ(int i) {
//        this.mController.setTitleLogo(0, i);
//    }
//
//    public final void showHomeBtn(boolean z) {
//        this.mController.showHomeBtn(z);
//    }
//
//    public final void enableBackMenu(boolean z) {
//        this.mController.enableBackMenu(z);
//    }
//
//    public final void enableOptionMenu(boolean z) {
//        this.mController.c(true, -1, z);
//    }
//
//    public final void enableOptionMenu(int i, boolean z) {
//        this.mController.c(false, i, z);
//    }
//
//    public final void showOptionMenu(boolean z) {
//        this.mController.d(true, -1, z);
//    }
//
//    public final void showOptionMenu(int i, boolean z) {
//        this.mController.d(false, i, z);
//    }
//
//    public final boolean czl() {
//        Iterator it = this.mController.uGV.iterator();
//        while (it.hasNext()) {
//            com.tencent.mm.ui.s.a aVar = (com.tencent.mm.ui.s.a) it.next();
//            if (aVar.uHd == 0) {
//                return aVar.bIU;
//            }
//        }
//        return false;
//    }
//
//    public final boolean czm() {
//        Iterator it = this.mController.uGV.iterator();
//        while (it.hasNext()) {
//            com.tencent.mm.ui.s.a aVar = (com.tencent.mm.ui.s.a) it.next();
//            if (aVar.uHd == 0) {
//                return aVar.aoL;
//            }
//        }
//        return false;
//    }
//
//    public final void a(n nVar) {
//        this.mController.addSearchMenu(true, nVar);
//    }
//
//    public void addTextOptionMenu(int i, String str, OnMenuItemClickListener onMenuItemClickListener) {
//        this.mController.addTextOptionMenu(i, str, onMenuItemClickListener);
//    }
//
//    public final void a(int i, String str, int i2, OnMenuItemClickListener onMenuItemClickListener) {
//        s sVar = this.mController;
//        s.b bVar = s.b.NORMAL;
//        com.tencent.mm.ui.s.a aVar = new com.tencent.mm.ui.s.a();
//        aVar.uHd = i;
//        aVar.text = str;
//        aVar.textColor = i2;
//        aVar.fkl = onMenuItemClickListener;
//        aVar.kfL = null;
//        aVar.uNu = bVar;
//        sVar.FT(aVar.uHd);
//        sVar.uGV.add(aVar);
//        new ah().postDelayed(new Runnable() {
//            public final void run() {
//                s.this.supportInvalidateOptionsMenu();
//            }
//        }, 200);
//    }
//
//    public final void a(int i, String str, OnMenuItemClickListener onMenuItemClickListener, s.b bVar) {
//        this.mController.addTextOptionMenu(i, str, onMenuItemClickListener, null, bVar);
//    }
//
//    public void addIconOptionMenu(int i, int i2, OnMenuItemClickListener onMenuItemClickListener) {
//        this.mController.addIconOptionMenu(i, i2, onMenuItemClickListener);
//    }
//
//    public final void a(int i, String str, Drawable drawable, OnMenuItemClickListener onMenuItemClickListener) {
//        s sVar = this.mController;
//        s.b bVar = s.b.NORMAL;
//        com.tencent.mm.ui.s.a aVar = new com.tencent.mm.ui.s.a();
//        aVar.uHd = i;
//        aVar.uNt = drawable;
//        aVar.text = str;
//        aVar.fkl = onMenuItemClickListener;
//        aVar.kfL = null;
//        aVar.uNu = bVar;
//        sVar.FT(aVar.uHd);
//        sVar.uGV.add(aVar);
//        new ah().postDelayed(new Runnable() {
//            public final void run() {
//                s.this.supportInvalidateOptionsMenu();
//            }
//        }, 200);
//    }
//
//    public final void a(String str, int i, OnMenuItemClickListener onMenuItemClickListener) {
//        this.mController.addIconOptionMenu(0, str, i, onMenuItemClickListener);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, int i3, OnMenuItemClickListener onMenuItemClickListener) {
//        this.mController.addIconOptionMenu(i, i2, i3, onMenuItemClickListener);
//    }
//
//    public final void addIconOptionMenu(int i, int i2, int i3, OnMenuItemClickListener onMenuItemClickListener, OnLongClickListener onLongClickListener) {
//        this.mController.addIconOptionMenu(i, i2, i3, onMenuItemClickListener, onLongClickListener);
//    }
//
//    public final void updateOptionMenuText(int i, String str) {
//        this.mController.updateOptionMenuText(i, str);
//    }
//
//    public final void a(OnMenuItemClickListener onMenuItemClickListener) {
//        this.mController.updateOptionMenuListener(1, onMenuItemClickListener, null);
//    }
//
//    public final void setTitleBarDoubleClickListener(Runnable runnable) {
//        this.mController.setTitleBarDoubleClickListener(runnable);
//    }
//
//    public final void b(Runnable runnable, Runnable runnable2) {
//        s sVar = this.mController;
//        if (sVar.mActionBar != null) {
//            sVar.mActionBar.getCustomView().setOnClickListener(new AnonymousClass3(runnable, runnable2));
//        }
//    }
//
//    public void setBackBtn(OnMenuItemClickListener onMenuItemClickListener) {
//        this.mController.setBackBtn(onMenuItemClickListener, 0);
//    }
//
//    public void setBackBtn(OnMenuItemClickListener onMenuItemClickListener, int i) {
//        this.mController.setBackBtn(onMenuItemClickListener, i);
//    }
//
//    public final void czn() {
//        s sVar = this.mController;
//        if (sVar.uMV != null) {
//            sVar.uMV.getDrawable().setColorFilter(WebView.NIGHT_MODE_COLOR, Mode.SRC_ATOP);
//        }
//    }
//
//    public void ta(int i) {
//        this.mController.ta(i);
//        if (this.uMp && VERSION.SDK_INT >= 21 && getWindow() != null) {
//            com.tencent.mm.ui.statusbar.a.d(this.mController.contentView, getWindow().getStatusBarColor(), d.c(getWindow()));
//        }
//    }
//
//    public final void mS(boolean z) {
//        s sVar = this.mController;
//        sVar.nST = z;
//        sVar.czC();
//        sVar.czB();
//    }
//
//    public final void FR(int i) {
//        s sVar = this.mController;
//        sVar.uMX = i;
//        if (sVar.uMV != null) {
//            sVar.uMV.setColorFilter(i, Mode.SRC_ATOP);
//        }
//        if (sVar.uMS != null && sVar.uMS.getVisibility() == 0) {
//            sVar.uMS.setTextColor(i);
//        }
//        if (sVar.uMW != null && sVar.uMW.getVisibility() == 0) {
//            sVar.uMW.setTextColor(i);
//        }
//        if (sVar.uGM != null && sVar.uGM.getVisibility() == 0) {
//            sVar.uGM.getDrawable().setColorFilter(i, Mode.SRC_ATOP);
//        } else if (sVar.uGO != null && sVar.uGO.getVisibility() == 0) {
//            sVar.uGO.setTextColor(i);
//        }
//    }
//
//    public final void czo() {
//        s sVar = this.mController;
//        if (VERSION.SDK_INT >= 21) {
//            sVar.getSupportActionBar().setElevation(0.0f);
//        }
//    }
//
//    public final void czp() {
//        s sVar = this.mController;
//        if (VERSION.SDK_INT >= 21) {
//            sVar.getSupportActionBar().setElevation(1.0f);
//        }
//    }
//
//    public final void mT(boolean z) {
//        s sVar = this.mController;
//        if (sVar.uMV == null) {
//            return;
//        }
//        if (z) {
//            sVar.uMV.setVisibility(0);
//        } else {
//            sVar.uMV.setVisibility(8);
//        }
//    }
//
//    public final void removeAllOptionMenu() {
//        this.mController.removeAllOptionMenu();
//    }
//
//    public final boolean removeOptionMenu(int i) {
//        return this.mController.removeOptionMenu(i);
//    }
//
//    public final int czq() {
//        return this.mController.czv();
//    }
//
//    public final void setTitleMuteIconVisibility(int i) {
//        this.mController.setTitleMuteIconVisibility(i);
//    }
//
//    public final boolean ass() {
//        return this.mController.hideVKB();
//    }
//
//    public void XM() {
//        this.mController.hideVKB();
//    }
//
//    public void hideVKB(View view) {
//        this.mController.hideVKB(view);
//    }
//
//    public void showVKB() {
//        this.mController.showVKB();
//    }
//
//    public static void showVKB(Activity activity) {
//        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
//        if (inputMethodManager != null) {
//            View currentFocus = activity.getCurrentFocus();
//            if (currentFocus != null && currentFocus.getWindowToken() != null) {
//                inputMethodManager.toggleSoftInput(0, 2);
//            }
//        }
//    }
//
//    public void onConfigurationChanged(Configuration configuration) {
//        super.onConfigurationChanged(configuration);
//    }
//
//    public void finish() {
//        super.finish();
//        int a = t.a(getIntent(), x.FLAG_OVERRIDE_ENTER_ANIMATION, -1);
//        int a2 = t.a(getIntent(), x.FLAG_OVERRIDE_EXIT_ANIMATION, -1);
//        if (a != -1) {
//            super.overridePendingTransition(a, a2);
//        }
//    }
//
//    public final void Y(Class<?> cls) {
//        Intent intent = new Intent();
//        intent.setClass(this, cls);
//        startActivity(intent);
//    }
//
//    public final void a(Class<?> cls, Intent intent) {
//        intent.setClass(this, cls);
//        startActivity(intent);
//    }
//
//    public final void b(a aVar, Intent intent, int i) {
//        this.gJb = aVar;
//        startActivityForResult(intent, i);
//    }
//
//    public void onActivityResult(int i, int i2, Intent intent) {
//        super.onActivityResult(i, i2, intent);
//        if (this.gJb != null) {
//            this.gJb.c(i, i2, intent);
//        }
//        this.gJb = null;
//    }
//
//    public final void czr() {
//        s sVar = this.mController;
//        sVar.uMV.setVisibility(8);
//        sVar.uMU.setVisibility(8);
//    }
//
//    public final void czs() {
//        int i = 0;
//        if (gq(this.mController.uMN)) {
//            int i2;
//            if (this.uMs == null) {
//                this.uMs = new View(this.mController.uMN);
//                ((ViewGroup) getWindow().getDecorView()).addView(this.uMs);
//            }
//            AppCompatActivity appCompatActivity = this.mController.uMN;
//            Resources resources = appCompatActivity.getResources();
//            if (resources.getConfiguration().orientation == 1) {
//                i2 = 1;
//            } else {
//                i2 = 0;
//            }
//            if (VERSION.SDK_INT >= 14 && gq(appCompatActivity)) {
//                i2 = resources.getIdentifier(i2 != 0 ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", Platform.ANDROID);
//                if (i2 > 0) {
//                    i = resources.getDimensionPixelSize(i2);
//                }
//            }
//            LayoutParams layoutParams = new LayoutParams(-1, i);
//            layoutParams.gravity = 80;
//            this.uMs.setLayoutParams(layoutParams);
//            this.uMs.setBackgroundColor(-637534208);
//            this.uMs.setVisibility(8);
//            return;
//        }
//        y.w("MicroMsg.MMActivity", "has not NavigationBar!");
//    }
//
//    public final void FS(int i) {
//        if (this.uMs != null) {
//            this.uMs.setVisibility(i);
//        }
//    }
//
//    static {
//        if (VERSION.SDK_INT >= 19) {
//            try {
//                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
//                declaredMethod.setAccessible(true);
//                uMt = (String) declaredMethod.invoke(null, new Object[]{"qemu.hw.mainkeys"});
//            } catch (Throwable th) {
//                uMt = null;
//            }
//        }
//    }
//
//    public static boolean gq(Context context) {
//        Resources resources = context.getResources();
//        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
//        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", Platform.ANDROID);
//        if (identifier != 0) {
//            boolean z = resources.getBoolean(identifier);
//            if ("1".equals(uMt)) {
//                z = false;
//            } else if ("0".equals(uMt)) {
//                z = true;
//            }
//            if (!z || deviceHasKey || ViewConfiguration.get(context).hasPermanentMenuKey()) {
//                return false;
//            }
//            return true;
//        } else if (ViewConfiguration.get(context).hasPermanentMenuKey() || deviceHasKey) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public final String czt() {
//        String str = "";
//        ComponentName callingActivity = getCallingActivity();
//        if (callingActivity != null) {
//            str = callingActivity.getPackageName();
//            y.i("MicroMsg.MMActivity", "get calling activity, %s", str);
//        }
//        String str2 = str;
//        if (bk.bl(str2) && VERSION.SDK_INT >= 22) {
//            try {
//                Object obj = new c(this, "mReferrer", null).get();
//                if (obj != null) {
//                    str = (String) obj;
//                } else {
//                    str = str2;
//                }
//                str2 = str;
//            } catch (Exception e) {
//                y.printErrStackTrace("MicroMsg.MMActivity", e, "get mReferrer error", new Object[0]);
//            }
//        }
//        if (!bk.bl(str2) || VERSION.SDK_INT < 22) {
//            return str2;
//        }
//        Uri referrer = getReferrer();
//        if (referrer == null) {
//            return str2;
//        }
//        y.i("MicroMsg.MMActivity", "get referrer, %s", referrer.getAuthority());
//        return referrer.getAuthority();
//    }
//
//    public final long czu() {
//        long j;
//        if (this.uMv != 0) {
//            j = (this.uMv - this.uMu) + this.uMw;
//        } else {
//            j = (bk.UZ() - this.uMu) + this.uMw;
//        }
//        if (j < 0) {
//            y.w("MicroMsg.MMActivity", "%d get activity browse time is error, may be something warn here.[%d %d %d %d]", Integer.valueOf(hashCode()), Long.valueOf(j), Long.valueOf(this.uMu), Long.valueOf(this.uMv), Long.valueOf(this.uMw));
//        }
//        y.v("MicroMsg.MMActivity", "%d get activity browse time [%d]", Integer.valueOf(hashCode()), Long.valueOf(j));
//        return j;
//    }
//}