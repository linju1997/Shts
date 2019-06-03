package cn.fves24.shts.router;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.entity.FeedBack;
import cn.fves24.shts.entity.User;
import cn.fves24.shts.mysql.mapper.FeedBackMapper;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户反馈相关API
 *
 * @author fves
 */
@RestController
public class FeedBackRouter {

    private FeedBackMapper feedBackMapper;
    private HttpServletRequest request;


    @Autowired
    public FeedBackRouter(FeedBackMapper feedBackMapper, HttpServletRequest request) {
        this.feedBackMapper = feedBackMapper;
        this.request = request;
    }

    @PostMapping("/feedback")
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
        } else if (contact.length() >= 5 && contact.length() <= 10) {
            feedBack.setContactType("QQ");
        } else {
            feedBack.setContactType("其他");
        }
        Integer insertFeedBack = feedBackMapper.insertFeedBack(getCurrentUser().getId(), feedBack);
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
    @PostMapping("/feedback/list")
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
    @PostMapping("/feedback/update")
    private RespVO updateFeedBackStatus(int id) {
        Integer updateFeedBackStatus = feedBackMapper.updateFeedBack(id);
        if (updateFeedBackStatus != null && updateFeedBackStatus == 1) {
            return RespVO.getSuccess(ComMsg.FEEDBACK_PROCESSED);
        }
        return RespVO.getFail(ComMsg.getFail("提交失败，请重新提交"));
    }

    private User getCurrentUser() {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
