package com.pmirkelam.wowchars;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Char {

    @NonNull
    @Expose
    @SerializedName("cardId")
    private String cardId;

    @NonNull
    @Expose
    @SerializedName("name")
    private String name;

    @NonNull
    @Expose
    @SerializedName("playerClass")
    private String playerClass; //TODO: check nonnull?

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("faction")
    private String faction;

    @Expose
    @SerializedName("rarity")
    private String rarity;

    @Expose
    @SerializedName("health")
    private int health;

    @Expose
    @SerializedName("attack")
    private int attack;

    @Expose
    @SerializedName("cost")
    private long cost;

    @Expose
    @SerializedName("img")
    private String img;

    @Expose
    @SerializedName("imgGold")
    private String imgGold;

    @Expose
    @SerializedName("text")
    private String text;

    @Expose
    @SerializedName("flavor")
    private String flavor;


    @NonNull
    public String getCardId() {
        return cardId;
    }

    public void setCardId(@NonNull String cardId) {
        this.cardId = cardId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgGold() {
        return imgGold;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
    }
}
