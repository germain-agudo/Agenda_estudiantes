package com.example.agenda_estudiantes.config


import android.app.Application
import android.content.Context
import android.database.DatabaseUtils

import android.provider.BaseColumns

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.agenda_estudiantes.Alumno


/*
 *
 */
class AppAgenda:Application(){

    companion object{
        lateinit var CONTEXT: Context
        lateinit var DB : InitDb
        val DB_NAME="agendaDB"
        val VERSION =1
        val TB_Alumnos ="tbAlumnos"

    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        DB = InitDb()
    }
}


/*
 *
 */

class Contract{
    class alumno:BaseColumns{
        companion object{
            val ID  ="id"
            val NUMERO_CONTROL = "numero_control"
            val NOMBRE = "nombre"
            val CARRERA = "carrera"
            val SEMESTRE = "semestre"
            val GRUPO = "grupo"
            val IMAGEN = "imagen"
        }
    }
}






/**
 * Clase para inicializar la DB
 */

class InitDb: SQLiteOpenHelper(AppAgenda.CONTEXT,AppAgenda.DB_NAME, null,AppAgenda.VERSION){

    val queryTablaAlumnos ="CREATE TABLE ${AppAgenda.TB_Alumnos}(" +
            "${Contract.alumno.ID} INTEGER PRIMARY KEY AUTOINCREMENT" +
            ",${Contract.alumno.NUMERO_CONTROL} TEXT NOT NULL" +
            ",${Contract.alumno.NOMBRE} TEXT NOT NULL" +
            ",${Contract.alumno.CARRERA} TEXT NOT NULL" +
            ",${Contract.alumno.SEMESTRE} INTEGER NOT NULL" +
            ",${Contract.alumno.GRUPO} TEXT NOT NULL" +
            ",${Contract.alumno.IMAGEN} TEXT NOT NULL );"

    override fun onCreate(db: SQLiteDatabase?) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL(queryTablaAlumnos)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}


/*
 *
 */
