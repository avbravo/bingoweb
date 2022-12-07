/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.avbravo.bingo.faces;

import com.avbravo.jmoordbutils.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
    
    
    private Integer progress1;
    private Integer progress2;

    public void longRunning() throws InterruptedException {
        setProgress2(0);
        Integer k = getProgress2();
        while (k == null || k < 100) {
            k = updateProgress(k);
            setProgress2(k);
            Thread.sleep(500);
        }
    }

    private static Integer updateProgress(Integer progress) {
        if (progress == null) {
            progress = 0;
        }
        else {
            progress = progress + (int) (Math.random() * 35);

            if (progress > 100) {
                progress = 100;
            }
        }

        return progress;
    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
    }

    public void cancel() {
        progress1 = null;
        progress2 = null;
    }

    public Integer getProgress1() {
        progress1 = updateProgress(progress1);
        return progress1;
    }

    public Integer getProgress2() {
        return progress2;
    }

    public void setProgress1(Integer progress1) {
        this.progress1 = progress1;
    }

    public void setProgress2(Integer progress2) {
        this.progress2 = progress2;
    }
}
