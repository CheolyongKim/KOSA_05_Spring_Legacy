package service;

import java.util.ArrayList;
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
    
    // 다중 정렬 조건 비즈니스 로직
    public List<Dept> searchDept(String searchType, String keyword, String sort1, String sort2) {
        List<Dept> deptList = null;
        try {
            List<String> sortList = new ArrayList<>();
            
            // 1차 정렬 조건이 있으면 리스트에 추가 (예: "LOC ASC")
            if (sort1 != null && !sort1.trim().isEmpty()) {
                sortList.add(sort1);
            }
            // 2차 정렬 조건이 있고, 1차 정렬과 동일하지 않을 때만 리스트에 추가
            if (sort2 != null && !sort2.trim().isEmpty() && !sort2.equals(sort1)) {
                sortList.add(sort2);
            }
            
            deptList = DeptMapper.searchDept(searchType, keyword, sortList);
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