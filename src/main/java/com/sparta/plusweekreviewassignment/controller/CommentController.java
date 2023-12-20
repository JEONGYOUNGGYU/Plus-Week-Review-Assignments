package com.sparta.plusweekreviewassignment.controller;

import com.sparta.plusweekreviewassignment.dto.CommentCreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.CommentModifyRequestDto;
import com.sparta.plusweekreviewassignment.dto.CommentReponseDto;
import com.sparta.plusweekreviewassignment.dto.CommonResponseDto;
import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.entity.Comment;
import com.sparta.plusweekreviewassignment.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  public ResponseEntity<CommonResponseDto> createComment(@PathVariable Board boardId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {
    try {
      commentService.createComment(boardId,commentCreateRequestDto);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new CommonResponseDto("댓글 작성에 실패하였습니다.",401));
    }
    return ResponseEntity.ok().body(new CommonResponseDto("댓글 작성에 성공하였습니다.", 200));
  }

  // 해당 게시글에 등록된 댓글만 보여주기
  // ex) 1번 게시글에 등록 된 댓글들 촤라락
  @GetMapping("/{boardId}")
  public List<CommentReponseDto> getComment(@PathVariable Board boardId, Long commentId) {
    return commentService.getComment(boardId, commentId);

  }

  @PatchMapping("/{boardId}/{commentId}")
  public ResponseEntity<CommonResponseDto> modifyComment(@PathVariable Board boardId,
                            @PathVariable Comment commentId,
                            @RequestBody CommentModifyRequestDto commentModifyRequestDto){
    try {
      commentService.modifyComment(boardId, commentId, commentModifyRequestDto);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new CommonResponseDto("댓글 수정에 실패하였습니다.", 401));
    }
    return ResponseEntity.ok().body(new CommonResponseDto("댓글 수정에 성공하였습니다.", 200));
  }

  @DeleteMapping("/{boardId}/{commentId}")
  public ResponseEntity<CommonResponseDto> deleteComment(@PathVariable Board boardId,
                            @PathVariable Comment commentId) {
    try {
      commentService.deleteComment(boardId, commentId);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new CommonResponseDto("댓글 삭제에 실패하였습니다.", 401));
    }
    return ResponseEntity.ok().body(new CommonResponseDto("댓글 삭제에 성공하였습니다.", 200));
  }
}
