package cupet.com.demo;

import cupet.com.demo.auth.ErrorCode;

public class MyCupetBootMainException extends Exception {
	
	private ErrorCode errorCode;

	public MyCupetBootMainException(ErrorCode errorCode) {
	        super(errorCode.getMessage());
	        this.errorCode = errorCode;
	    }

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
