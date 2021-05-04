--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: colaborador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.colaborador (
    carrera character varying(60),
    conclusion_servicio timestamp without time zone,
    inicio_servicio timestamp without time zone,
    responsable boolean NOT NULL,
    tiempo_prestado interval,
    usuario_id integer NOT NULL
);


ALTER TABLE public.colaborador OWNER TO postgres;

--
-- Name: documento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documento (
    documento_id integer NOT NULL,
    contenido bytea,
    fecha_creacion timestamp without time zone NOT NULL,
    nombre character varying(255) NOT NULL,
    colaborador_id integer
);


ALTER TABLE public.documento OWNER TO postgres;

--
-- Name: documento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.documento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documento_seq OWNER TO postgres;

--
-- Name: entrada_bitacora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entrada_bitacora (
    entrada_id integer NOT NULL,
    fecha date,
    hora_entrada time without time zone,
    hora_salida time without time zone,
    colaborador_id integer,
    materia_id integer,
    profesor_id integer
);


ALTER TABLE public.entrada_bitacora OWNER TO postgres;

--
-- Name: entrada_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.entrada_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.entrada_seq OWNER TO postgres;

--
-- Name: equipo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipo (
    equipo_id integer NOT NULL,
    descripcion text,
    imagen bytea,
    marca character varying(60) NOT NULL,
    numero_inventario integer,
    serial character varying(50),
    laboratorio_id integer
);


ALTER TABLE public.equipo OWNER TO postgres;

--
-- Name: equipo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.equipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.equipo_seq OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: incidente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.incidente (
    entrada_id integer NOT NULL,
    numero_incidente integer NOT NULL,
    descripcion text NOT NULL
);


ALTER TABLE public.incidente OWNER TO postgres;

--
-- Name: laboratorio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.laboratorio (
    laboratorio_id integer NOT NULL,
    edificio character varying(20),
    nombre character varying(50),
    salon character varying(20)
);


ALTER TABLE public.laboratorio OWNER TO postgres;

--
-- Name: laboratorio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.laboratorio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.laboratorio_seq OWNER TO postgres;

--
-- Name: materia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materia (
    materia_id integer NOT NULL,
    clave character varying(20) NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE public.materia OWNER TO postgres;

--
-- Name: materia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.materia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.materia_seq OWNER TO postgres;

--
-- Name: mensaje; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mensaje (
    mensaje_id uuid NOT NULL,
    contenido text,
    enviado timestamp without time zone,
    titulo character varying(255),
    enviado_por integer
);


ALTER TABLE public.mensaje OWNER TO postgres;

--
-- Name: profesor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profesor (
    responsable boolean NOT NULL,
    usuario_id integer NOT NULL
);


ALTER TABLE public.profesor OWNER TO postgres;

--
-- Name: solicitud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.solicitud (
    solicitud_id integer NOT NULL,
    costo numeric(19,2),
    descripcion text,
    estado character varying(30),
    folio character varying(255),
    tipo character varying(30),
    equipo_id integer,
    entrada_id integer,
    numero_incidente integer,
    solicitante_id integer
);


ALTER TABLE public.solicitud OWNER TO postgres;

--
-- Name: solicitud_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.solicitud_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.solicitud_seq OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    usuario_id integer NOT NULL,
    activo boolean NOT NULL,
    apellido_materno character varying(80),
    apellido_paterno character varying(80),
    confirmado boolean NOT NULL,
    correo character varying(100) NOT NULL,
    creado timestamp without time zone NOT NULL,
    matricula character varying(20) NOT NULL,
    nombre character varying(80) NOT NULL,
    password character varying(100) NOT NULL,
    telefono character varying(20)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_seq OWNER TO postgres;

--
-- Data for Name: colaborador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.colaborador (carrera, conclusion_servicio, inicio_servicio, responsable, tiempo_prestado, usuario_id) FROM stdin;
\.


--
-- Data for Name: documento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.documento (documento_id, contenido, fecha_creacion, nombre, colaborador_id) FROM stdin;
\.


--
-- Name: documento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.documento_seq', 1, false);


--
-- Data for Name: entrada_bitacora; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.entrada_bitacora (entrada_id, fecha, hora_entrada, hora_salida, colaborador_id, materia_id, profesor_id) FROM stdin;
\.


--
-- Name: entrada_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.entrada_seq', 1, false);


--
-- Data for Name: equipo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipo (equipo_id, descripcion, imagen, marca, numero_inventario, serial, laboratorio_id) FROM stdin;
\.


--
-- Name: equipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipo_seq', 1, false);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Data for Name: incidente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.incidente (entrada_id, numero_incidente, descripcion) FROM stdin;
\.


--
-- Data for Name: laboratorio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.laboratorio (laboratorio_id, edificio, nombre, salon) FROM stdin;
\.


--
-- Name: laboratorio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.laboratorio_seq', 1, false);


--
-- Data for Name: materia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.materia (materia_id, clave, nombre) FROM stdin;
\.


--
-- Name: materia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.materia_seq', 1, false);


--
-- Data for Name: mensaje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mensaje (mensaje_id, contenido, enviado, titulo, enviado_por) FROM stdin;
\.


--
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profesor (responsable, usuario_id) FROM stdin;
\.


--
-- Data for Name: solicitud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.solicitud (solicitud_id, costo, descripcion, estado, folio, tipo, equipo_id, entrada_id, numero_incidente, solicitante_id) FROM stdin;
\.


--
-- Name: solicitud_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.solicitud_seq', 1, false);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (usuario_id, activo, apellido_materno, apellido_paterno, confirmado, correo, creado, matricula, nombre, password, telefono) FROM stdin;
\.


--
-- Name: usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_seq', 1, false);


--
-- Name: colaborador colaborador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colaborador
    ADD CONSTRAINT colaborador_pkey PRIMARY KEY (usuario_id);


--
-- Name: documento documento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documento
    ADD CONSTRAINT documento_pkey PRIMARY KEY (documento_id);


--
-- Name: entrada_bitacora entrada_bitacora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada_bitacora
    ADD CONSTRAINT entrada_bitacora_pkey PRIMARY KEY (entrada_id);


--
-- Name: equipo equipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipo
    ADD CONSTRAINT equipo_pkey PRIMARY KEY (equipo_id);


--
-- Name: incidente incidente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidente
    ADD CONSTRAINT incidente_pkey PRIMARY KEY (entrada_id, numero_incidente);


--
-- Name: laboratorio laboratorio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laboratorio
    ADD CONSTRAINT laboratorio_pkey PRIMARY KEY (laboratorio_id);


--
-- Name: materia materia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT materia_pkey PRIMARY KEY (materia_id);


--
-- Name: mensaje mensaje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensaje
    ADD CONSTRAINT mensaje_pkey PRIMARY KEY (mensaje_id);


--
-- Name: profesor profesor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_pkey PRIMARY KEY (usuario_id);


--
-- Name: solicitud solicitud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (solicitud_id);


--
-- Name: usuario uk_2mlfr087gb1ce55f2j87o74t; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_2mlfr087gb1ce55f2j87o74t UNIQUE (correo);


--
-- Name: usuario uk_6r3pgk87k0cmatekasqghpka6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_6r3pgk87k0cmatekasqghpka6 UNIQUE (matricula);


--
-- Name: solicitud uk_86d1en2d1sofuetws0c9gx6vq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT uk_86d1en2d1sofuetws0c9gx6vq UNIQUE (folio);


--
-- Name: equipo uk_ri5kkdacjsmotaxj77cd6ckap; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipo
    ADD CONSTRAINT uk_ri5kkdacjsmotaxj77cd6ckap UNIQUE (serial);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usuario_id);


