<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dept List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg bg-white border-bottom">
		<div class="container">
			<a class="navbar-brand fw-semibold" href="${pageContext.request.contextPath}/dept/deptList">DEPT</a>
			<a class="btn btn-outline-secondary btn-sm" href="${pageContext.request.contextPath}/">EMP 페이지</a>
		</div>
	</nav>

	<main class="container py-4">
		<div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-3">
			<div>
				<h1 class="h3 mb-1">부서 목록</h1>
				<p class="text-muted mb-0">fetch API로 <code>GET /dept</code>를 호출합니다.</p>
			</div>
			<div class="d-flex gap-2">
				<div class="input-group">
					<input type="number" class="form-control" id="deptnoSearch" placeholder="DEPTNO">
					<button type="button" class="btn btn-outline-primary" id="searchBtn">검색</button>
				</div>
				<button type="button" class="btn btn-primary text-nowrap" id="reloadBtn">전체조회</button>
			</div>
		</div>

		<div class="bg-white border rounded p-3 mb-3">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2 class="h5 mb-0" id="formTitle">새 부서 입력</h2>
				<button type="button" class="btn btn-outline-secondary btn-sm" id="resetFormBtn">초기화</button>
			</div>
			<form id="deptForm" class="row g-2 align-items-end">
				<div class="col-12 col-md-3">
					<label for="deptno" class="form-label">DEPTNO</label>
					<input type="number" class="form-control" id="deptno" required>
				</div>
				<div class="col-12 col-md-4">
					<label for="dname" class="form-label">DNAME</label>
					<input type="text" class="form-control" id="dname" required>
				</div>
				<div class="col-12 col-md-3">
					<label for="loc" class="form-label">LOC</label>
					<input type="text" class="form-control" id="loc" required>
				</div>
				<div class="col-12 col-md-2 d-grid">
					<button type="submit" class="btn btn-success" id="saveBtn">등록</button>
				</div>
			</form>
		</div>

		<div class="alert d-none" id="message" role="alert"></div>

		<div class="table-responsive bg-white border rounded">
			<table class="table table-striped table-hover align-middle mb-0">
				<thead class="table-dark">
					<tr>
						<th scope="col">DEPTNO</th>
						<th scope="col">DNAME</th>
						<th scope="col">LOC</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody id="deptList">
					<tr>
						<td colspan="4" class="text-center text-muted py-4">조회 중입니다.</td>
					</tr>
				</tbody>
			</table>
		</div>
	</main>

	<script>
		const contextPath = '${pageContext.request.contextPath}';
		const deptList = document.getElementById('deptList');
		const message = document.getElementById('message');
		let updateMode = false;

		function showMessage(text, type) {
			message.textContent = text;
			message.className = 'alert alert-' + type;
		}

		function clearMessage() {
			message.textContent = '';
			message.className = 'alert d-none';
		}

		function renderDeptList(data) {
			deptList.innerHTML = '';

			if (!data || data.length === 0) {
				deptList.innerHTML = '<tr><td colspan="4" class="text-center text-muted py-4">조회된 부서가 없습니다.</td></tr>';
				return;
			}

			data.forEach(function(dept) {
				const tr = document.createElement('tr');
				tr.innerHTML =
					'<td>' + dept.deptno + '</td>' +
					'<td>' + dept.dname + '</td>' +
					'<td>' + dept.loc + '</td>' +
					'<td>' +
						'<button type="button" class="btn btn-outline-primary btn-sm me-1 updateBtn" data-deptno="' + dept.deptno + '" data-dname="' + dept.dname + '" data-loc="' + dept.loc + '">수정</button>' +
						'<button type="button" class="btn btn-outline-danger btn-sm deleteBtn" data-deptno="' + dept.deptno + '">삭제</button>' +
					'</td>';
				deptList.appendChild(tr);
			});
		}

		function renderDept(dept) {
			deptList.innerHTML = '';

			if (!dept || !dept.deptno) {
				deptList.innerHTML = '<tr><td colspan="4" class="text-center text-muted py-4">조회된 부서가 없습니다.</td></tr>';
				return;
			}

			const tr = document.createElement('tr');
			tr.innerHTML =
				'<td>' + dept.deptno + '</td>' +
				'<td>' + dept.dname + '</td>' +
				'<td>' + dept.loc + '</td>' +
				'<td>' +
					'<button type="button" class="btn btn-outline-primary btn-sm me-1 updateBtn" data-deptno="' + dept.deptno + '" data-dname="' + dept.dname + '" data-loc="' + dept.loc + '">수정</button>' +
					'<button type="button" class="btn btn-outline-danger btn-sm deleteBtn" data-deptno="' + dept.deptno + '">삭제</button>' +
				'</td>';
			deptList.appendChild(tr);
		}

		function resetDeptForm() {
			updateMode = false;
			document.getElementById('formTitle').textContent = '새 부서 입력';
			document.getElementById('saveBtn').textContent = '등록';
			document.getElementById('deptno').readOnly = false;
			document.getElementById('deptForm').reset();
		}

		function getDeptFormData() {
			return {
				deptno: document.getElementById('deptno').value.trim(),
				dname: document.getElementById('dname').value.trim(),
				loc: document.getElementById('loc').value.trim()
			};
		}

		async function findAllDept() {
			clearMessage();
			deptList.innerHTML = '<tr><td colspan="4" class="text-center text-muted py-4">조회 중입니다.</td></tr>';

			try {
				const response = await fetch(contextPath + '/dept', {
					method: 'GET',
					headers: {
						'Accept': 'application/json'
					}
				});

				if (!response.ok) {
					throw new Error('HTTP 상태 코드: ' + response.status);
				}

				const data = await response.json();
				renderDeptList(data);
				showMessage('부서 목록을 조회했습니다.', 'success');
			} catch (error) {
				deptList.innerHTML = '<tr><td colspan="4" class="text-center text-danger py-4">부서 목록 조회에 실패했습니다.</td></tr>';
				showMessage(error.message, 'danger');
			}
		}

		async function findDeptByDeptno() {
			const deptno = document.getElementById('deptnoSearch').value.trim();

			if (deptno === '') {
				findAllDept();
				return;
			}

			clearMessage();
			deptList.innerHTML = '<tr><td colspan="4" class="text-center text-muted py-4">조회 중입니다.</td></tr>';

			try {
				const response = await fetch(contextPath + '/dept/' + deptno, {
					method: 'GET',
					headers: {
						'Accept': 'application/json'
					}
				});

				if (!response.ok) {
					throw new Error('HTTP 상태 코드: ' + response.status);
				}

				const data = await response.json();
				renderDept(data);
				showMessage('부서 정보를 조회했습니다.', 'success');
			} catch (error) {
				deptList.innerHTML = '<tr><td colspan="4" class="text-center text-danger py-4">부서 조회에 실패했습니다.</td></tr>';
				showMessage(error.message, 'danger');
			}
		}

		async function saveDept(event) {
			event.preventDefault();

			const dept = getDeptFormData();
			const url = updateMode ? contextPath + '/dept' : contextPath + '/dept';
			const method = updateMode ? 'PUT' : 'POST';

			try {
				const response = await fetch(url, {
					method: method,
					headers: {
						'Content-Type': 'application/json; charset=utf-8'
					},
					body: JSON.stringify(dept)
				});

				if (!response.ok) {
					const msg = await response.text();
					
					throw new Error('HTTP 상태 코드: ' + response.status + '응답메세지: ' + msg);
				}

				resetDeptForm();
				findAllDept();
				showMessage(updateMode ? '부서 정보를 수정했습니다.' : '새 부서를 등록했습니다.', 'success');
			} catch (error) {
				showMessage(error.message, 'danger');
			}
		}

		async function deleteDept(deptno) {
			if (!confirm(deptno + '번 부서를 삭제할까요?')) {
				return;
			}

			try {
				const response = await fetch(contextPath + '/dept/' + deptno, {
					method: 'DELETE'
				});

				if (!response.ok) {
					throw new Error('HTTP 상태 코드: ' + response.status);
				}

				findAllDept();
				showMessage('부서를 삭제했습니다.', 'success');
			} catch (error) {
				showMessage(error.message, 'danger');
			}
		}

		function fillUpdateForm(button) {
			updateMode = true;
			document.getElementById('formTitle').textContent = '부서 수정';
			document.getElementById('saveBtn').textContent = '수정';
			document.getElementById('deptno').value = button.dataset.deptno;
			document.getElementById('dname').value = button.dataset.dname;
			document.getElementById('loc').value = button.dataset.loc;
			document.getElementById('deptno').readOnly = true;
		}

		document.getElementById('deptForm').addEventListener('submit', saveDept);
		document.getElementById('resetFormBtn').addEventListener('click', resetDeptForm);
		deptList.addEventListener('click', function(event) {
			if (event.target.classList.contains('updateBtn')) {
				fillUpdateForm(event.target);
			}

			if (event.target.classList.contains('deleteBtn')) {
				deleteDept(event.target.dataset.deptno);
			}
		});
		document.getElementById('searchBtn').addEventListener('click', findDeptByDeptno);
		document.getElementById('deptnoSearch').addEventListener('keydown', function(event) {
			if (event.key === 'Enter') {
				findDeptByDeptno();
			}
		});
		document.getElementById('reloadBtn').addEventListener('click', findAllDept);
		document.addEventListener('DOMContentLoaded', findAllDept);
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
