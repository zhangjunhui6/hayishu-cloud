package org.example.club.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Follow
 *
 * @author Zjh
 * @date 2021/12/6 16:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    private int id;
    /**
     * uid：用户id
     */
    private long uid;
    /**
     * follow:用户关注的人
     */
    private long followId;
    /**
     * 是否取消关注：1是，0否
     */
    private int status;
}
