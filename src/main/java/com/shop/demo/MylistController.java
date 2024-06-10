package com.shop.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MylistController {
    private static final Logger log = LoggerFactory.getLogger(MylistController.class);

    @Autowired
    private MyListRepository repository;


    //운동일지 페이지
    @GetMapping(value = "/List/{id}")
    public ModelAndView list(@PathVariable("id") String id, MyList mylist, ModelAndView mav) {
        mav.setViewName("List/mylist");
        List<Map<String, Object>> list = repository.findByList((String)id);
        String list1 = repository.findByIdList((String)id);
        mav.addObject("List", list);
        mav.addObject("List1", list1);
        return mav;
    }
    //운동일지 추가하기 페이지
    @GetMapping(value = "/List/createlist/{List1}")
    public ModelAndView mylist(@ModelAttribute("createlist") MyList mylist, @PathVariable("List1") String List1, ModelAndView mav) {
        mav.setViewName("List/createlist");
        Iterable<MyList> list2 = repository.findById(List1);
        Iterable<MyList> list = repository.findAll();
        String list0 = repository.findByIdList(List1);
        // list 값이 가장 큰 MyList 객체를 가져옴
        Optional<MyList> largestList = repository.findTopByOrderByListDesc();
        mav.addObject("list", list);
        mav.addObject("list2", list2);
        mav.addObject("mylist1", list0);
        mav.addObject("largestList", largestList.orElse(null));
        return mav;
    }
    //울동일지 추가하기 저장 
    @PostMapping(value="/List/createlist")
    @Transactional(readOnly=false)
    public String save(@ModelAttribute("list") MyList mylist) {
    	repository.saveAndFlush(mylist);
    	return "redirect:/List/"+mylist.getId();
    }
    //edit 폼
    @GetMapping(value="/List/edit/{list}")
    public ModelAndView edit(@ModelAttribute MyList mylist,@PathVariable("list")int list,ModelAndView mav) {
    	mav.setViewName("List/edit");
    	MyList list1=repository.findByList1((int)list);
    	mav.addObject("mylist",list1);
		return mav;
    }
    //edit 에서 데이터베이스 넣고 list로 넘어가기
    @PostMapping(value="/List/edit/{list}")
    @Transactional(readOnly=false)
    public ModelAndView update(@ModelAttribute MyList mylist,@PathVariable("list")int list, ModelAndView mav) {
    		repository.saveAndFlush(mylist);
    		return new ModelAndView("redirect:/List/"+mylist.getId());
    }
    // delete 폼
    @GetMapping(value="/List/delete/{list}")
    public ModelAndView delete(@PathVariable("list") int list, ModelAndView mav) {
        mav.setViewName("List/delete");
        MyList data = repository.findByList1(list);
        mav.addObject("mylist", data);
        log.info("Delete form data: " + mav);
        return mav;
    }

    // delete 실행
    @PostMapping(value="/List/delete/{list}")
    @Transactional
    public ModelAndView remove(@ModelAttribute MyList mylist, @RequestParam("list") int list, ModelAndView mav) {
        log.info("Deleting entry with list: " + list);
        repository.deleteByList(list);
        return new ModelAndView("redirect:/List/" + mylist.getId());
    }
    
}


