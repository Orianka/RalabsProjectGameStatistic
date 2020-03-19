package com.ralabs.iosstatistic.controller;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.ralabs.iosstatistic.domain.Game;
import com.ralabs.iosstatistic.domain.GameType;
import com.ralabs.iosstatistic.service.GameService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String getMainPage() throws IOException, JSONException {
        gameService.addAllGames();
        return "mainPage";
    }

    @RequestMapping(value = "/ios/games/charts/free", method = RequestMethod.GET)
    public String topFreeGames(Model model) {
        List<Game> game = gameService.getTopGames(GameType.FREE, 100);
        model.addAttribute("topGames", game);
        return "topFreeGames";
    }

    @GetMapping("/ios/games/charts/free/withLimit")
    public String topFreeGames(@RequestParam(value = "limit", required = false) int limit, Model model) {
        List<Game> game = gameService.getTopGames(GameType.FREE, limit);
        model.addAttribute("topGames", game);
        return "topFreeGames";
    }

    @RequestMapping(value = "/ios/games/charts/paid", method = RequestMethod.GET)
    public String topPaidGames(Model model) {
        List<Game> game = gameService.getTopGames(GameType.PAID, 100);
        model.addAttribute("topGames", game);
        return "topPaidGames";
    }

    @GetMapping("/ios/games/charts/paid/withLimit")
    public String topPaidGames(@RequestParam(value = "limit", required = false) int limit, Model model) {
        List<Game> game = gameService.getTopGames(GameType.PAID, limit);
        model.addAttribute("topGames", game);
        return "topPaidGames";
    }

    @RequestMapping(value = "/ios/games/charts/grossing", method = RequestMethod.GET)
    public String topGrossingGames(Model model) {
        List<Game> game = gameService.getTopGames(GameType.GROSSING, 100);
        model.addAttribute("topGames", game);
        return "topGrossingGames";
    }

    @GetMapping("/ios/games/charts/grossing/withLimit")
    public String topGrossingGames(@RequestParam(value = "limit", required = false) int limit, Model model) {
        List<Game> game = gameService.getTopGames(GameType.GROSSING, limit);
        model.addAttribute("topGames", game);
        return "topGrossingGames";
    }
}
