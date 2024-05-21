package Model.DAO;

public class ValideRG {
    // Vérifie si le RG est valide (doit être composé de 9 chiffres)
    public static boolean estRGValide(String rg) {
        return rg != null ;
    }

    // Formatte et imprime le RG si valide
    public static String imprimeRG(String rg) {
        if (estRGValide(rg)) {
            return rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + rg.substring(8);
        } else {
            return "RG invalide";
        }
    }

    // Vérifie si le RG est valide et le formatte
    public static String valideRG(String rg) {
        return imprimeRG(rg);
    }

    // Alias pour estRGValide
    public static boolean estRG(String rg) {
        return estRGValide(rg);
    }
}
