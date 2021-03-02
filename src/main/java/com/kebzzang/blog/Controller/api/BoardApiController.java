package com.kebzzang.blog.Controller.api;

import com.kebzzang.blog.Controller.dto.ReplySaveRequestDto;
import com.kebzzang.blog.Controller.dto.ResponseDto;
import com.kebzzang.blog.config.auth.PrincipalDetail;
import com.kebzzang.blog.model.Board;
import com.kebzzang.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController //데이터만 리턴
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.savePost(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }
    //데이터 받을 때 컨트롤러에서 dto를 만들어서 받는 게 좋다.
    //dto는 프로젝트가 커질 때 많은 필드들, 수많은 데이터들을 떙겨 오는 데 효과적임
    //
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.saveReply(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.deletePost(id, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> updateById(@PathVariable int id, @RequestBody Board board){
        boardService.updatePost(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        boardService.deleteReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }



}
