package org.example.book.service;

import org.example.book.mapper.CategoryMapper;
import org.example.book.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjh
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAll(){
        return categoryMapper.getAll();
    }
}
