package com.nse;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nse.repository.MarketDataRepository;

@Controller
public class ControllerJsp {
	@Autowired
	MarketDataRepository marketDataRepository;
	@RequestMapping("/")
    public String home(Map<String, Object> model) {
		System.out.println("ggg");
	 Date date = marketDataRepository.findMaxCreateDate();
		Calendar cal = Calendar.getInstance();
		
		 int diffInDays = (int) ((cal.getTimeInMillis() - date.getTime()) / (1000 * 60 * 60 * 24));
		
		 model.put("date", date);
		 model.put("date1", diffInDays);
		 model.put("date2", "0");
        model.put("message", "HowToDoInJava Reader !!");
        return "index";
    }
     
    @RequestMapping("/next")
    public String next(Map<String, Object> model) {
    	System.out.println("gggw");
        model.put("message", "You are in new page !!");
        return "next";
    }
}
