public class Test
{
    public static void main(String[] args) {
        Evento evento1 = new Evento("consulta dentista en Burlada",  "14/09/2021", "16:30 ", "17:30 ");
        
        System.out.println("evento1.getNombre() = " + evento1.getNombre());
        System.out.println("evento1.getFecha() = " + evento1.getFecha());
        System.out.println("evento1.getHoraInicio() = " + evento1.getHoraInicio());
        System.out.println("evento1.getHoraFin() = " + evento1.getHoraFin());
    }
}
