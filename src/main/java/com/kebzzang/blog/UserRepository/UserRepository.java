package com.kebzzang.blog.UserRepository;

import com.kebzzang.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //이 레파지토리는 유저 테이블을 관리하고 이 유저 테이들의 프라이머리 키는 인티저다
    //기본적인 CRUD
    //DAO (Data Access Object)
    //자동으로 bean 등록이 된다? =스프링 IoC에서 객체를 가지고 있나? yes
    //필요한 곳에서 DI
    //@Repository 생략 가능!!

    //JPA Naming 전략
    //select * from user where username=?;
    //select * from user where username = ? and password=?;
    Optional<User> findByUsername(String username);
    //User findByUsernameAndPassword(String username, String password);

    /*@Query(value="SELECT * FROM WHERE username= ?1 AND password= ?2", nativeQuery=true)
    User login(String username, String password);*/

}
