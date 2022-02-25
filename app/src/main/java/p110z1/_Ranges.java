package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.NoSuchElementException;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Progressions;
import p110z1.cmc;
import p110z1.cmf;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000n\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a'\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007\u001a\u0012\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b\u001a\u0012\u0010\u0000\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0012\u0010\u0000\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n\u001a'\u0010\u000b\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u0012\u0010\u000b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u0012\u0010\u000b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0012\u0010\u000b\u001a\u00020\n*\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a3\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00012\b\u0010\f\u001a\u0004\u0018\u0001H\u0001¢\u0006\u0002\u0010\u000e\u001a/\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a\u001a\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u001a\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u001a\u0010\r\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u001a\u0010\r\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0018\u0010\r\u001a\u00020\b*\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0012\u001a\u001a\u0010\r\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0018\u0010\r\u001a\u00020\t*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u001a\u001a\u0010\r\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0087\n¢\u0006\u0002\u0010\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b \u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020!2\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0087\n¢\u0006\u0002\u0010\"\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020#2\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0087\n¢\u0006\u0002\u0010$\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020)*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\r\u0010*\u001a\u00020\u0018*\u00020\u0016H\u0087\b\u001a\u0014\u0010*\u001a\u00020\u0018*\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0007\u001a\r\u0010*\u001a\u00020\b*\u00020!H\u0087\b\u001a\u0014\u0010*\u001a\u00020\b*\u00020!2\u0006\u0010*\u001a\u00020+H\u0007\u001a\r\u0010*\u001a\u00020\t*\u00020#H\u0087\b\u001a\u0014\u0010*\u001a\u00020\t*\u00020#2\u0006\u0010*\u001a\u00020+H\u0007\u001a\n\u0010,\u001a\u00020)*\u00020)\u001a\n\u0010,\u001a\u00020&*\u00020&\u001a\n\u0010,\u001a\u00020(*\u00020(\u001a\u0015\u0010-\u001a\u00020)*\u00020)2\u0006\u0010-\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010-\u001a\u00020&*\u00020&2\u0006\u0010-\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010-\u001a\u00020(*\u00020(2\u0006\u0010-\u001a\u00020\tH\u0086\u0004\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0000¢\u0006\u0002\u0010/\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H\u0000¢\u0006\u0002\u00100\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\bH\u0000¢\u0006\u0002\u00101\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\tH\u0000¢\u0006\u0002\u00102\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\nH\u0000¢\u0006\u0002\u00103\u001a\u0013\u00104\u001a\u0004\u0018\u00010\b*\u00020\u0006H\u0000¢\u0006\u0002\u00105\u001a\u0013\u00104\u001a\u0004\u0018\u00010\b*\u00020\u0007H\u0000¢\u0006\u0002\u00106\u001a\u0013\u00104\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000¢\u0006\u0002\u00107\u001a\u0013\u00108\u001a\u0004\u0018\u00010\t*\u00020\u0006H\u0000¢\u0006\u0002\u00109\u001a\u0013\u00108\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0000¢\u0006\u0002\u0010:\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\u0006H\u0000¢\u0006\u0002\u0010<\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\u0007H\u0000¢\u0006\u0002\u0010=\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\bH\u0000¢\u0006\u0002\u0010>\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\tH\u0000¢\u0006\u0002\u0010?\u001a\u0015\u0010@\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020\u0016*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0086\u0004¨\u0006A"}, m8860e = {"coerceAtLeast", TessBaseAPI.f9204e, "", "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "contains", "", "Lkotlin/ranges/CharRange;", "element", "", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", SizeSelector.SIZE_KEY, "byteRangeContains", "doubleRangeContains", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "Lkotlin/ranges/IntRange;", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "Lkotlin/ranges/LongRange;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "downTo", "Lkotlin/ranges/IntProgression;", "to", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/CharProgression;", "random", "Lkotlin/random/Random;", "reversed", "step", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "kotlin-stdlib"}, m8859f = "kotlin/ranges/RangesKt", m8857h = 1)
/* renamed from: z1.cml */
/* loaded from: classes3.dex */
public class _Ranges extends cmk {
    /* renamed from: b */
    public static final double m4744b(double d, double d2) {
        return d < d2 ? d2 : d;
    }

