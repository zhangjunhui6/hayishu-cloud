package org.example.book.controller;

import org.example.feign.pojo.BookTemplate;
import org.example.book.service.BookService;
import org.example.feign.view.MyBook;
import org.example.feign.view.SellBook;
import org.example.feign.pojo.Book;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController
 *
 * @author Zjh
 * @date 2020/5/25 18:18
 **/

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getAllTemplate")
    public List<BookTemplate> getAllTemplate(@RequestParam("uid") long uid){
        return bookService.getAllTemplate(uid);
    }

    @GetMapping("/getTemplateByCategory/{cid}")
    public List<BookTemplate> getTemplateByCategory(@PathVariable("cid") int cid, @RequestParam("uid") long uid){
        if (0 != cid){
            return bookService.getTemplateByCategory(cid, uid);
        } else {
            return bookService.getAllTemplate(uid);
        }
    }

    @GetMapping("/searchTemplateByKeyword")
    public List<BookTemplate> searchTemplateByKeyword(@RequestParam("keyword") String keywords,@RequestParam("cid") int cid,@RequestParam("uid") long uid){
        String keyword = "%" + keywords + "%";
        return bookService.searchTemplateByKeyword(keyword, cid, uid);
    }

    @GetMapping("/searchTemplateByISBN")
    public Result<BookTemplate> searchTemplateByIsbn(@RequestParam("isbn") long isbn){
        if (!isIsbn(isbn)){
            return ResultUtil.buildFailResult("ISBN格式错误!",null);
        } else {
            BookTemplate bookTemplate = bookService.searchTemplateByIsbn(isbn);
            if (null == bookTemplate){
                return ResultUtil.buildFailResult("没有找到该ISBN的相关信息",null);
            } else {
                return ResultUtil.buildSuccessResult("成功",bookTemplate);
            }
        }
    }

    @PostMapping("/insertBook")
    @ResponseBody
    public Result insertBook(@RequestBody Book book){
        int res = bookService.insertBook(book);
        if (res == 1){
            return ResultUtil.buildSuccessResult("发布书本成功！",null);
        } else {
            return ResultUtil.buildFailResult("发布书本失败!",null);
        }
    }

    @GetMapping("/getByIsbnAndNotUid")
    public List<SellBook> getByIsbnAndNotUid(@RequestParam("isbn") long isbn, @RequestParam("uid") long uid){
        return bookService.getByIsbnAndNotUid(isbn,uid);
    }

    @PostMapping("/insertBookTemplate")
    @ResponseBody
    public Result insertBookTemplate(@RequestBody BookTemplate bookTemplate){
        long isbn = bookTemplate.getIsbn();
        if (!isIsbn(isbn)){
            return ResultUtil.buildFailResult("ISBN格式错误！",null);
        }
       if (null != bookService.searchTemplateByIsbn(isbn)){
            return ResultUtil.buildFailResult("已存在相应模板!",null);
        } else {
            int res = bookService.insertTemplate(bookTemplate);
            if (res == 1){
                return ResultUtil.buildSuccessResult("书本模板增加成功!",null);
            } else {
                return ResultUtil.buildFailResult("书本模板增加失败!",null);
            }
        }
    }

    @GetMapping("/getMyAllBooks")
    public List<MyBook> getMyAllBooks(@RequestParam("id") long id){
        return bookService.findMyBookByUid(id);
    }

    @GetMapping("/searchMyBooks")
    public List<MyBook> searchMyBooks(@RequestParam("uid") long id,@RequestParam("keywords") String keywords){
        String keyword = "%" + keywords + "%";
        return bookService.findMyBookByUidAndKeyword(id, keyword);
    }

    @PostMapping("/changeBookSellPrice")
    @ResponseBody
    public Result changeBookSellPrice(@RequestBody Book book){
        int res = bookService.changeBookSellPrice(book);
        if (1 == res){
            return ResultUtil.buildSuccessResult("修改成功!",null);
        } else {
            return ResultUtil.buildFailResult("修改失败!",null);
        }
    }

    /**
     * 书本下架：需要将购物车中相应状态置为无效，将所有相关的订单项删除
     * @param id book id
     * @return result
     */
    @PostMapping("/deleteBook")
    @ResponseBody
    public Result deleteBook(@RequestParam("id") int id){
        int res = bookService.deleteBook(id);
        if (res == 1){
            return ResultUtil.buildSuccessResult("书本下架成功",null);
        } else {
            return ResultUtil.buildFailResult("书本下架失败!",null);
        }
    }

    @GetMapping("/getBook")
    public Book getBook(@RequestParam("id") int id) {
        return bookService.getBook(id);
    }

    @PostMapping("/sellBook")
    @ResponseBody
    public Result sellBook(@RequestParam("id") int id) {
        int res = bookService.sellBook(id);
        if (1 == res) {
            return ResultUtil.buildSuccessResult("售书成功", null);
        } else {
            return ResultUtil.buildFailResult("售书失败", null);
        }
    }

    public boolean isIsbn(long isbn){
        String i = String.valueOf(isbn);
        return 13 == i.length() && "978".equals(i.substring(0,3));
    }
}
