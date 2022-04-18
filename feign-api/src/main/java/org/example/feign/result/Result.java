package org.example.feign.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Result
 *
 * @author Zjh
 * @date 2020/5/24 23:15
 **/
@Data
@AllArgsConstructor
public class Result <T> {
    private int code;
    private String message;
    private T data;
}
