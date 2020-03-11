package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class ProductCommentServiceImpl implements ProductCommentService {
	@Autowired
	@Qualifier("productcommentdao")
	ProductCommentDAO dao;
	@Override
	public List<ProductCommentDTO> commentlist(String prd_no) {
		return dao.commentlist(prd_no);
	}

	@Override
	public int insert(ProductCommentDTO dto) {
		return dao.insert(dto);
	}
	@Override
	public List<ProductCommentResultDTO> commentResult() {
		return dao.commentResult();
	}
	@Override
	public List<ProductCommentResultDTO> commentResult(String year, String month) {
		return dao.commentResult(year,month);
	}
}
