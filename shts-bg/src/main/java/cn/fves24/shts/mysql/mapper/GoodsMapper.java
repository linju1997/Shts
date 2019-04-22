package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.model.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品Mapper
 *
 * @author fves
 */
@Mapper
@Component
public interface GoodsMapper {
    @Insert("insert into goods" +
            "(user_id, title, `desc`, type, price, quantity, img, status, created)" +
            " VALUES " +
            "(#{uid},#{goods.title},#{goods.desc},#{goods.type},#{goods.price},#{goods.quantity},#{goods.img},#{goods.status},current_date)")
    Integer saveGoods(@Param("uid") int uid, @Param("goods") Goods goods);

    @Select("select id,user_id, title, `desc`, type, price, quantity, img, status, created, saletime " +
            "from goods " +
            "where user_id = #{uid}")
    List<Goods> selectGoodsByUid(int uid);

    @Select("select id,user_id, title, `desc`, type, price, quantity, img, status, created, saletime " +
            "from goods " +
            "where user_id = #{id}")
    Goods selectGoodsById(int id);

    @Select("select id,user_id, title, `desc`, type, price, quantity, img, status, created, saletime " +
            "from goods ")
    List<Goods> selectGoods();

    @Update("update goods set status = status where id = #{gid} and user_id = #{uid} ")
    Integer updateGoodsStatus(@Param("uid") int uid, @Param("gid") int gid, @Param("status") int status);
}
