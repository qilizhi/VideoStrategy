package com.dz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dz.common.util.Result;
import com.dz.common.util.ResultCode;
import com.dz.entities.User;

@Controller
public class LoginController {

	private String pref="";
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("qilizhi","qilizhi");
        return "greeting";
    }
	@RequestMapping("/index")
    public String index() {
        return pref+"index";
    }
	@RequestMapping("/loginfail")
	@ResponseBody
    public Result indextest() {
        return new Result(ResultCode.FAIL,"登录失败");
    }
   
	@RequestMapping(value={"/login","/"})
	public String login(User user){
		
		return pref+"login";
	}
	/*@RequestMapping(value="/logout")
	public String logout(){
		return pref+"login";
		//return new Result(ResultCode.SUCCESS, "登出qq成功");
	}*/
	
	@RequestMapping(value="/content")
	public ModelAndView content(){
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("content","404");
	     ModelAndView mv=new ModelAndView("index",model);
		return mv;
	}
	
	
}
