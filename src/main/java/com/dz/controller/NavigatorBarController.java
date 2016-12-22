package com.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dz.annotation.Layout;
/**
 * 导航配置controller
 * @author qlz
 *
 */
@Controller
@RequestMapping("/nav")
@Layout(value="/layout/main",viewAttribute="content")
public class NavigatorBarController {
	
	@RequestMapping("/layout")
	public String layout(){
		return "hello";
	}
	@RequestMapping("/tables")
	@Layout
	public String tables(Model model){
	model.addAttribute("tables", "active");
	return "/views/tables";	
	}

	@RequestMapping("/calendar")
	public String calendar(Model model){
		model.addAttribute("calendar", "active");
		return "views/calendar";
	}
	@RequestMapping("/form")
	public String form(){
		return "views/form";
	}
	@RequestMapping("/chat")
	public String chat(){
		return "views/chat";
	}
}
