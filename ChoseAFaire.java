import java.io.*;
import java.util.StringTokenizer;

public class ChoseAFaire {
    protected int importance; //de 1 à 5
    protected String designation;
    protected boolean choseFaite;
    public ChoseAFaire() {
        importance = 0;
        designation = "";
        choseFaite = false;
    }
    public ChoseAFaire(String libelle, int priorite) {
        importance = priorite;
        designation = libelle;
        choseFaite = false;
    }
    public ChoseAFaire(String description) {
        StringTokenizer st = new StringTokenizer(description, ":");
        String extrait;
        extrait = st.nextToken(); 
        importance = Integer.parseInt(extrait);
        designation = st.nextToken();
        extrait = st.nextToken();
        choseFaite = extrait.equals("fait");
    }
    public void termine() {
        choseFaite = true;
    }
    public String toString() {
        String texte = "";
        texte += importance;
        texte += ":";
        texte += designation;
        texte += ":";
        if (choseFaite) texte += "fait";
        else texte += "a faire";   
        return texte;
    }
    public void enregistre(DataOutputStream s) throws IOException {
        s.writeInt(importance);
        s.writeUTF(designation);
        s.writeBoolean(choseFaite);
    }
    public void charge(DataInputStream s) throws IOException {
        importance = s.readInt();
        designation = s.readUTF();
        choseFaite = s.readBoolean();
    }
}