package kr.or.kosa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelloController 요청 실행: handleRequest 자동 호출");
		
		/*
		 * 1. 한글처리
		 * 2. 데이터 받기
		 * 3. 업무 처리하기
		 * 4. 데이터 담기
		 * 5. View 정의하기
		 * 6. forward하기
		 */
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "kosauser");	// request.setAttribute("name", "kosauser"); 와 같음
		mav.setViewName("Hello");
		return mav;
	}

}
