package com.ruanmiga.crab;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/**
 *
 * @author anton
 */
public class Url {

    private final String baseurl;
    private final String protocol;
    private final String host;
    private final int port;
    private final String path;

      
    private static final BitSet UNRESERVED_URI_CHARS;
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    static {
        // see https://www.rfc-editor.org/rfc/rfc3986#page-13
        // and https://url.spec.whatwg.org/#application-x-www-form-urlencoded-percent-encode-set
        BitSet unreserved = new BitSet('z' + 1);
        unreserved.set('-');
        unreserved.set('.');
        for (int c = '0'; c <= '9'; ++c) unreserved.set(c);
        for (int c = 'A'; c <= 'Z'; ++c) unreserved.set(c);
        unreserved.set('_');
        for (int c = 'a'; c <= 'z'; ++c) unreserved.set(c);
        UNRESERVED_URI_CHARS = unreserved;
    }
    
    public Url(String baseurl, String protocol, String host, int port, String path) {
        this.baseurl = baseurl;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
    }
    
    public String getBaseurl() {
        return baseurl;
    }

    public String getEncodeUrl(){
        return encode(baseurl, null, false);
    }
    
    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public static String encode(String source, String allow, boolean spaceToPlus) {
        if (source == null || source.isEmpty()) {
            return source;
        }

        StringBuilder out = null;
        char ch;
        int i = 0;
        while (i < source.length()) {
            ch = source.charAt(i);
            if (isUnreservedUriChar(ch) || (allow != null && allow.indexOf(ch) != -1)) {
                if (out != null) {
                    out.append(ch);
                }
                i += 1;
            } else {
                out = startConstructingIfNeeded(out, source, i);

                int cp = source.codePointAt(i);
                if (cp < 0x80) {
                    if (spaceToPlus && ch == ' ') {
                        out.append('+');
                    } else {
                        appendUrlEncodedByte(out, cp);
                    }
                    i += 1;
                } else if (Character.isBmpCodePoint(cp)) {
                    for (int b : Character.toString(ch).getBytes(StandardCharsets.UTF_8)) {
                        appendUrlEncodedByte(out, b);
                    }
                    i += 1;
                } else if (Character.isSupplementaryCodePoint(cp)) {
                    char high = Character.highSurrogate(cp);
                    char low = Character.lowSurrogate(cp);
                    
                    for (byte b : new String(new char[]{high, low}).getBytes(StandardCharsets.UTF_8)) {
                        appendUrlEncodedByte(out, b);
                    }
                    i += 2;
                }
            }
        }

        if (out == null) {
            return source;
        }

        return out.toString();
    }

    private static void appendUrlEncodedByte(StringBuilder out, int ch) {
        out.append("%");
        appendUrlEncodedDigit(out, ch >> 4);
        appendUrlEncodedDigit(out, ch);
    }

    private static void appendUrlEncodedDigit(StringBuilder out, int digit) {
        out.append(HEX_DIGITS[digit & 0x0F]);
    }
    
    private static StringBuilder startConstructingIfNeeded(StringBuilder out, String source, int currentSourcePosition) {
        if (out == null) {
            out = new StringBuilder(source.length());
            out.append(source, 0, currentSourcePosition);
        }
        return out;
    }
    // see https://www.rfc-editor.org/rfc/rfc3986#page-13
    // and https://url.spec.whatwg.org/#application-x-www-form-urlencoded-percent-encode-set
    private static boolean isUnreservedUriChar(char ch) {
        return ch <= 'z' && UNRESERVED_URI_CHARS.get(ch);
    }
}
