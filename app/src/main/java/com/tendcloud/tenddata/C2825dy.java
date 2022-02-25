package com.tendcloud.tenddata;

import android.os.Parcel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dy */
/* loaded from: classes2.dex */
public class C2825dy extends File {
    public final String content;

    public static String readFile(String str) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
            try {
                String str2 = "";
                for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                    sb.append(str2);
                    sb.append(readLine);
                    str2 = "\n";
                    if (sb.length() > 104857600) {
                        throw new RuntimeException("Input stream more than 100 MB size limit");
                    }
                }
                String sb2 = sb.toString();
                try {
                    bufferedReader2.close();
                } catch (Throwable unused) {
                }
                return sb2;
            } catch (Throwable unused2) {
                bufferedReader = bufferedReader2;
                try {
                    String sb3 = sb.toString();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused3) {
                        }
                    }
                    return sb3;
                } catch (Throwable th) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused4) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable unused5) {
        }
    }

    protected C2825dy(String str) {
        super(str);
        this.content = readFile(str);
    }

    protected C2825dy(Parcel parcel) {
        super(parcel.readString());
        this.content = parcel.readString();
    }

    @Override // java.io.File
    public long length() {
        return this.content.length();
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dy$c */
    /* loaded from: classes2.dex */
    public static class C2828c extends C2825dy {
        private String[] fields;

        public static C2828c get(int i) {
            try {
                return new C2828c(String.format("/proc/%d/stat", Integer.valueOf(i)));
            } catch (Throwable unused) {
                return null;
            }
        }

        private C2828c(String str) {
            super(str);
            this.fields = new String[0];
            try {
                this.fields = this.content.split("\\s+");
            } catch (Throwable unused) {
            }
        }

        private C2828c(Parcel parcel) {
            super(parcel);
            this.fields = new String[0];
            try {
                this.fields = parcel.createStringArray();
            } catch (Throwable unused) {
            }
        }

        public long startTime() {
            try {
                return Long.parseLong(this.fields[21]);
            } catch (Throwable unused) {
                return 0L;
            }
        }

        public String getComm() {
            try {
                return this.fields[1].replace("(", "").replace(")", "");
            } catch (Throwable unused) {
                return "";
            }
        }

        public char state() {
            try {
                return this.fields[2].charAt(0);
            } catch (Throwable unused) {
                return (char) 0;
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dy$d */
    /* loaded from: classes2.dex */
    static class C2829d extends C2825dy {
        public static C2829d get(int i) {
            try {
                return new C2829d(String.format("/proc/%d/status", Integer.valueOf(i)));
            } catch (Throwable unused) {
                return null;
            }
        }

        private C2829d(String str) {
            super(str);
        }

        private C2829d(Parcel parcel) {
            super(parcel);
        }

        public String getValue(String str) {
            String[] split;
            try {
                for (String str2 : this.content.split("\n")) {
                    if (str2.startsWith(str + ":")) {
                        return str2.split(str + ":")[1].trim();
                    }
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }

        public int getUid() {
            try {
                return Integer.parseInt(getValue("Uid").split("\\s+")[0]);
            } catch (Throwable unused) {
                return -1;
            }
        }

        public int getGid() {
            try {
                return Integer.parseInt(getValue("Gid").split("\\s+")[0]);
            } catch (Throwable unused) {
                return -1;
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dy$a */
    /* loaded from: classes2.dex */
    static class C2826a extends C2825dy {
        public ArrayList groups;

        public static C2826a get(int i) {
            try {
                return new C2826a(String.format("/proc/%d/cgroup", Integer.valueOf(i)));
            } catch (Throwable unused) {
                return null;
            }
        }

        private C2826a(String str) {
            super(str);
            try {
                this.groups = new ArrayList();
                for (String str2 : this.content.split("\n")) {
                    try {
                        this.groups.add(new C2827b(str2));
                    } catch (Throwable unused) {
                    }
                }
            } catch (Throwable unused2) {
            }
        }

        public C2827b getGroup(String str) {
            try {
                Iterator it = this.groups.iterator();
                while (it.hasNext()) {
                    C2827b bVar = (C2827b) it.next();
                    for (String str2 : bVar.subsystems.split(",")) {
                        if (str2.equals(str)) {
                            return bVar;
                        }
                    }
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dy$b */
    /* loaded from: classes2.dex */
    static class C2827b {
        protected String group;

        /* renamed from: id */
        protected int f13833id;
        protected String subsystems;

        protected C2827b(String str) {
            try {
                String[] split = str.split(":");
                this.f13833id = Integer.parseInt(split[0]);
                this.subsystems = split[1];
                this.group = split[2];
            } catch (Throwable unused) {
            }
        }

        protected C2827b(Parcel parcel) {
            try {
                this.f13833id = parcel.readInt();
                this.subsystems = parcel.readString();
                this.group = parcel.readString();
            } catch (Throwable unused) {
            }
        }
    }
}
