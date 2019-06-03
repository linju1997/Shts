package cn.fves24.shts.dto;

import cn.fves24.shts.entity.Goods;
import lombok.Data;

@Data
public class GoodsInfo {
    private String user;
    private Goods goods;
}
