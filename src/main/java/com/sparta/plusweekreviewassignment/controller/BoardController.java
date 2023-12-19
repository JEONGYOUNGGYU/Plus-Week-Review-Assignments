package com.sparta.plusweekreviewassignment.controller;

import com.sparta.plusweekreviewassignment.dto.BoardResponseDto;
import com.sparta.plusweekreviewassignment.dto.CreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.UpdateRequestDto;
import com.sparta.plusweekreviewassignment.service.BoardService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<BoardResponseDto> createBoard(
      @RequestBody @Valid CreateRequestDto createRequestDto) {

    boardService.createBoard(createRequestDto);
    return null;
  }

  @PatchMapping("/{boardId}")
  public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long boardId,
      @RequestBody UpdateRequestDto updateRequestDto) {
    boardService.updateBoard(boardId, updateRequestDto);

    return null;
  }

  @DeleteMapping("/{boardId}")
  public Long deleteBoard(@PathVariable Long boardId){
    boardService.deleteBoard(boardId);
    return null;
  }
}