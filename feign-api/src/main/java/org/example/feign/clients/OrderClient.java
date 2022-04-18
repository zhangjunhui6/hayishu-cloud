package org.example.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DELL
 */
@FeignClient("orderservice")
public interface OrderClient {
    @PostMapping("/order/deleteBook")
    void deleteBook(@RequestParam("bid") int bid);
}
