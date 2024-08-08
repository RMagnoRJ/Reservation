package ProgramaçãoOrientadaObjeto.Classes.Reservation.Model_Entities;

public class ReservationInfo {
    
    private String nome;

    private String cpf;

    private String ddd;

    private String telefone;

    public ReservationInfo() {
    }

    public ReservationInfo(String nome, String cpf, String ddd, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.ddd = ddd;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDdd() {
        return ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void showInfo(){
        System.out.println("  Nome: " + getNome().toUpperCase());
        System.out.println("  CPF: " + getCpf());
        System.out.println("  Telefone: (" + getDdd() + ") " + getTelefone());
    }

    public void altera_Info(int altera, String alteraDado){
        
        if (altera == 1){
            this.nome = alteraDado;
        } else if (altera == 2){
            this.cpf = alteraDado;
        } else if (altera == 3){
            this.telefone = alteraDado;
        }
        
    }

    
}
