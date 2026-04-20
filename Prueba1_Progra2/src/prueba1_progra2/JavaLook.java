/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba1_progra2;

import java.util.Scanner;

/**
 *
 * @author Nathan
 */
public class JavaLook {

    static EmailAccount[] cuentas = new EmailAccount[100];
    static EmailAccount cuentaActiva = null;

    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);

        while (true) {
            if (cuentaActiva == null) {
                System.out.println("\n*******Menu Inicial******");
                System.out.println("1.Login");
                System.out.println("2.Crear Cuenta");

                int op = lea.nextInt();

                switch (op) {
                    case 1:
                        login(lea);
                        break;
                    case 2:
                        crearCuenta(lea);
                        break;
                }
            } else {
                menuPrincipal(lea);
            }
        }
    }
 
    public static void login(Scanner lea) {
        System.out.println("Correo:");
        String correo = lea.next();
        System.out.println("Password:");
        String pass = lea.next();

        for (EmailAccount acc : cuentas) {
            if (acc != null && acc.getDireccion().equals(correo) && acc.getPassword().equals(pass)) {
                cuentaActiva = acc;
                System.out.println("Login Exitoso");
                return;

            }
        }
        System.out.println("Credenciales incorrectas");
    }

    public static void crearCuenta(Scanner lea) {
        System.out.println("Correo:");
        String correo = lea.next();

        for (EmailAccount acc : cuentas) {
            if (acc != null && acc.getDireccion().equals(correo)) {
                System.out.println("Correo ya existe");
                return;
            }
        }

        System.out.println("Nombre:");
        String nombre = lea.next();
        System.out.println("Password:");
        String pass = lea.next();

        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                cuentas[i] = new EmailAccount(correo, pass, nombre);
                cuentaActiva = cuentas[i];
                System.out.println("Cuentacreada exitosamente");
                return;
            }
        }
        System.out.println("No hay espacio para mas cuentas");
    }

    public static void menuPrincipal(Scanner lea) {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Ver inbox");
        System.out.println("2. Mandar correo");
        System.out.println("3. Leer correo");
        System.out.println("4. Limpiar inbox");
        System.out.println("5. Cerrar sesion");

        int op = lea.nextInt();
        lea.nextLine();

        switch (op) {
            case 1:
                cuentaActiva.mostrarInbox();
                break;

            case 2:
                enviarCorreo(lea);
                break;

            case 3:
                System.out.print("Posición: ");
                int pos = lea.nextInt();
                cuentaActiva.leerCorreo(pos);
                break;

            case 4:
                cuentaActiva.limpiarLeidos();
                System.out.println("Inbox limpiado");
                break;

            case 5:
                cuentaActiva = null;
                break;
        }
    }

     public static void enviarCorreo(Scanner lea) {
        System.out.print("Destinatario: ");
        String destino = lea.next();

        EmailAccount receptor = null;

        for (EmailAccount acc : cuentas) {
            if (acc != null && acc.getDireccion().equals(destino)) {
                receptor = acc;
                break;
            }
        }

        if (receptor == null) {
            System.out.println("Destinatario no existe");
            return;
        }

        System.out.print("Asunto: ");
        String asunto = lea.next();

        System.out.print("Contenido: ");
        String contenido = lea.next();

        Email correo = new Email(cuentaActiva.getDireccion(),asunto,contenido);

        if (receptor.recibirCorreo(correo)) {
            System.out.println("Correo enviado");
        } else {
            System.out.println("Inbox lleno");
        }
    }
}
