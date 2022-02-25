package p110z1;

import android.text.TextUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.p065en.Abcd;
import com.nrzs.data.p065en.Ufc;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/* renamed from: z1.ajn */
/* loaded from: classes3.dex */
public class CJU {
    /* renamed from: d */
    private String m12895d() {
        return "MD5";
    }

    /* renamed from: a */
    public int m12911a() {
        return m12903b();
    }

    /* renamed from: a */
    public String m12909a(String str, int i) {
        String str2 = "";
        try {
            str2 = URLDecoder.decode(m12908a(str, str2), "UTF-8");
            return m12897c(str2, i);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    /* renamed from: a */
    private String m12908a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        m12901b(str, arrayList);
        m12896c(arrayList);
        m12904a(arrayList);
        return m12907a(str2, arrayList);
    }

    /* renamed from: a */
    public void m12906a(String str, Map<String, String> map) {
        try {
            Set<String> keySet = map.keySet();
            ArrayList arrayList = new ArrayList();
            m12905a(str, map, keySet, arrayList);
            m12904a(arrayList);
            StringBuilder b = m12899b(arrayList);
            int b2 = m12903b();
            map.put("R", b2 + "");
            map.put("Sign", m12897c(b.toString(), b2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public List<String> m12900b(String str, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        Set<String> keySet = map.keySet();
        ArrayList arrayList2 = new ArrayList();
        m12905a(str, map, keySet, arrayList2);
        m12904a(arrayList2);
        StringBuilder b = m12899b(arrayList2);
        int b2 = m12903b();
        map.put("R", b2 + "");
        map.put("Sign", m12897c(b.toString(), b2));
        arrayList.add(b2 + "");
        arrayList.add(m12897c(b.toString(), b2));
        return arrayList;
    }

    /* renamed from: a */
    private MessageDigest m12910a(FileInputStream fileInputStream) throws NoSuchAlgorithmException, IOException {
        MessageDigest c = m12898c();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                return c;
            }
            c.update(bArr, 0, read);
        }
    }

    /* renamed from: c */
    private MessageDigest m12898c() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(m12895d());
    }

    /* renamed from: a */
    private void m12905a(String str, Map<String, String> map, Set<String> set, List<String> list) {
        for (String str2 : set) {
            if (!str.contains("UpdateUserInfo")) {
                list.add(str2.toLowerCase() + C4745bt.f20071b + map.get(str2));
            } else if (!str2.equals("Img")) {
                list.add(str2.toLowerCase() + C4745bt.f20071b + map.get(str2));
            }
        }
    }

    /* renamed from: b */
    private StringBuilder m12899b(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            String[] split = str.split(C4745bt.f20071b);
            if (split.length == 2) {
                sb.append(split[1]);
            }
        }
        return sb;
    }

    /* renamed from: a */
    public String m12907a(String str, List<String> list) {
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : list) {
            String[] split = str2.split(C4745bt.f20071b);
            if (split.length == 2) {
                sb.append(split[1]);
            }
        }
        return sb.toString();
    }

    /* renamed from: c */
    private void m12896c(List<String> list) {
        if (list.size() > 0) {
            String str = list.get(0);
            String[] split = str.split("\\?");
            if (split.length == 2) {
                list.remove(0);
                str = split[1];
            }
            list.add(0, str);
        }
    }

    /* renamed from: b */
    private void m12901b(String str, List<String> list) {
        for (String str2 : str.split(C4745bt.f20071b)) {
            String[] split = str2.split(SimpleComparison.f23609c);
            if (split.length == 2) {
                list.add(split[0].toLowerCase() + C4745bt.f20071b + split[1]);
            }
        }
    }

    /* renamed from: a */
    public void m12904a(List<String> list) {
        Collections.sort(list, $$Lambda$TEfSBt3hRUlBSSARfPEHsJesTtE.INSTANCE);
    }

    /* renamed from: b */
    public String m12902b(String str, int i) {
        String str2 = "";
        try {
            str2 = URLDecoder.decode(m12908a(str, str2), "UTF-8");
            return m12897c(str2, i);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    /* renamed from: b */
    public int m12903b() {
        return new Random().nextInt(8);
    }

    /* renamed from: c */
    protected String m12897c(String str, int i) {
        Abcd abcd = new Abcd();
        abcd.setSource(str);
        abcd.setIndex(i);
        abcd.setCryptType(6);
        String y11 = Ufc.getInstance().y11(abcd, Utils.m24103a());
        return TextUtils.isEmpty(y11) ? "" : y11;
    }
}
