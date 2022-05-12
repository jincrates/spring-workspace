package me.jincrates.reactivebook.domain.items;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>,
                                        ReactiveQueryByExampleExecutor<Item> {

    //고객이 입력한 검색어가 이름에 포함된 상품을 반환하는 메소드
    //스프링 데이터에서 정한 메소드 이름 규칙을 사용하면 직접 코드나 SQL문을 작성하지 않아도 자동으로 메소드를 만들 수 있다.
    Flux<Item> findByNameContaining(String partialName);

//    //커스텀 쿼리1
//    @Query("{'name' : ?0, 'age' : ?1 }")
//    Flux<Item> findItemsForCustomerMonthlyReport(String name, int age);
//
//    //커스텀 쿼리2
//    @Query(sort = "{'age' : -1 }")
//    Flux<Item> findSortedStuffForWeeklyReport();

    // search by name
    Flux<Item> findByNameContainingIgnoreCase(String partialName);

    // search by description
    Flux<Item> findByDescriptionContainingIgnoreCase(String partialDescription);

    // search by name AND description
    Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String partialName, String partialDescription);

    // search by name OR description
    Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String partialName, String partialDescription);
}
