package kr.multi.bigdataShop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	@Qualifier("ProductDAO")
	ProductDAO dao;
	@Override
	public List<ProductDTO> productlist(String category) {
		return dao.productlist(category);
	}

	@Override
	public List<ProductDTO> hitproduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> newproduct() {

		return dao.newproduct();
	}

	@Override
	public ProductDTO read(String prd_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
