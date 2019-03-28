package cn.fves24.shts.common;

import cn.fves24.shts.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * 邮件服务
 *
 * @author fves
 */
@Component
public class EmailTools {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailTools(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailCode(String to, String code) throws CommonException {
        String placeholder = "${code}$";
        String resetPasswordTemplate = "你的验证码是:" + placeholder + ",验证码有效期为5分钟。";
        sendMail(to, resetPasswordTemplate.replace(placeholder, code));
    }

    private void sendMail(String to, String template) throws CommonException {
        String subject = "校园二手交易系统";
        String from = "fves24@qq.com";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(template);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new CommonException(ComMsg.CODE_SEND_FAIL.getMsg(), e);
        }
    }
}
