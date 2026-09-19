/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg01.atividade.pratica;

/**
 *
 * @author Pichau
 */
public class dadosPessoa {
    private String CPF;
    private String Nome;
    private String Endereco;
    private String Estado;
    private String Cargo;

    public dadosPessoa(String CPF, String Nome, String Endereco, String Estado, String Cargo) {
        this.CPF = CPF;
        this.Nome = Nome;
        this.Endereco = Endereco;
        this.Estado = Estado;
        this.Cargo = Cargo;
    }    
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String dadosCadastrais(){
        return "Nome: " + Nome + " | CPF: " + CPF + " | Endereco: " + Endereco +
                " | Estado: " + Estado + " | Cargo: " + Cargo;
    }
        
}
