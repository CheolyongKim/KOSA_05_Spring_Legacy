<%@ page contentType="text/html; charset=UTF-8" %>
<html><body>
<h2>DEPT Detail</h2>
<p>DEPTNO: ${item.deptno}</p>
<p>DNAME : ${item.dname}</p>
<p>LOC   : ${item.loc}</p>
<p>
  <a href="${pageContext.request.contextPath}/dept/edit/${item.deptno}">[수정]</a> |
  <a href="${pageContext.request.contextPath}/dept/list">[목록]</a>
</p>
</body></html>