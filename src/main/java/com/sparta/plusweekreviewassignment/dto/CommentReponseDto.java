package com.sparta.plusweekreviewassignment.dto;

import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentReponseDto {

  private Long commentId;
  private String comment;

  private Board boardId;
  public CommentReponseDto(Comment comment) {
    this.commentId = comment.getCommentId();
    this.comment = comment.getComment();
  }
}
