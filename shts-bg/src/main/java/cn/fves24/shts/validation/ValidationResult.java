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

    public List<ComMsg> getErrMsg() {
        return this.msgs;
    }

    public boolean isHasErrors() {
        return !msgs.isEmpty();
    }
}
