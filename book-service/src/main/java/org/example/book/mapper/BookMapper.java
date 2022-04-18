package org.example.book.mapper;

import org.example.feign.view.MyBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.feign.view.SellBook;
import org.example.feign.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookMapper
 *
 * @author Zjh
 * @date 2020/5/24 23:13
 **/
@Repository
public interface BookMapper {
    /**
     * 首页书本详情获取的非本人的图书信息(图书状态为待出售)
     * @param isbn ISBN
     * @param uid now look user
     * @return result
     */
    @Select("select * from book b " +
            "where b.status=0 and b.uid<>#{uid} and b.isbn=#{isbn} order by b.sellPrice")
    List<Book> getByIsbnAndNotUid(long isbn, long uid);

    /**
     * 发布书本
     * @param book book
     * @return insert result
     */
    @Insert("insert into book (uid,isbn,sellPrice,degree,status)" +
            "values(#{uid},#{isbn},#{sellPrice},#{degree},#{status})")
    int insertBook(Book book);

    /**
     * Query somebody's all book info
     * @param id user id
     * @return book information
     */
    @Select("select b.id,b.isbn,t.title,t.author,t.cover,t.press,b.degree,t.price,b.sellPrice " +
            "from book b join booktemplate t on b.isbn=t.isbn " +
            "where b.uid=#{id} and b.status=0")
    List<MyBook> getMyBookByUid(long id);

    /**
     * Query somebody's books according keyword
     * @param id user id
     * @param keyword keyword
     * @return book information
     */
    @Select("select b.id,b.isbn,t.title,t.author,t.cover,t.press,b.degree,t.price,b.sellPrice " +
            "from book b join booktemplate t on b.isbn=t.isbn " +
            "where b.uid=#{id} and b.status=0 and " +
            "(b.isbn like #{keyword} or t.title like #{keyword} or t.author like #{keyword})")
    List<MyBook> getMyBookByUidAndKeyword(long id, String keyword);

    /**
     *Change book sell price
     * @param book new sell price
     * @return update Result
     */
    @Update("update book set sellPrice=#{sellPrice} where id=#{id}")
    int changeBookSellPrice(Book book);

    /**
     * 书本下架
     * @param id book id
     * @return update status result
     */
    @Update("update book set status=-1 where id=#{id}")
    int deleteBook(int id);

    /**
     * 书本出售成功
     * @param id book id
     */
    @Update("update book set status=1 where id=#{id}")
    int sellBook(int id);

    /**
     * 根据id获取书本
     * @param id id
     * @return book
     */
    @Select("select * from book where id=#{id}")
    Book getBook(int id);
}
