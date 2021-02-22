package com.kebzzang.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> Java(다른언어) Object -> 테이블로!!
@Entity //User 클래스를 읽어 자동으로 mariaDB에 테이블 생성
public class User {
    @Id //Primary Key
    @GeneratedValue(strategy= GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 따라감->auto_increment
    //SEQUENCE ->
    //TABLE ->테이블에 번호를 만들어 그걸 쓰겠다
    //AUTO는 자동
    private int id; //auto-increment
    @Column(nullable = false, length=30)
    private String username; //id에 해당
    @Column(nullable=false, length=100) //password는 나중에 해쉬함수로 암호화할 예정
    private String password;
    @Column(nullable=false, length=50)
    private String email;
    @ColumnDefault("'user'") //문자임을 알리기 위해 "''"
    private String role; //사실 enum을 쓰는 게 좋음. 회원이 회원가입->기본적으로 어드민(관리자), 유저, 매니저 등등 권한을 줄 수 있음

    @CreationTimestamp ///시간이 자동으로 입력됨 테이블에 인서트될 때 id와 같이 비워놔도 자동으로 채워짐
    private Timestamp createDate;




}
