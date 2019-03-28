package cn.fves24.shts.model;

import lombok.Data;

/**
 * 用户反馈
 *
 * @author fves
 */
@Data
public class FeedBack {
    private int id;
    private String type;
    private String contactType;
    private String contact;
    private String content;
    private int status;
}
