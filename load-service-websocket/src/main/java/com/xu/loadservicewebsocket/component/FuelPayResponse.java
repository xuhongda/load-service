package com.xu.loadservicewebsocket.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xuhongda on 2019/6/28
 * com.xu.loadservicewebsocket.component
 * load-service
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FuelPayResponse {
    /**
     * 100 success;
     * 200 error
     */
    private Integer code;

    /**
     * info tip
     */
    private String info;
}
