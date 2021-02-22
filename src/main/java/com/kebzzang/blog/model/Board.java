package com.kebzzang.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false, length=100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 <html 태그가 섞여서 디자인됨 그래서 크기가 매우 큼

    @ColumnDefault("0")
    private int count; //이건 조회수

    @ManyToOne //보드가 Many 유저는 One
    @JoinColumn(name="usreId")
    private User user; //DB는 오브젝트를 저장할 수 없음. FK, 자바는 오브젝트를 저장할 수 있음음


    @CreationTimestamp
    private Timestamp createDate;


}
