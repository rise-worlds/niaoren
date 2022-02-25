package com.angel.nrzs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.nrzs.data.other.bean.SgbInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;

/* loaded from: classes.dex */
public class RewardAdapter extends BaseListAdapter<SgbInfo, C0722a> {

    /* renamed from: a */
    public long f5200a = 0;

    /* renamed from: b */
    TextWatcher f5201b = new TextWatcher() { // from class: com.angel.nrzs.adapter.RewardAdapter.3
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            SgbInfo sgbInfo = (SgbInfo) RewardAdapter.this.f11255c.get(RewardAdapter.this.f11255c.size() - 1);
            if (!editable.toString().trim().equals("")) {
                long parseLong = Long.parseLong(editable.toString());
                sgbInfo.SGB = parseLong;
                RewardAdapter.this.f5200a = parseLong;
                return;
            }
            sgbInfo.SGB = 0L;
            RewardAdapter.this.f5200a = 0L;
        }
    };

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C0692R.layout.nrzs_item_reward_layout;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public RewardAdapter(List<SgbInfo> list) {
        super(list);
    }

    /* renamed from: a */
    public C0722a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C0722a(layoutInflater, viewGroup, i);
    }

    @RequiresApi(api = 16)
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull final C0722a aVar, final int i) {
        final SgbInfo sgbInfo = (SgbInfo) this.f11255c.get(i);
        if (sgbInfo.isonclick) {
            aVar.f5212d.setBackground(m18480d().getResources().getDrawable(C0692R.C0693drawable.f2074cp));
            aVar.f5209a.setTextColor(m18480d().getResources().getColor(C0692R.color.f1449fa));
        } else {
            aVar.f5212d.setBackground(m18480d().getResources().getDrawable(C0692R.C0693drawable.f2075cq));
            aVar.f5209a.setTextColor(m18480d().getResources().getColor(C0692R.color.f1261a7));
        }
        if (sgbInfo.type != 1) {
            aVar.f5209a.setVisibility(0);
            aVar.f5211c.setVisibility(8);
            TextView textView = aVar.f5209a;
            textView.setText(sgbInfo.SGB + "");
        } else if (sgbInfo.isonclick) {
            aVar.f5209a.setVisibility(8);
            aVar.f5211c.setVisibility(0);
            if (sgbInfo.SGB != 0) {
                EditText editText = aVar.f5211c;
                editText.setText(sgbInfo.SGB + "");
                aVar.f5211c.setSelection(String.valueOf(sgbInfo.SGB).length());
            }
        } else {
            aVar.f5211c.setVisibility(8);
            aVar.f5209a.setVisibility(0);
            if (sgbInfo.SGB == 0) {
                aVar.f5209a.setText("其他");
            } else {
                TextView textView2 = aVar.f5209a;
                textView2.setText(sgbInfo.SGB + "");
            }
        }
        aVar.f5212d.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.RewardAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (sgbInfo.type == 1) {
                    aVar.f5209a.setVisibility(8);
                    aVar.f5211c.setVisibility(0);
                    aVar.f5212d.setBackground(RewardAdapter.this.m18480d().getResources().getDrawable(C0692R.C0693drawable.f2074cp));
                } else {
                    aVar.f5209a.setVisibility(0);
                    aVar.f5211c.setVisibility(8);
                    RewardAdapter.this.m25156c((View) aVar.f5211c);
                }
                RewardAdapter.this.m25157b(i);
            }
        });
        aVar.f5211c.addTextChangedListener(this.f5201b);
        aVar.f5211c.postDelayed(new Runnable() { // from class: com.angel.nrzs.adapter.RewardAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                aVar.f5211c.requestFocus();
                Context d = RewardAdapter.this.m18480d();
                RewardAdapter.this.m18480d();
                InputMethodManager inputMethodManager = (InputMethodManager) d.getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.showSoftInput(aVar.f5211c, 0);
                }
            }
        }, 0L);
    }

    /* renamed from: b */
    public void m25157b(int i) {
        if (!(this.f11255c == null || this.f11255c.size() == 0)) {
            SgbInfo sgbInfo = (SgbInfo) this.f11255c.get(i);
            for (int i2 = 0; i2 < this.f11255c.size(); i2++) {
                if (i2 == i) {
                    sgbInfo.isonclick = true;
                    this.f5200a = sgbInfo.SGB;
                } else {
                    ((SgbInfo) this.f11255c.get(i2)).isonclick = false;
                }
            }
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public long m25161a() {
        return this.f5200a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.angel.nrzs.adapter.RewardAdapter$a */
    /* loaded from: classes.dex */
    public static class C0722a extends BaseViewHolder {

        /* renamed from: a */
        TextView f5209a = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app_reward_item_money);

        /* renamed from: c */
        EditText f5211c = (EditText) this.itemView.findViewById(C0692R.C0694id.nrzs_app_reward_item_money_edtext);

        /* renamed from: d */
        LinearLayout f5212d = (LinearLayout) this.itemView.findViewById(C0692R.C0694id.f2456ai);

        /* renamed from: b */
        TextView f5210b = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app_reward_item_money_img);

        C0722a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m25156c(View view) {
        Context d = m18480d();
        m18480d();
        InputMethodManager inputMethodManager = (InputMethodManager) d.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
