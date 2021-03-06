import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Esta clase modela un sencillo calendario de eventos.
 * 
 * Por simplicidad consideraremos que los eventos no se solapan
 * y no se repiten
 * 
 * El calendario guarda en un map los eventos de una serie de meses
 * Cada mes (la clave en el map, un enumerado Mes) tiene asociados 
 * en una colecci?n ArrayList los eventos de ese mes
 * 
 * Solo aparecen los meses que incluyen alg?n evento
 * 
 * Las claves se recuperan en orden alfab?tico
 * 
 */
public class CalendarioEventos {
	private TreeMap<Mes, ArrayList<Evento>> calendario;

	/**
	 * el constructor
	 */
	public CalendarioEventos() {
		this.calendario = new TreeMap<>();
	}

	/**
	 * a?ade un nuevo evento al calendario
	 * Si la clave (el mes del nuevo evento) no existe en el calendario
	 * se crear? una nueva entrada con dicha clave y la colecci?n formada
	 * por ese ?nico evento
	 * Si la clave (el mes) ya existe se a?ade el nuevo evento insert?ndolo de forma
	 * que quede ordenado por fecha y hora de inicio
	 * 
	 * Pista! Observa que en la clase Evento hay un m?todo antesDe() que vendr?
	 * muy bien usar aqu?
	 */
	public void addEvento(Evento nuevo) {
		 

	}

	 

	/**
	 * Representaci?n textual del calendario
	 * Hacer de forma eficiente 
	 * Usar el conjunto de entradas  
	 */
	public String toString() {
		 
		return null;
	}

	/**
	 * Dado un mes devolver la cantidad de eventos que hay en ese mes
	 * Si el mes no existe se devuelve 0
	 */
	public int totalEventosEnMes(Mes mes) {
		 
		return 0;
	}

	/**
	 * Devuelve un conjunto (importa el orden) 
	 * con los meses que tienen mayor n? de eventos
	 * Hacer un solo recorrido del map con el conjunto de claves
	 *  
	 */
	public TreeSet<Mes> mesesConMasEventos() {
	    
	    
		return null;
	}

	
	/**
	 * Devuelve el nombre del evento de mayor duraci?n en todo el calendario
	 * Se devuelve uno solo (el primero encontrado) aunque haya varios
	 */
	public String eventoMasLargo() {
	    
		return null;
	}

	/**
	 * Borrar del calendario todos los eventos de los meses indicados en el array
	 * y que tengan lugar el d?a de la semana proporcionado (se entiende d?a de la
	 * semana como 1 - Lunes, 2 - Martes ..  6 - S?bado, 7 - Domingo)
	 * 
	 * Si alguno de los meses del array no existe el el calendario no se hace nada
	 * Si al borrar de un mes los eventos el mes queda con 0 eventos se borra la entrada
	 * completa del map
	 */
	public int cancelarEventos(Mes[] meses, int dia) {
		 

		return 0;
	}

	/**
	 * C?digo para testear la clase CalendarioEventos
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
		System.out.println("Mes/es con m?s eventos "
		                    + calendario.mesesConMasEventos());

		System.out.println();
		System.out.println("Evento de mayor duraci?n: "
		                    + calendario.eventoMasLargo());

		System.out.println();
		Mes[] meses = {Mes.FEBRERO, Mes.MARZO, Mes.MAYO, Mes.JUNIO};
		int dia = 6;
		System.out.println("Cancelar eventos de " + Arrays.toString(meses));
		int cancelados = calendario.cancelarEventos(meses, dia);
		System.out.println("Se han cancelado " + cancelados +
		                    " eventos");
		System.out.println();
		System.out.println("Despu?s de cancelar eventos ...");
		System.out.println(calendario);
	}

}
