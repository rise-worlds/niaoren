package com.stripe.android.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import com.stripe.android.C2364R;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.PaymentSessionData;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.ShippingMethod;
import java.util.List;

/* loaded from: classes2.dex */
public class PaymentFlowActivity extends StripeActivity {

    /* renamed from: a */
    public static final String f12519a = "default_shipping_method";

    /* renamed from: b */
    public static final String f12520b = "shipping_is_shipping_info_valid";

    /* renamed from: c */
    public static final String f12521c = "shipping_info_data";

    /* renamed from: d */
    public static final String f12522d = "shipping_info_error";

    /* renamed from: e */
    public static final String f12523e = "shipping_info_processed";

    /* renamed from: f */
    public static final String f12524f = "shipping_info_submitted";

    /* renamed from: g */
    public static final String f12525g = "valid_shipping_methods";

    /* renamed from: h */
    static final String f12526h = "PaymentFlowActivity";

    /* renamed from: i */
    private BroadcastReceiver f12527i;

    /* renamed from: p */
    private BroadcastReceiver f12528p;

    /* renamed from: q */
    private PaymentFlowPagerAdapter f12529q;

    /* renamed from: r */
    private ViewPager f12530r;

    /* renamed from: s */
    private PaymentSessionData f12531s;

    /* renamed from: t */
    private ShippingInformation f12532t;

    /* renamed from: u */
    private List<ShippingMethod> f12533u;

    /* renamed from: v */
    private ShippingMethod f12534v;

    @Override // com.stripe.android.view.StripeActivity, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.stripe.android.view.StripeActivity, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.stripe.android.view.StripeActivity, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stripe.android.view.StripeActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        CustomerSession.m18072a().m18055a(PaymentSession.f11905a);
        CustomerSession.m18072a().m18055a(f12526h);
        this.f12647o.setLayoutResource(C2364R.layout.activity_shipping_flow);
        this.f12647o.inflate();
        this.f12530r = (ViewPager) findViewById(C2364R.C2366id.shipping_flow_viewpager);
        PaymentSessionConfig paymentSessionConfig = (PaymentSessionConfig) getIntent().getParcelableExtra(PaymentSession.f11910f);
        this.f12531s = (PaymentSessionData) getIntent().getParcelableExtra(PaymentSession.f11909e);
        if (this.f12531s != null) {
            this.f12529q = new PaymentFlowPagerAdapter(this, paymentSessionConfig);
            this.f12530r.setAdapter(this.f12529q);
            this.f12530r.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.stripe.android.view.PaymentFlowActivity.1
                @Override // android.support.v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                }

                @Override // android.support.v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                }

                @Override // android.support.v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                    PaymentFlowActivity paymentFlowActivity = PaymentFlowActivity.this;
                    paymentFlowActivity.setTitle(paymentFlowActivity.f12530r.getAdapter().getPageTitle(i));
                    if (PaymentFlowActivity.this.f12529q.m17238a(i) == PaymentFlowPagerEnum.SHIPPING_INFO) {
                        PaymentFlowActivity.this.f12529q.m17239a();
                    }
                }
            });
            this.f12528p = new BroadcastReceiver() { // from class: com.stripe.android.view.PaymentFlowActivity.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getBooleanExtra(PaymentFlowActivity.f12520b, false)) {
                        PaymentFlowActivity.this.m17344a();
                        PaymentFlowActivity.this.f12533u = intent.getParcelableArrayListExtra(PaymentFlowActivity.f12525g);
                        PaymentFlowActivity.this.f12534v = (ShippingMethod) intent.getParcelableExtra(PaymentFlowActivity.f12519a);
                        return;
                    }
                    PaymentFlowActivity.this.mo17219a(false);
                    String stringExtra = intent.getStringExtra(PaymentFlowActivity.f12522d);
                    if (stringExtra == null || stringExtra.isEmpty()) {
                        PaymentFlowActivity paymentFlowActivity = PaymentFlowActivity.this;
                        paymentFlowActivity.m17220a(paymentFlowActivity.getString(C2364R.string.invalid_shipping_information));
                    } else {
                        PaymentFlowActivity.this.m17220a(stringExtra);
                    }
                    PaymentFlowActivity.this.f12532t = null;
                }
            };
            this.f12527i = new BroadcastReceiver() { // from class: com.stripe.android.view.PaymentFlowActivity.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    PaymentFlowActivity paymentFlowActivity = PaymentFlowActivity.this;
                    paymentFlowActivity.m17337a(paymentFlowActivity.f12533u, PaymentFlowActivity.this.f12534v);
                    PaymentFlowActivity.this.f12531s.m18092a(PaymentFlowActivity.this.f12532t);
                }
            };
            setTitle(this.f12529q.getPageTitle(this.f12530r.getCurrentItem()));
            return;
        }
        throw new IllegalArgumentException("PaymentFlowActivity launched without PaymentSessionData");
    }

    @Override // com.stripe.android.view.StripeActivity
    /* renamed from: b */
    protected void mo17218b() {
        if (this.f12529q.m17238a(this.f12530r.getCurrentItem()).equals(PaymentFlowPagerEnum.SHIPPING_INFO)) {
            m17335c();
        } else {
            m17329f();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stripe.android.view.StripeActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f12528p);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f12527i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stripe.android.view.StripeActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f12528p, new IntentFilter(f12523e));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f12527i, new IntentFilter(CustomerSession.f11799c));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17344a() {
        CustomerSession.m18072a().m18071a(this, this.f12532t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17337a(@NonNull List<ShippingMethod> list, @Nullable ShippingMethod shippingMethod) {
        mo17219a(false);
        this.f12529q.m17237a(list, shippingMethod);
        this.f12529q.m17236a(true);
        if (m17333d()) {
            ViewPager viewPager = this.f12530r;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            return;
        }
        this.f12531s.m18092a(this.f12532t);
        Intent intent = new Intent();
        intent.putExtra(PaymentSession.f11909e, this.f12531s);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: c */
    private void m17335c() {
        ShippingInformation shippingInformation = ((ShippingInfoWidget) findViewById(C2364R.C2366id.shipping_info_widget)).getShippingInformation();
        if (shippingInformation != null) {
            this.f12532t = shippingInformation;
            mo17219a(true);
            m17343a(shippingInformation);
        }
    }

    /* renamed from: a */
    private void m17343a(ShippingInformation shippingInformation) {
        Intent intent = new Intent(f12524f);
        intent.putExtra(f12521c, shippingInformation);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* renamed from: d */
    private boolean m17333d() {
        return this.f12530r.getCurrentItem() + 1 < this.f12529q.getCount();
    }

    /* renamed from: e */
    private boolean m17331e() {
        return this.f12530r.getCurrentItem() != 0;
    }

    /* renamed from: f */
    private void m17329f() {
        this.f12531s.m18091a(((SelectShippingMethodWidget) findViewById(C2364R.C2366id.select_shipping_method_widget)).getSelectedShippingMethod());
        Intent intent = new Intent();
        intent.putExtra(PaymentSession.f11909e, this.f12531s);
        setResult(-1, intent);
        finish();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (m17331e()) {
            ViewPager viewPager = this.f12530r;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            return;
        }
        super.onBackPressed();
    }
}
