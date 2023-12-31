package com.sparta.plusweekreviewassignment.controller;

import com.sparta.plusweekreviewassignment.dto.BoardCreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.BoardResponseDto;
import com.sparta.plusweekreviewassignment.dto.BoardUpdateRequestDto;
import com.sparta.plusweekreviewassignment.dto.CommonResponseDto;
import com.sparta.plusweekreviewassignment.security.UserDetailsImpl;
import com.sparta.plusweekreviewassignment.service.BoardService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping
  public List<BoardResponseDto> getBoards() {
    return boardService.getBoards();

  }

  @PostMapping
  public ResponseEntity<CommonResponseDto> createBoard(@RequestBody @Valid BoardCreateRequestDto createRequestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

    try {
      boardService.createBoard(createRequestDto, userDetails);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    return ResponseEntity.ok().body(new CommonResponseDto("작성이 완료되었습니다", 200));
  }

  @PatchMapping("/{boardId}")
  public ResponseEntity<CommonResponseDto> updateBoard(@PathVariable Long boardId,
                                                      @RequestBody BoardUpdateRequestDto updateRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    try {
      boardService.updateBoard(boardId, updateRequestDto, userDetails);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    return ResponseEntity.ok().body(new CommonResponseDto("수정 성공하였습니다.", 200));

  }

  @DeleteMapping("/{boardId}")
  public ResponseEntity<CommonResponseDto> deleteBoard(@PathVariable Long boardId) {
    try {
      boardService.deleteBoard(boardId);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    return ResponseEntity.ok().body(new CommonResponseDto("삭제 성공하였습니다.", 200));
  }
}