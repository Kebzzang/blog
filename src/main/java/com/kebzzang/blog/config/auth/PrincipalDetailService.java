package com.kebzzang.blog.config.auth;


import com.kebzzang.blog.repository.UserRepository;
import com.kebzzang.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //빈에 등록
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    //스프링이 로그인 요청을 가로챌 때, username. password 변수 두 개를 가로채는데
    //password 부분 처리는 알아서 함
    //username이 디비에 있는지 확인해주면 됨

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal =userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: "+username));
        return new PrincipalDetail(principal); //시큐리티의 세션에 유저 정보가 저장이 됨. //유저 디테일즈 타입
    }


}
