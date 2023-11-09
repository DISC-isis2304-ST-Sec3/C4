create index consumos_fecha_idx on consumos(fecha);
create index reservas_fecha_idx on reservas(inicio);
create index reservas_hab_idx on reservas(idhabitacion);
CREATE INDEX idx_servicios_tipo_cobro ON servicios(tipo_cobro);
CREATE INDEX idx_reserva_servicios_horario ON reserva_servicios(horario);
CREATE INDEX idx_servicios_cobro ON servicios(cobro);
CREATE INDEX consumos_fecha_costo ON consumos(fecha, costo);
CREATE INDEX reservas_fecha ON reservas(fecha_reserva);
CREATE INDEX idx_horario ON reserva_servicios(horario);

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

-- REQ. 4
SELECT s.id, s.nombre, s.capacidad, s.tipo_cobro, s.cobro
FROM servicios s, reserva_servicios rs
WHERE
    s.cobro BETWEEN 50 AND 200
    AND rs.horario BETWEEN TO_DATE('2022-11-01', 'YYYY-MM-DD') AND TO_DATE('2022-11-10', 'YYYY-MM-DD')
    AND s.tipo_cobro = 'DIA';

-- REQ. 5
select usuarios.id, usuarios.nombre, consumos.costo
from usuarios join consumos on consumos.id_usuario = usuarios.id
where consumos.fecha between add_months(current_date, -12) and current_date and usuarios.id = 1;

-- REQ. 6
(SELECT 'Mayor Ocupación' AS tipo, fecha_reserva AS fecha, COUNT(*) AS valor
FROM reservas
GROUP BY fecha_reserva
ORDER BY COUNT(*) DESC
FETCH FIRST 1 ROW ONLY)
UNION
(SELECT 'Mayores Ingresos' AS tipo, fecha, SUM(costo) AS valor
FROM consumos
GROUP BY fecha
ORDER BY SUM(costo) DESC
FETCH FIRST 1 ROW ONLY)
UNION
(SELECT 'Menor Demanda' AS tipo, fecha_reserva AS fecha, COUNT(*) AS valor
FROM reservas
GROUP BY fecha_reserva
ORDER BY COUNT(*) ASC
FETCH FIRST 1 ROW ONLY);

-- REQ. 7
SELECT u.nombre AS nombre_cliente,  res.inicio AS fecha_consumo,  COUNT(*) AS veces_consumido 
FROM usuarios u
JOIN registros reg ON u.numDocumento = reg.docpersona 
JOIN reservas res ON reg.idreserva = res.idreserva
JOIN reserva_servicios ON res.idreserva = reserva_servicios.id
LEFT JOIN servicios s ON reserva_servicios.id_spa = s.id OR reserva_servicios.id_salon = s.id 
WHERE res.inicio BETWEEN ADD_MONTHS(CURRENT_DATE, -12) AND CURRENT_DATE  AND s.id IS NULL 
GROUP BY u.nombre, res.inicio
ORDER BY u.nombre ASC;

-- REQ. 8
SELECT s.id, s.nombre, s.capacidad, s.tipo_cobro, s.cobro
FROM servicios s
WHERE s.id NOT IN (
    SELECT rs.id_salon
    FROM reserva_servicios rs
    WHERE rs.horario >= ADD_MONTHS(SYSDATE, -12) 
    GROUP BY rs.id_salon
    HAVING COUNT(DISTINCT TO_CHAR(rs.horario, 'IW')) >= 3);

-- REQ. 9
select usuarios.id, count(consumos.id_servicio) from usuarios
inner join consumos on consumos.id_usuario = usuarios.id
where consumos.fecha between add_months(current_date, -12) and current_date and consumos.id_servicio = 1
having count(consumos.id_servicio)>0
group by usuarios.id;

-- REQ. 10
select usuarios.id, count(consumos.id_servicio) from usuarios
inner join consumos on consumos.id_usuario = usuarios.id
where consumos.fecha between add_months(current_date, -12) and current_date and consumos.id_servicio = 1
having count(consumos.id_servicio)=0
group by usuarios.id;

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
