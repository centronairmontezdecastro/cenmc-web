package br.org.cenmc.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.org.cenmc.model.Evento;

@Named
@ConversationScoped
public class EventoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String[] tema;

	private final static String[] data;

	static {
		tema = new String[10];
		tema[0] = "Black";
		tema[1] = "White";
		tema[2] = "Green";
		tema[3] = "Red";
		tema[4] = "Blue";
		tema[5] = "Orange";
		tema[6] = "Silver";
		tema[7] = "Yellow";
		tema[8] = "Brown";
		tema[9] = "Maroon";

		data = new String[10];
		data[0] = "01/02/2016";
		data[1] = "01/05/2017";
		data[2] = "05/05/2017";
		data[3] = "10/12/2017";
		data[4] = "01/02/2018";
		data[5] = "04/04/2018";
		data[6] = "06/07/2018";
		data[7] = "08/08/2018";
		data[8] = "09/09/2018";
		data[9] = "10/11/2018";
	}

	public List<Evento> createEventos(int size) {
		List<Evento> list = new ArrayList<Evento>();
		for (int i = 0; i < size; i++) {
			list.add(new Evento(getBData().get(i), getTema().get(i)));
		}

		return list;
	}

	public List<String> getTema() {
		return Arrays.asList(tema);
	}

	public List<String> getBData() {
		return Arrays.asList(data);
	}

}
