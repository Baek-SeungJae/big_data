package kr.multi.bigdataShop.product.comment;

import java.util.List;

public interface ProductCommentDAO {
	List<ProductCommentDTO> commentlist(String prd_no);
	int insert(ProductCommentDTO dto);
	List<ProductCommentResultDTO> commentResult();
	List<ProductCommentResultDTO> commentResult(String year, String month);
}
