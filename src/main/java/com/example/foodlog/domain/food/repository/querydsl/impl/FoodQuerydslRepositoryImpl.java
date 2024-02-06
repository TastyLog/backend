package com.example.foodlog.domain.food.repository.querydsl.impl;

import com.example.foodlog.domain.food.dto.FoodSearchCondition;
import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.entity.QFood;
import com.example.foodlog.domain.food.repository.querydsl.FoodQuerydslRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
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
import java.util.function.Supplier;
import static com.example.foodlog.domain.food.entity.QFood.*;

@RequiredArgsConstructor
public class FoodQuerydslRepositoryImpl implements FoodQuerydslRepository  {


    private final EntityManager entityManager;


    @Override
    public Page<Food> readFoodList(Pageable pageable, FoodSearchCondition foodSearchCondition) {

        String searchWord = foodSearchCondition.getSearchWord();
        BooleanExpression searchWordExpression = searchWordContains(searchWord);

        JPAQuery<Food> contentQuery = new JPAQueryFactory(entityManager).
                selectFrom(food)
                .where(youtuberEq(foodSearchCondition.getYoutuberId()),searchWordExpression)
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        JPAQuery<Long> countQuery = new JPAQueryFactory(entityManager)
                .select(food.count())
                .where(youtuberEq(foodSearchCondition.getYoutuberId()),searchWordExpression)
                .from(food);

        return PageableExecutionUtils.getPage(contentQuery.fetch(),pageable, () -> countQuery.fetch().size());
    }

    @Override
    public Page<Food> readClosestFoodList(Pageable pageable, FoodSearchCondition foodSearchCondition, String latitude, String longitude) {

        String searchWord = foodSearchCondition.getSearchWord();
        BooleanExpression searchWordExpression = searchWordContains(searchWord);

        JPAQuery<Food> contentQuery = new JPAQueryFactory(entityManager)
                .select(food)
                .from(food)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                                Expressions.stringTemplate("POINT({0}, {1})",
                                        longitude,
                                        latitude
                                ),
                                Expressions.stringTemplate("POINT({0}, {1})",
                                        food.longtitude,
                                        food.latitude
                                )
                        ).asc())
                .where(youtuberEq(foodSearchCondition.getYoutuberId()),searchWordExpression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());


        JPAQuery<Long> countQuery = new JPAQueryFactory(entityManager)
                .select(food.count())
                .where(youtuberEq(foodSearchCondition.getYoutuberId()),searchWordExpression)
                .from(food);

        return PageableExecutionUtils.getPage(contentQuery.fetch(),pageable, () -> countQuery.fetch().size());
    }




    /**
     * like함수를 통한 음식점 검색
     */
    private BooleanExpression searchWordContains(String searchWord) {
        BooleanExpression result = null;
        if (searchWord != null) {
                BooleanExpression includeRestaurant = food.restaurant.like("%" + searchWord + "%"); // restaurant 포함
                BooleanExpression includeAddress = food.address.like("%"+searchWord+"%"); // address 포함
                BooleanExpression includeCategory = food.category.like("%"+searchWord+"%"); // category 포함
            result = includeRestaurant.or(includeAddress).or(includeCategory);
        }
        return result;
    }

    /**
     * 유튜버Id 통한 동적검색
     */
    BooleanBuilder youtuberEq(List<Long> youtuberIds) {
        return youtuberIds==null ?  null : new BooleanBuilder().andAnyOf(youtuberIds.stream()
                .map(youtuberId -> food.youtuber.id.eq(youtuberId))
                .toArray(BooleanExpression[]::new));
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

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }
}
