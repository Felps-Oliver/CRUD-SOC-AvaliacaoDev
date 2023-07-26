package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarFichas {
	ID_FICHA("1", "Nº FICHA"),
	NM_EXAME("2", "EXAME (NOME)"),
	NM_FUNCIONARIO("3", "FUNCIONARIO (NOME)");
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarFichas> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarFichas.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarFichas(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarFichas buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarFichas opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarFichas> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}