//package com.tencent.mm.roomsdk.a.c;
//
//import android.content.Context;
//import android.content.DialogInterface.OnCancelListener;
//
//import com.tencent.mm.ui.base.p;
//
//public abstract class a {
//    protected p tipDialog;
//    protected boolean ubr = true;
//    protected a ubs;
//    protected a ubt;
//    protected a ubu;
//
//    public abstract void a(Context context, String str, boolean z, OnCancelListener onCancelListener);
//
//    public abstract void cancel();
//
//    public abstract void cpz();
//
//    public final boolean cpy() {
//        return this.ubr;
//    }
//
//    public static b mm(boolean z) {
//        return new b(z);
//    }
//
//    public static c mn(boolean z) {
//        return new c(z);
//    }
//
//    public final a b(a aVar) {
//        this.ubs = aVar;
//        return this;
//    }
//
//    public final a c(a aVar) {
//        this.ubt = aVar;
//        return this;
//    }
//
//    public final a d(a aVar) {
//        this.ubu = aVar;
//        return this;
//    }
//}