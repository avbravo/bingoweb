/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.avbravo.bingo.faces;

import com.avbravo.jmoordbutils.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
@Data
public class IndexFaces implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of IndexFaces
     */
    public IndexFaces() {
    }
    
        // <editor-fold defaultstate="collapsed" desc=" init">
    @PostConstruct
    public void init() {

    }
// </editor-fold>
    
    public String hello(){
        JsfUtil.warningMessage("Hola Mundo");
        System.out.println("llego al Hello.....");
        return "";
    }
}
