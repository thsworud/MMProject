package com.shop.demo;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloController {

//로그에 8090까지만 입력했을때 나타는 페이지(index.html)
@GetMapping("/")
public String index() {
	log.info("index controller");
	return "index";
}

}
