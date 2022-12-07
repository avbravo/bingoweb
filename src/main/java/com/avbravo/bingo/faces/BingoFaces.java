/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.avbravo.bingo.faces;

import com.avbravo.bingo.model.Numero;
import com.avbravo.bingo.repository.NumeroRepository;
import com.avbravo.jmoordbutils.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
@Data
public class BingoFaces implements Serializable {

    private static final long serialVersionUID = 1L;

    private Numero ultimoJugado = new Numero("", 0, "carton.png", "no");
    private Boolean showStart = Boolean.TRUE;
    private List<Numero> numeroList = new ArrayList<>();
    private List<Numero> numeroListJugados = new ArrayList<>();

    
    
    
    @Inject
    NumeroRepository numeroRepository;

    public List<Numero> getNumeroListJugados() {
        return numeroListJugados;
    }

    public void setNumeroListJugados(List<Numero> numeroListJugados) {
        this.numeroListJugados = numeroRepository.findByJugado("si");
        System.out.println(" JAY JUGADOZ "+numeroListJugados.size());
    }

    
    
    
    /**
     * Creates a new instance of IndexFaces
     */
    public BingoFaces() {
    }

    // <editor-fold defaultstate="collapsed" desc=" init">
    @PostConstruct
    public void init() {
        findAll();

    }
// </editor-fold>

    public String findAll() {
        numeroList = numeroRepository.findAll();
        JsfUtil.successMessage("Tiene " + numeroList.size() + " Jugados");
        return "";
    }

    public String hello() {
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
        } else {
            progress = progress + (int) (Math.random() * 35);

            if (progress > 100) {
                progress = 100;
            }
        }

        return progress;
    }

    public void onComplete() {
        jugar();
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

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public String jugar() {
        try {
            Integer value = 0;
            Boolean continuar = Boolean.TRUE;
            Integer total = numeroRepository.countByJugado("no");
            if (total == 0) {
                JsfUtil.warningMessage("No quedan numeros disponibles.");
                showStart = Boolean.FALSE;
                continuar = Boolean.FALSE;
            }

            while (continuar) {
               // value = getRandomNumber(1, 18);
                value = getRandomNumberUsingInts(1, 18);
                System.out.println("Value generado "+value);
                Numero numero = numeroRepository.findByNumero(value);
                if (numero == null) {

                } else {
                    if (numero.getNumero().equals(value)) {
                        if (numero.getJugado().equals("no")) {
                            Integer posicion = numeroRepository.positionOfNumero(value);
                            numeroList.get(posicion).setJugado("si");
                            ultimoJugado = numeroList.get(posicion);
                            continuar = Boolean.FALSE;
                        }
                    }
                }
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("jugar() " + e.getLocalizedMessage());
        }
        return "";
    }
}