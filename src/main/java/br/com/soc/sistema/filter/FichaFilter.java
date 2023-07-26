package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarFichas;

public class FichaFilter {
	private OpcoesComboBuscarFichas opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public FichaFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarFichas getOpcoesCombo() {
		return opcoesCombo;
	}

	public FichaFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarFichas.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static FichaFilter builder() {
		return new FichaFilter();
	}
}
