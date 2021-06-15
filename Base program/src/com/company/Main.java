package com.company;
import GUI.MainController;
import SubKlases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.ArrayList;
public class Main extends Application {
    CRUDAI crud  = new CRUDAI();
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/Main.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setCrud(crud);
        primaryStage.setTitle("Finance Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        Scanner nuskaitymas = new Scanner(System.in);
        CRUDAI cruds = new CRUDAI();
        autorizacija(nuskaitymas, cruds);
    }
    public static void autorizacija(Scanner nuskaitymas, CRUDAI cr){
        String autentifikavimas = " ";
        while(!autentifikavimas.equals("3")){
            System.out.print("Pasirinkti: \n" + "1.Registruotis \n" + "2.Prisijungti \n" + "3.Uždaryti \n");
            autentifikavimas = nuskaitymas.next();
            /*if(autentifikavimas == "1"){
                System.out.println("test1");
            }
            else if(autentifikavimas == "2"){
                System.out.println("test2");
            }
            else if(autentifikavimas == "3"){
                break;
            }
            else{
                System.out.println("Bloga įvestis.");
            }*/
            switch(autentifikavimas){
                case "1":
                    System.out.println("Vartotojo vardas: \n");
                    String vartotojo_vardas = nuskaitymas.next();
                    System.out.println("Vartotojo slaptažodis: \n");
                    String vartotojo_slaptazodis = nuskaitymas.next();
                    cr.Registracija(vartotojo_vardas, vartotojo_slaptazodis);
                    break;
                case "2":
                    System.out.println("Iveskite vartotojo varda: ");
                    vartotojo_vardas = nuskaitymas.next();
                    System.out.println("Iveskite vartotojo slaptazodi: ");
                    vartotojo_slaptazodis = nuskaitymas.next();
                    if(cr.Prisijungimas(vartotojo_vardas, vartotojo_slaptazodis)) {
                        Sasaja(nuskaitymas, cr);
                    }
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Bloga įvestis");
                    break;
            }
        }
    }
    public static void Sasaja(Scanner nuskaitymas, CRUDAI cr){
        String autentifikavimass = "";
        System.out.println("1.Meniu \n" + "2.Atsijungti \n");
        autentifikavimass = nuskaitymas.next();
        switch(autentifikavimass){
            case "1":
                //Meniu(nuskaitymas, cr);
            case "2":
                break;
        }
    }

    public static void Kategorijos(Scanner nuskaitymas, CRUDAI cr){
        String veiksmas = "";
        String pavadinimas = "";
        System.out.println("1.Pridėti kategoriją \n" + "2.Ištrinti kategoriją \n" + "3.Redaguoti kategoriją \n" + "4.Pamatyti sukurtas kategorijas \n" + "5.Grįžti \n");
        veiksmas = nuskaitymas.next();
        switch(veiksmas){
            case "1":
                System.out.println("Kategorijos pavadinimas: ");
                pavadinimas = nuskaitymas.next();
                cr.Prideti_kategorija(pavadinimas);
                //Meniu(nuskaitymas,cr);
                break;
            case "2":
                System.out.println("Kategorijos pavadinimas: ");
                pavadinimas = nuskaitymas.next();
                cr.Istrinti_kategorija(pavadinimas);
                break;
            case "3":
                System.out.println("Pervadinamos kategorijos pavadinimas: ");
                String senas_pavadinimas = nuskaitymas.next();
                System.out.println("Naujas kategorijos pavadinimas: ");
                String naujas_pavadinimas = nuskaitymas.next();
                cr.Atnaujinti_kategorija(senas_pavadinimas, naujas_pavadinimas);
                break;
            case "4":
                cr.Pamatyti_kategorijas();
            case "5":
               // Meniu(nuskaitymas, cr);
        }
    }
}
