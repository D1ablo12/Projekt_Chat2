package com.example.projektchat;

public class Wiadomosci implements Comparable<Wiadomosci> {
    private String wiadomoscId;
    private String wyslaneId;
    private String wiadomosc;
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Wiadomosci(String wiadomoscId, String wyslaneId, String wiadomosc, String dateTime) {
        this.wiadomoscId = wiadomoscId;
        this.wyslaneId = wyslaneId;
        this.wiadomosc = wiadomosc;
        this.dateTime = dateTime;
    }
/*
    public Wiadomosci(String wiadomoscId, String wyslaneId, String wiadomosc) {
        this.wiadomoscId = wiadomoscId;
        this.wyslaneId = wyslaneId;
        this.wiadomosc = wiadomosc;
    }
 */

    public Wiadomosci(){

    }

    public String getWiadomoscId() {
        return wiadomoscId;
    }

    public void setWiadomoscId(String wiadomoscId) {
        this.wiadomoscId = wiadomoscId;
    }

    public String getWyslaneId() {
        return wyslaneId;
    }

    public void setWyslaneId(String wyslaneId) {
        this.wyslaneId = wyslaneId;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    @Override
    public int compareTo(Wiadomosci employee) {
        return getDateTime().compareTo(employee.getDateTime());
    }
    @Override
    public String toString(){
       return  wiadomosc;
    }
}

