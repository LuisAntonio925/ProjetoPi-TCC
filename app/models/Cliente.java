package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity

public class Cliente extends Model {
	
	public String nome;
	public String email;
	public String telefone;
	
	public String senha;
	public String login;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Cliente() {
		this.status = Status.ATIVO;
	}
	
	@ManyToMany(mappedBy="clientes")
	public List<Restaurante> restaurantes;
	

}
