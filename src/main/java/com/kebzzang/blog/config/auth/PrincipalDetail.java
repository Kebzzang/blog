package com.kebzzang.blog.config.auth;

import com.kebzzang.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 유저디테일스 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다
@Getter
public class PrincipalDetail implements UserDetails {

    private User user; //콤포지션: 객체를 품고 있는 것

    public PrincipalDetail(User user){
        this.user=user;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //이건??/->계정이 만료되었는지 않았는지 리턴한다. true: 만료 안됨
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정이 잠겨있는지? TRUE 안잠김
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //비밀번호가 만료되지 않았는지 리턴한다. TRUE 만료안댐
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화되었는지 true 활성화
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //계정 권한 리턴 (여기선 권한 하나 밖에 없음!!) 권한이 여러개있다면 루프 돌아서..
       Collection<GrantedAuthority> collectors=new ArrayList() ;
        collectors.add(() -> {
            return "ROLE_"+user.getRole(); //이름 규칙 ROLE_USER, ROLE_ADMIN
        });

        return collectors;
    }
}
