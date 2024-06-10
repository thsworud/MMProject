package com.shop.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
public class MainPageController {
    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    MMMemberRepository repository;
    
    @GetMapping(value = "/main/mainpage")
    public ModelAndView main(HttpSession session, ModelAndView mav) {
        MMMember user = (MMMember) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/members/index");
        }
        mav.setViewName("main/mainpage");
        Iterable<MMMember> list = repository.findAll();
        mav.addObject("member", list);
        mav.addObject("user", user);
        return mav;
    }

}
