package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DeptDao;
import lombok.RequiredArgsConstructor;
import vo.Dept;
import vo.Member;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptDao DeptMapper;

	public Dept getDept(String deptno) {
		Dept dept = null;
		try {
			   dept = DeptMapper.getDept(deptno);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return dept;
	}
	
	public String insert(Dept dept) {
		try {
			DeptMapper.insert(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/index.do";
	}
}