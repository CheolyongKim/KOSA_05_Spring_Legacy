package service;

import java.util.Arrays;
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
    
    // 추가: 동적 다중 검색 서비스
    public List<Dept> searchDept(String searchType, String keyword) {
        List<Dept> deptList = null;
        try {
            List<String> keywordList = null;
            // 콤마(,)를 기준으로 배열로 자르고, 여백 제거 후 List로 변환 (예: "10, 20" -> ["10", "20"])
            if (keyword != null && !keyword.trim().isEmpty()) {
                keywordList = Arrays.asList(keyword.split("\\s*,\\s*"));
            }
            deptList = DeptMapper.searchDept(searchType, keywordList);
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
        return "redirect:/dept/DeptList.do";
    }
}