<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 String action = "DeptList.do";
%>
<html>
<body>
<h2>Dept List & Search</h2>

<form method="post" action="${pageContext.request.contextPath}/dept/<%=action%>">
  <p>DEPTNO: <input name="deptno" value="${dept.deptno}" /></p>
  <p>DNAME : <input name="dname"  value="${dept.dname}" /></p>
  <p>LOC   : <input name="loc"    value="${dept.loc}" /></p>
  <p>
    <button type="submit">저장</button> 
    <a href="${pageContext.request.contextPath}/dept/DeptList.do">취소</a>
  </p>
</form>

<hr>

<form method="get" action="${pageContext.request.contextPath}/dept/DeptList.do">
    <select name="searchType">
        <option value="deptno" <c:if test="${searchType == 'deptno'}">selected</c:if>>부서번호</option>
        <option value="dname" <c:if test="${searchType == 'dname'}">selected</c:if>>부서명</option>
        <option value="loc" <c:if test="${searchType == 'loc'}">selected</c:if>>지역</option>
    </select>
    
    <input type="text" name="keyword" value="${keyword}" placeholder="예: 10, 20 또는 DALLAS" style="width:200px;"/>
    <button type="submit">검색</button>
    <button type="button" onclick="location.href='${pageContext.request.contextPath}/dept/DeptList.do'">초기화(전체보기)</button>
</form>

<table border="1" cellspacing="0" cellpadding="6">
  <tr><th>DEPTNO</th><th>DNAME</th><th>LOC</th></tr>
  
  <c:choose>
    <c:when test="${empty deptList}">
        <tr>
            <td colspan="3" align="center">조건에 맞는 부서가 없습니다.</td>
        </tr>
    </c:when>
    <c:otherwise>
        <c:forEach var="dept" items="${deptList}">
          <tr>
            <td>${dept.deptno}</td><td>${dept.dname}</td><td>${dept.loc}</td>
          </tr>
        </c:forEach>
    </c:otherwise>
  </c:choose>
</table>

<br>
<a href="${pageContext.request.contextPath}/index.do">처음으로</a>
</body>
</html>