package controllers;

import java.util.List;

import models.Cliente;
import models.Restaurante;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;

//@With(Seguranca.class)
public class Gerenciamentos extends Controller {
	
	public static void principal() {
		render();
	}
	public static void formCadastro() {
		render();
	}

	public static void listar(String termo) {
		List<Cliente> listaClientes = null;
		if (termo == null) {
			listaClientes = Cliente.find("status <> ?1", Status.INATIVO).fetch();	
		} else {
			listaClientes = Cliente.find("(lower(nome) like ?1 "
					+ "or lower(email) like ?1) and status <> ?2",
					"%" + termo.toLowerCase() + "%",
					Status.INATIVO).fetch();
		}
		render(listaClientes, termo);
	}
		//List<Cliente> listaClientes = Cliente.findAll();
		//render(listaClientes);
	
	
	
	public static void salvar(Cliente l) {
		l.save();
		principal();
		
		
	}
	
	public static void remover(long id) {
		Cliente cli = Cliente.findById(id);
		cli.status = Status.INATIVO;
		cli.save();
		listar(null);
		

		
	}
	public static void editar(long id) {
		Cliente cli =Cliente.findById(id);
		renderTemplate("Gerenciamentos/formCadastro.html", cli);
		
	}
	

}
