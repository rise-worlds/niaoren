package com.nrzs.data.base;

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
import p110z1.C4745bt;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class CJU {
    private String getJ() {
        return "MD5";
    }

    public int getR() {
        return getRandomInt();
    }

    public String getK(String str, int i) {
        String str2 = "";
        try {
            str2 = URLDecoder.decode(getS(str, str2), "UTF-8");
            return getSign(str2, i);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    private String getS(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        getA(str, arrayList);
        getB(arrayList);
        sst(arrayList);
        return getC(str2, arrayList);
    }

    public void getD(String str, Map<String, String> map) {
        try {
            Set<String> keySet = map.keySet();
            ArrayList arrayList = new ArrayList();
            getF(str, map, keySet, arrayList);
            sst(arrayList);
            StringBuilder e = getE(arrayList);
            int randomInt = getRandomInt();
            map.put("R", randomInt + "");
            map.put("Sign", getSign(e.toString(), randomInt));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private MessageDigest getH(FileInputStream fileInputStream) throws NoSuchAlgorithmException, IOException {
        MessageDigest i = getI();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                return i;
            }
            i.update(bArr, 0, read);
        }
    }

    private MessageDigest getI() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(getJ());
    }

    private void getF(String str, Map<String, String> map, Set<String> set, List<String> list) {
        for (String str2 : set) {
            if (!str.contains("UpdateUserInfo")) {
                list.add(str2.toLowerCase() + C4745bt.f20071b + map.get(str2));
            } else if (!str2.equals("Img")) {
                list.add(str2.toLowerCase() + C4745bt.f20071b + map.get(str2));
            }
        }
    }

    private StringBuilder getE(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            String[] split = str.split(C4745bt.f20071b);
            if (split.length == 2) {
                sb.append(split[1]);
            }
        }
        return sb;
    }

    public String getC(String str, List<String> list) {
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : list) {
            String[] split = str2.split(C4745bt.f20071b);
            if (split.length == 2) {
                sb.append(split[1]);
            }
        }
        return sb.toString();
    }

    private void getB(List<String> list) {
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

    private void getA(String str, List<String> list) {
        for (String str2 : str.split(C4745bt.f20071b)) {
            String[] split = str2.split(SimpleComparison.f23609c);
            if (split.length == 2) {
                list.add(split[0].toLowerCase() + C4745bt.f20071b + split[1]);
            }
        }
    }

    public void sst(List<String> list) {
        Collections.sort(list, $$Lambda$TEfSBt3hRUlBSSARfPEHsJesTtE.INSTANCE);
    }

    public String getSignForJS(String str, int i) {
        String str2 = "";
        try {
            str2 = URLDecoder.decode(getS(str, str2), "UTF-8");
            return getSign(str2, i);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public int getRandomInt() {
        return new Random().nextInt(8);
    }

    protected String getSign(String str, int i) {
        Abcd abcd = new Abcd();
        abcd.setSource(str);
        abcd.setIndex(i);
        abcd.setCryptType(6);
        String y11 = Ufc.getInstance().y11(abcd, Utils.m24103a());
        return TextUtils.isEmpty(y11) ? "" : y11;
    }
}
