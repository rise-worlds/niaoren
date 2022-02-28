package com.cyjh.db;

import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.intf.IDownloadState;
import java.sql.SQLException;
import p110z1.BaseDataType;
import p110z1.DataPersister;
import p110z1.DatabaseResults;
import p110z1.FieldType;
import p110z1.SqlType;

/* renamed from: com.cyjh.db.JsonPersister */
/* loaded from: classes.dex */
public class JsonPersister extends BaseDataType implements DataPersister {
    private static JsonPersister upgradeInfoPersister;

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        return "parseDefaultString";
    }

    public static JsonPersister getSingleton() {
        if (upgradeInfoPersister == null) {
            upgradeInfoPersister = new JsonPersister(SqlType.STRING, null);
        }
        return upgradeInfoPersister;
    }

    public JsonPersister(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) throws SQLException {
        return ((IDownloadState) obj).getState().toString();
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        try {
            if (woVar == null) {
                return BaseDownloadStateFactory.getDownloadNewState();
            }
            if (woVar.mo221c(i) == null) {
                return BaseDownloadStateFactory.getDownloadNewState();
            }
            return BaseDownloadStateFactory.getDownloadState(BaseDownloadStateFactory.State.mapIntToValue(Integer.valueOf(woVar.mo221c(i)).intValue()));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseDownloadStateFactory.getDownloadNewState();
        }
    }
}
