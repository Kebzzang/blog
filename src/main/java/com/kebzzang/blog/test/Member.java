package com.kebzzang.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //게터 세터
//@AllArgsConstructor //생성자가 만들어짐
//@RequiredArgsConstructor //final 붙은 애들에 한해서 생성자를 만들어줌
@NoArgsConstructor //빈 생성자
public class Member {
    private  int id;
    private  String username;
    private  String password;
    private  String email;
//
    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
