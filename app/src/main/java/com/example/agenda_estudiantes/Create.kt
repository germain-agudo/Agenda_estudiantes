package com.example.agenda_estudiantes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IntegerRes
import com.example.agenda_estudiantes.config.AppAgenda
import com.example.agenda_estudiantes.modelo.AdminDB
import kotlinx.android.synthetic.main.activity_create.*

class Create : AppCompatActivity() {
    val contactoAdmin = AdminDB()
    val carrera = arrayOf("--Seleccione carrera--","ING. INFORMATICA","ING. TIC´S","ING. FORESTAL", "ING. EN AGRONOMIA","LIC. EN BILOGIA")
    var nombre_carrera : String = ""
    var spinner_verificacion : Boolean = false
    var numero_control_repetido : Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        title = "Agregar alumno"
        crearSpinner()
    }

    fun btnCreatePressed(view: View) {
        var imagen : String =""

        if (nombre_carrera.equals(carrera.get(1))){
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.informatica)
            imagen = uriImage.toString()
        }else if(nombre_carrera.equals(carrera.get(2))){
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.tics)
            imagen = uriImage.toString()
        }else if(nombre_carrera.equals(carrera.get(3))){
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.forestal)
            imagen = uriImage.toString()
        }else if(nombre_carrera.equals(carrera.get(4))){
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.agronomia)
            imagen = uriImage.toString()
        }else if(nombre_carrera.equals(carrera.get(5))){
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.biologia)
            imagen = uriImage.toString()
        } else{
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.contacto)
            imagen = uriImage.toString()
        }


        /*var numero_control = findViewById<EditText>(R.id.etAddControlNumber) as EditText
        var mostrar_numer_control = Integer.parseInt(numero_control.text.toString())*/

        if(!etAddControlNumber.text.toString().trim().isEmpty() &&  !etAddName.text.toString().trim().isEmpty()
            && !etAddSemester.text.trim().isEmpty()  &&  !etAddGroup.text.toString().trim().isEmpty() ) {


            if (spinner_verificacion) {
                existencia()
                if (numero_control_repetido>0){
                    Toast.makeText(applicationContext, "El numero de control: "+etAddControlNumber.text.toString().trim()+" ya existe", Toast.LENGTH_SHORT).show()
                } else{





                   val alumno = Alumno(
                       0,
                       etAddControlNumber.text.toString().trim(),
                       etAddName.text.toString().trim()
                       ,
                       nombre_carrera,
                       Integer.parseInt(etAddSemester.text.toString().trim()),
                       etAddGroup.text.toString().trim(),
                       imagen
                   )
                   contactoAdmin.addStudent(alumno)
                   finish()
                   startActivity(Intent(applicationContext, MainActivity::class.java))

                }






            } else {
                Toast.makeText(applicationContext, "Seleccione carrera", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(applicationContext, "Campos vacios", Toast.LENGTH_SHORT).show()
        }


    }

    fun btnCancelPressed(view: View) {


    }




    fun crearSpinner(){

        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            carrera)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter=adapter
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //tvTotal.text="Descuento de  : ${parent.getItemAtPosition(position).toString()}"
               // comparacion =parent.getItemAtPosition(position).toString()
                if (position>0){
                    nombre_carrera= parent.getItemAtPosition(position).toString()
                    spinner_verificacion = true
                }else{
                    spinner_verificacion = false
                   // Toast.makeText(applicationContext,"Seleccione una carrera", Toast.LENGTH_SHORT).show()
                }

            }    override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }


fun  existencia() {
    numero_control_repetido=0
    numero_control_repetido = contactoAdmin.getnc(etAddControlNumber.text.toString())

}



}


