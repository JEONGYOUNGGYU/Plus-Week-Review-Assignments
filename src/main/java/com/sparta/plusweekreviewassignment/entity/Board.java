package com.sparta.plusweekreviewassignment.entity;

import com.sparta.plusweekreviewassignment.dto.CreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.UpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class Board extends TimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @Column
  private String title;

  @Column
  private String content;

  @Column
  private String nickname;

  public Board(CreateRequestDto createRequestDto) {
    super();
    this.title = createRequestDto.getTitle();
    this.content = createRequestDto.getContent();
  }

  public void update(UpdateRequestDto updateRequestDto) {
    this.title = updateRequestDto.getTitle();
    this.content = updateRequestDto.getContent();
  }
}
