package cn.fves24.shts.router;

import cn.fves24.shts.auth.Authentication;
import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.model.FeedBack;
import cn.fves24.shts.mysql.mapper.FeedBackMapper;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 用户反馈相关API
 *
 * @author fves
 */
@RestController
@RequestMapping("/api/v1")
public class FeedBackRouter {

    private FeedBackMapper feedBackMapper;


    @Autowired
    public FeedBackRouter(FeedBackMapper feedBackMapper) {
        this.feedBackMapper = feedBackMapper;
    }

    @Authentication
    @RequestMapping("/feedback")
    private RespVO feedBack(FeedBack feedBack) {
        ValidationResult result = Validation.validateFeedBackParams(feedBack);
        if (result.isHasErrors()) {
            return RespVO.getFail(result.getErrMsg());
        }
        String contact = feedBack.getContact();
        if (contact.contains("@")) {
            feedBack.setContactType("邮箱");
        } else if (contact.length() == 11) {
            feedBack.setContactType("手机号码");
        } else if (contact.length() <= 10) {
            feedBack.setContactType("QQ");
        } else {
            feedBack.setContactType("其他");
        }
        Integer insertFeedBack = feedBackMapper.insertFeedBack(feedBack);
        if (insertFeedBack != null && insertFeedBack == 1) {
            return RespVO.getSuccess(ComMsg.FEEDBACK_SUCCESS);
        }
        return RespVO.getFail(ComMsg.FEEDBACK_FAIL);
    }

    /**
     * 获取所有，获取反馈数据
     *
     * @return 反馈结果
     */
    @Authentication
    @PostMapping("/feedback/1")
    private RespVO selectFeedBacks() {
        List<FeedBack> feedBacks = feedBackMapper.selectFeedBack();
        return RespVO.getSuccess(feedBacks);
    }

    /**
     * 更新反馈信息
     *
     * @param id 反馈ID
     * @return 更新结果
     */
    @Authentication
    @PostMapping("/feedback/update")
    private RespVO updateFeedBackStatus(int id) {
        Integer updateFeedBackStatus = feedBackMapper.updateFeedBack(id);
        if (updateFeedBackStatus != null && updateFeedBackStatus == 1) {
            return RespVO.getSuccess(ComMsg.FEEDBACK_PROCESSED);
        }
        return RespVO.getFail(ComMsg.getFail("提交失败，请重新提交"));
    }
}
