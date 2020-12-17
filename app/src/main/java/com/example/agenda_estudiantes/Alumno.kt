package com.example.agenda_estudiantes

class Alumno(id : Int, numero_control : String, nombre : String, carrera : String, semestre : Int, grupo: String, imagen : String ){
    public var id : Int = 0
    public var numero_control : String = ""
    public var nombre : String = ""
    public var carrera : String = ""
    public var semestre : Int = 0
    public var grupo : String = ""
    public var imagen : String = ""



    init {
        this.id = id
        this.numero_control = numero_control
        this.nombre = nombre
        this.carrera = carrera
        this.semestre = semestre
        this.grupo = grupo
        this.imagen = imagen
    }


}
