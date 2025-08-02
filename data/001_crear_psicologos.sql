-- Script para crear psicólogos ficticios
-- Primero creamos un Director (requerido por la relación ManyToOne)

-- Insertar Director
INSERT INTO director (correo, nombre) VALUES 
('dr.martinez@techsolutions.com', 'Dr. Carlos Martínez López');

-- Insertar 4 Psicólogos ficticios
INSERT INTO psicologo (nombre, especialidad, telefono, director_id) VALUES 
('Dra. Ana García Rodríguez', 'Psicología Clínica', '555-0101', 1),
('Dr. Luis Fernando Pérez', 'Psicología Infantil', '555-0102', 1),
('Dra. María Elena Sánchez', 'Terapia Familiar', '555-0103', 1),
('Dr. Roberto Jiménez Cruz', 'Psicología Cognitivo-Conductual', '555-0104', 1);