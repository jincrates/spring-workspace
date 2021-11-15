package org.zerock.domain;

import lombok.Data;

// BoardVO 클래스는 Lombok을 이용해서 생성자와 getter/setter/toString() 등을 만들어 내는 방식을 사용
@Data
public class BoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private String updateDate;
}
