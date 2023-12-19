package com.sparta.plusweekreviewassignment.repository;

import com.sparta.plusweekreviewassignment.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
