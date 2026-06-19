package ncontroller;
/*
 * 게시판 처리
 * 목록보기
 * 상세보기
 * 하나의 컨트롤러 (@Controller) 사용 메서드 단위 매핑
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
	// 1. CustomerController는 NoticeDao에 의존합니다.
	private NoticeDao noticeDao;

	@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	// 2. 전체조회
	@RequestMapping("notice.do")
	public String notices(String pg, String f, String q, Model model) {
		int page = 1;
		String field = "TITLE";
		String query = "%%";
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		if (f != null && !f.equals("")) {
			field = f;
		}

		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		if (q != null && !q.equals("")) {
			query = q;
		}

		// DAO 작업
		List<Notice> list = null;
		try {
			list = noticeDao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		model.addAttribute("list", list);	// 담기만 하면 자동 forward > return하지 않아도 됨
		return "customer/notice";
	}

	// 3. 상세보기
	@RequestMapping("noticeDetail.do")
	public String noticesDetail(String seq, Model model) {
		Notice notice = null;
		try {
			notice = noticeDao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("notice", notice);
		return "customer/noticeDetail";
	}
	
	// 글쓰기 화면
	@GetMapping("noticeReg.do")
	public String noticeReg() {
		return "customer/noticeReg";
	}
	
	// 글쓰기 처리 (파일업로드 처리)
	@PostMapping("noticeReg.do")
	public String noticeReg(Notice notice, HttpServletRequest request) {
		System.out.println(notice.toString());
		CommonsMultipartFile file = notice.getFile();
		System.out.println("file getName()" + file.getName() );
		System.out.println("file getContentType()" + file.getContentType() );
		System.out.println("file getOriginalFilename()" + file.getOriginalFilename() );
		System.out.println("file getBytes().length" + file.getBytes().length );
		
		//필요한 정보가 있다면 추출해서 DB > Table > insert 해야 되요
		
		//POINT 파일명 추출 image=null
		notice.setFileSrc(file.getOriginalFilename()); //수동 ...
		
		//upload (서버에 파일쓰기)
		//자동화 : cos.jar (무료) ,  덱스트 업로드(제품 구매) 
		
		//수동으로 코딩( I/O)
		String fileName = file.getOriginalFilename();
		//HttpServletRequest request
		String path =
				request.getServletContext().getRealPath("/customer/upload");
		String fpath = path + "\\" + fileName;  // C:\\Web\\upload\\a.jpg
		
		System.out.println(fpath);
		
		FileOutputStream fs = null;
		
		try {
			   fs = new FileOutputStream(fpath); //파일이 없으면 빈 파일 ( a.jpg) 자동
			   fs.write(file.getBytes()); //image생성 .... 업로드한 파일 서버에 write
			   
		} catch (Exception e) {
			   e.printStackTrace();
		}finally {
			 try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "customer/noticeReg";
	}
}
