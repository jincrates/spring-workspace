package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 1) @ContextConfiguration
//    - 지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내 객체로 등록함
//    - 이를 스프링의 빈(Bean)으로 등록된다고 표현
// 2) @Log4j
//    - Lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성함
//    - 로그에 대한 설정은 'src/main/resources'와 'src/test/resources'에 별도로 존재
// 3) @Autowired
//    - 해당 인스턴스 변수가 스프링으로부터 자동으로 주입해 달라는 표시
// 4) @Test
//    - JUnit에서 테스트 대상을 표시하는 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	
	@Setter(onMethod_ = { @Autowired})
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-----------------------------------");
		log.info(restaurant.getChef());		
	}
}
