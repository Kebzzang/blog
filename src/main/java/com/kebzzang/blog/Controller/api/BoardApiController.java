package com.kebzzang.blog.Controller.api;

import com.kebzzang.blog.Controller.dto.ResponseDto;
import com.kebzzang.blog.config.auth.PrincipalDetail;
import com.kebzzang.blog.model.Board;
import com.kebzzang.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //데이터만 리턴
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.savePost(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }



}
