package com.ruanmiga.crab;

/**
 *
 * @author anton
 */
public class HttpUtils {
    
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
