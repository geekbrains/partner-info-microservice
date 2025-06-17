package com.example.partner_info_microservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Partner {

    private String partnerKey;

    private String partnerName;

    private List<Game> games;

    private boolean enabled;

    private List<Player> players;

    public Partner(String partnerKey, String partnerName, List<Game> games, boolean enabled) {
        this.partnerKey = partnerKey;
        this.partnerName = partnerName;
        this.games = games;
        this.enabled=enabled;
    }



    public String getPartnerKey() {
        return partnerKey;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName){
        this.partnerName=partnerName;
    }

    public List<Game> getGames() {
        return games;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "partnerKey='" + partnerKey + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", games=" + games +
                ", enabled=" + enabled +
                ", players=" + players +
                '}';
    }
}
