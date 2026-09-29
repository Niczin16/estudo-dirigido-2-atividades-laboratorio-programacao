/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

/**
 *
 * @author Pichau
 */
public class Pessoa {
    private Integer controle;
    private String nome;
    private Integer idade;
    private String cpf;
    private String cep;

    public Pessoa(Integer controle, String nome, Integer idade, String cpf, String cep) {
        this.controle = controle;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.cep = cep;
    }    
    
    public Integer getControle() {
        return controle;
    }

    public void setControle(Integer controle) {
        this.controle = controle;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public void dadosCadastrais(){
        System.out.printf(" controle: [%2d] nome: [%-2s] | idade: [%2d] | cpf: [%-2s] | cep [%-2s] /n",
                           controle, nome, idade, cpf, cep);
    }
    
    
}
