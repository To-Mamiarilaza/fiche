/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mamiarilaza.fiche.model;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

/**
 *
 * @author to
 */
@DBTable(name = "fiche_language", sequenceName = "")
public class FicheLanguage {
    // Field
    @DBField(name = "id_fiche", isForeignKey = true)
    Fiche fiche;
    
    @DBField(name = "id_language", isForeignKey = true)
    Language language;
    
    // Getter and setter

    public Fiche getFiche() {
        return fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
    // Constructor

    public FicheLanguage() {
    }

    public FicheLanguage(Fiche fiche, Language language) {
        this.fiche = fiche;
        this.language = language;
    }
    
}
