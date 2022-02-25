package com.lody.virtual.server.memory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class MemoryRegionParser {
    public static final String PATTERN = "([0-9a-f]+)-([0-9a-f]+)\\s([r-])([w-])([x-])([sp])\\s([0-9a-f]+)\\s([0-9a-f]+):([0-9a-f]+)\\s(\\d+)\\s?(.*)";
    public static final Pattern MAPS_LINE_PATTERN = Pattern.compile(PATTERN, 2);

    private static long parseHex(String str) {
        return Long.parseLong(str, 16);
    }

    private static MappedMemoryRegion parseMapLine(String str) {
        String trim = str.trim();
        Matcher matcher = MAPS_LINE_PATTERN.matcher(trim);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("The provided line does not match the pattern for /proc/$pid/maps lines. Given: %s", trim));
        } else if (matcher.groupCount() == 11) {
            return new MappedMemoryRegion(parseHex(matcher.group(1)), parseHex(matcher.group(2)), matcher.group(3).equals("r"), matcher.group(4).equals("w"), matcher.group(5).equals("x"), matcher.group(6).equals("s"), parseHex(matcher.group(7)), parseHex(matcher.group(8)), parseHex(matcher.group(9)), parseHex(matcher.group(10)), matcher.group(11));
        } else {
            throw new InternalError(String.format(Locale.ENGLISH, "Invalid group count: Found %d, but expected %d", Integer.valueOf(matcher.groupCount()), 12));
        }
    }

    public static List<MappedMemoryRegion> getMemoryRegions(int i) throws IOException {
        LinkedList linkedList = new LinkedList();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(String.format(Locale.ENGLISH, "/proc/%d/maps", Integer.valueOf(i))));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return linkedList;
            }
            MappedMemoryRegion parseMapLine = parseMapLine(readLine);
            if (parseMapLine.isReadable && parseMapLine.isWritable && !parseMapLine.description.endsWith("(deleted)")) {
                linkedList.add(parseMapLine);
            }
        }
    }
}
