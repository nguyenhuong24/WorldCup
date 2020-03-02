package com.nguyenhuong.worldcup.modal;

public class Player {
    private Integer id;
    private String ten;
    private String doi;
    private String image;
    private String social;

    public Player() {
    }

    public Player(String ten, String doi, String image, String social) {
        this.ten = ten;
        this.doi = doi;
        this.image = image;
        this.social = social;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

}
