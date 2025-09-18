package controllers;

import models.Cliente;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void logar(String login, String senha) {
     	Cliente cliente = Cliente.find("login = ?1 and senha = ?2",
              	login, senha).first();
     	if (cliente == null) {
          	flash.error("Login ou senha inválidos");
          	form(); //Redireciona para form de login
     	} else {
          	session.put("usuarioLogado", cliente.email);
          	flash.success("Logado com sucesso!");
          	Restaurantes.listar2(null);; //Página inicial
     	}
 	}
	
	public static void logout() {
		session.clear();
		flash.success("Você saiu do sistema!");
		form();
	}

}
