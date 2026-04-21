package setteEmezzo;

import java.io.FileWriter;//serve per scrivere nel file
import java.io.IOException;//serve per gestire l'eccezioni
public class GestoreFile {
    public void salvaRisultato(String messaggio){
        try{
            //1.apriamo il file
            FileWriter scrittore= new FileWriter("storico.txt",true);
            //"storico.txt" è il nome del file
            //true invece sta nella sezione APPEND (aggiungi in fondo,non cancellare quello che c'era prima)

            //2.scriviamo
            scrittore.write(messaggio+"\n");//'\n'  serve per andare a capo
            //3. chiudiamo
            scrittore.close();
            System.out.println("Risultato salvato");
        } catch (IOException e) {
            System.out.println("Errore file non salvato");
            e.printStackTrace();
        }
    }
}
