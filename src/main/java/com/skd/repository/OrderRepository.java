package com.skd.repository;

import com.skd.dto.OrderDTO;
import com.skd.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT NEW dto.OrderDTO(" +
            "COALESCE(:field_order_number, NULL), " +
            "COALESCE(:field_order_status, NULL), " +
            "COALESCE(:field_created_date, NULL), " +
            "COALESCE(:field_orderItems, NULL)) " +
            "FROM Order o " +
            "WHERE o.order_id = :orderId")
    OrderDTO getOrder(@Param("orderId") Integer id,
                      @Param("field_order_number") String order_number,
                      @Param("field_order_status") String order_status,
                      @Param("field_created_date") String created_date,
                      @Param("field_orderItems") String orderItems);

}
