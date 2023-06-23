/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classveiculo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Date;
/**
 *
 * @author flavia
 */
@XStreamAlias("Veiculo")
public class Veiculos{
    String cliente;
    String marca;
    int  ano;
    double valor;
    Date data; // valor default é a data corrente de inserção   

    public Veiculos(String nomeCliente, String marcaModeloVeiculo, int anoModelo, double valorVenda,Date dataPublicacao) {
        this.cliente = nomeCliente;
        this.marca = marcaModeloVeiculo;
        this.ano = anoModelo;
        this.valor = valorVenda;
        this.data = dataPublicacao;
    }
    
    
    public String getCliente() {
        return cliente;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(Date data) {
       this.data = data;
    }
     
}

