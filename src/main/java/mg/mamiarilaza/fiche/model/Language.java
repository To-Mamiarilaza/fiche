/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mamiarilaza.fiche.model;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;
import generalisation.utils.GenericUtil;
import java.util.List;

/**
 *
 * @author to
 */
@DBTable(name = "language", sequenceName = "seq_language")
public class Language {
    // Field
    @DBField(name = "id_language", isPrimaryKey = true)
    Integer idLanguage;
    
    @DBField(name = "language_name")
    String languageName;
    
    @DBField(name = "etat")
    Integer etat;
    
    // Getter and Setter

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }
    
    // Constructor

    public Language() {
    }

    public Language(Integer idLanguage, String languageName, Integer etat) {
        this.idLanguage = idLanguage;
        this.languageName = languageName;
        this.etat = etat;
    }
    
    // class method
    public static List<Language> getAll() throws Exception {
        return GenericDAO.getAll(Language.class);
    }
}
