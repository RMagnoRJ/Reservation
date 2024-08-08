package ProgramaçãoOrientadaObjeto.Classes.Reservation.Application;

import java.time.LocalDate;
import java.util.Scanner;

import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Entities.Acomodacoes;
import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Entities.Reservation;
import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Entities.ReservationInfo;
import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Entities.ReservationList;
import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Enum.Rooms;
import ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Services.GeneralFunctions;




public class Program {
    
    public static void main(String[] args) {
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("                   RESERVATION v 1.0                 ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        Scanner inn = new Scanner (System.in);
        Acomodacoes listaVagas = new Acomodacoes();
        ReservationList cadastroGeral = new ReservationList();
        GeneralFunctions function = new GeneralFunctions();
        boolean programa =  true;

        while (programa == true){
            int menu = 0;
    // MENU
            System.out.println("\n      **********");
            System.out.println("      |  MENU  |");
            System.out.println("      **********\n");

            System.out.print("\n[1] Fazer reserva\n" +
            "[2] Buscar reserva\n" +
            "[3] Alterar reserva\n" +
            "[4] Cancelar reserva\n" +
            "[5] Cadastro geral de reservas\n" +
            "[6] Encerrar programa\n");
            menu = function.recebeInt(1, 6);
            switch (menu) {
                case 1:
    // REALIZAR
                    System.out.println("\n  ************");
                    System.out.println("    CADASTRO  ");
                    System.out.println("  ************\n");
                    String nome = function.getStringMin("Nome", 3);
                    String cpf = function.getStringMaxMin("CPF", 11, 14);
                    String ddd = function.getStringMax("DDD", 2);
                    String telefone = function.getStringMaxMin("Telefone", 8, 10);
                    
                    ReservationInfo cadInfo = new ReservationInfo(nome, cpf, ddd, telefone);
                    int quarto;
                    
                    boolean dataReserva = true;

                    while (dataReserva == true){
                       
                        System.out.println(); 
                        listaVagas.showMap();
                        quarto = function.recebeIntIndex("Quarto", 1, 12);
                        
                        System.out.println("\n>>> " + listaVagas.reservarRoom(quarto-1) + " escolhido com sucesso!\n");
                        System.out.println("\n  ***********");
                        System.out.println("    RESERVA  ");
                        System.out.println("  ***********\n");
                        Reservation reserva = new Reservation();

                        boolean datas = true;

                        LocalDate checkin = LocalDate.of(2000, 1, 1);
                        LocalDate checkOut = LocalDate.of(2000, 1, 1);

                        while (datas == true){

                            System.out.println("\n CheckIN \n");

                            int dia = function.recebeIntVariavel("Dia", 1, 31);
                            int mes = function.recebeIntVariavel("Mês", 1, 12);
                            int ano = function.recebeIntVariavel("Ano", 2024, 2025);
        
                            checkin = LocalDate.of(ano, mes, dia);
        
                            System.out.println("\n\n CheckOUT \n");

                            int diaOut = function.recebeIntVariavel("Dia", 1, 31);
                            int mesOut = function.recebeIntVariavel("Mês", 1, 12);;
                            int anoOut =  function.recebeIntVariavel("Ano", 2024, 2025);

                            checkOut = LocalDate.of(anoOut, mesOut, diaOut);

                            if (reserva.verifyDate(checkin, checkOut) == 1){
                                datas = false;
                                break;

                            } else if (reserva.verifyDate(checkin, checkOut) == 0) {
                                System.out.println("Operação INVÁLIDA!\nDATAS REPETIDAS!");

                            } else if (reserva.verifyDate(checkin, checkOut) == -1){
                                System.out.println("Operação INVÁLIDA!\n" +
                                             "Data de CHECKout é INFERIOR a data de CHECKin!");
                            }
                        }

                        if (cadastroGeral.getBook().size() > 0){

                            if (cadastroGeral.availability((quarto-1), checkin) == 1){
                                reserva = new Reservation(listaVagas.reservarRoom(quarto-1), checkin, checkOut, cadInfo);
                                cadastroGeral.addReservation(reserva);
                                dataReserva = false;
                                datas = true;
                                break;
                            
                            } else if (cadastroGeral.availability((quarto-1), checkin) == -1){
                                
                                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                                 "Já existe uma reserva para a DATA,\n" + 
                                                 "e o QUARTO selecionados! Por favor, \n" +
                                                 "consulte o cadastro de reservas!");
                                System.out.print("\nPressione [C] continuar >>> ");
                                @SuppressWarnings("unused") String wait = inn.nextLine();
                            }


                        } else {
                            reserva = new Reservation(listaVagas.reservarRoom(quarto-1), checkin, checkOut, cadInfo);
                            cadastroGeral.addReservation(reserva);
                            System.out.println("\nOperação realizada com sucesso!");
                            dataReserva = false;
                            break;
                        }
                        
                    }

                    System.out.print("\nPressione [C] continuar >>> ");
                    @SuppressWarnings("unused") String wait = inn.nextLine();
                    System.out.println();
                    break;
                
                case 2:
    // BUSCAR RESERVA
                    System.out.println("\n  ***********");
                    System.out.println("    RESERVA  ");
                    System.out.println("  ***********\n");
                    int busca;
                    System.out.print("Escolha uma opção: \n" +
                    "\n[1] Buscar por NÚMERO do quarto\n" +
                    "[2] Buscar por NOME da reserva\n" +
                    "[3] Buscar por DATA da reserva\n" +
                    "\n> ");
                    busca = function.recebeInt(1, 3);

                    if (busca == 1){

                        boolean buscadorQuarto = true;

                        while (buscadorQuarto == true){

                            listaVagas.showMap();
                            System.out.print("\nDigite o [NÚMERO] do quarto: ");
                            int buscaQuarto = function.recebeIntVariavel("Quarto", 1, 12);
                            Rooms room;
                            room = cadastroGeral.getBook().get(0).conversorInt_Room(buscaQuarto-1);
                            System.out.println();
                            boolean emptyRoom = true;
                            for (int i = 0; i < cadastroGeral.getBook().size(); i++){
    
                                if (cadastroGeral.getBook().get(i).getRoom() == room){
                                    cadastroGeral.getBook().get(i).showReserva();

                                    System.out.print("\nSelecione: \n"+
                                    "\n[1] ENCERRAR busca\n"+
                                    "[2] AVANÇAR busca\n"+
                                    "\n> ");
                                    int confirma = function.recebeInt(1, 2);

                                    if (confirma == 1){
                                        emptyRoom = false;
                                        buscadorQuarto = false;
                                        i = cadastroGeral.getBook().size();
                                        System.out.println("\nFim da busca!");
                                        System.out.print("Pressione [C] continuar >>> ");
                                        wait = inn.nextLine();
                                    }

                                    
                                } 
                            }
                            if (emptyRoom == true){
                                System.out.println("\nNão foram localizadas reservas para esta acomodação!\n");
                                System.out.println("\nFim da busca!\n");
                                
                                System.out.print("\nSelecione: \n" +
                                "\n[1] Realizar NOVA BUSCA\n" +
                                "[2] Voltar ao MENU principal\n" +
                                "\n> ");
                                int buscaMenu = function.recebeInt(1, 2);
    
                                if (buscaMenu == 2){
                                    buscadorQuarto = false;
                                    break;
                                }
                            }
                        }

                    } else if (busca == 2) {

                        String buscaNome = "";
                        boolean buscadorNome = true;

                        while (buscadorNome == true){

                            buscaNome = function.getStringMin("Nome", 3);
                            System.out.println();
                            boolean noNameFound = true;
                            for (int i = 0; i < cadastroGeral.getBook().size(); i++){
    
                                if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(0) == buscaNome.charAt(0)){
                                    if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(1) == buscaNome.charAt(1)){
                                        if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(2) == buscaNome.charAt(2)){
                                            
                                            cadastroGeral.getBook().get(i).showReserva();

                                            System.out.print("\nSelecione: \n"+
                                            "\n[1] ENCERRAR busca\n"+
                                            "[2] AVANÇAR busca\n"+
                                            "\n> ");
                                            int confirma = function.recebeInt(1, 2);

                                            if (confirma == 1){
                                                noNameFound = false;
                                                buscadorNome = false;
                                                i = cadastroGeral.getBook().size();
                                                System.out.println("\nFim da busca!");
                                                System.out.print("Pressione [C] continuar >>> ");
                                                wait = inn.nextLine();
                                            }
                                        }
                                    }
                                }
                            }
                            if (noNameFound == true){
                                System.out.println("\nNão foram localizadas reservas com este nome!\n");
                                System.out.println("\nFim da busca!");

                                System.out.print("\nSelecione: \n" +
                                "\n[1] Realizar NOVA BUSCA\n" +
                                "[2] Voltar ao MENU principal\n" +
                                "\n> ");
                                int buscaMenu = function.recebeInt(1, 2);

                                if (buscaMenu == 2){
                                    buscadorNome = false;
                                    break;
                                } else {
                                    buscaNome = "";
                                }
                            }
                        }
                    } else if (busca == 3){

                        boolean buscadorData = true;

                        while (buscadorData == true){

                            System.out.print("\nDATA da reserva \n\n");


                            int buscaDia = function.recebeIntVariavel("Dia", 1, 31);
                            int buscaMes = function.recebeIntVariavel("Mês", 1, 12);
                            int buscaAno = function.recebeIntVariavel("Ano", 2024, 2025);
                            
                            LocalDate buscaData = LocalDate.of(buscaAno, buscaMes, buscaDia);

                            boolean emptyData = true;
    
                            for (int i = 0; i < cadastroGeral.getBook().size(); i++){
    
                                if ( cadastroGeral.getBook().get(i).verifyDate(cadastroGeral.getBook().get(i).getCheckin(), buscaData) == 0 ){
                                    cadastroGeral.getBook().get(i).showReserva();
                                    

                                    System.out.print("\nSelecione: \n"+
                                    "\n[1] ENCERRAR busca\n"+
                                    "[2] AVANÇAR busca\n"+
                                    "\n> ");
                                    int confirma = function.recebeInt(1, 2);

                                    if (confirma == 1){
                                        emptyData = false;
                                        buscadorData = false;
                                        i = cadastroGeral.getBook().size();
                                        System.out.println("\nFim da busca!");
                                        System.out.print("Pressione [C] continuar >>> ");
                                        wait = inn.nextLine();
                                    }
                                }
                            }
                            if (emptyData == true){
                                System.out.println("\nNão foram localizadas reservas para esta DATA!\n");
                                System.out.println("\nFim da busca!");

                                System.out.print("\nSelecione: \n" +
                                "\n[1] Realizar NOVA BUSCA\n" +
                                "[2] Voltar ao MENU principal\n" +
                                "\n> ");
                                int buscaMenu = function.recebeInt(1, 2);

                                if (buscaMenu == 2){
                                    buscadorData = false;
                                    break;
                                } 
                            }
                        }
                    }
                    break;
                    

