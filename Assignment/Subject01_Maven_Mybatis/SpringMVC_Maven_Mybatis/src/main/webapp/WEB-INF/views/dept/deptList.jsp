<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 String action = "DeptList.do";
%>
<html>
<head>
    <title>부서 관리 (다중 정렬 및 검색)</title>
    <style>
        .search-box {
            background-color: #f8f9fa;
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .search-box select, .search-box input {
            margin-right: 10px;
            padding: 5px;
        }
    </style>
</head>
<body>
<h2>Dept List (다중 정렬 적용)</h2>

<form method="post" action="${pageContext.request.contextPath}/dept/<%=action%>">
  <p>DEPTNO: <input name="deptno" value="${dept.deptno}" /></p>
  <p>DNAME : <input name="dname"  value="${dept.dname}" /></p>
  <p>LOC   : <input name="loc"    value="${dept.loc}" /></p>
  <p><button type="submit">저장</button> <a href="${pageContext.request.contextPath}/dept/DeptList.do">취소</a></p>
</form>

<hr>

<div class="search-box">
    <form method="get" action="${pageContext.request.contextPath}/dept/DeptList.do">
      
      <div style="margin-bottom: 10px;">
          <strong>[검색 조건]</strong>
          <select name="searchType">
            <option value="dname" <c:if test="${searchType == 'dname'}">selected</c:if>>부서명</option>
            <option value="deptno" <c:if test="${searchType == 'deptno'}">selected</c:if>>부서번호</option>
            <option value="loc" <c:if test="${searchType == 'loc'}">selected</c:if>>지역</option>
          </select>
          <input type="text" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요" />
      </div>

      <div>
          <strong>[정렬 조건]</strong>
          
          <select name="sort1">
            <option value="">1차 정렬 선택</option>
            <option value="DEPTNO ASC" <c:if test="${sort1 == 'DEPTNO ASC'}">selected</c:if>>부서번호 오름차순</option>
            <option value="DEPTNO DESC" <c:if test="${sort1 == 'DEPTNO DESC'}">selected</c:if>>부서번호 내림차순</option>
            <option value="DNAME ASC" <c:if test="${sort1 == 'DNAME ASC'}">selected</c:if>>부서명 오름차순</option>
            <option value="DNAME DESC" <c:if test="${sort1 == 'DNAME DESC'}">selected</c:if>>부서명 내림차순</option>
            <option value="LOC ASC" <c:if test="${sort1 == 'LOC ASC'}">selected</c:if>>지역 오름차순</option>
            <option value="LOC DESC" <c:if test="${sort1 == 'LOC DESC'}">selected</c:if>>지역 내림차순</option>
          </select>
          
          <select name="sort2">
            <option value="">2차 정렬 선택</option>
            <option value="DEPTNO ASC" <c:if test="${sort2 == 'DEPTNO ASC'}">selected</c:if>>부서번호 오름차순</option>
            <option value="DEPTNO DESC" <c:if test="${sort2 == 'DEPTNO DESC'}">selected</c:if>>부서번호 내림차순</option>
            <option value="DNAME ASC" <c:if test="${sort2 == 'DNAME ASC'}">selected</c:if>>부서명 오름차순</option>
            <option value="DNAME DESC" <c:if test="${sort2 == 'DNAME DESC'}">selected</c:if>>부서명 내림차순</option>
            <option value="LOC ASC" <c:if test="${sort2 == 'LOC ASC'}">selected</c:if>>지역 오름차순</option>
            <option value="LOC DESC" <c:if test="${sort2 == 'LOC DESC'}">selected</c:if>>지역 내림차순</option>
          </select>

          <button type="submit">조회하기</button>
          <button type="button" onclick="location.href='${pageContext.request.contextPath}/dept/DeptList.do'">초기화</button>
      </div>
    </form>
</div>

<table border="1" cellspacing="0" cellpadding="6" style="width: 100%; text-align: center;">
  <tr style="background-color: #eee;">
      <th>DEPTNO</th>
      <th>DNAME</th>
      <th>LOC</th>
  </tr>
  
  <c:choose>
    <c:when test="${empty deptList}">
      <tr>
          <td colspan="3">조회된 부서 결과가 없습니다.</td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="dept" items="${deptList}">
        <tr>
          <td>${dept.deptno}</td>
          <td>${dept.dname}</td>
          <td>${dept.loc}</td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</table>

<br>
<a href="${pageContext.request.contextPath}/index.do">처음으로</a>
</body>
</html>