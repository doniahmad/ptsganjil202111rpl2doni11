package com.example.ptsganjil202111rpl2doni11.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DatabaseModel extends RealmObject {
    @PrimaryKey
    private int id;

    private String teamName, teamStadiumImg, teamDesc, teamCountry, teamBadge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamStadiumImg() {
        return teamStadiumImg;
    }

    public void setTeamStadiumImg(String teamStadiumImg) {
        this.teamStadiumImg = teamStadiumImg;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getTeamCountry() {
        return teamCountry;
    }

    public void setTeamCountry(String teamCountry) {
        this.teamCountry = teamCountry;
    }

    public String getTeamBadge() {
        return teamBadge;
    }

    public void setTeamBadge(String teamBadge) {
        this.teamBadge = teamBadge;
    }
}
