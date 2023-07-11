package com.example.gamestore.repositories;

import com.example.gamestore.domain.entities.Game;
import com.example.gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    @Query("SELECT u.games FROM User u WHERE u.id=:id")
    List<Game> findAllByUser(Long id);
}
