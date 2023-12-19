/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentification;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;
import generalisation.utils.GenericUtil;

/**
 *
 * @author to
 */
@DBTable(name = "utilisateur", sequenceName = "")
public class Utilisateur {
    /// Field
    @DBField(name = "id_utilisateur", isPrimaryKey = true)
    Integer idUtilisateur;
    
    @DBField(name = "username")
    String username;
    
    @DBField(name = "password")
    String password;
    
    @DBField(name = "email")
    String email;
    
    @DBField(name = "admin")
    Boolean admin;
    
    /// Getter and setter

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
    /// constructor

    public Utilisateur() {
    }
    
    public Utilisateur(Integer idUtilisateur, String username, String password, String email, Boolean admin) {
        this.idUtilisateur = idUtilisateur;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }
}
