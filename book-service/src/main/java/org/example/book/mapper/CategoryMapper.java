package org.example.book.mapper;

import org.example.book.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * CategoryMapper
 *
 * @author Zjh
 * @date 2020/5/25 17:45
 **/
@Repository
public interface CategoryMapper {
    /**
     * get All Category
     * @return all category's info
     */
    @Select("select * from category")
    List<Category> getAll();
}
