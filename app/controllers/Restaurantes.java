package controllers;
import models.Status;

import java.util.List;

import models.Cliente;
import models.Restaurante;
import play.mvc.Controller;
import play.mvc.With;

//@With(Seguranca.class)
public class Restaurantes extends Controller{
	
	public static void formCadastrarRestaurante() {
		render();
	}
	public static void parceiroRestApp() {
		render();
	}
	public static void principal() {
		render();
	}
	public static void listar2(String busca) {
		List<Restaurante> listaRest = null;
		if(busca == null) {
			listaRest = Restaurante.find("status <> ?1", Status.INATIVO).fetch();
		}else {
			listaRest = Restaurante.find("(lower(nomeDoRestaurante) like ?1"
								+ "or lower(CNPJ) like ?1 or lower(categoria) like ?1 and status <> ?2",
								"%" + busca.toLowerCase() + "%",
					 				Status.INATIVO).fetch();
		}
		
		
		render(listaRest, busca);
		
	}
	
	 
	public static void salvar(Restaurante rest, Long idCliente) {
		
		if(idCliente != null) {
		Cliente c = Cliente.findById(idCliente);
		
		if(rest.clientes.contains(c)== false)
			rest.clientes.add(c);
			
		}
		
		
		rest.save();
		editar(rest.id);
		
	}
	public static void editar(long id) {
		Restaurante  R =Restaurante.findById(id);
     List<Cliente> clientes = Cliente.findAll();
		renderTemplate("Restaurantes/formCadastrarRestaurante.html", R, clientes);
		
	}
	public static void remover(long id) {
		Restaurante rest = Restaurante.findById(id);
		rest.status = Status.INATIVO;
		rest.save();
		
		listar2(null);
	}
	public static void removerCliente(Long idRest, Long idCli) {
		Restaurante rest = Restaurante.findById(idRest);
		Cliente cli = Cliente.findById(idCli);
		
		rest.clientes.remove(cli);
		
		rest.save();
		editar(rest.id);
		
		
		
		
	}

}
