package com.example.agenda_estudiantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_estudiantes.modelo.AdminDB

class MainActivity : AppCompatActivity() {

    var lista_alumnos :ArrayList<Alumno>? =null





    var lista : RecyclerView?=null

    val contactoDb = AdminDB()

    var layoutManager: RecyclerView.LayoutManager?=null
    var adaptador: Adaptador_custom?=null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       // startActivity(Intent(applicationContext, Listar::class.java))

        lista_alumnos= ArrayList()


        var datos= contactoDb.getAllStudents()
        lista_alumnos= datos!!


        lista = findViewById(R.id.lista_alumnos)
        lista?.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        adaptador= Adaptador_custom(this, lista_alumnos!!, object:
            ClickListener {
            override fun onClick(vista: View, position: Int) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
          //      Toast.makeText(applicationContext,lista_alumnos?.get(position)?.nombre, Toast.LENGTH_SHORT).show()
                var id : Int = 0
                id= lista_alumnos?.get(position)?.id!!
                val intent : Intent = Intent(applicationContext, Update::class.java)
                intent.putExtra("id",id)
                startActivity(intent)

            }

        }

        )
        lista?.layoutManager=layoutManager
        lista?.adapter=adaptador
    }



    fun btnAddStudent(view: View) {

        startActivity(Intent(applicationContext, Create::class.java))
    }


}
