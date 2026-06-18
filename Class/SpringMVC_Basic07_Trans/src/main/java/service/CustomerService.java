package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.NoticeDao;
import vo.Notice;

@Service
public class CustomerService {
	
	private SqlSession sqlSession;
                 //<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
	@Autowired   
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public List<Notice> notices(String pg , String f , String q) {
		
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
		
		if(f != null   && ! f.equals("")) {
			field = f;
		}
		
		if(q != null   && ! q.equals("")) {
			query  = q;
		}
		
	
		List<Notice> list = null;
		try {
			    NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
			    list =  noticeDao.getNotices(page,field,query);
				
		} catch (ClassNotFoundException e) {
					e.printStackTrace();
		} catch (SQLException e) {
					e.printStackTrace();
		}
		
		return list;
	}
	public Notice noticesDetail(String seq) {
			
			Notice  notice = null;
			
			try {
				 
				 NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //異붽�
				 notice = noticeDao.getNotice(seq);
			
			} catch (ClassNotFoundException e ) {
					e.printStackTrace();
			} catch (SQLException e) {
					e.printStackTrace();
			}
					
			return notice;
		}

	//+ 포인트 증가 서비스
	@Transactional
	public String noticeReg(Notice n , HttpServletRequest request) throws Exception {
			  
		    String filename =n.getFile().getOriginalFilename();
			String path = request.getServletContext().getRealPath("/customer/upload");
			String fpath = path + "\\" + filename;
			System.out.println(fpath);
			
			FileOutputStream fs =null;
			try {
				     fs = new FileOutputStream(fpath);
				     fs.write(n.getFile().getBytes());
				     
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				 try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			n.setFileSrc(filename);
			
			try {
				    NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); 
				    // 2개의 업무 실행
				    noticeDao.insert(n);  //DB insert
				    // 추가
				    noticeDao.updateOfMemberPoint("admin");	// 원칙은 session에서 저장된 userid
				    
				    System.out.println("정상 insert . . . 정상 update . . . 둘 다 auto commit");
			} catch (Exception e) {
				e.printStackTrace();
				// 둘 중에 하나라도 문제 발생
				throw e;
				// 예외 발생에 대한 처리는 controller가
			} 
			
				
			
		  return "redirect:notice.do"; 
	}

	public Notice noticeEdit(String seq) {
			
			Notice  notice = null;
			
			try {
				  NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //異붽�
				  notice = noticeDao.getNotice(seq);
				  
			} catch (ClassNotFoundException e ) {
					e.printStackTrace();
			} catch (SQLException e) {
					e.printStackTrace();
			}

			return notice;
		
		}

	public String noticeEdit(Notice n , HttpServletRequest request) {
		 String filename =n.getFile().getOriginalFilename();
		 String path = request.getServletContext().getRealPath("/customer/upload");
		 String fpath = path + "\\" + filename;
		 System.out.println(fpath);
			
			FileOutputStream fs =null;
			try {
				     fs = new FileOutputStream(fpath);
				     fs.write(n.getFile().getBytes());
				     
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				 try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			n.setFileSrc(filename);
		
			try {
				     NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); 
				     noticeDao.update(n);  //DB update
				} catch (Exception e) {
					e.printStackTrace();
				} 
		  return "redirect:noticeDetail.do?seq="+n.getSeq();    
		}
		
	public String noticeDel(String seq) {
			
			 NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); 
			 
			 try {
				    noticeDao.delete(seq);
			} catch (Exception e) {
				    e.printStackTrace();
			}
			
			return "redirect:notice.do";
			
		}
		
	}