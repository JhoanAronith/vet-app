CREATE TABLE citas(
    id BIGSERIAL PRIMARY KEY,
    fecha_hora TIMESTAMP NOT NULL,
    motivo TEXT NOT NULL,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    mascota_id BIGINT NOT NULL,
    CONSTRAINT fk_mascota_cita FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE
)