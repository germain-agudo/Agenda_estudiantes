package com.example.agenda_estudiantes.modelo

import android.database.DatabaseUtils
import android.util.Log
import android.widget.Toast
import com.example.agenda_estudiantes.Alumno
import com.example.agenda_estudiantes.config.AppAgenda
import com.example.agenda_estudiantes.config.Contract
import kotlin.contracts.contract


class AdminDB{

    fun getAllStudents():ArrayList<Alumno>?{
        try {
            val datos = arrayListOf<Alumno>()
            val db = AppAgenda.DB.readableDatabase
            val numero_datos = DatabaseUtils.queryNumEntries(db, AppAgenda.TB_Alumnos).toInt()

            if (numero_datos>0){
                val query = "SELECT * FROM ${AppAgenda.TB_Alumnos} ORDER BY ${Contract.alumno.ID} DESC"
                val consulta = db.rawQuery(query,null)

                consulta.moveToFirst()
                do {
                    datos.add(
                        Alumno(consulta.getInt(consulta.getColumnIndex(Contract.alumno.ID))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.NUMERO_CONTROL))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.NOMBRE))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.CARRERA))
                            ,consulta.getInt(consulta.getColumnIndex(Contract.alumno.SEMESTRE))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.GRUPO))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.IMAGEN))


                        )
                    )

                } while (consulta.moveToNext())


            } else {
                Toast.makeText(AppAgenda.CONTEXT,"No hay alumnos guardados", Toast.LENGTH_SHORT).show()

            }
            db.close()
            return datos

        }catch (ex :Exception){
            Toast.makeText(AppAgenda.CONTEXT,"No se pudo recuperar la lista de alumnos", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
            return null

        }
    }






    fun addStudent(student: Alumno) {
        try {
            val db = AppAgenda.DB.writableDatabase
            var qry = "INSERT INTO ${AppAgenda.TB_Alumnos} (" +
                    "${Contract.alumno.NUMERO_CONTROL}, ${Contract.alumno.NOMBRE}, ${Contract.alumno.CARRERA}" +
                    ", ${Contract.alumno.SEMESTRE}, ${Contract.alumno.GRUPO}, ${Contract.alumno.IMAGEN}      )" +
                    "VALUES( '${student.numero_control}', '${student.nombre}', '${student.carrera}'" +
                    ", '${student.semestre}', '${student.grupo}', '${student.imagen}'        );"



            db.execSQL(qry)
            Toast.makeText(AppAgenda.CONTEXT, "Contacto Guardado", Toast.LENGTH_SHORT).show()
            db.close()
        } catch (ex: java.lang.Exception) {
            Toast.makeText(AppAgenda.CONTEXT, "No se pudo guardar al alumno", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
        }


    }

    fun deleteStudent(id: Int){
        try {

            val db = AppAgenda.DB.writableDatabase
            var qry ="DELETE FROM ${AppAgenda.TB_Alumnos} WHERE ${Contract.alumno.ID} = '$id' ;"
            db.execSQL(qry)
            db.close()
            Toast.makeText(AppAgenda.CONTEXT,"Alumno eliminado", Toast.LENGTH_SHORT).show()
        }catch (ex : java.lang.Exception){
            Toast.makeText(AppAgenda.CONTEXT,"No se pudo eliminar al alumno", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
        }

    }

    fun modiifyUser(id: Int, alumno:Alumno ){
        try {
            val db = AppAgenda.DB.writableDatabase
            var qry ="UPDATE  ${AppAgenda.TB_Alumnos}  SET ${Contract.alumno.NUMERO_CONTROL}  = '${alumno.numero_control}', " +
                    " ${Contract.alumno.NOMBRE} = '${alumno.nombre}', " +
                    " ${Contract.alumno.CARRERA} = '${alumno.carrera}', " +
                    " ${Contract.alumno.SEMESTRE} = '${alumno.semestre}', " +
                    " ${Contract.alumno.GRUPO} = '${alumno.grupo}', " +
                    " ${Contract.alumno.IMAGEN} = '${alumno.imagen}' " +

                    "  WHERE ${Contract.alumno.ID} = '$id' ;"
            db.execSQL(qry)
            db.close()

            Toast.makeText(AppAgenda.CONTEXT,"Alumno modificado", Toast.LENGTH_SHORT).show()
        }catch (ex: java.lang.Exception){
            Toast.makeText(AppAgenda.CONTEXT,"No se pudo modificar al ALumno", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
        }

    }




    fun getStudent(id: Int):ArrayList<Alumno>?{
        try {
            val datos = arrayListOf<Alumno>()
            val db = AppAgenda.DB.readableDatabase
            val numero_datos = DatabaseUtils.queryNumEntries(db, AppAgenda.TB_Alumnos).toInt()

            if (numero_datos>0){
                val query = "SELECT * FROM ${AppAgenda.TB_Alumnos} WHERE ${Contract.alumno.ID} = '$id';"
                val consulta = db.rawQuery(query,null)

                consulta.moveToFirst()
                do {
                    datos.add(
                        Alumno(consulta.getInt(consulta.getColumnIndex(Contract.alumno.ID))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.NUMERO_CONTROL))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.NOMBRE))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.CARRERA))
                            ,consulta.getInt(consulta.getColumnIndex(Contract.alumno.SEMESTRE))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.GRUPO))
                            ,consulta.getString(consulta.getColumnIndex(Contract.alumno.IMAGEN))


                        )
                    )

                } while (consulta.moveToNext())


            } else {
                Toast.makeText(AppAgenda.CONTEXT,"No existe el alumno", Toast.LENGTH_SHORT).show()

            }
            db.close()
            return datos

        }catch (ex :Exception){
            Toast.makeText(AppAgenda.CONTEXT,"No se pudo recuperar al alumno", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
            return null

        }
    }



    fun getnc(numero_control: String):Int{
        try {
            var datos = 0
            val db = AppAgenda.DB.readableDatabase
            val numero_datos = DatabaseUtils.queryNumEntries(db, AppAgenda.TB_Alumnos).toInt()

            if (numero_datos>0){
                val query = "SELECT * FROM ${AppAgenda.TB_Alumnos} WHERE ${Contract.alumno.NUMERO_CONTROL} = '$numero_control';"
                val consulta = db.rawQuery(query,null)
                consulta.moveToFirst()
                if (consulta.moveToNext()){
                    datos=consulta.count
                }

            } else {
               // Toast.makeText(AppAgenda.CONTEXT,"No existe el alumno", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return datos
        }catch (ex :Exception){
            Toast.makeText(AppAgenda.CONTEXT,"No se pudo recuperar al alumno", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
            return 0
        }
    }


    fun getnc_id(numero_control: String,id: Int):Int{
        try {
            var datos = 0
            val db = AppAgenda.DB.readableDatabase
            val numero_datos = DatabaseUtils.queryNumEntries(db, AppAgenda.TB_Alumnos).toInt()

            if (numero_datos>0){
                val query = "SELECT * FROM ${AppAgenda.TB_Alumnos} WHERE ${Contract.alumno.ID} = '$id'" +
                        "OR ${Contract.alumno.NUMERO_CONTROL} = '$numero_control';"
                val consulta = db.rawQuery(query,null)
                consulta.moveToFirst()
                if (consulta.moveToNext()){
                    datos=consulta.count
                }

            } else {
                Toast.makeText(AppAgenda.CONTEXT,"No existe el alumno", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return datos
        }catch (ex :Exception){
            Toast.makeText(AppAgenda.CONTEXT,"No se pudo recuperar al alumno", Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
            return 0
        }
    }


}
