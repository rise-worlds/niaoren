package com.nrzs.ft.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.TimeUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.ft.C1990R;
import java.text.SimpleDateFormat;
import java.util.List;
import p110z1.InputNoteDialog;
import p110z1.SessionInfoDialog;

/* renamed from: com.nrzs.ft.adapter.UserKickAdapter */
/* loaded from: classes2.dex */
public class UserKickAdapter extends BaseListAdapter<SessionInfo, C2005b> {

    /* renamed from: b */
    private AbstractC2004a f10690b;

    /* renamed from: d */
    private boolean f10691d = true;

    /* renamed from: a */
    SimpleDateFormat f10689a = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    /* renamed from: com.nrzs.ft.adapter.UserKickAdapter$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2004a {
        /* renamed from: a */
        void mo12161a(long j, String str, int i);

        /* renamed from: a */
        void mo12160a(String str, long j, String str2, int i);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public UserKickAdapter(AbstractC2004a aVar, List<SessionInfo> list) {
        super(list);
        this.f10690b = aVar;
    }

    /* renamed from: a */
    public void m18905a(boolean z) {
        this.f10691d = z;
    }

    /* renamed from: a */
    public C2005b mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C2005b(layoutInflater, viewGroup, mo18186a(i2));
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull final C2005b bVar, final int i) {
        final SessionInfo sessionInfo = (SessionInfo) this.f11255c.get(i);
        if (sessionInfo != null) {
            if (!sessionInfo.isTwentyFourHour() || TextUtils.isEmpty(sessionInfo.getLastRunTime())) {
                bVar.f10706c.setText("未运行");
            } else {
                bVar.f10706c.setText(TimeUtils.m23107a(TimeUtils.m23099b(sessionInfo.getLastRunTime()), this.f10689a));
            }
            String deviceNote = sessionInfo.getDeviceNote();
            bVar.f10704a.setText(sessionInfo.getDeviceModel());
            TextView textView = bVar.f10705b;
            if (TextUtils.isEmpty(deviceNote)) {
                deviceNote = "无";
            }
            textView.setText(deviceNote);
            bVar.f10705b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.adapter.UserKickAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    UserKickAdapter.this.m18903b(i, bVar.f10705b.getText().toString().trim());
                }
            });
            if (sessionInfo.getDeviceCode().equals(DataApp.m18939d().m18941c())) {
                bVar.f10709f.setVisibility(0);
            } else {
                bVar.f10709f.setVisibility(8);
            }
            bVar.f10708e.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.adapter.UserKickAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    UserKickAdapter.this.m18902c(i);
                }
            });
            bVar.f10707d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.adapter.UserKickAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    UserKickAdapter.this.m18910a(sessionInfo, i);
                }
            });
        }
    }

    /* renamed from: a */
    public void onViewAttachedToWindow(@NonNull C2005b bVar) {
        super.onViewAttachedToWindow(bVar);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C1990R.layout.nrzs_ft_item_user_kick;
    }

    /* renamed from: b */
    public void m18904b(int i) {
        if (i == -1) {
            this.f11255c.clear();
        } else if (this.f11255c != null && this.f11255c.size() > i) {
            this.f11255c.remove(i);
        }
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m18912a(int i, String str) {
        SessionInfo sessionInfo = (SessionInfo) this.f11255c.get(i);
        if (sessionInfo != null) {
            sessionInfo.setDeviceNote(str);
        }
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nrzs.ft.adapter.UserKickAdapter$b */
    /* loaded from: classes2.dex */
    public static class C2005b extends BaseViewHolder {

        /* renamed from: g */
        private LinearLayout f10710g = (LinearLayout) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_ll_root);

        /* renamed from: a */
        TextView f10704a = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_user_kick_name);

        /* renamed from: f */
        TextView f10709f = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_user_kick_current);

        /* renamed from: b */
        TextView f10705b = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_user_kick_remark);

        /* renamed from: c */
        TextView f10706c = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_user_kick_time);

        /* renamed from: d */
        TextView f10707d = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_user_kick_unOnline);

        /* renamed from: e */
        TextView f10708e = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_user_kick_info);

        public C2005b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }

    /* renamed from: b */
    public void m18903b(final int i, String str) {
        new InputNoteDialog(m18480d(), (SessionInfo) this.f11255c.get(i), this.f10691d, new InputNoteDialog.AbstractC3761a() { // from class: com.nrzs.ft.adapter.UserKickAdapter.4
            @Override // p110z1.InputNoteDialog.AbstractC3761a
            /* renamed from: a */
            public void mo12133a(String str2) {
                SessionInfo sessionInfo = (SessionInfo) UserKickAdapter.this.f11255c.get(i);
                if (sessionInfo != null) {
                    UserKickAdapter.this.f10690b.mo12160a(sessionInfo.getDeviceCode(), sessionInfo.getUserId(), str2, i);
                }
            }
        }, str).show();
    }

    /* renamed from: c */
    public void m18902c(final int i) {
        new SessionInfoDialog(m18480d(), (SessionInfo) this.f11255c.get(i), this.f10691d, new SessionInfoDialog.AbstractC3766a() { // from class: com.nrzs.ft.adapter.UserKickAdapter.5
            @Override // p110z1.SessionInfoDialog.AbstractC3766a
            /* renamed from: a */
            public void mo12131a(String str) {
                SessionInfo sessionInfo = (SessionInfo) UserKickAdapter.this.f11255c.get(i);
                if (sessionInfo != null) {
                    UserKickAdapter.this.f10690b.mo12160a(sessionInfo.getDeviceCode(), sessionInfo.getUserId(), str, i);
                }
            }

            @Override // p110z1.SessionInfoDialog.AbstractC3766a
            /* renamed from: a */
            public void mo12132a() {
                SessionInfo sessionInfo = (SessionInfo) UserKickAdapter.this.f11255c.get(i);
                if (sessionInfo != null) {
                    UserKickAdapter.this.m18910a(sessionInfo, i);
                }
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18910a(SessionInfo sessionInfo, int i) {
        AbstractC2004a aVar = this.f10690b;
        if (aVar != null) {
            aVar.mo12161a(sessionInfo.getUserId(), sessionInfo.getSessionId(), i);
        }
    }
}
