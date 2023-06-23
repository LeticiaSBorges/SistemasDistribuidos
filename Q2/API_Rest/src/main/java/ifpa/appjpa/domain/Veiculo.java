package ifpa.appjpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.Nullable;

@Entity
public class Veiculo implements Serializable {
	private static final long serialVersionUID = 268705783697101576L;
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE)
	private Long id;
	private String nomeCliente;
        private String marcaModeloVeiculo;

	private int anoModelo;
        private double valorVenda;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;


    public Veiculo() {
	}
    // gets e set´s
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getnomeCliente() {
		return nomeCliente;
	}

	public void setnomeCliente(String nome) {
		this.nomeCliente = nome;
	}

	public String getmarcaModeloVeiculo() {
		return marcaModeloVeiculo;
	}

	public void setmarcaModeloVeiculo(String nome) {
		this.marcaModeloVeiculo = nome;
	}
        
        public int getanoModelo() {
		return anoModelo;
	}

	public void setanoModelo(int ano) {
		this.anoModelo = ano;
	}
        
        public double getvalorVenda() {
		return valorVenda;
	}

	public void setvalorVenda(double valor) {
		this.valorVenda = valor;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	
 
}