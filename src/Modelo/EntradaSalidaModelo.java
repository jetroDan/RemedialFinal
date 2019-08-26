/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author jetro
 */
public class EntradaSalidaModelo {
    
    int id;
    String tipo;
    Date fecha;
    int person_id;

    public EntradaSalidaModelo() {
    }

    public EntradaSalidaModelo(int id, String tipo, Date fecha, int person_id) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.person_id = person_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
    
    
    
}
