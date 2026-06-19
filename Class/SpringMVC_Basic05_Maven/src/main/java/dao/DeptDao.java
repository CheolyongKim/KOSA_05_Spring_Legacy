package dao;

import vo.Dept;

import java.sql.SQLException;

public interface DeptDao {
	public Dept getDept(String deptno) throws ClassNotFoundException, SQLException;
	public int insert(Dept dept) throws ClassNotFoundException, SQLException;
}