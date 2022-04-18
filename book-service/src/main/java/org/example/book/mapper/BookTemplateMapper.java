package org.example.book.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.feign.pojo.BookTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTemplateMapper {
    /**
     * Get all book template
     * @return result
     */
    @Select("select * from booktemplate " +
            "where isbn in " +
            "(select distinct isbn from book where uid<>#{uid} and status=0)")
    List<BookTemplate> getAll(long uid);

    /**
     * Get books according to the category
     * @param cid category code
     * @return result
     */
    @Select("select * from booktemplate where cid=#{cid} and isbn in " +
            "(select distinct isbn from book where uid<>#{uid} and status=0)")
    List<BookTemplate> getByCategory(int cid, long uid);

    /**
     * Search by key word
     * @param keyword keyword
     * @return result
     */
    @Select("select * from booktemplate where (title like #{keyword} or author like #{keyword} or isbn like #{keyword}) and isbn in " +
            "(select distinct isbn from book where uid<>#{uid} and status=0)")
    List<BookTemplate> searchByKeyword(String keyword,long uid);

    /**
     * Search by keyword and category code
     * @param keyword keyword
     * @param cid category code
     * @return result
     */
    @Select("select * from booktemplate where cid=#{cid} and (title like #{keyword} or author like #{keyword} or isbn like #{keyword}) and isbn in " +
            "(select distinct isbn from book where uid<>#{uid} and status=0)")
    List<BookTemplate> searchByKeywordAndCid(String keyword, int cid,long uid);

    // above are searching Templates in index page

    /**
     * Search by ISBN
     * @param isbn ISBN
     * @return BookTemplate
     */
    @Select("select * from booktemplate where isbn=#{isbn}")
    BookTemplate searchByISBN(long isbn);

    /**
     * Add book templates
     * @param bookTemplate book template
     * @return insert result
     */
    @Insert("insert into booktemplate (isbn,title,author,cover,press,price,abs,cid)" +
            "values(#{isbn},#{title},#{author},#{cover},#{press},#{price},#{abs},#{cid})")
    int insertTemplate(BookTemplate bookTemplate);
}
