package dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import vo.Dept;

public interface DeptDao {
    public List<Dept> getDept() throws ClassNotFoundException, SQLException;
    public int insert(Dept dept) throws ClassNotFoundException, SQLException;
    
    // 추가: 다중 검색용 메서드
    public List<Dept> searchDept(
        @Param("searchType") String searchType, 
        @Param("keywordList") List<String> keywordList
    ) throws ClassNotFoundException, SQLException;
}