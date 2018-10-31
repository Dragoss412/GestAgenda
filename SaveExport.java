import java.io.*;
import outils.*;

public class SaveExport {
    public static void SaveExport() {
        ChoseAFaire uneChose;
        uneChose = new ChoseAFaire("save.txt");
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("save.bin"));
            uneChose.enregistre(dos);
            dos.close();
        } catch(IOException e) {
            System.err.println(e);
        }
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("save.txt"));
            pw.println(uneChose);
            pw.close();
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}
