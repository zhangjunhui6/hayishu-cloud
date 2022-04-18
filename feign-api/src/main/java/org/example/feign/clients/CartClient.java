package org.example.feign.clients;

import org.example.feign.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("cartservice")
public interface CartClient {
    @PostMapping("/cart/deActiveCart")
    Result deActiveCart(@RequestParam("bid") int bid);
}
