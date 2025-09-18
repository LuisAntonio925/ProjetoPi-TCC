package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import play.db.jpa.Model;

@Entity

public class Restaurante extends Model {
	
	public String nomeDoRestaurante;
	public String CNPJ;
	public String categoria;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Restaurante() {
		this.status = Status.ATIVO;
	}
	
	
	@ManyToMany
	@JoinTable(name="cliente_restaurante")
	public List<Cliente> clientes;
	

}
