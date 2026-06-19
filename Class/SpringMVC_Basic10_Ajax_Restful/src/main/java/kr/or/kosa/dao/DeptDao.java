package kr.or.kosa.dao;

import java.util.List;

import kr.or.kosa.dto.Dept;

public interface DeptDao {

	List<Dept> findAllDept();

	Dept findDeptByDeptno(int deptno);

	void insertDept(Dept dept);

	void deleteDept(int deptno);

	void updateDept(Dept dept);

}
