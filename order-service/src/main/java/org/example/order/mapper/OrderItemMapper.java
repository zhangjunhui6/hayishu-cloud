package org.example.order.mapper;


import org.example.order.pojo.OrderItem;
import org.example.order.view.OrderItemInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper {
    /**
     * 增加订单项
     * @param orderItem order item
     * @return insert result
     */
    @Insert("insert into orderitem(oid,bid) " +
            "values(#{oid},#{bid})")
    int insert(OrderItem orderItem);

    /**
     * 查询订单项ID
     * @param oid 订单编号
     * @param bid 售书编号
     * @return id
     */
    @Select("select id from orderitem where oid=#{oid} and bid=#{bid}")
    int queryId(String oid,int bid);

    /**
     * 删除某一订单项
     * @param id order item ID
     * @return delete result
     */
    @Delete("delete from orderitem where id=#{id}")
    int delete(int id);

    /**
     * 删除某一订单的所有订单项
     * @param oid 订单编号
     */
    @Delete("delete from orderitem where oid=#{oid}")
    void deleteItems(String oid);

    /**
     * 根据订单编号查找订单项
     * @param oid order ID
     * @return order items
     */
    @Select("select * from orderitem where oid=#{oid}")
    List<OrderItem> findByOid(String oid);

    /**
     * 根据ID查询订单项
     * @param id ID
     * @return order item
     */
    @Select("select * from orderitem where id=#{id}")
    OrderItem findById(int id);

    /**
     * 根据订单编号查询所有订单项
     * @param oid order id
     * @return order items
     */
    @Select("select * from orderitem where oid=#{oid}")
    List<OrderItem> getAllByOid(String oid);

    /**
     * 根据书本id找到所有订单编号(书本下架相关)
     * @param bid book id
     * @return order ids
     */
    @Select("select distinct oid from orderitem where bid=#{bid}")
    List<String> findOidByBid(int bid);

    /**
     * 根据书本id删除所有订单项(书本下架相关)
     * @param bid book id
     */
    @Delete("delete from orderitem where bid=#{bid}")
    void deleteByBid(int bid);

    /**
     * 根据书本id找到除此订单项之外的订单编号(书本出售成功相关)
     * @param bid book id
     * @param id id
     * @return order ids
     */
    @Select("select distinct oid from orderitem where bid=#{bid} and id<>#{id}")
    List<String> findOidByBidAndNotId(int bid,int id);

    /**
     * 根据书本id删除除此订单项之外的所有订单项(书本出售成功相关)
     * @param bid book ID
     * @param id ID
     */
    @Delete("delete from orderitem where bid=#{bid} and id<>#{id}")
    void deleteByBidAndNotId(int bid,int id);

    /**
     * 转售该订单项中的书本
     * @param id 订单项id
     * @return 数据更新结果
     */
    @Update("update orderitem set isResale=1 where id=#{id}")
    int resale(int id);
}
