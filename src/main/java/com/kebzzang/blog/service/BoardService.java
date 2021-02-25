package com.kebzzang.blog.service;


import com.kebzzang.blog.UserRepository.BoardRepository;
import com.kebzzang.blog.model.Board;
import com.kebzzang.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌 -> IoC

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional //이 전체가 하나의 트랜잭션으로 처리
    public void savePost(Board board, User user){ //타이틀과 컨텐트만 받음 -> 이 글을 누가 썼는지 유저 정보도 가져와야 하지 않니?//

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

    public Board viewBoard(int id){
        return boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
    }

    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

}
