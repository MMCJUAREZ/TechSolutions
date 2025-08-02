-- Script para crear pacientes ficticios
-- Los pacientes serán asignados a los psicólogos creados previamente (IDs 1-4)

-- Insertar 4 Pacientes ficticios
INSERT INTO paciente (nombre, telefono, correo, edad, psicologo_id) VALUES 
('María González López', '555-1001', 'maria.gonzalez@email.com', 28, 1),
('Carlos Mendoza Rivera', '555-1002', 'carlos.mendoza@email.com', 34, 2),
('Ana Patricia Ruiz', '555-1003', 'ana.ruiz@email.com', 22, 3),
('Jorge Alberto Castro', '555-1004', 'jorge.castro@email.com', 41, 4);