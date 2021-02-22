package com.kebzzang.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //이제 파일을 리턴할 것
public class TempController {
    //http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("tempHome()");
        //파일 리턴 기본 경로: src/main/resources/static
        return "/home.html";
    }
    @GetMapping("/temp/img")
    public String tempImg(){
        System.out.println("tempImg()");
        return "/img.png";
    }
    @GetMapping("/temp/jsp")
    public String tempJsp(){
        System.out.println("tempJsp()");
        return "/test";
    }
}
