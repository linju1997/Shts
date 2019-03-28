package cn.fves24.shts.validation;

import cn.fves24.shts.common.ComMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证结果
 *
 * @author fves
 */
public class ValidationResult {
    /**
     * 存储错误
     */
    private List<ComMsg> msgs;

    ValidationResult() {
        this.msgs = new ArrayList<>(3);
    }

    void addMsg(ComMsg comMsg) {
        this.msgs.add(comMsg);
    }

    void addMsg(List<ComMsg> msgs) {
        this.msgs.addAll(msgs);
    }

    public ComMsg getErrMsg() {
        StringBuilder errMsg = new StringBuilder();
        for (ComMsg msg : msgs) {
            errMsg.append(msg.getMsg()).append(",");
        }
        return ComMsg.getFail(errMsg.toString());
    }

    public boolean isHasErrors() {
        return !msgs.isEmpty();
    }
}
