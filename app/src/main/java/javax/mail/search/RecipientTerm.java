package javax.mail.search;

import javax.mail.Address;
import javax.mail.Message;

/* loaded from: classes2.dex */
public final class RecipientTerm extends AddressTerm {
    private static final long serialVersionUID = 6548700653122680468L;
    protected Message.RecipientType type;

    public RecipientTerm(Message.RecipientType recipientType, Address address) {
        super(address);
        this.type = recipientType;
    }

    public final Message.RecipientType getRecipientType() {
        return this.type;
    }

    @Override // javax.mail.search.SearchTerm
    public final boolean match(Message message) {
        try {
            Address[] recipients = message.getRecipients(this.type);
            if (recipients == null) {
                return false;
            }
            for (Address address : recipients) {
                if (super.match(address)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // javax.mail.search.AddressTerm
    public final boolean equals(Object obj) {
        return (obj instanceof RecipientTerm) && ((RecipientTerm) obj).type.equals(this.type) && super.equals(obj);
    }

    @Override // javax.mail.search.AddressTerm
    public final int hashCode() {
        return this.type.hashCode() + super.hashCode();
    }
}
