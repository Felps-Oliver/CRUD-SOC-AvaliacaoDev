## Sobre o projeto

Este projeto visa a criação de um CRUD básico envolvendo as temáticas:
- Exames;
- Funcionários;
- Exames Realizados pelo funcionário.

Além das temáticas acima, foi desenvolvida **autenticação** de usuário com base no uso de Cookies; ainda, foram desenvolvidos **Web Services SOAP** para cada temática (Exames, Funcionários e Exames Realizados), permitindo assim a integração entre sistemas.

### Autor

Felipe Rocha de Oliveira - felipe.oliveira200085@gmail.com

### Regras sistêmicas implementadas

Como parte da especificação do projeto, foram implementadas as seguintes regras:

- Ao deletar um funcionário, deletar também os  seus exames realizados
- Não permitir deletar um exame se ele tiver sido realizado por um ou mais funcionários
- Implementar melhoria para não permitir cadastrar exames realizados duplicados, ou seja, o mesmo exame para o mesmo funcionário na mesma data

### Tecnologias utilizadas

Abaixo consta listagem das tecnologias utilizadas para desenvolvimento do projeto:

- Eclipse
- Java 8+
- Maven
- Struts 2
- HTML/CSS/Javascript
- Bootstrap 5
- H2
- Jetty
- Git
- Soap
