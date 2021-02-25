package com.kebzzang.blog.Controller.api;

import com.kebzzang.blog.Controller.dto.ResponseDto;
import com.kebzzang.blog.model.User;
import com.kebzzang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //데이터만 리턴
public class UserApiController {

    @Autowired //빈에 등록된 것 DI
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encode;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출됨");
        //실제 디비에 인서트 및 리턴

       userService.SignUp(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }
    @GetMapping("/auth/idCheck")
    public String id_check(String username){
        System.out.println(username);
        return userService.idCheck(username);
    }


}
