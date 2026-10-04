/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

/**
 *
 * @author Pichau
 */
public class Membro extends Pessoa{
    private String preferencia;

    public Membro(String preferencia, Integer controle, String nome, Integer idade, String cpf, String cep) {
        super(controle, nome, idade, cpf, cep);
        this.preferencia = preferencia;
    }    
    
    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    @Override
    public void dadosCadastrais() {
        System.out.printf(" controle: [%2d] | nome: [%-2s] | idade: [%2d] | cpf: [%-2s] | cep [%-2s] | preferencia: [%-2s] /n",
                           getControle(), getNome(), getIdade(), getCpf(), getCep(), getPreferencia());
    }     
    
}
