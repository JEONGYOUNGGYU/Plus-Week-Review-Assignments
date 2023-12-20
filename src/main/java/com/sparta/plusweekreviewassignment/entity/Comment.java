package com.sparta.plusweekreviewassignment.entity;

import com.sparta.plusweekreviewassignment.dto.CommentCreateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column
  private String comment;

  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;

  public Comment(Board boardId, CommentCreateRequestDto commentCreateRequestDto) {
    this.board = boardId;
    this.comment = commentCreateRequestDto.getComment();
  }
}
