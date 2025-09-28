package controllers;

import models.Cliente;
import play.libs.Crypto;
import play.mvc.Controller;

public class Logins extends Controller{

	
	public static void form() {
		render();
	}
	
	public static void logar(String email, String senha) {
     	Cliente cliente = Cliente.find("email = ?1 and senha = ?2",
              	email, Crypto.passwordHash(senha)).first();
     	if (cliente == null) {
          	flash.error("email ou senha inválidos");
          	form(); //Redireciona para form de login
     	} else {
          	session.put("usuarioLogado", cliente.email);
          	flash.success("Logado com sucesso!");
          	Gerenciamentos.principal(); //Página inicial
     	}
 	}
	
	public static void logout() {
		session.clear();
		flash.success("Você saiu do sistema!");
		form();
	}

}
