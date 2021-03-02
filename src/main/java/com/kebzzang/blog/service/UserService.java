package com.kebzzang.blog.service;


import com.kebzzang.blog.repository.UserRepository;
import com.kebzzang.blog.model.RoleType;
import com.kebzzang.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌 -> IoC

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional //이 전체가 하나의 트랜잭션으로 처리
    public void SignUp(User user){
        String rawPassword=user.getPassword();

        String encPassword=encoder.encode(rawPassword); //해쉬된 비번
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);

    }
    //select 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
   /* @Transactional(readOnly=true)
    public User LogIn(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
    @Transactional
    public String idCheck(String userName){ //가입 시 아이디 중복 체크용
        System.out.println(userRepository.findByUsername(userName));
        if(userRepository.findByUsername(userName).isEmpty())
            return "YES";
        else return "NO";
    }

    @Transactional
    public void UpdateUser(User user){
        //수정 시에는 영속성 컨텍스트 내 유저 오브젝트를 영속화시키고 영속화된 유저 오브젝트를 수정
        User persistance=userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException("회원 찾기 실패"));



        //회원 수정 함수 종료시 === 서비스 종료시===트랜잭션이 종료됨 ===커밋이 자동으로 됨
        //영속화된 persistance 객체의 변화가 감지되면 더티체킹 ->update믄 날려줌
        if (persistance.getOauth()==null||persistance.getOauth().equals("")){  //카카오라고 적힌 애들은 이거 못함함
           String rawPassword=user.getPassword();
            String encPassword=encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
        }
        persistance.setEmail(user.getEmail());
    }
    @Transactional(readOnly = true)
    public User findUser(String username){
        User user=userRepository.findByUsername(username).orElseGet(User::new);
        return user;
    }
}
