package org.example.cart.service;

import org.example.cart.mapper.CartMapper;
import org.example.cart.pojo.Cart;
import org.example.cart.view.CartTable;
import org.example.feign.clients.BookClients;
import org.example.feign.clients.UserClients;
import org.example.feign.pojo.Book;
import org.example.feign.pojo.BookTemplate;
import org.example.feign.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserClients userClients;

    @Autowired
    private BookClients bookClients;

    public List<Cart> queryByUidAndStatus(long uid, int status){
        return cartMapper.queryByUidAndStatus(uid, status);
    }

    public Cart findByUidAndBid(Cart cart){
        return cartMapper.findByUidAndBid(cart);
    }

    public int insertCart(Cart cart){
        return cartMapper.insertCart(cart);
    }

    public int deleteById(int id){
        return cartMapper.deleteById(id);
    }

    public int updateCart(Cart cart){
        return cartMapper.updateCart(cart);
    }

    public List<CartTable> getMyCart(long uid,int status){
        List<Cart> cartsList = cartMapper.getMyCart(uid, status);
        List<CartTable> result = new ArrayList<>();
        // 先根据uid获取用户信息
        for(Cart oneCart : cartsList){
            //根据one_cart中的bid获取book信息,接着获取卖家的uid信息
            Book book = bookClients.getBook(oneCart.getBid());
            User sellUser = userClients.getInfo(book.getUid());
            // 根据book的isbn信息获取bookTemplate信息
            BookTemplate bookTemplate =  bookClients.searchTemplateByIsbn(book.getIsbn()).getData();

            CartTable cartTable = new CartTable();

            cartTable.setId(oneCart.getId());
            cartTable.setBid(oneCart.getBid());

            cartTable.setUid(book.getUid());
            cartTable.setIsbn(book.getIsbn());
            cartTable.setSellPrice(book.getSellPrice());
            cartTable.setDegree(book.getDegree());

            cartTable.setTitle(bookTemplate.getTitle());
            cartTable.setAuthor(bookTemplate.getAuthor());
            cartTable.setPress(bookTemplate.getPress());
            cartTable.setPrice(bookTemplate.getPrice());

            cartTable.setName(sellUser.getName());

            result.add(cartTable);
        }
        return result;
    }

    public int deActiveCart(int bid){
        return cartMapper.deActiveCart(bid);
    }
}
