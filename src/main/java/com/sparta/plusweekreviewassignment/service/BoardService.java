package com.sparta.plusweekreviewassignment.service;

import com.sparta.plusweekreviewassignment.dto.BoardResponseDto;
import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.repository.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public List<BoardResponseDto> getBoards() {
    List<Board> boardList = boardRepository.findAll();

    List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

    for (Board board: boardList) {
      boardResponseDtos.add(new BoardResponseDto(board));
    }
    return boardResponseDtos;
  }
}
