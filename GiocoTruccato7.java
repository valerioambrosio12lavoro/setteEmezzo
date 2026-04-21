package setteEmezzo;

import java.util.ArrayList;

public class GiocoTruccato7 extends GiocoLista {
    //variabile booleana per far capire al compilatore se il 7 è stato preso
    boolean toccaAlsette=true;
    String messaggio;
    GiocoTruccato7(ArrayList<Carta1> mazzoVecchio,int indiceVecchio){
        //1. super() per creare la struttura
        super();
        //2. SOVRASCRIVIAMO il mazzo vuoto con quello della partita in corso
        this.mazzo=mazzoVecchio;
        //3.Ripristiniamo l'indice (altrimenti riparte da 0)
        this.indicePesca=indiceVecchio;

    }
    @Override
    public Carta1 estraiCarta(){
        if(toccaAlsette){toccaAlsette=false; return new Carta1("7(truccato)",7);}
        else{toccaAlsette=true; return new Carta1("Re(truccato)",10);}
    }
}
