# Funciones PostgreSQL

## 1. Función: list_deportes


```sql
CREATE OR REPLACE FUNCTION list_deportes()
RETURNS TABLE(cod INT, nombre VARCHAR(20)) AS $$
BEGIN
    RETURN QUERY
    SELECT d.cod, d.nombre
    FROM deportes d
    ORDER BY d.cod;
END;
$$ LANGUAGE plpgsql;


```

## 2️. Función: list_deportistas_by_deporte

```sql
CREATE OR REPLACE FUNCTION list_deportistas_by_deporte(p_cod INT)
RETURNS TABLE(
    cod INT,
    nombre VARCHAR(50),
    cod_deporte INT
) AS $$
BEGIN
    RETURN QUERY
    SELECT d.cod, d.nombre, d.cod_deporte
    FROM deportistas d
    WHERE d.cod_deporte = p_cod
    ORDER BY d.nombre;
END;
$$ LANGUAGE plpgsql;


```
