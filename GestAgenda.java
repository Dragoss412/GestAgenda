import java.io.*;
import agenda.*;
import outils.*;

public abstract class GestAgenda extends ChoseAFaire {
    static ChoseAFaire uneChose;
    static Agenda agenda;
    private static void ajouterEvenement(Agenda a) {
        String nom, txtDate, txtHeure, txtDuree;
        System.out.println("Designation ?");
        nom = IN.getString();
        System.out.println("Date ?");
        txtDate = INTemps.getTexteDate();
        System.out.println("Heure ?");
        txtHeure = INTemps.getTexteHeure();
        System.out.println("Durée ?");
        txtDuree = INTemps.getTexteDuree();
        try {
            a.ajouter(new Evenement(nom, txtDate, txtHeure, txtDuree));
            a.trier();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String arg[]) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("uneChose.txt"));
            uneChose = new ChoseAFaire(br.readLine());
            System.out.println("importe : "+uneChose);
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("uneChose.bin"));
            uneChose = new ChoseAFaire();
            uneChose.charge(dis);
            System.out.println("charge : "+uneChose);
            dis.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        char choix;
        Agenda agenda = new Agenda();
        do {
            agenda.afficher();
            System.out.print("a)jouter q)uitter ? ");
            choix = IN.getChar();
            switch(choix) {
                case 'a' : ajouterEvenement(agenda); break;
            }
        } 
        while (choix != 'q');
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("uneChose.bin"));
            uneChose.enregistre(dos);
            dos.close();
        } catch(IOException e) {
            System.err.println(e);
        }
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("uneChose.txt"));
            pw.println(uneChose);
            pw.close();
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}
