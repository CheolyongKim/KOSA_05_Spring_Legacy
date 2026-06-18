package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import service.DeptService;
import vo.Dept;

@Controller
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {
    private final DeptService service;
    
    @GetMapping("/DeptList.do")
    public String list(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sort1", required = false) String sort1,
            @RequestParam(value = "sort2", required = false) String sort2,
            Model model) {
        
        // 검색어가 있거나 정렬 옵션을 선택한 경우 동적 쿼리 서비스 호출
        boolean hasKeyword = (keyword != null && !keyword.trim().isEmpty());
        boolean hasSort = (sort1 != null && !sort1.trim().isEmpty()) || (sort2 != null && !sort2.trim().isEmpty());
        
        if (hasKeyword || hasSort) {
            model.addAttribute("deptList", service.searchDept(searchType, keyword, sort1, sort2));
        } else {
            model.addAttribute("deptList", service.getDept());
        }
        
        // 검색 및 정렬 후 화면에서 드롭다운 옵션 상태를 유지하기 위해 다시 전달
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort1", sort1);
        model.addAttribute("sort2", sort2);
        
        return "dept/deptList";
    }
    
    @PostMapping("/DeptList.do")
    public String insert(Dept dept) {
        service.insert(dept);
        return "redirect:/dept/DeptList.do";
    }
}