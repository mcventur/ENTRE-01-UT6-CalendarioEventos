public class Test
{
    public static void main(String[] args) {
        Evento evento1 = new Evento("consulta dentista en Burlada",  "14/09/2021", "16:30 ", "17:30 ");

        System.out.println("evento1.getDia() = " + evento1.getDia());
        System.out.println("evento1.getMes() = " + evento1.getMes());
        System.out.println("evento1.getDuracion() = " + evento1.getDuracion());

        Evento evento2 = new Evento("Comida restaurante europa", "22/05/2021", "12:00","17:00");

        System.out.println("evento1.getDia() = " + evento2.getDia());
        System.out.println("evento1.getMes() = " + evento2.getMes());
        System.out.println("evento1.getDuracion() = " + evento2.getDuracion());
    }
}
