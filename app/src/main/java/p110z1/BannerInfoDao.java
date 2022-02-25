package p110z1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.nrzs.data.database.AppBaseDao;
import com.nrzs.data.other.bean.AdResultInfoItem;
import java.util.List;

@Dao
/* renamed from: z1.akn */
/* loaded from: classes3.dex */
public interface BannerInfoDao extends AppBaseDao<AdResultInfoItem> {
    @Delete
    /* renamed from: a */
    int mo12762a(AdResultInfoItem adResultInfoItem);

    @Query("select * from AdResultInfoItem")
    /* renamed from: a */
    List<AdResultInfoItem> mo12765a();

    @Query("select * from AdResultInfoItem where AdPosition == :adPosition")
    /* renamed from: a */
    List<AdResultInfoItem> mo12764a(int i);

    @Query("select * from AdResultInfoItem where id == :bannerId")
    /* renamed from: a */
    List<AdResultInfoItem> mo12763a(long j);

    @Insert(onConflict = 1)
    /* renamed from: a */
    void mo12761a(List<AdResultInfoItem> list);

    @Query("DELETE FROM AdResultInfoItem")
    /* renamed from: b */
    void mo12760b();

    @Query("DELETE FROM AdResultInfoItem where AdPosition == :adPosition")
    /* renamed from: b */
    void mo12759b(int i);
}
