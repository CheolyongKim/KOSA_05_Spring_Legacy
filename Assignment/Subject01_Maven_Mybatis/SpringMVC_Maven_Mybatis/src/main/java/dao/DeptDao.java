package dao;

import java.sql.SQLException;
import java.util.List;

import vo.Dept;

public interface DeptDao {
	public List<Dept> getDept() throws ClassNotFoundException, SQLException;
	public int insert(Dept dept) throws ClassNotFoundException, SQLException;
}
