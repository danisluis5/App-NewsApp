/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Timestamp;

/**
 *
 * @author vuongluis
 */
public class News {
    private int id_news;
    private String name;
    private String preview_text;
    private String detail_text;
    private int id_cat;
    private String picture;
    private Timestamp date_create;
    private String namecat; // you get object Category tutorials 

    public News(int id_news,String name, String preview_text,String detail_text, String picture, Timestamp date_create,int id_cat,String namecat) {
        this.id_news = id_news;
        this.name = name;
        this.preview_text = preview_text;
        this.detail_text = detail_text;
        this.id_cat = id_cat;
        this.picture = picture;
        this.date_create = date_create;
        this.namecat = namecat;
    }

    public int getId_news() {
        return id_news;
    }

    public String getName() {
        return name;
    }

    public String getPreview_text() {
        return preview_text;
    }

    public String getDetail_text() {
        return detail_text;
    }

    public int getId_cat() {
        return id_cat;
    }

    public String getPicture() {
        return picture;
    }

    public Timestamp getDate_create() {
        return date_create;
    }

    public String getNamecat() {
        return namecat;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreview_text(String preview_text) {
        this.preview_text = preview_text;
    }

    public void setDetail_text(String detail_text) {
        this.detail_text = detail_text;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDate_create(Timestamp date_create) {
        this.date_create = date_create;
    }

    public void setNamecat(String namecat) {
        this.namecat = namecat;
    }
}
