package project.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import project.project.dto.BoardDto;

@Component
public class BoardValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BoardDto.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		BoardDto board = (BoardDto) obj;
		if(StringUtils.isEmpty(board.getContents())) {
			e.rejectValue("contents", "key", "contents empty!!");
		}
	}
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
