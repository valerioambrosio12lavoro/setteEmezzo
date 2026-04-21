package setteEmezzo;
import java.util.ArrayList;
import javax.swing.*; // Importa tutto il kit grafico (Swing)
import java.awt.*;

// "extends JFrame" significa: questa classe È UNA finestra
public class FinestraGioco extends JFrame {
    //Il cervello del gioco
    //creiamo il mazzo e una variabile per il punteggio
    private GiocoLista gioco=new GiocoLista();
    private GestoreFile diario=new GestoreFile();

    private double punteggio=0.0;
    private double punteggioPC=0.0;
    //METTIAMO TUTTO NEL COSTRUTTORE COSI AL RICHIAMO DELLA CLASSE OVVIAMENTE CI CREA TUTTO AUTOMATICAMENTE
    public FinestraGioco() {

        //Comandi per la tavola:1a.super 2a.setSize, 3a.setDefaultCloseOperation, 4a.setLocationRelativeTo, 5a.setVisible...
        // ...sono i 5 comandi base per inizializzare la finestra(la tavola)
        // 1a. Diamo un titolo alla finestra
        super("Sette e Mezzo - Tavolo da Gioco");


        gioco.mescolaCarte();
        // 2a. Decidiamo le dimensioni (Larghezza, Altezza)
        setSize(600, 600);
        // 3a. Importante: quando premi la "X" rossa, il programma deve spegnersi davvero
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 4a. Facciamo apparire la finestra al centro dello schermo (null = centro)
        setLocationRelativeTo(null);

        //Comandi per la tovaglia:
        //1b.Creiamo il pannello(tovaglia)
        JPanel pannello= new JPanel();
        //2b.creiamo un Oggetto(in questo caso il bottone)
        JButton bottonePesca= new JButton("Pesca");
        JButton bottoneStai= new JButton("Stai");
        JButton bottoneRiavvia= new JButton("Riavvia");
        bottoneRiavvia.setVisible(false);
        //3b.Mettimo il bottone sopra la tovaglia
        pannello.add(bottonePesca);
        pannello.add(bottoneStai);
        pannello.add(bottoneRiavvia);
        //4b.Inseriamo il pannello nella finestra(mettiamo la tovaglia sopra al tavolo)
        this.add(pannello);

        JLabel etichetta=new JLabel("In attesa");
        JLabel giocoT=new JLabel("Gioco truccato");
        pannello.add(etichetta);

        //AZIONE BOTTONE PESCA
        bottonePesca.addActionListener(e->{
            //Diciamo a java che questa è una carta vera(con il cast(Cart1) ) cosi assume i sui metodi
           Carta1 cartaPesca=(Carta1) gioco.estraiCarta();
           punteggio+= cartaPesca.getPunteggio();
           etichetta.setText("Hai pescato: "+cartaPesca.toString()+"|Totale: "+punteggio);
            System.out.println("Pescato: " + cartaPesca+"|Totale: "+punteggio);
            // Controllo se hai sballato (Giusto per vederlo in console per ora)
            if (punteggio > 7.5) {
                System.out.println("HAI SBALLATO!");
                etichetta.setText("HAI SBALLATO! Punti: " + punteggio);
                diario.salvaRisultato("(P)Hai sballato: "+punteggio);
                // Qui poi disattiveremo il bottone...
                bottonePesca.setEnabled(false);
                bottoneStai.setEnabled(false);
                bottoneRiavvia.setVisible(true);
            }
            /*
            //System.out.println(""); è per scrivere nel pannello di controllo e no nella finestra
            System.out.println("Click ricevuto!!");
            etichetta.setText("Hai pescato una carta!");//cambia la scrittura sulla finestra
             */
        });
        //AZIONE BOTTONE STAI

        bottoneStai.addActionListener(c->{

            bottoneStai.setEnabled(false);
            bottonePesca.setEnabled(false);
            etichetta.setText("Hai concluso il tuo turno ora tocca al pc!");

            //POLIMORFISMO
            // ERO RIMASTO QUI: Il riavvio non resetta il gioco truccato. Da fixare la prossima volta
            double sorte=Math.random();
            if(sorte<0.5){
                System.out.println("!!La partita è truccata!!");

                ArrayList<Carta1>mazzoCorrente= gioco.getMazzo();
                int indiceCorrente=gioco.getIndicePesca();

                gioco=new GiocoTruccato7(mazzoCorrente,indiceCorrente);

                etichetta.setText("La partita è truccata");
            }else{
                System.out.println("!!La partita è normale!!");
                etichetta.setText("Turno del PC");
            }
            //FINE POLIMORFISMO

            do {
                   Carta1 cartaPC = (Carta1) gioco.estraiCarta();
                   punteggioPC += cartaPC.getPunteggio();
                   System.out.println("Tocca al pc");
                    etichetta.setText("Carta Pc: "+cartaPC.toString()+"|Totale: "+punteggioPC);
                    System.out.println("Carta Pc: "+cartaPC.toString()+"|Totale: "+punteggioPC);
                   if (punteggioPC > 7.5){
                       etichetta.setText("Il pc ha sballato: "+punteggioPC);
                       System.out.println("Il pc ha sballato");
                       bottoneRiavvia.setVisible(true);
                       break; }

               }while(punteggioPC<=punteggio);

               //CONTROLLO VINCITORE+STORICO PARTITA
            if(punteggioPC>7.5){diario.salvaRisultato("(V)Il giocatore ha vinto con: "+punteggio+"\tIl pc ha sballato: "+punteggioPC);}
            else if(punteggioPC<=7.5&& punteggioPC>=punteggio){diario.salvaRisultato("(P)Il giocatore ha perso con: "+punteggio+"\tIl pc ha vinto: "+punteggioPC);}
            else{diario.salvaRisultato("(V)Il giocatore ha vinto con: "+punteggio+"\tIl pc ha perso: "+punteggioPC);}

               bottoneRiavvia.setVisible(true);
        });
        //BOTTONO RIAVVIA
        bottoneRiavvia.addActionListener(e -> {
            // 1. Reset variabili
            punteggio = 0.0; // Azzera il tuo punteggio
            punteggioPC=0.0;
            // 2. Reset Mazzo (Buttiamo il vecchio, ne prendiamo uno nuovo e mescoliamo)
            // ERO RIMASTO QUI: Il riavvio non resetta il gioco truccato. Da fixare la prossima volta
            gioco = new GiocoLista();
            gioco.mescolaCarte();

            // 3. Reset Interfaccia
            etichetta.setText("Nuova partita! A te la mossa.");
            bottonePesca.setEnabled(true); // Riattiva Pesca
            bottoneStai.setEnabled(true);  // Riattiva Stai
            giocoT.setVisible(false);
            System.out.println("DOPO IL RESET SONO: " + gioco.getClass().getSimpleName());
            // 4. Nasconditi
            bottoneRiavvia.setVisible(false);
        });



        // 5a. Rendiamola visibile (di base nasce invisibile!)
        setVisible(true);//questa deve restare sempre l'ultima riga
    }

    // Il main serve solo per testare se la finestra si apre
    public static void main(String[] args) {
        new FinestraGioco();
    }
}