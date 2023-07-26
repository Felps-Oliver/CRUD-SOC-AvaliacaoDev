package br.com.soc.sistema.action.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import br.com.soc.sistema.business.usuario.UsuarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.UsuarioVo;

@SuppressWarnings("serial")
public class LoginAction extends Action implements ServletRequestAware, ServletResponseAware {

	private UsuarioBusiness business = new UsuarioBusiness();
	private UsuarioVo usuarioVo = new UsuarioVo();

	protected HttpServletRequest servletRequest;
	protected HttpServletResponse servletResponse;

	/* Action que valida acesso do usuário*/
	public String valida() {

		/* Se o login/senha informado existe no Banco.
		 * Caso exista, retorna SUCCESS (permite acesso),
		 * efetuando a criação de um novo cookie.
		 * Caso não, retorna INPUT (redireciona a tela de login)
		 */
		
		if (usuarioVo.getLogin() != null) {
			boolean resultado = business.validaUsuario(usuarioVo);

			if (resultado) {
				Cookie userCookie = new Cookie("login", usuarioVo.getLogin());
				servletResponse.addCookie(userCookie);
				return SUCCESS;
			}
		}
		return INPUT;
	}
	
	/* Action que verifica se o usuário está logado */ 
	public boolean usuarioExisteCookie(HttpServletRequest request) {
	
		/* Valida se existe algum Cookie de nome "login".
		 * Caso sim, o usuário está logado (visto que 
		 * o login/cadastro de Cookie foi efetuado anteriormente).
		 */
		
		if (request != null) {
			if (request.getCookies() != null) {
				Cookie cookies[] = request.getCookies();
				if (cookies != null) {
					for (Cookie current : cookies) {
						if (current.getName().equals("login")) {
							return true;
						}
					}
				}

			}
		}
		return false;
	}

	/* Action que efetua o logout do usuário*/
	public String logout() {
		
		/* Segundo documentação (Cookie API - Oracle), para deletar um Cookie,
		 * deve-se settar o tempo de expiração como 0 segundos
		 */
		Cookie c = new Cookie("login", "");
		c.setMaxAge(0);
		servletResponse.addCookie(c);

		return LOGOUT;
	}

	public UsuarioVo getUsuarioVo() {
		return usuarioVo;
	}

	public void setUsuarioVo(UsuarioVo usuarioVo) {
		this.usuarioVo = usuarioVo;
	}

	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		this.servletRequest = hsr;
	}

	@Override
	public void setServletResponse(HttpServletResponse hsr) {
		this.servletResponse = hsr;

	}
}