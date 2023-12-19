package com.sparta.plusweekreviewassignment.dto;


import com.sparta.plusweekreviewassignment.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
  private String title;
  private String nickname;

  public BoardResponseDto(Board board) {
    this.title = board.getTitle();
    this.nickname = board.getNickname();
  }
}
