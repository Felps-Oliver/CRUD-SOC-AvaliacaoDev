package br.com.sistema.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.soc.sistema.business.exame.ExameBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class TestExameBusiness {

	private final String CODIGO = "1";
	private ExameBusiness business;
	private ExameVo exame;

	@BeforeEach
	void before() {
		this.business = new ExameBusiness();
		this.exame = new ExameVo();
	}

	@Test
	void deveriaBuscarExamePorCodigo() {
		exame = business.buscarExamePor(CODIGO);
		assertNotNull(exame);
	}

	@Test
	void deveriaLancarExceptionAoBuscarExamePorCodigoInvalido() {
		final String CODIGO_INVALIDO = "teste";
		final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
		
		BusinessException be = assertThrows(BusinessException.class, () -> business.buscarExamePor(CODIGO_INVALIDO));
		
		assertEquals(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO,
				be.getMessage());
	}

	@Test
	void deveriaInserirExameComExito() {
		exame.setNome("Exame exito");
		
		ExameFilter filter = new ExameFilter();
		filter.setOpcoesCombo("2");
		filter.setValorBusca(exame.getNome());

		// Valida se exame foi incluído sem Exception
		assertDoesNotThrow(() -> business.inserirExame(exame));

		// Obtém lista de exames no banco com base no nome do exame incluído
		List<ExameVo> lista = new ArrayList<>();
		lista.addAll(business.filtrarExames(filter));

		// Verifica se há na lista o exame que foi incluído previamente
		lista.forEach(exameLista -> {
			if (exame.getNome().equals(exameLista.getNome())) {
				assertEquals(exame.getNome(), exameLista.getNome());
			}
		});
	}

	@Test
	void deveriaLancarExceptionAoTentarIncluirExameSemNome() {
		final String EXPECTED = "Nao foi possivel realizar a inclusao do registro";
		exame.setNome("");
		BusinessException be = assertThrows(BusinessException.class, () -> business.inserirExame(exame));
		
		assertEquals(EXPECTED, be.getMessage());
	}

	@Test
	void deveriaEditarExame() {
		exame.setRowid("1");
		exame.setNome("Teste");

		business.editarExame(exame);

		assertEquals("Teste", business.buscarExamePor(exame.getRowid()).getNome());
	}

	@Test
	void naoDeveriaEditarExameCasoNomeEstejaVazio() {
		final String EXPECTED = "Nao foi possivel realizar a edicao do registro";
		exame.setRowid("1");
		exame.setNome("");
		
		BusinessException be = assertThrows(BusinessException.class, () -> business.editarExame(exame));
		
		assertEquals(EXPECTED, be.getMessage());
	}

	@Test
	void naoDeveriaEditarExameCasoCodigoNaoExista() {
		final String EXPECTED = "Nao foi possivel realizar a edicao do registro";
		exame.setRowid("9");
		exame.setNome("Teste");
		
		BusinessException be = assertThrows(BusinessException.class, () -> business.editarExame(exame));
		
		assertEquals(EXPECTED, be.getMessage());
	}
	
	@Test
	void deveriaDeletarExame() {
		exame.setNome("Exame teste");
		business.inserirExame(exame);
		
		ExameFilter filter = new ExameFilter();
		filter.setOpcoesCombo("2");
		filter.setValorBusca(exame.getNome());
		
		// Obtém lista de exames no banco com base no nome do exame incluído
		List<ExameVo> lista = new ArrayList<>();
		lista = business.filtrarExames(filter);
		int idExameInserido = Integer.parseInt(lista.get(0).getRowid()); //Índice do exame inserido no teste
		
		assertDoesNotThrow(() -> business.deletarExame(idExameInserido));
	}
	
	@Test
	void deveriaLancarExceptionAoTentarDeletarExameJaRealizadoPorUmFuncionario() {
		final String EXPECTED = "Impossível excluir exame já realizado por um funcionário";

		BusinessException be = assertThrows(BusinessException.class, 
				() -> business.deletarExame(Integer.parseInt(CODIGO)));
		
		assertEquals(EXPECTED, be.getMessage());
	}
	
	@Test
	void deveriaLancarExceptionAoTentarDeletarExameDeRowidInvalido() {
		final String CODIGO_INVALIDO = "99"; 
		final String EXPECTED = "Impossível excluir exame";
					
		BusinessException be = assertThrows(BusinessException.class, 
				() -> business.deletarExame(Integer.parseInt(CODIGO_INVALIDO))
			);
		
		assertEquals(EXPECTED, be.getMessage());
		
	}

}
