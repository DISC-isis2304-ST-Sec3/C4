create sequence hoteles_sequence start with 1 increment by 1;

CREATE TABLE hoteles (
    idhotel   INTEGER NOT NULL,
    nombre    VARCHAR2(255 byte) NOT NULL,
    estrellas INTEGER NOT NULL,
    pais      VARCHAR2(255 byte) NOT NULL);


ALTER TABLE hoteles ADD CONSTRAINT hoteles_pk PRIMARY KEY ( idhotel );

CREATE TABLE ADMINISTRATIVO (
    rol          VARCHAR2(40 CHAR) NOT NULL,
    id_hotel integer NOT NULL,
    usuario_id   INTEGER NOT NULL
);

CREATE UNIQUE INDEX adiministrativo__idx ON
    ADMINISTRATIVO (
        usuario_id
    ASC );

ALTER TABLE ADMINISTRATIVO ADD CONSTRAINT adiministrativo_pk PRIMARY KEY ( usuario_id );

CREATE TABLE descuento (
    porcentaje          INTEGER NOT NULL,
    producto_idproducto INTEGER NOT NULL
);

ALTER TABLE descuento ADD CONSTRAINT descuento_pk PRIMARY KEY ( porcentaje );

CREATE TABLE descuentoplan (
    plan_tipo            VARCHAR2(50 CHAR) NOT NULL,
    descuento_porcentaje INTEGER NOT NULL
);

ALTER TABLE descuentoplan ADD CONSTRAINT descuentoplan_pk PRIMARY KEY ( plan_tipo,
                                                                        descuento_porcentaje );

CREATE TABLE plan (
    tipo                  VARCHAR2(50 CHAR) NOT NULL,
    costo                 INTEGER NOT NULL,
    duracion              INTEGER NOT NULL,
    promocion_idpromocion INTEGER NOT NULL
);

CREATE UNIQUE INDEX plan__idx ON
    plan (
        promocion_idpromocion
    ASC );

ALTER TABLE plan ADD CONSTRAINT plan_pk PRIMARY KEY ( tipo );

