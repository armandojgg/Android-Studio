<?php

require_once('datos/ConexionBD.php');
require_once('utilidades/ExcepcionApi.php');
require_once('datos/mensajes.php');

// Esta clase representa un controlador para las peliculas
class ControladorPeliculas
{
    // Nombres de la tabla y de los atributos
    const NOMBRE_TABLA = "peliculas";
    const ID = "ID";
    const NOMBRE = "nombre";
    const DIRECTOR = "director";
    const DESCRIPCION = "descripcion";
    
    // Constantes de estado
    const ESTADO_ERROR_BD = 1;
    const ESTADO_CREACION_EXITOSA = 2;
    const ESTADO_CREACION_FALLIDA = 3;
    const ESTADO_FALLA_DESCONOCIDA = 4;

    /**
     * Descripción: Obtiene los datos de todas las peliculas que hay en el sistema
     * @return Datos de todas las peliculas que hay en el sistema
     */
    public function obtenerTodasPeliculas()
    {
        try 
        {
            $pdo = ConexionBD::obtenerInstancia()->obtenerBD();

            // Sentencia SELECT
            $comando = "SELECT * FROM " . self::NOMBRE_TABLA;

            $sentencia = $pdo->prepare($comando);

            $sentencia->execute();

            $array = array();

            while ($row = $sentencia->fetch(PDO::FETCH_ASSOC)) 
            { 
                array_push($array, $row);
            }

            return [
                [
                    "estado" => self::ESTADO_CREACION_EXITOSA,
                    "mensaje" => $array
                ]
            ];
        } 
        catch (PDOException $e) 
        {
            throw new ExcepcionApi(self::ESTADO_ERROR_BD, $e->getMessage());
        }
    }

    /**
     * Descripción: Obtiene y devuelve una pelicula según su ID
     * @param ID ID de la pelicula
     * @return Datos de la pelicula indicada con su ID
     */
    public function obtenerPeliculaID($ID)
    {
        try 
        {
            $pdo = ConexionBD::obtenerInstancia()->obtenerBD();

            // Sentencia SELECT
            $comando = "SELECT * FROM " . self::NOMBRE_TABLA . " " .
            "WHERE " . self::ID . " = ?";

            $sentencia = $pdo->prepare($comando);

            // Pongo los datos en la consulta INSERT
            $sentencia->bindParam(1, $ID);

            $sentencia->execute();

            $array = array();

            while ($row = $sentencia->fetch(PDO::FETCH_ASSOC)) 
            { 
                array_push($array, $row);
            }

            return [
                [
                    "estado" => self::ESTADO_CREACION_EXITOSA,
                    "mensaje" => $array
                ]
            ];
        } 
        catch (PDOException $e) 
        {
            throw new ExcepcionApi(self::ESTADO_ERROR_BD, $e->getMessage());
        }
    }

    /**
     * Descripción: Inserta una pelicula en la base de datos
     * @param pelicula Pelicula para insertar en la base de datos
     * @return Indica si se ha insertado la pelicula correctamente (Código 1)
     */
    public function insertarPelicula($pelicula)
    {
        try 
        {
            // Obtengo una instancia de la base de datos ya conectada
            $pdo = ConexionBD::obtenerInstancia()->obtenerBD();

            // Creo la sentencia INSERT
            $comando = "INSERT INTO " . self::NOMBRE_TABLA . " ( " .
                self::ID . "," .
                self::NOMBRE . "," .
                self::DIRECTOR . "," .
                self::DESCRIPCION . ")" .
                " VALUES(?,?,?,?)";

            $sentencia = $pdo->prepare($comando);

            // Pongo los datos en la consulta INSERT
            $sentencia->bindParam(1, $pelicula->ID);
            $sentencia->bindParam(2, $pelicula->nombre);
            $sentencia->bindParam(3, $pelicula->director);
            $sentencia->bindParam(4, $pelicula->descripcion);

            // Ejecuto la consulta
            $resultado = $sentencia->execute();
        } 
        catch (PDOException $e) 
        {
            throw new ExcepcionApi(self::ESTADO_ERROR_BD, $e->getMessage());
        }

        switch ($resultado) 
        {
            case self::ESTADO_CREACION_EXITOSA:
                http_response_code(200);
                return correcto;
                break;
            case self::ESTADO_CREACION_FALLIDA:
                throw new ExcepcionApi(self::ESTADO_CREACION_FALLIDA, "Ha ocurrido un error.");
                break;
            default:
                throw new ExcepcionApi(self::ESTADO_FALLA_DESCONOCIDA, "Fallo desconocido.", 400);
        }
    }
}
