package com.kebzzang.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data //게터 세터
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //제일 밑에 있는 것이 좋다
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false, length=100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 <html 태그가 섞여서 디자인됨 그래서 크기가 매우 큼

    private int count; //이건 조회수

    @ManyToOne(fetch=FetchType.EAGER) //보드가 Many 유저는 One.
    //항상 함께 로드되어야 하는 조건을 가진 엔티티에 대해선 EAGER 방식
    @JoinColumn(name="userId") //유저 테이블의 id를 참조해 포린키 userId를 만들겠다
    private User user; //DB는 오브젝트를 저장할 수 없음. FK, 자바는 오브젝트를 저장할 수 있음음
    //유저 테이블을 참고 -> 포린키로 유저아이디 생성

    @OneToMany(mappedBy="board", fetch=FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다 (=난 FK 가 아님)
    // DB에 리플라이 컬럼 만들지 말라 나는 그냥 보드를 셀렉트할 때 조인문을 통해 값을 얻기 위해 필요함!!
    //리플라이 클래스에 있는 private Board board에서 board, 페치타입은 레이지 -> 필요하면 가져오겠다
    //하지만 우리는 게시글 상세보기에 댓글을 펼쳐서 보기가 아니라 바로 보기이므로 EAGER로 함
    //@JoinColumn //이 어노테이션이 들어오면 정규성 위배
    private List<Reply> reply; //게시글 보드는 리플라이 정보도 들고 있어야 함
    //리플라이는 딱 하나가 아니므로 리스트로 가지고 있어야함
    @CreationTimestamp
    private Timestamp createDate;


}
