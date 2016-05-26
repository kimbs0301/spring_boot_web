package com.example.boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jsps")
public class JspController {

	
//	@RequestMapping(value = "/jsptest", method = RequestMethod.GET)
//	public ModelAndView getJsp() {
//		
//		
//		ModelAndView view = new ModelAndView();
//		view.setViewName("test");
//		
//		return view;
//	}
	
	@RequestMapping(value = "/jsptest", method = RequestMethod.GET)
	public String getJsp() {
		
		return "test";
	}
}
