package com.gsantos;

public class Veiculo{
    private final double consumoPorLitro = 10;
    private Placa placa;
    private double combustivel;
    private String Marca;
    private String Cor;
    private String Categoria;
    
    //Um comentario
    //Comentario veiculo
    public Veiculo(Placa placa, String Marca, String Cor, String Categoria){
        this.placa = placa;
        combustivel = 0;
	this.Cor = Cor;
	this.Marca = Marca;
	this.Categoria = Categoria;
    }

    public void setPais(String pais){
        placa.setPais(pais);
    }

    public Placa getPlaca(){
        return placa;
    }

    public String getCor(){
        return Cor;
    }

    public String getMarca(){
        return Marca;
    }

    public String getCategoria(){
        return Categoria;
    }

    public double getCombustivelNoTanque(){
        return combustivel;
    }

    public double abastece(double litros){
        if (litros > 0.0){
            combustivel += litros;
        }
        return combustivel;
    }

    public double dirige(double distancia){
        // Calcula o consumo para a distancia
        double consumo = distancia / consumoPorLitro;
        // Diminui o combustivel gasto e retorna a distancia percorrida
        if (combustivel >= consumo){
            combustivel -= consumo;
            return distancia;
        }else{
            double distPossivel = combustivel * consumoPorLitro;
            combustivel = 0.0;
            return distPossivel;
        }
    }

    public String toString(){
        return "Placa: "+getPlaca()+", combustivel no tanque: "+getCombustivelNoTanque();
    }
}