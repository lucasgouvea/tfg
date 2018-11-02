package br.edu.unifei.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class AppController {
 
    @RequestMapping("/")
    String home(ModelMap modal) {
    	
        //modal.addAttribute("title","webscreen");
        return "index";
        
    }
 
    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }
 
}