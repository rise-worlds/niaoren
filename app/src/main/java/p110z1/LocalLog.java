package p110z1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import p110z1.Log;

/* renamed from: z1.ug */
/* loaded from: classes3.dex */
public class LocalLog implements Log {

    /* renamed from: a */
    public static final String f23445a = "com.j256.ormlite.logger.level";

    /* renamed from: b */
    public static final String f23446b = "com.j256.ormlite.logger.file";

    /* renamed from: f */
    private static PrintStream f23450f;

    /* renamed from: h */
    private final String f23452h;

    /* renamed from: i */
    private final Log.EnumC5569a f23453i;

    /* renamed from: d */
    private static final Log.EnumC5569a f23448d = Log.EnumC5569a.DEBUG;

    /* renamed from: e */
    private static final ThreadLocal<DateFormat> f23449e = new ThreadLocal<DateFormat>() { // from class: z1.ug.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        }
    };

    /* renamed from: c */
    public static final String f23447c = "/ormliteLocalLog.properties";

    /* renamed from: g */
    private static final List<C5568a> f23451g = m628a(LocalLog.class.getResourceAsStream(f23447c));

    static {
        m627a(System.getProperty(f23446b));
    }

    public LocalLog(String str) {
        this.f23452h = LoggerFactory.m543b(str);
        List<C5568a> list = f23451g;
        Log.EnumC5569a aVar = null;
        if (list != null) {
            for (C5568a aVar2 : list) {
                if (aVar2.f23454a.matcher(str).matches() && (aVar == null || aVar2.f23455b.ordinal() < aVar.ordinal())) {
                    aVar = aVar2.f23455b;
                }
            }
        }
        if (aVar == null) {
            String property = System.getProperty(f23445a);
            if (property == null) {
                aVar = f23448d;
            } else {
                try {
                    aVar = Log.EnumC5569a.valueOf(property.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Level '" + property + "' was not found", e);
                }
            }
        }
        this.f23453i = aVar;
    }

    /* renamed from: a */
    public static void m627a(String str) {
        if (str == null) {
            f23450f = System.out;
            return;
        }
        try {
            f23450f = new PrintStream(new File(str));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Log file " + str + " was not found", e);
        }
    }

    @Override // p110z1.Log
    /* renamed from: a */
    public boolean mo623a(Log.EnumC5569a aVar) {
        return this.f23453i.isEnabled(aVar);
    }

    @Override // p110z1.Log
    /* renamed from: a */
    public void mo622a(Log.EnumC5569a aVar, String str) {
        m625b(aVar, str, null);
    }

    @Override // p110z1.Log
    /* renamed from: a */
    public void mo621a(Log.EnumC5569a aVar, String str, Throwable th) {
        m625b(aVar, str, th);
    }

    /* renamed from: a */
    void m629a() {
        f23450f.flush();
    }

    /* renamed from: a */
    static List<C5568a> m628a(InputStream inputStream) {
        try {
            if (inputStream != null) {
                try {
                    List<C5568a> b = m626b(inputStream);
                    try {
                        inputStream.close();
                        return b;
                    } catch (IOException unused) {
                        return b;
                    }
                } catch (IOException e) {
                    PrintStream printStream = System.err;
                    printStream.println("IO exception reading the log properties file '/ormliteLocalLog.properties': " + e);
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    /* renamed from: b */
    private static List<C5568a> m626b(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            if (!(readLine.length() == 0 || readLine.charAt(0) == '#')) {
                String[] split = readLine.split(SimpleComparison.f23609c);
                if (split.length != 2) {
                    PrintStream printStream = System.err;
                    printStream.println("Line is not in the format of 'pattern = level': " + readLine);
                } else {
                    try {
                        arrayList.add(new C5568a(Pattern.compile(split[0].trim()), Log.EnumC5569a.valueOf(split[1].trim())));
                    } catch (IllegalArgumentException unused) {
                        PrintStream printStream2 = System.err;
                        printStream2.println("Level '" + split[1] + "' was not found");
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m625b(Log.EnumC5569a aVar, String str, Throwable th) {
        if (mo623a(aVar)) {
            StringBuilder sb = new StringBuilder(128);
            sb.append(f23449e.get().format(new Date()));
            sb.append(" [");
            sb.append(aVar.name());
            sb.append("] ");
            sb.append(this.f23452h);
            sb.append(' ');
            sb.append(str);
            f23450f.println(sb.toString());
            if (th != null) {
                th.printStackTrace(f23450f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LocalLog.java */
    /* renamed from: z1.ug$a */
    /* loaded from: classes3.dex */
    public static class C5568a {

        /* renamed from: a */
        Pattern f23454a;

        /* renamed from: b */
        Log.EnumC5569a f23455b;

        public C5568a(Pattern pattern, Log.EnumC5569a aVar) {
            this.f23454a = pattern;
            this.f23455b = aVar;
        }
    }
}
