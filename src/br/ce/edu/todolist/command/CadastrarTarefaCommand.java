package br.ce.edu.todolist.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.service.CadastrarTarefaService;
import br.ce.edu.todolist.util.ConverterTarefaStatus;
import br.ce.edu.todolist.util.PersistenceException;

public class CadastrarTarefaCommand implements Command {
	
	private TarefaDAO tarefaDAO;
	private CadastrarTarefaService service;
	private TarefaBean tarefa;
	
	public CadastrarTarefaCommand() {
		tarefaDAO = new TarefaDAO();
		service = new CadastrarTarefaService();
		tarefa = new TarefaBean();
	}
	
	public CadastrarTarefaCommand(TarefaDAO tarefaDAO){
		this.tarefaDAO = tarefaDAO;
		service = new CadastrarTarefaService();
		tarefa = new TarefaBean();
	}

	@Override
	public String execute(HttpServletRequest request) {
		String proximaPage = "cadastrar-tarefa.jsp";
		
		UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("usuario");
		StatusTarefa status = ConverterTarefaStatus.converterEnum(request.getParameter("status"));
		
		tarefa.setId(Integer.parseInt(request.getParameter("id")));
		tarefa.setTitulo(request.getParameter("titulo"));
		tarefa.setDescricao(request.getParameter("descricao"));
		tarefa.setStatus(status);
		tarefa.setUsuarioId(Integer.parseInt(request.getParameter("usuarioId")));

		if(service.validarCampos(tarefa)){
			try {
				if(tarefa.getId() > 0){
					tarefaDAO.editarTarefa(tarefa);
					request.setAttribute("msgSucesso", "Tarefa editada com sucesso.");

				}else{
					tarefaDAO.addTarefa(tarefa);					
					request.setAttribute("msgSucesso", "Tarefa cadastrada com sucesso.");
				}
				
				List<TarefaBean> tarefas = tarefaDAO.listarTarefaPorUsuario(usuario.getId());
				request.setAttribute("tarefas", tarefas);
				request.setAttribute("aba", "home");
			} catch (PersistenceException | NullPointerException e) {
				request.setAttribute("msgErro", "Erro ao gravar tarefa no banco de dados.");
			}
			proximaPage = "index.jsp";		
		}else{
			request.setAttribute("msgAviso", "Dados invalidos, favor preencher todos os campos.");
		}
		
		return proximaPage;
	}

}
