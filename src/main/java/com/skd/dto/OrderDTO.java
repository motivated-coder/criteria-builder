package com.skd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String order_status;
    private String order_number;
    private Date created_date;
    List<OrderItemDTO> orderItemDTOS;
}
