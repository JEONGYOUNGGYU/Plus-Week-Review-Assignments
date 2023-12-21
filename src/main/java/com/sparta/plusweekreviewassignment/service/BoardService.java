package com.sparta.plusweekreviewassignment.service;

import com.sparta.plusweekreviewassignment.dto.BoardResponseDto;
import com.sparta.plusweekreviewassignment.dto.BoardCreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.BoardUpdateRequestDto;
import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.repository.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public void createBoard(BoardCreateRequestDto boardCreateRequestDto) {

    Board board = new Board(boardCreateRequestDto);
    boardRepository.save(board);

  }

  @Transactional
  public void updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = findById(boardId);

        if(board.getBoardId().equals(boardId)) {
          board.update(boardUpdateRequestDto);
        }
  }

  public void deleteBoard(Long boardId) {
    Board board = findById(boardId);

    if(board.getBoardId().equals(boardId)) {
      boardRepository.delete(board);
    }
  }

  private Board findById(Long boardId){
    return boardRepository.findById(boardId).orElseThrow(() ->
        new IllegalArgumentException("존재하지 않는 게시글입니다."));
  }
}
