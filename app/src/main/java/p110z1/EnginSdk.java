package p110z1;

import android.content.Context;
import com.cyjh.mq.sdk.entity.Script4Run;
import java.io.PrintStream;

/* renamed from: z1.acd */
/* loaded from: classes3.dex */
public class EnginSdk {

    /* renamed from: a */
    public static boolean f15153a = false;

    /* renamed from: b */
    private EnginSDKCallback f15154b;

    /* renamed from: c */
    private EnginModel enginModel;

    /* renamed from: d */
    private Context context;

    /* renamed from: e */
    private String f15157e;

    /* renamed from: f */
    private EnginCallback f15158f;

    /* renamed from: g */
    private int f15159g;

    /* renamed from: f */
    public void m14377f() {
    }

    /* renamed from: a */
    public void m14389a(Context context, EnginSDKCallback aceVar, String str) {
        this.context = context;
        this.f15154b = aceVar;
        this.f15157e = str;
        if (this.enginModel == null) {
            this.enginModel = new EnginModel();
        }
    }

    /* renamed from: a */
    public void m14394a() {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14431a(this.context.getApplicationContext(), this.f15158f, this.f15157e);
        }
    }

    /* renamed from: a */
    public void m14392a(int i) {
        this.f15159g = i;
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14434a(i);
        }
    }

    /* renamed from: a */
    public void m14384a(byte[] bArr, String str, String str2, String str3) {
        if (this.enginModel != null) {
            Script4Run script4Run = new Script4Run();
            script4Run.lcPath = str;
            script4Run.atcPath = str2;
            script4Run.uiCfgPath = str3;
            this.enginModel.m14430a(script4Run);
        }
    }

    /* renamed from: b */
    public void m14383b() {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14426b();
        }
    }

    /* renamed from: c */
    public void m14381c() {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14436a();
        }
    }

    /* renamed from: d */
    public int m14379d() {
        return this.f15159g;
    }

    /* renamed from: e */
    public boolean m14378e() {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            return abtVar.m14424c();
        }
        return false;
    }

    /* renamed from: a */
    public void m14388a(String str) {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14432a(this.context, str);
        }
    }

    /* renamed from: b */
    public void m14382b(String str) {
        PrintStream printStream = System.out;
        printStream.println("json:" + str);
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14429a(str);
        }
    }

    /* renamed from: c */
    public void m14380c(String str) {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14425b(str);
        }
    }

    /* renamed from: a */
    public void m14385a(boolean z, int i, int i2, int i3, int i4) {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14427a(z, i, i2, i3, i4);
        }
    }

    /* renamed from: a */
    public IScriptUiModel m14390a(Context context, String str, String str2, String str3) {
        return ScriptModelDispatch.m14412a(context, str, str2, str3);
    }

    /* renamed from: a */
    public void m14386a(boolean z) {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14428a(z);
        }
    }

    /* renamed from: a */
    public void m14393a(float f, float f2) {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            abtVar.m14435a(f, f2);
        }
    }

    /* renamed from: a */
    public boolean m14391a(int i, int i2, int i3, int i4, String str, String str2, int i5, float f, int[] iArr) {
        EnginModel abtVar = this.enginModel;
        if (abtVar != null) {
            return abtVar.m14433a(i, i2, i3, i4, str, str2, i5, f, iArr);
        }
        return false;
    }

    private EnginSdk() {
        this.f15158f = new EnginCallback() { // from class: z1.acd.1
            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14371a(int i, String str, int i2) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12389a(i, str, i2);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14373a(int i) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12391a(i);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14369a(String str, int i) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12387a(str, i);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: b */
            public void mo14367b(int i) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12385b(i);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14374a(float f, int i) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12392a(f, i);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14375a() {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12393a();
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: b */
            public void mo14368b() {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12386b();
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: c */
            public void mo14365c() {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12383c();
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14372a(int i, String str) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12390a(i, str);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: d */
            public void mo14362d() {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12380d();
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: a */
            public void mo14370a(String str) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12388a(str);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: b */
            public void mo14366b(String str) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12384b(str);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: e */
            public String mo14361e() {
                if (EnginSdk.this.f15154b != null) {
                    return EnginSdk.this.f15154b.mo12379e();
                }
                return null;
            }

            @Override // p110z1.EnginCallback
            /* renamed from: f */
            public String mo14360f() {
                if (EnginSdk.this.f15154b != null) {
                    return EnginSdk.this.f15154b.mo12378f();
                }
                return null;
            }

            @Override // p110z1.EnginCallback
            /* renamed from: c */
            public void mo14364c(int i) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12382c(i);
                }
            }

            @Override // p110z1.EnginCallback
            /* renamed from: c */
            public void mo14363c(String str) {
                if (EnginSdk.this.f15154b != null) {
                    EnginSdk.this.f15154b.mo12381c(str);
                }
            }
        };
        this.f15159g = 0;
    }

    /* renamed from: g */
    public static final EnginSdk getInstance() {
        return C3330a.f15161a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EnginSdk.java */
    /* renamed from: z1.acd$a */
    /* loaded from: classes3.dex */
    public static class C3330a {

        /* renamed from: a */
        private static final EnginSdk f15161a = new EnginSdk();

        private C3330a() {
        }
    }
}
