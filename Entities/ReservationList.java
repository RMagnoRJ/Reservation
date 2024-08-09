package Programas.Reservation.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Programas.Reservation.Enum.Rooms;



public class ReservationList {
    
     List <Reservation> book = new ArrayList<>();

    public ReservationList() {
    }

    public ReservationList(List<Reservation> book) {
        this.book = book;
    }

    public List<Reservation> getBook() {
        return book;
    }

    public void addReservation(Reservation reserva){
        book.add(reserva);
    }

    public void removeReservation(int index){
        book.remove(index);
    }

    public void removeLast(){
        book.removeLast();
    }

    public void showReservation(){
        
        @SuppressWarnings("resource")
        Scanner inn = new Scanner (System.in);
        @SuppressWarnings("unused")
        String wait = "";

        for (int i = 0; i < book.size(); i++){
            System.out.println("Registro # " + (i+1) + "\n");
            book.get(i).showReserva();
            System.out.print("\nPressione [C] Continuar >>> ");
            wait = inn.nextLine();
            System.out.println("\n");
            if (i == (book.size()-1)){
                System.out.println("\nFim do cadastro!\n");
            }
        }

        
    }

    public int availability(int quartoInt, LocalDate data){
        int yes = 1;
        Reservation reserva = new Reservation();
        Rooms quartoAval = reserva.conversorInt_Room(quartoInt);

        for (int i = 0; i < book.size(); i++){

            if (book.get(i).getRoom() == quartoAval && book.get(i).getCheckin().compareTo(data) == 0){
                yes = -1;
            }
        }
        
        return yes;
    }

    public void findReserva (String nomeBusca){

        
        @SuppressWarnings("resource")
        Scanner inn = new Scanner (System.in);

        for (int i = 0; i < book.size(); i++){

            if (book.get(i).getInfo().getNome().charAt(0) == nomeBusca.charAt(0)){
                if (book.get(i).getInfo().getNome().charAt(1) == nomeBusca.charAt(1)){
                    if (book.get(i).getInfo().getNome().charAt(2) == nomeBusca.charAt(2)){
                        System.out.println();
                        book.get(i).showReserva();
                        @SuppressWarnings("unused")
                        String wait = inn.nextLine();
                    }
                }
            } 

        }

    }



}
