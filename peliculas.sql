DROP TABLE IF EXISTS "public"."director", "public"."genero", "public"."pelicula", "public"."pelicula_genero";

CREATE TABLE "public"."director" (
    "id_director" serial NOT NULL,
    "nombres" character varying(100) NOT NULL,
    "apellidos" character varying(100) NOT NULL,
    "fecha_nacimiento" date,
    "nacionalidad" character varying(50),
    CONSTRAINT "director_pkey" PRIMARY KEY ("id_director")
)
WITH (oids = false);

INSERT INTO "director" ("id_director", "nombres", "apellidos", "fecha_nacimiento", "nacionalidad") VALUES
(1,	'Christopher',	'Nolan',	'1970-07-30',	'Británico'),
(2,	'Quentin',	'Tarantino',	'1963-03-27',	'Estadounidense'),
(3,	'Guillermo',	'del Toro',	'1964-10-09',	'Mexicano'),
(4,	'Denis',	'Villeneuve',	'1967-10-03',	'Canadiense'),
(5,	'Greta',	'Gerwig',	'1983-08-04',	'Estadounidense'),
(6,	'Bong',	'Joon-ho',	'1969-09-14',	'Surcoreano'),
(7,	'Pedro',	'Almodóvar',	'1949-09-25',	'Español'),
(8,	'Hayao',	'Miyazaki',	'1941-01-05',	'Japonés'),
(9,	'Steven',	'Spielberg',	'1946-12-18',	'Estadounidense'),
(10,	'Martin',	'Scorsese',	'1942-11-17',	'Estadounidense'),
(11,	'Alfonso',	'Cuarón',	'1961-11-28',	'Mexicano'),
(12,	'James',	'Cameron',	'1954-08-16',	'Canadiense'),
(13,	'Ridley',	'Scott',	'1937-11-30',	'Británico');

CREATE TABLE "public"."genero" (
    "id_genero" serial NOT NULL,
    "nombre" character varying(50) NOT NULL,
    CONSTRAINT "genero_pkey" PRIMARY KEY ("id_genero")
)
WITH (oids = false);

CREATE UNIQUE INDEX genero_nombre_key ON public.genero USING btree (nombre);

INSERT INTO "genero" ("id_genero", "nombre") VALUES
(1,	'Acción'),
(2,	'Ciencia Ficción'),
(3,	'Drama'),
(4,	'Comedia'),
(5,	'Suspense'),
(6,	'Terror'),
(7,	'Fantasía'),
(8,	'Animación'),
(9,	'Crimen'),
(10,	'Romance'),
(11,	'Aventura'),
(12,	'Misterio'),
(13,	'Bélico'),
(14,	'Musical'),
(15,	'Biografía');

CREATE TABLE "public"."pelicula" (
    "id_pelicula" serial NOT NULL,
    "titulo" character varying(200) NOT NULL,
    "fecha_estreno" date,
    "calificacion" numeric(3,1),
    "id_director" integer NOT NULL,
    CONSTRAINT "pelicula_pkey" PRIMARY KEY ("id_pelicula"),
    CONSTRAINT "chk_pelicula_calificacion" CHECK (((calificacion >= 0.0) AND (calificacion <= 10.0)))
)
WITH (oids = false);

CREATE INDEX idx_pelicula_director ON public.pelicula USING btree (id_director);

INSERT INTO "pelicula" ("id_pelicula", "titulo", "fecha_estreno", "calificacion", "id_director") VALUES
(1,	'Inception',	'2010-07-16',	8.8,	1),
(2,	'Interstellar',	'2014-11-07',	8.7,	1),
(3,	'Pulp Fiction',	'1994-10-14',	8.9,	2),
(4,	'Kill Bill: Vol. 1',	'2003-10-10',	8.2,	2),
(5,	'El laberinto del fauno',	'2006-10-11',	8.2,	3),
(6,	'La forma del agua',	'2017-12-01',	7.3,	3),
(7,	'Dune',	'2021-10-22',	8.0,	4),
(8,	'Blade Runner 2049',	'2017-10-06',	8.0,	4),
(9,	'Barbie',	'2023-07-21',	6.8,	5),
(10,	'Parásitos',	'2019-05-30',	8.5,	6),
(11,	'Todo sobre mi madre',	'1999-04-08',	7.8,	7),
(12,	'El viaje de Chihiro',	'2001-07-20',	8.6,	8),
(13,	'Oppenheimer',	'2023-07-21',	8.9,	1),
(14,	'Parque Jurásico',	'1993-06-11',	8.2,	9),
(15,	'La lista de Schindler',	'1993-12-15',	9.0,	9),
(16,	'Uno de los nuestros (Goodfellas)',	'1990-09-19',	8.7,	10),
(17,	'Taxi Driver',	'1976-02-08',	8.2,	10),
(18,	'Roma',	'2018-11-21',	7.7,	11),
(19,	'Hijos de los hombres',	'2006-12-25',	7.9,	11),
(20,	'Titanic',	'1997-12-19',	7.9,	12),
(21,	'Avatar',	'2009-12-18',	7.9,	12),
(22,	'Gladiator',	'2000-05-05',	8.5,	13),
(23,	'Alien, el octavo pasajero',	'1979-05-25',	8.5,	13);

CREATE TABLE "public"."pelicula_genero" (
    "id_pelicula" integer NOT NULL,
    "id_genero" integer NOT NULL,
    CONSTRAINT "pelicula_genero_pkey" PRIMARY KEY ("id_pelicula", "id_genero")
)
WITH (oids = false);

CREATE INDEX idx_pelicula_genero_genero ON public.pelicula_genero USING btree (id_genero);

INSERT INTO "pelicula_genero" ("id_pelicula", "id_genero") VALUES
(1,	1),
(1,	2),
(1,	5),
(2,	2),
(2,	3),
(3,	9),
(3,	3),
(4,	1),
(4,	9),
(5,	7),
(5,	3),
(5,	6),
(6,	7),
(6,	3),
(6,	10),
(7,	2),
(7,	1),
(7,	11),
(8,	2),
(8,	3),
(8,	12),
(9,	4),
(9,	7),
(10,	3),
(10,	5),
(10,	9),
(11,	3),
(12,	8),
(12,	7),
(12,	11),
(13,	3),
(13,	15),
(13,	13),
(14,	2),
(14,	11),
(15,	3),
(15,	15),
(15,	13),
(16,	9),
(16,	3),
(16,	15),
(17,	3),
(17,	9),
(18,	3),
(19,	2),
(19,	3),
(19,	1),
(20,	3),
(20,	10),
(21,	2),
(21,	11),
(21,	1),
(22,	1),
(22,	3),
(22,	11),
(23,	2),
(23,	6),
(23,	5);

ALTER TABLE ONLY "public"."pelicula" ADD CONSTRAINT "fk_pelicula_director" FOREIGN KEY (id_director) REFERENCES "public".director(id_director) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY "public"."pelicula_genero" ADD CONSTRAINT "fk_pg_genero" FOREIGN KEY (id_genero) REFERENCES "public".genero(id_genero) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY "public"."pelicula_genero" ADD CONSTRAINT "fk_pg_pelicula" FOREIGN KEY (id_pelicula) REFERENCES "public".pelicula(id_pelicula) ON UPDATE CASCADE ON DELETE CASCADE;
