package temps;

import java.util.StringTokenizer;
import java.lang.Integer;
import java.lang.Comparable;

public class Date implements Comparable<Date> {
    private int jour;
    private int mois;
    private int annee;
    private void initialise(int j, int m, int a)
    throws ExceptionMauvaisJour, ExceptionMauvaisMois, ExceptionMauvaiseAnnee {
        if (m < 1 || m > 12) throw new ExceptionMauvaisMois(m); else mois = m;
        if (a < 1) throw new ExceptionMauvaiseAnnee(a); else annee = a;
        int jmax = nombreDeJoursDuMois(m, a);
        if (j < 1 || j > jmax) {
            String msg;
            msg = "Jour incorrect (" + j + ") pour une date ";
            msg += "(il faut un entier entre 1 et " + jmax + " pour ";
            if (jmax == 30 || jmax == 31)
                msg += "un mois de " + nomDuMois(m) + ")";
            else
                msg += "le mois de " + nomDuMois(m) + " " + a + ")";
            throw new ExceptionMauvaisJour(msg);
        } else
            jour = j;
    }
    public Date(int j, int m, int a)
    throws ExceptionMauvaisJour, ExceptionMauvaisMois, ExceptionMauvaiseAnnee {
        initialise(j, m, a);
    }
    public Date(String texte)
    throws ExceptionMauvaisJour, ExceptionMauvaisMois, ExceptionMauvaiseAnnee {
        StringTokenizer st = new StringTokenizer(texte, "/");
        int j, m, a;
        j = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        initialise(j, m, a);
    }
    public String toString() {
        String s = "";
        s = s + jour;
        s += '/';
        if (mois < 10) s += '0';
        s += mois;
        s += '/';
        s += annee;
        return s;
    }
    public static boolean bissextile(int a) {
        if (a % 400 == 0) return true;
        if (a % 4 == 0 && a % 100 != 0) return true;
        return false;
    }
    private int nombreDeJoursDuMois(int m, int a) {
        switch(m) {
            case 1  :
            case 3  :
            case 5  :
            case 7  :
            case 8  :
            case 10 :
            case 12 :
                return 31;
            case 2 :
                return (bissextile(a)) ? 29 : 28;    
            case 4 :
            case 6 :
            case 9 :
            case 11 :
                return 30;
            default :
                return 0;
        }
    }
    public static String nomDuMois(int m) {
        switch (m) {
            case  1: return "janvier";
            case  2: return "février";
            case  3: return "mars";
            case  4: return "avril";
            case  5: return "mai";
            case  6: return "juin";
            case  7: return "juillet";
            case  8: return "août";
            case  9: return "septembre";
            case 10: return "octobre";
            case 11: return "novembre";
            case 12: return "décembre";
            default: return "inexistant";
        }
    }
    public void passerAuLendemain() {
        if (jour < nombreDeJoursDuMois(mois, annee)) {
            jour ++;
        } else {
            jour = 1;
            if (mois < 12) {
                mois ++;
            } else {
                mois = 1;
                annee ++;
            }
        }
    }
    public void passerALaVeille() {
        if (jour > 1) {
            jour --;
        } else {  
            if (mois >1) {
                mois --;
            } else {
                mois = 12;
                annee --;
            }
            jour = nombreDeJoursDuMois(mois, annee);
        }
    }
    public int compareTo(Date autreDate) {
        if (annee == autreDate.annee) {
            if (mois == autreDate.mois) {
                return jour - autreDate.jour;
            } else
                return mois - autreDate.mois;
        } else
            return annee - autreDate.annee;
    }
}