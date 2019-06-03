package cn.fves24.shts.entity;

import lombok.Data;

import java.util.Date;

/**
 * 收藏
 *
 * @author fves
 */
@Data
public class Collection {
    /**
     * 收藏id
     */
    private int id;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 商品ID
     */
    private int goodsId;
    /**
     * 收藏时间
     */
    private Date createTime;
}
