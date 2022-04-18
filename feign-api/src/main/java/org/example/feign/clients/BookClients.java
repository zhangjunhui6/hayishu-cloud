package org.example.feign.clients;

import org.example.feign.pojo.Book;
import org.example.feign.pojo.BookTemplate;
import org.example.feign.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author Liulu
 */
@FeignClient("bookservice")
public interface BookClients {

    @PostMapping("/book/insertBook")
    Result insertBook(@RequestBody Book book);

    @GetMapping("/book/getBook")
    Book getBook(@RequestParam("id") int id);

    @PostMapping("/book/sellBook")
    Result sellBook(@RequestParam("id") int id);

    @GetMapping("/book/searchTemplateByISBN")
    Result<BookTemplate> searchTemplateByIsbn(@RequestParam("isbn") long isbn);
}
