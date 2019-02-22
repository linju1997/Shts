package cn.fves24.shts.exception;

/**
 * CommonException
 *
 * @author fves
 */
public class CommonException extends Exception {

    public CommonException() {
    }

    public CommonException(String errMsg) {
        super(errMsg);
    }

    public CommonException(String errMsg, Throwable cause) {
        super(errMsg,cause);
    }
}
