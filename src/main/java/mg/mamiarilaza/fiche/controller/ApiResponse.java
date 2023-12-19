/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mamiarilaza.fiche.controller;

/**
 *
 * @author to
 */
public class ApiResponse {
    // Field
    String message;
    Object data;
    
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    
    // Getter and setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    // Constructor
    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    
}
