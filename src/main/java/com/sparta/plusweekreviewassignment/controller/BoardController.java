package com.sparta.plusweekreviewassignment.controller;

import com.sparta.plusweekreviewassignment.dto.BoardResponseDto;
import com.sparta.plusweekreviewassignment.dto.CreateRequestDto;
import com.sparta.plusweekreviewassignment.service.BoardService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateRequestDto createRequestDto){

      boardService.createBoard(createRequestDto);
    return null;
  }
}
