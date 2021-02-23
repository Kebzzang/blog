package com.kebzzang.blog.test;

import com.kebzzang.blog.UserRepository.UserRepository;
import com.kebzzang.blog.model.RoleType;
import com.kebzzang.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController //데이터를 리턴
public class DummyControllerTest {
    @Autowired //의존성 주입 (DI) 메모리에 떴다 !
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }
    @GetMapping("dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id", direction= Sort.Direction.DESC) Pageable pageable){
       Page<User> users= userRepository.findAll(pageable);
       return users;
    }
    //http://localhost:8000/blog/dummy/join 에 리퀘스트
    //

   /* public String join(String username, String password, String email){ //key=value 형태로 데이터 규칙
    System.out.println("username: "+username+ " password: "+password+" email: "+email);


        return "회원가입이 완료!!";

    }*/
    //{id} 주소로 파라미터를 전달 받을 수 있음
    //localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

        //리턴 타입이 옵셔널!! -> user/5가 없는데 찾으면 user가 null
        // ->return null이 되지 않도록 Optional로 user객체를 감싸서 가져오겠다 null인지는 니가 판단해
        User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
            @Override
            public IllegalArgumentException get(){
                return new IllegalArgumentException("해당 유저는 없습니다 id: "+id);
            }
        });
       /*//같은 코드 람다식
        User user=userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다.");
    });*/
        //MessageConverter가 Jackson 라이브러리를 호출해서
        //user 오브젝트를 json로 변환해서 브라우저에게 던져줌
        return user;


    }
    @PostMapping("/dummy/join") //유저를 인서트이므로 포스트맵핑
    public String join(User user){ //오브젝트로 받아서 처리함
        System.out.println("id: "+user.getId());
        System.out.println("username: "+user.getUsername()+
                " password: "+user.getPassword()+
                " email: "+user.getEmail());
        System.out.println("role: "+user.getRole());
        System.out.println("createDate: "+user.getCreateDate());
        user.setRole(RoleType.USER); //user.setRole("user");
        userRepository.save(user);
        return "회원가입이 완료!!";
    }
}
