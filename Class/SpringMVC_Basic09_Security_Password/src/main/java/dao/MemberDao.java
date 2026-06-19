package dao;

import java.sql.SQLException;

import vo.Member;

public interface  MemberDao {
	
	//회원정보 가져오기
	public Member getMember(String userid) throws ClassNotFoundException, SQLException;
	
	//회원가입 하기 
	public int insert(Member member) throws ClassNotFoundException, SQLException;
	
	//회원정보 수정
	public int updateMember(Member member);
}
