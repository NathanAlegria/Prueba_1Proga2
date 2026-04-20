/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_progra2;

/**
 *
 * @author Nathan
 */
public class EmailAccount {
    
    private String direccion;
    private String password;
    private String nombre;
    private Email[] inbox;

    public EmailAccount(String direccion, String password, String nombre, Email[] inbox) {
        this.direccion = direccion;
        this.password = password;
        this.nombre = nombre;
        this.inbox = new Email[100];
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }    
    
    public boolean recibirCorreo(Email correo){
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i]==null) {
                inbox[i]=correo;
                return true;
            }
        }
        return false;
    }
    
    public void mostrarInbox(){
        System.out.println("Cuenta:"+direccion+"\nNombre:"+nombre);
        
        int noLeidos=0;
        int total=0;
        
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] !=null) {
                total++;
                String estado=inbox[i].isLeido()?"Leído":"Sin Leer";
                if (!inbox[i].isLeido()) {
                    noLeidos++;
                }
                System.out.println(i+" - "+inbox[i].getEmisor()+" - "+inbox[i].getAsunto()+" - "+estado);
            }
        }
        System.out.println("Correos sin Leer:"+noLeidos+"\nTotal de correos:"+total);
    }
    
    public void leercorreo(int pos){
        if (pos>=0 && pos<inbox.length && inbox[pos]!=null) {
            inbox[pos].imprimir();
            inbox[pos].marcarleido();
        }else{
            System.out.println("Correo no Existe");
        }
    }
    
    public void limpiarLeidos(){
        for (int i = 0; i < inbox.length; i++) {
            if(inbox[i]!=null && inbox[i].isLeido()){
                inbox[i]=null;
            }
        }
    }
}
