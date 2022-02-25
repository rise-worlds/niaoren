package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.h */
/* loaded from: classes2.dex */
final class C2931h extends AbstractC2790d {
    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getCert() {
        return "";
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getHost() {
        return C2663aa.f13459b;
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getMessageFormat() {
        return "MP";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2931h(String str, int i) {
        super(str, i);
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getUrl() {
        return "https" + getHost() + C2663aa.f13462e;
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getIP() {
        return C2663aa.f13461d;
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getRootFolder() {
        return super.getRootFolder();
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getDataFolder() {
        return super.getDataFolder();
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public int getFileLimitType() {
        return super.getFileLimitType();
    }
}
