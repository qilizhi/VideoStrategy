package com.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dz.annotation.Layout;
import com.dz.common.util.Const;
import com.dz.common.util.Result;
import com.dz.common.util.ResultCode;
import com.dz.entities.User;
import com.dz.repository.UserResposity;
import com.sun.scenario.effect.Blend.Mode;

/**
 * 用户管理
 * 
 * @author qlz
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserResposity userService;
	private String prefView = "views/user/";

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */

	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("_userFirstMenus", Const.ACTIVE);
		model.addAttribute("_userOpen", Const.OPEN);
		model.addAttribute("_userSecondMenus", Const.ACTIVE);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Layout
	public String userList(Pageable pageBounds, Model model) {
		Page<User> usersPage = userService.findAll(pageBounds);
		model.addAttribute("pageList", usersPage);
		return prefView + "list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Layout
	public String add(Model model) {
		model.addAttribute("title", "新增");
		return prefView + "form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@Layout
	public String edit(@PathVariable("id") Long id, Model model) {
		User user = userService.getOne(id);
		model.addAttribute("title", "编辑");
		model.addAttribute("obj", user);
		return prefView + "form";
	}

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Result addOrUpdateUser(User user) {
		Result rs = new Result();
		try {
			if (user.getId() == null
					&& ("".equals(user.getPassword()) || user.getPassword() == null)) {
				user.setPassword("123456");
				String ps = user.getPassword();
				// md5 加密 username 作为salt
				PasswordEncoder pEncoder = new StandardPasswordEncoder();
				user.setPassword(pEncoder.encode(ps));
			}
			userService.saveOrUpdate(user);
			rs.setCode(ResultCode.SUCCESS);
			rs.setMsg(ResultCode.SUCCESS_MSG);

		} catch (Exception e) {
			e.printStackTrace();
			rs.setCode(ResultCode.FAIL);
			rs.setMsg(ResultCode.FAIL_MSG);
		}
		return rs;
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delUser(@PathVariable("id") Long id) {
		Result rs = new Result();
		try {
			userService.delete(id);
			rs.setCode(ResultCode.SUCCESS);
			rs.setMsg(ResultCode.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs.setCode(ResultCode.FAIL);
			rs.setMsg(ResultCode.FAIL_MSG);
		}
		return rs;

	}

}
