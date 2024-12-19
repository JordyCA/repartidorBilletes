
CREATE TABLE denominacion (
    id INT PRIMARY KEY,
    tipo INT NOT NULL,
    cantidad INT NOT NULL,
    denominacion DECFLOAT NOT NULL
);

CREATE TABLE movimiento (
	id INT PRIMARY KEY,
	tipo CHARACTER NOT NULL, 
	cantidad DECFLOAT NOT NULL
);