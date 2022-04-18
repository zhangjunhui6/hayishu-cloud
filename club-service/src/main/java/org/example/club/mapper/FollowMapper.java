package org.example.club.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.example.club.pojo.Follow;
import org.apache.ibatis.annotations.Select;
import org.example.feign.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FollowMapper
 *
 * @author Zjh
 * @date 2021/12/6 16:17
 **/
@Repository
public interface FollowMapper {
    @Select("select * from follow where id=#{id}")
    Follow findById(int id);

    /**
     * 查找我关注的人
     * @param id my id
     * @return followers
     */
    @Select("select  followId from follow where uid=#{id} and status=0")
    List<Long> findFollowers(long id);

    /**
     * 查找粉丝
     * @param id my id
     * @return fans
     */
    @Select("select uid from follow where followId=#{id} and status=0")
    List<Long> findFans(long id);

    /**
     * 找相互关注的人
     * @param id my id
     * @return other users
     */
    @Select("select f.followId from follow f join follow t " +
            "on f.uid=t.followId and f.followId=t.uid and f.uid=#{id} and f.status=0 and t.status=0")
    List<Long> findMutualAttention(long id);


    /**
     * Query follow is exist
     * @param follow uid and followId
     * @return follow Info
     */
    @Select("select * from follow where uid=#{uid} and followId=#{followId}")
    Follow findByUidAndFollowId(Follow follow);

    /**
     * Insert follow
     * @param follow info
     * @return insert result
     */
    @Insert("insert into follow (uid,followId,status)" +
            "values(#{uid},#{followId},#{status})")
    int insertFollow(Follow follow);

    /**
     * Change status
     * @param follow info
     * @return update result
     */
    @Update("update follow set status=#{status} where uid=#{uid} and followId=#{followId}")
    int updateFollowStatus(Follow follow);

    /**
     * 获取两个人的关系：是否关注
     * @param uid user id
     * @param followId follower id
     * @return status
     */
    @Select("select status from follow where uid=#{uid} and followId=#{followId}")
    int getStatus(long uid,long followId);
}
