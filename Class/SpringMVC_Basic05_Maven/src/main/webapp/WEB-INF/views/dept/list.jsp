<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><body>
<h2>DEPT List</h2>
<p><a href="${pageContext.request.contextPath}/dept/new">[새 부서]</a></p>
<table border="1" cellspacing="0" cellpadding="6">
  <tr><th>DEPTNO</th><th>DNAME</th><th>LOC</th><th>Action</th></tr>
  <c:forEach var="d" items="${items}">
    <tr>
      <td>${d.deptno}</td><td>${d.dname}</td><td>${d.loc}</td>
      <td>
        <a href="${pageContext.request.contextPath}/dept/detail/${d.deptno}">상세</a>
        <form action="${pageContext.request.contextPath}/dept/delete/${d.deptno}" method="post" style="display:inline">
          <button type="submit" onclick="return confirm('삭제?')">삭제</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
</body></html>