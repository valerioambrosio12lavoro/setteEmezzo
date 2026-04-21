package setteEmezzo;

import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        GiocoLista giocoAttuale = new GiocoLista();

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean giocoTruccato = rand.nextBoolean();

        String rispostaFinale;
        System.out.println("Sto mescolando il mazzo");
        giocoAttuale.mescolaCarte();
        double punteggio = 0.0;
        double punteggioPC = 0.0;

        //GIOCO UTENTE
        do {
            Carta1 cartaGiocatore = giocoAttuale.estraiCarta();
            System.out.println("carta: " + cartaGiocatore.toString() + "\te vale: " + cartaGiocatore.getPunteggio());
            punteggio += cartaGiocatore.getPunteggio();
            System.out.println("Punteggio: " + punteggio);

            //controllo punteggio
            if (punteggio > 7.5) {
                System.out.println("Hai sballato,questo è il tuo punteggio: " + punteggio);
                break;
            }

            System.out.println("Vuoi continuare a pescare?");
            rispostaFinale = leggiInputSicuro(sc);

        } while (rispostaFinale.equalsIgnoreCase("s"));//FINE GIOCO UTENTE

        System.out.println("Ora pesco io finche non ti supero o non sballo");

        //POLIMORFISMO
        if (giocoTruccato) {
            System.out.println("\n!!!Attenzione questa partita sarà truccata!!!\t");
            giocoAttuale = new GiocoTruccato7(giocoAttuale.getMazzo(),giocoAttuale.getIndicePesca());
        } else {
            System.out.println("\n!!!Attenzione questa partita è normale!!!");
            giocoAttuale = new GiocoLista();
        }//FINE POLIMORFISMO

        //TURNO PC
        if (punteggio <= 7.5) {

            while (punteggioPC < punteggio) {


                Carta1 cartaPC = giocoAttuale.estraiCarta();

                System.out.println("la mia carta: " + cartaPC.toString() + "\te vale: " + cartaPC.getPunteggio());
                punteggioPC += cartaPC.getPunteggio();
                System.out.println("Punteggio: " + punteggioPC);
                if (punteggioPC > 7.5) {
                    System.out.println("Ho sballato: " + punteggioPC);
                    break;
                }
            }
        }//FINE PC

        //CONTROLLO VINCITORE
        if (punteggio <= 7.5 && punteggioPC <= 7.5) {
            System.out.println("\n---Risultato finale---");
            System.out.println("Tu: " + punteggio + " PC: " + punteggioPC);
            if (punteggio > punteggioPC) {
                System.out.println("Complimenti hai vinto!!!");
            } else if (punteggio == punteggioPC) {
                System.out.println("Abbiamo pareggiato quindi per regola ha vinto il PC");
            } else {
                System.out.println("Hai perso!!!");
            }
        }//FINE CONTROLLO

        System.out.println("Test completato ");
    }
    //TRY-THROW-CATCH RISPOSTA
    public static String leggiInputSicuro(Scanner scanner) {
        String input = "";
        boolean inputValido = false;

        do {
            try {
                System.out.println("Premi 's' o 'n': ");
                input = scanner.next();

                if (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n")) {
                    throw new Exception("Input invalido");

                }
                inputValido = true;

                // 2. QUI DICIAMO CHE IL SEMAFORO È VERDE (visto che non siamo crashati)
                // ???

            } catch (Exception e) {
                System.out.println("Devi inserire solo s o n!");
                scanner.nextLine();
            }

        } while (!inputValido);

        return input;
    }//FINE RISPOSTA

}