    /* renamed from: b */
    public static final float m4742b(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    /* renamed from: c */
    public static final byte m4720c(byte b, byte b2) {
        return b < b2 ? b2 : b;
    }

    /* renamed from: c */
    public static final double m4718c(double d, double d2) {
        return d > d2 ? d2 : d;
    }

    /* renamed from: c */
    public static final float m4716c(float f, float f2) {
        return f > f2 ? f2 : f;
    }

    /* renamed from: c */
    public static final int m4715c(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    /* renamed from: c */
    public static final long m4713c(long j, long j2) {
        return j < j2 ? j2 : j;
    }

    /* renamed from: c */
    public static final short m4705c(short s, short s2) {
        return s < s2 ? s2 : s;
    }

    /* renamed from: d */
    public static final byte m4704d(byte b, byte b2) {
        return b > b2 ? b2 : b;
    }

    /* renamed from: d */
    public static final int m4701d(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    /* renamed from: d */
    public static final long m4700d(long j, long j2) {
        return j > j2 ? j2 : j;
    }

    /* renamed from: d */
    public static final short m4693d(short s, short s2) {
        return s > s2 ? s2 : s;
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final int m4764a(@NotNull cme cmeVar) {
        return cmi.m4762a(cmeVar, (Random) Random.f20808b);
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final long m4759a(@NotNull cmh cmhVar) {
        return cmi.m4757a(cmhVar, (Random) Random.f20808b);
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final char m4775a(@NotNull Ranges clwVar) {
        return cmi.m4773a(clwVar, (Random) Random.f20808b);
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final int m4762a(@NotNull cme cmeVar, @NotNull Random clqVar) {
        cji.m5162f(cmeVar, "$this$random");
        cji.m5162f(clqVar, "random");
        try {
            return clr.m4881a(clqVar, cmeVar);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final long m4757a(@NotNull cmh cmhVar, @NotNull Random clqVar) {
        cji.m5162f(cmhVar, "$this$random");
        cji.m5162f(clqVar, "random");
        try {
            return clr.m4880a(clqVar, cmhVar);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final char m4773a(@NotNull Ranges clwVar, @NotNull Random clqVar) {
        cji.m5162f(clwVar, "$this$random");
        cji.m5162f(clqVar, "random");
        try {
            return (char) clqVar.mo4898a((int) clwVar.m4861a(), clwVar.m4860b() + 1);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final boolean m4763a(@NotNull cme cmeVar, Integer num) {
        cji.m5162f(cmeVar, "$this$contains");
        return num != null && cmeVar.m4826a(num.intValue());
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final boolean m4758a(@NotNull cmh cmhVar, Long l) {
        cji.m5162f(cmhVar, "$this$contains");
        return l != null && cmhVar.m4814a(l.longValue());
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final boolean m4774a(@NotNull Ranges clwVar, Character ch) {
        cji.m5162f(clwVar, "$this$contains");
        return ch != null && clwVar.m4855a(ch.charValue());
    }

    @cgo(m5270a = "intRangeContains")
    /* renamed from: a */
    public static final boolean m4772a(@NotNull Range<Integer> cmaVar, byte b) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Integer.valueOf(b));
    }

    @cgo(m5270a = "longRangeContains")
    /* renamed from: b */
    public static final boolean m4730b(@NotNull Range<Long> cmaVar, byte b) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Long.valueOf(b));
    }

    @cgo(m5270a = "shortRangeContains")
    /* renamed from: c */
    public static final boolean m4711c(@NotNull Range<Short> cmaVar, byte b) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Short.valueOf(b));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "doubleRangeContains")
    /* renamed from: d */
    public static final boolean m4699d(@NotNull Range<Double> cmaVar, byte b) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Double.valueOf(b));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "floatRangeContains")
    /* renamed from: e */
    public static final boolean m4692e(@NotNull Range<Float> cmaVar, byte b) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Float.valueOf(b));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "intRangeContains")
    /* renamed from: a */
    public static final boolean m4771a(@NotNull Range<Integer> cmaVar, double d) {
        cji.m5162f(cmaVar, "$this$contains");
        Integer b = cmi.m4745b(d);
        if (b != null) {
            return cmaVar.mo4668a(b);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "longRangeContains")
    /* renamed from: b */
    public static final boolean m4729b(@NotNull Range<Long> cmaVar, double d) {
        cji.m5162f(cmaVar, "$this$contains");
        Long c = cmi.m4719c(d);
        if (c != null) {
            return cmaVar.mo4668a(c);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "byteRangeContains")
    /* renamed from: c */
    public static final boolean m4710c(@NotNull Range<Byte> cmaVar, double d) {
        cji.m5162f(cmaVar, "$this$contains");
        Byte a = cmi.m4798a(d);
        if (a != null) {
            return cmaVar.mo4668a(a);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "shortRangeContains")
    /* renamed from: d */
    public static final boolean m4698d(@NotNull Range<Short> cmaVar, double d) {
        cji.m5162f(cmaVar, "$this$contains");
        Short d2 = cmi.m4703d(d);
        if (d2 != null) {
            return cmaVar.mo4668a(d2);
        }
        return false;
    }

    @cgo(m5270a = "floatRangeContains")
    /* renamed from: e */
    public static final boolean m4691e(@NotNull Range<Float> cmaVar, double d) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Float.valueOf((float) d));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "intRangeContains")
    /* renamed from: a */
    public static final boolean m4770a(@NotNull Range<Integer> cmaVar, float f) {
        cji.m5162f(cmaVar, "$this$contains");
        Integer b = cmi.m4743b(f);
        if (b != null) {
            return cmaVar.mo4668a(b);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "longRangeContains")
    /* renamed from: b */
    public static final boolean m4728b(@NotNull Range<Long> cmaVar, float f) {
        cji.m5162f(cmaVar, "$this$contains");
        Long c = cmi.m4717c(f);
        if (c != null) {
            return cmaVar.mo4668a(c);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "byteRangeContains")
    /* renamed from: c */
    public static final boolean m4709c(@NotNull Range<Byte> cmaVar, float f) {
        cji.m5162f(cmaVar, "$this$contains");
        Byte a = cmi.m4796a(f);
        if (a != null) {
            return cmaVar.mo4668a(a);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "shortRangeContains")
    /* renamed from: d */
    public static final boolean m4697d(@NotNull Range<Short> cmaVar, float f) {
        cji.m5162f(cmaVar, "$this$contains");
        Short d = cmi.m4702d(f);
        if (d != null) {
            return cmaVar.mo4668a(d);
        }
        return false;
    }

    @cgo(m5270a = "doubleRangeContains")
    /* renamed from: e */
    public static final boolean m4690e(@NotNull Range<Double> cmaVar, float f) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Double.valueOf(f));
    }

    @cgo(m5270a = "longRangeContains")
    /* renamed from: a */
    public static final boolean m4769a(@NotNull Range<Long> cmaVar, int i) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Long.valueOf(i));
    }

    @cgo(m5270a = "byteRangeContains")
    /* renamed from: b */
    public static final boolean m4727b(@NotNull Range<Byte> cmaVar, int i) {
        cji.m5162f(cmaVar, "$this$contains");
        Byte a = cmi.m4794a(i);
        if (a != null) {
            return cmaVar.mo4668a(a);
        }
        return false;
    }

    @cgo(m5270a = "shortRangeContains")
    /* renamed from: c */
    public static final boolean m4708c(@NotNull Range<Short> cmaVar, int i) {
        cji.m5162f(cmaVar, "$this$contains");
        Short b = cmi.m4741b(i);
        if (b != null) {
            return cmaVar.mo4668a(b);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "doubleRangeContains")
    /* renamed from: d */
    public static final boolean m4696d(@NotNull Range<Double> cmaVar, int i) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Double.valueOf(i));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "floatRangeContains")
    /* renamed from: e */
    public static final boolean m4689e(@NotNull Range<Float> cmaVar, int i) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Float.valueOf(i));
    }

    @cgo(m5270a = "intRangeContains")
    /* renamed from: a */
    public static final boolean m4768a(@NotNull Range<Integer> cmaVar, long j) {
        cji.m5162f(cmaVar, "$this$contains");
        Integer b = cmi.m4736b(j);
        if (b != null) {
            return cmaVar.mo4668a(b);
        }
        return false;
    }

    @cgo(m5270a = "byteRangeContains")
    /* renamed from: b */
    public static final boolean m4726b(@NotNull Range<Byte> cmaVar, long j) {
        cji.m5162f(cmaVar, "$this$contains");
        Byte a = cmi.m4787a(j);
        if (a != null) {
            return cmaVar.mo4668a(a);
        }
        return false;
    }

    @cgo(m5270a = "shortRangeContains")
    /* renamed from: c */
    public static final boolean m4707c(@NotNull Range<Short> cmaVar, long j) {
        cji.m5162f(cmaVar, "$this$contains");
        Short c = cmi.m4714c(j);
        if (c != null) {
            return cmaVar.mo4668a(c);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "doubleRangeContains")
    /* renamed from: d */
    public static final boolean m4695d(@NotNull Range<Double> cmaVar, long j) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Double.valueOf(j));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "floatRangeContains")
    /* renamed from: e */
    public static final boolean m4688e(@NotNull Range<Float> cmaVar, long j) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Float.valueOf((float) j));
    }

    @cgo(m5270a = "intRangeContains")
    /* renamed from: a */
    public static final boolean m4767a(@NotNull Range<Integer> cmaVar, short s) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Integer.valueOf(s));
    }

    @cgo(m5270a = "longRangeContains")
    /* renamed from: b */
    public static final boolean m4725b(@NotNull Range<Long> cmaVar, short s) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Long.valueOf(s));
    }

    @cgo(m5270a = "byteRangeContains")
    /* renamed from: c */
    public static final boolean m4706c(@NotNull Range<Byte> cmaVar, short s) {
        cji.m5162f(cmaVar, "$this$contains");
        Byte a = cmi.m4756a(s);
        if (a != null) {
            return cmaVar.mo4668a(a);
        }
        return false;
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "doubleRangeContains")
    /* renamed from: d */
    public static final boolean m4694d(@NotNull Range<Double> cmaVar, short s) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Double.valueOf(s));
    }

    @Annotations(m8902a = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @cgo(m5270a = "floatRangeContains")
    /* renamed from: e */
    public static final boolean m4687e(@NotNull Range<Float> cmaVar, short s) {
        cji.m5162f(cmaVar, "$this$contains");
        return cmaVar.mo4668a(Float.valueOf(s));
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4793a(int i, byte b) {
        return cmc.f20836a.m4829a(i, b, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4786a(long j, byte b) {
        return cmf.f20846a.m4817a(j, b, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4804a(byte b, byte b2) {
        return cmc.f20836a.m4829a(b, b2, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4755a(short s, byte b) {
        return cmc.f20836a.m4829a(s, b, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final Progressions m4799a(char c, char c2) {
        return Progressions.f20817a.m4857a(c, c2, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4792a(int i, int i2) {
        return cmc.f20836a.m4829a(i, i2, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4785a(long j, int i) {
        return cmf.f20846a.m4817a(j, i, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4802a(byte b, int i) {
        return cmc.f20836a.m4829a(b, i, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4754a(short s, int i) {
        return cmc.f20836a.m4829a(s, i, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4790a(int i, long j) {
        return cmf.f20846a.m4817a(i, j, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4784a(long j, long j2) {
        return cmf.f20846a.m4817a(j, j2, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4801a(byte b, long j) {
        return cmf.f20846a.m4817a(b, j, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4753a(short s, long j) {
        return cmf.f20846a.m4817a(s, j, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4788a(int i, short s) {
        return cmc.f20836a.m4829a(i, s, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4781a(long j, short s) {
        return cmf.f20846a.m4817a(j, s, -1L);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4800a(byte b, short s) {
        return cmc.f20836a.m4829a(b, s, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4752a(short s, short s2) {
        return cmc.f20836a.m4829a(s, s2, -1);
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4766a(@NotNull cmc cmcVar) {
        cji.m5162f(cmcVar, "$this$reversed");
        return cmc.f20836a.m4829a(cmcVar.m4832b(), cmcVar.m4833a(), -cmcVar.m4831c());
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4761a(@NotNull cmf cmfVar) {
        cji.m5162f(cmfVar, "$this$reversed");
        return cmf.f20846a.m4817a(cmfVar.m4820b(), cmfVar.m4821a(), -cmfVar.m4819c());
    }

    @NotNull
    /* renamed from: a */
    public static final Progressions m4777a(@NotNull Progressions cluVar) {
        cji.m5162f(cluVar, "$this$reversed");
        return Progressions.f20817a.m4857a(cluVar.m4860b(), cluVar.m4861a(), -cluVar.m4859c());
    }

    @NotNull
    /* renamed from: a */
    public static final cmc m4765a(@NotNull cmc cmcVar, int i) {
        cji.m5162f(cmcVar, "$this$step");
        cmi.m4805a(i > 0, Integer.valueOf(i));
        cmc.C4987a aVar = cmc.f20836a;
        int a = cmcVar.m4833a();
        int b = cmcVar.m4832b();
        if (cmcVar.m4831c() <= 0) {
            i = -i;
        }
        return aVar.m4829a(a, b, i);
    }

    @NotNull
    /* renamed from: a */
    public static final cmf m4760a(@NotNull cmf cmfVar, long j) {
        cji.m5162f(cmfVar, "$this$step");
        cmi.m4805a(j > 0, Long.valueOf(j));
        cmf.C4989a aVar = cmf.f20846a;
        long a = cmfVar.m4821a();
        long b = cmfVar.m4820b();
        if (cmfVar.m4819c() <= 0) {
            j = -j;
        }
        return aVar.m4817a(a, b, j);
    }

    @NotNull
    /* renamed from: a */
    public static final Progressions m4776a(@NotNull Progressions cluVar, int i) {
        cji.m5162f(cluVar, "$this$step");
        cmi.m4805a(i > 0, Integer.valueOf(i));
        Progressions.C4982a aVar = Progressions.f20817a;
        char a = cluVar.m4861a();
        char b = cluVar.m4860b();
        if (cluVar.m4859c() <= 0) {
            i = -i;
        }
        return aVar.m4857a(a, b, i);
    }

    @dbs
    /* renamed from: a */
    public static final Byte m4794a(int i) {
        if (-128 <= i && 127 >= i) {
            return Byte.valueOf((byte) i);
        }
        return null;
    }

    @dbs
    /* renamed from: a */
    public static final Byte m4787a(long j) {
        long j2 = 127;
        if (-128 <= j && j2 >= j) {
            return Byte.valueOf((byte) j);
        }
        return null;
    }

    @dbs
    /* renamed from: a */
    public static final Byte m4756a(short s) {
        short s2 = (short) 127;
        if (((short) (-128)) <= s && s2 >= s) {
            return Byte.valueOf((byte) s);
        }
        return null;
    }

    @dbs
    /* renamed from: a */
    public static final Byte m4798a(double d) {
        double d2 = 127;
        if (d < -128 || d > d2) {
            return null;
        }
        return Byte.valueOf((byte) d);
    }

    @dbs
    /* renamed from: a */
    public static final Byte m4796a(float f) {
        float f2 = 127;
        if (f < -128 || f > f2) {
            return null;
        }
        return Byte.valueOf((byte) f);
    }

    @dbs
    /* renamed from: b */
    public static final Integer m4736b(long j) {
        long j2 = Integer.MAX_VALUE;
        if (Integer.MIN_VALUE <= j && j2 >= j) {
            return Integer.valueOf((int) j);
        }
        return null;
    }

    @dbs
    /* renamed from: b */
    public static final Integer m4745b(double d) {
        double d2 = Integer.MAX_VALUE;
        if (d < Integer.MIN_VALUE || d > d2) {
            return null;
        }
        return Integer.valueOf((int) d);
    }

    @dbs
    /* renamed from: b */
    public static final Integer m4743b(float f) {
        float f2 = Integer.MAX_VALUE;
        if (f < Integer.MIN_VALUE || f > f2) {
            return null;
        }
        return Integer.valueOf((int) f);
    }

    @dbs
    /* renamed from: c */
    public static final Long m4719c(double d) {
        double d2 = (double) cjm.f20759b;
        if (d < Long.MIN_VALUE || d > d2) {
            return null;
        }
        return Long.valueOf((long) d);
    }

    @dbs
    /* renamed from: c */
    public static final Long m4717c(float f) {
        float f2 = (float) cjm.f20759b;
        if (f < ((float) Long.MIN_VALUE) || f > f2) {
            return null;
        }
        return Long.valueOf(f);
    }

    @dbs
    /* renamed from: b */
    public static final Short m4741b(int i) {
        if (-32768 <= i && 32767 >= i) {
            return Short.valueOf((short) i);
        }
        return null;
    }

    @dbs
    /* renamed from: c */
    public static final Short m4714c(long j) {
        long j2 = 32767;
        if (-32768 <= j && j2 >= j) {
            return Short.valueOf((short) j);
        }
        return null;
    }

    @dbs
    /* renamed from: d */
    public static final Short m4703d(double d) {
        double d2 = 32767;
        if (d < -32768 || d > d2) {
            return null;
        }
        return Short.valueOf((short) d);
    }

    @dbs
    /* renamed from: d */
    public static final Short m4702d(float f) {
        float f2 = 32767;
        if (f < -32768 || f > f2) {
            return null;
        }
        return Short.valueOf((short) f);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4740b(int i, byte b) {
        return new cme(i, b - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4735b(long j, byte b) {
        return new cmh(j, b - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4750b(byte b, byte b2) {
        return new cme(b, b2 - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4724b(short s, byte b) {
        return new cme(s, b - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final Ranges m4746b(char c, char c2) {
        if (c2 <= 0) {
            return Ranges.f20825b.m4851a();
        }
        return new Ranges(c, (char) (c2 - 1));
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4739b(int i, int i2) {
        if (i2 <= Integer.MIN_VALUE) {
            return cme.f20844b.m4822a();
        }
        return new cme(i, i2 - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4734b(long j, int i) {
        return new cmh(j, i - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4749b(byte b, int i) {
        if (i <= Integer.MIN_VALUE) {
            return cme.f20844b.m4822a();
        }
        return new cme(b, i - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4723b(short s, int i) {
        if (i <= Integer.MIN_VALUE) {
            return cme.f20844b.m4822a();
        }
        return new cme(s, i - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4738b(int i, long j) {
        if (j <= Long.MIN_VALUE) {
            return cmh.f20854b.m4810a();
        }
        return new cmh(i, j - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4733b(long j, long j2) {
        if (j2 <= Long.MIN_VALUE) {
            return cmh.f20854b.m4810a();
        }
        return new cmh(j, j2 - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4748b(byte b, long j) {
        if (j <= Long.MIN_VALUE) {
            return cmh.f20854b.m4810a();
        }
        return new cmh(b, j - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4722b(short s, long j) {
        if (j <= Long.MIN_VALUE) {
            return cmh.f20854b.m4810a();
        }
        return new cmh(s, j - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4737b(int i, short s) {
        return new cme(i, s - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cmh m4732b(long j, short s) {
        return new cmh(j, s - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4747b(byte b, short s) {
        return new cme(b, s - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final cme m4721b(short s, short s2) {
        return new cme(s, s2 - 1);
    }

    @NotNull
    /* renamed from: b */
    public static final <T extends Comparable<? super T>> T m4731b(@NotNull T t, @NotNull T t2) {
        cji.m5162f(t, "$this$coerceAtLeast");
        cji.m5162f(t2, "minimumValue");
        return t.compareTo(t2) < 0 ? t2 : t;
    }

    @NotNull
    /* renamed from: c */
    public static final <T extends Comparable<? super T>> T m4712c(@NotNull T t, @NotNull T t2) {
        cji.m5162f(t, "$this$coerceAtMost");
        cji.m5162f(t2, "maximumValue");
        return t.compareTo(t2) > 0 ? t2 : t;
    }

    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> T m4780a(@NotNull T t, @dbs T t2, @dbs T t3) {
        cji.m5162f(t, "$this$coerceIn");
        if (t2 == null || t3 == null) {
            if (t2 != null && t.compareTo(t2) < 0) {
                return t2;
            }
            if (t3 != null && t.compareTo(t3) > 0) {
                return t3;
            }
        } else if (t2.compareTo(t3) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + t3 + " is less than minimum " + t2 + FilenameUtils.EXTENSION_SEPARATOR);
        } else if (t.compareTo(t2) < 0) {
            return t2;
        } else {
            if (t.compareTo(t3) > 0) {
                return t3;
            }
        }
        return t;
    }

    /* renamed from: a */
    public static final byte m4803a(byte b, byte b2, byte b3) {
        if (b2 <= b3) {
            return b < b2 ? b2 : b > b3 ? b3 : b;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ((int) b3) + " is less than minimum " + ((int) b2) + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final short m4751a(short s, short s2, short s3) {
        if (s2 <= s3) {
            return s < s2 ? s2 : s > s3 ? s3 : s;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ((int) s3) + " is less than minimum " + ((int) s2) + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final int m4791a(int i, int i2, int i3) {
        if (i2 <= i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final long m4783a(long j, long j2, long j3) {
        if (j2 <= j3) {
            return j < j2 ? j2 : j > j3 ? j3 : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j3 + " is less than minimum " + j2 + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final float m4795a(float f, float f2, float f3) {
        if (f2 <= f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f3 + " is less than minimum " + f2 + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final double m4797a(double d, double d2, double d3) {
        if (d2 <= d3) {
            return d < d2 ? d2 : d > d3 ? d3 : d;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d3 + " is less than minimum " + d2 + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> T m4779a(@NotNull T t, @NotNull clz<T> clzVar) {
        cji.m5162f(t, "$this$coerceIn");
        cji.m5162f(clzVar, "range");
        if (!clzVar.mo4667e()) {
            return (!clzVar.mo4842a(t, clzVar.mo4665g()) || clzVar.mo4842a(clzVar.mo4665g(), t)) ? (!clzVar.mo4842a(clzVar.mo4663i(), t) || clzVar.mo4842a(t, clzVar.mo4663i())) ? t : clzVar.mo4663i() : clzVar.mo4665g();
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + clzVar + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> T m4778a(@NotNull T t, @NotNull Range<T> cmaVar) {
        cji.m5162f(t, "$this$coerceIn");
        cji.m5162f(cmaVar, "range");
        if (cmaVar instanceof clz) {
            return (T) cmi.m4779a((Comparable) t, (clz) cmaVar);
        }
        if (!cmaVar.mo4667e()) {
            return t.compareTo(cmaVar.mo4665g()) < 0 ? cmaVar.mo4665g() : t.compareTo(cmaVar.mo4663i()) > 0 ? cmaVar.mo4663i() : t;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + cmaVar + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final int m4789a(int i, @NotNull Range<Integer> cmaVar) {
        cji.m5162f(cmaVar, "range");
        if (cmaVar instanceof clz) {
            return ((Number) cmi.m4779a(Integer.valueOf(i), (clz<Integer>) cmaVar)).intValue();
        }
        if (!cmaVar.mo4667e()) {
            return i < cmaVar.mo4665g().intValue() ? cmaVar.mo4665g().intValue() : i > cmaVar.mo4663i().intValue() ? cmaVar.mo4663i().intValue() : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + cmaVar + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: a */
    public static final long m4782a(long j, @NotNull Range<Long> cmaVar) {
        cji.m5162f(cmaVar, "range");
        if (cmaVar instanceof clz) {
            return ((Number) cmi.m4779a(Long.valueOf(j), (clz<Long>) cmaVar)).longValue();
        }
        if (!cmaVar.mo4667e()) {
            return j < cmaVar.mo4665g().longValue() ? cmaVar.mo4665g().longValue() : j > cmaVar.mo4663i().longValue() ? cmaVar.mo4663i().longValue() : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + cmaVar + FilenameUtils.EXTENSION_SEPARATOR);
    }
}
