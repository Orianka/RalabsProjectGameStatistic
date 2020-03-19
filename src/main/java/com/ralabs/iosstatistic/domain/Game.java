package com.ralabs.iosstatistic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String artistName;
    String name;
    String url;
    LocalDate releaseDate;
    GameType gameType;

    public Game(String artistName, String name, String url, LocalDate releaseDate, GameType gameType){
        this.artistName = artistName;
        this.name = name;
        this.url = url;
        this.releaseDate = releaseDate;
        this.gameType = gameType;
    }
}
