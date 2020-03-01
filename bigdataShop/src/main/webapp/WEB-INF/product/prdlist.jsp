<%@ page language="java" contentType="text/html; charset=UTF-8" 
session="true" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" href="/bigdataShop/common/css/master.css"
	rel="stylesheet" media="screen,print" />
<link type="text/css" href="/bigdataShop/common/css/print.css"
	rel="stylesheet" media="print" />
<link type="text/css" href="/bigdataShop/common/css/mall.css"
	rel="stylesheet" media="screen,print" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default rounded borders and increase the bottom margin */
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}
/* Remove the jumbotron's default bottom margin */
.jumbotron {
	margin-bottom: 0;
}
/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}
#outer {
	width: 95%;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	
	<div class="goodsListArea">
		<!-- 조회 결과 메세지 및 순서 -->
		<div class="goodsListHead">
			<p>
				총<span></span> 개의 상품이 있습니다.
			</p>
			<ul>
				<li class="first selected"><a href="#"
					onclick="dataSort( 1, 'PR_INDATE desc' ); return false;">등록순</a></li>
				<li class=""><a href="#"
					onclick="dataSort( 2, 'NVL( PRLP_SALEPRICE1, 0) desc' ); return false;">높은가격순</a></li>
				<li class=""><a href="#"
					onclick="dataSort( 3, 'NVL( PRLP_SALEPRICE1, 0) asc' ); return false;">낮은가격순</a></li>
				<li class=""><a href="#"
					onclick="dataSort( 4, 'PR_NAME asc' ); return false;">상품명순</a></li>
				<li class="typeGallery"><a href="#"
					onclick="dataViewType( 2 );return false;"> <img
						src="/bigdataShop/images/icn_gllry_2.gif" alt="갤러리 형" />
				</a></li>
				<li class="typeList"><a href="#"
					onclick="dataViewType( 1 );return false;"> <img
									src="/bigdataShop/images/icn_list_2.gif" alt="리스트 형" />

							</a></li>
				<li class="last"><select name="r_limitrow"
					onchange="pageLimitRow();" title="개수">
						<option value="10">10개</option>
						<option value="20" selected='selected'>20개</option>
						<option value="50">50개</option>
						<option value="100">100개</option>


				</select></li>
			</ul>
		</div>
		<div class="boardAreaList">
			<!-- 상품리스트 [리스트 형] 시작 -->

			<!-- 상품리스트 [리스트 형] 끝 -->

			<!-- 상품리스트 [갤러리 형] 시작 -->

			<ul class="goodsAreaG">
							<!-- *상품 있을경우 -->
				<li><a href="#" class="goodsLink"
					onclick="formGetSubmit( '/commerce/foffice/product/product.lime', 'r_prcode=G4135_F0002_X0004_K0040' )">
						<img src="/bigdataShop/images/product/bottom_image1.jpg" alt="상품"
						class="photo" /><br /> <span class="proPrice1"></span>
				</a><br /> <span class="proPrice2">원</span>


					<ul class="bIcon">


						<li><a href="#"
							onclick="basketIn( 'G4135_F0002_X0004_K0040' );return false;"><img
								src="/bigdataShop/images/bcon_cart.gif" alt="장바구니" /></a></li>
						<li><a href="#" onclick="imageZoom( '1010' );"><img
								src="/bigdataShop/images/bcon_zoom.gif" alt="확대보기" /></a></li>

					</ul>
				</li>
		
			</ul>
		</div>
	</div>
	
		
	<footer class="container-fluid text-center">
			<p>Online Store Copyright</p>
			<form class="form-inline">
				Get deals: <input type="email" class="form-control" size="50"
					placeholder="Email Address">
				<button type="button" class="btn btn-danger">Sign Up</button>
			</form>
		</footer>

</body>
</html>