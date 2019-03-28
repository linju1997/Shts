package cn.fves24.shts.model;

import lombok.Data;

import java.util.Date;

/**
 * 收藏
 *
 * @author fves
 */
@Data
public class Collection {
    private int id;
    private int userId;
    private int goodsId;
    private Date created;
}
