import java.io.*;
import outils.*;

public class SaveExport {
    public static void SaveExport() {
        ChoseAFaire save;
        save = new SaveExport("save.txt");
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("save.bin"));
            save.enregistre(dos);
            dos.close();
        } catch(IOException e) {
            System.err.println(e);
        }
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("save.txt"));
            pw.println(save);
            pw.close();
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}
