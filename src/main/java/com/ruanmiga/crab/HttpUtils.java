package com.ruanmiga.crab;

/**
 *
 * @author anton
 */
public class HttpUtils {
    
       public static final String HTTP_PROTOCOL = "http://";
       public static final String HTTPS_PROTOCOL = "https://";
       
       
       public static int getPortFromProtocol(String protocol, int port){
           if(port == -1){
               if (protocol.equalsIgnoreCase("https")) {
				return 443;
	       }
               return 80;
           }
           return port;
       }
	/**
	 * Returns {@code true} if status code indicates successful result.
	 */
	public static boolean isSuccessful(final int statusCode) {
		return statusCode < HttpStatus.HTTP_BAD_REQUEST;
	}

	/**
	 * Returns {@code true} if status code indicates a redirect.
	 */
	public static boolean isRedirect(final int statusCode) {
		return statusCode >= HttpStatus.HTTP_MOVED_PERMANENTLY && statusCode < HttpStatus.HTTP_BAD_REQUEST;
	}

	/**
	 * Returns {@code true} if status code indicates an error.
	 */
	public static boolean isError(final int statusCode) {
		return statusCode >= HttpStatus.HTTP_INTERNAL_ERROR;
	}

}
