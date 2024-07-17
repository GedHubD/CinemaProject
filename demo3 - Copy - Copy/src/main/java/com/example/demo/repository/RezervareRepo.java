package com.example.demo.repository;

import com.example.demo.model.Rezervare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RezervareRepo {
    public void adaugaRezervare(Rezervare rezervare) throws SQLException {
        String sql = "INSERT INTO rezervari (nume, film, sala, locuri, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, rezervare.getNume());
            stmt.setString(2, rezervare.getFilm());
            stmt.setInt(3, rezervare.getSala());
            stmt.setInt(4, rezervare.getLocuri());
            stmt.setDate(5, (Date) rezervare.getData());
            stmt.executeUpdate(); //se executa comanda pt parametrii setati setInt setDate
        }
    }

    public List<Rezervare> getRezervariByNume(String nume) throws SQLException {
        List<Rezervare> rezervari = new ArrayList<>();
        String sql = "SELECT * FROM rezervari WHERE nume = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nume);
            try (ResultSet rs = stmt.executeQuery()) { //creat obiect pentru a parcurge lista
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String film = rs.getString("film");
                    int sala = Integer.parseInt(rs.getString("sala"));
                    int locuri = rs.getInt("locuri");
                    Date data = rs.getDate("data");
                    rezervari.add(new Rezervare(id, film, sala, nume, locuri, data));

                }
            }//trebuie sa returnam lista de rezervari
        }
        return rezervari;
    }

    public void stergeRezervare(int id) throws SQLException { //logica ar fi sa stergem o rezervare pe baza ID
        String sql = "DELETE FROM rezervari WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate(); //comanda de stergere gasita
        }
    }

    public int getCapacitateLibera(int sala) throws SQLException{
        String sql = "SELECT SUM(locuri) as locuriOcupate FROM rezervari WHERE sala = ?";
        int locuriOcupate = 0;
        int capacitateTotala = 30;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sala); //ocupate si param setat
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    locuriOcupate = rs.getInt("locuriOcupate");
                }
            }
        }
        int locuriLibere = capacitateTotala - locuriOcupate;
        System.out.println("Locuri libere = " + locuriLibere + " Capacitate totala = " + capacitateTotala + " Locuri ocupate = " + locuriOcupate);
        if(locuriLibere < 0) {
            locuriLibere = 0; //daca libere= -10
        }

        return locuriLibere;
    }
}
