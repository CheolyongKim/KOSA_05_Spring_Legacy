package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.MemberDao;
import service.CustomerService;
import service.JoinService;
import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	
	
	//일반 member field   주입 가능 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private JoinService service;


	//GET 요청
	//join.jsp
	@GetMapping("join.do")  //   /joinus/join.do
	public String join() {
		return "joinus/join";
	}
	
	
	//POST 요청
	@PostMapping("join.do")  //   /joinus/join.do 
	public String join(Member member) throws ClassNotFoundException, SQLException {
		System.out.println(member.toString());
		
		int result = 0;
		String viewPage="";
		
		//암호화 (사용자가 입력한 비밀번호 평문을 > 암호화된 값으로////////////////////////// 
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		////////////////////////////////////////////////////////////////////
		result = service.insertMember(member);
		
		if(result > 0 ) {
			System.out.println("가입성공");
			viewPage = "redirect:/index.do";
		}else {
			System.out.println("가입실패");
			viewPage = "join.do";
		}
		return viewPage;
	}

	
	//로그인 처리 
	//@GetMapping("login.do") 
	//security 에서 처리
	@GetMapping("login.do") 
	public String login() {
		return "joinus/login";
	}
	
	//추가 권한 없는 페이지 접근시 처리 
	
	@GetMapping("accessDenied.do")
	public String Denied() {
		return "joinus/accessDenied";
	}
}
