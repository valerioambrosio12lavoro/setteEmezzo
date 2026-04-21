package setteEmezzo;

public class Carta1 {
    private String nome;
    private double valore;
    public Carta1(String nome, double valore) {
        this.nome=nome;
        this.valore=valore;
    }
    //METODO PUNTEGGIO
    public double getPunteggio(){
        if(valore<=7) return (double) valore;
        else return 0.5;
    }

    //METODI DI ACCESSO
    public String getNome() {return nome;}
    public double getValore() {return valore;}

    @Override
    public String toString() {return nome+": "+valore;}
}
