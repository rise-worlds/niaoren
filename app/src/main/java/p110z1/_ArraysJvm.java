package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0006\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\b\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\n\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\f\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\r0\u0001*\u00020\u000e\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u00020\u0010\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u00020\u0012\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u00020\u0014\u001aU\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001c\u001a9\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001d\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a2\u0010\u001e\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\f¢\u0006\u0004\b \u0010!\u001a\"\u0010\"\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0004\b#\u0010$\u001a\"\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0004\b'\u0010(\u001a0\u0010)\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\f¢\u0006\u0002\u0010!\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0087\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0087\f\u001a \u0010*\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010$\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0006H\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\bH\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\nH\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\fH\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u000eH\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0010H\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0012H\u0087\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0014H\u0087\b\u001a \u0010+\u001a\u00020&\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010(\u001a\r\u0010+\u001a\u00020&*\u00020\u0006H\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\bH\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\nH\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\fH\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\u000eH\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0010H\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0012H\u0087\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0014H\u0087\b\u001aQ\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00101\u001a2\u0010,\u001a\u00020\u0006*\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\b*\u00020\b2\u0006\u0010-\u001a\u00020\b2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\n*\u00020\n2\u0006\u0010-\u001a\u00020\n2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\f*\u00020\f2\u0006\u0010-\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0010*\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0012*\u00020\u00122\u0006\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0014*\u00020\u00142\u0006\u0010-\u001a\u00020\u00142\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a$\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u00103\u001a.\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u00104\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\u00105\u001a\r\u00102\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u00102\u001a\u00020\u0006*\u00020\u00062\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\b*\u00020\bH\u0087\b\u001a\u0015\u00102\u001a\u00020\b*\u00020\b2\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\n*\u00020\nH\u0087\b\u001a\u0015\u00102\u001a\u00020\n*\u00020\n2\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\f*\u00020\fH\u0087\b\u001a\u0015\u00102\u001a\u00020\f*\u00020\f2\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\u000e*\u00020\u000eH\u0087\b\u001a\u0015\u00102\u001a\u00020\u000e*\u00020\u000e2\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u001a\u0015\u00102\u001a\u00020\u0010*\u00020\u00102\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\u0012*\u00020\u0012H\u0087\b\u001a\u0015\u00102\u001a\u00020\u0012*\u00020\u00122\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a\r\u00102\u001a\u00020\u0014*\u00020\u0014H\u0087\b\u001a\u0015\u00102\u001a\u00020\u0014*\u00020\u00142\u0006\u00104\u001a\u00020\u000fH\u0087\b\u001a6\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b7\u00108\u001a\"\u00106\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b7\u001a5\u00109\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0004\b6\u00108\u001a!\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a(\u0010:\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010;\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\u0010<\u001a\u0015\u0010:\u001a\u00020\u0005*\u00020\u00062\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\u0007*\u00020\b2\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\t*\u00020\n2\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\u000b*\u00020\f2\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\r*\u00020\u000e2\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\u000f*\u00020\u00102\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\u0011*\u00020\u00122\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010:\u001a\u00020\u0013*\u00020\u00142\u0006\u0010;\u001a\u00020\u000fH\u0087\b\u001a7\u0010=\u001a\u00020>\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010?\u001a&\u0010=\u001a\u00020>*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010=\u001a\u00020>*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a-\u0010@\u001a\b\u0012\u0004\u0012\u0002HA0\u0001\"\u0004\b\u0000\u0010A*\u0006\u0012\u0002\b\u00030\u00032\f\u0010B\u001a\b\u0012\u0004\u0012\u0002HA0C¢\u0006\u0002\u0010D\u001aA\u0010E\u001a\u0002HF\"\u0010\b\u0000\u0010F*\n\u0012\u0006\b\u0000\u0012\u0002HA0G\"\u0004\b\u0001\u0010A*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010-\u001a\u0002HF2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002HA0C¢\u0006\u0002\u0010H\u001a,\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010J\u001a4\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0010K\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0086\u0002¢\u0006\u0002\u0010L\u001a2\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u00020MH\u0086\u0002¢\u0006\u0002\u0010N\u001a\u0015\u0010I\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005H\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0006*\u00020\u00062\u0006\u0010K\u001a\u00020\u0006H\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\u0006*\u00020\u00062\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00050MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0007H\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\b*\u00020\b2\u0006\u0010K\u001a\u00020\bH\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\b*\u00020\b2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00070MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\n*\u00020\n2\u0006\u0010\u0016\u001a\u00020\tH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\nH\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\n*\u00020\n2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\t0MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000bH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\f*\u00020\f2\u0006\u0010K\u001a\u00020\fH\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\f*\u00020\f2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000b0MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010K\u001a\u00020\u000eH\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\u000e*\u00020\u000e2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\r0MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000fH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0010*\u00020\u00102\u0006\u0010K\u001a\u00020\u0010H\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\u0010*\u00020\u00102\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000f0MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0011H\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0012*\u00020\u00122\u0006\u0010K\u001a\u00020\u0012H\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\u0012*\u00020\u00122\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110MH\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u0010I\u001a\u00020\u0014*\u00020\u00142\u0006\u0010K\u001a\u00020\u0014H\u0086\u0002\u001a\u001b\u0010I\u001a\u00020\u0014*\u00020\u00142\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00130MH\u0086\u0002\u001a,\u0010O\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010J\u001a\u001d\u0010P\u001a\u00020>\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010Q\u001a*\u0010P\u001a\u00020>\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020R*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010S\u001a1\u0010P\u001a\u00020>\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010T\u001a\n\u0010P\u001a\u00020>*\u00020\b\u001a\u001e\u0010P\u001a\u00020>*\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010P\u001a\u00020>*\u00020\n\u001a\u001e\u0010P\u001a\u00020>*\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010P\u001a\u00020>*\u00020\f\u001a\u001e\u0010P\u001a\u00020>*\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010P\u001a\u00020>*\u00020\u000e\u001a\u001e\u0010P\u001a\u00020>*\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010P\u001a\u00020>*\u00020\u0010\u001a\u001e\u0010P\u001a\u00020>*\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010P\u001a\u00020>*\u00020\u0012\u001a\u001e\u0010P\u001a\u00020>*\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010P\u001a\u00020>*\u00020\u0014\u001a\u001e\u0010P\u001a\u00020>*\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a9\u0010U\u001a\u00020>\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010V\u001aM\u0010U\u001a\u00020>\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010W\u001a-\u0010X\u001a\b\u0012\u0004\u0012\u0002H\u00020Y\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020R*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010Z\u001a?\u0010X\u001a\b\u0012\u0004\u0012\u0002H\u00020Y\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010[\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00050Y*\u00020\u0006\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00070Y*\u00020\b\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\t0Y*\u00020\n\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\u000b0Y*\u00020\f\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\r0Y*\u00020\u000e\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\u000f0Y*\u00020\u0010\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00110Y*\u00020\u0012\u001a\u0010\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00130Y*\u00020\u0014\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u0006¢\u0006\u0002\u0010]\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\u00020\b¢\u0006\u0002\u0010^\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\n¢\u0006\u0002\u0010_\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003*\u00020\f¢\u0006\u0002\u0010`\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\r0\u0003*\u00020\u000e¢\u0006\u0002\u0010a\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003*\u00020\u0010¢\u0006\u0002\u0010b\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003*\u00020\u0012¢\u0006\u0002\u0010c\u001a\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003*\u00020\u0014¢\u0006\u0002\u0010d¨\u0006e"}, m8860e = {"asList", "", TessBaseAPI.f9204e, "", "([Ljava/lang/Object;)Ljava/util/List;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "binarySearch", "element", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "fromIndex", "toIndex", "([Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;II)I", "([Ljava/lang/Object;Ljava/lang/Object;II)I", "contentDeepEquals", "other", "contentDeepEqualsInline", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepHashCode", "contentDeepHashCodeInline", "([Ljava/lang/Object;)I", "contentDeepToString", "", "contentDeepToStringInline", "([Ljava/lang/Object;)Ljava/lang/String;", "contentEquals", "contentHashCode", "contentToString", "copyInto", "destination", "destinationOffset", "startIndex", "endIndex", "([Ljava/lang/Object;[Ljava/lang/Object;III)[Ljava/lang/Object;", "copyOf", "([Ljava/lang/Object;)[Ljava/lang/Object;", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRange", "copyOfRangeInline", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "copyOfRangeImpl", "elementAt", "index", "([Ljava/lang/Object;I)Ljava/lang/Object;", "fill", "", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "filterIsInstance", "R", "klass", "Ljava/lang/Class;", "([Ljava/lang/Object;Ljava/lang/Class;)Ljava/util/List;", "filterIsInstanceTo", "C", "", "([Ljava/lang/Object;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "plus", "([Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "elements", "([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "", "([Ljava/lang/Object;Ljava/util/Collection;)[Ljava/lang/Object;", "plusElement", "sort", "([Ljava/lang/Object;)V", "", "([Ljava/lang/Comparable;)V", "([Ljava/lang/Object;II)V", "sortWith", "([Ljava/lang/Object;Ljava/util/Comparator;)V", "([Ljava/lang/Object;Ljava/util/Comparator;II)V", "toSortedSet", "Ljava/util/SortedSet;", "([Ljava/lang/Comparable;)Ljava/util/SortedSet;", "([Ljava/lang/Object;Ljava/util/Comparator;)Ljava/util/SortedSet;", "toTypedArray", "([Z)[Ljava/lang/Boolean;", "([B)[Ljava/lang/Byte;", "([C)[Ljava/lang/Character;", "([D)[Ljava/lang/Double;", "([F)[Ljava/lang/Float;", "([I)[Ljava/lang/Integer;", "([J)[Ljava/lang/Long;", "([S)[Ljava/lang/Short;", "kotlin-stdlib"}, m8859f = "kotlin/collections/ArraysKt", m8857h = 1)
/* renamed from: z1.bze */
/* loaded from: classes3.dex */
public class _ArraysJvm extends Arrays {
    @cey
    /* renamed from: b */
    private static final <T> T m8135b(@NotNull T[] tArr, int i) {
        return tArr[i];
    }

    @cey
    /* renamed from: a */
    private static final byte m8270a(@NotNull byte[] bArr, int i) {
        return bArr[i];
    }

    @cey
    /* renamed from: a */
    private static final short m8192a(@NotNull short[] sArr, int i) {
        return sArr[i];
    }

    @cey
    /* renamed from: b */
    private static final int m8147b(@NotNull int[] iArr, int i) {
        return iArr[i];
    }

    @cey
    /* renamed from: a */
    private static final long m8219a(@NotNull long[] jArr, int i) {
        return jArr[i];
    }

    @cey
    /* renamed from: a */
    private static final float m8237a(@NotNull float[] fArr, int i) {
        return fArr[i];
    }

    @cey
    /* renamed from: a */
    private static final double m8248a(@NotNull double[] dArr, int i) {
        return dArr[i];
    }

    @cey
    /* renamed from: a */
    private static final boolean m8181a(@NotNull boolean[] zArr, int i) {
        return zArr[i];
    }

    @cey
    /* renamed from: a */
    private static final char m8259a(@NotNull char[] cArr, int i) {
        return cArr[i];
    }

    @NotNull
    /* renamed from: a */
    public static final <R> List<R> m8206a(@NotNull Object[] objArr, @NotNull Class<R> cls) {
        cji.m5162f(objArr, "$this$filterIsInstance");
        cji.m5162f(cls, "klass");
        return (List) bzb.m8199a(objArr, new ArrayList(), cls);
    }

    @NotNull
    /* renamed from: a */
    public static final <C extends Collection<? super R>, R> C m8199a(@NotNull Object[] objArr, @NotNull C c, @NotNull Class<R> cls) {
        cji.m5162f(objArr, "$this$filterIsInstanceTo");
        cji.m5162f(c, "destination");
        cji.m5162f(cls, "klass");
        for (Object obj : objArr) {
            if (cls.isInstance(obj)) {
                c.add(obj);
            }
        }
        return c;
    }

    @NotNull
    /* renamed from: d */
    public static final <T> List<T> m8093d(@NotNull T[] tArr) {
        cji.m5162f(tArr, "$this$asList");
        List<T> a = ArraysUtilJVM.m6827a(tArr);
        cji.m5175b(a, "ArraysUtilJVM.asList(this)");
        return a;
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$1", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Byte;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$a */
    /* loaded from: classes3.dex */
    public static final class C4829a extends AbstractList<Byte> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ byte[] f20443b;

        C4829a(byte[] bArr) {
            this.f20443b = bArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Byte) {
                return m8060a(((Number) obj).byteValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Byte) {
                return m8058b(((Number) obj).byteValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Byte) {
                return m8057c(((Number) obj).byteValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20443b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20443b.length == 0;
        }

        /* renamed from: a */
        public boolean m8060a(byte b) {
            return bzb.m7588b(this.f20443b, b);
        }

        @NotNull
        /* renamed from: a */
        public Byte get(int i) {
            return Byte.valueOf(this.f20443b[i]);
        }

        /* renamed from: b */
        public int m8058b(byte b) {
            return bzb.m7422c(this.f20443b, b);
        }

        /* renamed from: c */
        public int m8057c(byte b) {
            return bzb.m7333d(this.f20443b, b);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Byte> m8274a(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$asList");
        return new C4829a(bArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$2", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Short;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$b */
    /* loaded from: classes3.dex */
    public static final class C4830b extends AbstractList<Short> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ short[] f20444b;

        C4830b(short[] sArr) {
            this.f20444b = sArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Short) {
                return m8055a(((Number) obj).shortValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Short) {
                return m8054b(((Number) obj).shortValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Short) {
                return m8053c(((Number) obj).shortValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20444b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20444b.length == 0;
        }

        /* renamed from: a */
        public boolean m8055a(short s) {
            return bzb.m7442b(this.f20444b, s);
        }

        @NotNull
        /* renamed from: a */
        public Short get(int i) {
            return Short.valueOf(this.f20444b[i]);
        }

        /* renamed from: b */
        public int m8054b(short s) {
            return bzb.m7343c(this.f20444b, s);
        }

        /* renamed from: c */
        public int m8053c(short s) {
            return bzb.m7282d(this.f20444b, s);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Short> m8193a(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$asList");
        return new C4830b(sArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$3", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Integer;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$c */
    /* loaded from: classes3.dex */
    public static final class C4831c extends AbstractList<Integer> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ int[] f20445b;

        C4831c(int[] iArr) {
            this.f20445b = iArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Integer) {
                return m8052a(((Number) obj).intValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Integer) {
                return m8050c(((Number) obj).intValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Integer) {
                return m8049d(((Number) obj).intValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20445b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20445b.length == 0;
        }

        /* renamed from: a */
        public boolean m8052a(int i) {
            return bzb.m7516b(this.f20445b, i);
        }

        @NotNull
        /* renamed from: b */
        public Integer get(int i) {
            return Integer.valueOf(this.f20445b[i]);
        }

        /* renamed from: c */
        public int m8050c(int i) {
            return bzb.m7309d(this.f20445b, i);
        }

        /* renamed from: d */
        public int m8049d(int i) {
            return bzb.m7253e(this.f20445b, i);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Integer> m8230a(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$asList");
        return new C4831c(iArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$4", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Long;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$d */
    /* loaded from: classes3.dex */
    public static final class C4832d extends AbstractList<Long> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ long[] f20446b;

        C4832d(long[] jArr) {
            this.f20446b = jArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Long) {
                return m8047a(((Number) obj).longValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Long) {
                return m8046b(((Number) obj).longValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Long) {
                return m8045c(((Number) obj).longValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20446b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20446b.length == 0;
        }

        /* renamed from: a */
        public boolean m8047a(long j) {
            return bzb.m7497b(this.f20446b, j);
        }

        @NotNull
        /* renamed from: a */
        public Long get(int i) {
            return Long.valueOf(this.f20446b[i]);
        }

        /* renamed from: b */
        public int m8046b(long j) {
            return bzb.m7377c(this.f20446b, j);
        }

        /* renamed from: c */
        public int m8045c(long j) {
            return bzb.m7303d(this.f20446b, j);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Long> m8220a(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$asList");
        return new C4832d(jArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$5", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Float;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$e */
    /* loaded from: classes3.dex */
    public static final class C4833e extends AbstractList<Float> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ float[] f20447b;

        C4833e(float[] fArr) {
            this.f20447b = fArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Float) {
                return m8044a(((Number) obj).floatValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Float) {
                return m8042b(((Number) obj).floatValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Float) {
                return m8041c(((Number) obj).floatValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20447b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20447b.length == 0;
        }

        /* renamed from: a */
        public boolean m8044a(float f) {
            return bzb.m7534b(this.f20447b, f);
        }

        @NotNull
        /* renamed from: a */
        public Float get(int i) {
            return Float.valueOf(this.f20447b[i]);
        }

        /* renamed from: b */
        public int m8042b(float f) {
            return bzb.m7395c(this.f20447b, f);
        }

        /* renamed from: c */
        public int m8041c(float f) {
            return bzb.m7315d(this.f20447b, f);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Float> m8241a(@NotNull float[] fArr) {
        cji.m5162f(fArr, "$this$asList");
        return new C4833e(fArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$6", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Double;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$f */
    /* loaded from: classes3.dex */
    public static final class C4834f extends AbstractList<Double> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ double[] f20448b;

        C4834f(double[] dArr) {
            this.f20448b = dArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Double) {
                return m8040a(((Number) obj).doubleValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Double) {
                return m8038b(((Number) obj).doubleValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Double) {
                return m8037c(((Number) obj).doubleValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20448b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20448b.length == 0;
        }

        /* renamed from: a */
        public boolean m8040a(double d) {
            return bzb.m7552b(this.f20448b, d);
        }

        @NotNull
        /* renamed from: a */
        public Double get(int i) {
            return Double.valueOf(this.f20448b[i]);
        }

        /* renamed from: b */
        public int m8038b(double d) {
            return bzb.m7404c(this.f20448b, d);
        }

        /* renamed from: c */
        public int m8037c(double d) {
            return bzb.m7321d(this.f20448b, d);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Double> m8252a(@NotNull double[] dArr) {
        cji.m5162f(dArr, "$this$asList");
        return new C4834f(dArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$7", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "element", "get", "index", "(I)Ljava/lang/Boolean;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$g */
    /* loaded from: classes3.dex */
    public static final class C4835g extends AbstractList<Boolean> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ boolean[] f20449b;

        C4835g(boolean[] zArr) {
            this.f20449b = zArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Boolean) {
                return m8035a(((Boolean) obj).booleanValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Boolean) {
                return m8034b(((Boolean) obj).booleanValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Boolean) {
                return m8033c(((Boolean) obj).booleanValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20449b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20449b.length == 0;
        }

        /* renamed from: a */
        public boolean m8035a(boolean z) {
            return bzb.m7424b(this.f20449b, z);
        }

        @NotNull
        /* renamed from: a */
        public Boolean get(int i) {
            return Boolean.valueOf(this.f20449b[i]);
        }

        /* renamed from: b */
        public int m8034b(boolean z) {
            return bzb.m7334c(this.f20449b, z);
        }

        /* renamed from: c */
        public int m8033c(boolean z) {
            return bzb.m7275d(this.f20449b, z);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Boolean> m8182a(@NotNull boolean[] zArr) {
        cji.m5162f(zArr, "$this$asList");
        return new C4835g(zArr);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m8860e = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$8", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Character;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: z1.bze$h */
    /* loaded from: classes3.dex */
    public static final class C4836h extends AbstractList<Character> implements RandomAccess {

        /* renamed from: b */
        final /* synthetic */ char[] f20450b;

        C4836h(char[] cArr) {
            this.f20450b = cArr;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Character) {
                return m8032a(((Character) obj).charValue());
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof Character) {
                return m8030b(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof Character) {
                return m8029c(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return this.f20450b.length;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.f20450b.length == 0;
        }

        /* renamed from: a */
        public boolean m8032a(char c) {
            return bzb.m7570b(this.f20450b, c);
        }

        @NotNull
        /* renamed from: a */
        public Character get(int i) {
            return Character.valueOf(this.f20450b[i]);
        }

        /* renamed from: b */
        public int m8030b(char c) {
            return bzb.m7413c(this.f20450b, c);
        }

        /* renamed from: c */
        public int m8029c(char c) {
            return bzb.m7327d(this.f20450b, c);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final List<Character> m8263a(@NotNull char[] cArr) {
        cji.m5162f(cArr, "$this$asList");
        return new C4836h(cArr);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8201a(Object[] objArr, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        return bzb.m8202a(objArr, obj, comparator, i, i2);
    }

    /* renamed from: a */
    public static final <T> int m8202a(@NotNull T[] tArr, T t, @NotNull Comparator<? super T> comparator, int i, int i2) {
        cji.m5162f(tArr, "$this$binarySearch");
        cji.m5162f(comparator, "comparator");
        return Arrays.binarySearch(tArr, i, i2, t, comparator);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8203a(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        return bzb.m8204a(objArr, obj, i, i2);
    }

    /* renamed from: a */
    public static final <T> int m8204a(@NotNull T[] tArr, T t, int i, int i2) {
        cji.m5162f(tArr, "$this$binarySearch");
        return Arrays.binarySearch(tArr, i, i2, t);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8271a(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return bzb.m8272a(bArr, b, i, i2);
    }

    /* renamed from: a */
    public static final int m8272a(@NotNull byte[] bArr, byte b, int i, int i2) {
        cji.m5162f(bArr, "$this$binarySearch");
        return Arrays.binarySearch(bArr, i, i2, b);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8186a(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        return bzb.m8187a(sArr, s, i, i2);
    }

    /* renamed from: a */
    public static final int m8187a(@NotNull short[] sArr, short s, int i, int i2) {
        cji.m5162f(sArr, "$this$binarySearch");
        return Arrays.binarySearch(sArr, i, i2, s);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8226a(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        return bzb.m8227a(iArr, i, i2, i3);
    }

    /* renamed from: a */
    public static final int m8227a(@NotNull int[] iArr, int i, int i2, int i3) {
        cji.m5162f(iArr, "$this$binarySearch");
        return Arrays.binarySearch(iArr, i2, i3, i);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8214a(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        return bzb.m8215a(jArr, j, i, i2);
    }

    /* renamed from: a */
    public static final int m8215a(@NotNull long[] jArr, long j, int i, int i2) {
        cji.m5162f(jArr, "$this$binarySearch");
        return Arrays.binarySearch(jArr, i, i2, j);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8238a(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return bzb.m8239a(fArr, f, i, i2);
    }

    /* renamed from: a */
    public static final int m8239a(@NotNull float[] fArr, float f, int i, int i2) {
        cji.m5162f(fArr, "$this$binarySearch");
        return Arrays.binarySearch(fArr, i, i2, f);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8249a(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        return bzb.m8250a(dArr, d, i, i2);
    }

    /* renamed from: a */
    public static final int m8250a(@NotNull double[] dArr, double d, int i, int i2) {
        cji.m5162f(dArr, "$this$binarySearch");
        return Arrays.binarySearch(dArr, i, i2, d);
    }

    /* renamed from: a */
    public static /* synthetic */ int m8260a(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return bzb.m8261a(cArr, c, i, i2);
    }

    /* renamed from: a */
    public static final int m8261a(@NotNull char[] cArr, char c, int i, int i2) {
        cji.m5162f(cArr, "$this$binarySearch");
        return Arrays.binarySearch(cArr, i, i2, c);
    }

    @bwy(m8750a = "1.1")
    @cey
    @cgo(m5270a = "contentDeepEqualsInline")
    /* renamed from: c */
    private static final <T> boolean m8103c(@NotNull T[] tArr, T[] tArr2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8279a((Object[]) tArr, (Object[]) tArr2);
        }
        return Arrays.deepEquals(tArr, tArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    @cgo(m5270a = "contentDeepHashCodeInline")
    /* renamed from: f */
    private static final <T> int m8074f(@NotNull T[] tArr) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8282b(tArr);
        }
        return Arrays.deepHashCode(tArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    @cgo(m5270a = "contentDeepToStringInline")
    /* renamed from: g */
    private static final <T> String m8065g(@NotNull T[] tArr) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8276c(tArr);
        }
        String deepToString = Arrays.deepToString(tArr);
        cji.m5175b(deepToString, "java.util.Arrays.deepToString(this)");
        return deepToString;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: d */
    private static final <T> boolean m8092d(@NotNull T[] tArr, T[] tArr2) {
        return Arrays.equals(tArr, tArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8167b(@NotNull byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8123b(@NotNull short[] sArr, short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8143b(@NotNull int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8137b(@NotNull long[] jArr, long[] jArr2) {
        return Arrays.equals(jArr, jArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8149b(@NotNull float[] fArr, float[] fArr2) {
        return Arrays.equals(fArr, fArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8155b(@NotNull double[] dArr, double[] dArr2) {
        return Arrays.equals(dArr, dArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8119b(@NotNull boolean[] zArr, boolean[] zArr2) {
        return Arrays.equals(zArr, zArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final boolean m8161b(@NotNull char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: h */
    private static final <T> int m8063h(@NotNull T[] tArr) {
        return Arrays.hashCode(tArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8089e(@NotNull byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8082e(@NotNull short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8085e(@NotNull int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8084e(@NotNull long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8086e(@NotNull float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8087e(@NotNull double[] dArr) {
        return Arrays.hashCode(dArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: d */
    private static final int m8090d(@NotNull boolean[] zArr) {
        return Arrays.hashCode(zArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final int m8088e(@NotNull char[] cArr) {
        return Arrays.hashCode(cArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: i */
    private static final <T> String m8062i(@NotNull T[] tArr) {
        String arrays = Arrays.toString(tArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8080f(@NotNull byte[] bArr) {
        String arrays = Arrays.toString(bArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8073f(@NotNull short[] sArr) {
        String arrays = Arrays.toString(sArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8076f(@NotNull int[] iArr) {
        String arrays = Arrays.toString(iArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8075f(@NotNull long[] jArr) {
        String arrays = Arrays.toString(jArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8077f(@NotNull float[] fArr) {
        String arrays = Arrays.toString(fArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8078f(@NotNull double[] dArr) {
        String arrays = Arrays.toString(dArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final String m8081e(@NotNull boolean[] zArr) {
        String arrays = Arrays.toString(zArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: f */
    private static final String m8079f(@NotNull char[] cArr) {
        String arrays = Arrays.toString(cArr);
        cji.m5175b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    /* renamed from: a */
    public static /* synthetic */ Object[] m8194a(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        return bzb.m8195a(objArr, objArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> T[] m8195a(@NotNull T[] tArr, @NotNull T[] tArr2, int i, int i2, int i3) {
        cji.m5162f(tArr, "$this$copyInto");
        cji.m5162f(tArr2, "destination");
        System.arraycopy(tArr, i2, tArr2, i, i3 - i2);
        return tArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ byte[] m8264a(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        return bzb.m8265a(bArr, bArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final byte[] m8265a(@NotNull byte[] bArr, @NotNull byte[] bArr2, int i, int i2, int i3) {
        cji.m5162f(bArr, "$this$copyInto");
        cji.m5162f(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
        return bArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ short[] m8183a(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length;
        }
        return bzb.m8184a(sArr, sArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final short[] m8184a(@NotNull short[] sArr, @NotNull short[] sArr2, int i, int i2, int i3) {
        cji.m5162f(sArr, "$this$copyInto");
        cji.m5162f(sArr2, "destination");
        System.arraycopy(sArr, i2, sArr2, i, i3 - i2);
        return sArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ int[] m8221a(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length;
        }
        return bzb.m8222a(iArr, iArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final int[] m8222a(@NotNull int[] iArr, @NotNull int[] iArr2, int i, int i2, int i3) {
        cji.m5162f(iArr, "$this$copyInto");
        cji.m5162f(iArr2, "destination");
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
        return iArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ long[] m8210a(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length;
        }
        return bzb.m8211a(jArr, jArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final long[] m8211a(@NotNull long[] jArr, @NotNull long[] jArr2, int i, int i2, int i3) {
        cji.m5162f(jArr, "$this$copyInto");
        cji.m5162f(jArr2, "destination");
        System.arraycopy(jArr, i2, jArr2, i, i3 - i2);
        return jArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ float[] m8231a(float[] fArr, float[] fArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length;
        }
        return bzb.m8232a(fArr, fArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final float[] m8232a(@NotNull float[] fArr, @NotNull float[] fArr2, int i, int i2, int i3) {
        cji.m5162f(fArr, "$this$copyInto");
        cji.m5162f(fArr2, "destination");
        System.arraycopy(fArr, i2, fArr2, i, i3 - i2);
        return fArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ double[] m8242a(double[] dArr, double[] dArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length;
        }
        return bzb.m8243a(dArr, dArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final double[] m8243a(@NotNull double[] dArr, @NotNull double[] dArr2, int i, int i2, int i3) {
        cji.m5162f(dArr, "$this$copyInto");
        cji.m5162f(dArr2, "destination");
        System.arraycopy(dArr, i2, dArr2, i, i3 - i2);
        return dArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ boolean[] m8173a(boolean[] zArr, boolean[] zArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = zArr.length;
        }
        return bzb.m8174a(zArr, zArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final boolean[] m8174a(@NotNull boolean[] zArr, @NotNull boolean[] zArr2, int i, int i2, int i3) {
        cji.m5162f(zArr, "$this$copyInto");
        cji.m5162f(zArr2, "destination");
        System.arraycopy(zArr, i2, zArr2, i, i3 - i2);
        return zArr2;
    }

    /* renamed from: a */
    public static /* synthetic */ char[] m8253a(char[] cArr, char[] cArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = cArr.length;
        }
        return bzb.m8254a(cArr, cArr2, i, i2, i3);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final char[] m8254a(@NotNull char[] cArr, @NotNull char[] cArr2, int i, int i2, int i3) {
        cji.m5162f(cArr, "$this$copyInto");
        cji.m5162f(cArr2, "destination");
        System.arraycopy(cArr, i2, cArr2, i, i3 - i2);
        return cArr2;
    }

    @cey
    /* renamed from: j */
    private static final <T> T[] m8061j(@NotNull T[] tArr) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length);
        cji.m5175b(tArr2, "java.util.Arrays.copyOf(this, size)");
        return tArr2;
    }

    @cey
    /* renamed from: g */
    private static final byte[] m8071g(@NotNull byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: g */
    private static final short[] m8064g(@NotNull short[] sArr) {
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: g */
    private static final int[] m8067g(@NotNull int[] iArr) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: g */
    private static final long[] m8066g(@NotNull long[] jArr) {
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: g */
    private static final float[] m8068g(@NotNull float[] fArr) {
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: g */
    private static final double[] m8069g(@NotNull double[] dArr) {
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: f */
    private static final boolean[] m8072f(@NotNull boolean[] zArr) {
        boolean[] copyOf = Arrays.copyOf(zArr, zArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: g */
    private static final char[] m8070g(@NotNull char[] cArr) {
        char[] copyOf = Arrays.copyOf(cArr, cArr.length);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final byte[] m8169b(@NotNull byte[] bArr, int i) {
        byte[] copyOf = Arrays.copyOf(bArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final short[] m8127b(@NotNull short[] sArr, int i) {
        short[] copyOf = Arrays.copyOf(sArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: c */
    private static final int[] m8109c(@NotNull int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final long[] m8141b(@NotNull long[] jArr, int i) {
        long[] copyOf = Arrays.copyOf(jArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final float[] m8151b(@NotNull float[] fArr, int i) {
        float[] copyOf = Arrays.copyOf(fArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final double[] m8157b(@NotNull double[] dArr, int i) {
        double[] copyOf = Arrays.copyOf(dArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final boolean[] m8121b(@NotNull boolean[] zArr, int i) {
        boolean[] copyOf = Arrays.copyOf(zArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final char[] m8163b(@NotNull char[] cArr, int i) {
        char[] copyOf = Arrays.copyOf(cArr, i);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @cey
    /* renamed from: c */
    private static final <T> T[] m8105c(@NotNull T[] tArr, int i) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i);
        cji.m5175b(tArr2, "java.util.Arrays.copyOf(this, newSize)");
        return tArr2;
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final <T> T[] m8104c(@NotNull T[] tArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return (T[]) bzb.m8208a(tArr, i, i2);
        }
        if (i2 <= tArr.length) {
            T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i, i2);
            cji.m5175b(tArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return tArr2;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + tArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final byte[] m8117c(@NotNull byte[] bArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8269a(bArr, i, i2);
        }
        if (i2 <= bArr.length) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + bArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final short[] m8101c(@NotNull short[] sArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8191a(sArr, i, i2);
        }
        if (i2 <= sArr.length) {
            short[] copyOfRange = Arrays.copyOfRange(sArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + sArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final int[] m8108c(@NotNull int[] iArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8228a(iArr, i, i2);
        }
        if (i2 <= iArr.length) {
            int[] copyOfRange = Arrays.copyOfRange(iArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + iArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final long[] m8106c(@NotNull long[] jArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8218a(jArr, i, i2);
        }
        if (i2 <= jArr.length) {
            long[] copyOfRange = Arrays.copyOfRange(jArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + jArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final float[] m8111c(@NotNull float[] fArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8236a(fArr, i, i2);
        }
        if (i2 <= fArr.length) {
            float[] copyOfRange = Arrays.copyOfRange(fArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + fArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final double[] m8113c(@NotNull double[] dArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8247a(dArr, i, i2);
        }
        if (i2 <= dArr.length) {
            double[] copyOfRange = Arrays.copyOfRange(dArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + dArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: b */
    private static final boolean[] m8120b(@NotNull boolean[] zArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8180a(zArr, i, i2);
        }
        if (i2 <= zArr.length) {
            boolean[] copyOfRange = Arrays.copyOfRange(zArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + zArr.length);
    }

    @cey
    @cgo(m5270a = "copyOfRangeInline")
    /* renamed from: c */
    private static final char[] m8115c(@NotNull char[] cArr, int i, int i2) {
        if (cfe.m5471a(1, 3, 0)) {
            return bzb.m8258a(cArr, i, i2);
        }
        if (i2 <= cArr.length) {
            char[] copyOfRange = Arrays.copyOfRange(cArr, i, i2);
            cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + cArr.length);
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final <T> T[] m8208a(@NotNull T[] tArr, int i, int i2) {
        cji.m5162f(tArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, tArr.length);
        T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i, i2);
        cji.m5175b(tArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return tArr2;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final byte[] m8269a(@NotNull byte[] bArr, int i, int i2) {
        cji.m5162f(bArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, bArr.length);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final short[] m8191a(@NotNull short[] sArr, int i, int i2) {
        cji.m5162f(sArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, sArr.length);
        short[] copyOfRange = Arrays.copyOfRange(sArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final int[] m8228a(@NotNull int[] iArr, int i, int i2) {
        cji.m5162f(iArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, iArr.length);
        int[] copyOfRange = Arrays.copyOfRange(iArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final long[] m8218a(@NotNull long[] jArr, int i, int i2) {
        cji.m5162f(jArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, jArr.length);
        long[] copyOfRange = Arrays.copyOfRange(jArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final float[] m8236a(@NotNull float[] fArr, int i, int i2) {
        cji.m5162f(fArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, fArr.length);
        float[] copyOfRange = Arrays.copyOfRange(fArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final double[] m8247a(@NotNull double[] dArr, int i, int i2) {
        cji.m5162f(dArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, dArr.length);
        double[] copyOfRange = Arrays.copyOfRange(dArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final boolean[] m8180a(@NotNull boolean[] zArr, int i, int i2) {
        cji.m5162f(zArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, zArr.length);
        boolean[] copyOfRange = Arrays.copyOfRange(zArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "copyOfRange")
    @NotNull
    /* renamed from: a */
    public static final char[] m8258a(@NotNull char[] cArr, int i, int i2) {
        cji.m5162f(cArr, "$this$copyOfRangeImpl");
        bzb.m8287a(i2, cArr.length);
        char[] copyOfRange = Arrays.copyOfRange(cArr, i, i2);
        cji.m5175b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    /* renamed from: b */
    public static /* synthetic */ void m8131b(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        bzb.m8132b(objArr, obj, i, i2);
    }

    /* renamed from: b */
    public static final <T> void m8132b(@NotNull T[] tArr, T t, int i, int i2) {
        cji.m5162f(tArr, "$this$fill");
        Arrays.fill(tArr, i, i2, t);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8170b(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        bzb.m8171b(bArr, b, i, i2);
    }

    /* renamed from: b */
    public static final void m8171b(@NotNull byte[] bArr, byte b, int i, int i2) {
        cji.m5162f(bArr, "$this$fill");
        Arrays.fill(bArr, i, i2, b);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8124b(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        bzb.m8125b(sArr, s, i, i2);
    }

    /* renamed from: b */
    public static final void m8125b(@NotNull short[] sArr, short s, int i, int i2) {
        cji.m5162f(sArr, "$this$fill");
        Arrays.fill(sArr, i, i2, s);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8144b(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        bzb.m8145b(iArr, i, i2, i3);
    }

    /* renamed from: b */
    public static final void m8145b(@NotNull int[] iArr, int i, int i2, int i3) {
        cji.m5162f(iArr, "$this$fill");
        Arrays.fill(iArr, i2, i3, i);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8138b(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        bzb.m8139b(jArr, j, i, i2);
    }

    /* renamed from: b */
    public static final void m8139b(@NotNull long[] jArr, long j, int i, int i2) {
        cji.m5162f(jArr, "$this$fill");
        Arrays.fill(jArr, i, i2, j);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8152b(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        bzb.m8153b(fArr, f, i, i2);
    }

    /* renamed from: b */
    public static final void m8153b(@NotNull float[] fArr, float f, int i, int i2) {
        cji.m5162f(fArr, "$this$fill");
        Arrays.fill(fArr, i, i2, f);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8158b(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        bzb.m8159b(dArr, d, i, i2);
    }

    /* renamed from: b */
    public static final void m8159b(@NotNull double[] dArr, double d, int i, int i2) {
        cji.m5162f(dArr, "$this$fill");
        Arrays.fill(dArr, i, i2, d);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8176a(boolean[] zArr, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = zArr.length;
        }
        bzb.m8177a(zArr, z, i, i2);
    }

    /* renamed from: a */
    public static final void m8177a(@NotNull boolean[] zArr, boolean z, int i, int i2) {
        cji.m5162f(zArr, "$this$fill");
        Arrays.fill(zArr, i, i2, z);
    }

    /* renamed from: b */
    public static /* synthetic */ void m8164b(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        bzb.m8165b(cArr, c, i, i2);
    }

    /* renamed from: b */
    public static final void m8165b(@NotNull char[] cArr, char c, int i, int i2) {
        cji.m5162f(cArr, "$this$fill");
        Arrays.fill(cArr, i, i2, c);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> T[] m8205a(@NotNull T[] tArr, T t) {
        cji.m5162f(tArr, "$this$plus");
        int length = tArr.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, length + 1);
        tArr2[length] = t;
        cji.m5175b(tArr2, C4985cm.f20833c);
        return tArr2;
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m8273a(@NotNull byte[] bArr, byte b) {
        cji.m5162f(bArr, "$this$plus");
        int length = bArr.length;
        byte[] copyOf = Arrays.copyOf(bArr, length + 1);
        copyOf[length] = b;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final short[] m8188a(@NotNull short[] sArr, short s) {
        cji.m5162f(sArr, "$this$plus");
        int length = sArr.length;
        short[] copyOf = Arrays.copyOf(sArr, length + 1);
        copyOf[length] = s;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final int[] m8229a(@NotNull int[] iArr, int i) {
        cji.m5162f(iArr, "$this$plus");
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, length + 1);
        copyOf[length] = i;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final long[] m8216a(@NotNull long[] jArr, long j) {
        cji.m5162f(jArr, "$this$plus");
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, length + 1);
        copyOf[length] = j;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final float[] m8240a(@NotNull float[] fArr, float f) {
        cji.m5162f(fArr, "$this$plus");
        int length = fArr.length;
        float[] copyOf = Arrays.copyOf(fArr, length + 1);
        copyOf[length] = f;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final double[] m8251a(@NotNull double[] dArr, double d) {
        cji.m5162f(dArr, "$this$plus");
        int length = dArr.length;
        double[] copyOf = Arrays.copyOf(dArr, length + 1);
        copyOf[length] = d;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final boolean[] m8178a(@NotNull boolean[] zArr, boolean z) {
        cji.m5162f(zArr, "$this$plus");
        int length = zArr.length;
        boolean[] copyOf = Arrays.copyOf(zArr, length + 1);
        copyOf[length] = z;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final char[] m8262a(@NotNull char[] cArr, char c) {
        cji.m5162f(cArr, "$this$plus");
        int length = cArr.length;
        char[] copyOf = Arrays.copyOf(cArr, length + 1);
        copyOf[length] = c;
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: a */
    public static final <T> T[] m8200a(@NotNull T[] tArr, @NotNull Collection<? extends T> collection) {
        cji.m5162f(tArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = tArr.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, collection.size() + length);
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            length++;
            tArr2[length] = it.next();
        }
        cji.m5175b(tArr2, C4985cm.f20833c);
        return tArr2;
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m8267a(@NotNull byte[] bArr, @NotNull Collection<Byte> collection) {
        cji.m5162f(bArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = bArr.length;
        byte[] copyOf = Arrays.copyOf(bArr, collection.size() + length);
        for (Byte b : collection) {
            length++;
            copyOf[length] = b.byteValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final short[] m8189a(@NotNull short[] sArr, @NotNull Collection<Short> collection) {
        cji.m5162f(sArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = sArr.length;
        short[] copyOf = Arrays.copyOf(sArr, collection.size() + length);
        for (Short sh : collection) {
            length++;
            copyOf[length] = sh.shortValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final int[] m8224a(@NotNull int[] iArr, @NotNull Collection<Integer> collection) {
        cji.m5162f(iArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, collection.size() + length);
        for (Integer num : collection) {
            length++;
            copyOf[length] = num.intValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final long[] m8213a(@NotNull long[] jArr, @NotNull Collection<Long> collection) {
        cji.m5162f(jArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, collection.size() + length);
        for (Long l : collection) {
            length++;
            copyOf[length] = l.longValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final float[] m8234a(@NotNull float[] fArr, @NotNull Collection<Float> collection) {
        cji.m5162f(fArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = fArr.length;
        float[] copyOf = Arrays.copyOf(fArr, collection.size() + length);
        for (Float f : collection) {
            length++;
            copyOf[length] = f.floatValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final double[] m8245a(@NotNull double[] dArr, @NotNull Collection<Double> collection) {
        cji.m5162f(dArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = dArr.length;
        double[] copyOf = Arrays.copyOf(dArr, collection.size() + length);
        for (Double d : collection) {
            length++;
            copyOf[length] = d.doubleValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final boolean[] m8179a(@NotNull boolean[] zArr, @NotNull Collection<Boolean> collection) {
        cji.m5162f(zArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = zArr.length;
        boolean[] copyOf = Arrays.copyOf(zArr, collection.size() + length);
        for (Boolean bool : collection) {
            length++;
            copyOf[length] = bool.booleanValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final char[] m8256a(@NotNull char[] cArr, @NotNull Collection<Character> collection) {
        cji.m5162f(cArr, "$this$plus");
        cji.m5162f(collection, "elements");
        int length = cArr.length;
        char[] copyOf = Arrays.copyOf(cArr, collection.size() + length);
        for (Character ch : collection) {
            length++;
            copyOf[length] = ch.charValue();
        }
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: b */
    public static final <T> T[] m8129b(@NotNull T[] tArr, @NotNull T[] tArr2) {
        cji.m5162f(tArr, "$this$plus");
        cji.m5162f(tArr2, "elements");
        int length = tArr.length;
        int length2 = tArr2.length;
        T[] tArr3 = (T[]) Arrays.copyOf(tArr, length + length2);
        System.arraycopy(tArr2, 0, tArr3, length, length2);
        cji.m5175b(tArr3, C4985cm.f20833c);
        return tArr3;
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m8266a(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        cji.m5162f(bArr, "$this$plus");
        cji.m5162f(bArr2, "elements");
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] copyOf = Arrays.copyOf(bArr, length + length2);
        System.arraycopy(bArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final short[] m8185a(@NotNull short[] sArr, @NotNull short[] sArr2) {
        cji.m5162f(sArr, "$this$plus");
        cji.m5162f(sArr2, "elements");
        int length = sArr.length;
        int length2 = sArr2.length;
        short[] copyOf = Arrays.copyOf(sArr, length + length2);
        System.arraycopy(sArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final int[] m8223a(@NotNull int[] iArr, @NotNull int[] iArr2) {
        cji.m5162f(iArr, "$this$plus");
        cji.m5162f(iArr2, "elements");
        int length = iArr.length;
        int length2 = iArr2.length;
        int[] copyOf = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(iArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final long[] m8212a(@NotNull long[] jArr, @NotNull long[] jArr2) {
        cji.m5162f(jArr, "$this$plus");
        cji.m5162f(jArr2, "elements");
        int length = jArr.length;
        int length2 = jArr2.length;
        long[] copyOf = Arrays.copyOf(jArr, length + length2);
        System.arraycopy(jArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final float[] m8233a(@NotNull float[] fArr, @NotNull float[] fArr2) {
        cji.m5162f(fArr, "$this$plus");
        cji.m5162f(fArr2, "elements");
        int length = fArr.length;
        int length2 = fArr2.length;
        float[] copyOf = Arrays.copyOf(fArr, length + length2);
        System.arraycopy(fArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final double[] m8244a(@NotNull double[] dArr, @NotNull double[] dArr2) {
        cji.m5162f(dArr, "$this$plus");
        cji.m5162f(dArr2, "elements");
        int length = dArr.length;
        int length2 = dArr2.length;
        double[] copyOf = Arrays.copyOf(dArr, length + length2);
        System.arraycopy(dArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final boolean[] m8175a(@NotNull boolean[] zArr, @NotNull boolean[] zArr2) {
        cji.m5162f(zArr, "$this$plus");
        cji.m5162f(zArr2, "elements");
        int length = zArr.length;
        int length2 = zArr2.length;
        boolean[] copyOf = Arrays.copyOf(zArr, length + length2);
        System.arraycopy(zArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @NotNull
    /* renamed from: a */
    public static final char[] m8255a(@NotNull char[] cArr, @NotNull char[] cArr2) {
        cji.m5162f(cArr, "$this$plus");
        cji.m5162f(cArr2, "elements");
        int length = cArr.length;
        int length2 = cArr2.length;
        char[] copyOf = Arrays.copyOf(cArr, length + length2);
        System.arraycopy(cArr2, 0, copyOf, length, length2);
        cji.m5175b(copyOf, C4985cm.f20833c);
        return copyOf;
    }

    @cey
    /* renamed from: b */
    private static final <T> T[] m8133b(@NotNull T[] tArr, T t) {
        return (T[]) bzb.m8205a(tArr, t);
    }

    /* renamed from: b */
    public static final void m8148b(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$sort");
        if (iArr.length > 1) {
            Arrays.sort(iArr);
        }
    }

    /* renamed from: b */
    public static final void m8142b(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$sort");
        if (jArr.length > 1) {
            Arrays.sort(jArr);
        }
    }

    /* renamed from: b */
    public static final void m8172b(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$sort");
        if (bArr.length > 1) {
            Arrays.sort(bArr);
        }
    }

    /* renamed from: b */
    public static final void m8128b(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$sort");
        if (sArr.length > 1) {
            Arrays.sort(sArr);
        }
    }

    /* renamed from: b */
    public static final void m8160b(@NotNull double[] dArr) {
        cji.m5162f(dArr, "$this$sort");
        if (dArr.length > 1) {
            Arrays.sort(dArr);
        }
    }

    /* renamed from: b */
    public static final void m8154b(@NotNull float[] fArr) {
        cji.m5162f(fArr, "$this$sort");
        if (fArr.length > 1) {
            Arrays.sort(fArr);
        }
    }

    /* renamed from: b */
    public static final void m8166b(@NotNull char[] cArr) {
        cji.m5162f(cArr, "$this$sort");
        if (cArr.length > 1) {
            Arrays.sort(cArr);
        }
    }

    @cey
    /* renamed from: b */
    private static final <T extends Comparable<? super T>> void m8136b(@NotNull T[] tArr) {
        if (tArr != null) {
            bzb.m8083e((Object[]) tArr);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
    }

    /* renamed from: e */
    public static final <T> void m8083e(@NotNull T[] tArr) {
        cji.m5162f(tArr, "$this$sort");
        if (tArr.length > 1) {
            Arrays.sort(tArr);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m8207a(Object[] objArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = objArr.length;
        }
        bzb.m8134b(objArr, i, i2);
    }

    /* renamed from: b */
    public static final <T> void m8134b(@NotNull T[] tArr, int i, int i2) {
        cji.m5162f(tArr, "$this$sort");
        Arrays.sort(tArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8268a(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        bzb.m8168b(bArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8168b(@NotNull byte[] bArr, int i, int i2) {
        cji.m5162f(bArr, "$this$sort");
        Arrays.sort(bArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8190a(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        bzb.m8126b(sArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8126b(@NotNull short[] sArr, int i, int i2) {
        cji.m5162f(sArr, "$this$sort");
        Arrays.sort(sArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8225a(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        bzb.m8146b(iArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8146b(@NotNull int[] iArr, int i, int i2) {
        cji.m5162f(iArr, "$this$sort");
        Arrays.sort(iArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8217a(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        bzb.m8140b(jArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8140b(@NotNull long[] jArr, int i, int i2) {
        cji.m5162f(jArr, "$this$sort");
        Arrays.sort(jArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8235a(float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = fArr.length;
        }
        bzb.m8150b(fArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8150b(@NotNull float[] fArr, int i, int i2) {
        cji.m5162f(fArr, "$this$sort");
        Arrays.sort(fArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8246a(double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = dArr.length;
        }
        bzb.m8156b(dArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8156b(@NotNull double[] dArr, int i, int i2) {
        cji.m5162f(dArr, "$this$sort");
        Arrays.sort(dArr, i, i2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m8257a(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        bzb.m8162b(cArr, i, i2);
    }

    /* renamed from: b */
    public static final void m8162b(@NotNull char[] cArr, int i, int i2) {
        cji.m5162f(cArr, "$this$sort");
        Arrays.sort(cArr, i, i2);
    }

    /* renamed from: a */
    public static final <T> void m8198a(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(tArr, "$this$sortWith");
        cji.m5162f(comparator, "comparator");
        if (tArr.length > 1) {
            Arrays.sort(tArr, comparator);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m8196a(Object[] objArr, Comparator comparator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        bzb.m8197a(objArr, comparator, i, i2);
    }

    /* renamed from: a */
    public static final <T> void m8197a(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator, int i, int i2) {
        cji.m5162f(tArr, "$this$sortWith");
        cji.m5162f(comparator, "comparator");
        Arrays.sort(tArr, i, i2, comparator);
    }

    @NotNull
    /* renamed from: c */
    public static final Byte[] m8118c(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$toTypedArray");
        Byte[] bArr2 = new Byte[bArr.length];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    @NotNull
    /* renamed from: c */
    public static final Short[] m8102c(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$toTypedArray");
        Short[] shArr = new Short[sArr.length];
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return shArr;
    }

    @NotNull
    /* renamed from: c */
    public static final Integer[] m8110c(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$toTypedArray");
        Integer[] numArr = new Integer[iArr.length];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    @NotNull
    /* renamed from: c */
    public static final Long[] m8107c(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$toTypedArray");
        Long[] lArr = new Long[jArr.length];
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    @NotNull
    /* renamed from: c */
    public static final Float[] m8112c(@NotNull float[] fArr) {
        cji.m5162f(fArr, "$this$toTypedArray");
        Float[] fArr2 = new Float[fArr.length];
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    @NotNull
    /* renamed from: c */
    public static final Double[] m8114c(@NotNull double[] dArr) {
        cji.m5162f(dArr, "$this$toTypedArray");
        Double[] dArr2 = new Double[dArr.length];
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    @NotNull
    /* renamed from: b */
    public static final Boolean[] m8122b(@NotNull boolean[] zArr) {
        cji.m5162f(zArr, "$this$toTypedArray");
        Boolean[] boolArr = new Boolean[zArr.length];
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    @NotNull
    /* renamed from: c */
    public static final Character[] m8116c(@NotNull char[] cArr) {
        cji.m5162f(cArr, "$this$toTypedArray");
        Character[] chArr = new Character[cArr.length];
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return chArr;
    }

    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> SortedSet<T> m8209a(@NotNull T[] tArr) {
        cji.m5162f(tArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7239e((Object[]) tArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Byte> m8099d(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7419c(bArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Short> m8091d(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7349c(sArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Integer> m8095d(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7384c(iArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Long> m8094d(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7375c(jArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Float> m8096d(@NotNull float[] fArr) {
        cji.m5162f(fArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7392c(fArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Double> m8097d(@NotNull double[] dArr) {
        cji.m5162f(dArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7401c(dArr, new TreeSet());
    }

    @NotNull
    /* renamed from: c */
    public static final SortedSet<Boolean> m8100c(@NotNull boolean[] zArr) {
        cji.m5162f(zArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7340c(zArr, new TreeSet());
    }

    @NotNull
    /* renamed from: d */
    public static final SortedSet<Character> m8098d(@NotNull char[] cArr) {
        cji.m5162f(cArr, "$this$toSortedSet");
        return (SortedSet) bzb.m7410c(cArr, new TreeSet());
    }

    @NotNull
    /* renamed from: b */
    public static final <T> SortedSet<T> m8130b(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(tArr, "$this$toSortedSet");
        cji.m5162f(comparator, "comparator");
        return (SortedSet) bzb.m7239e((Object[]) tArr, new TreeSet(comparator));
    }
}
