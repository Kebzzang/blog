package com.kebzzang.blog.service;


import com.kebzzang.blog.UserRepository.BoardRepository;
import com.kebzzang.blog.config.auth.PrincipalDetail;
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
    @Transactional(readOnly = true)
    public Board viewPost(int id){
        return boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
    }
    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public void deletePost(int id, PrincipalDetail principal){
        Board board=boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("글 찾기 실패: 해당 글이 존재하지 않습니다."));
        if(board.getUser().getId()!=principal.getUser().getId()){
            throw new IllegalArgumentException("글 삭제 실패: 해당 글을 삭제할 권한이 없습니다.");
        }
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(int id, Board requestBoard){
        Board board=boardRepository.findById(id) //영속화 완료 (영속성 컨텍스트에 들어옴)
                .orElseThrow(()-> new IllegalArgumentException("글 수정 실패: 아이디를 찾을 수 없습니다."));
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());

        //해당 함수로 종료시 , 서비스가 종료시, 트랜재션이 종료 => 이때 더티 체킹 자동 업데이트 flush
    }
}
