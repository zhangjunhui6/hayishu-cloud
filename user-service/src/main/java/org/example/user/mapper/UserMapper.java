package org.example.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.feign.pojo.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * UserMapper
 *
 * @author Zjh
 * @date 2021/12/6 15:51
 **/
@Repository
public interface UserMapper {
    /**
     * Query users based on user id
     * @param id user id
     * @return user info
     */
    @Select("select * from user where id=#{id}")
    User findById(long id);

    /**
     * Query user by name
     * @param name user name
     * @return user info
     */
    @Select("select * from user where name=#{name}")
    User findByName(String name);

    /**
     *Insert new user
     * @param user user base info
     * @return insert result
     */
    @Insert("insert into user (id,name,password,sex,identity,addr,money,sales,fans) "
            + "values(#{id},#{name},#{password},#{sex},#{identity},#{addr},#{money},#{sales},#{fans})")
    int insertUser(User user);

    /**
     * Login
     * @param user id and password
     * @return user info
     */
    @Select("select * from user where id=#{id} and password=#{password}")
    User findByIdAndPassword(User user);

    /**
     * ChangePassword
     * @param user id and new password
     * @return update result
     */
    @Update("update user set password=#{password} where id=#{id}")
    int updatePassword(User user);

    /**
     * Change base info
     * @param user id name sex and addr
     * @return update result
     */
    @Update("update user set sex=#{sex},addr=#{addr} where id=#{id}")
    int updateSexAndAddr(User user);

    /**
     * Query Money
     * @param id user ID
     * @return money
     */
    @Select("select money from user where id=#{id}")
    BigDecimal queryMoneyById(long id);

    /**
     * Change money
     * @param user id and money
     * @return status
     */
    @Update("update user set money=#{money} where id=#{id}")
    int updateMoney(User user);

    /**
     * Change sales
     * @param user id and sales
     * @return status
     */
    @Update("update user set sales=#{sales} where id=#{id}")
    int updateSales(User user);

    /**
     * Change fans
     * @param user id and fans
     * @return status
     */
    @Update("update user set fans=#{fans} where id=#{id}")
    int updateFans(User user);

    /**
     * 获取用户列表
     * @return userList
     */
    @Select("select id,name,sex,time from user where identity=1")
    List<User> getUserList();

    @Select("select id,name,sex,sales,fans from user " +
            "where id<>#{id} order by ${sort} desc")
    List<User> getOtherUsers(long id, String sort);
}
