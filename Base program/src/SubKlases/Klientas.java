package SubKlases;

public class Klientas {
    private String vartotojo_vardas;
    private String vartotojo_slaptazodis;
    public Klientas(String vartotojo_vardas, String vartotojo_slaptazodis){
        this.vartotojo_vardas = vartotojo_vardas;
        this.vartotojo_slaptazodis = vartotojo_slaptazodis;
    }

    public String getVartotojo_vardas() {
        return vartotojo_vardas;
    }

    public void setVartotojo_vardas(String vartotojo_vardas) {
        this.vartotojo_vardas = vartotojo_vardas;
    }

    public String getVartotojo_slaptazodis() {
        return vartotojo_slaptazodis;
    }

    public void setVartotojo_slaptazodis(String vartotojo_slaptazodis) {
        this.vartotojo_slaptazodis = vartotojo_slaptazodis;
    }
}
