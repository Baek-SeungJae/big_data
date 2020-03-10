package kr.multi.bigdataShop.product;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ProductDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	SqlSession sqlSession;
	@Override
	public List<ProductDTO> productlist(String category) {
		if(category.equals("all")) {
			return sqlSession.selectList("kr.multi.bigdataShop.product.allsearch");
		}
		else {
			return sqlSession.selectList("kr.multi.bigdataShop.product.categorysearch",category);
		}
	}

	@Override
	public List<ProductDTO> hitproduct() {
		return sqlSession.selectList("kr.multi.bigdataShop.product.hitproduct");
	}

	@Override
	public List<ProductDTO> newproduct() {
		return sqlSession.selectList("kr.multi.bigdataShop.product.newproduct");
	}

	@Override
	public ProductDTO read(String prd_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
