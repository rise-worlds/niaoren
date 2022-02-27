package com.redwas.redwars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.smtt.sdk.TbsConfig;
import p110z1.aqr;

/* loaded from: classes2.dex */
public class CoursuActivity extends AppCompatActivity {

    /* renamed from: a */
    private ImageView f11656a;

    /* renamed from: b */
    private TextView f11657b;

    /* renamed from: c */
    private TextView f11658c;

    /* renamed from: a */
    public static void m18179a(Context context) {
        context.startActivity(new Intent(context, CoursuActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2342R.layout.activity_course);
        this.f11656a = (ImageView) findViewById(C2342R.C2344id.coust_back);
        this.f11657b = (TextView) findViewById(C2342R.C2344id.open_wx);
        this.f11658c = (TextView) findViewById(C2342R.C2344id.setSevice_tv);
        this.f11658c.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.CoursuActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoursuActivity.this.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            }
        });
        this.f11657b.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.CoursuActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (aqr.m11465k(view.getContext())) {
                    CoursuActivity.this.startActivity(CoursuActivity.this.getPackageManager().getLaunchIntentForPackage(TbsConfig.APP_WX));
                    return;
                }
                Toast.makeText(view.getContext(), "您还未安装微信，请先安装", 1).show();
            }
        });
        this.f11656a.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.CoursuActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoursuActivity.this.finish();
            }
        });
    }
}
