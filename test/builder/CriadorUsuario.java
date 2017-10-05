package builder;

import br.ce.edu.todolist.bean.UsuarioBean;

public class CriadorUsuario {
	
	private UsuarioBean usuario;
	
	public CriadorUsuario init(String nome){
		usuario = new UsuarioBean();
		usuario.setNome(nome);
		usuario.setEmail("teste@teste.com");
		usuario.setSenha("1234");
		return this;
	}
	
	public CriadorUsuario id(Integer id){
		usuario.setId(id);
		return this;
	}
	
	public CriadorUsuario camposNull(){
		usuario = new UsuarioBean();
		return this;
	}
	
	public UsuarioBean retornar() {
		return usuario;
	}

}
