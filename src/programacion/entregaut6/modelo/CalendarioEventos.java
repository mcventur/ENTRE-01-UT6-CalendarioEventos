package programacion.entregaut6.modelo;

import programacion.entregaut6.io.CalendarioIO;

import java.util.*;

/**
 * Esta clase modela un sencillo calendario de eventos.
 * 
 * Por simplicidad consideraremos que los eventos no se solapan
 * y no se repiten
 * 
 * El calendario guarda en un map los eventos de una serie de meses
 * Cada mes (la clave en el map, un enumerado programacion.entregaut6.modelo.Mes) tiene asociados
 * en una colecci�n ArrayList los eventos de ese mes
 * 
 * Solo aparecen los meses que incluyen alg�n evento
 * 
 * Las claves se recuperan en orden alfab�tico
 * 
 */
public class CalendarioEventos {

	/*ATRIBUTOS*/
	private TreeMap<Mes, ArrayList<Evento>> calendario;

	/**
	 * el constructor
	 */
	public CalendarioEventos() {
		this.calendario = new TreeMap<>();
	}

	/**
	 * a�ade un nuevo evento al calendario
	 * Si la clave (el mes del nuevo evento) no existe en el calendario
	 * se crear� una nueva entrada con dicha clave y la colecci�n formada
	 * por ese �nico evento
	 * Si la clave (el mes) ya existe se a�ade el nuevo evento insert�ndolo de forma
	 * que quede ordenado por fecha y hora de inicio
	 * <p>
	 * Pista! Observa que en la clase programacion.entregaut6.modelo.Evento hay un m�todo antesDe() que vendr�
	 * muy bien usar aqu�
	 */
	public void addEvento(Evento nuevo) {

		ArrayList<Evento> setEventos;

		if (!calendario.containsKey(nuevo.getMes())) {

			setEventos = new ArrayList<>();
			setEventos.add(nuevo);
			calendario.put(nuevo.getMes(), setEventos);

		} else {

			setEventos = new ArrayList<>();
			for (Evento i : calendario.get(nuevo.getMes())) {
				if (i.antesDe(nuevo)) {
					setEventos.add(nuevo);
				}
			}
			calendario.put((nuevo.getMes()), setEventos);
		}
	}

	/**
	 * Representaci�n textual del calendario
	 * Hacer de forma eficiente
	 * Usar el conjunto de entradas
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	/**
	 * Dado un mes devolver la cantidad de eventos que hay en ese mes
	 * Si el mes no existe se devuelve 0
	 */
	public int totalEventosEnMes(Mes mes) {

		if (calendario.containsKey(mes)) {

			ArrayList<Evento> eventos = calendario.get(mes);
			return eventos.size();

		}

		return 0;
	}

	/**
	 * Devuelve un conjunto (importa el orden)
	 * con los meses que tienen mayor n� de eventos
	 * Hacer un solo recorrido del map con el conjunto de claves
	 */
	public TreeSet<Mes> mesesConMasEventos() {

		TreeSet<Mes> retorno = new TreeSet<>();

		for (Mes element : calendario.keySet()) {

			int mayor = totalEventosEnMes(element);

			if (totalEventosEnMes(element) > mayor) {
				mayor = totalEventosEnMes(element);
				retorno.add(element);
			}
		}

		return retorno;
	}


	/**
	 * Devuelve el nombre del evento de mayor duraci�n en todo el calendario
	 * Se devuelve uno solo (el primero encontrado) aunque haya varios
	 */
	public String eventoMasLargo() {

		String retorno = null;

		for (Mes mes : calendario.keySet()) {

			ArrayList<Evento> recorrido = calendario.get(mes);

			for (Evento evento : recorrido) {
				int mayor = evento.getDuracion();
				if (evento.getDuracion() > mayor) {
					retorno = evento.getNombre();
				}
			}
		}

		return retorno;
	}

	/**
	 * Borrar del calendario todos los eventos de los meses indicados en el array
	 * y que tengan lugar el d�a de la semana proporcionado (se entiende d�a de la
	 * semana como 1 - Lunes, 2 - Martes ..  6 - S�bado, 7 - Domingo)
	 * <p>
	 * Si alguno de los meses del array no existe el el calendario no se hace nada
	 * Si al borrar de un mes los eventos el mes queda con 0 eventos se borra la entrada
	 * completa del map
	 */
	public int cancelarEventos(Mes[] meses, int dia) {

		int retorno = 0;

		for (Map.Entry<Mes, ArrayList<Evento>> item : calendario.entrySet()) {

		}

		return retorno;
	}


	/**
	 * C�digo para testear la clase programacion.entregaut6.modelo.CalendarioEventos
	 */
	public static void main(String[] args) {
		CalendarioEventos calendario = new CalendarioEventos();
		CalendarioIO.cargarEventos(calendario);
		System.out.println(calendario);

		System.out.println();

		Mes mes = Mes.FEBRERO;
		System.out.println("Eventos en " + mes + " = "
		                    + calendario.totalEventosEnMes(mes));
		mes = Mes.MARZO;
		System.out.println("Eventos en " + mes + " = "
		                    + calendario.totalEventosEnMes(mes));
		System.out.println("programacion.entregaut6.modelo.Mes/es con m�s eventos "
		                    + calendario.mesesConMasEventos());

		System.out.println();
		System.out.println("programacion.entregaut6.modelo.Evento de mayor duraci�n: "
		                    + calendario.eventoMasLargo());

		System.out.println();
		Mes[] meses = {Mes.FEBRERO, Mes.MARZO, Mes.MAYO, Mes.JUNIO};
		int dia = 6;
		System.out.println("Cancelar eventos de " + Arrays.toString(meses));
		int cancelados = calendario.cancelarEventos(meses, dia);
		System.out.println("Se han cancelado " + cancelados +
		                    " eventos");
		System.out.println();
		System.out.println("Despu�s de cancelar eventos ...");
		System.out.println(calendario);
	}

}
