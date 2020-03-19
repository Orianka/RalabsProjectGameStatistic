package com.ralabs.iosstatistic.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ralabs.iosstatistic.DAO.GameDAO;

import com.ralabs.iosstatistic.domain.Game;
import com.ralabs.iosstatistic.domain.GameType;
import com.ralabs.iosstatistic.reader.JSONReader;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;

@Service
public class GameService {

    @Autowired
    GameDAO gameDAO;

    JSONReader jsonReader = new JSONReader();

    String  [] topGames = {"https://rss.itunes.apple.com/api/v1/us/ios-apps/top-free/games/100/explicit.json",
    "https://rss.itunes.apple.com/api/v1/us/ios-apps/top-paid/games/100/explicit.json",
    "https://rss.itunes.apple.com/api/v1/us/ios-apps/top-grossing/all/100/explicit.json"};

    public void addAllGames() throws IOException, JSONException {
        GameType type = GameType.FREE;;
        for(int i = 0; i < topGames.length; i++){
            if(i == 1){
                type = GameType.PAID;
            }
            if(i == 2){
                type = GameType.GROSSING;
            }
            String [] games = jsonReader.getGames(topGames[i], type);
            System.out.println("This " + games[0]);
            for(int j = 1; j < games.length; j++){
                Game game = jsonReader.makeGameObjectFromString(games[j], type);
                gameDAO.save(game);
            }
        }
    }

    public List<Game> getTopGames(GameType type, int limit){
        Pageable pageable = PageRequest.of(0, limit);
        List<Game> games = this.gameDAO.findGamesByGameType(type, pageable);
        return games;
    }

}
