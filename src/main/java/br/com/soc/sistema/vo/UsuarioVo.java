package br.com.soc.sistema.vo;

public class UsuarioVo {

	private String login;
	private String password;
	
	public UsuarioVo() {}
	
	public UsuarioVo(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioVo [login=" + login + ", password=" + password + "]";
	}
}
