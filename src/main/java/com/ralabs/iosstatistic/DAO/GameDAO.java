package com.ralabs.iosstatistic.DAO;

import com.ralabs.iosstatistic.domain.Game;
import com.ralabs.iosstatistic.domain.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface GameDAO extends JpaRepository<Game, Long> {

    List<Game> findGamesByGameType(GameType type, Pageable pageable);
}
