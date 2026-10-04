/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Pichau
 */
public class GestaoPessoas {
    private ArrayList<Pessoa> pessoas = new ArrayList<>();
    
    // <editor-fold defaultstate="collapsed" desc="cadastrarPessoa">
    public void cadastrarPessoa(Pessoa pessoa){
        pessoas.add(pessoa);
        System.out.println( "\n" + pessoa + " adicionado com sucesso!");        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="editarCadastroMembro">
        public void editarCadastroMembro(
                Pessoa pessoa     , 
                Integer Controle  , 
                String nome       , 
                Integer idade     , 
                String cpf        ,  
                String cep        ,
                String preferencia
        ){
        if (pessoas.isEmpty()) {
            System.out.println("Não há nenhum membro cadastrado.");
            return; 
        }
        
        for(Pessoa lPessoa: pessoas){
            if(lPessoa.getControle().equals(Controle)){                
                
                lPessoa.setNome(nome);
                lPessoa.setIdade(idade);
                lPessoa.setCpf(cpf);
                lPessoa.setCep(cep);
                ((Membro) lPessoa).setPreferencia(preferencia);                          
                
                System.out.println("Membro editado com sucesso!");
            }            
        }
    }
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="editarCadastroFuncionario">
        public void editarCadastroFuncionario(
                Pessoa pessoa     , 
                Integer Controle  , 
                String nome       , 
                Integer idade     , 
                String cpf        ,  
                String cep        ,
                String numeroRegis
        ){
        if (pessoas.isEmpty()) {
            System.out.println("Não há nenhum funcionario cadastrado.");
            return; 
        }
        
        for(Pessoa lPessoa: pessoas){
            if(lPessoa.getControle().equals(Controle)){                
                
                lPessoa.setNome(nome);
                lPessoa.setIdade(idade);
                lPessoa.setCpf(cpf);
                lPessoa.setCep(cep);
                ((Funcionario) lPessoa).setNumeroRegistro(numeroRegis);                          
                
                System.out.println("Funcionario editado com sucesso!");
            }            
        }
    }
    // </editor-fold>    
        
    // <editor-fold defaultstate="collapsed" desc="listarPessoas">
    public void listarPessoas(){
        if (pessoas.isEmpty()) {
            System.out.println("Nenhum pessoa cadastrado na lista de pessoas.");
            return; 
        }
        
        for (Pessoa lPessoa : pessoas) {
            lPessoa.dadosCadastrais();
        }    
    }
    // </editor-fold>    
        
    // <editor-fold defaultstate="collapsed" desc="buscarPessoa">
    public Pessoa buscarPessoa(Integer Controle){
        for (Pessoa lPessoa : pessoas){
            if (lPessoa.getControle().equals(Controle)){
                return lPessoa;
            }
        }
        return null;
    }
    // </editor-fold>    
    
    
}
