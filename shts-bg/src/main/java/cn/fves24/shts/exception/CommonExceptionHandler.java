package cn.fves24.shts.exception;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理异常
 *
 * @author fves
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public RespVO commonExceptionHandler(CommonException e) {
        e.printStackTrace();
        // 自定义异常，给用户返回异常信息
        return RespVO.newFailRespVO(ComMsg.getFail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespVO exceptionHandler(Exception e) {
        // TODO
        // 系统运行时异常，后面采用将错误信息保存到数据库中，给用户返回一个错误码
        e.printStackTrace();
        return RespVO.newFailRespVO(ComMsg.SYSTEM_EXCEPTION);
    }
}
