package br.ce.edu.todolist.service;

import org.junit.Assert;
import org.junit.Test;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;

public class CadastrarTarefaServiceTest {
	
	@Test
	public void naoDeveValidarCamposNulos(){
		
		TarefaBean tarefa = new TarefaBean();
		CadastrarTarefaService service = new CadastrarTarefaService();
		boolean vericado = service.validarCampos(tarefa);
		
		Assert.assertFalse(vericado);
	}
	
	@Test
	public void naoDeveValidarCamposEmBranco(){
		
		TarefaBean tarefa = new TarefaBean();
		tarefa.setDescricao("");
		tarefa.setTitulo("");
		tarefa.setUsuarioId(0);
		CadastrarTarefaService service = new CadastrarTarefaService();
		boolean vericado = service.validarCampos(tarefa);
		
		Assert.assertFalse(vericado);
	}
	
	@Test
	public void deveValidarCamposPreenchidos(){
		TarefaBean tarefa = new TarefaBean();
		tarefa.setTitulo("Teste Junit");
		tarefa.setDescricao("Fazendo teste com junit");
		tarefa.setStatus(StatusTarefa.A_FAZER);
		tarefa.setUsuarioId(0);		
		CadastrarTarefaService service = new CadastrarTarefaService();
		boolean vericado = service.validarCampos(tarefa);
		
		Assert.assertTrue(vericado);
	}
	
	

}
