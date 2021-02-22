package com.kebzzang.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //스프링이 com.cos.blog 패키지를 스캔 -> 특정 어노테이션이 붙어 있는 클래스 파일들을 new해서 (IoC)
                // Spring container에서 관리
public class BlogControllerTest {
    @GetMapping("/test/hello")
    public String hello(){
        return "<h1> hello spring boot nice to meet you</h1>";
    }
}
