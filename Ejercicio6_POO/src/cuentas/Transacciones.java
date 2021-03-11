package cuentas;

import java.util.Scanner;

/*
 Crear un programa para realizar gestiones de un banco para lo cual tendremos 2 
 clases (Cliente y Cuenta) . Considere que un cliente se caracteriza por nombre 
 , apellido y DNI . El cliente puede consultar saldo , asi como ingresar y retirar
 dinero de sus cuentas . Ademas cada cuenta se caracteriza por un número de cuenta 
 y saldo . 

*/

public class Transacciones {
     
    //Creamis un método para buscar el número de cuenta
     public static int buscarNumeroDeCuenta(Cuenta cuentas[],int numeroDeCuentaAbucar){
         int indice = 0;
         int iterador = 0;
         boolean numeroDeCuentaEncontrada = false;
         
         while((iterador < cuentas.length) && (numeroDeCuentaEncontrada == false)){
              if(cuentas[iterador].getNumeroCuenta() == numeroDeCuentaAbucar){
                    numeroDeCuentaEncontrada = true;
                    indice = iterador;
              }
              iterador ++;
         }
         
         if(numeroDeCuentaEncontrada == false){
             indice = -1;
         }  
         return indice;
     }
    
     public static void main(String[] args) {   
           Scanner teclado = new Scanner(System.in);
           String nombre,apellido,dni;
           Cuenta cuentas[];
           Cliente cliente;
           int numeroCuenta,cantidadCuentas,opcion,indiceNumeroCuenta;
           double saldo,cantidadDinero;
     
           //Pedimos los datos del cliente
           System.out.print("Digite el nombre del cliente : ");
           nombre = teclado.next();
           
           System.out.print("Digite el apellido del cliente : ");
           apellido = teclado.next();
           
           System.out.print("Digite el dni del cliente : ");
           dni = teclado.next();
    
           System.out.print("\nAhora digite la cantidad de cuentas que tiene el cliente : ");
           cantidadCuentas = teclado.nextInt();
           
           System.out.println("");
           
           cuentas = new Cuenta[cantidadCuentas];
           
           //rellenamos las cuentas
           for(int i = 0;i < cuentas.length; i++){
                System.out.println("\nDigite la " +(i+1)+"º cuenta : ");
                
                System.out.print("Digite el numero de cuenta : ");
                numeroCuenta = teclado.nextInt();
                
                System.out.print("Digite el saldo de la cuenta : ");
                saldo = teclado.nextDouble();
                System.out.println("");
                cuentas[i] = new Cuenta(numeroCuenta, saldo);
           }
           
           cliente = new Cliente(nombre, apellido, dni, cuentas);
           
           do{
               System.out.println("\n\tMENU");
               System.out.println("1 . Ingresar saldo a la cuenta");
               System.out.println("2 . Retirar saldo de la cuenta");
               System.out.println("3 . Consultar saldo de la cuenta");
               System.out.println("4 . Salir");
               System.out.print("Digite una opcion : ");
               opcion = teclado.nextInt();
              
               switch(opcion){
                   case 1:
                       System.out.print("\nDigite el numero de cuenta : ");
                       numeroCuenta = teclado.nextInt();
                       indiceNumeroCuenta = buscarNumeroDeCuenta(cuentas, numeroCuenta);
                       
                       if(indiceNumeroCuenta == -1){
                           System.out.println("\nCuenta no encontrado , verifique su número de cuenta");
                           System.out.println("");
                       }else{
                           System.out.print("Digite la cantidad de dinero a depositar : ");   
                           cantidadDinero = teclado.nextDouble();
                           
                           cliente.ingresarDinero(indiceNumeroCuenta, cantidadDinero);
                           System.out.println("\nDeposito con exito ");
                           System.out.print("Saldo disponible : " + cliente.consultarSaldo(indiceNumeroCuenta));
                           System.out.println("");
                       }
                       
                       break;
                       
                   case 2:
                       System.out.print("\nDigite el numero de cuenta : ");
                       numeroCuenta = teclado.nextInt();
                       indiceNumeroCuenta = buscarNumeroDeCuenta(cuentas, numeroCuenta);
                       
                       if(indiceNumeroCuenta == -1){
                           System.out.println("\nNo existe una cuenta con ese número");
                           System.out.println("");
                       }
                       else{
                           System.out.print("\nDigite la cantidad de dinero a retirar : ");
                           cantidadDinero = teclado.nextDouble();
                           
                           if(cliente.consultarSaldo(indiceNumeroCuenta) < cantidadDinero){
                               System.out.print("Saldo insuficiente");  
                               System.out.println("");
                           }else{
                               cliente.retirarDinero(indiceNumeroCuenta, cantidadDinero);
                               System.out.println("");
                               System.out.println("Retiro con exito");
                               System.out.println("Saldo disponible de la cuenta : " + cliente.consultarSaldo(indiceNumeroCuenta));
                               System.out.println("");
                           }
                       }
                       
                       break;
                       
                   case 3:
                       System.out.print("\nDigite el numero de cuenta : ");
                       numeroCuenta = teclado.nextInt();
                       indiceNumeroCuenta = buscarNumeroDeCuenta(cuentas, numeroCuenta);
                       
                       if(indiceNumeroCuenta == -1){
                           System.out.println("\nNo existe una cuenta con ese número");
                           System.out.println("");
                       }else{
                           System.out.println("Saldo disponible : " + cliente.consultarSaldo(indiceNumeroCuenta));
                           System.out.println("");
                       }
                       break;
                       
                   case 4:
                       System.out.println("\nGracias por participar");
                       opcion = 4;
                       break;
                       
                   default:
                       System.out.println("\nOpcion no disponible , vuelva a digitar");
                       break;
               }
           }while(opcion != 4);
    }
}