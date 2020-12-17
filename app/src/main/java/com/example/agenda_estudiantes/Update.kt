package com.example.agenda_estudiantes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.agenda_estudiantes.modelo.AdminDB
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_update.*
import kotlin.math.log

class Update : AppCompatActivity() {
    val carrera = arrayOf("--Seleccione carrera--","ING. INFORMATICA","ING. TIC´S","ING. FORESTAL", "ING. EN AGRONOMIA","LIC. EN BILOGIA")

    val contactoDb = AdminDB()
    var id : Int = 0
    var nombre_carrera : String = ""
    var update_nombre_carrera : String = ""

    var numero_control_repetido : Int =0
    var spinner_verificacion : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val intent : Intent = intent
         id = intent.getIntExtra("id",0)
        findStudent()

        crearSpinner()
        title = "Editar alumno"
    }

    fun btnUpdatePressed(view: View) {
        var imagen: String = ""

        if (update_nombre_carrera.equals(carrera.get(1))) {
            val uriImage =
                Uri.parse("android.resource://" + packageName + "/" + R.drawable.informatica)
            imagen = uriImage.toString()
        } else if (update_nombre_carrera.equals(carrera.get(2))) {
            val uriImage = Uri.parse("android.resource://" + packageName + "/" + R.drawable.tics)
            imagen = uriImage.toString()
        } else if (update_nombre_carrera.equals(carrera.get(3))) {
            val uriImage =
                Uri.parse("android.resource://" + packageName + "/" + R.drawable.forestal)
            imagen = uriImage.toString()
        } else if (update_nombre_carrera.equals(carrera.get(4))) {
            val uriImage =
                Uri.parse("android.resource://" + packageName + "/" + R.drawable.agronomia)
            imagen = uriImage.toString()
        } else if (update_nombre_carrera.equals(carrera.get(5))) {
            val uriImage =
                Uri.parse("android.resource://" + packageName + "/" + R.drawable.biologia)
            imagen = uriImage.toString()
        } else {
            val uriImage =
                Uri.parse("android.resource://" + packageName + "/" + R.drawable.contacto)
            imagen = uriImage.toString()
        }
        if(!etUpdateControlNumber.text.toString().trim().isEmpty() &&  !etUpdateName.text.toString().trim().isEmpty()
            && !etUpdateSemester.text.trim().isEmpty()  &&  !etUpdateGroup.text.toString().trim().isEmpty() ) {


            if (spinner_verificacion) {
                existencia()

                if(numero_control_repetido!=0){
                    Toast.makeText(applicationContext, "El numero de control: "+etUpdateControlNumber.text.toString().trim()+" ya existe", Toast.LENGTH_SHORT).show()
                }else {


                    var updateStudent: Alumno = Alumno(
                        0
                        ,
                        etUpdateControlNumber.text.toString().trim(),
                        etUpdateName.text.toString().trim(),
                        update_nombre_carrera,
                        Integer.parseInt(etUpdateSemester.text.toString().trim()),
                        etUpdateGroup.text.toString().trim(),
                        imagen
                    )
                    contactoDb.modiifyUser(id, updateStudent)
                    finish()
                    startActivity(Intent(applicationContext, MainActivity::class.java))

                }




        } else{
                Toast.makeText(applicationContext, "Seleccione carrera", Toast.LENGTH_SHORT).show()
            }

     } else{
            Toast.makeText(applicationContext, "Campos vacios", Toast.LENGTH_SHORT).show()
        }

    }


    fun btnCancelPressed(view: View) {
        startActivity(Intent(applicationContext,MainActivity::class.java))
    }
    fun btnDeletePressed(view: View) {
        contactoDb.deleteStudent(id)
        startActivity(Intent(applicationContext,MainActivity::class.java))
    }



    fun findStudent(){
        val datos = contactoDb.getStudent(id)
        etUpdateControlNumber.setText(datos!!.get(0).numero_control.toString())
        etUpdateName.setText(datos!!.get(0).nombre.toString())
        //etUpdateCareer.setText(datos!!.get(0).carrera.toString())
        etUpdateSemester.setText(datos!!.get(0).semestre.toString())
        etUpdateGroup.setText(datos!!.get(0).grupo.toString())

        var url = Uri.parse(datos!!.get(0).imagen)
        updateImageView.setImageURI(url)

        nombre_carrera= datos!!.get(0).carrera.toString()


    }

    fun crearSpinner(){


        val spinner :Spinner
        spinner= findViewById(R.id.spinnerUpdate)

        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            carrera)

        spinner.adapter=adapter
        spinner.setSelection(carrera.indexOf(nombre_carrera))
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
               // var res =parent.getItemAtPosition(position).toString()

                if (position>0){
                    update_nombre_carrera = parent.getItemAtPosition(position).toString()
                    spinner_verificacion = true
                }else{
                    spinner_verificacion = false
                    // Toast.makeText(applicationContext,"Seleccione una carrera", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun  existencia() {
        numero_control_repetido=0
        numero_control_repetido = contactoDb.getnc_id(etUpdateControlNumber.text.toString(),id)
       // Log.d("es este", numero_control_repetido.toString())

    }



}
