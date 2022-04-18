package org.example.order.mapper;

import org.example.order.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    /**
     * 创建新订单
     * @param order order
     * @return insert result
     */
    @Insert("insert into orders (id,buyerId,sellerId,time,note,status) values (#{id},#{buyerId},#{sellerId},#{time},#{note},#{status}) ")
    int insert(Order order);

    /**
     * 根据订单号查找订单
     * @param id order code
     * @return order
     */
    @Select("select * from orders where id=#{id};")
    Order findById(String id);

    /**
     * 更改时间和状态信息
     * @param order order
     * @return update result
     */
    @Update("update orders set time=#{time},status=#{status} where id=#{id}")
    int updateStatus(Order order);

    /**
     * 更改备注信息
     * @param note note
     * @param id ID
     */
    @Update("update orders set note=#{note} where id=#{id}")
    void updateNote(String note,String id);

    /**
     * 删除订单
     * @param id order code
     * @return delete result
     */
    @Delete("delete from orders where id=#{id}")
    int delete(String id);

    /**
     * 根据状态获取买家的所有订单
     * @param id 买家ID
     * @param statuses 状态集合
     * @return orders
     */
    @Select({
            "<script>",
            "select",
            "*",
            "from orders",
            "where buyerId=#{id} and status in",
            "<foreach collection='statuses' item='status' open='(' separator=',' close=')'>",
            "#{status}",
            "</foreach>",
            "</script>"
    })
    List<Order> getBuyerOrdersByStatus(long id, List<Integer> statuses);

    /**
     * 根据状态获取卖家的所有订单
     * @param id 卖家ID
     * @param statuses 状态集合
     * @return orders
     */
    @Select({
            "<script>",
            "select",
            "*",
            "from orders",
            "where sellerId=#{id} and status in",
            "<foreach collection='statuses' item='status' open='(' separator=',' close=')'>",
            "#{status}",
            "</foreach>",
            "</script>"
    })
    List<Order> getSellerOrdersByStatus(long id,List<Integer> statuses);

    /**
     * 获取所有订单信息
     * @return all orders
     */
    @Select("select * from orders order by time desc")
    List<Order> getAll();
}
