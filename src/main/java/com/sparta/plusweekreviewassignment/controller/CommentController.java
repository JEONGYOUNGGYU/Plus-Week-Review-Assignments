package com.sparta.plusweekreviewassignment.controller;

import com.sparta.plusweekreviewassignment.dto.CommentCreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.CommentReponseDto;
import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{boardId}")
  public CommentReponseDto createComment(@PathVariable Board boardId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {
    commentService.createComment(boardId,commentCreateRequestDto);

    return null;
  }

  // 해당 게시글에 등록된 댓글만 보여주기
  // ex) 1번 게시글에 등록 된 댓글들 촤라락
  @GetMapping("/{boardId}")
  public List<CommentReponseDto> getBoard(@PathVariable Board boardId, Long commentId) {
    return commentService.getBoard(boardId, commentId);

  }
}
