package setteEmezzo;
import java.util.ArrayList;       // Per la lista magica
import java.util.Random;

public class GiocoLista{
    protected ArrayList<Carta1> mazzo=new ArrayList();
    protected int indicePesca=0;

    public GiocoLista(){
        //alla chimata del costruttore creera automaticamente la lista ordinata
        String[] semi={"Denari","Bastoni","Coppe","Spade"};
        //for per scorrere i semi
        for(String s:semi){
            //for per dare nome e valore(iniziale) alle carte
            for(int valore=1;valore<=10;valore++){
                String nome="";
                if(valore==1) nome="Asso";
                else if(valore==8) nome="Donna";
                else if(valore==9) nome="Cavallo";
                else if(valore==10) nome="Re";
                else nome=String.valueOf(valore);
                //siccome nome è una variabile di testo l'else prende il numero di valore(il numero della carta)
                //e lo trasforma in testo,String.valueOf(valore) prende il num 7(matematico) e lo trasforma in "7"(carattere stampabile)
                String nomeCompleto=nome+" di "+s;
                mazzo.add(new Carta1(nomeCompleto,valore));
            }
        }

    }//FINE COSTRUTTORE

    //MESCOLA CARTE
    public void mescolaCarte(){
        Random rand=new Random();
        //ALGORITMO DI MESCOLAMENTO
        for(int i=0;i<mazzo.size();i++){

            //creiamo una varibile locale che prende una posizione a caso nel mazzo
            int posizioneCasuale=rand.nextInt(mazzo.size());

            //salvo la carta nella varibile temporanea(la metto in tasca)
            Carta1 temp=mazzo.get(i);

            //metto alla posizione i la carta che ho trovato in posizioneCasuale
            //.set(indice,cosaMettere) è come fare mazzo[i]
            mazzo.set(i,mazzo.get(posizioneCasuale));

            //metto la carta che avevo in tasca nella posizione casuale
            mazzo.set(posizioneCasuale,temp);
        }
        //indicePesca=0 perchè quando andiamo a prende una carta nel main l'indice aumenta
        //per questo quando andiamo a chiamare il metodo mescolaCarte l'indice era rimasto
        // alla posizione in cui l'avevamo lasciato nel main e una volta mischiate le carte lo settima a 0
        // cosi partiamo sempre dalla posizione 0 per pescare
        this.indicePesca=0;
    }//FINE MESCOLA CARTE

    //ESTRAZIONE CARTA
    public Carta1 estraiCarta(){
        if(indicePesca<mazzo.size()){
            return mazzo.get(indicePesca++);
        }else{
            return null;
        }

    }//FINE ESTRAZIONE CARTA
    public ArrayList<Carta1> getMazzo() {
        return mazzo;
    }
    public int getIndicePesca(){
        return indicePesca;
    }
}