package com.xu.loadservicewebsocket.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xuhongda on 2019/4/2
 * com.xu.loadservicewebsocket.component
 * load-service
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {

    private Integer stationId;

    private String stationName;

    private String deviceId;

    private String vehicleId;

    private String rfId;

    private String plateNo;

    private String fuelType;

    private Double price;

    private Double fuelQuantity;

    private Double totalFee;

    private String simulationType;

}
