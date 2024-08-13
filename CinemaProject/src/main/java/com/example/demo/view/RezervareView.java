package com.example.demo.view;

import com.example.demo.model.Rezervare;
import com.example.demo.service.RezervareService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class RezervareView {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private RezervareService rezervareService;

    public RezervareView() {
        this.rezervareService = new RezervareService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
            System.out.println("Username sau parola gresita!");
            return;
        }

        System.out.println("Autentificare reusita!");

        while (true) {
            System.out.println("Comenzi disponibile: rezervare, afisareRezervari, verificareCapacitate, stergeRezervare, exit");
            System.out.print("Introdu comanda: ");
            String comanda = scanner.nextLine();

            try {
                switch (comanda) {
                    case "rezervare":
                        adaugaRezervare(scanner);
                        break;
                    case "afisareRezervari":
                        afisareRezervari(scanner);
                        break;
                    case "verificareCapacitate":
                        verificareCapacitate(scanner);
                        break;
                    case "stergeRezervare":
                        stergeRezervare(scanner);
                        break;
                    case "exit":
                        System.out.println("Iesire din aplicatie.");
                        return;
                    default:
                        System.out.println("Comanda necunoscuta!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void adaugaRezervare(Scanner scanner) throws SQLException {

        System.out.println("Nume: ");
        String nume = scanner.nextLine();

        System.out.println("Film: ");
        String film = scanner.nextLine();

        System.out.println("Numar Sala: ");
        int sala = Integer.parseInt(scanner.nextLine());

        System.out.println("Locuri in Sala: ");
        int locuri = Integer.parseInt(scanner.nextLine());

        System.out.println("Data (yyyy-MM-dd): ");
        Date data = Date.valueOf(scanner.nextLine());

        Rezervare rezervare = new Rezervare(0, film, sala, nume, locuri, data);
        rezervareService.adaugaRezervare(rezervare); //trebuia sa verific formatul datei care astepta un obiect
    }

    private void afisareRezervari(Scanner scanner) throws SQLException {
        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        rezervareService.afisareRezervari(nume);
    }

    private void verificareCapacitate(Scanner scanner) throws SQLException {
        System.out.print("Numar Sala: ");
        int numarSala = Integer.parseInt(scanner.nextLine());
        rezervareService.verificareCapacitate(numarSala);
    }


    private void stergeRezervare(Scanner scanner) throws SQLException {
        try {
            System.out.print("ID Rezervare: ");
            int id = Integer.parseInt(scanner.nextLine());
            rezervareService.stergeRezervare(id);
        } catch (NumberFormatException e) {
            System.out.println("ID-ul rezervarii este invalid!");
        }
    }
}




