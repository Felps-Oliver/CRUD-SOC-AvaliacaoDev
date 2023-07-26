package br.com.soc.sistema.vo;

public class FichaVo {
	private String id_ficha;
	private String id_funcionario;
	private String id_exame;
	private String data_exame;
	
	private String nome_exame;
	private String nome_funcionario;
	
	//Datas para serem utilizadas no relatório de exames realizados
	private String data_inicio;
	private String data_fim;
	
	//Refere-se a quantidade de exames realizados, utilizado no relatório de TOP 5 Exames
	private String total_exame;
	
	public FichaVo() {}
		
	public String getId_ficha() {
		return id_ficha;
	}

	public void setId_ficha(String id_ficha) {
		this.id_ficha = id_ficha;
	}
	
	public FichaVo(String id_ficha, String id_funcionario, String id_exame, String data_exame) {
		this.id_ficha = id_ficha;
		this.id_funcionario = id_funcionario;
		this.id_exame = id_exame;
		this.data_exame = data_exame;
	}
		
	public FichaVo(String id_ficha, String id_funcionario, String id_exame, String data_exame, String nome_exame,
			String nome_funcionario) {
		this.id_ficha = id_ficha;
		this.id_funcionario = id_funcionario;
		this.id_exame = id_exame;
		this.data_exame = data_exame;
		this.nome_exame = nome_exame;
		this.nome_funcionario = nome_funcionario;
	}

	public String getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(String id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getId_exame() {
		return id_exame;
	}

	public void setId_exame(String id_exame) {
		this.id_exame = id_exame;
	}

	public String getData_exame() {
		return data_exame;
	}

	public void setData_exame(String data_exame) {
		this.data_exame = data_exame;
	}
	
	public String getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}

	public String getNome_exame() {
		return nome_exame;
	}

	public void setNome_exame(String nome_exame) {
		this.nome_exame = nome_exame;
	}

	public String getNome_funcionario() {
		return nome_funcionario;
	}

	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}
	
	public String getTotal_exame() {
		return total_exame;
	}

	public void setTotal_exame(String total_exame) {
		this.total_exame = total_exame;
	}

	@Override
	public String toString() {
		return "FichaVo [id_ficha=" + id_ficha + ", id_funcionario=" + id_funcionario + ", id_exame=" + id_exame
				+ ", data_exame=" + data_exame + ", nome_exame=" + nome_exame + ", nome_funcionario=" + nome_funcionario
				+ "]";
	}

}
