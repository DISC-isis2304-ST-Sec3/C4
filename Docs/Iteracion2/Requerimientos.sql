create index consumos_fecha_idx on consumos(fecha);
create index reservas_fecha_idx on reservas(inicio);
create index reservas_hab_idx on reservas(idhabitacion);

-- REQ. 1
select * from (
select rownum as r, idhabitacion, coste_total from (
select sum(consumos.costo) as coste_total, habitaciones.idhabitacion from consumos
    inner join reservas on reservas.idreserva = consumos.id_reserva
    inner join habitaciones on habitaciones.idhabitacion = reservas.idhabitacion
where consumos.fecha >= add_months(CURRENT_DATE, -12)
group by habitaciones.idhabitacion
order by coste_total desc
)) where r between (:page - 1) * 20 and :page * 20;

-- REQ. 2
select servicios.id, servicios.nombre, count(consumos.id) as cantidad_consumos from servicios
    inner join consumos on consumos.id_servicio = servicios.id
where consumos.fecha between :date1 and :date2
group by servicios.id, servicios.nombre
order by cantidad_consumos desc
fetch first 20 rows only;

-- REQ. 3
select habitaciones.idhabitacion, ROUND((sum(reservas.cantidadpersonas) / sum(habitaciones.capacidad)) * 100, 2) as ocupacion, count(reservas.idreserva) as cantidad_reservas from habitaciones
    inner join reservas on reservas.idhabitacion = habitaciones.idhabitacion
where idhotel = :idhotel and reservas.inicio >= ADD_MONTHS(CURRENT_DATE, -12)
group by habitaciones.idhabitacion
order by ocupacion desc;

-- REQ. 11
select
    c.SEMANA,
    servicio_menos_consumo,
    menor_cantidad,
    servicio_mas_consumo,
    mayor_cantidad,
    habitacion_menos_pedida,
    menor_cantidad_reservas,
    habitacion_mas_pedida,
    mayor_cantidad_reservas
from (
    select
        semana,
        max(id_servicio) keep ( dense_rank first order by consumos_servicio) as servicio_menos_consumo,
        min(consumos_servicio) as menor_cantidad,
        max(id_servicio) keep ( dense_rank last order by consumos_servicio) as servicio_mas_consumo,
        max(consumos_servicio) as mayor_cantidad
    from (
        select TO_CHAR(consumos.fecha, 'WW') as SEMANA, consumos.id_servicio, count(consumos.id_servicio) as consumos_servicio from consumos
        where consumos.id_servicio IS NOT NULL and EXTRACT(year from consumos.fecha) = EXTRACT(year from CURRENT_DATE)
        group by TO_CHAR(consumos.fecha, 'WW'), consumos.id_servicio
    )
    group by semana
) c full join (
    select
        semana,
        max(idhabitacion) keep ( dense_rank first order by reservas_habitacion) as habitacion_menos_pedida,
        min(reservas_habitacion) as menor_cantidad_reservas,
        max(idhabitacion) keep ( dense_rank last order by reservas_habitacion) as habitacion_mas_pedida,
        max(reservas_habitacion) as mayor_cantidad_reservas
    from (
        select TO_CHAR(reservas.fecha_reserva, 'WW') as SEMANA, reservas.idhabitacion, count(reservas.idhabitacion) as reservas_habitacion from reservas
        where reservas.idhabitacion IS NOT NULL and EXTRACT(year from reservas.fecha_reserva) = EXTRACT(year from CURRENT_DATE)
        group by TO_CHAR(reservas.fecha_reserva, 'WW'), reservas.idhabitacion
    )
    group by semana
) h on c.SEMANA = h.SEMANA;
