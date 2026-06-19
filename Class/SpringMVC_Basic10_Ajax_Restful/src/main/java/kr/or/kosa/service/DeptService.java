package kr.or.kosa.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosa.dao.DeptDao;
import kr.or.kosa.dto.Dept;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
	private final SqlSession sqlsession;

	public List<Dept> getDeptList() {
		DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
		return deptDao.findAllDept();
	}

	public Dept getDept(int deptno) {
		DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
		return deptDao.findDeptByDeptno(deptno);
	}

	public void insertDept(Dept dept) {
		DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
		deptDao.insertDept(dept);
	}

	public void deleteDept(int deptno) {
		DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
		deptDao.deleteDept(deptno);
	}

	public void updateDept(Dept dept) {
		DeptDao deptDao = sqlsession.getMapper(DeptDao.class);
		deptDao.updateDept(dept);
	}
	
}
