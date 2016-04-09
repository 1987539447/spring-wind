// Copyright (C) 1998-2001 by Jason Hunter <jhunter_AT_acm_DOT_org>.
// All rights reserved.  Use of this class is limited.
// Please see the LICENSE for more information.

package com.baomidou.framework.upload.cos;

/**
 * Thrown to indicate a parameter does not exist.
 *
 * @see com.baomidou.framework.upload.cos.ParameterParser
 *
 * @author <b>Jason Hunter</b>, Copyright &#169; 1998
 * @version 1.0, 98/09/18
 */
public class ParameterNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ParameterNotFoundException with no detail message.
	 */
	public ParameterNotFoundException() {
		super();
	}

	/**
	 * Constructs a new ParameterNotFoundException with the specified detail
	 * message.
	 *
	 * @param s
	 *            the detail message
	 */
	public ParameterNotFoundException(String s) {
		super(s);
	}
}