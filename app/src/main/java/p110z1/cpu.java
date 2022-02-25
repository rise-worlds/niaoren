package p110z1;

import com.tendcloud.tenddata.C2970ih;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.selectors.FilenameSelector;

/* compiled from: Strings.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\n\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a:\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0006\u001a4\u0010 \u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\u0087\b¢\u0006\u0002\u0010%\u001a4\u0010&\u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\u0087\b¢\u0006\u0002\u0010%\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b)\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010+\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010+\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u0010.\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u0010/\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u00100\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a \u00101\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00102\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00103\u001a\u000204*\u00020\u0002H\u0086\u0002\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00106\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u00106\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00107\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u0002\u001a\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u0002\u001a\u0015\u0010;\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\f\u001a\u000f\u0010<\u001a\u00020\n*\u0004\u0018\u00010\nH\u0087\b\u001a\u001c\u0010=\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010=\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001aG\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u000e\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0004\bE\u0010F\u001a=\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u0006\u0010B\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0002\bE\u001a4\u0010G\u001a\u00020\r*\u00020\u00022\u0006\u0010H\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010J\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u0002\u001a\u0012\u0010J\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u0002\u001a\u001a\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006\u001a\u0012\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a+\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010R\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00020SH\u0087\b\u001a\u001d\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\u0087\b\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001d\u0010[\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\u0087\b\u001a\"\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002\u001a\u001a\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002\u001a%\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002H\u0087\b\u001a=\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006¢\u0006\u0002\u0010^\u001a0\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a/\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010P\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0002\b_\u001a%\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010D\u001a\u00020\u0006H\u0087\b\u001a=\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006¢\u0006\u0002\u0010a\u001a0\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010c\u001a\u00020\u0002*\u00020\n2\u0006\u0010d\u001a\u00020\u00062\u0006\u0010e\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010(\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u0012\u0010f\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\n\u0010k\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010k\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u001a\u0016\u0010k\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010k\u001a\u00020\n*\u00020\nH\u0087\b\u001a!\u0010k\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u001a\u0016\u0010k\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010m\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010m\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u001a\u0016\u0010m\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010m\u001a\u00020\n*\u00020\nH\u0087\b\u001a!\u0010m\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u001a\u0016\u0010m\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010n\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010n\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u001a\u0016\u0010n\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010n\u001a\u00020\n*\u00020\nH\u0087\b\u001a!\u0010n\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\u0086\b\u001a\u0016\u0010n\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006o"}, m8860e = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", FilenameSelector.REGEX_KEY, "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", C2970ih.C2971a.LENGTH, "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", MSVSSConstants.WRITABLE_REPLACE, "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpu */
/* loaded from: classes3.dex */
public class cpu extends StringsJVM {
    @cey
    /* renamed from: q */
    private static final String m3839q(@dbs String str) {
        return str != null ? str : "";
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3971a(@NotNull CharSequence charSequence, @NotNull chd<? super Character, Boolean> chdVar) {
        cji.m5162f(charSequence, "$this$trim");
        cji.m5162f(chdVar, "predicate");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean booleanValue = chdVar.invoke(Character.valueOf(charSequence.charAt(!z ? i : length))).booleanValue();
            if (!z) {
                if (!booleanValue) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!booleanValue) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3941a(@NotNull String str, @NotNull chd<? super Character, Boolean> chdVar) {
        cji.m5162f(str, "$this$trim");
        cji.m5162f(chdVar, "predicate");
        String str2 = str;
        int length = str2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean booleanValue = chdVar.invoke(Character.valueOf(str2.charAt(!z ? i : length))).booleanValue();
            if (!z) {
                if (!booleanValue) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!booleanValue) {
                break;
            } else {
                length--;
            }
        }
        return str2.subSequence(i, length + 1).toString();
    }

    @NotNull
    /* renamed from: b */
    public static final CharSequence m3918b(@NotNull CharSequence charSequence, @NotNull chd<? super Character, Boolean> chdVar) {
        cji.m5162f(charSequence, "$this$trimStart");
        cji.m5162f(chdVar, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!chdVar.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    /* renamed from: b */
    public static final String m3895b(@NotNull String str, @NotNull chd<? super Character, Boolean> chdVar) {
        String str2;
        cji.m5162f(str, "$this$trimStart");
        cji.m5162f(chdVar, "predicate");
        String str3 = str;
        int length = str3.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (!chdVar.invoke(Character.valueOf(str3.charAt(i))).booleanValue()) {
                str2 = str3.subSequence(i, str3.length());
                break;
            } else {
                i++;
            }
        }
        return str2.toString();
    }

    @NotNull
    /* renamed from: c */
    public static final CharSequence m3884c(@NotNull CharSequence charSequence, @NotNull chd<? super Character, Boolean> chdVar) {
        cji.m5162f(charSequence, "$this$trimEnd");
        cji.m5162f(chdVar, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (chdVar.invoke(Character.valueOf(charSequence.charAt(length))).booleanValue());
        return charSequence.subSequence(0, length + 1);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3871c(@NotNull String str, @NotNull chd<? super Character, Boolean> chdVar) {
        String str2;
        cji.m5162f(str, "$this$trimEnd");
        cji.m5162f(chdVar, "predicate");
        String str3 = str;
        int length = str3.length();
        while (true) {
            length--;
            if (length >= 0) {
                if (!chdVar.invoke(Character.valueOf(str3.charAt(length))).booleanValue()) {
                    str2 = str3.subSequence(0, length + 1);
                    break;
                }
            } else {
                break;
            }
        }
        return str2.toString();
    }

    @cey
    /* renamed from: n */
    private static final String m3842n(@NotNull String str) {
        if (str != null) {
            return cpl.m3937b((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @cey
    /* renamed from: o */
    private static final String m3841o(@NotNull String str) {
        if (str != null) {
            return cpl.m3892c((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @cey
    /* renamed from: p */
    private static final String m3840p(@NotNull String str) {
        if (str != null) {
            return cpl.m3869d((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    /* renamed from: a */
    public static /* synthetic */ CharSequence m3990a(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return cpl.m3991a(charSequence, i, c);
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3991a(@NotNull CharSequence charSequence, int i, char c) {
        cji.m5162f(charSequence, "$this$padStart");
        if (i < 0) {
            throw new IllegalArgumentException("Desired length " + i + " is less than zero.");
        } else if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(i);
            int length = i - charSequence.length();
            int i2 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append(c);
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            sb.append(charSequence);
            return sb;
        }
    }

    /* renamed from: a */
    public static /* synthetic */ String m3947a(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return cpl.m3948a(str, i, c);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3948a(@NotNull String str, int i, char c) {
        cji.m5162f(str, "$this$padStart");
        return cpl.m3991a((CharSequence) str, i, c).toString();
    }

    /* renamed from: b */
    public static /* synthetic */ CharSequence m3930b(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return cpl.m3931b(charSequence, i, c);
    }

    @NotNull
    /* renamed from: b */
    public static final CharSequence m3931b(@NotNull CharSequence charSequence, int i, char c) {
        cji.m5162f(charSequence, "$this$padEnd");
        if (i < 0) {
            throw new IllegalArgumentException("Desired length " + i + " is less than zero.");
        } else if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(i);
            sb.append(charSequence);
            int length = i - charSequence.length();
            int i2 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append(c);
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return sb;
        }
    }

    /* renamed from: b */
    public static /* synthetic */ String m3902b(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return cpl.m3903b(str, i, c);
    }

    @NotNull
    /* renamed from: b */
    public static final String m3903b(@NotNull String str, int i, char c) {
        cji.m5162f(str, "$this$padEnd");
        return cpl.m3931b((CharSequence) str, i, c).toString();
    }

    @cey
    /* renamed from: j */
    private static final boolean m3847j(@dbs CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    @cey
    /* renamed from: k */
    private static final boolean m3846k(@NotNull CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    @cey
    /* renamed from: l */
    private static final boolean m3845l(@NotNull CharSequence charSequence) {
        return charSequence.length() > 0;
    }

    @cey
    /* renamed from: m */
    private static final boolean m3844m(@NotNull CharSequence charSequence) {
        return !cpl.m4074a(charSequence);
    }

    @cey
    /* renamed from: n */
    private static final boolean m3843n(@dbs CharSequence charSequence) {
        return charSequence == null || cpl.m4074a(charSequence);
    }

    /* compiled from: Strings.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\t\u0010\u0004\u001a\u00020\u0005H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m8860e = {"kotlin/text/StringsKt__StringsKt$iterator$1", "Lkotlin/collections/CharIterator;", "index", "", "hasNext", "", "nextChar", "", "kotlin-stdlib"})
    /* renamed from: z1.cpu$a */
    /* loaded from: classes3.dex */
    public static final class C5084a extends bzj {

        /* renamed from: a */
        final /* synthetic */ CharSequence f21027a;

        /* renamed from: b */
        private int f21028b;

        C5084a(CharSequence charSequence) {
            this.f21027a = charSequence;
        }

        @Override // p110z1.bzj
        /* renamed from: b */
        public char mo3838b() {
            CharSequence charSequence = this.f21027a;
            int i = this.f21028b;
            this.f21028b = i + 1;
            return charSequence.charAt(i);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f21028b < this.f21027a.length();
        }
    }

    @NotNull
    /* renamed from: e */
    public static final bzj m3856e(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$iterator");
        return new C5084a(charSequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <C extends CharSequence & R, R> R m3972a(C c, chc<? extends R> chcVar) {
        return c.length() == 0 ? (R) chcVar.invoke() : c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: b */
    private static final <C extends CharSequence & R, R> R m3919b(C c, chc<? extends R> chcVar) {
        return cpl.m4074a(c) ? (R) chcVar.invoke() : c;
    }

    @NotNull
    /* renamed from: f */
    public static final cme m3851f(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$indices");
        return new cme(0, charSequence.length() - 1);
    }

    /* renamed from: g */
    public static final int m3850g(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$lastIndex");
        return charSequence.length() - 1;
    }

    /* renamed from: b */
    public static final boolean m3932b(@NotNull CharSequence charSequence, int i) {
        cji.m5162f(charSequence, "$this$hasSurrogatePairAt");
        return i >= 0 && charSequence.length() + (-2) >= i && Character.isHighSurrogate(charSequence.charAt(i)) && Character.isLowSurrogate(charSequence.charAt(i + 1));
    }

    @NotNull
    /* renamed from: a */
    public static final String m3940a(@NotNull String str, @NotNull cme cmeVar) {
        cji.m5162f(str, "$this$substring");
        cji.m5162f(cmeVar, "range");
        String substring = str.substring(cmeVar.mo4665g().intValue(), cmeVar.mo4663i().intValue() + 1);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3970a(@NotNull CharSequence charSequence, @NotNull cme cmeVar) {
        cji.m5162f(charSequence, "$this$subSequence");
        cji.m5162f(cmeVar, "range");
        return charSequence.subSequence(cmeVar.mo4665g().intValue(), cmeVar.mo4663i().intValue() + 1);
    }

    @Annotations(m8902a = "Use parameters named startIndex and endIndex.", m8901b = @bwu(m8768a = "subSequence(startIndex = start, endIndex = end)", m8767b = {}))
    @cey
    /* renamed from: b */
    private static final CharSequence m3901b(@NotNull String str, int i, int i2) {
        return str.subSequence(i, i2);
    }

    /* renamed from: a */
    static /* synthetic */ String m3988a(CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = charSequence.length();
        }
        return charSequence.subSequence(i, i2).toString();
    }

    @cey
    /* renamed from: b */
    private static final String m3929b(@NotNull CharSequence charSequence, int i, int i2) {
        return charSequence.subSequence(i, i2).toString();
    }

    @NotNull
    /* renamed from: b */
    public static final String m3917b(@NotNull CharSequence charSequence, @NotNull cme cmeVar) {
        cji.m5162f(charSequence, "$this$substring");
        cji.m5162f(cmeVar, "range");
        return charSequence.subSequence(cmeVar.mo4665g().intValue(), cmeVar.mo4663i().intValue() + 1).toString();
    }

    /* renamed from: a */
    public static /* synthetic */ String m3951a(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return cpl.m3952a(str, c, str2);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3952a(@NotNull String str, char c, @NotNull String str2) {
        cji.m5162f(str, "$this$substringBefore");
        cji.m5162f(str2, "missingDelimiterValue");
        int a = cpl.m3994a((CharSequence) str, c, 0, false, 6, (Object) null);
        if (a == -1) {
            return str2;
        }
        String substring = str.substring(0, a);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: b */
    public static /* synthetic */ String m3898b(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return cpl.m3899b(str, str2, str3);
    }

    @NotNull
    /* renamed from: b */
    public static final String m3899b(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$substringBefore");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "missingDelimiterValue");
        int a = cpl.m3977a((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (a == -1) {
            return str3;
        }
        String substring = str.substring(0, a);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: b */
    public static /* synthetic */ String m3906b(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return cpl.m3907b(str, c, str2);
    }

    @NotNull
    /* renamed from: b */
    public static final String m3907b(@NotNull String str, char c, @NotNull String str2) {
        cji.m5162f(str, "$this$substringAfter");
        cji.m5162f(str2, "missingDelimiterValue");
        int a = cpl.m3994a((CharSequence) str, c, 0, false, 6, (Object) null);
        if (a == -1) {
            return str2;
        }
        String substring = str.substring(a + 1, str.length());
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: c */
    public static /* synthetic */ String m3874c(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return cpl.m3875c(str, str2, str3);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3875c(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$substringAfter");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "missingDelimiterValue");
        int a = cpl.m3977a((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (a == -1) {
            return str3;
        }
        String substring = str.substring(a + str2.length(), str.length());
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: c */
    public static /* synthetic */ String m3880c(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return cpl.m3881c(str, c, str2);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3881c(@NotNull String str, char c, @NotNull String str2) {
        cji.m5162f(str, "$this$substringBeforeLast");
        cji.m5162f(str2, "missingDelimiterValue");
        int b = cpl.m3935b((CharSequence) str, c, 0, false, 6, (Object) null);
        if (b == -1) {
            return str2;
        }
        String substring = str.substring(0, b);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: d */
    public static /* synthetic */ String m3859d(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return cpl.m3860d(str, str2, str3);
    }

    @NotNull
    /* renamed from: d */
    public static final String m3860d(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$substringBeforeLast");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "missingDelimiterValue");
        int b = cpl.m3923b((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (b == -1) {
            return str3;
        }
        String substring = str.substring(0, b);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: d */
    public static /* synthetic */ String m3863d(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return cpl.m3864d(str, c, str2);
    }

    @NotNull
    /* renamed from: d */
    public static final String m3864d(@NotNull String str, char c, @NotNull String str2) {
        cji.m5162f(str, "$this$substringAfterLast");
        cji.m5162f(str2, "missingDelimiterValue");
        int b = cpl.m3935b((CharSequence) str, c, 0, false, 6, (Object) null);
        if (b == -1) {
            return str2;
        }
        String substring = str.substring(b + 1, str.length());
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* renamed from: e */
    public static /* synthetic */ String m3852e(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return cpl.m3853e(str, str2, str3);
    }

    @NotNull
    /* renamed from: e */
    public static final String m3853e(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$substringAfterLast");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "missingDelimiterValue");
        int b = cpl.m3923b((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (b == -1) {
            return str3;
        }
        String substring = str.substring(b + str2.length(), str.length());
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3987a(@NotNull CharSequence charSequence, int i, int i2, @NotNull CharSequence charSequence2) {
        cji.m5162f(charSequence, "$this$replaceRange");
        cji.m5162f(charSequence2, "replacement");
        if (i2 >= i) {
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence, 0, i);
            sb.append(charSequence2);
            sb.append(charSequence, i2, charSequence.length());
            return sb;
        }
        throw new IndexOutOfBoundsException("End index (" + i2 + ") is less than start index (" + i + ").");
    }

    @cey
    /* renamed from: a */
    private static final String m3946a(@NotNull String str, int i, int i2, CharSequence charSequence) {
        if (str != null) {
            return cpl.m3987a((CharSequence) str, i, i2, charSequence).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3969a(@NotNull CharSequence charSequence, @NotNull cme cmeVar, @NotNull CharSequence charSequence2) {
        cji.m5162f(charSequence, "$this$replaceRange");
        cji.m5162f(cmeVar, "range");
        cji.m5162f(charSequence2, "replacement");
        return cpl.m3987a(charSequence, cmeVar.mo4665g().intValue(), cmeVar.mo4663i().intValue() + 1, charSequence2);
    }

    @cey
    /* renamed from: a */
    private static final String m3939a(@NotNull String str, cme cmeVar, CharSequence charSequence) {
        if (str != null) {
            return cpl.m3969a((CharSequence) str, cmeVar, charSequence).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3989a(@NotNull CharSequence charSequence, int i, int i2) {
        cji.m5162f(charSequence, "$this$removeRange");
        if (i2 < i) {
            throw new IndexOutOfBoundsException("End index (" + i2 + ") is less than start index (" + i + ").");
        } else if (i2 == i) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(charSequence.length() - (i2 - i));
            sb.append(charSequence, 0, i);
            sb.append(charSequence, i2, charSequence.length());
            return sb;
        }
    }

    @cey
    /* renamed from: c */
    private static final String m3877c(@NotNull String str, int i, int i2) {
        if (str != null) {
            return cpl.m3989a((CharSequence) str, i, i2).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    /* renamed from: c */
    public static final CharSequence m3883c(@NotNull CharSequence charSequence, @NotNull cme cmeVar) {
        cji.m5162f(charSequence, "$this$removeRange");
        cji.m5162f(cmeVar, "range");
        return cpl.m3989a(charSequence, cmeVar.mo4665g().intValue(), cmeVar.mo4663i().intValue() + 1);
    }

    @cey
    /* renamed from: b */
    private static final String m3894b(@NotNull String str, cme cmeVar) {
        if (str != null) {
            return cpl.m3883c(str, cmeVar).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3986a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        cji.m5162f(charSequence, "$this$removePrefix");
        cji.m5162f(charSequence2, "prefix");
        if (cpl.m3979a(charSequence, charSequence2, false, 2, (Object) null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    /* renamed from: a */
    public static final String m3945a(@NotNull String str, @NotNull CharSequence charSequence) {
        cji.m5162f(str, "$this$removePrefix");
        cji.m5162f(charSequence, "prefix");
        if (!cpl.m3979a((CharSequence) str, charSequence, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(charSequence.length());
        cji.m5175b(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }

    @NotNull
    /* renamed from: b */
    public static final CharSequence m3927b(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        cji.m5162f(charSequence, "$this$removeSuffix");
        cji.m5162f(charSequence2, "suffix");
        if (cpl.m3925b(charSequence, charSequence2, false, 2, (Object) null)) {
            return charSequence.subSequence(0, charSequence.length() - charSequence2.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    /* renamed from: b */
    public static final String m3900b(@NotNull String str, @NotNull CharSequence charSequence) {
        cji.m5162f(str, "$this$removeSuffix");
        cji.m5162f(charSequence, "suffix");
        if (!cpl.m3925b((CharSequence) str, charSequence, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(0, str.length() - charSequence.length());
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3981a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull CharSequence charSequence3) {
        cji.m5162f(charSequence, "$this$removeSurrounding");
        cji.m5162f(charSequence2, "prefix");
        cji.m5162f(charSequence3, "suffix");
        if (charSequence.length() < charSequence2.length() + charSequence3.length() || !cpl.m3979a(charSequence, charSequence2, false, 2, (Object) null) || !cpl.m3925b(charSequence, charSequence3, false, 2, (Object) null)) {
            return charSequence.subSequence(0, charSequence.length());
        }
        return charSequence.subSequence(charSequence2.length(), charSequence.length() - charSequence3.length());
    }

    @NotNull
    /* renamed from: a */
    public static final String m3944a(@NotNull String str, @NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        cji.m5162f(str, "$this$removeSurrounding");
        cji.m5162f(charSequence, "prefix");
        cji.m5162f(charSequence2, "suffix");
        if (str.length() >= charSequence.length() + charSequence2.length()) {
            String str2 = str;
            if (cpl.m3979a((CharSequence) str2, charSequence, false, 2, (Object) null) && cpl.m3925b((CharSequence) str2, charSequence2, false, 2, (Object) null)) {
                String substring = str.substring(charSequence.length(), str.length() - charSequence2.length());
                cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return str;
    }

    @NotNull
    /* renamed from: c */
    public static final CharSequence m3889c(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        cji.m5162f(charSequence, "$this$removeSurrounding");
        cji.m5162f(charSequence2, "delimiter");
        return cpl.m3981a(charSequence, charSequence2, charSequence2);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3876c(@NotNull String str, @NotNull CharSequence charSequence) {
        cji.m5162f(str, "$this$removeSurrounding");
        cji.m5162f(charSequence, "delimiter");
        return cpl.m3944a(str, charSequence, charSequence);
    }

    /* renamed from: a */
    public static /* synthetic */ String m3949a(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return cpl.m3950a(str, c, str2, str3);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3950a(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$replaceBefore");
        cji.m5162f(str2, "replacement");
        cji.m5162f(str3, "missingDelimiterValue");
        String str4 = str;
        int a = cpl.m3994a((CharSequence) str4, c, 0, false, 6, (Object) null);
        return a == -1 ? str3 : cpl.m3987a((CharSequence) str4, 0, a, (CharSequence) str2).toString();
    }

    /* renamed from: a */
    public static /* synthetic */ String m3942a(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return cpl.m3943a(str, str2, str3, str4);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3943a(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        cji.m5162f(str, "$this$replaceBefore");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "replacement");
        cji.m5162f(str4, "missingDelimiterValue");
        String str5 = str;
        int a = cpl.m3977a((CharSequence) str5, str2, 0, false, 6, (Object) null);
        return a == -1 ? str4 : cpl.m3987a((CharSequence) str5, 0, a, (CharSequence) str3).toString();
    }

    /* renamed from: b */
    public static /* synthetic */ String m3904b(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return cpl.m3905b(str, c, str2, str3);
    }

    @NotNull
    /* renamed from: b */
    public static final String m3905b(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$replaceAfter");
        cji.m5162f(str2, "replacement");
        cji.m5162f(str3, "missingDelimiterValue");
        String str4 = str;
        int a = cpl.m3994a((CharSequence) str4, c, 0, false, 6, (Object) null);
        return a == -1 ? str3 : cpl.m3987a((CharSequence) str4, a + 1, str.length(), (CharSequence) str2).toString();
    }

    /* renamed from: b */
    public static /* synthetic */ String m3896b(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return cpl.m3897b(str, str2, str3, str4);
    }

    @NotNull
    /* renamed from: b */
    public static final String m3897b(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        cji.m5162f(str, "$this$replaceAfter");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "replacement");
        cji.m5162f(str4, "missingDelimiterValue");
        String str5 = str;
        int a = cpl.m3977a((CharSequence) str5, str2, 0, false, 6, (Object) null);
        return a == -1 ? str4 : cpl.m3987a((CharSequence) str5, a + str2.length(), str.length(), (CharSequence) str3).toString();
    }

    /* renamed from: c */
    public static /* synthetic */ String m3872c(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return cpl.m3873c(str, str2, str3, str4);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3873c(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        cji.m5162f(str, "$this$replaceAfterLast");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "replacement");
        cji.m5162f(str4, "missingDelimiterValue");
        String str5 = str;
        int b = cpl.m3923b((CharSequence) str5, str2, 0, false, 6, (Object) null);
        return b == -1 ? str4 : cpl.m3987a((CharSequence) str5, b + str2.length(), str.length(), (CharSequence) str3).toString();
    }

    /* renamed from: c */
    public static /* synthetic */ String m3878c(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return cpl.m3879c(str, c, str2, str3);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3879c(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$replaceAfterLast");
        cji.m5162f(str2, "replacement");
        cji.m5162f(str3, "missingDelimiterValue");
        String str4 = str;
        int b = cpl.m3935b((CharSequence) str4, c, 0, false, 6, (Object) null);
        return b == -1 ? str3 : cpl.m3987a((CharSequence) str4, b + 1, str.length(), (CharSequence) str2).toString();
    }

    /* renamed from: d */
    public static /* synthetic */ String m3861d(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return cpl.m3862d(str, c, str2, str3);
    }

    @NotNull
    /* renamed from: d */
    public static final String m3862d(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        cji.m5162f(str, "$this$replaceBeforeLast");
        cji.m5162f(str2, "replacement");
        cji.m5162f(str3, "missingDelimiterValue");
        String str4 = str;
        int b = cpl.m3935b((CharSequence) str4, c, 0, false, 6, (Object) null);
        return b == -1 ? str3 : cpl.m3987a((CharSequence) str4, 0, b, (CharSequence) str2).toString();
    }

    /* renamed from: d */
    public static /* synthetic */ String m3857d(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return cpl.m3858d(str, str2, str3, str4);
    }

    @NotNull
    /* renamed from: d */
    public static final String m3858d(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        cji.m5162f(str, "$this$replaceBeforeLast");
        cji.m5162f(str2, "delimiter");
        cji.m5162f(str3, "replacement");
        cji.m5162f(str4, "missingDelimiterValue");
        String str5 = str;
        int b = cpl.m3923b((CharSequence) str5, str2, 0, false, 6, (Object) null);
        return b == -1 ? str4 : cpl.m3987a((CharSequence) str5, 0, b, (CharSequence) str3).toString();
    }

    @cey
    /* renamed from: a */
    private static final String m3965a(@NotNull CharSequence charSequence, cph cphVar, String str) {
        return cphVar.replace(charSequence, str);
    }

    @cey
    /* renamed from: a */
    private static final String m3964a(@NotNull CharSequence charSequence, cph cphVar, chd<? super cpf, ? extends CharSequence> chdVar) {
        return cphVar.replace(charSequence, chdVar);
    }

    @cey
    /* renamed from: b */
    private static final String m3915b(@NotNull CharSequence charSequence, cph cphVar, String str) {
        return cphVar.replaceFirst(charSequence, str);
    }

    @cey
    /* renamed from: a */
    private static final boolean m3968a(@NotNull CharSequence charSequence, cph cphVar) {
        return cphVar.matches(charSequence);
    }

    /* renamed from: b */
    public static final boolean m3928b(@NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2, int i2, int i3, boolean z) {
        cji.m5162f(charSequence, "$this$regionMatchesImpl");
        cji.m5162f(charSequence2, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!cov.m4227a(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m3992a(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3993a(charSequence, c, z);
    }

    /* renamed from: a */
    public static final boolean m3993a(@NotNull CharSequence charSequence, char c, boolean z) {
        cji.m5162f(charSequence, "$this$startsWith");
        return charSequence.length() > 0 && cov.m4227a(charSequence.charAt(0), c, z);
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m3933b(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3934b(charSequence, c, z);
    }

    /* renamed from: b */
    public static final boolean m3934b(@NotNull CharSequence charSequence, char c, boolean z) {
        cji.m5162f(charSequence, "$this$endsWith");
        return charSequence.length() > 0 && cov.m4227a(charSequence.charAt(cpl.m3850g(charSequence)), c, z);
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m3979a(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3980a(charSequence, charSequence2, z);
    }

    /* renamed from: a */
    public static final boolean m3980a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        cji.m5162f(charSequence, "$this$startsWith");
        cji.m5162f(charSequence2, "prefix");
        if (z || !(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            return cpl.m3928b(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
        }
        return cpl.m4020b((String) charSequence, (String) charSequence2, false, 2, (Object) null);
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m3982a(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3983a(charSequence, charSequence2, i, z);
    }

    /* renamed from: a */
    public static final boolean m3983a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, int i, boolean z) {
        cji.m5162f(charSequence, "$this$startsWith");
        cji.m5162f(charSequence2, "prefix");
        if (z || !(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            return cpl.m3928b(charSequence, i, charSequence2, 0, charSequence2.length(), z);
        }
        return cpl.m4055a((String) charSequence, (String) charSequence2, i, false, 4, (Object) null);
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m3925b(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3926b(charSequence, charSequence2, z);
    }

    /* renamed from: b */
    public static final boolean m3926b(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        cji.m5162f(charSequence, "$this$endsWith");
        cji.m5162f(charSequence2, "suffix");
        if (z || !(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            return cpl.m3928b(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
        }
        return cpl.m4013c((String) charSequence, (String) charSequence2, false, 2, (Object) null);
    }

    /* renamed from: c */
    public static /* synthetic */ String m3887c(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3888c(charSequence, charSequence2, z);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3888c(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        cji.m5162f(charSequence, "$this$commonPrefixWith");
        cji.m5162f(charSequence2, "other");
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i = 0;
        while (i < min && cov.m4227a(charSequence.charAt(i), charSequence2.charAt(i), z)) {
            i++;
        }
        int i2 = i - 1;
        if (cpl.m3932b(charSequence, i2) || cpl.m3932b(charSequence2, i2)) {
            i--;
        }
        return charSequence.subSequence(0, i).toString();
    }

    /* renamed from: d */
    public static /* synthetic */ String m3867d(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3868d(charSequence, charSequence2, z);
    }

    @NotNull
    /* renamed from: d */
    public static final String m3868d(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        int length;
        cji.m5162f(charSequence, "$this$commonSuffixWith");
        cji.m5162f(charSequence2, "other");
        int length2 = charSequence.length();
        int min = Math.min(length2, charSequence2.length());
        int i = 0;
        while (i < min && cov.m4227a(charSequence.charAt((length2 - i) - 1), charSequence2.charAt((length - i) - 1), z)) {
            i++;
        }
        if (cpl.m3932b(charSequence, (length2 - i) - 1) || cpl.m3932b(charSequence2, (length - i) - 1)) {
            i--;
        }
        return charSequence.subSequence(length2 - i, length2).toString();
    }

    /* renamed from: a */
    public static /* synthetic */ int m3959a(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3962a(charSequence, cArr, i, z);
    }

    /* renamed from: a */
    public static final int m3962a(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        boolean z2;
        cji.m5162f(charSequence, "$this$indexOfAny");
        cji.m5162f(cArr, "chars");
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            int c = cmi.m4715c(i, 0);
            int g = cpl.m3850g(charSequence);
            if (c > g) {
                return -1;
            }
            while (true) {
                char charAt = charSequence.charAt(c);
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z2 = false;
                        break;
                    } else if (cov.m4227a(cArr[i2], charAt, z)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    return c;
                }
                if (c == g) {
                    return -1;
                }
                c++;
            }
        } else {
            return ((String) charSequence).indexOf(bzb.m7151i(cArr), i);
        }
    }

    /* renamed from: b */
    public static /* synthetic */ int m3912b(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = cpl.m3850g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3913b(charSequence, cArr, i, z);
    }

    /* renamed from: b */
    public static final int m3913b(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        cji.m5162f(charSequence, "$this$lastIndexOfAny");
        cji.m5162f(cArr, "chars");
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            for (int d = cmi.m4701d(i, cpl.m3850g(charSequence)); d >= 0; d--) {
                char charAt = charSequence.charAt(d);
                int length = cArr.length;
                boolean z2 = false;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cov.m4227a(cArr[i2], charAt, z)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    return d;
                }
            }
            return -1;
        }
        return ((String) charSequence).lastIndexOf(bzb.m7151i(cArr), i);
    }

    /* renamed from: a */
    static /* synthetic */ int m3984a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        return m3985a(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    /* renamed from: a */
    private static final int m3985a(@NotNull CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        cme cmeVar = !z2 ? new cme(cmi.m4715c(i, 0), cmi.m4701d(i2, charSequence.length())) : cmi.m4792a(cmi.m4701d(i, cpl.m3850g(charSequence)), cmi.m4715c(i2, 0));
        if (!(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            int a = cmeVar.m4833a();
            int b = cmeVar.m4832b();
            int c = cmeVar.m4831c();
            if (c >= 0) {
                if (a > b) {
                    return -1;
                }
            } else if (a < b) {
                return -1;
            }
            while (!cpl.m3928b(charSequence2, 0, charSequence, a, charSequence2.length(), z)) {
                if (a == b) {
                    return -1;
                }
                a += c;
            }
            return a;
        }
        int a2 = cmeVar.m4833a();
        int b2 = cmeVar.m4832b();
        int c2 = cmeVar.m4831c();
        if (c2 >= 0) {
            if (a2 > b2) {
                return -1;
            }
        } else if (a2 < b2) {
            return -1;
        }
        while (!cpl.m4060a((String) charSequence2, 0, (String) charSequence, a2, charSequence2.length(), z)) {
            if (a2 == b2) {
                return -1;
            }
            a2 += c2;
        }
        return a2;
    }

    /* renamed from: b */
    public static final Tuples<Integer, String> m3920b(@NotNull CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object obj;
        Object obj2;
        if (z || collection.size() != 1) {
            cme cmeVar = !z2 ? new cme(cmi.m4715c(i, 0), charSequence.length()) : cmi.m4792a(cmi.m4701d(i, cpl.m3850g(charSequence)), 0);
            if (charSequence instanceof String) {
                int a = cmeVar.m4833a();
                int b = cmeVar.m4832b();
                int c = cmeVar.m4831c();
                if (c < 0 ? a >= b : a <= b) {
                    while (true) {
                        Iterator<T> it = collection.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it.next();
                            String str = (String) obj2;
                            if (cpl.m4060a(str, 0, (String) charSequence, a, str.length(), z)) {
                                break;
                            }
                        }
                        String str2 = (String) obj2;
                        if (str2 == null) {
                            if (a == b) {
                                break;
                            }
                            a += c;
                        } else {
                            return bxh.m8730a(Integer.valueOf(a), str2);
                        }
                    }
                }
            } else {
                int a2 = cmeVar.m4833a();
                int b2 = cmeVar.m4832b();
                int c2 = cmeVar.m4831c();
                if (c2 < 0 ? a2 >= b2 : a2 <= b2) {
                    while (true) {
                        Iterator<T> it2 = collection.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it2.next();
                            String str3 = (String) obj;
                            if (cpl.m3928b(str3, 0, charSequence, a2, str3.length(), z)) {
                                break;
                            }
                        }
                        String str4 = (String) obj;
                        if (str4 == null) {
                            if (a2 == b2) {
                                break;
                            }
                            a2 += c2;
                        } else {
                            return bxh.m8730a(Integer.valueOf(a2), str4);
                        }
                    }
                }
            }
            return null;
        }
        String str5 = (String) bzk.m6567k((Iterable<? extends Object>) collection);
        int a3 = !z2 ? cpl.m3977a(charSequence, str5, i, false, 4, (Object) null) : cpl.m3923b(charSequence, str5, i, false, 4, (Object) null);
        if (a3 < 0) {
            return null;
        }
        return bxh.m8730a(Integer.valueOf(a3), str5);
    }

    /* renamed from: a */
    public static /* synthetic */ Tuples m3974a(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3975a(charSequence, collection, i, z);
    }

    @dbs
    /* renamed from: a */
    public static final Tuples<Integer, String> m3975a(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        cji.m5162f(charSequence, "$this$findAnyOf");
        cji.m5162f(collection, "strings");
        return m3920b(charSequence, collection, i, z, false);
    }

    /* renamed from: b */
    public static /* synthetic */ Tuples m3921b(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = cpl.m3850g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3922b(charSequence, collection, i, z);
    }

    @dbs
    /* renamed from: b */
    public static final Tuples<Integer, String> m3922b(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        cji.m5162f(charSequence, "$this$findLastAnyOf");
        cji.m5162f(collection, "strings");
        return m3920b(charSequence, collection, i, z, true);
    }

    /* renamed from: c */
    public static /* synthetic */ int m3885c(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3886c(charSequence, collection, i, z);
    }

    /* renamed from: c */
    public static final int m3886c(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Integer first;
        cji.m5162f(charSequence, "$this$indexOfAny");
        cji.m5162f(collection, "strings");
        Tuples<Integer, String> b = m3920b(charSequence, collection, i, z, false);
        if (b == null || (first = b.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    /* renamed from: d */
    public static /* synthetic */ int m3865d(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = cpl.m3850g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3866d(charSequence, collection, i, z);
    }

    /* renamed from: d */
    public static final int m3866d(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Integer first;
        cji.m5162f(charSequence, "$this$lastIndexOfAny");
        cji.m5162f(collection, "strings");
        Tuples<Integer, String> b = m3920b(charSequence, collection, i, z, true);
        if (b == null || (first = b.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    /* renamed from: a */
    public static /* synthetic */ int m3994a(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3995a(charSequence, c, i, z);
    }

    /* renamed from: a */
    public static final int m3995a(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        cji.m5162f(charSequence, "$this$indexOf");
        return (z || !(charSequence instanceof String)) ? cpl.m3962a(charSequence, new char[]{c}, i, z) : ((String) charSequence).indexOf(c, i);
    }

    /* renamed from: a */
    public static /* synthetic */ int m3977a(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3978a(charSequence, str, i, z);
    }

    /* renamed from: a */
    public static final int m3978a(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        cji.m5162f(charSequence, "$this$indexOf");
        cji.m5162f(str, "string");
        if (z || !(charSequence instanceof String)) {
            return m3984a(charSequence, (CharSequence) str, i, charSequence.length(), z, false, 16, (Object) null);
        }
        return ((String) charSequence).indexOf(str, i);
    }

    /* renamed from: b */
    public static /* synthetic */ int m3935b(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = cpl.m3850g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3936b(charSequence, c, i, z);
    }

    /* renamed from: b */
    public static final int m3936b(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        cji.m5162f(charSequence, "$this$lastIndexOf");
        return (z || !(charSequence instanceof String)) ? cpl.m3913b(charSequence, new char[]{c}, i, z) : ((String) charSequence).lastIndexOf(c, i);
    }

    /* renamed from: b */
    public static /* synthetic */ int m3923b(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = cpl.m3850g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m3924b(charSequence, str, i, z);
    }

    /* renamed from: b */
    public static final int m3924b(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        cji.m5162f(charSequence, "$this$lastIndexOf");
        cji.m5162f(str, "string");
        if (z || !(charSequence instanceof String)) {
            return m3985a(charSequence, (CharSequence) str, i, 0, z, true);
        }
        return ((String) charSequence).lastIndexOf(str, i);
    }

    /* renamed from: e */
    public static /* synthetic */ boolean m3854e(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3855e(charSequence, charSequence2, z);
    }

    /* renamed from: e */
    public static final boolean m3855e(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        cji.m5162f(charSequence, "$this$contains");
        cji.m5162f(charSequence2, "other");
        return charSequence2 instanceof String ? cpl.m3977a(charSequence, (String) charSequence2, 0, z, 2, (Object) null) >= 0 : m3984a(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, (Object) null) >= 0;
    }

    /* renamed from: c */
    public static /* synthetic */ boolean m3890c(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m3891c(charSequence, c, z);
    }

    /* renamed from: c */
    public static final boolean m3891c(@NotNull CharSequence charSequence, char c, boolean z) {
        cji.m5162f(charSequence, "$this$contains");
        return cpl.m3994a(charSequence, c, 0, z, 2, (Object) null) >= 0;
    }

    @cey
    /* renamed from: b */
    private static final boolean m3916b(@NotNull CharSequence charSequence, cph cphVar) {
        cji.m5162f(charSequence, "$this$contains");
        return cphVar.containsMatchIn(charSequence);
    }

    /* renamed from: a */
    static /* synthetic */ Sequence m3960a(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return m3961a(charSequence, cArr, i, z, i2);
    }

    /* compiled from: Strings.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "Lkotlin/Pair;", "", "", "currentIndex", "invoke"})
    /* renamed from: z1.cpu$b */
    /* loaded from: classes3.dex */
    public static final class C5085b extends Lambda implements cho<CharSequence, Integer, Tuples<? extends Integer, ? extends Integer>> {
        final /* synthetic */ char[] $delimiters;
        final /* synthetic */ boolean $ignoreCase;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5085b(char[] cArr, boolean z) {
            super(2);
            this.$delimiters = cArr;
            this.$ignoreCase = z;
        }

        @Override // p110z1.cho
        public /* synthetic */ Tuples<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
            return invoke(charSequence, num.intValue());
        }

        @dbs
        public final Tuples<Integer, Integer> invoke(@NotNull CharSequence charSequence, int i) {
            cji.m5162f(charSequence, "$receiver");
            int a = cpl.m3962a(charSequence, this.$delimiters, i, this.$ignoreCase);
            if (a < 0) {
                return null;
            }
            return bxh.m8730a(Integer.valueOf(a), 1);
        }
    }

    /* renamed from: a */
    private static final Sequence<cme> m3961a(@NotNull CharSequence charSequence, char[] cArr, int i, boolean z, int i2) {
        if (i2 >= 0) {
            return new Strings(charSequence, i, i2, new C5085b(cArr, z));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    /* renamed from: a */
    static /* synthetic */ Sequence m3955a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return m3956a(charSequence, strArr, i, z, i2);
    }

    /* renamed from: a */
    private static final Sequence<cme> m3956a(@NotNull CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        if (i2 >= 0) {
            return new Strings(charSequence, i, i2, new C5086c(bzb.m8093d((Object[]) strArr), z));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    /* compiled from: Strings.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "Lkotlin/Pair;", "", "", "currentIndex", "invoke"})
    /* renamed from: z1.cpu$c */
    /* loaded from: classes3.dex */
    public static final class C5086c extends Lambda implements cho<CharSequence, Integer, Tuples<? extends Integer, ? extends Integer>> {
        final /* synthetic */ List $delimitersList;
        final /* synthetic */ boolean $ignoreCase;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5086c(List list, boolean z) {
            super(2);
            this.$delimitersList = list;
            this.$ignoreCase = z;
        }

        @Override // p110z1.cho
        public /* synthetic */ Tuples<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
            return invoke(charSequence, num.intValue());
        }

        @dbs
        public final Tuples<Integer, Integer> invoke(@NotNull CharSequence charSequence, int i) {
            cji.m5162f(charSequence, "$receiver");
            Tuples b = cpu.m3920b(charSequence, (Collection<String>) this.$delimitersList, i, this.$ignoreCase, false);
            if (b != null) {
                return bxh.m8730a(b.getFirst(), Integer.valueOf(((String) b.getSecond()).length()));
            }
            return null;
        }
    }

    /* compiled from: Strings.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "", "it", "Lkotlin/ranges/IntRange;", "invoke"})
    /* renamed from: z1.cpu$d */
    /* loaded from: classes3.dex */
    public static final class C5087d extends Lambda implements chd<cme, String> {
        final /* synthetic */ CharSequence $this_splitToSequence;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5087d(CharSequence charSequence) {
            super(1);
            this.$this_splitToSequence = charSequence;
        }

        @NotNull
        public final String invoke(@NotNull cme cmeVar) {
            cji.m5162f(cmeVar, "it");
            return cpl.m3917b(this.$this_splitToSequence, cmeVar);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ Sequence m3953a(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return cpl.m3954a(charSequence, strArr, z, i);
    }

    @NotNull
    /* renamed from: a */
    public static final Sequence<String> m3954a(@NotNull CharSequence charSequence, @NotNull String[] strArr, boolean z, int i) {
        cji.m5162f(charSequence, "$this$splitToSequence");
        cji.m5162f(strArr, "delimiters");
        return coe.m4302u(m3955a(charSequence, strArr, 0, z, i, 2, (Object) null), new C5087d(charSequence));
    }

    /* renamed from: b */
    public static /* synthetic */ List m3908b(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return cpl.m3909b(charSequence, strArr, z, i);
    }

    @NotNull
    /* renamed from: b */
    public static final List<String> m3909b(@NotNull CharSequence charSequence, @NotNull String[] strArr, boolean z, int i) {
        cji.m5162f(charSequence, "$this$split");
        cji.m5162f(strArr, "delimiters");
        if (strArr.length == 1) {
            boolean z2 = false;
            String str = strArr[0];
            if (str.length() == 0) {
                z2 = true;
            }
            if (!z2) {
                return m3976a(charSequence, str, z, i);
            }
        }
        Iterable<cme> H = coe.m4416H(m3955a(charSequence, strArr, 0, z, i, 2, (Object) null));
        ArrayList arrayList = new ArrayList(bzk.m6783a(H, 10));
        for (cme cmeVar : H) {
            arrayList.add(cpl.m3917b(charSequence, cmeVar));
        }
        return arrayList;
    }

    /* compiled from: Strings.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "", "it", "Lkotlin/ranges/IntRange;", "invoke"})
    /* renamed from: z1.cpu$e */
    /* loaded from: classes3.dex */
    public static final class C5088e extends Lambda implements chd<cme, String> {
        final /* synthetic */ CharSequence $this_splitToSequence;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5088e(CharSequence charSequence) {
            super(1);
            this.$this_splitToSequence = charSequence;
        }

        @NotNull
        public final String invoke(@NotNull cme cmeVar) {
            cji.m5162f(cmeVar, "it");
            return cpl.m3917b(this.$this_splitToSequence, cmeVar);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ Sequence m3957a(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return cpl.m3958a(charSequence, cArr, z, i);
    }

    @NotNull
    /* renamed from: a */
    public static final Sequence<String> m3958a(@NotNull CharSequence charSequence, @NotNull char[] cArr, boolean z, int i) {
        cji.m5162f(charSequence, "$this$splitToSequence");
        cji.m5162f(cArr, "delimiters");
        return coe.m4302u(m3960a(charSequence, cArr, 0, z, i, 2, (Object) null), new C5088e(charSequence));
    }

    /* renamed from: b */
    public static /* synthetic */ List m3910b(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return cpl.m3911b(charSequence, cArr, z, i);
    }

    @NotNull
    /* renamed from: b */
    public static final List<String> m3911b(@NotNull CharSequence charSequence, @NotNull char[] cArr, boolean z, int i) {
        cji.m5162f(charSequence, "$this$split");
        cji.m5162f(cArr, "delimiters");
        if (cArr.length == 1) {
            return m3976a(charSequence, String.valueOf(cArr[0]), z, i);
        }
        Iterable<cme> H = coe.m4416H(m3960a(charSequence, cArr, 0, z, i, 2, (Object) null));
        ArrayList arrayList = new ArrayList(bzk.m6783a(H, 10));
        for (cme cmeVar : H) {
            arrayList.add(cpl.m3917b(charSequence, cmeVar));
        }
        return arrayList;
    }

    /* renamed from: a */
    private static final List<String> m3976a(@NotNull CharSequence charSequence, String str, boolean z, int i) {
        int i2 = 0;
        if (i >= 0) {
            int a = cpl.m3978a(charSequence, str, 0, z);
            if (a == -1 || i == 1) {
                return bzk.m6822a(charSequence.toString());
            }
            boolean z2 = i > 0;
            int i3 = 10;
            if (z2) {
                i3 = cmi.m4701d(i, 10);
            }
            ArrayList arrayList = new ArrayList(i3);
            do {
                arrayList.add(charSequence.subSequence(i2, a).toString());
                i2 = str.length() + a;
                if (z2 && arrayList.size() == i - 1) {
                    break;
                }
                a = cpl.m3978a(charSequence, str, i2, z);
            } while (a != -1);
            arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
            return arrayList;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    @cey
    /* renamed from: a */
    private static final List<String> m3967a(@NotNull CharSequence charSequence, cph cphVar, int i) {
        return cphVar.split(charSequence, i);
    }

    @NotNull
    /* renamed from: h */
    public static final Sequence<String> m3849h(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$lineSequence");
        return cpl.m3953a(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, (Object) null);
    }

    @NotNull
    /* renamed from: i */
    public static final List<String> m3848i(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$lines");
        return coe.m4311q(cpl.m3849h(charSequence));
    }

    @NotNull
    /* renamed from: a */
    public static final CharSequence m3963a(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        cji.m5162f(charSequence, "$this$trim");
        cji.m5162f(cArr, "chars");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean b = bzb.m7570b(cArr, charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!b) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!b) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3938a(@NotNull String str, @NotNull char... cArr) {
        cji.m5162f(str, "$this$trim");
        cji.m5162f(cArr, "chars");
        String str2 = str;
        int length = str2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean b = bzb.m7570b(cArr, str2.charAt(!z ? i : length));
            if (!z) {
                if (!b) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!b) {
                break;
            } else {
                length--;
            }
        }
        return str2.subSequence(i, length + 1).toString();
    }

    @NotNull
    /* renamed from: b */
    public static final CharSequence m3914b(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        cji.m5162f(charSequence, "$this$trimStart");
        cji.m5162f(cArr, "chars");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!bzb.m7570b(cArr, charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    /* renamed from: b */
    public static final String m3893b(@NotNull String str, @NotNull char... cArr) {
        String str2;
        cji.m5162f(str, "$this$trimStart");
        cji.m5162f(cArr, "chars");
        String str3 = str;
        int length = str3.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (!bzb.m7570b(cArr, str3.charAt(i))) {
                str2 = str3.subSequence(i, str3.length());
                break;
            } else {
                i++;
            }
        }
        return str2.toString();
    }

    @NotNull
    /* renamed from: c */
    public static final CharSequence m3882c(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        cji.m5162f(charSequence, "$this$trimEnd");
        cji.m5162f(cArr, "chars");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (bzb.m7570b(cArr, charSequence.charAt(length)));
        return charSequence.subSequence(0, length + 1);
    }

    @NotNull
    /* renamed from: c */
    public static final String m3870c(@NotNull String str, @NotNull char... cArr) {
        String str2;
        cji.m5162f(str, "$this$trimEnd");
        cji.m5162f(cArr, "chars");
        String str3 = str;
        int length = str3.length();
        while (true) {
            length--;
            if (length >= 0) {
                if (!bzb.m7570b(cArr, str3.charAt(length))) {
                    str2 = str3.subSequence(0, length + 1);
                    break;
                }
            } else {
                break;
            }
        }
        return str2.toString();
    }

    @NotNull
    /* renamed from: b */
    public static final CharSequence m3937b(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$trim");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean a = cov.m4248a(charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!a) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!a) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    /* renamed from: c */
    public static final CharSequence m3892c(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$trimStart");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!cov.m4248a(charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    /* renamed from: d */
    public static final CharSequence m3869d(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$trimEnd");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (cov.m4248a(charSequence.charAt(length)));
        return charSequence.subSequence(0, length + 1);
    }

    /* renamed from: a */
    static /* synthetic */ List m3966a(CharSequence charSequence, cph cphVar, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return cphVar.split(charSequence, i);
    }
}
