package com.young.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.young.animal.service.AccountService;
import com.young.animal.service.BoardConfigService;
import com.young.animal.vo.Account;
import com.young.animal.vo.BoardConfigVO;
import com.young.animal.vo.RoleAccount;

@Controller
public class MemberController {
	@Autowired
	private AccountService accService;
	@Autowired
	private BoardConfigService boardConfigService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = {"/", "/login"})
	public ModelAndView login(@RequestParam(value = "error", required = false)	String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}
	@RequestMapping(value="/admin**")
	public String admin() {
		logger.info("BoardConfigController.admin");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/list")
	public String home(Model model, HttpServletRequest request) {
		logger.info("MemberController.admin.list");
		List<BoardConfigVO> list = boardConfigService.selectList();
		model.addAttribute("list", list);
		logger.info("MemberController.list : " + list);
		return "adminList";
	}
	@RequestMapping(value = "/admin/insert")
	public String insert(Model model, HttpServletRequest request) {
		logger.info("BoardConfigController.admin.insert");
		return "adminInsert";
	}
	@RequestMapping(value = "/admin/insertOK")
	public String insertOK(@ModelAttribute BoardConfigVO vo, Model model, HttpServletRequest request) {
		logger.info("BoardConfigController.admin.insertOK");
		boardConfigService.insert(vo);
		return "redirect:/admin/list";
	}
	@RequestMapping(value = "/admin/delete")
	public String delete(Model model, HttpServletRequest request) {
		logger.info("BoardConfigController.admin.delete");
		List<BoardConfigVO> list = boardConfigService.selectList();
		model.addAttribute("list", list);
		return "adminDelete";
	}
	@RequestMapping(value = "/admin/deleteOK")
	public String deleteOK(@ModelAttribute BoardConfigVO vo, Model model, HttpServletRequest request) {
		logger.info("BoardConfigController.admin.deleteOK");
		boardConfigService.deleteTable(vo);
		return "redirect:/admin/list";
	}
	@RequestMapping(value = "/admin/memberlist")
	public String memberlist(Model model, HttpServletRequest request) {
		logger.info("MemberController.admin.memberlist");
		List<RoleAccount> list = accService.selectMemberList();
		model.addAttribute("list", list);
		logger.info("MemberController.admin.memberlist");
		return "adminmemberList";
	}
	@RequestMapping(value = "/admin/memberupdate")
	public String memberupdate(RoleAccount ra, Model model, HttpServletRequest request) {
		accService.roleupdate(ra);
		return "redirect:/admin/memberlist";
	}
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "403";
	}
	@RequestMapping(value="/account")
	public String account() {
		return "account";
	}
	@RequestMapping(value="/accountOk", method = RequestMethod.GET)
	public String accountOkGet() {
		return "redirect:/account";
	}
	@RequestMapping(value="/accountOk", method = RequestMethod.POST)
	public String accountOkPost(Account ac, RoleAccount ra) {
		accService.insert(ac, ra);
		return "redirect:/login";
	}
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}