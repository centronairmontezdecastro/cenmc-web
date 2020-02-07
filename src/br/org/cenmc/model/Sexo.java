package br.org.cenmc.model;

public enum Sexo {
	M("M"),
	F("F");
	
	  private String sexo;

	   public String getSexo() {

	       return this.sexo;

	   }

	   Sexo(String sexo) {

	           this.sexo = sexo;

	   }
}
