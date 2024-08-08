package ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Enum.Rooms;

public class Reservation {
    
    private Rooms room;

    private ReservationInfo info;

    private LocalDate checkin;

    private LocalDate checkout;

    private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Rooms room, LocalDate checkin, LocalDate checkout, ReservationInfo info) {
            this.room = room;
            this.checkin = checkin;
            this.checkout = checkout;
            this.info = info;
    }


    public Rooms getRoom() {
        return room;
    }

    public ReservationInfo getInfo(){
        return info;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }


    /*
    public int duration(){
        int dias = (int) checkin.until(checkout, ChronoUnit.DAYS);
        // CASTING do dado original <LONG> para RETORNAR o esperado em INT
        return dias;
    }
 */

    public Rooms conversorInt_Room(int quartoInt){

            int cont = 0;

            Rooms roomConvertido = Rooms.QUARTO_101;

            for (Rooms quartoRoom : Rooms.values()){

                if (cont == quartoInt){
                    roomConvertido = quartoRoom;
                }
                cont += 1;
            }
            return roomConvertido;
    }

    public int conversorRoom_Int(Rooms convRoom){

        List <Rooms> roomList = new ArrayList<>();
        
        for (Rooms room : Rooms.values()){
            roomList.add(room);
        }
        int quartoInt = -1;        

        for (int i = 0; i < 12; i++){

            if (roomList.get(i) == convRoom){
                quartoInt = i;
            }
        }
        return quartoInt;
}



    public int available(int quartoInt, LocalDate data){
        int yes = 0;

        Rooms quartoAval = conversorInt_Room(quartoInt);

        if (getRoom() == quartoAval && getCheckin() == data){
            yes = -1;
        } else {
            yes = 1;
        }
        return yes;
    }

    public int duration(){
        int dias = (int) ChronoUnit.DAYS.between(checkin, checkout);
        // CASTING do dado original <LONG> para RETORNAR o esperado em INT
        return dias;
    }


    public int verifyDate(LocalDate checkin, LocalDate checkout){
        
        int verify = checkout.compareTo(checkin);

        if (verify == 0){
            verify = 0;
        } else if (verify < 0){
            verify = -1;
        } else if (verify > 0){
            verify = 1;
        }
        return verify;
    }

    
    
    public void updateDates(LocalDate checkin, LocalDate checkout){
        
        int verify = checkin.compareTo(checkout);

        if (verify == 0){
            System.out.println("Operação INVÁLIDA!\nDATAS REPETIDAS!");
        } else if (verify < 0){
            System.out.println("Operação INVÁLIDA!\n" +
            "Data de CHECKout é INFERIOR a data de CHECKin!");
        } else if (verify > 0){
            this.checkin = checkin;
            this.checkout = checkout;
            System.out.println("Operação realizada com sucesso!");
        }

    }

    public void showReserva(){

        System.out.println("----------------------------------");
        System.out.println("  QUARTO: " + this.room);
        System.out.println("----------------------------------");
        info.showInfo();
        System.out.println("----------------------------------");
        System.out.println("  CheckIN: " + dataFormat.format(checkin));
        System.out.println("  CheckOUT: " + dataFormat.format(checkout));
        System.out.println("  Hospedagem: " + duration() + " dia(s)");
        System.out.println("----------------------------------");
        
    }

 
    public void alteraDados(int opcao, String dado){
        info.altera_Info(opcao, dado);
    }

    public void alteraRoom(int novoQuarto){
        Acomodacoes newRoom = new Acomodacoes();
        this.room = newRoom.reservarRoom(novoQuarto);
    }

    public void alteraData(LocalDate novoCheckIn, LocalDate novoCheckOut){
        this.checkin = novoCheckIn;
        this.checkout = novoCheckOut;
    }

    
}
