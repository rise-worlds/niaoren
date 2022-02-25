package javax.mail;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

/* loaded from: classes2.dex */
public abstract class Transport extends Service {
    private Vector transportListeners = null;

    public abstract void sendMessage(Message message, Address[] addressArr) throws MessagingException;

    public Transport(Session session, URLName uRLName) {
        super(session, uRLName);
    }

    public static void send(Message message) throws MessagingException {
        message.saveChanges();
        send0(message, message.getAllRecipients());
    }

    public static void send(Message message, Address[] addressArr) throws MessagingException {
        message.saveChanges();
        send0(message, addressArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    private static void send0(Message message, Address[] addressArr) throws MessagingException {
        Session session;
        Address[] addressArr2;
        Address[] addressArr3;
        Address[] addressArr4;
        if (addressArr == null || addressArr.length == 0) {
            throw new SendFailedException("No recipient addresses");
        }
        Hashtable hashtable = new Hashtable();
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < addressArr.length; i++) {
            if (hashtable.containsKey(addressArr[i].getType())) {
                ((Vector) hashtable.get(addressArr[i].getType())).addElement(addressArr[i]);
            } else {
                Vector vector4 = new Vector();
                vector4.addElement(addressArr[i]);
                hashtable.put(addressArr[i].getType(), vector4);
            }
        }
        int size = hashtable.size();
        if (size != 0) {
            if (message.session != null) {
                session = message.session;
            } else {
                session = Session.getDefaultInstance(System.getProperties(), null);
            }
            if (size == 1) {
                Transport transport = session.getTransport(addressArr[0]);
                try {
                    transport.connect();
                    transport.sendMessage(message, addressArr);
                } finally {
                }
            } else {
                Enumeration elements = hashtable.elements();
                MessagingException messagingException = null;
                boolean z = false;
                while (elements.hasMoreElements()) {
                    Vector vector5 = (Vector) elements.nextElement();
                    Address[] addressArr5 = new Address[vector5.size()];
                    vector5.copyInto(addressArr5);
                    Transport transport2 = session.getTransport(addressArr5[0]);
                    if (transport2 == null) {
                        for (Address address : addressArr5) {
                            vector.addElement(address);
                        }
                    } else {
                        try {
                            try {
                                transport2.connect();
                                transport2.sendMessage(message, addressArr5);
                            } catch (SendFailedException e) {
                                if (messagingException == null) {
                                    messagingException = e;
                                } else {
                                    messagingException.setNextException(e);
                                }
                                Address[] invalidAddresses = e.getInvalidAddresses();
                                if (invalidAddresses != null) {
                                    for (Address address2 : invalidAddresses) {
                                        vector.addElement(address2);
                                    }
                                }
                                Address[] validSentAddresses = e.getValidSentAddresses();
                                if (validSentAddresses != null) {
                                    for (Address address3 : validSentAddresses) {
                                        vector2.addElement(address3);
                                    }
                                }
                                Address[] validUnsentAddresses = e.getValidUnsentAddresses();
                                messagingException = messagingException;
                                if (validUnsentAddresses != null) {
                                    for (Address address4 : validUnsentAddresses) {
                                        vector3.addElement(address4);
                                    }
                                    messagingException = messagingException;
                                }
                                transport2.close();
                                z = true;
                            } catch (MessagingException e2) {
                                if (messagingException == 0) {
                                    messagingException = e2;
                                } else {
                                    messagingException.setNextException(e2);
                                    messagingException = messagingException;
                                }
                                transport2.close();
                                z = true;
                            }
                        } finally {
                        }
                    }
                }
                if (!(!z && vector.size() == 0 && vector3.size() == 0)) {
                    if (vector2.size() > 0) {
                        Address[] addressArr6 = new Address[vector2.size()];
                        vector2.copyInto(addressArr6);
                        addressArr2 = addressArr6;
                    } else {
                        addressArr2 = null;
                    }
                    if (vector3.size() > 0) {
                        Address[] addressArr7 = new Address[vector3.size()];
                        vector3.copyInto(addressArr7);
                        addressArr3 = addressArr7;
                    } else {
                        addressArr3 = null;
                    }
                    if (vector.size() > 0) {
                        Address[] addressArr8 = new Address[vector.size()];
                        vector.copyInto(addressArr8);
                        addressArr4 = addressArr8;
                    } else {
                        addressArr4 = null;
                    }
                    throw new SendFailedException("Sending failed", messagingException, addressArr2, addressArr3, addressArr4);
                }
            }
        } else {
            throw new SendFailedException("No recipient addresses");
        }
    }

    public synchronized void addTransportListener(TransportListener transportListener) {
        if (this.transportListeners == null) {
            this.transportListeners = new Vector();
        }
        this.transportListeners.addElement(transportListener);
    }

    public synchronized void removeTransportListener(TransportListener transportListener) {
        if (this.transportListeners != null) {
            this.transportListeners.removeElement(transportListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyTransportListeners(int i, Address[] addressArr, Address[] addressArr2, Address[] addressArr3, Message message) {
        if (this.transportListeners != null) {
            queueEvent(new TransportEvent(this, i, addressArr, addressArr2, addressArr3, message), this.transportListeners);
        }
    }
}
