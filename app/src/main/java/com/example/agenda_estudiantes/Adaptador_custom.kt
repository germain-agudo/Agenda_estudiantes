package com.example.agenda_estudiantes

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador_custom (var contexto: Context, items : ArrayList<Alumno>, val clickListener: ClickListener):
RecyclerView.Adapter<Adaptador_custom.ViewHolder>(),Filterable{


    lateinit var items : ArrayList<Alumno>
    lateinit var items_filter : ArrayList<Alumno>
    init {
        this.items = items
        this.items_filter=items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val vista =LayoutInflater.from(contexto).inflate(R.layout.template_alumnos,parent, false)
        var viewHolder = ViewHolder(vista, clickListener)
        return  viewHolder


    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions u   se File | Settings | File Templates.
        return  this.items.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val item = items?.get(position)

        holder.id?.text = item?.id.toString()
        holder.numero_control?.text = item?.numero_control.toString()
        holder.nombre?.text = item?.nombre.toString()
        holder.carrera?.text = item?.carrera.toString()
        holder.semestre?.text = item?.semestre.toString()
        holder.grupo?.text = item?.grupo.toString()

        //var url = Uri.parse(item?.imagen)
        //holder.imagen?.setImageURI(url)
    }


    class ViewHolder (vista : View, listener: ClickListener) : RecyclerView.ViewHolder(vista), View.OnClickListener {


        var vista = vista

        var id : TextView? = null
        var numero_control : TextView? =null
        var nombre : TextView?=null
        var carrera : TextView?=null
        var semestre : TextView?=null
        var grupo : TextView?=null
       // var imagen : ImageView?=null


        var listener : ClickListener?=null
        init {
            id =vista.findViewById(R.id.mostrar_id)
            numero_control =vista.findViewById(R.id.mostrar_numero_control)
            nombre =vista.findViewById(R.id.mostrar_nombre)
            carrera =vista.findViewById(R.id.mostrar_carrera)
            semestre =vista.findViewById(R.id.mostrar_semestre)
            grupo =vista.findViewById(R.id.mostrar_grupo)
         //   imagen =vista.findViewById(R.id.iV_alumnos)

            this.listener=listener
            vista.setOnClickListener(this)
        }



        override fun onClick(v: View?) {
         //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            this.listener?.onClick(v!!,adapterPosition)
        }

    }



    override fun getFilter(): Filter {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        return object:Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val filterResults = FilterResults();
                if (charsequence==null||charsequence.length<0){
                    filterResults.count =items_filter.size
                    filterResults.values = items_filter
                }else{
                    var searchChr = charsequence.toString().toUpperCase()
                    var item_modal = ArrayList<Alumno>()
                    for(i in items_filter){
                            if(i.numero_control.contains(searchChr)||i.nombre.contains(searchChr)){

                                item_modal.add(i)
                            }
                    }
                    filterResults.count=item_modal.size
                    filterResults.values = item_modal
                }

                return filterResults;
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                items= filterResults!!.values as ArrayList<Alumno>
                notifyDataSetChanged()
            }

        }
    }



}