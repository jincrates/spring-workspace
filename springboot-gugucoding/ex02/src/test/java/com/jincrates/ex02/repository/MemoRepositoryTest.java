package com.jincrates.ex02.repository;

import com.jincrates.ex02.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {

        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {

        //데이터베이스에 존재하는 mno
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=============================================================");

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2() {

        //데이터베이스에 존재하는 mno
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("================================================================");
        System.out.println(memo);
    }

    @Test
    public void testUpdate() {

        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {

        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {

        //1페이지 10개
        Pageable pageable = PageRequest.of(0,10);
        Page<Memo> result =  memoRepository.findAll(pageable);

        //데이터 확인
        System.out.println(result);
        System.out.println("=========================================================================");
        System.out.println("Total Pages: "    + result.getTotalPages());    //총 몇 페이지
        System.out.println("Total Count: "    + result.getTotalElements()); //전체 개수
        System.out.println("Page Number: "    + result.getNumber());        //현재 페이지 번호
        System.out.println("Page Size: "      + result.getSize());          //페이지당 데이터 개수
        System.out.println("has next page?: " + result.hasNext());          //다음 페이지 존재여부
        System.out.println("first page?: "    + result.isFirst());          //시작 페이지(0) 여부
        System.out.println("=========================================================================");

        //데이터 가져오기
        for(Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() {

        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2); //and를 이용한 연결

        Pageable pageable = PageRequest.of(0, 10, sortAll); //결합된 정렬 조건 사용

        Page<Memo> result = memoRepository.findAll(pageable);

        //result.get().forEach(memo -> System.out.println(memo));
        result.get().forEach(System.out::println);
    }

    @Test
    public void testQueryMethods() {

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for(Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPagable() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {

        //실무에서는 많이 사용하지 않는다. 한 번에 삭제가 이루어지는 것이 아니라 엔티티 객체를 하나씩 삭제하기 때문이다.
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    public void testQueryAnnotaion() {

        List<Memo> list = memoRepository.getListDesc();

        for(Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryAnnotaion2() {

        Long mno = 99L;
        String text = "Hello Spring Boot!";

        int result = memoRepository.updateMemoText(mno, text);

        System.out.println("result : " + result);

        //데이터 조회
        Optional<Memo> findMemo = memoRepository.findById(mno);

        System.out.println("=============================================================");

        if(findMemo.isPresent()) {
            Memo memo = findMemo.get();
            System.out.println(memo);
        }
    }
}