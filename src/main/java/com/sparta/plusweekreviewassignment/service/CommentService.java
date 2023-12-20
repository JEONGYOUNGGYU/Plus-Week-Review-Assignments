package com.sparta.plusweekreviewassignment.service;

import com.sparta.plusweekreviewassignment.dto.CommentCreateRequestDto;
import com.sparta.plusweekreviewassignment.dto.CommentModifyRequestDto;
import com.sparta.plusweekreviewassignment.dto.CommentReponseDto;
import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.entity.Comment;
import com.sparta.plusweekreviewassignment.repository.BoardRepository;
import com.sparta.plusweekreviewassignment.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;
  public void createComment(Board boardId, CommentCreateRequestDto commentCreateRequestDto) {
    // 작성할 댓글의 게시글 가져오기
    Board board = boardRepository.findById(boardId.getBoardId()).orElseThrow(()->
        new IllegalArgumentException("해당 게시글이 없습니다."));

    if(board.getBoardId().equals(boardId.getBoardId())){
      // request 값을 entity에 저장!
      Comment comment = new Comment(boardId, commentCreateRequestDto);
      // entity에 저장 돼 있는걸 db에 저장
      commentRepository.save(comment);
    }



  }

  public List<CommentReponseDto> getComment(Board boardId, Long commentId) {
    // 게시글 가져오기
    Board board = boardRepository.findById(boardId.getBoardId()).orElseThrow(() ->
        new IllegalArgumentException("해당 게시글이 없습니다."));

    // **게시글에 연관된 모든 댓글 가져오기** 챗 GPT가 만들어줌
    List<Comment> commentList = commentRepository.findByBoard(board);

    List<CommentReponseDto> commentReponseDtoList = new ArrayList<>();

    // 해당 게시글에 있는 댓글만 보여주는 로직 만들기
    // 게시글이 1번이면 1번에 관한 댓글들만 뽑아오기
    if(board.getBoardId().equals(boardId.getBoardId())) {
      for (Comment comment: commentList) {
        commentReponseDtoList.add(new CommentReponseDto(comment));
      }
    }
      return commentReponseDtoList;

  }

  @Transactional
  public void modifyComment(Board boardId,
                            Comment commentId,
                            CommentModifyRequestDto commentModifyRequestDto) {
    // 댓글 가져오기
    Comment comment = commentRepository.findById(commentId.getCommentId()).orElseThrow(() ->
        new IllegalArgumentException("해당 댓글이 없습니다."));

    // **댓글이 해당 게시글에 속하는지 확인** 챗 GPT가 만들어줌...
    if(!comment.getBoard().getBoardId().equals(boardId.getBoardId())) {
        throw new IllegalArgumentException("해당 댓글은 지정된 게시글에 속하지 않습니다.");
      }
      comment.setComment(commentModifyRequestDto.getComment());

      commentRepository.save(comment);
  }

  public void deleteComment(Board boardId, Comment commentId) {

    // 댓글 가져오기
    Comment comment = commentRepository.findById(commentId.getCommentId()).orElseThrow(() ->
        new IllegalArgumentException("해당 댓글이 없습니다."));
    if(!comment.getBoard().getBoardId().equals(boardId.getBoardId())) {
      throw new IllegalArgumentException("해당 댓글은 지정된 게시글에 속하지 않습니다.");
    }
    commentRepository.delete(comment);
  }

}
