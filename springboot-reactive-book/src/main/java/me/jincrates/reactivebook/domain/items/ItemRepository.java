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

    //커스텀 쿼리1
    @Query("{'name' : ?0, 'age' : ?1 }")
    Flux<Item> findItemsForCustomerMonthlyReport(String name, int age);

    //커스텀 쿼리2
    @Query(sort = "{'age' : -1 }")
    Flux<Item> findSortedStuffForWeeklyReport();

    //이직 생각을 심각하게 불러오는 쿼리
    default Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return this.findByNameContainingAndDescriptionContainingAllIgnoreCase(partialName, partialDescription);
                } else {
                    return this.findByNameContainingOrDescriptionContainingAllIgnoreCase(partialName, partialDescription);
                }
            } else {
                return this.findByNameContaining(partialName);
            }
        } else {
            if (partialDescription != null) {
                return this.findByDescriptionContainingIgnore(partialDescription);
            } else {
                return this.findAll();
            }
        }
    }

    //name 검색
    Flux<Item> findByNameContainingIgnoreCase(String partialName);

    //description 검색
    Flux<Item> findByDescriptionContainingIgnore(String partialDescription);

    //name AND description 검색
    Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String partialName, String partialDescription);

    //name OR description 검색
    Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String partialName, String partialDescription);

    //우리를 구원해줄 Example 쿼리
    default Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny())
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                    .withIgnoreCase()
                    .withIgnorePaths("price");

        Example<Item> probe = Example.of(item, matcher);

        return this.findAll(probe);
    }
}
