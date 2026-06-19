package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	private MemberDao memberDao;
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao=memberDao;
	}
	
	@GetMapping("join.do")
	public String join() {
		return "joinus/join";
	}
	
	@PostMapping("join.do")
	public String join(Member member) {
		System.out.println(member.toString());
		try {
			memberDao.insert(member);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:index.do";
	}
	
	// 로그인 처리 (Security)
	// @GetMapping("login.do")
}
