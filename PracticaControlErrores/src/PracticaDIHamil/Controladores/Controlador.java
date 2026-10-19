/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PracticaDIHamil.Controladores;

import PracticaDIHamil.Modelo.Facturas;
import PracticaDIHamil.Modelo.GestorFacturas;
import PracticaDIHamil.Vista.PantallaPrincipal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author m
 */
public class Controlador {
    
    PantallaPrincipal pantallaPrincipal;
    GestorFacturas gt;
        private static boolean editarDado=false;
    public Controlador( PantallaPrincipal pp){
        this.pantallaPrincipal=pp;
        gt=new GestorFacturas ();
    }
    
    
    
    public void revisarFormulario() throws ParseException{
        String asunto,tipoEmpresa,cantidadString;
        int dia, mes, año, cantidad=0;
        asunto=pantallaPrincipal.getjTextFieldAsunto().getText();
        dia=(int) pantallaPrincipal.getjSpinnerDay().getValue();
        mes=(int) pantallaPrincipal.getjSpinnerMonth().getValue();
        año=(int) pantallaPrincipal.getjSpinnerAño().getValue();
        cantidadString=pantallaPrincipal.getjTextFieldCantidad().getText();
        try{
        cantidad=Integer.parseInt(cantidadString);
        }catch(NumberFormatException iop){//avisamos que deben ser numeros en cantidad para continuar
            pantallaPrincipal.establecerLabelInformacion("La cantidad tiene que ser un numero",false);
            return;
        }
        tipoEmpresa=(String) pantallaPrincipal.getjComboBoxTipo().getSelectedItem();
        
        Date fechaActual= new Date(); //Fecha actual
        
        int caracteres = asunto.length();

        String fechaMinima="2000-01-01"; //fecha minima 
        String fechaIntroducida=año+"-"+mes+"-"+dia; //fecha introducida
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //Formato que queremos por comodidad
        Date fechaComparar = formatter.parse(fechaMinima); //fecha minima formateada 
      
        Date fechaFormulario = formatter.parse(fechaIntroducida);//fecha introducida
        
        if(caracteres == 0 || caracteres >10){//comprobamos asunto
                pantallaPrincipal.establecerLabelInformacion("El asunto debe tener entre 1 y 10 caracteres",false);
               
                
        }else if((dia>30 || dia<1 && mes==1) ||(dia>28 || dia<1 && mes==2) ||  (dia>31 || dia<1 && mes==3) || (dia>30 || dia<1&& mes==4) || (dia>31 || dia<1&& mes==5) 
                || (dia>30 || dia<1 && mes==6) || (dia>31 || dia<1 && mes==7) || (dia>30 || dia<1 && mes==8) || (dia>31 || dia<1 && mes==9)
                || (dia>31 || dia<1 && mes==10) || (dia>30 || dia<1 && mes==11) || (dia>31 || dia<1 && mes==12)){
            
            pantallaPrincipal.establecerLabelInformacion("Superas el número de días del mes",false);
            
            
        }else if(fechaFormulario.before(fechaComparar) ){//comprobamos fecha minima
            pantallaPrincipal.establecerLabelInformacion("La fecha tiene que ser mayor al 1 de enero del 2000",false);
            
            
        }else if(fechaFormulario.after(fechaActual)) {//comprobamos fecha maxima que es la actual
            pantallaPrincipal.establecerLabelInformacion("La fecha no es la actual :" + fechaActual  ,false  );
            
            
        }else if(cantidad < 0){//comprobamos la cantidad
              pantallaPrincipal.establecerLabelInformacion("La cantidad tiene que ser mayor o igual a 0",false);
            
        }else{
           if(!editarDado){//si no le ha dado a editar, quiere decir que esta añadiendo
               
                Facturas fact= new Facturas( asunto, fechaFormulario,  cantidad ,  tipoEmpresa);
                boolean existe= gt.comprobarFacturas(fact);//Comprobamos para menor codigo en controlador que existe directamente desde nuestra base de memoria
                if(existe){
                    pantallaPrincipal.establecerLabelInformacion("Ya existe factura con este asunto",false);
             
                }else{
                    gt.añadirFactura(fact);//añadimos la factura al Jlist y a la base de memoria con el gestor
                    pantallaPrincipal.mostrarListaFactura(fact);
                    pantallaPrincipal.establecerLabelInformacion("Añadido con exito",true);
               
                }
            
           }else{//en este caso le ha dado editar , por lo tanto le damos la funcion de actualizar
            Facturas fact= new Facturas( asunto, fechaFormulario,  cantidad ,  tipoEmpresa);

            // La factura que estoy editando es la seleccionada en la lista
            int index = pantallaPrincipal.getjList().getSelectedIndex();
            Facturas factVieja = (Facturas) pantallaPrincipal.getModelolista().getElementAt(index);
            
            //Comprobamos si ha cambiado el asunto a uno nuevo
            boolean cambiaAsunto = !factVieja.getAsunto().equals(asunto);

            if(cambiaAsunto && gt.comprobarFacturas(fact)){// si lo ha hecho , nos aseguramos que ya no exista, si lo hace , no le dejamos
                pantallaPrincipal.establecerLabelInformacion("Ya existe factura con este asunto",false);
            }else{
                gt.actualizarFactura(fact,factVieja);
                pantallaPrincipal.actualizarListaFactura(fact, factVieja);
                pantallaPrincipal.establecerLabelInformacion("Actualizado con exito",true);
            }
           }
        }
            
    }
    
    
    public void controlarEdicion(int index){
        if(index==-1){
            pantallaPrincipal.establecerLabelInformacion("Tienes que selecionar alguno de la lista para editar" , false);
        }else {
            Facturas fact = (Facturas)pantallaPrincipal.getModelolista().getElementAt(index);//pillamos el objeto factura que tiene marcado
            
            pantallaPrincipal.darDatosEdicionFactura(fact);//metemos los datos del objeto factura seleccionado en cada campo de datos 
        }
    }
    
    
      public void controlarEliminacion(int index,Facturas fact){
        if(index==-1){
            pantallaPrincipal. establecerLabelInformacion("Tienes que selecionar alguno de la lista para eliminar" , false);
           
        }else {
             if(gt.eliminarFactura(fact)){//comprobamos que ha salido bien eliminar si de algun modo llega a fallar
                pantallaPrincipal.eliminarListaFactura(fact);
                pantallaPrincipal.establecerLabelInformacion("Eliminado con exito",true); 
            }else {
                pantallaPrincipal.establecerLabelInformacion("Ha fallado algo",false);
            }
          
        }
        
    }

    public static boolean isEditarDado() {
        return editarDado;
    }

    public static void setEditarDado(boolean editarDado) {
        Controlador.editarDado = editarDado;
    }
      
      
      
      
      
}
