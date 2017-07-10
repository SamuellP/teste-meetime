package models;

import java.util.Calendar;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Validate;
import play.data.validation.Constraints.Validatable;
import play.data.validation.ValidationError;

@Validate
public class Car implements Validatable<ValidationError> {

	public int id;
    public String responsavel;
    public String modelo;
    public String ano;
    public String cor;
    
    @Override
    public ValidationError validate() {
    	if(responsavel == null || responsavel.isEmpty()){
        	return new ValidationError("responsavel", "INFORME UM RESPONSAVEL");
    	}

    	if(modelo == null || modelo.isEmpty()){
        	return new ValidationError("modelo", "INFORME UM MODELO");
    	}

    	if(ano == null || ano.isEmpty()){
        	return new ValidationError("ano", "IRNFORME UM ANO");
    	}

    	if(cor == null || cor.isEmpty()){
        	return new ValidationError("cor", "INFORME UMA COR");
    	}

    	if(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(ano) > 30){
    		return new ValidationError("ano", "O CARRO NÃO PODE TER MAIS DE 30 ANOS");
    	}
    	
    	if(!cor.toUpperCase().equals("BRANCO") && !cor.toUpperCase().equals("PRETO") && !cor.toUpperCase().equals("VERDE")){
    		return new ValidationError("cor", "SOMENTE SÃO PERMITIDOS CARROS DE COR BRANCO, PRETO OU VERDE");
    	}


        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    
}
