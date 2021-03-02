package com.kebzzang.blog.repository;

import com.kebzzang.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //Optional<User> findByUsername(String username);

}
