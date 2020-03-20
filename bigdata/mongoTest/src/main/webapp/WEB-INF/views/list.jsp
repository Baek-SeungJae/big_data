<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,spring.data.mongodb.dto.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% List<ScoreDTO> mongolist  =
			(List<ScoreDTO>)request.getAttribute("list"); 
	   int size = mongolist.size();	%>
	<h1>Score(Mongo)</h1>
	
	<hr/>
	<table align="center" border="1" width="600">
		<tr bgcolor="skyblue">
			<th>아이디</th><th>이름</th>
			<th>주소</th><th>부서</th>
			<th>삭제</th>
		</tr>
		<%if(size!=0){ %>
		<%for(int i = 0;i<size;i++){
			ScoreDTO user = mongolist.get(i);%>
			<tr>
				<td><a href="/mongoTest/score/detail?key=id&value=<%=user.getId()%>&action=READ"><%= user.getId() %></a></td>
				<td><%= user.getName() %></td>
				<td><%= user.getAddr() %></td>
				<td><%= user.getDept()%></td>
				<td> <a href="/mongoTest/score/delete?id=<%=user.getId() %>">삭제</a></td>
			</tr>
		<%}}else{ %>
		<tr>
			<td>검색된 값이 없습니다.</td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
			<% if(request.getParameter("pageNo")!=null){ %>
			<a href="/mongoTest/score/paginglist?pageNo=0">1</a>
			<a href="/mongoTest/score/paginglist?pageNo=1">2</a>
			<a href="/mongoTest/score/paginglist?pageNo=2">3</a>
			<%} %>
			</td>
		</tr>
	</table>
	
</body>
</html>










