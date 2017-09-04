package br.ce.edu.todolist.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.dao.UsuarioDao;
import br.ce.edu.todolist.service.CadastrarTarefaService;
import br.ce.edu.todolist.util.ConverterTarefaStatus;
import br.ce.edu.todolist.util.PersistenceException;

public class CadastrarTarefa implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String proximaPage = "cadastrar-tarefa.jsp";
		
		UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("usuario");
		
		TarefaBean tarefa = new TarefaBean();
		tarefa.setId(Integer.parseInt(request.getParameter("id")));
		tarefa.setTitulo(request.getParameter("titulo"));
		tarefa.setDescricao(request.getParameter("descricao"));
		StatusTarefa status = ConverterTarefaStatus.converterEnum(request.getParameter("status"));
		tarefa.setStatus(status);
		tarefa.setUsuarioId(Integer.parseInt(request.getParameter("usuarioId")));
		
		TarefaDAO tarefaDAO = new TarefaDAO();
		UsuarioDao usuarioDAO = new UsuarioDao();
		CadastrarTarefaService service = new CadastrarTarefaService();
		
		if(service.verificaCampos(tarefa)){
			try {
				if(tarefa.getId() > 0){
					tarefaDAO.editarTarefa(tarefa);
					request.setAttribute("msgAviso", "Tarefa editada com sucesso.");

				}else{
					tarefaDAO.addTarefa(tarefa);					
					request.setAttribute("msgAviso", "Tarefa cadastrada com sucesso.");
				}
				
				List<TarefaBean> tarefas = tarefaDAO.listarTarefaPorUsuario(usuario.getId());
				request.setAttribute("tarefas", tarefas);
			} catch (PersistenceException e) {
				request.setAttribute("msgErro", "Erro ao gravar tarefa no banco de dados.");
			}
			proximaPage = "index.jsp";		
		}else{
			request.setAttribute("msgErro", "Dados invalidos, favor preencher todos os campos.");
			
		}
		return proximaPage;
	}

}
