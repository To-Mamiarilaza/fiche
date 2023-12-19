/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mamiarilaza.fiche.controller;

import java.util.List;
import mg.mamiarilaza.fiche.model.Language;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author to
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/gestionLanguage")
public class LanguageController {
    
    @GetMapping("/languages")
    public ApiResponse getAllLanguage() {
        try {
            return new ApiResponse(ApiResponse.SUCCESS, Language.getAll());
        } catch (Exception e) {
            return new ApiResponse(ApiResponse.ERROR, e.getMessage());
        }
    }
}
