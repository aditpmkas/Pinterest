package com.example.pinterestgrid;

public class Photos {
    private final int image;
    private final String keyword; // Tambahkan atribut keyword

    public Photos(int image, String keyword) {
        this.image = image;
        this.keyword = keyword;
    }

    public int getImage() {
        return image;
    }

    public String getKeyword() {
        return keyword;
    }
}
