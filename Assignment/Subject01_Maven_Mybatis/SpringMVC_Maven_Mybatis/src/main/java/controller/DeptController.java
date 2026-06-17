package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import service.DeptService;

@Controller
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {
	private final DeptService service;
	
	@GetMapping("/DeptList.do")
	public String list(Model model) {
		model.addAttribute("deptList", service.getDept());
		return "dept/deptList";
	}
}