                case 3:
    // ALTERAR
                    int altera;
                    System.out.print("\nEscolha uma opção: \n" +
                    "\n[1] Alterar DADOS CADASTRAIS\n" +
                    "[2] Alterar NÚMERO DO QUARTO\n" +
                    "[3] Alterar DATA da reserva\n" +
                    "\n> ");
                    altera = function.recebeInt(1, 3);

                    if (altera == 1){
                        boolean buscandoNome = true;

                        while (buscandoNome == true){

                            String buscaNome = function.getStringMin("Nome", 3);
                            System.out.println();
                            
                            for (int i = 0; i < cadastroGeral.getBook().size(); i++){

                                if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(0) == buscaNome.charAt(0)){
                                    if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(1) == buscaNome.charAt(1)){
                                        if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(2) == buscaNome.charAt(2)){

                                            cadastroGeral.getBook().get(i).showReserva();

                                            System.out.print("\nConfirma a reserva acima? \n"+
                                            "\n[1] SIM, alterar essa reserva \n"+
                                            "[2] NÃO, continuar a busca\n"+
                                            "\n> ");
                                            int confirma = function.recebeInt(1, 2);

                                            if (confirma == 1){

                                                buscandoNome = false;
                                                System.out.print("\nSelecione a INFORMAÇÃO que deseja alterar:\n"+
                                                "\n[1] NOME\n" +
                                                "[2] CPF\n" +
                                                "[3] TELEFONE\n" +
                                                "\n> ");
                                                int alteraInfo = function.recebeInt(1, 3);

                                                switch (alteraInfo) {

                                                    case 1:

                                                        String alteraNome = function.getStringMin("Nome", 3);
                                                        cadastroGeral.getBook().get(i).alteraDados(1, alteraNome);
                                                        System.out.println("\nAlteração realizada com sucesso!\n");
                                                        cadastroGeral.getBook().get(i).showReserva();
                                                        System.out.print("Pressione [C] continuar >>> ");
                                                        wait = inn.nextLine();
                                                        break;
                                                    
                                                    case 2:

                                                        String alteraCpf = function.getStringMaxMin("CPF", 11, 14);
                                                        cadastroGeral.getBook().get(i).alteraDados(2, alteraCpf);
                                                        System.out.println("\nAlteração realizada com sucesso!\n");
                                                        cadastroGeral.getBook().get(i).showReserva();
                                                        System.out.print("Pressione [C] continuar >>> ");
                                                        wait = inn.nextLine();
                                                        break;

                                                    case 3:

                                                        String alteraTelefone = function.getStringMaxMin("Telefone", 8, 10);
                                                        cadastroGeral.getBook().get(i).alteraDados(3, alteraTelefone);
                                                        System.out.println("\nAlteração realizada com sucesso!\n");
                                                        cadastroGeral.getBook().get(i).showReserva();
                                                        System.out.print("Pressione [C] continuar >>> ");
                                                        wait = inn.nextLine();
                                                        break;
                                                }
                                            }
                                            System.out.println();
                                        }
                                    }
                                } 
                            }
                            if (buscandoNome == true){

                                System.out.print("\nRegistro não foi encontrado!\n" +
                                "Deseja tentar novamente? \n" +
                                "[1] SIM \n" +
                                "[2] NÃO \n" +
                                "> ");
                                int novamente = function.recebeInt(1, 2);
                                if (novamente == 2){
                                    buscandoNome = false;
                                }
                            }
                        }
                    } else if (altera == 2){

                        boolean buscandoNome = true;

                        while (buscandoNome == true){

                            String buscaNome = function.getStringMin("Nome", 3);
                            System.out.println();

                            for (int i = 0; i < cadastroGeral.getBook().size(); i++){

                                if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(0) == buscaNome.charAt(0)){
                                    if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(1) == buscaNome.charAt(1)){
                                        if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(2) == buscaNome.charAt(2)){
                                            
                                            buscandoNome = false;
                                            quarto = -1;

                                            cadastroGeral.getBook().get(i).showReserva();

                                            System.out.print("\nConfirma a reserva acima? \n"+
                                            "\n[1] SIM, alterar essa reserva \n"+
                                            "[2] NÃO, continuar a busca\n"+
                                            "\n> ");
                                            int confirma = function.recebeInt(1, 2);

                                            if (confirma == 1){

                                                buscandoNome = false;
                                                System.out.println("\nSelecione o NOVO QUARTO:\n");
                                                listaVagas.showMap();
                                                quarto = function.recebeIntVariavel("Quarto", 1, 12);
                                                cadastroGeral.getBook().get(i).alteraRoom(quarto-1);
                                                System.out.println("\n>>> " + listaVagas.reservarRoom(quarto-1) + " escolhido com sucesso!\n");
                                                cadastroGeral.getBook().get(i).showReserva();
                                                System.out.print("Pressione [C] continuar >>> ");
                                                wait = inn.nextLine();
                                                
                                            }
                                        }
                                    }
                                } 
                            }
                            if (buscandoNome == true){
                                System.out.print("Registro não foi encontrado!\n" +
                                "Deseja tentar novamente? \n" +
                                "[1] SIM \n" +
                                "[2] NÃO \n" +
                                "> ");
                                int novamente = function.recebeInt(1, 2);
                                if (novamente == 2){
                                    buscandoNome = false;
                                }
                            }
                        }

                    } else if (altera == 3){

                        boolean buscandoNome = true;

                        while (buscandoNome == true){

                            String buscaNome = function.getStringMin("Nome", 3);
                            System.out.println();

                            for (int i = 0; i < cadastroGeral.getBook().size(); i++){

                                if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(0) == buscaNome.charAt(0)){
                                    if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(1) == buscaNome.charAt(1)){
                                        if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(2) == buscaNome.charAt(2)){

                                            cadastroGeral.getBook().get(i).showReserva();

                                            System.out.print("\nConfirma a reserva acima? \n"+
                                            "\n[1] SIM, alterar essa reserva \n"+
                                            "[2] NÃO, continuar a busca\n"+
                                            "\n> ");
                                            int confirma =  function.recebeInt(1, 2);

                                            if (confirma == 1){
                                                buscandoNome = false;
                                                
                                                boolean datas = true;

                                                LocalDate novo_checkin = LocalDate.of(2000, 1, 1);
                                                LocalDate novo_checkOut = LocalDate.of(2000, 1, 1);
                        
                                                while (datas == true){
                                                    
                                                    boolean dataCerta = true;

                                                    while (dataCerta == true){

                                                        System.out.println("\n NOVO CheckIN \n");
                                                        
                                                        int dia = function.recebeIntVariavel("Dia", 1, 31);
                                                        int mes = function.recebeIntVariavel("Mês", 1, 12);
                                                        int ano = function.recebeIntVariavel("Ano", 2024, 2025);
                                    
                                                        novo_checkin = LocalDate.of(ano, mes, dia);
                                    
                                                        System.out.println("\n NOVO CheckOUT \n");
                                                       
                                                        int diaOut = function.recebeIntVariavel("Dia", 1, 31);
                                                        int mesOut = function.recebeIntVariavel("Mês", 1, 12);
                                                        int anoOut = function.recebeIntVariavel("Ano", 2024, 2025);
                                
                                                        novo_checkOut = LocalDate.of(anoOut, mesOut, diaOut);
                            
                                                        if (cadastroGeral.getBook().get(i).verifyDate(novo_checkin, novo_checkOut) == 1){
                                                            dataCerta = false;
                                                            break;
                            
                                                        } else if (cadastroGeral.getBook().get(i).verifyDate(novo_checkin, novo_checkOut) == 0) {
                                                            System.out.println("Operação INVÁLIDA!\nDATAS REPETIDAS!");
                            
                                                        } else if (cadastroGeral.getBook().get(i).verifyDate(novo_checkin, novo_checkOut) == -1){
                                                            System.out.println("Operação INVÁLIDA!\n" +
                                                                        "Data de CHECKout é INFERIOR a data de CHECKin!");
                                                        }
                                                    }
                                                    int novo_quarto = cadastroGeral.getBook().get(i).conversorRoom_Int(cadastroGeral.getBook().get(i).getRoom());
                                                    
                                                    if ((cadastroGeral.availability(novo_quarto, novo_checkin) == 1)){
                                                        cadastroGeral.getBook().get(i).alteraData(novo_checkin, novo_checkOut);
                                                        System.out.println("\nAlteração realizada com sucesso!\n");
                                                        cadastroGeral.getBook().get(i).showReserva();
                                                        System.out.print("\nPressione [C] continuar >>> ");
                                                        wait = inn.nextLine();
                                                        datas = false;
                                                        break;
                                                    
                                                    } else if (cadastroGeral.availability((novo_quarto), novo_checkin) == -1){
                                                        dataCerta = true;
                                                        System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                                                            "Já existe uma reserva para a DATA,\n" + 
                                                                            "e o QUARTO selecionados! Por favor, \n" +
                                                                            "consulte o cadastro de reservas!");
                                                        System.out.print("Pressione [C] continuar >>> ");
                                                        wait = inn.nextLine();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } 
                            }
                            if (buscandoNome == true){
                                System.out.print("Registro não foi encontrado!\n" +
                                "Deseja tentar novamente? \n" +
                                "[1] SIM \n" +
                                "[2] NÃO \n" +
                                "> ");
                                int novamente = function.recebeInt(1, 2);
                                if (novamente == 2){
                                    buscandoNome = false;
                                }
                            }  
                        }
                    }
                    break;

                case 4:
    // CANCELAR
                    boolean buscandoNome = true;

                    while (buscandoNome == true){
                        
                        String buscaNome = function.getStringMin("Nome", 3);
                        System.out.println();
                        
                        for (int i = 0; i < cadastroGeral.getBook().size(); i++){

                            if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(0) == buscaNome.charAt(0)){
                                if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(1) == buscaNome.charAt(1)){
                                    if (cadastroGeral.getBook().get(i).getInfo().getNome().charAt(2) == buscaNome.charAt(2)){

                                        cadastroGeral.getBook().get(i).showReserva();

                                        System.out.print("\nSelecione \n"+
                                        "\n[1] CONFIRMAR essa reserva \n"+
                                        "[2] CONTINUAR a busca\n"+
                                        "\n> ");
                                        int confirma = function.recebeInt(1, 2);

                                        if (confirma == 1){

                                            buscandoNome = false;
                                            int delete = 0;
                                            System.out.print("\nTem certeza que deseja CANCELAR essa reserva? \n" +
                                            "\n[1] SIM\n" +
                                            "[2] NÃO\n" +
                                            "\n> ");
                                            delete = function.recebeInt(1, 2);
                                            if (delete == 1){
                                                cadastroGeral.removeReservation(i);
                                            }

                                        }
                                    }
                                }
                            } 
                        }
                        if (buscandoNome == true){
                            System.out.print("Registro não foi encontrado!\n" +
                            "Deseja tentar novamente? \n" +
                            "[1] SIM \n" +
                            "[2] NÃO \n" +
                            "> ");
                            int novamente = function.recebeInt(1, 2);
                            if (novamente == 2){
                                buscandoNome = false;
                            }
                        }
                    }
                    break;

                case 5:
    // VERIFICAR CADASTRO GERAL
    
                    System.out.println("\n  ******************");
                    System.out.println("    CADASTRO GERAL  ");
                    System.out.println("  ******************\n");
                    cadastroGeral.showReservation();
                    break;
                    
                
                case 6:
    // SAIR
                    programa = false;
                    break;
            }
        }
        inn.close();
        System.out.println("\n\n**********************");
        System.out.println(" >>> VOLTE SEMPRE <<< ");
        System.out.println("**********************\n\n");


    }
}
