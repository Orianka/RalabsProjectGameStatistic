package com.ralabs.iosstatistic.controller;

import com.ralabs.iosstatistic.domain.Game;
import com.ralabs.iosstatistic.domain.GameType;
import com.ralabs.iosstatistic.service.GameService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String getMainPage() throws IOException, JSONException {
        gameService.addAllGames();
        return "mainPage";
    }

    @RequestMapping(value = "/ios/games/charts/free", method = RequestMethod.GET)
    public List<Game> topFreeGames(){
       return gameService.getTopGames(GameType.FREE, 100);
    }

    @GetMapping("/ios/games/charts/free/withLimit")
    public List<Game> topFreeGames(@RequestParam(value = "limit", required = false) int limit) {
        return gameService.getTopGames(GameType.FREE, limit);
    }

    @GetMapping(value = "/ios/games/charts/paid")
    public List<Game> topPaidGames() {
        return gameService.getTopGames(GameType.PAID, 100);
    }

    @GetMapping("/ios/games/charts/paid/withLimit")
    public List<Game> topPaidGames(@RequestParam(value = "limit", required = false) int limit) {
        return gameService.getTopGames(GameType.PAID, limit);
    }

    @RequestMapping(value = "/ios/games/charts/grossing", method = RequestMethod.GET)
    public List<Game> topGrossingGames() {
        return gameService.getTopGames(GameType.GROSSING, 100);
    }

    @GetMapping("/ios/games/charts/grossing/withLimit")
    public List<Game> topGrossingGames(@RequestParam(value = "limit", required = false) int limit) {
        return gameService.getTopGames(GameType.GROSSING, limit);
    }
}
