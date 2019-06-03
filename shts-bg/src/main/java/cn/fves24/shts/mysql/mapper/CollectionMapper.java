package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.entity.Collection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CollectionMapper {

    Integer save(@Param("email") String email,@Param("gid") int gid);

    @Select("select id, user_id, goods_id, create_time from collection where user_id = #{uid}")
    List<Collection> getCollectionsByUserId(int uid);

    @Select("select id,user_id,goods_id,create_time from collection")
    List<Collection> getCollections();

    @Delete("delete from collection where goods_id = #{gid} and user_id = #{uid} ")
    Integer delete(@Param("uid") int uid,@Param("gid") int gid);
}
