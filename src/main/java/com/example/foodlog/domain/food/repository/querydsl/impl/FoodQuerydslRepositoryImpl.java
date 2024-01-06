package com.example.foodlog.domain.food.repository.querydsl.impl;

import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.repository.querydsl.FoodQuerydslRepository;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.foodlog.domain.food.entity.QFood.*;

@RequiredArgsConstructor
public class FoodQuerydslRepositoryImpl implements FoodQuerydslRepository  {


    private final EntityManager entityManager;


    @Override
    public Page<Food> readFoodList(Pageable pageable) {
        JPAQuery<Food> contentQuery = new JPAQueryFactory(entityManager).
                selectFrom(food)
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        JPAQuery<Long> countQuery = new JPAQueryFactory(entityManager)
                .select(food.count())
                .from(food);

        return PageableExecutionUtils.getPage(contentQuery.fetch(),pageable, () -> countQuery.fetch().size());
    }

    /**
     * 동적 orderby
     */

    private List<OrderSpecifier> getOrderSpecifier(Sort sort){
        List<OrderSpecifier> orders=new ArrayList<>();

        sort.stream().forEach(order->{
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();
            PathBuilder<Food> orderByExpression = new PathBuilder<>(Food.class, "food");
            orders.add(new OrderSpecifier(direction,orderByExpression.get(property)));

        });
        return orders;
    }
}
