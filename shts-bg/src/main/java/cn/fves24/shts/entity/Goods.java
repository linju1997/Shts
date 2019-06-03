package cn.fves24.shts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 商品
 *
 * @author fves
 */
@Data
@ToString
@NoArgsConstructor
public class Goods {
    /**
     * 商品ID
     */
    private int id;
    /**
     * 出售者ID
     */
    private int userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 数量
     */
    private int quantity;
    /**
     * 价格
     */
    private float price;
    /**
     * 类型
     */
    private String type;
    /**
     * 图片
     */
    private String img;
    /**
     * 状态，出售中，交易中，已出售
     */
    private int status;
    /**
     * 出售时间
     */
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 交易完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date saleTime;

    /**
     * 出售中
     */
    public static int INSALE = 0;
    /**
     * 交易中
     */
    public static int INTRANSACTION = 1;
    /**
     * 已出售
     */
    public static int SOLD = 2;

}
