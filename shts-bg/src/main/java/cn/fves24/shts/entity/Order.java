package cn.fves24.shts.entity;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 * 出售者修改商品的状态为已交易，就触发修改订单表为交易完成
 */
@Data
public class Order {
    /**
     * 订单ID
     */
    private int id;
    /**
     * 用户ID
     */
    private int uid;
    /**
     * 商品ID
     */
    private int gid;
    /**
     * 订单状态
     */
    private int status;

    /**
     * 交易中
     */
    public static int INTRANSACTION = 0;

    /**
     * 交易完成
     */
    public static int COMPLETE = 1;

    private Date createTime;
}
