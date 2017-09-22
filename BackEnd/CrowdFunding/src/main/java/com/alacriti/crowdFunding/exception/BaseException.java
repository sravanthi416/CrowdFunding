package com.alacriti.crowdFunding.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


public class BaseException extends Exception {
	
	protected Throwable m_innerException;
	protected String m_message = "";
	protected String m_errorCode = "";

	// TODO: USAGE: To Override in the child exceptions

	protected BaseException() {
	}

	public BaseException(String msg, Throwable th, String errorCode) {
		//super(String.noNullTrim(msg), th);
		setErrorCode(errorCode);
	}

	/*
	 * public ServerError getError() { return ErrorUtil.getError(this); }
	 */

	public String getErrorCode() {
		//log.debugPrintCurrentMethodName();
		return m_errorCode;
	}

	@Override
	public String getMessage() {
		//log.debugPrintCurrentMethodName();
		return m_message;
	}

	public Throwable getThrowable() {
		//log.debugPrintCurrentMethodName();
		return getCause();
	}

	protected void setErrorCode(String errorCode) {
		//log.debugPrintCurrentMethodName();
		//m_errorCode = StringUtil.noNullTrim(errorCode);
	}

	public String returnStackTrace() {
		//log.debugPrintCurrentMethodName();
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		this.printStackTrace(printWriter);
		return result.toString();
	}
}

