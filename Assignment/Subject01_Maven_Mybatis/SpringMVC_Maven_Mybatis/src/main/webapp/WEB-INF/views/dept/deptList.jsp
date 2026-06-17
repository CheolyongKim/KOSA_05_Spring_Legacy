<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><body>
<h2>deptList</h2>
<p><a href="${pageContext.request.contextPath}/dept/NewDept.do">[새 부서]</a></p>
<table border="1" cellspacing="0" cellpadding="6">
  <tr><th>DEPTNO</th><th>DNAME</th><th>LOC</th></tr>
  <c:forEach var="dept" items="${deptList}">
    <tr>
      <td>${dept.deptno}</td><td>${dept.dname}</td><td>${dept.loc}</td>
    </tr>
  </c:forEach>
</table>
</body></html>