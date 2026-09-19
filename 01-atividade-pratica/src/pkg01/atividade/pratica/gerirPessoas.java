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
 
    private ArrayList<dadosPessoa> pessoas = new ArrayList<>();
    
    public String incluirPessoa(dadosPessoa pessoa){
        pessoas.add(pessoa);        
        return "Pessoa cadastrada com sucesso!";
    }
    
    public String listarPessoas(){
        if (pessoas.isEmpty()){
            return "Nao existe nenhuma pessoa na lista";
        }
        
        String lPessoa = "";               
        
        for(dadosPessoa p: pessoas){
            lPessoa += p.dadosCadastrais() + "\n";            
        }
        
        return lPessoa;
        
    }
    
}
