package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {

	// 테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인할 것
	private Long[] bnoArr = {1L,4L,5L,7L,10L,11L,12L,21L,22L,23L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Ignore
	public void testMapper() {
		log.info(mapper);
	}
	
	@Ignore
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
			vo.setBno(bnoArr[i]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer " + i);
			
			mapper.insert(vo);
		});
	}
	
	@Ignore
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	@Ignore
	public void testDelete() {
		Long targetRno = 1L;
		
		mapper.delete(targetRno);
	}
	
	@Ignore
	public void testUpdate() {
		Long targetRno = 7L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update Rply ");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: " + count);	
	}
	
	@Ignore
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[3]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(2, 10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 20L);
		
		replies.forEach(reply -> log.info(reply));
	}

}
