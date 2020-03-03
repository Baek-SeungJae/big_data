package kr.multi.bigdataShop.product;

import java.util.List;

public interface ProductDAO {
	//상품목록을 조회하는 메소드
	List<ProductDTO> productlist (String category);
	//히트상품의 목록을 조회하는 메소드
	List<ProductDTO> hitproduct();
	//new상품의 목록을 조회하는 메소드
	List<ProductDTO> newproduct();
	//상품을 클릭했을때 상세보기를 하기 위한 메소드
	ProductDTO read(String prd_no);
}