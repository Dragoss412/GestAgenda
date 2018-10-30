import java.io.*;
import outils.*;

class EssaiChoseAFaire {
    public static void main(String arg[]) {
        ChoseAFaire uneChose;
        int priorite;
        String quoi;

        System.out.print("Quoi faire ? ");
        quoi = IN.getString();
        System.out.print("Avec quelle priorite ? ");
        do {
            priorite = IN.getInt();
        } while (priorite < 1 || priorite > 5);
        uneChose = new ChoseAFaire(quoi, priorite);
        System.out.println(uneChose);
        uneChose.termine();
        System.out.println(uneChose);
        //enregistrement
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("uneChose.bin"));
            uneChose.enregistre(dos);
            dos.close();
        } catch(IOException e) {
            System.err.println(e);
        }
        //chargement
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("uneChose.bin"));
            uneChose = new ChoseAFaire();
            uneChose.charge(dis);
            System.out.println("charge : "+uneChose);
            dis.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        //export texte
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("uneChose.txt"));
            pw.println(uneChose);
            pw.close();
        } catch(IOException e) {
            System.err.println(e);
        }
        //import texte
        try {
            BufferedReader br = new BufferedReader(new FileReader("uneChose.txt"));
            uneChose = new ChoseAFaire(br.readLine());
            System.out.println("importe : "+uneChose);
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}