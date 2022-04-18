package org.example.cart.mapper;

import org.example.cart.pojo.Cart;
import org.example.cart.view.CartTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {

    /**
     * 查找某人的购物车（失效、有效）
     * @param uid uid
     * @param status status
     * @return cart list
     */
    @Select("select * where uid=#{uid} and status=#{status}")
    List<Cart> queryByUidAndStatus(long uid, int status);

    /**
     * 查找一条购物车记录
     * @param cart uid、bid
     * @return cart
     */
    @Select("select * from cart where uid=#{uid} and bid=#{bid}")
    Cart findByUidAndBid(Cart cart);

    /**
     * 增加一条购物车记录
     * @param cart cart info
     * @return insert result
     */
    @Insert("insert into cart(uid,bid,status)" +
            "values(#{uid},#{bid},#{status})")
    int insertCart(Cart cart);

    /**
     * 利用主键删除一条记录
     * @param id id
     * @return delete result
     */
    @Delete("delete from cart where id=#{id}")
    int deleteById(int id);

    /**
     * 利用主键更新一条记录的状态
     * @param cart id and status
     * @return update result
     */
    @Update("update cart set status=#{status} where id=#{id}")
    int updateCart(Cart cart);

    /*,u.name,bt.title,bt.author,bt.press,bt.price*/
    /**
     * 获取购物车界面中需要的信息
     * @param uid 用户的id
     * @param status 购物车状态
     * @return cart table
     */
    @Select("select * from cart where uid=#{uid} and status=#{status}")
    List<Cart> getMyCart(long uid,int status);

    /**
     * 当书本被卖家下架或被买家购买时，将该项置为无效
     * @param bid book ID
     */
    @Update("update cart set status=0 where bid=#{bid}")
    int deActiveCart(int bid);
}