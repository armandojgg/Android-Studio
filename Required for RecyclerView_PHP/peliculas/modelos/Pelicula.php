<?php

// Esta clase representa una pelicula

class Pelicula
{
    // Variables de clase
    public $ID, $nombre, $director, $descripcion;

    // Constructor
    public function __construct($nID, $nnombre, $ndirector, $ndescripcion)
    {
        $this->ID = $nID;
        $this->nombre = $nnombre;
        $this->director = $ndirector;
        $this->descripcion = $ndescripcion;
    }

    // Getter para ID
    public function getID()
    {
        return $this->ID;
    }

    // Getter para nombre
    public function getNombre()
    {
        return $this->nombre;
    }

    // Getter para director
    public function getDirector()
    {
        return $this->director;
    }

    // Getter para descripcion
    public function getDescripcion()
    {
        return $this->descripcion;
    }

    // Muestra los datos de la pelicula
    public function toString()
    {
        return
            [
                "ID" => utf8_encode($this->ID),
                "nombre" => utf8_encode($this->nombre),
                "director" => utf8_encode($this->director),
                "descripcion" => utf8_encode($this->descripcion),
            ];
    }
}
