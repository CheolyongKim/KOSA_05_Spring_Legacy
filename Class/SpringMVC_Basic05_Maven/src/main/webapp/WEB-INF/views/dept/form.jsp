<%@ page contentType="text/html; charset=UTF-8" %>
<%
 String mode = (String)request.getAttribute("mode");
 String action = "create".equals(mode) ? "/dept/new" : "/dept/edit";
%>
<html><body>
<h2>DEPT <%= "create".equals(mode) ? "Create" : "Edit" %></h2>
<form method="post" action="${pageContext.request.contextPath}<%=action%>">
  <p>DEPTNO: <input name="deptno" value="${item.deptno}" <%= "edit".equals(mode) ? "readonly" : "" %> /></p>
  <p>DNAME : <input name="dname"  value="${item.dname}" /></p>
  <p>LOC   : <input name="loc"    value="${item.loc}" /></p>
  <p><button type="submit">저장</button> <a href="${pageContext.request.contextPath}/dept/list">취소</a></p>
</form>
</body></html>