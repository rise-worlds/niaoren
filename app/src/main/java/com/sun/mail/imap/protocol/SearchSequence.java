package com.sun.mail.imap.protocol;

import com.sun.mail.iap.Argument;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.DateTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.HeaderTerm;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.NotTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.RecipientStringTerm;
import javax.mail.search.RecipientTerm;
import javax.mail.search.SearchException;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.SizeTerm;
import javax.mail.search.SubjectTerm;
import p110z1.ManyClause;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SearchSequence {
    private static String[] monthTable = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static Calendar cal = new GregorianCalendar();

    SearchSequence() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Argument generateSequence(SearchTerm searchTerm, String str) throws SearchException, IOException {
        if (searchTerm instanceof AndTerm) {
            return and((AndTerm) searchTerm, str);
        }
        if (searchTerm instanceof OrTerm) {
            return m17189or((OrTerm) searchTerm, str);
        }
        if (searchTerm instanceof NotTerm) {
            return not((NotTerm) searchTerm, str);
        }
        if (searchTerm instanceof HeaderTerm) {
            return header((HeaderTerm) searchTerm, str);
        }
        if (searchTerm instanceof FlagTerm) {
            return flag((FlagTerm) searchTerm);
        }
        if (searchTerm instanceof FromTerm) {
            return from(((FromTerm) searchTerm).getAddress().toString(), str);
        }
        if (searchTerm instanceof FromStringTerm) {
            return from(((FromStringTerm) searchTerm).getPattern(), str);
        }
        if (searchTerm instanceof RecipientTerm) {
            RecipientTerm recipientTerm = (RecipientTerm) searchTerm;
            return recipient(recipientTerm.getRecipientType(), recipientTerm.getAddress().toString(), str);
        } else if (searchTerm instanceof RecipientStringTerm) {
            RecipientStringTerm recipientStringTerm = (RecipientStringTerm) searchTerm;
            return recipient(recipientStringTerm.getRecipientType(), recipientStringTerm.getPattern(), str);
        } else if (searchTerm instanceof SubjectTerm) {
            return subject((SubjectTerm) searchTerm, str);
        } else {
            if (searchTerm instanceof BodyTerm) {
                return body((BodyTerm) searchTerm, str);
            }
            if (searchTerm instanceof SizeTerm) {
                return size((SizeTerm) searchTerm);
            }
            if (searchTerm instanceof SentDateTerm) {
                return sentdate((SentDateTerm) searchTerm);
            }
            if (searchTerm instanceof ReceivedDateTerm) {
                return receiveddate((ReceivedDateTerm) searchTerm);
            }
            if (searchTerm instanceof MessageIDTerm) {
                return messageid((MessageIDTerm) searchTerm, str);
            }
            throw new SearchException("Search too complex");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
        if (r0 == false) goto L_0x003f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
        r3 = ((javax.mail.search.AndTerm) r3).getTerms();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003f, code lost:
        r3 = ((javax.mail.search.OrTerm) r3).getTerms();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
        if (r1 < r3.length) goto L_0x004c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
        if (isAscii(r3[r1]) != false) goto L_0x0055;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isAscii(javax.mail.search.SearchTerm r3) {
        /*
        L_0x0000:
            boolean r0 = r3 instanceof javax.mail.search.AndTerm
            if (r0 != 0) goto L_0x0036
            boolean r1 = r3 instanceof javax.mail.search.OrTerm
            if (r1 == 0) goto L_0x0009
            goto L_0x0036
        L_0x0009:
            boolean r0 = r3 instanceof javax.mail.search.NotTerm
            if (r0 == 0) goto L_0x0014
            javax.mail.search.NotTerm r3 = (javax.mail.search.NotTerm) r3
            javax.mail.search.SearchTerm r3 = r3.getTerm()
            goto L_0x0000
        L_0x0014:
            boolean r0 = r3 instanceof javax.mail.search.StringTerm
            if (r0 == 0) goto L_0x0023
            javax.mail.search.StringTerm r3 = (javax.mail.search.StringTerm) r3
            java.lang.String r3 = r3.getPattern()
            boolean r3 = isAscii(r3)
            return r3
        L_0x0023:
            boolean r0 = r3 instanceof javax.mail.search.AddressTerm
            if (r0 == 0) goto L_0x004a
            javax.mail.search.AddressTerm r3 = (javax.mail.search.AddressTerm) r3
            javax.mail.Address r3 = r3.getAddress()
            java.lang.String r3 = r3.toString()
            boolean r3 = isAscii(r3)
            return r3
        L_0x0036:
            if (r0 == 0) goto L_0x003f
            javax.mail.search.AndTerm r3 = (javax.mail.search.AndTerm) r3
            javax.mail.search.SearchTerm[] r3 = r3.getTerms()
            goto L_0x0045
        L_0x003f:
            javax.mail.search.OrTerm r3 = (javax.mail.search.OrTerm) r3
            javax.mail.search.SearchTerm[] r3 = r3.getTerms()
        L_0x0045:
            r0 = 0
            r1 = 0
        L_0x0047:
            int r2 = r3.length
            if (r1 < r2) goto L_0x004c
        L_0x004a:
            r3 = 1
            return r3
        L_0x004c:
            r2 = r3[r1]
            boolean r2 = isAscii(r2)
            if (r2 != 0) goto L_0x0055
            return r0
        L_0x0055:
            int r1 = r1 + 1
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.protocol.SearchSequence.isAscii(javax.mail.search.SearchTerm):boolean");
    }

    private static boolean isAscii(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    private static Argument and(AndTerm andTerm, String str) throws SearchException, IOException {
        SearchTerm[] terms = andTerm.getTerms();
        Argument generateSequence = generateSequence(terms[0], str);
        for (int i = 1; i < terms.length; i++) {
            generateSequence.append(generateSequence(terms[i], str));
        }
        return generateSequence;
    }

    /* renamed from: or */
    private static Argument m17189or(OrTerm orTerm, String str) throws SearchException, IOException {
        SearchTerm[] terms = orTerm.getTerms();
        if (terms.length > 2) {
            OrTerm orTerm2 = terms[0];
            int i = 1;
            while (i < terms.length) {
                i++;
                orTerm2 = new OrTerm(orTerm2, terms[i]);
            }
            terms = ((OrTerm) orTerm2).getTerms();
        }
        Argument argument = new Argument();
        if (terms.length > 1) {
            argument.writeAtom(ManyClause.f23596b);
        }
        if ((terms[0] instanceof AndTerm) || (terms[0] instanceof FlagTerm)) {
            argument.writeArgument(generateSequence(terms[0], str));
        } else {
            argument.append(generateSequence(terms[0], str));
        }
        if (terms.length > 1) {
            if ((terms[1] instanceof AndTerm) || (terms[1] instanceof FlagTerm)) {
                argument.writeArgument(generateSequence(terms[1], str));
            } else {
                argument.append(generateSequence(terms[1], str));
            }
        }
        return argument;
    }

    private static Argument not(NotTerm notTerm, String str) throws SearchException, IOException {
        Argument argument = new Argument();
        argument.writeAtom("NOT");
        SearchTerm term = notTerm.getTerm();
        if ((term instanceof AndTerm) || (term instanceof FlagTerm)) {
            argument.writeArgument(generateSequence(term, str));
        } else {
            argument.append(generateSequence(term, str));
        }
        return argument;
    }

    private static Argument header(HeaderTerm headerTerm, String str) throws SearchException, IOException {
        Argument argument = new Argument();
        argument.writeAtom("HEADER");
        argument.writeString(headerTerm.getHeaderName());
        argument.writeString(headerTerm.getPattern(), str);
        return argument;
    }

    private static Argument messageid(MessageIDTerm messageIDTerm, String str) throws SearchException, IOException {
        Argument argument = new Argument();
        argument.writeAtom("HEADER");
        argument.writeString("Message-ID");
        argument.writeString(messageIDTerm.getPattern(), str);
        return argument;
    }

    private static Argument flag(FlagTerm flagTerm) throws SearchException {
        boolean testSet = flagTerm.getTestSet();
        Argument argument = new Argument();
        Flags flags = flagTerm.getFlags();
        Flags.Flag[] systemFlags = flags.getSystemFlags();
        String[] userFlags = flags.getUserFlags();
        if (systemFlags.length == 0 && userFlags.length == 0) {
            throw new SearchException("Invalid FlagTerm");
        }
        for (int i = 0; i < systemFlags.length; i++) {
            if (systemFlags[i] == Flags.Flag.DELETED) {
                argument.writeAtom(testSet ? "DELETED" : "UNDELETED");
            } else if (systemFlags[i] == Flags.Flag.ANSWERED) {
                argument.writeAtom(testSet ? "ANSWERED" : "UNANSWERED");
            } else if (systemFlags[i] == Flags.Flag.DRAFT) {
                argument.writeAtom(testSet ? "DRAFT" : "UNDRAFT");
            } else if (systemFlags[i] == Flags.Flag.FLAGGED) {
                argument.writeAtom(testSet ? "FLAGGED" : "UNFLAGGED");
            } else if (systemFlags[i] == Flags.Flag.RECENT) {
                argument.writeAtom(testSet ? "RECENT" : "OLD");
            } else if (systemFlags[i] == Flags.Flag.SEEN) {
                argument.writeAtom(testSet ? "SEEN" : "UNSEEN");
            }
        }
        for (String str : userFlags) {
            argument.writeAtom(testSet ? "KEYWORD" : "UNKEYWORD");
            argument.writeAtom(str);
        }
        return argument;
    }

    private static Argument from(String str, String str2) throws SearchException, IOException {
        Argument argument = new Argument();
        argument.writeAtom("FROM");
        argument.writeString(str, str2);
        return argument;
    }

    private static Argument recipient(Message.RecipientType recipientType, String str, String str2) throws SearchException, IOException {
        Argument argument = new Argument();
        if (recipientType == Message.RecipientType.f14650TO) {
            argument.writeAtom("TO");
        } else if (recipientType == Message.RecipientType.f14649CC) {
            argument.writeAtom("CC");
        } else if (recipientType == Message.RecipientType.BCC) {
            argument.writeAtom("BCC");
        } else {
            throw new SearchException("Illegal Recipient type");
        }
        argument.writeString(str, str2);
        return argument;
    }

    private static Argument subject(SubjectTerm subjectTerm, String str) throws SearchException, IOException {
        Argument argument = new Argument();
        argument.writeAtom("SUBJECT");
        argument.writeString(subjectTerm.getPattern(), str);
        return argument;
    }

    private static Argument body(BodyTerm bodyTerm, String str) throws SearchException, IOException {
        Argument argument = new Argument();
        argument.writeAtom("BODY");
        argument.writeString(bodyTerm.getPattern(), str);
        return argument;
    }

    private static Argument size(SizeTerm sizeTerm) throws SearchException {
        Argument argument = new Argument();
        int comparison = sizeTerm.getComparison();
        if (comparison == 2) {
            argument.writeAtom("SMALLER");
        } else if (comparison == 5) {
            argument.writeAtom("LARGER");
        } else {
            throw new SearchException("Cannot handle Comparison");
        }
        argument.writeNumber(sizeTerm.getNumber());
        return argument;
    }

    private static String toIMAPDate(Date date) {
        StringBuffer stringBuffer = new StringBuffer();
        cal.setTime(date);
        stringBuffer.append(cal.get(5));
        stringBuffer.append("-");
        stringBuffer.append(monthTable[cal.get(2)]);
        stringBuffer.append('-');
        stringBuffer.append(cal.get(1));
        return stringBuffer.toString();
    }

    private static Argument sentdate(DateTerm dateTerm) throws SearchException {
        Argument argument = new Argument();
        String iMAPDate = toIMAPDate(dateTerm.getDate());
        switch (dateTerm.getComparison()) {
            case 1:
                argument.writeAtom("OR SENTBEFORE " + iMAPDate + " SENTON " + iMAPDate);
                break;
            case 2:
                argument.writeAtom("SENTBEFORE " + iMAPDate);
                break;
            case 3:
                argument.writeAtom("SENTON " + iMAPDate);
                break;
            case 4:
                argument.writeAtom("NOT SENTON " + iMAPDate);
                break;
            case 5:
                argument.writeAtom("SENTSINCE " + iMAPDate);
                break;
            case 6:
                argument.writeAtom("OR SENTSINCE " + iMAPDate + " SENTON " + iMAPDate);
                break;
            default:
                throw new SearchException("Cannot handle Date Comparison");
        }
        return argument;
    }

    private static Argument receiveddate(DateTerm dateTerm) throws SearchException {
        Argument argument = new Argument();
        String iMAPDate = toIMAPDate(dateTerm.getDate());
        switch (dateTerm.getComparison()) {
            case 1:
                argument.writeAtom("OR BEFORE " + iMAPDate + " ON " + iMAPDate);
                break;
            case 2:
                argument.writeAtom("BEFORE " + iMAPDate);
                break;
            case 3:
                argument.writeAtom("ON " + iMAPDate);
                break;
            case 4:
                argument.writeAtom("NOT ON " + iMAPDate);
                break;
            case 5:
                argument.writeAtom("SINCE " + iMAPDate);
                break;
            case 6:
                argument.writeAtom("OR SINCE " + iMAPDate + " ON " + iMAPDate);
                break;
            default:
                throw new SearchException("Cannot handle Date Comparison");
        }
        return argument;
    }
}
