package cn.fves24.shts.common;

/**
 * API响应VO
 *
 * @author fves
 */
public class RespVO {
    /**
     * data 保存 api 返回的结果
     */
    private Integer status;

    private String errMsg;

    private Object data;

    private RespVO(int status, String errMsg, Object data) {
        this.status = status;
        this.errMsg = errMsg;
        this.data = data;
    }

    public static RespVO getSuccess(ComMsg comMsg) {
        return new RespVO(comMsg.getCode(), null, comMsg.getMsg());
    }

    public static RespVO getSuccess(Object data) {
        return new RespVO(200, null, data);
    }

    public static RespVO getFail(ComMsg comMsg) {
        return new RespVO(comMsg.getCode(), comMsg.getMsg(), null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
