package com.ralabs.iosstatistic.service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public class SchedulingDatabaseRefreshing {

    @Autowired
    GameService gameService;

    @Scheduled(fixedRate = 20000)
    public void databaseRefreshing() throws IOException, JSONException {
        gameService.addAllGames();
    }
}
