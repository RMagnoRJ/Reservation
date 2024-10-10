package Classes.Reservation.Model_Exception;

public class GeneralException extends Exception {
    
    /* 
     * Via de regra, utiliza-se o nome para esta classe de exceções como DOMAIN Exception,
     * isto porque, ela será uma classe de EXCEÇÃO lançada por algo erro da classe de
     * DOMÍNIO que é a RESERVATION. Porém, chamei de GENERAL exception por conveniência
     * própria.
     * 
     * Esta classe - GENERAL Exception - pode ser uma EXTENSÃO de Exception ou de RuntimeException;
     * o RUNTIME é um tipo de exceção que o compilador não obriga o programador a tratar. Já, 
     * se optarmos por ser uma extensão de EXCEPTION, o compilador irá obrigar o programar a
     * tratar o erro e defini-lo como deve ser.
     * 
     * O construtor da classe GENERAL foi declarado abaixo utilizando como argumento uma STRING e,
     * em seguida, repassa essa mensagem para o CONSTRUTOR da classe SUPER Exception. Isso se deu 
     * com o objetivo de permitir que se possa INSTANCIAR a Exceção PERSONALIZADA passando uma 
     * mensagem para ela. A mensagem ficará ARMAZENADA dentro da EXCEÇÃO.
     * 
     * 
     * 
     * 
     */

    public GeneralException(String mensagem){
        super(mensagem);
    }

}
