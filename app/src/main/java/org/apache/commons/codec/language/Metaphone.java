package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class Metaphone implements StringEncoder {
    private String vowels = "AEIOU";
    private String frontv = "EIY";
    private String varson = "CSPTG";
    private int maxCodeLen = 4;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public String metaphone(String txt) {
        char c;
        boolean hard;
        if (txt == null || txt.length() == 0) {
            return "";
        }
        if (txt.length() == 1) {
            return txt.toUpperCase();
        }
        char[] inwd = txt.toUpperCase().toCharArray();
        StringBuffer local = new StringBuffer(40);
        StringBuffer code = new StringBuffer(10);
        int n = 0;
        char c2 = inwd[0];
        char c3 = 'G';
        if (c2 != 'A') {
            if (c2 != 'G' && c2 != 'K' && c2 != 'P') {
                switch (c2) {
                    case 'W':
                        if (inwd[1] != 'R') {
                            if (inwd[1] != 'H') {
                                local.append(inwd);
                                break;
                            } else {
                                local.append(inwd, 1, inwd.length - 1);
                                local.setCharAt(0, 'W');
                                break;
                            }
                        } else {
                            local.append(inwd, 1, inwd.length - 1);
                            break;
                        }
                    case 'X':
                        inwd[0] = 'S';
                        local.append(inwd);
                        break;
                    default:
                        local.append(inwd);
                        break;
                }
            } else if (inwd[1] == 'N') {
                local.append(inwd, 1, inwd.length - 1);
            } else {
                local.append(inwd);
            }
        } else if (inwd[1] == 'E') {
            local.append(inwd, 1, inwd.length - 1);
        } else {
            local.append(inwd);
        }
        int wdsz = local.length();
        while (code.length() < getMaxCodeLen() && n < wdsz) {
            char symb = local.charAt(n);
            if (symb == 'C' || !isPreviousChar(local, n, symb)) {
                switch (symb) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                        c = 'G';
                        if (n == 0) {
                            code.append(symb);
                            break;
                        }
                        break;
                    case 'B':
                        c = 'G';
                        if (!isPreviousChar(local, n, 'M') || !isLastChar(wdsz, n)) {
                            code.append(symb);
                            break;
                        }
                        break;
                    case 'C':
                        c = 'G';
                        if (!isPreviousChar(local, n, 'S') || isLastChar(wdsz, n) || this.frontv.indexOf(local.charAt(n + 1)) < 0) {
                            if (!regionMatch(local, n, "CIA")) {
                                if (isLastChar(wdsz, n) || this.frontv.indexOf(local.charAt(n + 1)) < 0) {
                                    if (!isPreviousChar(local, n, 'S') || !isNextChar(local, n, 'H')) {
                                        if (isNextChar(local, n, 'H')) {
                                            if (n == 0 && wdsz >= 3 && isVowel(local, 2)) {
                                                code.append('K');
                                                break;
                                            } else {
                                                code.append('X');
                                                break;
                                            }
                                        } else {
                                            code.append('K');
                                            break;
                                        }
                                    } else {
                                        code.append('K');
                                        break;
                                    }
                                } else {
                                    code.append('S');
                                    break;
                                }
                            } else {
                                code.append('X');
                                break;
                            }
                        }
                        break;
                    case 'D':
                        if (!isLastChar(wdsz, n + 1)) {
                            c = 'G';
                            if (isNextChar(local, n, 'G') && this.frontv.indexOf(local.charAt(n + 2)) >= 0) {
                                code.append('J');
                                n += 2;
                                break;
                            }
                        } else {
                            c = 'G';
                        }
                        code.append('T');
                        break;
                    case 'F':
                    case 'J':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'R':
                        code.append(symb);
                        c = 'G';
                        break;
                    case 'G':
                        if ((!isLastChar(wdsz, n + 1) || !isNextChar(local, n, 'H')) && ((isLastChar(wdsz, n + 1) || !isNextChar(local, n, 'H') || isVowel(local, n + 2)) && (n <= 0 || (!regionMatch(local, n, "GN") && !regionMatch(local, n, "GNED"))))) {
                            if (isPreviousChar(local, n, 'G')) {
                                hard = true;
                            } else {
                                hard = false;
                            }
                            if (isLastChar(wdsz, n) || this.frontv.indexOf(local.charAt(n + 1)) < 0 || hard) {
                                code.append('K');
                            } else {
                                code.append('J');
                            }
                        }
                        c = 'G';
                        break;
                    case 'H':
                        if (!isLastChar(wdsz, n) && ((n <= 0 || this.varson.indexOf(local.charAt(n - 1)) < 0) && isVowel(local, n + 1))) {
                            code.append('H');
                        }
                        c = 'G';
                        break;
                    case 'K':
                        if (n <= 0) {
                            code.append(symb);
                        } else if (!isPreviousChar(local, n, 'C')) {
                            code.append(symb);
                        }
                        c = 'G';
                        break;
                    case 'P':
                        if (isNextChar(local, n, 'H')) {
                            code.append('F');
                        } else {
                            code.append(symb);
                        }
                        c = 'G';
                        break;
                    case 'Q':
                        code.append('K');
                        c = 'G';
                        break;
                    case 'S':
                        if (regionMatch(local, n, "SH") || regionMatch(local, n, "SIO") || regionMatch(local, n, "SIA")) {
                            code.append('X');
                        } else {
                            code.append('S');
                        }
                        c = 'G';
                        break;
                    case 'T':
                        if (regionMatch(local, n, "TIA") || regionMatch(local, n, "TIO")) {
                            code.append('X');
                        } else if (!regionMatch(local, n, "TCH")) {
                            if (regionMatch(local, n, "TH")) {
                                code.append('0');
                            } else {
                                code.append('T');
                            }
                        }
                        c = 'G';
                        break;
                    case 'V':
                        code.append('F');
                        c = 'G';
                        break;
                    case 'W':
                    case 'Y':
                        if (!isLastChar(wdsz, n) && isVowel(local, n + 1)) {
                            code.append(symb);
                        }
                        c = 'G';
                        break;
                    case 'X':
                        code.append('K');
                        code.append('S');
                        c = 'G';
                        break;
                    case 'Z':
                        code.append('S');
                        c = 'G';
                        break;
                    default:
                        c = 'G';
                        break;
                }
                n++;
            } else {
                n++;
                c = c3;
            }
            if (code.length() > getMaxCodeLen()) {
                code.setLength(getMaxCodeLen());
            }
            c3 = c;
        }
        return code.toString();
    }

    private boolean isVowel(StringBuffer string, int index) {
        return this.vowels.indexOf(string.charAt(index)) >= 0;
    }

    private boolean isPreviousChar(StringBuffer string, int index, char c) {
        if (index <= 0 || index >= string.length()) {
            return false;
        }
        boolean matches = string.charAt(index + (-1)) == c;
        return matches;
    }

    private boolean isNextChar(StringBuffer string, int index, char c) {
        if (index < 0) {
            return false;
        }
        boolean matches = true;
        if (index >= string.length() - 1) {
            return false;
        }
        if (string.charAt(index + 1) != c) {
            matches = false;
        }
        return matches;
    }

    private boolean regionMatch(StringBuffer string, int index, String test) {
        if (index < 0 || (test.length() + index) - 1 >= string.length()) {
            return false;
        }
        String substring = string.substring(index, test.length() + index);
        boolean matches = substring.equals(test);
        return matches;
    }

    private boolean isLastChar(int wdsz, int n) {
        return n + 1 == wdsz;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object pObject) throws EncoderException {
        if (pObject instanceof String) {
            return metaphone((String) pObject);
        }
        throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String pString) {
        return metaphone(pString);
    }

    public boolean isMetaphoneEqual(String str1, String str2) {
        return metaphone(str1).equals(metaphone(str2));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int maxCodeLen) {
        this.maxCodeLen = maxCodeLen;
    }
}
