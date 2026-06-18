package dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import vo.Dept;

public interface DeptDao {
    public List<Dept> getDept() throws ClassNotFoundException, SQLException;
    public int insert(Dept dept) throws ClassNotFoundException, SQLException;
    
    // 검색어와 다중 정렬 리스트를 파라미터로 받음
    public List<Dept> searchDept(
        @Param("searchType") String searchType, 
        @Param("keyword") String keyword,
        @Param("sortList") List<String> sortList
    ) throws ClassNotFoundException, SQLException;
}