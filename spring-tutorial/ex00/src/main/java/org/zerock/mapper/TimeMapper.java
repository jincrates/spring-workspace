package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("SELECT CURRENT_TIMESTAMP() FROM DUAL")
	public String getTime();
	
	public String getTime2();
}
