package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

@Controller
public class CommonController {


	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/index")
	public String index(@RequestParam(defaultValue="M000000")String mCode,Model model)
																throws SQLException{
		String url="common/indexPage";
	
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();			
			MenuVO menu = menuService.getMenuByMcode(mCode);
			
			model.addAttribute("menuList",menuList);
			model.addAttribute("menu",menu);
		} catch (SQLException e) {			
			e.printStackTrace();			
			throw e;
		}
		return url;
	}
}
