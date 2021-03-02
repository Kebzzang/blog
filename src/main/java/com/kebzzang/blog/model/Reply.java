package com.kebzzang.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false, length=500)
    private String content;

    @ManyToOne //여러개의리플라이는 한개의 게시글에 속함
    @JoinColumn(name="boardId") //포린키
    private Board board;

    @ManyToOne //여러개의 리플라이를 한 유저가 쓴다
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createTime;
    @Override
    public String toString() {
        return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
                + createTime + "]";
    }

}
