package com.shop.demo;




import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@Validated
@RequiredArgsConstructor
public class MemberController {
	private static final Logger log=LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MMMemberRepository repositroy;
	
	@GetMapping("/members/index")
	public String index(Model model) {
		return "index";
	}
	@GetMapping("/members")
	public String members(Model model) {
		return "/members/createmember";
	}
	@GetMapping(value="/members/new")
	public ModelAndView members1(
			@ModelAttribute("memberForm")MMMember member,ModelAndView mav) {
			mav.setViewName("members/creatememberForm");
			mav.addObject("msg","sample content.");
			Iterable<MMMember>list=repositroy.findAll();
			mav.addObject("datalist",list);
			return mav;
	}
	@PostMapping(value="/members/new")
	@Transactional(readOnly=false)
	public ModelAndView members(MMMember member, ModelAndView mav) {
		repositroy.saveAndFlush(member);
		return new ModelAndView("redirect:/members");
	}
//추가
	@PostMapping("/members/login")
	public String login(@RequestParam(name="username") String username,
	                    @RequestParam(name="password") String password,
	                    HttpSession session, Model model) {
	    Optional<MMMember> member = repositroy.findById(username);

	    if (member.isPresent() && member.get().getPassword().equals(password)) {
	        session.setAttribute("user", member.get());
	        log.info("log12222222222222222222 : " + member.get().getId());
	        return "redirect:/main/mainpage"; // 로그인 성공 시 메인 페이지로 이동
	    } else {
	        model.addAttribute("error", "Invalid username or password");
	        return "redirect:/members/index?error"; // 로그인 실패 시 로그인 페이지로 다시 이동
	    }
	}
	@GetMapping(value="/members/edit/{id}")
	public ModelAndView edit(@ModelAttribute MMMember member,@PathVariable("id")String id,ModelAndView mav) {
		mav.setViewName("members/edit");
		MMMember list=repositroy.findById1((String)id);
		log.info("list111111111111111:"+list);
		mav.addObject("member",list);
		return mav;
	}
	@PostMapping(value="/members/edit")
	@Transactional(readOnly=false)
	public ModelAndView members3(MMMember member, ModelAndView mav) {
		repositroy.saveAndFlush(member);
		return new ModelAndView("redirect:/main/editmain");
	}
	@GetMapping("/main/editmain")
	public String members5(Model model) {
		return "/main/editmain";
	}
}
