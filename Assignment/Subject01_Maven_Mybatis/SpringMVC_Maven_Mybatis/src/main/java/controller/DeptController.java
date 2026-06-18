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
            Model model) {
        
        // 검색어가 있으면 searchDept 호출, 없으면 전체 조회 getDept 호출
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("deptList", service.searchDept(searchType, keyword));
        } else {
            model.addAttribute("deptList", service.getDept());
        }
        
        // 검색 후 화면에서 옵션을 유지하기 위해 검색조건도 전달해줍니다.
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        
        return "dept/deptList";
    }
    
    @PostMapping("/DeptList.do")
    public String insert(Dept dept) {
        service.insert(dept);
        return "redirect:/dept/DeptList.do";
    }
}