package p110z1;

import java.io.Serializable;

/* renamed from: z1.ajl */
/* loaded from: classes3.dex */
public class UpdateInfoResponse implements Serializable {
    private UpdateBean FUpdate;
    private UpdateBean NewZUpdate;
    private UpdateBean OUpdate;

    public UpdateBean getFUpdate() {
        return this.FUpdate;
    }

    public void setFUpdate(UpdateBean ajkVar) {
        this.FUpdate = ajkVar;
    }

    public UpdateBean getOUpdate() {
        return this.OUpdate;
    }

    public void setOUpdate(UpdateBean ajkVar) {
        this.OUpdate = ajkVar;
    }

    public UpdateBean getNewZUpdate() {
        return this.NewZUpdate;
    }

    public void setNewZUpdate(UpdateBean ajkVar) {
        this.NewZUpdate = ajkVar;
    }
}
