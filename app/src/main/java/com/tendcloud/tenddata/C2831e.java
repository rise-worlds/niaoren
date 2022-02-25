package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.e */
/* loaded from: classes2.dex */
final class C2831e extends AbstractC2790d {
    private boolean isUrlUpdated = false;
    private String url;

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
        return AbstractC2790d.MF_JSON;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2831e(String str, int i) {
        super(str, i);
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public String getUrl() {
        return this.isUrlUpdated ? this.url : C2663aa.f13465h;
    }

    @Override // com.tendcloud.tenddata.AbstractC2790d
    public void setUrl(String str) {
        this.isUrlUpdated = true;
        this.url = str;
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
