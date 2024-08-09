package Programas.Reservation.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Programas.Reservation.Enum.Rooms;



public class Acomodacoes {
    
    List <String> cadastro = new ArrayList<>();

    Scanner inn = new Scanner (System.in);

    public Acomodacoes() {
        for (Rooms room : Rooms.values()){
            cadastro.add(room.toString());
        }
    }

    public List<String> getCadastro() {
        return cadastro;
    }

    public void showRooms(){

        System.out.println(" ---------------------------------------------------------------------");
        System.out.println(" |                           ACOMODAÇÕES                             |");
        System.out.println(" ---------------------------------------------------------------------");
        for ( int i = 0; i < cadastro.size(); i++){

            if (i < 4){
                if (i == 0){
                    System.out.print(" | ");
                }
                System.out.print("[" + (i+1) + "] " + cadastro.get(i) + " | ");
            } else if (i < 8){
                if (i == 4){
                    System.out.println();
                    System.out.print(" | ");
                }
                System.out.print("[" + (i+1) + "] " + cadastro.get(i) + "  | ");
            } else if ( i < 12){
                if (i == 8){
                    System.out.println();
                    System.out.print(" | ");
                    System.out.print("[" + (i+1) + "] "+ cadastro.get(i) + "   | ");
                } else if (i > 8){
                    System.out.print("[" + (i+1) + "] "+ cadastro.get(i) + "  | ");
                }
            } 
        }
        System.out.println("\n ---------------------------------------------------------------------");
    }


    public void book_a_Room(int quarto){

        if (quarto < 4){
            cadastro.add((quarto-1), "X X X X X ");
        } else if (quarto < 8){
            cadastro.add((quarto-1), "X X X X X");
        } else if (quarto < 12){
            cadastro.add((quarto-1), "X X X X ");
        }

    }

    public Rooms reservarRoom(int quarto){
        Rooms reserva = Rooms.CHALE_01;
        int cont = 0;
        for (Rooms room : Rooms.values()){
            if (cont == quarto){
                reserva = room;
                return reserva;
            }
            cont += 1;
        }
        return reserva;
    }

    

    public void cancelRoom(int quarto){
        String back = "";
        int cont = 0;
        for (Rooms room : Rooms.values()){
            if (cont == quarto){
                back = room.toString();
            }
            cont += 1;
        }

        for (int i = 0; i < cadastro.size(); i++){
            if (i == quarto){
                cadastro.add(i, back);
            }
        }
    }

    public void showMap(){
        List <String> map = new ArrayList<>();
       
        for (Rooms room : Rooms.values()){
            map.add(room.toString());
        }
       
        System.out.println(" ---------------------------------------------------------------------");
        System.out.println(" |                           ACOMODAÇÕES                             |");
        System.out.println(" ---------------------------------------------------------------------");
        for ( int i = 0; i < cadastro.size(); i++){

            if (i < 4){
                if (i == 0){
                    System.out.print(" | ");
                }
                System.out.print("[" + (i+1) + "] " + cadastro.get(i) + " | ");
            } else if (i < 8){
                if (i == 4){
                    System.out.println();
                    System.out.print(" | ");
                }
                System.out.print("[" + (i+1) + "] " + cadastro.get(i) + "  | ");
            } else if ( i < 12){
                if (i == 8){
                    System.out.println();
                    System.out.print(" | ");
                    System.out.print("[" + (i+1) + "] "+ cadastro.get(i) + "   | ");
                } else if (i > 8){
                    System.out.print("[" + (i+1) + "] "+ cadastro.get(i) + "  | ");
                }
            } 
        }
        System.out.println("\n ---------------------------------------------------------------------");
    }

}
