/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg01.atividade.pratica;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Pichau
 */
public class gerirPessoas {
 
    private ArrayList<Fornecedor> fornecedors = new ArrayList<>();
    private ArrayList<Cliente> clientes       = new ArrayList<>();
    
    //{ FORNECEDORES }
    public String incluirFornecedor(Fornecedor AFornecedor){
        fornecedors.add(AFornecedor);        
        return "Fornecedor cadastrada com sucesso!";
    }
    
    //{ CLIENTE }
    public String incluirCliente(Cliente ACliente){
        clientes.add(ACliente);        
        return "Fornecedor cadastrada com sucesso!";
    }
    
    //{ FORNECEDORES }
    public String listarFornecedores(){
        if (fornecedors.isEmpty()){
            return "Nao existe nenhum fornecedor na lista";
        }
        
        String lFornecedor = "";               
        
        for(Fornecedor p: fornecedors){
            lFornecedor += p.dadosCadastrais() + "\n";            
        }
        
        return lFornecedor;
        
    }
    
    //{ CLIENTE }
    public String listarCliente(){
        if (clientes.isEmpty()){
            return "Nao existe nenhuma cliente na lista";
        }
        
        String lCliente = "";               
        
        for(Cliente p: clientes){
            lCliente += p.dadosCadastrais() + "\n";            
        }
        
        return lCliente;
        
    }
    
}
