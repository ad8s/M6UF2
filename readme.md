# Funciones PostgreSQL

## 1. Función: list_deportes


```sql
CREATE OR REPLACE FUNCTION list_deportes()
RETURNS TABLE(cod INT, nombre TEXT) AS $$
BEGIN
    RETURN QUERY
    SELECT cod, nombre
    FROM deportes
    ORDER BY cod;
END;
$$ LANGUAGE plpgsql;

```

## 2️. Función: list_deportistas_by_deporte

```sql
CREATE OR REPLACE FUNCTION list_deportistas_by_deporte(p_cod INT)
RETURNS TABLE(deportista_nombre TEXT, deportista_cod INT, deporte_nombre TEXT) AS $$
BEGIN
    RETURN QUERY
    SELECT d.nombre, d.cod, dp.nombre
    FROM deportistas d
    JOIN deportes dp ON d.cod_deporte = dp.cod
    WHERE dp.cod = p_cod
    ORDER BY d.nombre;
END;
$$ LANGUAGE plpgsql;
```
