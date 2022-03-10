import java.util.*;

/**
 * Esta clase modela un sencillo calendario de eventos.
 *
 * Por simplicidad consideraremos que los eventos no se solapan
 * y no se repiten
 *
 * El calendario guarda en un map los eventos de una serie de meses
 * Cada mes (la clave en el map, un enumerado Mes) tiene asociados 
 * en una colección ArrayList los eventos de ese mes
 *
 * Solo aparecen los meses que incluyen algún evento
 *
 * Las claves se recuperan en orden alfabético
 */
public class CalendarioEventos {
    private TreeMap<Mes, ArrayList<Evento>> calendario;

    /**
     * Constructor
     */
    public CalendarioEventos() {
        this.calendario = new TreeMap<>();
    }

    /**
     * añade un nuevo evento al calendario
     * Si la clave (el mes del nuevo evento) no existe en el calendario
     * se creará una nueva entrada con dicha clave y la colección formada
     * por ese único evento
     * Si la clave (el mes) ya existe se añade el nuevo evento insertándolo de forma
     * que quede ordenado por fecha y hora de inicio
     *
     * Pista! Observa que en la clase Evento hay un método antesDe() que vendrá
     * muy bien usar aquí
     */
    public void addEvento(Evento nuevo) {
        ArrayList<Evento> eventos = calendario.get(nuevo.getMes());

        if(!calendario.containsKey(nuevo.getMes())){
            eventos = new ArrayList<Evento>();
            eventos.add(nuevo);
            calendario.put(nuevo.getMes(), eventos);
        }
        else insertarOrdenado(nuevo, eventos);
    }

    /**
     * Añade el nuevo evento insertándolo de forma que quede ordenado por fecha y hora de inicio
     */
    private void insertarOrdenado(Evento nuevo, ArrayList<Evento> eventos) {
        for (int i = eventos.size()-1; i > 0; i--) {
            if(eventos.get(i).antesDe(nuevo)) eventos.add(i+1, nuevo);
        }
        if(eventos.size()==1){
            if(eventos.get(0).antesDe(nuevo)) eventos.add(nuevo);
        }
        if(!eventos.contains(nuevo)) eventos.add(0, nuevo);
    } //TODO


    /**
     * Representación textual del calendario
     * Hacer de forma eficiente
     * Usar el conjunto de entradas
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes, ArrayList<Evento>>> set = calendario.entrySet();

        for (Map.Entry<Mes, ArrayList<Evento>> m: set){
            sb.append(m.getKey()).append("\n\n");
            for (Evento evento: m.getValue()) sb.append(evento.toString());
        }
        return sb.toString();
    }

    /**
     * Dado un mes devolver la cantidad de eventos que hay en ese mes
     * Si el mes no existe se devuelve 0
     */
    public int totalEventosEnMes(Mes mes) {
        if(calendario.containsKey(mes)) return calendario.get(mes).size();
        else return 0;
    }

    /**
     * Devuelve un conjunto (importa el orden)
     * con los meses que tienen mayor nº de eventos
     * Hacer un solo recorrido del map con el conjunto de claves
     */
    public TreeSet<Mes> mesesConMasEventos() {
        TreeSet<Mes> salida = new TreeSet<>();
        int max = 0;

        for (Mes m: calendario.keySet()){
            if(totalEventosEnMes(m) >= max){
                salida = new TreeSet<>();
                salida.add(m);
                max = totalEventosEnMes(m);
            }
        }
        return salida;
    }


    /**
     * Devuelve el nombre del evento de mayor duración en todo el calendario
     * Se devuelve uno solo (el primero encontrado) aunque haya varios
     */
    public String eventoMasLargo() {
        Evento max = calendario.firstEntry().getValue().get(0);

        for (Map.Entry<Mes, ArrayList<Evento>> e: calendario.entrySet()){
            for (Evento evento: e.getValue()){
                if(evento.getDuracion() > max.getDuracion()) max = evento;
            }
        }
        return max.getNombre();
    }

    /**
     * Borrar del calendario todos los eventos de los meses indicados en el array
     * y que tengan lugar el día de la semana proporcionado (se entiende día de la
     * semana como 1 - Lunes, 2 - Martes ..  6 - Sábado, 7 - Domingo)
     *
     * Si alguno de los meses del array no existe el el calendario no se hace nada
     * Si al borrar de un mes los eventos el mes queda con 0 eventos se borra la entrada
     * completa del map
     *
     * @return nº de eventos cancelados
     */
    public int cancelarEventos(Mes[] meses, int dia) {
        int con = 0;
        Iterator<Map.Entry<Mes, ArrayList<Evento>>> itEnt = calendario.entrySet().iterator();
        Map.Entry<Mes, ArrayList<Evento>> act;
        Evento act2;
        Iterator<Evento> itEve;

        while(itEnt.hasNext()){
            act = itEnt.next();

            itEve = act.getValue().iterator();
            while(itEve.hasNext()){
                act2 = itEve.next();
                if(act2.getDia() == dia){
                    for (int i = 0; i < meses.length; i++) {
                        if(act2.getMes() == meses[i]){
                            itEve.remove();
                            con++;
                            if(totalEventosEnMes(act.getKey())==0) itEnt.remove();
                        }
                    }
                }
            }
        }
        return con;
    }

    /**
     * Código para testear la clase CalendarioEventos
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
        System.out.println("Mes/es con más eventos "
                + calendario.mesesConMasEventos());

        System.out.println();
        System.out.println("Evento de mayor duración: "
                + calendario.eventoMasLargo());

        System.out.println();
        Mes[] meses = {Mes.FEBRERO, Mes.MARZO, Mes.MAYO, Mes.JUNIO};
        int dia = 6;
        System.out.println("Cancelar eventos de " + Arrays.toString(meses));
        int cancelados = calendario.cancelarEventos(meses, dia);
        System.out.println("Se han cancelado " + cancelados +
                " eventos");
        System.out.println();
        System.out.println("Después de cancelar eventos ...");
        System.out.println(calendario);
    }
}
