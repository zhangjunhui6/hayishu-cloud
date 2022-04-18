package org.example.club.service;

import org.example.club.mapper.FollowMapper;
import org.example.club.pojo.Follow;
import org.example.feign.clients.UserClients;
import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * FollowService
 *
 * @author Zjh
 * @date 2021/12/6 16:22
 **/
@Service
public class FollowService {
    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserClients userClients;

    public List<User> findFollowers(long id) {
        List<Long> ids = followMapper.findFollowers(id);
        ids.removeAll(followMapper.findMutualAttention(id));
        List<User> list = new ArrayList<>();
        for (long uid:ids) {
            User user= userClients.getInfo(uid);
            list.add(user);
        }
        return list.isEmpty() ? null : list;
    }

    public List<User> findFans(long id) {
        List<Long> ids = followMapper.findFans(id);
        ids.removeAll(followMapper.findMutualAttention(id));
        List<User> list = new ArrayList<>();
        for (long uid:ids) {
            User user= userClients.getInfo(uid);
            list.add(user);
        }
        return list.isEmpty() ? null : list;
    }

    public List<User> findMutualAttention(long id) {
        List<Long> ids = followMapper.findMutualAttention(id);
        List<User> list = new ArrayList<>();
        for (long uid:ids) {
            User user= userClients.getInfo(uid);
            list.add(user);
        }
        return list.isEmpty() ? null : list;
    }

    public List<User> findLatestRegister(long id) {
        // 1.获取按注册时间排序的其它用户列表
        List<User> users = userClients.getOtherUsers(id, "time");
        // 2.从中去除掉我关注的人
        List<Long> ids = followMapper.findFollowers(id);
        users.removeIf(user -> ids.contains(user.getId()));
        return users.isEmpty() ? null : users;
    }

    public List<User> findMostFans(long id) {
        // 1.获取按粉丝数量排序的其它用户列表
        List<User> users = userClients.getOtherUsers(id, "fans");
        // 2.从中去除掉我关注的人
        List<Long> ids = followMapper.findFollowers(id);
        users.removeIf(user -> ids.contains(user.getId()));
        return users.isEmpty() ? null : users;
    }

    public List<User> findMostSales(long id) {
        // 1.获取按销售数量排序的其它用户列表
        List<User> users = userClients.getOtherUsers(id, "sales");
        // 2.从中去除掉我关注的人
        List<Long> ids = followMapper.findFollowers(id);
        users.removeIf(user -> ids.contains(user.getId()));
        return users.isEmpty() ? null : users;
    }

    public List<User> getOtherUsers(long id, int index) {
        List<User> list;
        switch (index){
            case 1:
                list = findLatestRegister(id);
                break;
            case 2:
                list = findMostFans(id);
                break;
            case 3:
                list = findMostSales(id);
                break;
            case 4:
                list = findFollowers(id);
                break;
            case 5:
                list =findFans(id);
                break;
            case 6:
                list = findMutualAttention(id);
                break;
            default:
                list = null;
        }
        return list;
    }

    public List<User> queryOtherUsers(long id, int index, String keyword) {
        List<User> list = getOtherUsers(id, index);
        if (null != list && !"".equals(keyword)){
            list.removeIf(user -> !user.getName().contains(keyword));
        }
        return list==null||list.isEmpty() ? null : list;
    }

    public int getStatus(long uid,long followId) {
        Follow follow = new Follow(0, uid, followId, 0);
        if (null == findByUidAndFollowId(follow)) {
            return 1;
        }
        return followMapper.getStatus(uid, followId);
    }

    public int updateFollowStatus(Follow follow) {
        return followMapper.updateFollowStatus(follow);
    }

    public int insertFollow(Follow follow) {
        return followMapper.insertFollow(follow);
    }

    public Follow findByUidAndFollowId(Follow follow) {
        return followMapper.findByUidAndFollowId(follow);
    }

    public Result changeFollowStatus(Follow follow) {
        int res;
        if (null != findByUidAndFollowId(follow)){
            res = updateFollowStatus(follow);
        } else {
            res = insertFollow(follow);
        }

        if (res == 1){
            /*成功操作*/
            User user = userClients.getInfo(follow.getFollowId());
            int fans = follow.getStatus() == 1
                    ? user.getFans() - 1 : user.getFans() + 1;
            String successMessage = follow.getStatus()  == 1
                    ? "取消关注成功" : "关注成功";
            user.setFans(fans);
            userClients.setFans(user);
            return ResultUtil.buildSuccessResult(successMessage,null);
        } else {
            String failMessage = follow.getStatus()  == 1
                    ? "取消关注失败" : "关注失败";
            return ResultUtil.buildFailResult(failMessage,null);
        }
    }
}
