// luisantonio925/projetopi-tcc/ProjetoPi-TCC-1f367715d58cce63ad3bf1ce68416d2a7aad77b1/app/controllers/Favoritos.java
package controllers;

import models.Cliente;
import models.Restaurante;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;
import java.util.List;

@With(Seguranca.class)
public class Favoritos extends Controller {

    public static void index() {
        Cliente clienteConectado = Seguranca.getClienteConectado();
        
        if (clienteConectado == null) {
            // CORREÇÃO: Redireciona (forward) para a ação 'form' do controller 'Logins'.
            Logins.form(); // Chamada direta para um forward interno.
        }
        
        List<Restaurante> meusFavoritos = clienteConectado.restaurantes;

        List<Restaurante> outrosRestaurantes = Restaurante.find(
            "status = ?1 and ?2 not member of clientes", 
            Status.ATIVO, 
            clienteConectado
        ).fetch();

        render(meusFavoritos, outrosRestaurantes);
    }

    public static void alternarFavorito(Long idRest) {
        Cliente clienteConectado = Seguranca.getClienteConectado();
        
        if (clienteConectado == null) {
            // CORREÇÃO: Redireciona (forward) para a ação 'form' do controller 'Logins'.
            Logins.form(); // Chamada direta para um forward interno.
        }

        Restaurante restaurante = Restaurante.findById(idRest);

        if (restaurante != null) {
            if (clienteConectado.restaurantes.contains(restaurante)) {
                clienteConectado.restaurantes.remove(restaurante);
                flash.success("'%s' foi removido dos seus favoritos.", restaurante.nomeDoRestaurante);
            } else {
                clienteConectado.restaurantes.add(restaurante);
                flash.success("'%s' foi adicionado aos seus favoritos!", restaurante.nomeDoRestaurante);
            }
            clienteConectado.save();
        }
        index();
    }
}