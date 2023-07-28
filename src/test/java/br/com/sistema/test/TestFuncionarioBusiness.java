package br.com.sistema.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.soc.sistema.business.funcionario.FuncionarioBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class TestFuncionarioBusiness {
	
	private FuncionarioBusiness business;
	private FuncionarioVo funcionario;
	final String CODIGO = "1";
	final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	final String NAO_FOI_POSSIVEL_LOCALIZAR_O_FUNCIONARIO = "Nao foi possivel localizar o funcionario";
	
	@BeforeEach
	void before() {
		this.business = new FuncionarioBusiness();
		this.funcionario = new FuncionarioVo();
	}

	@Test
	void deveriaBuscarFuncionarioPorCodigo() {
		business.buscarFuncionarioPor(CODIGO);
		
		assertNotNull(business);
	}
	
	@Test
	void deveriaLancarBusinessExceptionQuandoInformadoCaractere() {
		final String CODIGO_CARACTERE = "a";
		
		BusinessException be = assertThrows(BusinessException.class, () -> business.buscarFuncionarioPor(CODIGO_CARACTERE));
		
		assertEquals(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO, be.getMessage());
	}
	
	@Test
	void deveriaLancarBusinessExceptionQuandoNaoExistirFuncionarioComEsseCodigo() {
		
		// Obtém sempre o último código da tabela + 1
		String CODIGO_INVALIDO = String.valueOf(business.trazerTodosOsFuncionarios().size()+1);
		
		BusinessException be = assertThrows(BusinessException.class, () -> business.buscarFuncionarioPor(CODIGO_INVALIDO));
		
		assertEquals(NAO_FOI_POSSIVEL_LOCALIZAR_O_FUNCIONARIO, be.getMessage());
	}
	
	@Test
	void deveriaIncluirFuncionarioComExito() {
		funcionario.setNome("Funcionario teste");
		
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setOpcoesCombo("2");
		filter.setValorBusca(funcionario.getNome());

		// Valida se funcionario foi incluído sem Exception
		assertDoesNotThrow(() -> business.inserirFuncionario(funcionario));
		
		// Obtém lista de funcionarios no banco com base no nome do funcionario incluído
		List<FuncionarioVo> lista = new ArrayList<>();
		lista.addAll(business.filtrarFuncionario(filter));
		
		// Verifica se há na lista o funcionário que foi incluído previamente
		boolean condicao = false;
		for (int i = 0; i < lista.size(); i++) {
			if (funcionario.getNome().equals(lista.get(i).getNome())){
				condicao = true;
				break;
			}
		}
		
		assertTrue("Funcionário não foi encontrado", condicao);
	}
	
	@Test
	void deveriaLancarExceptionAoIncluirFuncionarioComNomeVazio() {
		funcionario.setNome("");
		
		BusinessException be = assertThrows(BusinessException.class, 
				() -> business.inserirFuncionario(funcionario));
		
		assertEquals("Nome nao pode ser em branco",
				be.getMessage());
	}
	
	@Test
	void deveriaEditarFuncionario() {
		funcionario.setRowid("1");
		funcionario.setNome("Alterado");
		
		//assertDoesNotThrow(() -> business.editarFuncionario(funcionario));
		business.editarFuncionario(funcionario);
		
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setOpcoesCombo("2");
		filter.setValorBusca(funcionario.getNome());
		
		// Obtém lista de funcionarios no banco com base no nome do funcionario incluído
		List<FuncionarioVo> lista = new ArrayList<>();
		lista.addAll(business.filtrarFuncionario(filter));
		
		// Verifica se há na lista o funcionário que foi incluído previamente
		boolean condicao = false;
		for (int i = 0; i < lista.size(); i++) {
			if (funcionario.getNome().equals(lista.get(i).getNome())){
				condicao = true;
				break;
			}
		}
		
		assertTrue("Funcionário não foi encontrado", condicao);
		
	}
	
	@Test
	void deveriaLancarExceptionAoEditarFuncionarioComNomeVazio() {
		funcionario.setRowid("1");
		funcionario.setNome("");
		
		BusinessException be = assertThrows(BusinessException.class, 
				() -> business.editarFuncionario(funcionario));
		
		assertEquals("Nome nao pode ser em branco",
				be.getMessage());
	}
	
	@Test
	void deveriaLancarExceptionAoEditarFuncionarioDeCodigoInvalido(){
		final String CODIGO_INVALIDO = String.valueOf(business.trazerTodosOsFuncionarios().size()+1);
		final String CODIGO_DO_FUNCIONARIO_NAO_ENCONTRADO = "Codigo do funcionario nao encontrado";
		
		funcionario.setRowid(CODIGO_INVALIDO);
		funcionario.setNome("deveriaLancarExceptionAoEditarFuncionarioDeCodigoInvalido");
		
		BusinessException be = assertThrows(BusinessException.class, 
				() -> business.editarFuncionario(funcionario));
		
		assertEquals(CODIGO_DO_FUNCIONARIO_NAO_ENCONTRADO,
				be.getMessage());
	}
	
	@Test
	void deveriaDeletarFuncionarioComBaseNoCodigoInformado() {
		funcionario.setNome("deveriaDeletarFuncionarioComBaseNoCodigoInformado");
		
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setOpcoesCombo("2");
		filter.setValorBusca(funcionario.getNome());

		// Valida se funcionario foi incluído sem Exception
		assertDoesNotThrow(() -> business.inserirFuncionario(funcionario));
		
		// Obtém lista de funcionarios no banco com base no nome do funcionario incluído
		List<FuncionarioVo> lista = new ArrayList<>();
		lista.addAll(business.filtrarFuncionario(filter));
		
		// Obtém id do funcionário incluído
		Integer codigo_delecao = null;
		for (int i = 0; i < lista.size(); i++) {
			if (funcionario.getNome().equals(lista.get(i).getNome())){
				codigo_delecao = Integer.parseInt(lista.get(i).getRowid());
				break;
			}
		}
		
		business.deletarFuncionario(codigo_delecao);
		assertDoesNotThrow(()-> Exception.class);
	}
	
	@Test
	void deveriaLancarExceptionAoExcluirFuncionarioComCodigoInvalido() {
		Integer CODIGO_INVALIDO = business.trazerTodosOsFuncionarios().size()+1;
		String expected = "Codigo do funcionario nao encontrado";
		
		BusinessException be = assertThrows(BusinessException.class, () -> business.deletarFuncionario(CODIGO_INVALIDO));
		
		assertEquals(expected, be.getMessage());
	}
}