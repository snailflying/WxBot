//package com.tencent.mm.plugin.subapp.ui.friend;
//
//import android.app.ActivityOptions;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Pair;
//import android.view.ContextMenu;
//import android.view.ContextMenu.ContextMenuInfo;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.MenuItem.OnMenuItemClickListener;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.AdapterContextMenuInfo;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemLongClickListener;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import com.tencent.mm.R;
//import com.tencent.mm.bh.c;
//import com.tencent.mm.kernel.g;
//import com.tencent.mm.model.a.f;
//import com.tencent.mm.model.au;
//import com.tencent.mm.plugin.account.bind.ui.BindMContactIntroUI;
//import com.tencent.mm.plugin.account.bind.ui.MobileFriendUI;
//import com.tencent.mm.plugin.account.friend.a.l;
//import com.tencent.mm.plugin.subapp.ui.pluginapp.AddMoreFriendsUI;
//import com.tencent.mm.sdk.platformtools.bk;
//import com.tencent.mm.sdk.platformtools.y;
//import com.tencent.mm.storage.av;
//import com.tencent.mm.ui.MMActivity;
//import com.tencent.mm.ui.MMWizardActivity;
//import com.tencent.mm.ui.base.n.d;
//import com.tencent.mm.ui.tools.j;
//import com.tencent.mm.ui.x;
//
///**
// * @Description 有新的好友列表页面
// * @Author sean
// * @Email xiao.lu@magicwindow.cn
// * @Date 2019/1/3 11:48 AM
// * @Version
// */
//public class FMessageConversationUI extends MMActivity {
//    private d hZq = new d() {
//        public final void onMMMenuItemSelected(MenuItem menuItem, int i) {
//            c.g(FMessageConversationUI.this.pww, FMessageConversationUI.this.ncT);
//        }
//    };
//    private String ncT;
//    private b pwe;
//    private ListView pwt;
//    private a pwu;
//    private TextView pwv;
//    private long pww;
//
//    class a {
//        TextView fcy;
//        ImageView hic;
//
//        public a(View view) {
//            this.hic = (ImageView) view.findViewById(R.h.fmsg_item_icon);
//            this.fcy = (TextView) view.findViewById(R.h.fmsg_item_title);
//        }
//    }
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setMMTitle(R.l.fmessage_conversation_title);
//        try {
//            au.getNotification().wp();
//        } catch (Exception e) {
//            y.printErrStackTrace("MicroMsg.FMessageConversationUI", e, "try cancel notification fail", new Object[0]);
//        }
//        if (au.DK()) {
//            initView();
//        } else {
//            finish();
//        }
//    }
//
//    protected void onPause() {
//        super.onPause();
//        au.Hx();
//        com.tencent.mm.model.c.Dz().o(143618, Integer.valueOf(0));
//    }
//
//    protected void onResume() {
//        super.onResume();
//        if (!au.DK()) {
//            finish();
//        } else if (this.pwv != null && bk.fV(this)) {
//            this.pwv.setText(R.l.fmessage_no_recommend_msg_google);
//        }
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        if (g.DN().Dc()) {
//            f.jc("1");
//            com.tencent.mm.bh.d.RY().cuT();
//            if (this.pwe != null) {
//                com.tencent.mm.bh.d.RY().d(this.pwe);
//                return;
//            }
//            return;
//        }
//        y.w("MicroMsg.FMessageConversationUI", "account not init.");
//    }
//
//    protected final void initView() {
//        int i;
//        boolean z = true;
//        if (com.tencent.mm.model.a.g.Iy().iX("1") != null) {
//            int i2;
//            String str = com.tencent.mm.model.a.g.Iy().iX("1").value;
//            boolean i22;
//            if (str.equals("0")) {
//                i22 = 0;
//            } else if (str.equals("1")) {
//                i22 = true;
//            } else {
//                i22 = true;
//            }
//            f.jb("1");
//            i = i22;
//        } else {
//            boolean i3 = true;
//        }
//        this.pwe = new b(this.mController.uMN);
//        com.tencent.mm.bh.d.RY().c(this.pwe);
//        this.pwe.uMi = new com.tencent.mm.ui.r.a() {
//            public final void Wp() {
//                if (FMessageConversationUI.this.pwe.getCount() >= 0) {
//                    FMessageConversationUI.this.enableOptionMenu(0, true);
//                } else {
//                    FMessageConversationUI.this.enableOptionMenu(0, false);
//                }
//            }
//        };
//        this.pwt = (ListView) findViewById(R.h.fmessage_conversation_lv);
//        if (i3 != 0) {
//            View inflate = LayoutInflater.from(this.mController.uMN).inflate(R.i.fmessage_conversation_header, null);
//            inflate.findViewById(R.h.fmsg_searchEt).setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    Intent putExtra = new Intent().putExtra("Search_Scene", 2).putExtra(x.FLAG_OVERRIDE_ENTER_ANIMATION, 0).putExtra(x.FLAG_OVERRIDE_EXIT_ANIMATION, 0);
//                    Bundle bundle = null;
//                    if (VERSION.SDK_INT >= 21) {
//                        bundle = ActivityOptions.makeSceneTransitionAnimation(FMessageConversationUI.this, new Pair[0]).toBundle();
//                    }
//                    com.tencent.mm.plugin.fts.a.d.b(FMessageConversationUI.this.mController.uMN, ".ui.FTSAddFriendUI", putExtra, bundle);
//                }
//            });
//            this.pwt.addHeaderView(inflate);
//        }
//        this.pwt.setAdapter(this.pwe);
//        final j jVar = new j(this);
//        this.pwt.setOnItemLongClickListener(new OnItemLongClickListener() {
//            public final boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
//                if (i < FMessageConversationUI.this.pwt.getHeaderViewsCount()) {
//                    y.w("MicroMsg.FMessageConversationUI", "on header view long click, ignore");
//                } else {
//                    View view2 = view;
//                    jVar.a(view2, i - FMessageConversationUI.this.pwt.getHeaderViewsCount(), j, FMessageConversationUI.this, FMessageConversationUI.this.hZq);
//                }
//                return true;
//            }
//        });
//        AppCompatActivity appCompatActivity = this.mController.uMN;
//        b bVar = this.pwe;
//        if (this.pwt.getHeaderViewsCount() <= 0) {
//            z = false;
//        }
//        this.pwu = new a(appCompatActivity, bVar, z);
//        this.pwt.setOnItemClickListener(this.pwu);
//        View findViewById;
//        if (i3 == 0) {
//            findViewById = findViewById(R.h.fmessage_conversation_empty_a);
//            findViewById.setVisibility(0);
//            ((TextView) findViewById(R.h.empty_tip_recommend_bind_tv)).setOnClickListener(new OnClickListener() {
//                public final void onClick(View view) {
//                    au.Hx();
//                    if (bk.bl((String) com.tencent.mm.model.c.Dz().get(6, null))) {
//                        Intent intent = new Intent(FMessageConversationUI.this.mController.uMN, BindMContactIntroUI.class);
//                        intent.putExtra("key_upload_scene", 5);
//                        MMWizardActivity.C(FMessageConversationUI.this, intent);
//                        return;
//                    }
//                    FMessageConversationUI.this.startActivity(new Intent(FMessageConversationUI.this, MobileFriendUI.class));
//                }
//            });
//            this.pwt.setEmptyView(findViewById);
//        } else {
//            findViewById = findViewById(R.h.fmessage_conversation_empty_b);
//            findViewById.setVisibility(0);
//            this.pwv = (TextView) findViewById.findViewById(R.h.fmsg_msg_content);
//            ListView listView = (ListView) findViewById.findViewById(R.h.fmsg_list);
//            listView.setAdapter(new BaseAdapter() {
//                public final View getView(int i, View view, ViewGroup viewGroup) {
//                    a aVar;
//                    if (view == null || view.getTag() == null) {
//                        view = LayoutInflater.from(FMessageConversationUI.this.mController.uMN).inflate(R.i.fmessage_conversation_empty_list_item, null);
//                        aVar = new a(view);
//                        view.setTag(aVar);
//                    } else {
//                        aVar = (a) view.getTag();
//                    }
//                    if (i == 0) {
//                        aVar.hic.setImageResource(R.k.find_more_friend_mobile_icon);
//                        aVar.fcy.setText(R.l.find_friends_by_mobile);
//                    }
//                    return view;
//                }
//
//                public final long getItemId(int i) {
//                    return (long) i;
//                }
//
//                public final Object getItem(int i) {
//                    return Integer.valueOf(i);
//                }
//
//                public final int getCount() {
//                    return 1;
//                }
//            });
//            listView.setOnItemClickListener(new OnItemClickListener() {
//                public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
//                    if (i != 0) {
//                        return;
//                    }
//                    if (l.WP() != com.tencent.mm.plugin.account.friend.a.l.a.SUCC) {
//                        Intent intent = new Intent(FMessageConversationUI.this.mController.uMN, BindMContactIntroUI.class);
//                        intent.putExtra("key_upload_scene", 5);
//                        MMWizardActivity.C(FMessageConversationUI.this.mController.uMN, intent);
//                        return;
//                    }
//                    FMessageConversationUI.this.startActivity(new Intent(FMessageConversationUI.this.mController.uMN, MobileFriendUI.class));
//                }
//            });
//            this.pwt.setEmptyView(findViewById);
//        }
//        addTextOptionMenu(0, getString(R.l.menu_item_add_friend), new OnMenuItemClickListener() {
//            public final boolean onMenuItemClick(MenuItem menuItem) {
//                Intent intent = new Intent(FMessageConversationUI.this, AddMoreFriendsUI.class);
//                intent.putExtra("invite_friend_scene", 3);
//                FMessageConversationUI.this.startActivity(intent);
//                return true;
//            }
//        });
//        setBackBtn(new OnMenuItemClickListener() {
//            public final boolean onMenuItemClick(MenuItem menuItem) {
//                FMessageConversationUI.this.finish();
//                return true;
//            }
//        });
//    }
//
//    protected final int getLayoutId() {
//        return R.i.fmessage_conversation;
//    }
//
//    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
//        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) contextMenuInfo;
//        av avVar = (av) this.pwe.getItem(adapterContextMenuInfo.position);
//        if (avVar == null) {
//            y.e("MicroMsg.FMessageConversationUI", "onItemLongClick, item is null, pos = " + adapterContextMenuInfo.position);
//            return;
//        }
//        if (!bk.bl(avVar.field_displayName)) {
//            contextMenu.setHeaderTitle(com.tencent.mm.pluginsdk.ui.d.j.b((Context) this, avVar.field_displayName));
//        }
//        contextMenu.add(0, 0, 0, R.l.app_delete);
//        this.pww = avVar.field_fmsgSysRowId;
//        this.ncT = avVar.field_talker;
//    }
//}