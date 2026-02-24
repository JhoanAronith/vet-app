CREATE TABLE mascotas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero VARCHAR(20),
    peso_actual DECIMAL(5,2),
    raza_id INTEGER NOT NULL,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_raza FOREIGN KEY (raza_id) REFERENCES razas(id),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
)