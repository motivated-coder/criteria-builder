package com.skd.service;

import com.skd.dto.OrderDTO;
import com.skd.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @PostConstruct
    public void someMethod() {
         entityManager = entityManagerFactory.createEntityManager();
        // Use the entityManager to perform JPA operations
    }
    public OrderDTO getOrder(Integer orderId, List<String> listString) {
        Order order = null;
        String jpql = "SELECT NEW dto.OrderDTO(";
        Map<String, Object> params = new HashMap<>();

        if (listString == null || listString.isEmpty()) {
            jpql += "o.order_number, o.order_status, ...)";
        } else {
            for (String field : listString) {
                String paramName = "field_" + field;
                jpql += ":" + paramName + ", ";
                params.put(paramName, getFieldValueByName(order, field));
            }
            jpql = jpql.substring(0, jpql.length() - 2) + ")";
        }

        jpql += " FROM Order o WHERE o.id = :orderId";

        TypedQuery<OrderDTO> query = entityManager.createQuery(jpql, OrderDTO.class)
                .setParameter("orderId", orderId);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getSingleResult();
    }

    private Object getFieldValueByName(Order order, String fieldName) {
        try {
            Field field = order.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(order);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get field value", e);
        }
    }
}
