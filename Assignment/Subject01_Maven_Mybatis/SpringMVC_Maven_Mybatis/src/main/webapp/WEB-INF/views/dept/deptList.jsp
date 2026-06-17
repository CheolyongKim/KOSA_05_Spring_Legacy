<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 String action = "DeptList.do";
%>
<html><body>
<h2>deptList</h2>
<form method="post" action="${pageContext.request.contextPath}<%=action%>">
  <p>DEPTNO: <input name="deptno" value="${dept.deptno}" /></p>
  <p>DNAME : <input name="dname"  value="${dept.dname}" /></p>
  <p>LOC   : <input name="loc"    value="${dept.loc}" /></p>
  <p><button type="submit">저장</button> <a href="${pageContext.request.contextPath}DeptList.do">취소</a></p>
</form>
<table border="1" cellspacing="0" cellpadding="6">
  <tr><th>DEPTNO</th><th>DNAME</th><th>LOC</th></tr>
  <c:forEach var="dept" items="${deptList}">
    <tr>
      <td>${dept.deptno}</td><td>${dept.dname}</td><td>${dept.loc}</td>
    </tr>
  </c:forEach>
</table>
<a href="/index.do">처음으로</a>
</body></html>