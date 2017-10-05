package br.ce.edu.todolist.util;

import org.junit.Assert;
import org.junit.Test;

import br.ce.edu.todolist.bean.StatusTarefa;

public class ConverterTarefaStatusTest {
	
	@Test
	public void deveConverterStringParaEnum(){
		
		StatusTarefa a = ConverterTarefaStatus.converterEnum("A");
		StatusTarefa e = ConverterTarefaStatus.converterEnum("E");
		StatusTarefa c = ConverterTarefaStatus.converterEnum("C");
		
		Assert.assertEquals(StatusTarefa.A_FAZER, a);
		Assert.assertEquals(StatusTarefa.EM_ANDAMENTO, e);
		Assert.assertEquals(StatusTarefa.CONCLUIDO, c);
	}
	
	@Test
	public void naoDeveConverterVazioParaEnum(){
		
		StatusTarefa a = ConverterTarefaStatus.converterEnum("");
		StatusTarefa e = ConverterTarefaStatus.converterEnum("");
		StatusTarefa c = ConverterTarefaStatus.converterEnum("");
		
		Assert.assertNull(a);
		Assert.assertNull(e);
		Assert.assertNull(c);
	}
	
	@Test
	public void naoDeveConverterNullParaEnum(){
		
		StatusTarefa a = ConverterTarefaStatus.converterEnum(null);
		StatusTarefa e = ConverterTarefaStatus.converterEnum(null);
		StatusTarefa c = ConverterTarefaStatus.converterEnum(null);
		
		Assert.assertNull(a);
		Assert.assertNull(e);
		Assert.assertNull(c);
	}

}
