package org.example.order.service;

import org.example.feign.clients.BookClients;
import org.example.feign.pojo.Book;
import org.example.feign.pojo.BookTemplate;
import org.example.order.mapper.OrderMapper;
import org.example.order.mapper.OrderItemMapper;
import org.example.feign.clients.UserClients;
import org.example.order.pojo.Order;
import org.example.order.pojo.OrderItem;
import org.example.order.view.OrderItemInfo;
import org.example.order.view.OrderInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UserClients userClients;

    @Autowired
    private BookClients bookClients;

    public int insertOrder(Order order){
        return orderMapper.insert(order);
    }

    public int insertOrderItem(OrderItem orderItem){
        return orderItemMapper.insert(orderItem);
    }

    public int queryItemId(String oid,int bid){
        return orderItemMapper.queryId(oid,bid);
    }

    public Order findOrderById(String id){
        return orderMapper.findById(id);
    }

    public Boolean orderIsExist(String id){
        return null != findOrderById(id);
    }

    public int updateOrderStatus(Order order){
        return orderMapper.updateStatus(order);
    }

    public void updateOrderNote(String note,String id){
        orderMapper.updateNote(note, id);
    }

    public int deleteOrder(String id){
        return orderMapper.delete(id);
    }

    public int deleteOrderItem(int id){
        return orderItemMapper.delete(id);
    }

    public void deleteOrderItems(String oid){
        orderItemMapper.deleteItems(oid);
    }

    public List<OrderItem> findOrderItemsByOid(String oid){
        return orderItemMapper.findByOid(oid);
    }

    public Boolean orderItemIsExist(String oid){
        return findOrderItemsByOid(oid).size()>0;
    }

    public OrderItem findOrderItemById(int id){
        return orderItemMapper.findById(id);
    }

    public List<Order> getBuyerOrderByStatus(long buyerId,List<Integer> statuses){
        return orderMapper.getBuyerOrdersByStatus(buyerId,statuses);
    }

    public List<Order> getSellerOrderByStatus(long sellerId,List<Integer> statuses){
        return orderMapper.getSellerOrdersByStatus(sellerId,statuses);
    }

    public List<OrderItemInfo> getAllItemByOid(String oid){
        List<OrderItemInfo> result_list = new ArrayList<>();

        // ??????List<OrderItem>
        List<OrderItem> my_orderItem = orderItemMapper.getAllByOid(oid);
        for(OrderItem orderItem:my_orderItem){
            Book book = bookClients.getBook(orderItem.getBid());
            BookTemplate bookTemplate = bookClients.searchTemplateByIsbn(book.getIsbn()).getData();

            OrderItemInfo one_ItemInfo = new OrderItemInfo();
            one_ItemInfo.setId(orderItem.getId());
            one_ItemInfo.setBid(orderItem.getBid());
            one_ItemInfo.setIsResale(orderItem.getIsResale());

            one_ItemInfo.setTitle(bookTemplate.getTitle());

            one_ItemInfo.setSellPrice(book.getSellPrice());

            result_list.add(one_ItemInfo);
        }

        return result_list;
    }

    public List<OrderInfo> getBuyerOrSellerOrders(long uid,List<Integer> statuses, boolean isBuyer){
        List<OrderInfo> orderInfos = new ArrayList<>();
        List<Order> orderList;
        if (isBuyer){
            orderList = getBuyerOrderByStatus(uid,statuses);
        } else {
            orderList = getSellerOrderByStatus(uid,statuses);
        }
        for (Order order:orderList){
            /*Order ????????????*/
            String id = order.getId();
            long userId;
            if (isBuyer){
                userId = order.getSellerId();
            } else {
                userId = order.getBuyerId();
            }
            String time = order.getTime();
            String note = order.getNote();
            int status = order.getStatus();
            /*User ???????????????name*/
            String name = userClients.getInfo(userId).getName();
            /*??????List<OrderItemInfo>*/
            List<OrderItemInfo> itemInfos = getAllItemByOid(id);
            /*????????????:????????????*/
            BigDecimal subTotal = new BigDecimal(0);
            for (OrderItemInfo itemInfo:itemInfos){
                subTotal = subTotal.add(itemInfo.getSellPrice());
            }
            OrderInfo orderInfo = new OrderInfo(id,userId,name,time,note,status,subTotal,itemInfos);
            orderInfos.add(orderInfo);
        }
        return orderInfos;
    }

    /**
     * ????????????????????????????????????->???????????????
     * @param bid book id
     */
    public void deleteBook(int bid){
        /*????????????????????????????????????*/
        List<String> orderIds = orderItemMapper.findOidByBid(bid);
        /*???????????????????????????*/
        orderItemMapper.deleteByBid(bid);     // ?????????orderItem???????????????
        /*????????????:??????????????????*/
        for (String id:orderIds){
            if (!orderItemIsExist(id)){
                deleteOrder(id);   // ??????order?????????
            }
        }
    }

    /*????????????*/
    public void sellBook(int bid,int id){
        /*??????????????????????????????*/
        List<String> orderIds = orderItemMapper.findOidByBidAndNotId(bid, id);
        /*???????????????????????????*/
        orderItemMapper.deleteByBidAndNotId(bid, id);
        /*?????????????????????????????????*/
        for (String oid:orderIds){
            if (!orderItemIsExist(oid)){
                deleteOrder(oid);
            }
        }
    }

    public List<Order> getAll(){
        return orderMapper.getAll();
    }

    public int resale(int id){
        return orderItemMapper.resale(id);
    }
}
