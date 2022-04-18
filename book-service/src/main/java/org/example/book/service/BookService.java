package org.example.book.service;

import org.example.book.mapper.BookMapper;
import org.example.book.mapper.BookTemplateMapper;
import org.example.feign.clients.OrderClient;
import org.example.feign.pojo.BookTemplate;
import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.example.feign.view.MyBook;
import org.example.feign.view.SellBook;
import org.example.feign.clients.UserClients;
import org.example.feign.clients.CartClient;
import org.example.feign.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BookService
 *
 * @author Zjh
 * @date 2020/5/25 17:59
 **/
@Service
public class BookService {
    @Autowired
    private BookTemplateMapper bookTemplateMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserClients userClients;

    @Autowired
    private CartClient cartClients;

    @Autowired
    private OrderClient orderClients;


    public List<BookTemplate> getAllTemplate(long uid){
        return bookTemplateMapper.getAll(uid);
    }

    public List<BookTemplate> getTemplateByCategory(int cid,long uid){
        return bookTemplateMapper.getByCategory(cid,uid);
    }

    public List<BookTemplate> searchTemplateByKeyword(String keyword,int cid,long uid){
        if (cid == 0){
            return bookTemplateMapper.searchByKeyword(keyword,uid);
        } else {
            return bookTemplateMapper.searchByKeywordAndCid(keyword,cid,uid);
        }
    }

    public List<SellBook> getByIsbnAndNotUid(long isbn, long uid){
        List<Book> books = bookMapper.getByIsbnAndNotUid(isbn, uid);

        List<SellBook> list = new ArrayList<>();
        for (Book book: books) {
            // user ID
            long uIdOther = book.getUid();
            User user = userClients.getInfo(uIdOther);
            list.add(new SellBook(user.getId(), book.getId(), user.getName(), user.getSex(), book.getSellPrice(), book.getDegree()));
        }
        return list.isEmpty() ? null : list;
    }

    public BookTemplate searchTemplateByIsbn(long isbn){
        return bookTemplateMapper.searchByISBN(isbn);
    }

    public int insertBook(Book book){
        return bookMapper.insertBook(book);
    }

    public int insertTemplate(BookTemplate bookTemplate){
        return bookTemplateMapper.insertTemplate(bookTemplate);
    }

    public List<MyBook> findMyBookByUid(long id){
        return bookMapper.getMyBookByUid(id);
    }

    public List<MyBook> findMyBookByUidAndKeyword(long id,String keyword){
        return bookMapper.getMyBookByUidAndKeyword(id, keyword);
    }

    public int changeBookSellPrice(Book book){
        return bookMapper.changeBookSellPrice(book);
    }

    public int deleteBook(int id){
        // 这里调用 cart 和 order 服务, 无法直接完成
        // 事务 and 回滚?
        int r = bookMapper.deleteBook(id);
        if (1 == r) {
            // TODO: order service
            int code = cartClients.deActiveCart(id).getCode();
            orderClients.deleteBook(id);
            return 1;
        } else {
            return 0;
        }
    }

    public int sellBook(int id){
        return bookMapper.sellBook(id);
    }



    public Book getBook(int id){
        return bookMapper.getBook(id);
    }
}