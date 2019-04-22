package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.model.FeedBack;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * FeedBackMapper
 *
 * @author fves
 */
@Mapper
@Component
public interface FeedBackMapper {

    /**
     * 插入反馈数据
     *
     * @param uid      用户id
     * @param feedBack 反馈数据
     * @return 插入结果
     */
    @Insert("insert into feedback (user_id, type, contact_type, contact, content, status) " +
            "values " +
            "(#{uid}, #{feedBack.type} ,#{feedBack.contactType} ,#{feedBack.contact} ,#{feedBack.content},0)")
    Integer insertFeedBack(@Param("uid") int uid, @Param("feedBack") FeedBack feedBack);

    /**
     * 查询反馈数据
     *
     * @return 查询结果
     */
    @Select("select id,user_id, type, contact_type, contact, content, created, status " +
            "from feedback")
    List<FeedBack> selectFeedBack();

    /**
     * 修改反馈状态
     *
     * @param id 反馈id
     * @return 修改结果
     */
    @Update("update feedback set status = 1 where id = #{id}")
    Integer updateFeedBack(int id);
}
