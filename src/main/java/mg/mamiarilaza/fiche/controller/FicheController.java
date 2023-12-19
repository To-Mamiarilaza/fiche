/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mamiarilaza.fiche.controller;

import authentification.JwtAuthentificationManager;
import authentification.LoginRequest;
import generalisation.utils.GenericUtil;
import jakarta.servlet.http.HttpServletRequest;
import mg.mamiarilaza.fiche.model.Fiche;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author to
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/gestionFiche")
public class FicheController {

    private final JwtAuthentificationManager authentifier;

    public FicheController() {
        this.authentifier = new JwtAuthentificationManager();
    }
    
    // Login
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            return new ApiResponse(ApiResponse.SUCCESS, authentifier.login(loginRequest));
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    // get all available fiche
    @GetMapping("/fiches")
    public ApiResponse getAllFiche(HttpServletRequest request) {
        try {
            authentifier.checkMethodAccess(request, Boolean.FALSE);
            return new ApiResponse(ApiResponse.SUCCESS, Fiche.getAll());
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    @GetMapping("/fiches/{idFiche}")
    public ApiResponse getFiche(@PathVariable int idFiche, HttpServletRequest request) {
        try {
            authentifier.checkMethodAccess(request, Boolean.FALSE);
            return new ApiResponse(ApiResponse.SUCCESS, Fiche.getById(idFiche));
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    @PostMapping("/fiches")
    public ApiResponse addNewFiche(@RequestBody Fiche fiche, HttpServletRequest request) {
        try {
            authentifier.checkMethodAccess(request, Boolean.FALSE);
            fiche.save();
            return new ApiResponse(ApiResponse.SUCCESS, "Employée inséré avec success !");
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    @PutMapping("/fiches")
    public ApiResponse updateFiche(@RequestBody Fiche fiche, HttpServletRequest request) {
        try {
            authentifier.checkMethodAccess(request, Boolean.FALSE);
            fiche.update();
            return new ApiResponse(ApiResponse.SUCCESS, "Employée inséré avec success !");
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/fiches/{idFiche}")
    public ApiResponse deleteFiche(@PathVariable int idFiche, HttpServletRequest request) {
        try {
            authentifier.checkMethodAccess(request, Boolean.FALSE);
            Fiche fiche = Fiche.getById(idFiche);
            fiche.delete();
            return new ApiResponse(ApiResponse.SUCCESS, "Employée supprimé avec success");
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    @GetMapping("/fiches/{idFiche}/validate")
    public ApiResponse validateFiche(@PathVariable int idFiche, HttpServletRequest request) {
        try {
            authentifier.checkMethodAccess(request, Boolean.TRUE);
            Fiche fiche = Fiche.getById(idFiche);
            fiche.validate();
            return new ApiResponse(ApiResponse.SUCCESS, "Employée validé avec success");
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }

    @GetMapping("/fiches/{idFiche}/refuse")
    public ApiResponse refuseFiche(@PathVariable int idFiche) {
        try {
            Fiche fiche = Fiche.getById(idFiche);
            fiche.refuse();
            return new ApiResponse(ApiResponse.SUCCESS, "Employée validé avec success");
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }
}
