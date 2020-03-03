package kr.multi.bigdataShop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	@Autowired
	ProductService productservice;
	
	@RequestMapping("/product/list.do")
	public ModelAndView productlist(String category) {
		ModelAndView mav = new ModelAndView();
		
		List<ProductDTO> list = productservice.productlist(category);
		
		mav.addObject("productlist", list);
		mav.setViewName("product/list");
		return mav;
	}
}
