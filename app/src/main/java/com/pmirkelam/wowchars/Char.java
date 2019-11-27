package com.pmirkelam.wowchars;

import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
    private String playerClass;

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

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .apply(
                        new RequestOptions()
                                .error(R.drawable.no_img)
                                .placeholder(R.drawable.loading_img)
                                .fitCenter())
                .into(imageView);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

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

    public String getHealth() {
        return String.valueOf(health);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getAttack() {
        return String.valueOf(attack);
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getImgGold() {
        return imgGold;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
    }

    public String getCost() {
        return String.valueOf(cost);
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Spanned getText() {
        if(text != null) {
            text = text.replace("\\n","<br />");
            return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return null;
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString(){
        return "\nID:" + cardId + "\nName: " + name + "\nPlayer Class: " + playerClass + "\nType: " + type
                + "\nFaction: " + faction + "\nRarity: " + rarity + "\nHealth: " + health + "\nAttack: "
                + attack + "\nCost: " + cost + "\nText: " + text + "\nFlavor: " + flavor;
    }

}
