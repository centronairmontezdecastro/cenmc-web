package br.org.cenmc.controller;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.model.Contato;
import br.org.cenmc.model.Pessoa;
import br.org.cenmc.model.autenticacao.SessaoUsuario;
import br.org.cenmc.utils.EnviarEmail;

@Named
@ConversationScoped
public class HomeController implements Serializable {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Conversation conversation;
	@Inject
	private EnviarEmail email;
	@Inject
	private LoginController loginController;
	@Inject
	private SessaoUsuario sessaoUsuario;
	@Inject
	private JornalController jornalController;
	
	private Contato contato = new Contato();
	public static final String ORIGEM = "mmcnet@gmail.com";
	public static final String DESTINO = "mmcnet@gmail.com";
	private String message;
	private static final String AREARESTRITA = "principal.xhtml?faces-redirect=true";
	
	
	public String init() {
	    if (!conversation.isTransient())
            conversation.end();
        conversation.begin();   
        return AREARESTRITA;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	

	
	public void Enviar() {

		if (contato.getAssunto() != "") {

			email.sendMail(ORIGEM, DESTINO, contato.getAssunto(), formatoTexto());
			//MensagemResposta("Seu e-maill foi enviado corretamente, aguarde nosso retorno !", "Successful");
		}
		//MensagemResposta("Seu e-maill não foi enviado corretamente, aguarde e tente novamente !",
		//		"Error");
	}

	private String formatoTexto() {
		String msg="<html>\n" + 
				"<title>CENMC : Centro Espirita Nair Montez de Castro</title>\n" + 
				"<head>\n" + 
				"<meta charset=\"utf-8\" />\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" + 
				"	<link rel=\"stylesheet\"\n" + 
				"		href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" />\n" + 
				"	<link href=\"https://fonts.googleapis.com/css?family=Montserrat\"\n" + 
				"		rel=\"stylesheet\" type=\"text/css\" />\n" + 
				"	<link href=\"https://fonts.googleapis.com/css?family=Lato\"\n" + 
				"		rel=\"stylesheet\" type=\"text/css\" />\n" + 
				"	<link rel=\"stylesheet\"\n" + 
				"		href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" />\n" + 
				"	<script\n" + 
				"		src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" + 
				"	<script\n" + 
				"		src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" + 
				"</head>\n" + 
				"<style>\n" + 
				"body {\n" + 
				"	font: 400 15px Lato, sans-serif;\n" + 
				"	line-height: 1.8;\n" + 
				"	color: #818181;\n" + 
				"}\n" + 
				"\n" + 
				"h2 {\n" + 
				"	font-size: 24px;\n" + 
				"	text-transform: uppercase;\n" + 
				"	color: #303030;\n" + 
				"	font-weight: 600;\n" + 
				"	margin-bottom: 30px;\n" + 
				"}\n" + 
				"\n" + 
				"h4 {\n" + 
				"	font-size: 19px;\n" + 
				"	line-h <b:formGroup>\n" + 
				"    <b:selectOneMenu value=\"#{selectOneMenuBean.brand}\" colMd=\"2\" required=\"true\" label=\"Select your car's brand:\" labelColMd=\"2\">\n" + 
				"      <f:selectItem itemLabel=\"(Please select)\" itemValue=\"\" />\n" + 
				"      <f:selectItem itemLabel=\"Fiat\" itemValue=\"1\" />\n" + 
				"      <f:selectItem itemLabel=\"Honda\" itemValue=\"2\" />\n" + 
				"      <f:selectItem itemLabel=\"Opel\" itemValue=\"3\" /Area>\n" + 
				"    </b:selectOneMenu>\n" + 
				"    <b//        HttpServletRequest request = (HttpServletRequest) getFacesContext().getCurrentInstance().getExternalContext()\n" + 
				"//				.getRequest();\n" + 
				"//		HttpServletResponse response = (HttpServletResponse) getFacesContext().getCurrentInstance().getExternalContext()\n" + 
				"//				.getResponse();\n" + 
				"//\n" + 
				"//		HttpSession sessao = request.getSession();\n" + 
				"//		if(sessao!=null) {\n" + 
				"//			pessoa = (Pessoa) sessao.getAttribute(\"usuarioLogado\");\n" + 
				"//		}\n" + 
				"//		:message for=\"@previous\" colMd=\"2\" />\n" + 
				"    <b:inputText value=\"#{selectOneMenuBean.brand}\" disabled=\"true\" colMd=\"4\" label=\"Last submitted value:\" labelColMd=\"2\" />\n" + 
				"  </b:formGroup>eight: 1.375em;\n" + 
				"	color: #303030;\n" + 
				"	font-weight: 400;\n" + 
				"	margin-bottom: 30px;\n" + 
				"}\n" + 
				"\n" + 
				".container-fluid {\n" + 
				"	padding: 60px 50px;\n" + 
				"}\n" + 
				"\n" + 
				".bg-grey {\n" + 
				"	background-color: #f6f6f6;\n" + 
				"}\n" + 
				"\n" + 
				".bg-white {\n" + 
				"	background-color: #fff;\n" + 
				"}\n" + 
				".bg-verdeNormal {\n" + 
				"	background-color: #C7E5C8;\n" + 
				"}\n" + 
				".bg-verdeEscuro {\n" + 
				"	background-color: #4B574C;\n" + 
				"}\n" + 
				"\n" + 
				".bg-verdeMusgo {\n" + 
				"	background-color: #99A39A;\n" + 
				"}\n" + 
				"\n" + 
				".bg-verdeForte {\n" + 
				"	background-color: #97AD98;\n" + 
				"}\n" + 
				"\n" + 
				".logo-small {\n" + 
				"	color: #C7E5C8;\n" + 
				"	font-size: 50px;\n" + 
				"}\n" + 
				"\n" + 
				".logo {\n" + 
				"	color: #C7E5C8;\n" + 
				"	font-size: 200px;\n" + 
				"}\n" + 
				"\n" + 
				".textoBranco {\n" + 
				"	color: #fff;	\n" + 
				"}\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				".item h4 {\n" + 
				"	font-size: 19px;\n" + 
				"	line-height: 1.375em;\n" + 
				"	font-weight: 400;\n" + 
				"	font-style: italic;\n" + 
				"	margin: 70px 0;\n" + 
				"}\n" + 
				"\n" + 
				".item span {\n" + 
				"	font-style: normal;\n" + 
				"}\n" + 
				"\n" + 
				".panel {\n" + 
				"	border: 1px solid #C7E5C8;\n" + 
				"	border-radius: 0 !important;\n" + 
				"	transition: box-shadow 0.5s;\n" + 
				"}\n" + 
				"\n" + 
				".panel:hover {\n" + 
				"	box-shdataFormatadaadow: 5px 0px 40px rgba(0, 0, 0, .2);\n" + 
				"}\n" + 
				"\n" + 
				".panel-footer .btn:hover {\n" + 
				"	border: 1px solid #C7E5C8;\n" + 
				"	background-color: #fff !important;\n" + 
				"	color: #C7E5C8;\n" + 
				"}\n" + 
				"\n" + 
				".panel-heading {\n" + 
				"	color: #fff !important;\n" + 
				"	background-color: #C7E5C8 !important;\n" + 
				"	padding: 25px;\n" + 
				"	border-bottom: 1px solid transparent;\n" + 
				"	border-top-left-radius: 0px;\n" + 
				"	border-top-right-radius: 0px;\n" + 
				"	border-bottom-left-radius: 0px;\n" + 
				"	border-bottom-right-radius: 0px;\n" + 
				"}\n" + 
				"\n" + 
				".panel-footer {\n" + 
				"	background-color: white !important;\n" + 
				"}\n" + 
				"\n" + 
				".panel-footer h3 {\n" + 
				"	font-size: 32px;\n" + 
				"}\n" + 
				"\n" + 
				".panel-footer h4 {\n" + 
				"	color: #aaa;\n" + 
				"	font-size: 14px;\n" + 
				"}\n" + 
				"\n" + 
				".panel-footer .btn {\n" + 
				"	margin: 15px 0;\n" + 
				"	background-color: #C7E5C8;\n" + 
				"	color: #fff;\n" + 
				"}\n" + 
				"\n" + 
				".navbar {\n" + 
				"	margin-bottom: 0;\n" + 
				"	background-color: #C7E5C8;\n" + 
				"	z-index: 9999;\n" + 
				"	border: 0;\n" + 
				"	font-size: 12px !important;\n" + 
				"	line-height: 1.42857143 !important;\n" + 
				"	letter-spacing: 4px;\n" + 
				"	border-radius: 0;\n" + 
				"	font-familyTEMPLATE_AREARESTRITA: Montserrat, sans-serif;\n" + 
				"}\n" + 
				"\n" + 
				".navbar li a, .navbar .navbar-brand {\n" + 
				"	color: #4B574C !important;\n" + 
				"	font-style: inherit;\n" + 
				"}\n" + 
				"\n" + 
				".navbar-nav li a:hover, .navbar-nav li.active a {\n" + 
				"	color: #4B574C !important;\n" + 
				"	background-color: #fff !important;\n" + 
				"}\n" + 
				"\n" + 
				".navbar-default .navbar-toggle {\n" + 
				"	border-color: transparent;\n" + 
				"	color: #fff !important;\n" + 
				"}\n" + 
				"\n" + 
				"footer .glyphicon {\n" + 
				"	font-size: 20px;\n" + 
				"	margin-bottom: 20px;\n" + 
				"	color: #FFF;\n" +
				"}\n" + 
				"\n" + 
				"\n" + 
				"</style>\n" + 
				"<body id=\"myPage\" data-spy=\"scroll\" data-offset=\"60\">\n" + 
				"	<div id=\"casa\" class=\"container-fluid bg-verdeNormal\">\n" + 
				"		<div class=\"row\">\n" + 
				"			<div class=\"col-sm-8\">\n" + 
				"				<h2>FALE CONOSCO</h2>\n" + 
				"				<br />\n" + 
				"				<h4>Obrigado por ter entrado em contato conosco.</h4>\n" + 
				"		dataFormatada		<br />\n" + 
				"				<p>O Centro Espírita Nair Montez de Castro tem por missão acalentar \n" + 
				"				seja qual for seu problema em breve lhe enviaremos uma mensagem ou\n" + 
				"				entraremos em contato pelos dados que você nos forneceu aguarde !</p>\n" + 
				"				<br />				\n" + 
				"			</div>\n" + 
				"			<div class=\"col-sm-4\">\n" + 
				"				<span class=\"glyphicon glyphicon-phone-alt logo\"></span>\n" + 
				"			</div>\n" + 
				"		</div>\n" + 
				"	</div>\n" + 
				"	<div id=\"dados\" class=\"container-fluid bg-white\">\n" + 
				"		<div class=\"text-center\">\n" + 
				"			<h2>DADOS DO USUÁRIO</h2>\n" + 
				"			<h4>NOME : "+contato.getNome()+"</h4>\n" + 
				"			<br/>\n" + 
				"			<h4>E-MAIL : "+contato.getEmail()+"</h4>\n" + 
				"			<br/>\n" + 
				"			<h4>ASSUNTO : "+contato.getAssunto()+"</h4>\n" + 
				"			<br/>\n" + 
				"			<p>COMENTÁRIO : "+contato.getComentario()+"</p>\n" + 
				"			\n" + 
				"		</div>\n" + 
				"		\n" + 
				"	</div>\n" + 
				"</body>\n" + 
				"</html>";
		
		return msg;
	}



	
	public String portal() {
		return loginController.init();
	}
	public String efetuarLogout() {
		sessaoUsuario.deleteSessao();
		return loginController.init();
	}
	public Pessoa getUsuarioLogado() {
		return sessaoUsuario.getSessao();
	}
	public String downloadJornal() {
		return jornalController.pathJornal();
	}
}
