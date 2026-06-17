package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.DeptDao;
import lombok.RequiredArgsConstructor;
import vo.Dept;

@Service
@RequiredArgsConstructor
public class DeptService {
	private final DeptDao DeptMapper;
	
	public List<Dept> getDept() {
		List<Dept> deptList = null;
		try {
			deptList = DeptMapper.getDept();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptList;
	}
	
	public String insert(Dept dept) {
		try {
			DeptMapper.insert(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect/dept/DeptList.do";
	}
}
