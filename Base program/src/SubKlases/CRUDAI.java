package SubKlases;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUDAI {
    private String name;
    private String prisijunges_asmuo = "";
    double balansas = 0;
    private ArrayList<Klientas> vartotojai = new ArrayList<>();
    private ArrayList<Kategorijos> kategoriju_sarasas = new ArrayList();
    private ArrayList<Finansai> islaidu_sarasas = new ArrayList<>();
    //public ArrayList<Pajamos> pajamu_sarasas = new ArrayList<>();
    public void Registracija(String vartotojo_vardas, String vartotojo_slaptazodis){
        Klientas reg = new Klientas(vartotojo_vardas, vartotojo_slaptazodis);
        if(!vartotojai.contains(reg)){
            vartotojai.add(reg);
            System.out.println("Sėkmingai užsiregistruota!");
            try{
                BufferedWriter data = new BufferedWriter(new FileWriter("data.txt",true));
                for(Klientas autentifikavimas : vartotojai){
                    data.write("Vartotojo vardas: " + autentifikavimas.getVartotojo_vardas() + " " + "Vartotojo slaptažodis: " + autentifikavimas.getVartotojo_slaptazodis() + " ");
                    data.newLine();
                }
                data.close();
            }
            catch(Exception exc){
                System.out.println("Klaida");
            }
        }
    }
    public boolean Prisijungimas(String vartotojo_vardas, String vartotojo_slaptazodis){
        try{
            Scanner data2 = new Scanner(new File("data.txt"));
            while(data2.hasNext()){
                String eilute = data2.nextLine();
                if(eilute.contains(vartotojo_vardas) && eilute.contains(vartotojo_slaptazodis)){
                    System.out.println("Sėkmingai prisijungta!");
                    prisijunges_asmuo = vartotojo_vardas;
                    return true;
                }
            }
            data2.close();
        }
        catch(Exception exc){
            System.out.println("Klaida");
        }
        System.out.println("Tokio vartotojo nėra.");
        return false;
    }

    public String getPrisijunges_asmuo() {
        return prisijunges_asmuo;
    }

    public void setPrisijunges_asmuo(String prisijunges_asmuo) {
        this.prisijunges_asmuo = prisijunges_asmuo;
    }
    public void Prideti_kategorija(String pavadinimas){
        Kategorijos kat_sukurimas = new Kategorijos(pavadinimas, prisijunges_asmuo);
        try{
            kategoriju_sarasas.add(kat_sukurimas);
            //Galimybe kiekviena sukurta kategorija irasyti i faila, prirasant dar ir asmeni kuris ja sukure
            /*System.out.println("Kategorija pridėta.");
            BufferedWriter data2 = new BufferedWriter(new FileWriter("kategorijos.txt",true));
            data2.write("Kategorijos pavadinimas: " + kat_sukurimas.getPavadinimas() + " " + "Kategoriją sukūrė: " + kat_sukurimas.getKurejas());
            data2.newLine();*/
            for(Kategorijos cat : kategoriju_sarasas){
                System.out.println("Kategorijos pavadinimas: " + cat.getPavadinimas() + " " + "Kategoriją sukūrė: " + cat.getKurejas());
            }
            //data2.close();
        }
        catch(Exception exc){
            System.out.println("Klaida!");
        }
    }
    public void Istrinti_kategorija(String pavadinimas){
        Kategorijos kat_istrynimas = new Kategorijos(pavadinimas, null);
        for(Kategorijos istrinti : this.kategoriju_sarasas){
            if(istrinti.getPavadinimas().equals(pavadinimas)){
                kat_istrynimas = istrinti;
                System.out.println("Kategorija ištrinta.");
            }
        }
        this.kategoriju_sarasas.remove(kat_istrynimas);
        for(Kategorijos cat : kategoriju_sarasas){
            System.out.println("Kategorijos pavadinimas: " + cat.getPavadinimas() + " " + "Kategoriją sukūrė: " + cat.getKurejas());
        }
    }
    public void Atnaujinti_kategorija(String senas, String naujas) {
        for (Kategorijos kat_atnaujinimas : this.kategoriju_sarasas) {
            if (senas.equals(kat_atnaujinimas.getPavadinimas())) {
                kat_atnaujinimas.setPavadinimas(naujas);
                System.out.println("Pavadinimas pakeistas");
            }
        }
        for(Kategorijos cat : kategoriju_sarasas){
            System.out.println("Kategorijos pavadinimas: " + cat.getPavadinimas() + " " + "Kategoriją sukūrė: " + cat.getKurejas());
        }
    }
    public void Pamatyti_kategorijas(){
        for(Kategorijos cat : kategoriju_sarasas){
            System.out.println("Kategorijos pavadinimas: " + cat.getPavadinimas() + " " + "Kategoriją sukūrė: " + cat.getKurejas());
        }
    }
    /*public void Prideti_islaidas(double suma, String kateg) {
        Kategorijos C;
        for (Kategorijos KAT : this.kategoriju_sarasas) {
            if (kateg.equals(KAT.getPavadinimas())) {
                C = KAT;
                Finansai islaidos = new Finansai(suma, C);
                islaidu_sarasas.add(islaidos);
                balansas -= islaidos.getSuma();
                System.out.println("Išlaidos sėkmingai pridėtos: \n");
                System.out.println("Kategorija: " + kateg + " " + "Suma: " + suma);
            }
        }
    }*/
    /*public void Islaidu_istrynimas(double sum, String kategg){
        I6laidos islaida = new I6laidos(kategg);
        for (I6laidos islaidu_trynimas : this.islaidu_sarasas) {
            if (islaidu_trynimas.getKategorija().equals(kategg)) {
                islaida = islaidu_trynimas;
            }
        }
        this.islaidu_sarasas.remove(islaida);
    }*/
    /*
    public void Rodyti_islaidas(String kateg){
        Kategorijos kategorija1;
        for(Kategorijos islaidos_kategorija : this.kategoriju_sarasas){
            if(kateg.equals(islaidos_kategorija.getPavadinimas())){
                kategorija1 = islaidos_kategorija;
                for(Finansai islaidu_sarasas : this.islaidu_sarasas){
                    if(kategorija1.equals(islaidu_sarasas.getKateg())){
                        System.out.println("Suma: " + islaidu_sarasas.getSuma() + " " + "Kategorija: " + islaidos_kategorija.getPavadinimas() + " ");
                    }
                }
            }
        }
    }
    public ArrayList<Finansai> getVisosislaidos(){
        return this.islaidu_sarasas;
    }
    public void Kategoriju_redagavimas(String kateg, double kateg2){
        for (Kategorijos kat_atnaujinimas : this.kategoriju_sarasas) {
            if (kateg.equals(kat_atnaujinimas.getPavadinimas())) {
                kat_atnaujinimas.setPavadinimas(naujas);
                System.out.println("Pavadinimas pakeistas");
            }
        }
    }*/
    /*public void Prideti_pajamas(double suma, String kategorija){
        Kategorijos kategorija2;
        for (Kategorijos pajamucat : this.kategoriju_sarasas) {
            if (kategorija.equals(pajamucat.getPavadinimas())) {
                kategorija2 = pajamucat;
                Pajamos pajamos = new Pajamos(suma, kategorija2);
                pajamu_sarasas.add(pajamos);
                balansas -= pajamos.getSuma();
                System.out.println("Išlaidos sėkmingai pridėtos");
                return;
            }
        }
    }
    public void Pajamuspausdinimas(String pav){
        Kategorijos kategorija2;
        for(Kategorijos pajamucat : this.kategoriju_sarasas){
            if(pav.equals(pajamucat.getPavadinimas())){
                kategorija2 = pajamucat;
                for(Pajamos pajamos : this.pajamu_sarasas){
                    if(kategorija2.equals(pajamos.getKateg())){
                        System.out.println("Pajamos: " + "Suma: " + pajamos.getSuma() + " " + "Kategorija: " + pajamucat.getPavadinimas());
                    }
                }
            }
        }
    }
    public ArrayList<Pajamos> getVisospajamos(){
        return this.pajamu_sarasas;
    }

     */
}

