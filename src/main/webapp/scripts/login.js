//Script js para procurar por um cookie, usado para verificar
// se o usuário está ou não logado na aplicação
function getCookie(name) {
	
	//Separa os cookies por delimitador, guardando em um array
	var cookieArr = document.cookie.split(";");

	//Itera pelo array de cookies	
	for (var i = 0; i < cookieArr.length; i++) {
		var cookiePair = cookieArr[i].split("=");
		
		//Compara o item do array, tirando espaços em branco, e comparando com a var 'name'
		if (name == cookiePair[0].trim()) {
			return decodeURIComponent(cookiePair[1]);
		}
	}
	
	return null;
}