package br.com.soc.sistema.infra;

import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	/* Strings para serem usadas como Returns customizados nas Actions */
	public static final String REDIRECT = "redirect";
	public static final String EDIT = "edit";
	public static final String REPORT = "report_examesrealizados";
	public static final String REPORT_TOPEXAMES = "report_topexames";
	public static final String LOGOUT = "logout";
}
