package p110z1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.nrzs.data.database.AppBaseDao;
import com.nrzs.data.redbag.bean.MoneyInfo;
import java.util.List;

@Dao
/* renamed from: z1.aln */
/* loaded from: classes3.dex */
public interface MoneyDao extends AppBaseDao<MoneyInfo> {
    @Delete
    /* renamed from: a */
    int mo12573a(MoneyInfo moneyInfo);

    @Query("select * from MoneyInfo")
    /* renamed from: a */
    List<MoneyInfo> mo12574a();

    @Insert(onConflict = 1)
    /* renamed from: b */
    void mo12571b(MoneyInfo moneyInfo);
}
