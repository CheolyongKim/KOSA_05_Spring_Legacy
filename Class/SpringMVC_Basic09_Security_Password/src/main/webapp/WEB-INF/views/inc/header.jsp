<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>	
<div id="header">
	<div class="top-wrapper">
		<h1 id="logo">
			<a href="/"><img src="" alt="로고" /></a>
		</h1>
		<h2 class="hidden">메인메뉴</h2>
		<ul id="mainmenu" class="block_hlist">
			<li><a href="">kosa가이드</a></li>
			<li><a href="">kosa과정</a></li>
			<li><a href="">kosa</a></li>
		</ul>
		<form id="searchform" action="" method="get">
			<fieldset>
				<legend class="hidden"> 과정검색폼 </legend>
				<label for="query">과정검색</label> <input type="text" name="query" />
				<input type="submit" class="button" value="검색" />
			</fieldset>
		</form>
		<h3 class="hidden">로그인메뉴</h3>
		<ul id="loginmenu" class="block_hlist">
			<li><a href="${pageContext.request.contextPath}/index.do">HOME</a></li>
			<!-- 
			EL & JSTL 사용한 예제
			<c:if test="${empty pageContext.request.userPrincipal}">
   				 <li><a href="${pageContext.request.contextPath}/joinus/login.do">로그인</a></li>
 			</c:if>
 			
 			userPrincipal.name 인증된 사용(로그인 성공한 사용자 username) 값을 가지고 있어요
 			 
			<c:if test="${not empty pageContext.request.userPrincipal}">
    			<li><a href="${pageContext.request.contextPath}/logout">
    			(${pageContext.request.userPrincipal.name})로그아웃</a></li>
 			</c:if>
 			-->
			<li><a href="${pageContext.request.contextPath}/joinus/join.do">회원가입</a></li>
			
			<!--  
			security  제공하는 script 언어를 사용해서 
			1. 회원 가입 모든 가입된 회원은 : default ROLE_USER
			-->
			<security:authorize access="!hasRole('ROLE_USER')"><!-- 인증되지 않았다면 로그인 하지 않았다면 -->
			    <li><a href="${pageContext.request.contextPath}/joinus/login.do">로그인</a></li>
			</security:authorize>
			
			<!-- 인증이 성공되면 spring 내부에서  userPrincipal 객체 생성  name 속에서 loginid 값 설정 -->
			<security:authentication property="name" var="loginuser" />
			
			<!--  Any -> OR   사용자 권한이 : ROLE_USER 또는 ROLE_ADMIN  -->
			<security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
				<li><a href="${pageContext.request.contextPath}/logout">${loginuser}:로그아웃</a>
			</security:authorize>
			
			<%--
			security 적용 전에 .....
			<li><a href="${pageContext.request.contextPath}/joinus/login.do">로그인</a></li>
			--%>
			
		</ul>
		<h3 class="hidden">회원메뉴</h3>
		<ul id="membermenu" class="clear">
			<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
				<li>
						<a href="${pageContext.request.contextPath}/joinus/memberconfirm.do"><img src="${pageContext.request.contextPath}/images/menuMyPage.png" alt="마이페이지" /></a>
				</li>
			</security:authorize>
				<li>
					<a href="${pageContext.request.contextPath}/customer/notice.do">
					<img src="${pageContext.request.contextPath}/images/menuCustomer.png" alt="고객센터" /></a>
				</li>
		</ul>
	</div>
</div>