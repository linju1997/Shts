package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fves
 */
public interface OrderMapper {

    @Insert("insert into order (user_id, goods_id) values (#{uid}, #{gid})")
    Integer saveOrder(@Param("uid") int uid, @Param("gid") int gid);

    @Select("select id, user_id, goods_id, created from order where user_id = #{uid} ")
    List<Order> getOrderByUid(int uid);

    @Select("select id, user_id, goods_id, created from order")
    List<Order> getOrders();
}
