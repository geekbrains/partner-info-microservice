package com.example.partner_info_microservice.model;

public class Game {

    private final String gameId;

    private final String gameName;


    public Game(String gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }
}
