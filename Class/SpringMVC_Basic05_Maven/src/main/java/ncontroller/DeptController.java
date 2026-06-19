package ncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.DeptService;
import vo.Dept;

@Controller
@RequestMapping("/dept")
public class DeptController {
    private final DeptService service;
    public DeptController(DeptService service) { this.service = service; }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", service.list());
        return "dept/list";
    }

    @GetMapping("/detail/{deptno}")
    public String detail(@PathVariable int deptno, Model model) {
        model.addAttribute("item", service.get(deptno));
        return "dept/detail";
    }

    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("item", new Dept());
        model.addAttribute("mode", "create");
        return "dept/form";
    }

    @PostMapping("/new")
    public String create(Dept dept) {
        service.create(dept);
        return "redirect:/dept/list";
    }

    @GetMapping("/edit/{deptno}")
    public String formEdit(@PathVariable int deptno, Model model) {
        model.addAttribute("item", service.get(deptno));
        model.addAttribute("mode", "edit");
        return "dept/form";
    }

    @PostMapping("/edit")
    public String edit(Dept dept) {
        service.edit(dept);
        return "redirect:/dept/detail/" + dept.getDeptno();
    }

    @PostMapping("/delete/{deptno}")
    public String delete(@PathVariable int deptno) {
        service.remove(deptno);
        return "redirect:/dept/list";
    }
}