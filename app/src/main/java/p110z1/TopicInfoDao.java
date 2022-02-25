package p110z1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.nrzs.data.database.AppBaseDao;
import com.nrzs.data.game.bean.TopicInfo;
import java.util.List;

@Dao
/* renamed from: z1.akp */
/* loaded from: classes3.dex */
public interface TopicInfoDao extends AppBaseDao<TopicInfo> {
    @Delete
    /* renamed from: a */
    int mo12750a(TopicInfo topicInfo);

    @Query("select * from TopicInfo")
    /* renamed from: a */
    List<TopicInfo> mo12752a();

    @Query("select * from TopicInfo where TopicID == :topicId")
    /* renamed from: a */
    List<TopicInfo> mo12751a(long j);

    @Insert(onConflict = 1)
    /* renamed from: a */
    void mo12749a(List<TopicInfo> list);

    @Query("select Max(TopicID) from TopicInfo")
    /* renamed from: b */
    long mo12748b();

    @Query("select * from TopicInfo where tid == :tid")
    /* renamed from: b */
    TopicInfo mo12747b(long j);

    @Update(onConflict = 1)
    /* renamed from: b */
    void mo12745b(List<TopicInfo> list);

    @Query("DELETE FROM TopicInfo where TopicID == :tpicid")
    /* renamed from: c */
    void mo12744c(long j);
}
