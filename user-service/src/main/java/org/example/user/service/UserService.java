package org.example.user.service;

import org.example.feign.pojo.User;
import org.example.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * UserService
 *
 * @author Zjh
 * @date 2021/12/6 15:52
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int register(User user) {
        User user1 = findById(user.getId());
        if (null != user1) {
            return -2;
        }
        User user2 = findByName(user.getName());
        if (null != user2) {
            return -1;
        } else {
            return insertUser(user);
        }
    }

    public User login(User user) {
        return findByIdAndPassword(user);
    }

    public int changePassword(User user) {
        return updatePassword(user);
    }

    public int setInfo(User user) {
        return updateSexAndAddr(user);
    }


    public User findById(long id) {
        return userMapper.findById(id);
    }

    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public User findByIdAndPassword(User user) {
        return userMapper.findByIdAndPassword(user);
    }

    public int updatePassword(User user) {
        return userMapper.updatePassword(user);
    }

    public int updateSexAndAddr(User user) {
        return userMapper.updateSexAndAddr(user);
    }

    public BigDecimal queryMoneyById(long id) {
        return userMapper.queryMoneyById(id);
    }

    public int updateMoney(User user) {
        return userMapper.updateMoney(user);
    }

    public int updateSales(User user) {
        return userMapper.updateSales(user);
    }

    public int updateFans(User user) {
        return userMapper.updateFans(user);
    }

    public List<User> getAllUser() {
        return userMapper.getUserList();
    }

    public List<User> getOtherUsers(long id, String orderCol) {
        return userMapper.getOtherUsers(id, orderCol);
    }
}
