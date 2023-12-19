/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mamiarilaza.fiche.model;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;
import generalisation.utils.GenericUtil;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author to
 */
@DBTable(name = "fiche", sequenceName = "seq_fiche")
public class Fiche {
    // Field 
    @DBField(name = "id_fiche", isPrimaryKey = true)
    Integer idFiche;
    
    @DBField(name = "name")
    String name;
    
    @DBField(name = "firstname")
    String firstname;
    
    @DBField(name = "address")
    String address;
    
    @DBField(name = "mail")
    String mail;
    
    @DBField(name = "description")
    String description;
    
    @DBField(name = "photo")
    String photo;
    
    @DBField(name = "etat")
    Integer etat;
    
    List<Language> languages;
    
    // Getter and Setter

    public Integer getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(Integer idFiche) {
        this.idFiche = idFiche;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
    
    // constructor

    public Fiche() {
    }

    public Fiche(Integer idFiche, String name, String firstname, String address, String mail, String description, String photo, Integer etat) {
        this.idFiche = idFiche;
        this.name = name;
        this.firstname = firstname;
        this.address = address;
        this.mail = mail;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
    }

    public Fiche(String name, String firstname, String address, String mail, String description, String photo, Integer etat) {
        this.name = name;
        this.firstname = firstname;
        this.address = address;
        this.mail = mail;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
    }
    
    
    
    // fiche method
    // get all waiting and valid fiche
    public static List<Fiche> getAll() throws Exception {
        return GenericDAO.getAll(Fiche.class, "WHERE etat >= 10", null);
    }
    
    // get a fiche with the language
    public static Fiche getById(Integer id) throws Exception {
        Fiche fiche = GenericDAO.findById(Fiche.class, id);
        
        // get all language
        String whereClause = " WHERE id_language IN (SELECT id_language FROM fiche_language WHERE id_fiche = %d)";
        whereClause = String.format(whereClause, id);
        List<Language> languages = GenericDAO.getAll(Language.class, whereClause, null);
        
        fiche.setLanguages(languages);
        return fiche;
    } 
    
    // save new fiche
    public void save() throws Exception {
        Connection connection = GenericDAO.getConnection();
        this.setEtat(10);
        
        // Save this fiche
        GenericDAO.save(this, connection);
        
        // Save specified language
        for (Language language : languages) {
            FicheLanguage ficheLanguage = new FicheLanguage(this, language);
            GenericDAO.save(ficheLanguage, connection);
        }
        
        connection.commit();
        connection.close();
    }
    
    // update a fiche
    public void update() throws Exception {
        Connection connection = GenericDAO.getConnection();
        
        // Update by id
        this.setEtat(10);
        GenericDAO.updateById(this, connection);
        
        // Delete all added language
        String deleteQuery = "DELETE FROM fiche_language WHERE id_fiche = %d";
        deleteQuery = String.format(deleteQuery, getIdFiche());
        
        GenericDAO.directUpdate(deleteQuery, connection);
        
        // Insert new added language
        for (Language language : languages) {
            FicheLanguage ficheLanguage = new FicheLanguage(this, language);
            GenericDAO.save(ficheLanguage, connection);
        }
        
        connection.commit();
        connection.close();
    }
    
    // delete a fiche
    public void delete() throws Exception {
        this.setEtat(0);
        GenericDAO.updateById(this, null);
    }
    
    // validate a fiche
    public void validate() throws Exception {
        this.setEtat(20);
        GenericDAO.updateById(this, null);
    }
    
    // validate a fiche
    public void refuse() throws Exception {
        this.setEtat(5);
        GenericDAO.updateById(this, null);
    }
    
}
