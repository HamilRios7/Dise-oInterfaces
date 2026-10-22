/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaDIHamil.Modelo;


import PracticaDIHamil.Vista.PantallaPrincipal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultListModel;

/**
 *
 * @author m
 */
public class GestorFacturas {
    
    ArrayList <Facturas> gf;
  
    
    public GestorFacturas(){
        gf=new ArrayList<Facturas>();  
    }
    
    
  
    
    public boolean comprobarFacturas(Facturas fact){//Metodo de ayuda para reducir codigo y en controlador y revisar que no este repetido
        boolean existe=false;
        
        
        for(int i =0; i < gf.size() && !existe; i++){
         if(gf.get(i).getAsunto().equals(fact.getAsunto())){
             
                 existe=true;
            }
            
        }
        
        return existe;
    }
    
    
    public void añadirFactura(Facturas fact ){
        gf.add(fact);
    }
    
    
    public boolean eliminarFactura(Facturas fact){
         boolean existe=false;
        
        
        for(int i =0; i < gf.size() && !existe; i++){
           
            if(gf.get(i).getAsunto().equals(fact.getAsunto())){
                gf.remove(i);
                 existe=true;
            }
        }
        
      
           return existe;
        
           
        
        
    }
    
    
    public void actualizarFactura(Facturas fact, Facturas factVieja){
      boolean existe=false;
       

        for(int i = 0; i < gf.size() && !existe; i++){
            if(gf.get(i).getAsunto().equals(factVieja.getAsunto())){
                gf.get(i).setAsunto(fact.getAsunto());
                gf.get(i).setFecha(fact.getFecha());
                gf.get(i).setCantidad(fact.getCantidad());
                gf.get(i).setTipo(fact.getTipo());
                existe = true;
            }
        }

       }
        
        
     
 
        
    }


    
  

