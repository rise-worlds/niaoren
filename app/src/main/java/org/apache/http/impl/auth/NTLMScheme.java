package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.NTCredentials;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class NTLMScheme extends AuthSchemeBase {
    private String challenge;
    private final NTLMEngine engine;
    private State state;

    /* loaded from: assets/org.apache.http.legacy.boot */
    enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public NTLMScheme(NTLMEngine engine) {
        if (engine != null) {
            this.engine = engine;
            this.state = State.UNINITIATED;
            this.challenge = null;
            return;
        }
        throw new IllegalArgumentException("NTLM engine may not be null");
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getSchemeName() {
        return "ntlm";
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getParameter(String name) {
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getRealm() {
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isConnectionBased() {
        return true;
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer buffer, int pos, int len) throws MalformedChallengeException {
        String challenge = buffer.substringTrimmed(pos, len);
        if (challenge.length() == 0) {
            if (this.state == State.UNINITIATED) {
                this.state = State.CHALLENGE_RECEIVED;
            } else {
                this.state = State.FAILED;
            }
            this.challenge = null;
            return;
        }
        this.state = State.MSG_TYPE2_RECEVIED;
        this.challenge = challenge;
    }

    @Override // org.apache.http.auth.AuthScheme
    public Header authenticate(Credentials credentials, HttpRequest request) throws AuthenticationException {
        String response;
        try {
            NTCredentials ntcredentials = (NTCredentials) credentials;
            if (this.state == State.CHALLENGE_RECEIVED || this.state == State.FAILED) {
                response = this.engine.generateType1Msg(ntcredentials.getDomain(), ntcredentials.getWorkstation());
                this.state = State.MSG_TYPE1_GENERATED;
            } else if (this.state == State.MSG_TYPE2_RECEVIED) {
                response = this.engine.generateType3Msg(ntcredentials.getUserName(), ntcredentials.getPassword(), ntcredentials.getDomain(), ntcredentials.getWorkstation(), this.challenge);
                this.state = State.MSG_TYPE3_GENERATED;
            } else {
                throw new AuthenticationException("Unexpected state: " + this.state);
            }
            CharArrayBuffer buffer = new CharArrayBuffer(32);
            if (isProxy()) {
                buffer.append("Proxy-Authorization");
            } else {
                buffer.append("Authorization");
            }
            buffer.append(": NTLM ");
            buffer.append(response);
            return new BufferedHeader(buffer);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isComplete() {
        return this.state == State.MSG_TYPE3_GENERATED || this.state == State.FAILED;
    }
}
