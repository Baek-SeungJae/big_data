package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductCommentController {
	@Autowired
	ProductCommentService commentservice;
	
	@RequestMapping(value="/comment/write.do", method=RequestMethod.POST)
	public String insert(ProductCommentDTO dto) {
		commentservice.insert(dto);
		return "redirect:/product/read.do?prd_no="+dto.getPrd_no();
	}
	
	@RequestMapping(value="/comment/result.do")
	public ModelAndView commentresult(String year, String month) {
		ModelAndView mav = new ModelAndView();
		List<ProductCommentResultDTO> dto = null;
		if(year==null||month==null) {
			dto = commentservice.commentResult();
		}
		else if(year==""||month=="") {
			dto = commentservice.commentResult();
		}
		else {
			dto = commentservice.commentResult(year,month);
		}
		mav.addObject("commentresult", dto);
		mav.setViewName("comment/result");
		return mav;
	}
	
}
