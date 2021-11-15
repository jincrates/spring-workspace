package org.zerock.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

//Test for Controller
@WebAppConfiguration

@ContextConfiguration(classes = { org.zerock.config.RootConfig.class, 
								  org.zerock.config.ServletConfig.class })
@Log4j	
public class BoardControllerTests {

	@Setter(onMethod_ = { @Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	// @Before - 모든 테스트 전에 매번 실행되는 메서드
	@Before
	public void setUp() {
		// 가짜로 URL로 파라미터 등을 브라우저에서 사용하는 것처럼 만들어서 테스트 코드를 실행
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Ignore
	public void testList() throws Exception {
		log.info(
				mockMvc
				.perform(MockMvcRequestBuilders
						.get("/board/list"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Ignore
	public void testRegister() throws Exception {
		
		// 테스트시 post()를 이용하면 POST 방식으로 데이터를 전달할 수 있음
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/board/register")
						.param("title", "테스트 새 글 제목")
						.param("content", "테스트 새 글 내용")
						.param("writer", "user00"))
				.andReturn()
				.getModelAndView()
				.getViewName();
				
		log.info(resultPage);
	}
	
	@Ignore
	public void testGet() throws Exception {
		
		log.info(mockMvc
				.perform(MockMvcRequestBuilders
						.get("/board/get")
						.param("bno", "1"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Ignore
	public void testModify() throws Exception {
		
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/board/modify")
						.param("bno", "1")
						.param("title", "수정된 테스트 새 글 제목")
						.param("content", "수정된 테스트 새 글 내용")
						.param("writer", "user00"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info(resultPage);
	}
	
	@Ignore
	public void testRemove() throws Exception {
		
		//삭제 전 데이터베이스에 게시물 번호 확인할 것
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/board/remove")
						.param("bno", "9"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info(resultPage);
	}
	
	@Test
	public void testListPaging() throws Exception {
		
		log.info(mockMvc.perform(MockMvcRequestBuilders
					.get("/board/list")
					.param("pageNum", "2")
					.param("amount", "50"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
}
