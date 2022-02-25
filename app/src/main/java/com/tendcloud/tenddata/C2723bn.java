package com.tendcloud.tenddata;

import android.view.View;
import android.view.ViewGroup;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.List;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bn */
/* loaded from: classes2.dex */
class C2723bn {

    /* renamed from: a */
    private boolean f13606a = false;

    /* renamed from: b */
    private final C2725b f13607b = new C2725b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bn$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2724a {
        void accumulate(View view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bn$c */
    /* loaded from: classes2.dex */
    public static class C2726c {
        static final int SHORTEST_PREFIX = 1;
        static final int ZERO_LENGTH_PREFIX = 0;
        final String contentDescription;
        final int index;
        final int prefix;
        final String tag;
        final String viewClassName;
        final int viewId;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C2726c(int i, String str, int i2, int i3, String str2, String str3) {
            this.prefix = i;
            this.viewClassName = str;
            this.index = i2;
            this.viewId = i3;
            this.contentDescription = str2;
            this.tag = str3;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.prefix == 1) {
                    jSONObject.put("prefix", "shortest");
                }
                if (this.viewClassName != null) {
                    jSONObject.put("class", this.viewClassName);
                }
                if (this.index > -1) {
                    jSONObject.put("index", this.index);
                }
                if (this.viewId > -1) {
                    jSONObject.put(ConnectionModel.f10389a, this.viewId);
                }
                if (this.contentDescription != null) {
                    jSONObject.put("contentDescription", this.contentDescription);
                }
                if (this.tag != null) {
                    jSONObject.put("tag", this.tag);
                }
                return jSONObject.toString();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return jSONObject.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16236a(View view, List list, AbstractC2724a aVar) {
        try {
            if (!list.isEmpty() && !this.f13607b.full()) {
                List subList = list.subList(1, list.size());
                View a = m16234a((C2726c) list.get(0), view, this.f13607b.alloc());
                this.f13607b.free();
                if (a != null) {
                    m16232b(a, subList, aVar);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private void m16232b(View view, List list, AbstractC2724a aVar) {
        try {
            if (list.isEmpty()) {
                this.f13606a = true;
                aVar.accumulate(view);
            } else if ((view instanceof ViewGroup) && !this.f13607b.full()) {
                ViewGroup viewGroup = (ViewGroup) view;
                C2726c cVar = (C2726c) list.get(0);
                List subList = list.subList(1, list.size());
                int childCount = viewGroup.getChildCount();
                int alloc = this.f13607b.alloc();
                for (int i = 0; i < childCount; i++) {
                    View a = m16234a(cVar, viewGroup.getChildAt(i), alloc);
                    if (a != null) {
                        m16232b(a, subList, aVar);
                        if (!this.f13606a) {
                            this.f13607b.decrement(alloc);
                        }
                    }
                    if (cVar.index >= 0 && this.f13607b.read(alloc) > cVar.index) {
                        break;
                    }
                }
                this.f13607b.free();
                this.f13606a = false;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private View m16234a(C2726c cVar, View view, int i) {
        try {
            int read = this.f13607b.read(i);
            if (m16235a(cVar, view)) {
                this.f13607b.increment(i);
                if (cVar.index == -1 || cVar.index == read) {
                    return view;
                }
            }
            if (cVar.prefix != 1 || !(view instanceof ViewGroup)) {
                return null;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View a = m16234a(cVar, viewGroup.getChildAt(i2), i);
                if (a != null) {
                    return a;
                }
            }
            return null;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: a */
    private boolean m16235a(C2726c cVar, View view) {
        if (cVar.viewClassName != null && !m16233a(view, cVar.viewClassName)) {
            return false;
        }
        if (-1 != cVar.viewId && view.getId() != cVar.viewId) {
            return false;
        }
        if (cVar.contentDescription != null && !cVar.contentDescription.equals(view.getContentDescription())) {
            return false;
        }
        String str = cVar.tag;
        if (cVar.tag != null) {
            return view.getTag() != null && str.equals(view.getTag().toString());
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m16233a(Object obj, String str) {
        Class<?> cls = obj.getClass();
        while (true) {
            if (cls.getCanonicalName() != null && cls.getCanonicalName().equals(str)) {
                return true;
            }
            if (cls == Object.class) {
                return false;
            }
            cls = cls.getSuperclass();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bn$b */
    /* loaded from: classes2.dex */
    public static class C2725b {
        private static final int MAX_INDEX_STACK_SIZE = 256;
        private final int[] mStack = new int[256];
        private int mStackSize = 0;

        C2725b() {
        }

        boolean full() {
            return this.mStack.length == this.mStackSize;
        }

        int alloc() {
            int i = this.mStackSize;
            this.mStackSize = i + 1;
            this.mStack[i] = 0;
            return i;
        }

        int read(int i) {
            return this.mStack[i];
        }

        void increment(int i) {
            int[] iArr = this.mStack;
            iArr[i] = iArr[i] + 1;
        }

        void decrement(int i) {
            int[] iArr = this.mStack;
            iArr[i] = iArr[i] - 1;
        }

        void free() {
            this.mStackSize--;
            int i = this.mStackSize;
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }
    }
}
