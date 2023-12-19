/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentification;

import generalisation.genericDAO.GenericDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author to
 */
public class JwtAuthentificationManager {
    // Private field
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    
    private final String SECRET_KEY = "secret";
    private final long ACCESS_TOKEN_VALIDITY = 60*60*1000;  // une heure
    
    private final JwtParser jwtParser;
    
    // Constructor
    public JwtAuthentificationManager() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }
    
    // a method to get user with the given username and given password
    public Utilisateur getUser(String username, String password) throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);
        
        List<Utilisateur> utilisateurs = GenericDAO.find(utilisateur);
        if (utilisateurs.isEmpty()) {
            throw new Exception("Nom d'utilisateur ou mot de passe invalide !");
        }
        return utilisateurs.get(0);
    }
    
    // Crée un token pour l'utilisateur
    // Fonction pour crée le token et l'enregistrer
    public String createToken(Utilisateur utilisateur) throws Exception {
        Claims claims = Jwts.claims().setSubject(String.valueOf(utilisateur.getIdUtilisateur()));
        claims.put("admin", utilisateur.getAdmin());
        
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + ACCESS_TOKEN_VALIDITY);
        
        String token =  Jwts.builder()
                                .setClaims(claims)
                                .setExpiration(tokenValidity)
                                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                                .compact();
        return token;
    }
    
    // method to login
    public String login(LoginRequest loginRequest) throws Exception {
        Utilisateur utilisateur = getUser(loginRequest.getUsername(), loginRequest.getPassword());
        String token = createToken(utilisateur);
        return token;
    }   
    
    // Extract the token from the request
    public String resolveToken(HttpServletRequest request) throws Exception {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        throw new Exception("Aucun token n'est fournie avec la requette !");
    }
    
    // Get the claims from the request
    public Claims resolveClaims(HttpServletRequest request) throws Exception {
        try {
            String token = resolveToken(request);
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            request.setAttribute("expired", e.getMessage());
            throw e;
        } catch (Exception e) {
            request.setAttribute("invalid", e.getMessage());
            throw e;
        }
    }
    
    // chech access to a method
    public void checkMethodAccess(HttpServletRequest request, Boolean admin) throws Exception {
        Claims claims = resolveClaims(request);
        System.out.println("Claims " + claims.get("admin") + " et " + admin);
        if (!(Boolean) claims.get("admin") && admin) {
            throw new Exception("Vous n' êtes pas authoriser à acceder à cette fonction !");
        }
    }
}
