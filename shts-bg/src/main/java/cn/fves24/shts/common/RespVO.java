package cn.fves24.shts.common;

/**
 * API响应VO
 *
 * @author fves
 */
public class RespVO {
    /**
     * 响应状态，“"success"或者”"fail"
     */
    private String status;

    /**
     * 响应状态为"success"，则该变量为响应数据
     * 响应状态为"fail",则该变量为错误信息
     */
    private Object data;

    private RespVO(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static RespVO newSuccessRespVO(Object data) {
        return new RespVO("success", data);
    }

    public static RespVO newFailRespVO(Object data) {
        return new RespVO("fail", data);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
