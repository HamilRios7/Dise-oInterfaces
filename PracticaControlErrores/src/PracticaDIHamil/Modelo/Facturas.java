/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaDIHamil.Modelo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author m
 */
public class Facturas {
    
    String asunto;
    Date fecha;
    int cantidad;
    String tipo;

    public Facturas(String asunto, Date fecha, int cantidad, String tipo) {
        this.asunto = asunto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }
   

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    public String toString(){
         SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = sdf.format(fecha);
        return asunto+":"+tipo+":"+cantidad+":"+fechaFormateada;
    }
  
}
