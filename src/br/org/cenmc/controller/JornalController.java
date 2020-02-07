package br.org.cenmc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped
public class JornalController implements Serializable {

	/**
	 * dataFormatada
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Conversation conversation;

	private UploadedFile file;
	private StreamedContent arquivo;
	private static final String JORNALUPLOAD = "/view/sistema/jornal/enviarJornal.xhtml?faces-redirect=true";
	
	public JornalController() throws IOException{
		try {
			
			String nomeArquivo = "caravana"+formatarDataAtual()+".pdf";
			InputStream stream = new FileInputStream(pathJornal());
			arquivo = new DefaultStreamedContent(stream, "application/pdf", nomeArquivo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void setArquivo(StreamedContent arquivo) {
		this.arquivo = arquivo;
	}

	public StreamedContent getarquivo() {
		return arquivo;
	}

	public String init() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
		return JORNALUPLOAD;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {

			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " Arquivo enviado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			salvar(file);
		}
	}

	@Deprecated
	public void handleFileUpload(FileUploadEvent event) {
		if (file != null) {
			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " Arquivo enviado.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			salvar(file);

		}
	}

	public void salvar(UploadedFile file) {

		OutputStream outputStream;
		InputStream inputStream;
		try {
			outputStream = new FileOutputStream(
					new File(pathJornal()));
			inputStream = file.getInputstream();
			byte[] buffer = new byte[1024];
			for (int count; (count = inputStream.read(buffer)) != -1;) {
				outputStream.write(buffer, 0, count);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String pathJornal() {
		String path = "/opt/jornal/" + "caravana_" + formatarDataAtual() + ".pdf";
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
		String diretorio = ec.getRealPath("/");
//		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
//		path = session.getServletContext().getRealPath(path);
		return path;
	}
	
	private String formatarDataAtual() {
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat dataSimples = new SimpleDateFormat("MM_yyyy");
		String dataFormatada = dataSimples.format(data);
		return dataFormatada;
	}

}
