<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page
	import="kr.multi.bigdataShop.product.comment.ProductCommentResultDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="/bigdataShop/jQCloud-master/jqcloud/jqcloud.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="/bigdataShop/jQCloud-master/jqcloud/jqcloud-1.0.4.js"></script>
<script type="text/javascript">
		var word_array = [];
		size=${commentresult.size()}

		<c:forEach var="commentresult" items="${commentresult}">
			word_array.push({
				text : "${commentresult.word}",
				weight : "${commentresult.count}"
			})
		</c:forEach>
	$(function() {
		$("#example").jQCloud(word_array);
	});
</script>
<script type="text/javascript">
	year = "${year}";
	month = "${month}";
	$(document).ready(function() {
		if (year == "") {
			year = "${year}";
		}
		$("#year").val(year).attr("selected", "selected");

		/* 
		$("#year")
				.change(
						function() {
							location.href = "/bigdataShop/comment/result.do?year="
									+ encodeURI($(this).val())
									+ "&month=" + month;
						});
		 */
		if (month == "") {
			month = "${month}";
		}
		$("#month").val(month).attr("selected", "selected");
		/* 
		$("#month")
				.change(
						function() {
							location.href = "/bigdataShop/comment/result.do?year="
									+ year
									+ "&month="
									+ encodeURI($(this).val());
						});
		 */
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<%
		List<ProductCommentResultDTO> list = (List<ProductCommentResultDTO>) request.getAttribute("commentresult");
	%>
	<%
		String year = request.getParameter("year");
	%>
	<%
		String month = request.getParameter("month");
	%>
	<div class="row">
		<h3>상품댓글분석</h3>

		<br />
		<div class="row">


			<form action="/bigdataShop/comment/result.do">
				<div class="col-sm-2">
					<select class="form-control" name="year" id="year">
						<option value="">년도선택</option>
						<option value="19">2019</option>
						<option value="20">2020</option>
						<option value="21">2021</option>
					</select>
				</div>
				<div class="col-sm-2">
					<select class="form-control" name="month" id="month">
						<option value="">월선택</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">월별보기</button>
				</div>
			</form>
			<div class="col-sm-6"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-2">
				<a class="btn btn-primary" href="/bigdataShop/comment/result.do">전체보기</a>
			</div>
			<div class="col-sm-6"></div>
		</div>
		<br />
		<hr />
		<%
			if (year == null | month == null) {
		%>
		<h3>전체기간</h3>
		<%
			} else if (year == "" | month == "") {
		%>
		<h3>전체기간</h3>
		<%
			} else {
		%>
		<h3>
			20<%=year%>.
			<%=month%></h3>
		<%
			}
		%>
		<div class="col-sm-5">
			<table class="table">
				<tr>
					<th>키워드</th>
					<th>반복횟수</th>
				</tr>
				<%
					if (list.size() != 0) {
				%>
				<c:forEach varStatus="mystatus" var="commentresult"
					items="${commentresult}">
					<tr>
						<td id="word">${commentresult.word}</td>
						<td id="count">${commentresult.count}</td>
					</tr>
				</c:forEach>
				<%
					}
				%>
			</table>

		</div>
		<div class="col-sm-7">
			<div id="example"
				style="width: 550px; height: 350px; border: 1px solid #ccc;">

			</div>
		</div>
	</div>
</body>
</html>