--
-- Name: equipo fk2ldmke9bg9xoo0515sucr89cm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipo
    ADD CONSTRAINT fk2ldmke9bg9xoo0515sucr89cm FOREIGN KEY (laboratorio_id) REFERENCES public.laboratorio(laboratorio_id);


--
-- Name: solicitud fk3g74vj4of878h91yh1oy9jgjg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fk3g74vj4of878h91yh1oy9jgjg FOREIGN KEY (equipo_id) REFERENCES public.equipo(equipo_id);


--
-- Name: entrada_bitacora fka3jfllopya7gf9nu6rh5e5d8n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada_bitacora
    ADD CONSTRAINT fka3jfllopya7gf9nu6rh5e5d8n FOREIGN KEY (colaborador_id) REFERENCES public.colaborador(usuario_id);


--
-- Name: solicitud fkaiavo5g0nrl4hhgko6slcuv4s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fkaiavo5g0nrl4hhgko6slcuv4s FOREIGN KEY (solicitante_id) REFERENCES public.usuario(usuario_id);


--
-- Name: profesor fkcmfic78v0qp2wu3c0n9jbqhox; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT fkcmfic78v0qp2wu3c0n9jbqhox FOREIGN KEY (usuario_id) REFERENCES public.usuario(usuario_id);


--
-- Name: colaborador fkf3rksclkninaywfgb0t59lss8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colaborador
    ADD CONSTRAINT fkf3rksclkninaywfgb0t59lss8 FOREIGN KEY (usuario_id) REFERENCES public.usuario(usuario_id);


--
-- Name: documento fkhp3gnw83celtudosew9oecg7h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documento
    ADD CONSTRAINT fkhp3gnw83celtudosew9oecg7h FOREIGN KEY (colaborador_id) REFERENCES public.colaborador(usuario_id);


--
-- Name: solicitud fkio1mfrg9qde2kieq4o5o7n6tg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fkio1mfrg9qde2kieq4o5o7n6tg FOREIGN KEY (entrada_id, numero_incidente) REFERENCES public.incidente(entrada_id, numero_incidente);


--
-- Name: entrada_bitacora fkmx4ng0ecsntl9hxhsqo43n0k7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada_bitacora
    ADD CONSTRAINT fkmx4ng0ecsntl9hxhsqo43n0k7 FOREIGN KEY (profesor_id) REFERENCES public.profesor(usuario_id);


--
-- Name: incidente fkndam9pfuq1wpsy4k8e93abtkv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidente
    ADD CONSTRAINT fkndam9pfuq1wpsy4k8e93abtkv FOREIGN KEY (entrada_id) REFERENCES public.entrada_bitacora(entrada_id);


--
-- Name: mensaje fknq0ba6ls2ft80g0u2ksdfre3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensaje
    ADD CONSTRAINT fknq0ba6ls2ft80g0u2ksdfre3 FOREIGN KEY (enviado_por) REFERENCES public.usuario(usuario_id);


--
-- Name: entrada_bitacora fkp3lck57fwf8hlasc8j04nc7ew; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada_bitacora
    ADD CONSTRAINT fkp3lck57fwf8hlasc8j04nc7ew FOREIGN KEY (materia_id) REFERENCES public.materia(materia_id);


--
-- PostgreSQL database dump complete
--

