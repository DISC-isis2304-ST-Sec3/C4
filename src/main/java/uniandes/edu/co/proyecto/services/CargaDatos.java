package uniandes.edu.co.proyecto.services;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.proyecto.repositories.CuentasRepositorio;
import uniandes.edu.co.proyecto.repositories.HabitacionesRepositorio;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;
import uniandes.edu.co.proyecto.repositories.ReservasRepositorio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CargaDatos {
  @Autowired
  private HotelesRepositorio hotelesRepositorio;
  @Autowired
  private HabitacionesRepositorio habitacionesRepositorio;
  @Autowired
  private ReservasRepositorio reservasRepositorio;
  @Autowired
  private CuentasRepositorio cuentasRepositorio;
  @Autowired
  private EntityManager entityManager;

  private final Faker faker = new Faker();
  private final String formato = "YYYY/MM/DD HH24:MI:SS";
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  @Transactional
  public int cargar() {
    AtomicLong idGenerator = new AtomicLong(hotelesRepositorio.darSiguienteId());

    Calendar calendar = Calendar.getInstance();
    Calendar topCalendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, -2);
    topCalendar.add(Calendar.MONTH, 6);

    List<String> servicios = new LinkedList<>();
    List<String> hoteles = new LinkedList<>();
    List<String> habitaciones = new LinkedList<>();
    List<List<String>> cuentas = new LinkedList<>();
    List<List<String>> reservas = new LinkedList<>();
    List<List<String>> consumos = new LinkedList<>();

    long primerServicio = idGenerator.get();
    long ultimoServicio = primerServicio;

    for (int i = 0; i < 1000; i++) {
      ultimoServicio = idGenerator.getAndIncrement();
      String[] tipos = {"DIA", "HORA", "UNICO"};

      servicios.add(String.format(
          "(%d, '%s', %d, '%s', %d)",
          ultimoServicio,
          faker.commerce().productName().replace("'", ""),
          faker.number().numberBetween(1, 500),
          tipos[faker.random().nextInt(tipos.length)],
          faker.number().numberBetween(0, 150000)
      ));
    }

    String queryServicios = String.format("INSERT ALL INTO SERVICIOS (id, nombre, capacidad, tipo_cobro, cobro) values %s SELECT * FROM DUAL\n", String.join(" INTO servicios (id, nombre, capacidad, tipo_cobro, cobro) values ", servicios));
    Query sqlServicios = entityManager.createNativeQuery(queryServicios);
    sqlServicios.executeUpdate();
    System.out.println("SERVICIOS");

    servicios = null;

    for (int i = 0; i < 100; i++) {
      long hotel = idGenerator.getAndIncrement();

      hoteles.add(String.format(
          "(%d, '%s', %s, '%s')",
          hotel,
          faker.company().name().replace("'", ""),
          faker.number().numberBetween(1, 5),
          faker.address().country().replace("'", "")
      ));

      for (int j = 0; j < faker.number().numberBetween(5, 15); j++) {
        String[] tipos = {"Suite", "Multiple", "Económica", "Doble"};
        int capacidad = faker.number().numberBetween(1, 10);

        long habitacion = idGenerator.getAndIncrement();

        habitaciones.add(String.format(
           "(%d, '%s', %d, %d, %d)",
            habitacion,
            tipos[faker.random().nextInt(tipos.length)],
            capacidad,
            faker.number().numberBetween(10000, 100000),
            hotel
        ));

        List<String> cuentasHabitacion = new LinkedList<>();
        List<String> reservasHabitacion = new LinkedList<>();
        List<String> consumosHabitacion = new LinkedList<>();

        for (int k = 0; k < 90; k++) {
          long cuenta = idGenerator.getAndIncrement();
          cuentasHabitacion.add(String.format("(%d, '%s')", cuenta, "false"));

          Date fecha_reserva = faker.date().between(calendar.getTime(), new Date());
          Date fecha_inicio = faker.date().between(fecha_reserva, topCalendar.getTime());
          topCalendar.add(Calendar.DATE, 2);
          Date min = topCalendar.getTime();
          topCalendar.add(Calendar.DATE, 5);
          Date max = topCalendar.getTime();
          Date fecha_fin = faker.date().between(min, max);
          topCalendar.add(Calendar.DATE, -7);

          long reserva = idGenerator.getAndIncrement();

          reservasHabitacion.add(String.format(
             "(%d, TO_TIMESTAMP('%s', '"+formato+"'), TO_TIMESTAMP('%s', '"+formato+"'), TO_TIMESTAMP('%s', '"+formato+"'), %d, %d, %d)",
              reserva,
              dateFormat.format(fecha_reserva),
              dateFormat.format(fecha_inicio),
              dateFormat.format(fecha_fin),
              faker.number().numberBetween(1, capacidad),
              habitacion,
              cuenta
          ));

          for (int l = 0; l < faker.number().numberBetween(1, 5); l++) {
            consumosHabitacion.add(String.format(
                "(%d, TO_TIMESTAMP('%s', '"+formato+"'), %d, %d, %d)",
                idGenerator.getAndIncrement(),
                dateFormat.format(faker.date().between(fecha_inicio, fecha_fin)),
                faker.number().numberBetween(0, 1000000),
                reserva,
                faker.number().numberBetween(primerServicio, ultimoServicio)
            ));
          }
        }
        cuentas.add(cuentasHabitacion);
        reservas.add(reservasHabitacion);
        consumos.add(consumosHabitacion);
      }
    }

    String queryHoteles = String.format("INSERT ALL INTO HOTELES (IDHOTEL, NOMBRE, ESTRELLAS, PAIS) values %s SELECT * FROM DUAL\n", String.join(" INTO HOTELES (IDHOTEL, NOMBRE, ESTRELLAS, PAIS) values ", hoteles));
    Query sqlHoteles = entityManager.createNativeQuery(queryHoteles);
    sqlHoteles.executeUpdate();
    System.out.println("HOTELES");

    String queryHabitaciones = String.format("INSERT ALL INTO HABITACIONES (IDHABITACION, TIPO, CAPACIDAD, COSTO, IDHOTEL) values %s SELECT * FROM DUAL\n", String.join(" INTO HABITACIONES (IDHABITACION, TIPO, CAPACIDAD, COSTO, IDHOTEL) values ", habitaciones));
    Query sqlHabitaciones = entityManager.createNativeQuery(queryHabitaciones);
    sqlHabitaciones.executeUpdate();
    System.out.println("HABITACIONES");

    for (List<String> cuentasHab: cuentas) {
      String queryCuentas = String.format("INSERT ALL INTO CUENTAS (IDPAGO, PAGADO) values %s SELECT * FROM DUAL\n", String.join(" INTO CUENTAS (IDPAGO, PAGADO) values ", cuentasHab));
      Query sqlCuentas = entityManager.createNativeQuery(queryCuentas);
      sqlCuentas.executeUpdate();
    }
    System.out.println("CUENTAS");

    for (List<String> reservasHab: reservas) {
      String queryReservas = String.format("INSERT ALL INTO RESERVAS (IDRESERVA, FECHA_RESERVA, INICIO, FIN, CANTIDADPERSONAS, IDHABITACION, IDPAGO) values %s SELECT * FROM DUAL\n", String.join(" INTO RESERVAS (IDRESERVA, FECHA_RESERVA, INICIO, FIN, CANTIDADPERSONAS, IDHABITACION, IDPAGO) values ", reservasHab));
      Query sqlReservas = entityManager.createNativeQuery(queryReservas);
      sqlReservas.executeUpdate();
    }
    System.out.println("RESERVAS");

    for (List<String> consumosHab: consumos) {
      String queryConsumos = String.format("INSERT ALL INTO CONSUMOS (ID, FECHA, COSTO, ID_RESERVA, ID_SERVICIO) values %s SELECT * FROM DUAL\n", String.join(" INTO CONSUMOS (ID, FECHA, COSTO, ID_RESERVA, ID_SERVICIO) values ", consumosHab));
      Query sqlConsumos = entityManager.createNativeQuery(queryConsumos);
      sqlConsumos.executeUpdate();
    }
    System.out.println("CONSUMOS");

    Query querySecuencia = entityManager.createNativeQuery("alter sequence hoteles_sequence restart start with " + idGenerator.incrementAndGet());
    querySecuencia.executeUpdate();
    System.out.println("SECUENCIA");
    return 1;
  }
}