CREATE TABLE producto (
    idproducto    INTEGER NOT NULL,
    todoincluido  CHAR(1) NOT NULL,
    nombre        VARCHAR2(50 CHAR) NOT NULL,
    limite        INTEGER NOT NULL,
    costo         INTEGER NOT NULL,
    tienda_nombre VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( idproducto );

CREATE TABLE promocion (
    vigencia    DATE,
    descripcion VARCHAR2(100 CHAR) NOT NULL,
    rebaja      FLOAT(2) NOT NULL,
    idpromocion INTEGER NOT NULL,
    plan_tipo   VARCHAR2(50 CHAR) NOT NULL
);

CREATE UNIQUE INDEX promocion__idx ON
    promocion (
        plan_tipo
    ASC );

ALTER TABLE promocion ADD CONSTRAINT promocion_pk PRIMARY KEY ( idpromocion );

CREATE TABLE reserva (
    id           INTEGER NOT NULL,
    inicio       DATE NOT NULL,
    fin          DATE NOT NULL,
    plan         VARCHAR2(50 CHAR) NOT NULL,
    cantpersonas INTEGER NOT NULL,
    usuario_id   INTEGER NOT NULL,
    plan_tipo    VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE reserva ADD CONSTRAINT reserva_pk PRIMARY KEY ( id );

CREATE TABLE tienda (
    tipo                           VARCHAR2(20 CHAR) NOT NULL,
    nombre                         VARCHAR2(50 CHAR) NOT NULL,
    capacidad                      INTEGER NOT NULL,
    id_hotel                   INTEGER NOT NULL,
    tiendaconsumible_tienda_nombre VARCHAR2(50 CHAR) NOT NULL
);

-- Error - Index Tienda__IDX has no columns

ALTER TABLE tienda ADD CONSTRAINT tienda_pk PRIMARY KEY ( nombre );

CREATE TABLE tiendaconsumible (
    estilo        VARCHAR2(4000) NOT NULL,
    tienda_nombre VARCHAR2(50 CHAR) NOT NULL
);

CREATE UNIQUE INDEX tiendaconsumible__idx ON
    tiendaconsumible (
        tienda_nombre
    ASC );

ALTER TABLE tiendaconsumible ADD CONSTRAINT tiendaconsumible_pk PRIMARY KEY ( tienda_nombre );

CREATE TABLE usuario (
    id                         INTEGER NOT NULL,
    nombre                     VARCHAR2(50 CHAR) NOT NULL,
    numdocumento               INTEGER NOT NULL,
    nickname                   VARCHAR2(25 CHAR) NOT NULL,
    contraseña                 VARCHAR2(20 CHAR) NOT NULL,
    adiministrativo_usuario_id INTEGER NOT NULL
);

-- Error - Index Usuario__IDX has no columns

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

ALTER TABLE ADMINISTRATIVO
    ADD CONSTRAINT adiministrativo_hotel_fk FOREIGN KEY ( id_hotel )
        REFERENCES hoteles ( idhotel );

ALTER TABLE ADMINISTRATIVO
    ADD CONSTRAINT adiministrativo_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE descuento
    ADD CONSTRAINT descuento_producto_fk FOREIGN KEY ( producto_idproducto )
        REFERENCES producto ( idproducto );

ALTER TABLE descuentoplan
    ADD CONSTRAINT descuentoplan_descuento_fk FOREIGN KEY ( descuento_porcentaje )
        REFERENCES descuento ( porcentaje );

ALTER TABLE descuentoplan
    ADD CONSTRAINT descuentoplan_plan_fk FOREIGN KEY ( plan_tipo )
        REFERENCES plan ( tipo );

ALTER TABLE plan
    ADD CONSTRAINT plan_promocion_fk FOREIGN KEY ( promocion_idpromocion )
        REFERENCES promocion ( idpromocion );

ALTER TABLE producto
    ADD CONSTRAINT producto_tienda_fk FOREIGN KEY ( tienda_nombre )
        REFERENCES tienda ( nombre );

ALTER TABLE promocion
    ADD CONSTRAINT promocion_plan_fk FOREIGN KEY ( plan_tipo )
        REFERENCES plan ( tipo );

ALTER TABLE reserva
    ADD CONSTRAINT reserva_plan_fk FOREIGN KEY ( plan_tipo )
        REFERENCES plan ( tipo );

ALTER TABLE reserva
    ADD CONSTRAINT reserva_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE tienda
    ADD CONSTRAINT tienda_hotel_fk FOREIGN KEY ( id_hotel )
        REFERENCES hoteles ( idhotel );

ALTER TABLE tienda
    ADD CONSTRAINT tienda_tiendaconsumible_fk FOREIGN KEY ( tiendaconsumible_tienda_nombre )
        REFERENCES tiendaconsumible ( tienda_nombre );

ALTER TABLE tiendaconsumible
    ADD CONSTRAINT tiendaconsumible_tienda_fk FOREIGN KEY ( tienda_nombre )
        REFERENCES tienda ( nombre );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_adiministrativo_fk FOREIGN KEY ( adiministrativo_usuario_id )
        REFERENCES ADMINISTRATIVO ( usuario_id );

--CREACIÓN TABLA HABITACIONES
CREATE TABLE habitaciones (
    idhabitacion    INTEGER NOT NULL,
    tipo            VARCHAR2 (20) NOT NULL,
    capacidad       INTEGER NOT NULL,
    costo           INTEGER NOT NULL,
    idhotel         INTEGER NOT NULL);

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( idhabitacion );
ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_hoteles_fk FOREIGN KEY ( idhotel ) REFERENCES hoteles ( idhotel );

--CREACIÓN TABLA DOTACIONES
CREATE TABLE dotaciones (
    iddotacion     INTEGER NOT NULL,
    nombre         VARCHAR2(20) NOT NULL,
    cantidad       INTEGER NOT NULL,
    costoadicional INTEGER NOT NULL);

ALTER TABLE dotaciones ADD CONSTRAINT dotaciones_pk PRIMARY KEY ( iddotacion );


--CREACIÓN TABLA HDOTACIONES
CREATE TABLE hdotaciones (
    idhabitacion   INTEGER NOT NULL,
    iddotacion     INTEGER NOT NULL);

ALTER TABLE hdotaciones ADD CONSTRAINT hdotaciones PRIMARY KEY (idhabitacion);
ALTER TABLE hdotaciones ADD CONSTRAINT hdotaciones_fk FOREIGN KEY ( iddotacion )REFERENCES dotaciones ( iddotacion );
ALTER TABLE hdotaciones ADD CONSTRAINT idhdotaciones_fk FOREIGN KEY ( idhabitacion )REFERENCES habitaciones ( idhabitacion );

--CREACIÓN TABLA CUENTAS

CREATE TABLE cuentas (
    idpago INTEGER NOT NULL,
    pagado  VARCHAR2(20)NOT NULL);

ALTER TABLE cuentas ADD CONSTRAINT cuentas_pk PRIMARY KEY ( idpago );

--CREACIÓN TABLA RESERVAS
CREATE TABLE reservas (
    idreserva                 INTEGER NOT NULL,
    inicio                    DATE NOT NULL,
    fin                       DATE NOT NULL,
    cantidadpersonas          INTEGER NOT NULL,
    idhabitacion              INTEGER NOT NULL,
    idpago                    INTEGER NOT NULL);

ALTER TABLE reservas ADD CONSTRAINT reservas_pk PRIMARY KEY ( idreserva );
ALTER TABLE reservas ADD CONSTRAINT reservas_cuentas_fk FOREIGN KEY ( idpago )REFERENCES cuentas ( idpago );
ALTER TABLE reservas ADD CONSTRAINT reservas_habitaciones_fk FOREIGN KEY ( idhabitacion )REFERENCES habitaciones ( idhabitacion );


--CREACIÓN TABLA REGISTROS
CREATE TABLE registros (
    docpersona         INTEGER NOT NULL,
    capacidad          INTEGER NOT NULL,
    idreserva          INTEGER NOT NULL);

ALTER TABLE registros ADD CONSTRAINT registros_pk PRIMARY KEY ( docpersona );
ALTER TABLE registros ADD CONSTRAINT registros_reservas_fk FOREIGN KEY ( idreserva )REFERENCES reservas ( idreserva );

create table servicios (
    id number,
    nombre varchar(255 byte),
    capacidad number,
    tipo_cobro varchar(20),
    cobro number,
    constraint pk_servicios primary key (id),
    constraint ck_servicios_capacidad check (capacidad > 0),
    constraint ck_servicios_tipo_cobro check (tipo_cobro in ('DIA', 'HORA', 'UNICO')),
    constraint ck_servicios_cobro check (cobro >= 0)
);

create table piscinas (
    id number,
    profundidad number not null,
    horario_abre date not null,
    horario_cierra date not null,
    id_servicio number not null,
    constraint pk_piscinas primary key (id),
    constraint ck_piscinas_profundidad check ( profundidad >= 0 ),
    constraint fk_piscinas_servicio foreign key (id_servicio) references servicios
);

alter table piscinas add constraint unique_servicio_piscina unique(id_servicio);

create table gimnasios (
    id number,
    horario_abre date not null,
    horario_cierra date not null,
    id_servicio number not null unique,
    constraint pk_gimnasios primary key (id),
    constraint fk_gimnasios_servicio foreign key (id_servicio) references servicios
);

create table salones (
    id number,
    tipo varchar(255 byte) not null,
    costo number not null,
    capacidad number not null,
    constraint pk_salones primary key (id),
    constraint ck_salones_tipo check ( tipo in ('REUNIONES', 'CONFERENCIAS') ),
    constraint ck_salones_costo check ( costo >= 0 ),
    constraint ck_salones_capacidad check ( capacidad > 0 )
);

create table equipos (
    id number,
    tipo_equipo varchar(255 byte) not null,
    costo_adicional number not null,
    constraint pk_equipos primary key (id),
    constraint ck_equipos_costo check (costo_adicional >= 0)
);

create table equipos_gimnasios (
    id_equipo number not null,
    id_gimnasio number not null,
    constraint pk_equipos_gimnasio primary key (id_equipo, id_gimnasio),
    constraint fk_equipos_gym_equipo foreign key (id_equipo) references equipos,
    constraint fk_equipos_gym_gimnasio foreign key (id_gimnasio) references gimnasios
);

create table equipos_salones (
    id_equipo number not null,
    id_salon number not null,
    constraint pk_equipos_salones primary key (id_equipo, id_salon),
    constraint fk_equipos_salon_equipo foreign key (id_equipo) references equipos,
    constraint fk_equipos_salon_salon foreign key (id_salon) references salones
);

create table spas (
    id number not null,
    costo number not null,
    descripcion varchar(255 byte) not null,
    id_hotel number not null,
    constraint pk_spas primary key (id),
    constraint fk_spas_hotel foreign key (id_hotel) references hoteles,
    constraint ck_spas_costo check ( costo >= 0 )
);

create table reserva_servicios (
    id number not null,
    horario date not null,
    duracion number not null,
    id_spa number,
    id_salon number,
    constraint pk_reservas_servicios primary key (id),
    constraint fk_reservas_servicios_spa foreign key (id_spa) references spas,
    constraint fk_reservas_servicios_salon foreign key (id_salon) references salones,
    constraint ck_reservas_servicios_duracion check ( duracion > 0 )
);

commit;