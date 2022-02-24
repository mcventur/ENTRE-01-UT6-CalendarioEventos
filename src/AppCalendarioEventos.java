/**
 * Punto de entrad a la aplicación
 */
public class AppCalendarioEventos {

	public static void main(String[] args) {
		CalendarioEventos calendario = new CalendarioEventos();
		IUConsola interfaz = new IUConsola(calendario);
		interfaz.iniciar();

	}

}
