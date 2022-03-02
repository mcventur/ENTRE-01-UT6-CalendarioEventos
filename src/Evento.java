import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  Representa a un evento del calendario
 *  @author Adri�n Las
 */
public class Evento {
    private String nombre;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private static DateTimeFormatter formateadorFecha = DateTimeFormatter
        .ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formateadorHora = DateTimeFormatter
        .ofPattern("HH:mm");

    /**
     * A partir de los argumentos recibidos
     * inicializa los atributos de forma adecuada
     * Todos los argumento recibidos son correctos (no hay incoherencias)
     */                 
    public Evento(String nombre, String fecha, String horaInicio, String horaFin) {
        this.nombre = fnombre(nombre);
        this.fecha = LocalDate.parse(fecha);
        this.horaInicio = LocalTime.parse(horaInicio);
        this.horaFin = LocalTime.parse(horaFin);
        //TODO: Cambiar parse, usar formatter
    }

    private String fnombre(String nombre) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = nombre.strip().split(" ");
        for(int i=0; i<tokens.length; i++){
            if(!tokens[i].equalsIgnoreCase(" ")){
                sb.append(tokens[i].toUpperCase().charAt(0) + tokens[i].toLowerCase().substring(1));
            }
            if(i != tokens.length-1) sb.append(" ");
        }
        return sb.toString();
        //TODO: Debug
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
        return fecha.getDayOfWeek().getValue();
    }

    /**
     * devuelve el mes (como valor enumerado)
     * que se obtendr� a partir de la fecha del evento
     */
    public Mes getMes() {
        return Mes.values()[fecha.getMonthValue()];
        //TODO: Debug
    }

    /**
     * calcula y devuelve la duraci�n del evento en minutos
     */
    public int getDuracion() {
        return horaFin.toSecondOfDay()/60 - horaInicio.toSecondOfDay()/60;
        //TODO: Debug
    }

    /**
     * Determina si el evento actual va antes (se produce) que
     * el pasado como par�metro
     * 
     * Se tiene en cuenta la fecha y hora de inicio de cada evento
     * Pista! usa un objeto LocalDateTime
     */
    public boolean antesDe(Evento otro) {
        if(fecha.isBefore(otro.fecha)) return true;
        else if(fecha.equals(otro.fecha)){
            if(horaInicio.isBefore(otro.horaInicio)) return true;
            else return false;
        }
        else return false;
        //TODO: Debug
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
     * C�digo para testea la clase Evento
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
        Evento ev3 = new Evento("   baluarte Pamplona    negra   ", "29/05/2021",
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
