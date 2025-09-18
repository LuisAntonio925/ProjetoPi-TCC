package jobs;

import models.Cliente;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {

			
			Cliente joao = new Cliente();
			joao.nome = "João da Silva";
			joao.email = "joaossilva@gmail.com";
			joao.login = "joao";
			joao.senha = "1111";
			joao.save();
			
			
		}
			
		

}
