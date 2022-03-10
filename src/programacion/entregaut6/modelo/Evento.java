package programacion.entregaut6.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa a un evento del calendario
 * 
 */
public class Evento {
    private String nombre;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private static DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formateadorHora = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * A partir de los argumentos recibidos
     * inicializa los atributos de forma adecuada
     * Todos los argumento recibidos son correctos (no hay incoherencias)
     */                 
    public Evento(String nombre, String fecha, String horaInicio, String horaFin) {

         this.nombre = capital(nombre.trim());
         this.fecha = LocalDate.parse(fecha.trim(), formateadorFecha);
         this.horaInicio = LocalTime.parse(horaInicio.trim(), formateadorHora);
         this.horaFin = LocalTime.parse(horaFin.trim(), formateadorHora);
    }

    private String capital(String name) {

        var splitFrase = name.split(" "); //Separo por palabras la frase en un Array
        var str = "";
        var resultado = "";

        // A cada palabra cojo la primera letra, la capitalizo y la concateno con el resto de su palabra
        for (int i = 0; i < splitFrase.length; i++) {
            str = splitFrase[i].substring(0,1).toUpperCase() + splitFrase[i].substring(1);
            // Uno las palabras con espacios para formar la frase
            resultado += str + " ";
        }

        return resultado.trim(); //Vuelvo a limpiar la frase por el espacio anterior para eliminar el �ltimo espacio
    }

    /**
     * accesor para el nombre del evento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre del evento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para la fecha del evento
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * mutador para la fecha del evento
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * accesor para la hora de inicio del evento
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * mutador para la hora de inicio del evento
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * accesor para la hora de fin del evento
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * mutador para la hora de fin del evento
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * devuelve n� de d�a de la semana (1 lunes, 2 martes .... 7 domingo)
     * que se obtendr� a partir de la fecha del evento
     */
    public int getDia() {

        return getFecha().getDayOfWeek().getValue();
    }

    /**
     * devuelve el mes (como valor enumerado)
     * que se obtendr� a partir de la fecha del evento
     */
    public Mes getMes() {

        //Pasamos todos los valores del enum a un array con la funci�n values()
        Mes[] valores = Mes.values();
        //getMonthValue nos da un valor entre 0 y 12 para el mes de la fecha correspondiente
        return valores[fecha.getMonthValue()-1];
    }

    /**
     * calcula y devuelve la duraci�n del evento en minutos
     */
    public int getDuracion() {

        return (int) Duration.between(getHoraInicio(), getHoraFin()).toMinutes();
    }

    /**
     * Determina si el evento actual va antes (se produce) que
     * el pasado como par�metro
     * 
     * Se tiene en cuenta la fecha y hora de inicio de cada evento
     * Pista! usa un objeto LocalDateTime
     */
    public boolean antesDe(Evento otro) {

        LocalDateTime miFecha=LocalDateTime.of(this.fecha,this.horaInicio);
        LocalDateTime otraFecha= LocalDateTime.of(otro.getFecha(),otro.getHoraInicio());
        return (miFecha.compareTo(otraFecha)<0);
    }

  
    /**
     * representaci�n textual del evento  
     */
    public String toString() {

        return String.format("%8s: %s (D�a semana %d)\n", "Nombre", this.nombre, this.getDia()) +
        String.format("%8s: %s\t", "Fecha",
            this.fecha.format(formateadorFecha))   +
        String.format("%s: %s", "Hora inicio",
            this.horaInicio.format(formateadorHora))   +
        String.format("%12s: %s (%d')", "Hora fin",
            this.horaFin.format(formateadorHora),
            this.getDuracion())
        +  "\n------------------------------------------------------";
    }

    /**
     * C�digo para testea la clase programacion.entregaut6.modelo.Evento
     */
    public static void main(String[] args) {
        Evento ev1 = new Evento("Examen de programaci�n", "03/02/2021",
                "11:45",
                "13:20");
        System.out.println(ev1.toString());      

        System.out.println();
        Evento ev2 = new Evento("Recoger abrigo en tintorer�a", "13/03/2021",
                "09:30",
                "10:00");
        System.out.println(ev2.toString());     

        System.out.println();
        Evento ev3 = new Evento("   baluarte Pamplona negra   ", "29/05/2021",
                "17:00",
                "21:00");
        System.out.println(ev3.toString());

        System.out.println();
        Evento ev4 = new Evento("Comida restaurante europa", "22/05/2021",
                "12:00",
                "17:00");
        System.out.println(ev4.toString());

        System.out.println();
        Evento ev5 = new Evento(" peluquer�a   ", "29/05/2021",
                "10:20",
                "12:00");
        System.out.println(ev5.toString());

        System.out.println();

        System.out.println("El evento de nombre " + ev3.getNombre() + 
            "\nse produce antes del evento de nombre " + ev5.getNombre() + "? " +
            ev3.antesDe(ev5));
        System.out.println("\nEl evento de nombre " + ev3.getNombre() + 
            "\nse produce despu�s del evento de nombre " + ev5.getNombre() + "? " +
            !(ev3.antesDe(ev5)));
        System.out.println("\nEl evento de nombre " + ev1.getNombre() + 
            "\nse produce antes del evento de nombre " + ev2.getNombre() + "? " +
            ev1.antesDe(ev2));
    }
}
