package cn.fves24.shts.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户反馈
 *
 * @author fves
 */
@Data
public class FeedBack {
    /**
     * 反馈ID
     */
    private int id;

    /**
     * 反馈用户ID
     */
    private int userId;

    /**
     * 反馈类型
     */
    private String type;

    /**
     * 联系类型
     */
    private String contactType;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 反馈状态
     */
    private int status;

    /**
     * 反馈时间
     */
    private Date created;
}
