CREATE TABLE visitas (
    id BIGSERIAL PRIMARY KEY,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    temperatura DECIMAL(4,2),
    peso_en_visita DECIMAL(5,2),
    diagnostico TEXT,
    mascota_id BIGINT NOT NULL,
    CONSTRAINT fk_mascota_visita FOREIGN KEY (mascota_id) REFERENCES mascotas(id)
);

CREATE TABLE recetas (
    id BIGSERIAL PRIMARY KEY,
    visita_id BIGINT NOT NULL UNIQUE,
    indicaciones_generales TEXT,
    CONSTRAINT fk_visita_receta FOREIGN KEY (visita_id) REFERENCES visitas(id)
);

CREATE TABLE detalle_receta (
    id BIGSERIAL PRIMARY KEY,
    receta_id BIGINT NOT NULL,
    medicamento_id INTEGER NOT NULL,
    dosis VARCHAR(255),
    CONSTRAINT fk_receta_detalle FOREIGN KEY (receta_id) REFERENCES recetas(id),
    CONSTRAINT fk_medicamento_detalle FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)
);

CREATE TABLE detalle_visita (
    id BIGSERIAL PRIMARY KEY,
    visita_id BIGINT NOT NULL,
    servicio_id INTEGER,
    medicamento_id INTEGER,
    cantidad INTEGER NOT NULL DEFAULT 1,
    precio_aplicado DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_visita_factura FOREIGN KEY (visita_id) REFERENCES visitas(id),
    CONSTRAINT fk_servicio_factura FOREIGN KEY (servicio_id) REFERENCES servicios(id),
    CONSTRAINT fk_medicamento_factura FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)
);