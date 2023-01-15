package com.example.projektchat;


public class Uzytkownik {
    private String uzytkownikId, uzytkownikNazwa, uzytkownikEmail, uzytkownikHaslo;

    public Uzytkownik(){

    }

    public Uzytkownik(String uzytkownikId, String uzytkownikNazwa, String uzytkownikEmail, String uzytkownikHaslo) {
        this.uzytkownikId = uzytkownikId;
        this.uzytkownikNazwa = uzytkownikNazwa;
        this.uzytkownikEmail = uzytkownikEmail;
        this.uzytkownikHaslo = uzytkownikHaslo;
    }

    public String getUzytkownikId() {
        return uzytkownikId;
    }

    public String getuzytkownikNazwa() {
        return uzytkownikNazwa;
    }

    public String getuzytkownikEmail() {
        return uzytkownikEmail;
    }

    public String getuzytkownikHaslo() {
        return uzytkownikHaslo;
    }

    public void setUzytkownikId(String uzytkownikId) {
        this.uzytkownikId = uzytkownikId;
    }

    public void setuzytkownikNazwa(String uzytkownikNazwa) {
        this.uzytkownikNazwa = uzytkownikNazwa;
    }

    public void setuzytkownikEmail(String uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
    }

    public void setuzytkownikHaslo(String uzytkownikHaslo) {
        this.uzytkownikHaslo = uzytkownikHaslo;
    }
}
