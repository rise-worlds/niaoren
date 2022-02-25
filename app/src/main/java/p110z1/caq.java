package p110z1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Maps.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000~\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010(\n\u0002\u0010)\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0016\u001a\u001e\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\u001a1\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007j\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\u0087\b\u001a_\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007j\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\f\u001a1\u0010\r\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000ej\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\u000f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\u0087\b\u001a_\u0010\r\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000ej\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\u000f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0001\u001a!\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\u0087\b\u001aO\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\u0014\u001a!\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\u0087\b\u001aO\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\u0014\u001a*\u0010\u0017\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018H\u0087\n¢\u0006\u0002\u0010\u0019\u001a*\u0010\u001a\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018H\u0087\n¢\u0006\u0002\u0010\u0019\u001a9\u0010\u001b\u001a\u00020\u001c\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0087\n¢\u0006\u0002\u0010\u001f\u001a1\u0010 \u001a\u00020\u001c\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d*\u000e\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0002\b\u00030\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0087\b¢\u0006\u0002\u0010\u001f\u001a7\u0010!\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0004\"\t\b\u0001\u0010\u0005¢\u0006\u0002\b\u001d*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\"\u001a\u0002H\u0005H\u0087\b¢\u0006\u0002\u0010\u001f\u001aS\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\u0086\b\u001aG\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u001c0%H\u0086\b\u001aS\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\u0086\b\u001an\u0010(\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\u0086\b¢\u0006\u0002\u0010+\u001an\u0010,\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\u0086\b¢\u0006\u0002\u0010+\u001aG\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u001c0%H\u0086\b\u001a;\u0010.\u001a\u0004\u0018\u0001H\u0005\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0087\n¢\u0006\u0002\u0010/\u001a@\u00100\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000502H\u0087\b¢\u0006\u0002\u00103\u001a@\u00104\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000502H\u0080\b¢\u0006\u0002\u00103\u001a@\u00105\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000502H\u0086\b¢\u0006\u0002\u00103\u001a1\u00106\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0007¢\u0006\u0002\u0010/\u001a<\u00107\u001a\u0002H8\"\u0014\b\u0000\u0010)*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003*\u0002H8\"\u0004\b\u0001\u00108*\u0002H)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H802H\u0087\b¢\u0006\u0002\u00109\u001a'\u0010:\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0087\b\u001a:\u0010;\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a9\u0010<\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00180=\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0087\n\u001a<\u0010<\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050?0>\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016H\u0087\n¢\u0006\u0002\b@\u001aY\u0010A\u001a\u000e\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\u0086\b\u001at\u0010C\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108\"\u0018\b\u0003\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H8\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\u0086\b¢\u0006\u0002\u0010+\u001aY\u0010D\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H80\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\u0086\b\u001at\u0010E\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108\"\u0018\b\u0003\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H80\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\u0086\b¢\u0006\u0002\u0010+\u001a@\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0087\u0002¢\u0006\u0002\u0010G\u001aH\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\nH\u0087\u0002¢\u0006\u0002\u0010I\u001aA\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040JH\u0087\u0002\u001aA\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040KH\u0087\u0002\u001a2\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u0004H\u0087\n¢\u0006\u0002\u0010N\u001a:\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\nH\u0087\n¢\u0006\u0002\u0010O\u001a3\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040JH\u0087\n\u001a3\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040KH\u0087\n\u001a0\u0010P\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0000\u001a3\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0003H\u0087\b\u001aT\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001a\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\nH\u0086\u0002¢\u0006\u0002\u0010S\u001aG\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000bH\u0086\u0002\u001aM\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0JH\u0086\u0002\u001aI\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0014\u0010U\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0086\u0002\u001aM\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0KH\u0086\u0002\u001aJ\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u001a\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\nH\u0087\n¢\u0006\u0002\u0010W\u001a=\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000bH\u0087\n\u001aC\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0JH\u0087\n\u001a=\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0087\n\u001aC\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0KH\u0087\n\u001aG\u0010X\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u001a\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n¢\u0006\u0002\u0010W\u001a@\u0010X\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0J\u001a@\u0010X\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0K\u001a;\u0010Y\u001a\u0004\u0018\u0001H\u0005\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u0004H\u0087\b¢\u0006\u0002\u0010/\u001a:\u0010Z\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u00042\u0006\u0010\"\u001a\u0002H\u0005H\u0087\n¢\u0006\u0002\u0010[\u001a;\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n¢\u0006\u0002\u0010\u0014\u001aQ\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n2\u0006\u0010*\u001a\u0002H)¢\u0006\u0002\u0010]\u001a4\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0J\u001aO\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0J2\u0006\u0010*\u001a\u0002H)¢\u0006\u0002\u0010^\u001a2\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0007\u001aM\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)H\u0007¢\u0006\u0002\u0010_\u001a4\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0K\u001aO\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0K2\u0006\u0010*\u001a\u0002H)¢\u0006\u0002\u0010`\u001a2\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0007\u001a1\u0010b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018H\u0087\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006c"}, m8860e = {"INT_MAX_POWER_OF_TWO", "", "emptyMap", "", "K", "V", "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapCapacity", "expectedSize", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", "", "component1", "", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "contains", "", "Lkotlin/internal/OnlyInputTypes;", "key", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "containsValue", SizeSelector.SIZE_KEY, "filter", "predicate", "Lkotlin/Function1;", "filterKeys", "filterNot", "filterNotTo", "M", "destination", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "ifEmpty", "R", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "iterator", "", "", "", "mutableIterator", "mapKeys", "transform", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "Lkotlin/sequences/Sequence;", "minusAssign", "", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "pair", "map", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "kotlin-stdlib"}, m8859f = "kotlin/collections/MapsKt", m8857h = 1)
/* renamed from: z1.caq */
/* loaded from: classes3.dex */
public class caq extends MapsJVM {

    /* renamed from: a */
    private static final int f20493a = 1073741824;

    @NotNull
    /* renamed from: a */
    public static final <K, V> Map<K, V> m6470a() {
        Maps bzyVar = Maps.INSTANCE;
        if (bzyVar != null) {
            return bzyVar;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
    }

    @NotNull
    /* renamed from: b */
    public static final <K, V> Map<K, V> m6441b(@NotNull Tuples<? extends K, ? extends V>... bwoVarArr) {
        cji.m5162f(bwoVarArr, "pairs");
        return bwoVarArr.length > 0 ? can.m6453a(bwoVarArr, new LinkedHashMap(can.m6469a(bwoVarArr.length))) : can.m6470a();
    }

    @cey
    /* renamed from: b */
    private static final <K, V> Map<K, V> m6452b() {
        return can.m6470a();
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: c */
    private static final <K, V> Map<K, V> m6440c() {
        return new LinkedHashMap();
    }

    @NotNull
    /* renamed from: c */
    public static final <K, V> Map<K, V> m6429c(@NotNull Tuples<? extends K, ? extends V>... bwoVarArr) {
        cji.m5162f(bwoVarArr, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(can.m6469a(bwoVarArr.length));
        can.m6456a((Map) linkedHashMap, (Tuples[]) bwoVarArr);
        return linkedHashMap;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: d */
    private static final <K, V> HashMap<K, V> m6428d() {
        return new HashMap<>();
    }

    @NotNull
    /* renamed from: d */
    public static final <K, V> HashMap<K, V> m6421d(@NotNull Tuples<? extends K, ? extends V>... bwoVarArr) {
        cji.m5162f(bwoVarArr, "pairs");
        HashMap<K, V> hashMap = new HashMap<>(can.m6469a(bwoVarArr.length));
        can.m6456a((Map) hashMap, (Tuples[]) bwoVarArr);
        return hashMap;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final <K, V> LinkedHashMap<K, V> m6420e() {
        return new LinkedHashMap<>();
    }

    @NotNull
    /* renamed from: e */
    public static final <K, V> LinkedHashMap<K, V> m6414e(@NotNull Tuples<? extends K, ? extends V>... bwoVarArr) {
        cji.m5162f(bwoVarArr, "pairs");
        return (LinkedHashMap) can.m6453a(bwoVarArr, new LinkedHashMap(can.m6469a(bwoVarArr.length)));
    }

    @bwt
    /* renamed from: a */
    public static final int m6469a(int i) {
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return i + (i / 3);
        }
        return Integer.MAX_VALUE;
    }

    @cey
    /* renamed from: f */
    private static final <K, V> boolean m6413f(@NotNull Map<? extends K, ? extends V> map) {
        return !map.isEmpty();
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: g */
    private static final <K, V> boolean m6409g(@dbs Map<? extends K, ? extends V> map) {
        return map == null || map.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: h */
    private static final <K, V> Map<K, V> m6406h(@dbs Map<K, ? extends V> map) {
        return map != 0 ? map : can.m6470a();
    }

    /* JADX WARN: Incorrect types in method signature: <M::Ljava/util/Map<**>;:TR;R:Ljava/lang/Object;>(TM;Lz1/chc<+TR;>;)TR; */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final Object m6459a(Map map, chc chcVar) {
        return map.isEmpty() ? chcVar.invoke() : map;
    }

    @cey
    /* renamed from: d */
    private static final <K, V> boolean m6425d(@NotNull Map<? extends K, ? extends V> map, K k) {
        cji.m5162f(map, "$this$contains");
        return map.containsKey(k);
    }

    @cey
    /* renamed from: e */
    private static final <K, V> V m6417e(@NotNull Map<? extends K, ? extends V> map, K k) {
        cji.m5162f(map, "$this$get");
        return (V) map.get(k);
    }

    @cey
    /* renamed from: a */
    private static final <K, V> void m6464a(@NotNull Map<K, V> map, K k, V v) {
        cji.m5162f(map, "$this$set");
        map.put(k, v);
    }

    @cey
    /* renamed from: f */
    private static final <K> boolean m6412f(@NotNull Map<? extends K, ?> map, K k) {
        if (map != null) {
            return map.containsKey(k);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
    }

    @cey
    /* renamed from: g */
    private static final <K, V> boolean m6408g(@NotNull Map<K, ? extends V> map, V v) {
        return map.containsValue(v);
    }

    @cey
    /* renamed from: h */
    private static final <K, V> V m6405h(@NotNull Map<? extends K, V> map, K k) {
        if (map != null) {
            return (V) TypeIntrinsics.m5044t(map).remove(k);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
    }

    @cey
    /* renamed from: a */
    private static final <K, V> K m6466a(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        cji.m5162f(entry, "$this$component1");
        return (K) entry.getKey();
    }

    @cey
    /* renamed from: b */
    private static final <K, V> V m6451b(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        cji.m5162f(entry, "$this$component2");
        return (V) entry.getValue();
    }

    @cey
    /* renamed from: c */
    private static final <K, V> Tuples<K, V> m6439c(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        return new Tuples<>(entry.getKey(), entry.getValue());
    }

    @cey
    /* renamed from: c */
    private static final <K, V> V m6435c(@NotNull Map<K, ? extends V> map, K k, chc<? extends V> chcVar) {
        V v = (V) map.get(k);
        return v != null ? v : (V) chcVar.invoke();
    }

    /* renamed from: a */
    public static final <K, V> V m6463a(@NotNull Map<K, ? extends V> map, K k, @NotNull chc<? extends V> chcVar) {
        cji.m5162f(map, "$this$getOrElseNullable");
        cji.m5162f(chcVar, "defaultValue");
        V v = (V) map.get(k);
        return (v != null || map.containsKey(k)) ? v : (V) chcVar.invoke();
    }

    @bwy(m8750a = "1.1")
    /* renamed from: b */
    public static final <K, V> V m6449b(@NotNull Map<K, ? extends V> map, K k) {
        cji.m5162f(map, "$this$getValue");
        return (V) can.m6481a(map, k);
    }

    /* renamed from: b */
    public static final <K, V> V m6448b(@NotNull Map<K, V> map, K k, @NotNull chc<? extends V> chcVar) {
        cji.m5162f(map, "$this$getOrPut");
        cji.m5162f(chcVar, "defaultValue");
        V v = map.get(k);
        if (v != null) {
            return v;
        }
        V v2 = (V) chcVar.invoke();
        map.put(k, v2);
        return v2;
    }

    @cey
    /* renamed from: i */
    private static final <K, V> Iterator<Map.Entry<K, V>> m6403i(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$iterator");
        return (Iterator<Map.Entry<? extends K, ? extends V>>) map.entrySet().iterator();
    }

    @cey
    @cgo(m5270a = "mutableIterator")
    /* renamed from: j */
    private static final <K, V> Iterator<Map.Entry<K, V>> m6401j(@NotNull Map<K, V> map) {
        cji.m5162f(map, "$this$iterator");
        return map.entrySet().iterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: a */
    public static final <K, V, R, M extends Map<? super K, ? super R>> M m6461a(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapValuesTo");
        cji.m5162f(m, "destination");
        cji.m5162f(chdVar, "transform");
        for (Object obj : map.entrySet()) {
            m.put(((Map.Entry) obj).getKey(), chdVar.invoke(obj));
        }
        return m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: b */
    public static final <K, V, R, M extends Map<? super R, ? super V>> M m6446b(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapKeysTo");
        cji.m5162f(m, "destination");
        cji.m5162f(chdVar, "transform");
        for (Object obj : map.entrySet()) {
            m.put(chdVar.invoke(obj), ((Map.Entry) obj).getValue());
        }
        return m;
    }

    /* renamed from: a */
    public static final <K, V> void m6456a(@NotNull Map<? super K, ? super V> map, @NotNull Tuples<? extends K, ? extends V>[] bwoVarArr) {
        cji.m5162f(map, "$this$putAll");
        cji.m5162f(bwoVarArr, "pairs");
        for (Tuples<? extends K, ? extends V> bwoVar : bwoVarArr) {
            map.put((Object) bwoVar.component1(), (Object) bwoVar.component2());
        }
    }

    /* renamed from: a */
    public static final <K, V> void m6465a(@NotNull Map<? super K, ? super V> map, @NotNull Iterable<? extends Tuples<? extends K, ? extends V>> iterable) {
        cji.m5162f(map, "$this$putAll");
        cji.m5162f(iterable, "pairs");
        Iterator<? extends Tuples<? extends K, ? extends V>> it = iterable.iterator();
        while (it.hasNext()) {
            Tuples bwoVar = (Tuples) it.next();
            map.put((Object) bwoVar.component1(), (Object) bwoVar.component2());
        }
    }

    /* renamed from: a */
    public static final <K, V> void m6458a(@NotNull Map<? super K, ? super V> map, @NotNull Sequence<? extends Tuples<? extends K, ? extends V>> cobVar) {
        cji.m5162f(map, "$this$putAll");
        cji.m5162f(cobVar, "pairs");
        Iterator<? extends Tuples<? extends K, ? extends V>> a = cobVar.mo3707a();
        while (a.hasNext()) {
            Tuples bwoVar = (Tuples) a.next();
            map.put((Object) bwoVar.component1(), (Object) bwoVar.component2());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: c */
    public static final <K, V, R> Map<K, R> m6432c(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapValues");
        cji.m5162f(chdVar, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(can.m6469a(map.size()));
        for (Object obj : map.entrySet()) {
            linkedHashMap.put(((Map.Entry) obj).getKey(), chdVar.invoke(obj));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: d */
    public static final <K, V, R> Map<R, V> m6423d(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, ? extends R> chdVar) {
        cji.m5162f(map, "$this$mapKeys");
        cji.m5162f(chdVar, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(can.m6469a(map.size()));
        for (Object obj : map.entrySet()) {
            linkedHashMap.put(chdVar.invoke(obj), ((Map.Entry) obj).getValue());
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: e */
    public static final <K, V> Map<K, V> m6416e(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super K, Boolean> chdVar) {
        cji.m5162f(map, "$this$filterKeys");
        cji.m5162f(chdVar, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke((Object) entry.getKey()).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: f */
    public static final <K, V> Map<K, V> m6411f(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super V, Boolean> chdVar) {
        cji.m5162f(map, "$this$filterValues");
        cji.m5162f(chdVar, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke((Object) entry.getValue()).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: c */
    public static final <K, V, M extends Map<? super K, ? super V>> M m6433c(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$filterTo");
        cji.m5162f(m, "destination");
        cji.m5162f(chdVar, "predicate");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke(entry).booleanValue()) {
                m.put(entry.getKey(), entry.getValue());
            }
        }
        return m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: g */
    public static final <K, V> Map<K, V> m6407g(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$filter");
        cji.m5162f(chdVar, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (chdVar.invoke(entry).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: d */
    public static final <K, V, M extends Map<? super K, ? super V>> M m6424d(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$filterNotTo");
        cji.m5162f(m, "destination");
        cji.m5162f(chdVar, "predicate");
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (!chdVar.invoke(entry).booleanValue()) {
                m.put(entry.getKey(), entry.getValue());
            }
        }
        return m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: h */
    public static final <K, V> Map<K, V> m6404h(@NotNull Map<? extends K, ? extends V> map, @NotNull chd<? super Map.Entry<? extends K, ? extends V>, Boolean> chdVar) {
        cji.m5162f(map, "$this$filterNot");
        cji.m5162f(chdVar, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (!chdVar.invoke(entry).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V> Map<K, V> m6468a(@NotNull Iterable<? extends Tuples<? extends K, ? extends V>> iterable) {
        cji.m5162f(iterable, "$this$toMap");
        if (!(iterable instanceof Collection)) {
            return can.m6419e(can.m6467a(iterable, new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        switch (collection.size()) {
            case 0:
                return can.m6470a();
            case 1:
                return can.m6475a((Tuples) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
            default:
                return can.m6467a(iterable, new LinkedHashMap(can.m6469a(collection.size())));
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V, M extends Map<? super K, ? super V>> M m6467a(@NotNull Iterable<? extends Tuples<? extends K, ? extends V>> iterable, @NotNull M m) {
        cji.m5162f(iterable, "$this$toMap");
        cji.m5162f(m, "destination");
        can.m6465a((Map) m, (Iterable) iterable);
        return m;
    }

    @NotNull
    /* renamed from: f */
    public static final <K, V> Map<K, V> m6410f(@NotNull Tuples<? extends K, ? extends V>[] bwoVarArr) {
        cji.m5162f(bwoVarArr, "$this$toMap");
        switch (bwoVarArr.length) {
            case 0:
                return can.m6470a();
            case 1:
                return can.m6475a(bwoVarArr[0]);
            default:
                return can.m6453a(bwoVarArr, new LinkedHashMap(can.m6469a(bwoVarArr.length)));
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V, M extends Map<? super K, ? super V>> M m6453a(@NotNull Tuples<? extends K, ? extends V>[] bwoVarArr, @NotNull M m) {
        cji.m5162f(bwoVarArr, "$this$toMap");
        cji.m5162f(m, "destination");
        can.m6456a((Map) m, (Tuples[]) bwoVarArr);
        return m;
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V> Map<K, V> m6455a(@NotNull Sequence<? extends Tuples<? extends K, ? extends V>> cobVar) {
        cji.m5162f(cobVar, "$this$toMap");
        return can.m6419e(can.m6454a(cobVar, new LinkedHashMap()));
    }

    @NotNull
    /* renamed from: a */
    public static final <K, V, M extends Map<? super K, ? super V>> M m6454a(@NotNull Sequence<? extends Tuples<? extends K, ? extends V>> cobVar, @NotNull M m) {
        cji.m5162f(cobVar, "$this$toMap");
        cji.m5162f(m, "destination");
        can.m6458a((Map) m, (Sequence) cobVar);
        return m;
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: c */
    public static final <K, V> Map<K, V> m6438c(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$toMap");
        switch (map.size()) {
            case 0:
                return can.m6470a();
            case 1:
                return can.m6473b(map);
            default:
                return can.m6427d(map);
        }
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: d */
    public static final <K, V> Map<K, V> m6427d(@NotNull Map<? extends K, ? extends V> map) {
        cji.m5162f(map, "$this$toMutableMap");
        return new LinkedHashMap(map);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <K, V, M extends Map<? super K, ? super V>> M m6462a(@NotNull Map<? extends K, ? extends V> map, @NotNull M m) {
        cji.m5162f(map, "$this$toMap");
        cji.m5162f(m, "destination");
        m.putAll(map);
        return m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: a */
    public static final <K, V> Map<K, V> m6460a(@NotNull Map<? extends K, ? extends V> map, @NotNull Tuples<? extends K, ? extends V> bwoVar) {
        cji.m5162f(map, "$this$plus");
        cji.m5162f(bwoVar, "pair");
        if (map.isEmpty()) {
            return can.m6475a(bwoVar);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(bwoVar.getFirst(), bwoVar.getSecond());
        return linkedHashMap;
    }

    @NotNull
    /* renamed from: b */
    public static final <K, V> Map<K, V> m6450b(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends Tuples<? extends K, ? extends V>> iterable) {
        cji.m5162f(map, "$this$plus");
        cji.m5162f(iterable, "pairs");
        if (map.isEmpty()) {
            return can.m6468a(iterable);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        can.m6465a((Map) linkedHashMap, (Iterable) iterable);
        return linkedHashMap;
    }

    @NotNull
    /* renamed from: b */
    public static final <K, V> Map<K, V> m6442b(@NotNull Map<? extends K, ? extends V> map, @NotNull Tuples<? extends K, ? extends V>[] bwoVarArr) {
        cji.m5162f(map, "$this$plus");
        cji.m5162f(bwoVarArr, "pairs");
        if (map.isEmpty()) {
            return can.m6410f(bwoVarArr);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        can.m6456a((Map) linkedHashMap, (Tuples[]) bwoVarArr);
        return linkedHashMap;
    }

    @NotNull
    /* renamed from: b */
    public static final <K, V> Map<K, V> m6444b(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends Tuples<? extends K, ? extends V>> cobVar) {
        cji.m5162f(map, "$this$plus");
        cji.m5162f(cobVar, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        can.m6458a((Map) linkedHashMap, (Sequence) cobVar);
        return can.m6419e(linkedHashMap);
    }

    @NotNull
    /* renamed from: b */
    public static final <K, V> Map<K, V> m6447b(@NotNull Map<? extends K, ? extends V> map, @NotNull Map<? extends K, ? extends V> map2) {
        cji.m5162f(map, "$this$plus");
        cji.m5162f(map2, "map");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    @cey
    /* renamed from: b */
    private static final <K, V> void m6445b(@NotNull Map<? super K, ? super V> map, Tuples<? extends K, ? extends V> bwoVar) {
        cji.m5162f(map, "$this$plusAssign");
        map.put((Object) bwoVar.getFirst(), (Object) bwoVar.getSecond());
    }

    @cey
    /* renamed from: d */
    private static final <K, V> void m6426d(@NotNull Map<? super K, ? super V> map, Iterable<? extends Tuples<? extends K, ? extends V>> iterable) {
        cji.m5162f(map, "$this$plusAssign");
        can.m6465a((Map) map, (Iterable) iterable);
    }

    @cey
    /* renamed from: c */
    private static final <K, V> void m6430c(@NotNull Map<? super K, ? super V> map, Tuples<? extends K, ? extends V>[] bwoVarArr) {
        cji.m5162f(map, "$this$plusAssign");
        can.m6456a((Map) map, (Tuples[]) bwoVarArr);
    }

    @cey
    /* renamed from: d */
    private static final <K, V> void m6422d(@NotNull Map<? super K, ? super V> map, Sequence<? extends Tuples<? extends K, ? extends V>> cobVar) {
        cji.m5162f(map, "$this$plusAssign");
        can.m6458a((Map) map, (Sequence) cobVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: c */
    private static final <K, V> void m6434c(@NotNull Map<? super K, ? super V> map, Map<K, ? extends V> map2) {
        cji.m5162f(map, "$this$plusAssign");
        map.putAll(map2);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: c */
    public static final <K, V> Map<K, V> m6436c(@NotNull Map<? extends K, ? extends V> map, K k) {
        cji.m5162f(map, "$this$minus");
        Map d = can.m6427d(map);
        d.remove(k);
        return can.m6419e(d);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: c */
    public static final <K, V> Map<K, V> m6437c(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends K> iterable) {
        cji.m5162f(map, "$this$minus");
        cji.m5162f(iterable, "keys");
        Map d = can.m6427d(map);
        bzk.m6749b((Collection) d.keySet(), (Iterable) iterable);
        return can.m6419e(d);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <K, V> Map<K, V> m6457a(@NotNull Map<? extends K, ? extends V> map, @NotNull K[] kArr) {
        cji.m5162f(map, "$this$minus");
        cji.m5162f(kArr, "keys");
        Map d = can.m6427d(map);
        bzk.m6745b((Collection) d.keySet(), (Object[]) kArr);
        return can.m6419e(d);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: c */
    public static final <K, V> Map<K, V> m6431c(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends K> cobVar) {
        cji.m5162f(map, "$this$minus");
        cji.m5162f(cobVar, "keys");
        Map d = can.m6427d(map);
        bzk.m6746b((Collection) d.keySet(), (Sequence) cobVar);
        return can.m6419e(d);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: i */
    private static final <K, V> void m6402i(@NotNull Map<K, V> map, K k) {
        cji.m5162f(map, "$this$minusAssign");
        map.remove(k);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final <K, V> void m6418e(@NotNull Map<K, V> map, Iterable<? extends K> iterable) {
        cji.m5162f(map, "$this$minusAssign");
        bzk.m6749b((Collection) map.keySet(), (Iterable) iterable);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final <K, V> void m6443b(@NotNull Map<K, V> map, K[] kArr) {
        cji.m5162f(map, "$this$minusAssign");
        bzk.m6745b((Collection) map.keySet(), (Object[]) kArr);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final <K, V> void m6415e(@NotNull Map<K, V> map, Sequence<? extends K> cobVar) {
        cji.m5162f(map, "$this$minusAssign");
        bzk.m6746b((Collection) map.keySet(), (Sequence) cobVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: e */
    public static final <K, V> Map<K, V> m6419e(@NotNull Map<K, ? extends V> map) {
        cji.m5162f(map, "$this$optimizeReadOnlyMap");
        switch (map.size()) {
            case 0:
                return can.m6470a();
            case 1:
                return can.m6473b(map);
            default:
                return map;
        }
    }
}
