package com.example.demo.model;
import java.util.Date;

public class Rezervare {
    int id;
    String film;
    int sala;
    String nume;
    int locuri;
    Date data;

    //am corectat din private in default

    public Rezervare() {

    }

    public Rezervare(int id, String film, int sala, String nume, int locuri, Date data) {
        this.id = id;
        this.film = film;
        this.sala = sala;
        this.nume = nume;
        this.locuri = locuri;
        this.data = data;
    } //am sters jav.sql.Date

    public int getId() {

        return id;
    }

    //nu aveam setter pe id
    public void setId(int id){
        this.id=id;
    }

    public String getFilm() {

        return film;
    }

    public void setFilm(String film) {

        this.film = film;
    }

    public int getSala() {

        return sala;
    }
    public void setSala(int sala) {

        this.sala = sala;
    }

    public String getNume() {

        return nume;
    }

    public void setNume(String nume) {

        this.nume = nume;
    }

    public int getLocuri() {

        return locuri;
    }

    public void setLocuri(int locuri) {

        this.locuri = locuri;
    }

    public Date getData() {

        return data;
    }

    public void setData(Date data) {

        this.data = data;
    }

    @Override
    public String toString() {
        return "Rezervare{" + "id=" + id + ", film='" + film + '\'' + ", sala=" + sala +
                ", nume='" + nume + '\'' + ", locuri=" + locuri + ", data=" + data + '}';
    }

    //adaug o metoda de afisare
    public void afiseazaDetalii(){
        System.out.println("Id: "+id);
        System.out.println("Film: " +film);
        System.out.println("Sala " + sala);
        System.out.println("Nume" + nume);
        System.out.println("Locuri"+locuri);
        System.out.println("Data: "+ data);
    }

    // id, film, sala, nume, locuri, data(a se folosi de preferat un obiect de tip Date -> cautati pe net exemple simple)
    // getteri, setteri
    // orice alta metoda pe care o considerati utila
}
