package Programas.Reservation.Services;

import java.util.Scanner;

public class GeneralFunctions {
    
    private Scanner inn = new Scanner (System.in);

    public String getStringMin (String variavel, int limiteMin){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print("\n" + variavel + ": " );
            string = inn.nextLine();
            if (string.length() < limiteMin){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                "Digite no MÍNIMO " + limiteMin + " \n" + 
                                "caracteres referente ao cadastro!\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public String getStringMax (String variavel, int limiteMax){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print("\n" + variavel + ": " );
            string = inn.nextLine();
            if (string.length() > limiteMax){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                "Digite no MÁXIMO " + limiteMax + " \n" + 
                                "caracteres referente ao cadastro!\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public String getStringMaxMin (String variavel, int limiteMin, int limiteMax){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print("\n" + variavel + ": " );
            string = inn.nextLine();
            if (string.length() < limiteMin || string.length() > limiteMax){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + (limiteMin) + 
                                        "\ne o no MÁXIMO " + (limiteMax) +" !\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public boolean isNumeric(String str){
        if (str != null && str.matches("[0-9.]+")){
            return true;
        } else {
            return false;
        }
    }

    public Integer recebeIntVariavel (String variavel, int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print("\n" + variavel + ": " );
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + limiteMin + 
                                        "\ne o no MÁXIMO " + limiteMax +" !\n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                    variavel.toUpperCase() + " deve ser um NÚMERO INTEIRO!");
                teste = "";
            }
        }
        return numConv;

    }

    public Integer recebeInt (int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print("\n> ");
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        "Você deve digitar no MÍNIMO " + limiteMin + 
                                        "\ne o no MÁXIMO " + limiteMax +" !\n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                   "Você deve digitar um NÚMERO INTEIRO!");
                teste = "";
            }
        }
        return numConv;

    }

    public Integer recebeIntIndex (String variavel, int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print("\n" + variavel + ": " );
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + limiteMin + 
                                        "\ne o no MÁXIMO " + limiteMax + " !\n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                    variavel.toUpperCase() + " deve ser um NÚMERO INTEIRO!");
                teste = "";
            }
        }
        return numConv;

    }

}
