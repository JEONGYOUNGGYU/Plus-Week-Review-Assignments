package com.sparta.plusweekreviewassignment.repository;

import com.sparta.plusweekreviewassignment.entity.Board;
import com.sparta.plusweekreviewassignment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByBoard(Board board);
}
