package com.example.demo.service;

import com.example.demo.model.Rezervare;
import com.example.demo.repository.RezervareRepo;

import java.sql.SQLException;
import java.util.List;

public class RezervareService {
    private final RezervareRepo rezervareRepository; //final

    public RezervareService() {

        this.rezervareRepository = new RezervareRepo();
    }

    public void adaugaRezervare(Rezervare rezervare) throws SQLException {
        int locuriRamase = rezervareRepository.getCapacitateLibera(Integer.parseInt(String.valueOf(rezervare.getSala())));
        if (locuriRamase < rezervare.getLocuri()) { //aici aveam if (rezervare.getLocuri() > 30) si imi afisa cu -numarramas
            System.out.println("Sala plina!");
            return;
        }


        rezervareRepository.adaugaRezervare(rezervare);
        System.out.println("Rezervare adaugata"); //am adaugat apelul
    }

    public List<Rezervare> afisareRezervari(String nume) throws SQLException {
        List<Rezervare> rezervari = rezervareRepository.getRezervariByNume(nume);
        if (rezervari.isEmpty()) { //adaugat if pentru ca lipsea
            System.out.println("Nu exista rezervare");
        } else {
            for (Rezervare rezervare : rezervari) {
            System.out.println(rezervare);
            }
        }
        return rezervari;
    } //aveam typo

    public int verificareCapacitate (int sala) throws SQLException {
        int locuriLibereRamase = rezervareRepository.getCapacitateLibera(sala);
        System.out.println("Locuri libere in sala " + sala + ": " + locuriLibereRamase);
        return locuriLibereRamase;
    }

    public void stergeRezervare(int id) throws SQLException {
        rezervareRepository.stergeRezervare(id);
        System.out.println("Rezervare stearsa!");
    }
}

