package kr.or.kosa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.dto.Dept;
import kr.or.kosa.service.DeptService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {
	private final DeptService deptService;

	@GetMapping
	public ResponseEntity<List<Dept>> getDeptList() {
		// 1. 서비스에서 Dao로 전체 리스트를 가져온다.

		// 2. responseEntity의 body에 그 리스트를 넘겨준다.

		return ResponseEntity.status(HttpStatus.OK).body(deptService.getDeptList());
	}

	@GetMapping("{deptno}")
	public ResponseEntity<Dept> getDept(@PathVariable("deptno") int deptno) {
		return (deptService.getDept(deptno) == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
				: ResponseEntity.status(HttpStatus.OK).body(deptService.getDept(deptno));
	}

	@PostMapping(produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> insertDept(@RequestBody Dept dept) {
		if (deptService.getDept(dept.getDeptno()) == null) {
			deptService.insertDept(dept);
			return ResponseEntity.status(HttpStatus.CREATED).body("DEPT INSERT 완료됐습니다.");
		}
		// deptno 중복의 경우
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Deptno 중복입니다.");
		}
	}

	@DeleteMapping("{deptno}")
	public ResponseEntity<String> deleteDept(@PathVariable("deptno") int deptno) {
		deptService.deleteDept(deptno);
		return ResponseEntity.status(HttpStatus.OK).body("DEPT DELETE 완료됐습니다.");
	}

	@PutMapping
	public ResponseEntity<String> updateDept(@RequestBody Dept dept) {
	    deptService.updateDept(dept);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("DEPT UPDATE 완료됐습니다.");
	}
}
