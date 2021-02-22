package com.kebzzang.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청->응답(HTML 파일)
//@Controller
//사용자가 요청->응답(데이터)
@RestController

public class HttpControllerTest {
    //인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다
    //http://localhost:8080/http/get
    private static final String TAG="HttpControllerTest:";
@GetMapping("/http/lombok")
    public String lombokTest(){
    //Member에서 @Build를 붙여서 이제 요런 식으로 쓸 수 있음
    //그전에는
    //Member m=new Member(1, "keb", "keb1234", "keb@nate.com"); 이렇게 넣었음
    //
        Member m=Member.builder().username("keb").password("1234").email("keb@nate.com").build();
        System.out.println(TAG+" : getter: "+m.getId()+": username: "+m.getUsername());
        m.setId(5000);
    System.out.println(TAG+"setter: "+m.getId());
        return "lombok test 완료"
;    }
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청: "+m.getId()+" user: "+m.getUsername()+" passowrd: " +m.getPassword()+" email: " + m.getEmail();
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){//MessageConverter가 이 맵핑을 한다.
        return "post 요청: "+m.getId()+" user: "+m.getUsername()+" passowrd: " +m.getPassword()+" email: " + m.getEmail();


    }
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청 "+m.getId()+" user: "+m.getUsername()+" passowrd: " +m.getPassword()+" email: " + m.getEmail();

    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
