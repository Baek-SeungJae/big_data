package spring.data.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.data.mongodb.dto.ScoreDTO;
import spring.data.mongodb.service.ScoreMongoService;

@Controller
public class ScoreFindController {
	@Autowired
	ScoreMongoService service;

	@RequestMapping(value = "/score/search", method = RequestMethod.GET)
	public String search() {
		return "search";
	}
	@RequestMapping(value = "/score/search", method = RequestMethod.POST)
	public ModelAndView search(String field, String value, String opr) {
		System.out.println(opr);
		System.out.println(field+" "+value);
		ModelAndView mav = new ModelAndView();
		List<ScoreDTO> list = service.findCriteria(field, value);
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;
	}
	@RequestMapping(value = "/score/detail", method = RequestMethod.GET)
	public ModelAndView findbyId(String key, String value, String action) {
		
		ScoreDTO doc = service.findById(key, value);
		String view = "";
		if (action.equals("READ")) {
			view = "mongo_detail";
		} else {
			view = "mongo_update";
		}
		return new ModelAndView(view, "document", doc);
	}

	
}
