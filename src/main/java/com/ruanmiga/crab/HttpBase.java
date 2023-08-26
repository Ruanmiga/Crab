package com.ruanmiga.crab;

/**
 *
 * @author anton
 */
public abstract class HttpBase {
    
    protected Url url;
    
    public HttpBase(String baseurl){
        parseUrl(baseurl);
    }

    private void parseUrl(String url){
        int index = url.indexOf("://");
        
        String protocol = HttpUtils.HTTP_PROTOCOL;
        
        if(index != -1){
            protocol = url.substring(0, index);
        }
        
        String host = extractHostname(url);
        int port = HttpUtils.getPortFromProtocol(protocol, extractPort(url));
        
      this.url = new Url(url, protocol, host, port, "");
    }
    
    
    private String extractHostname(String url) {
        int startIdx = url.indexOf("://");
        if (startIdx != -1) {
            startIdx += 3; // Skip past "://"
        } else {
            startIdx = 0;
        }

        int endIdx = url.indexOf("/", startIdx);
        if (endIdx == -1) {
            endIdx = url.length();
        }

        String hostname = url.substring(startIdx, endIdx);
    
        int portIdx = hostname.indexOf(":");
        if (portIdx != -1) {
            hostname = hostname.substring(0, portIdx);
        }

        return hostname;
    }
    
        private int extractPort(String url) {
        int port = -1;
        int portIdx = url.indexOf(":", url.indexOf("://") + 3);
        if (portIdx != -1) {
            int slashIdx = url.indexOf("/", portIdx);
            if (slashIdx != -1) {
                try {
                    port = Integer.parseInt(url.substring(portIdx + 1, slashIdx));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    port = Integer.parseInt(url.substring(portIdx + 1));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return port;
    }

    public Url getUrl() {
        return url;
    }

}
