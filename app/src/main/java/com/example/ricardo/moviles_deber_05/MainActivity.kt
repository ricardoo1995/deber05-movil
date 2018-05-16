package com.example.ricardo.moviles_deber_05

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ricardo.moviles_deber_5.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var personas = ArrayList<Persona>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //crearDatosPrueba()


        val layoutManager = LinearLayoutManager(this)
        personas = CrearPersonas.persona
        val adaptador = PersonasAdaptador(personas)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        //personas.addAll(CrearPersonas.persona)
        adaptador.notifyDataSetChanged()
        Log.i("vista-principal", "Cambio personas ${personas.size}")


        //botonLike.setOnClickListener{view:View -> botonLike.setBackgroundColor(Color.YELLOW)}
    }


}

class Persona(var cedula: String, var nombre: String, var apellido: String, var descripcion:String, var fechaNacimiento:Date,var like:Boolean) {}

class CrearPersonas(){
    companion object {

        var persona: ArrayList<Persona> = ArrayList()

        init {
            persona.add(Persona("1715925739", "marcelo", "Nieto", "algo", Date(), true))
            persona.add(Persona("1712781381", "gabriel", "Sanchez", "algo2", Date(), false))
            persona.add(Persona("1721739189", "juan", "Arcentales", "algo3", Date(), false))
            persona.add(Persona("1799903002", "david", "Acosta", "algo4", Date(), true))
            persona.add(Persona("1793227133", "daniela", "Paredes", "algo5", Date(), true))
        }
    }
}

class PersonasAdaptador(private val listaPersonas: List<Persona>) : RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var apellido: TextView
        var cedula: TextView
        lateinit var persona: Persona
        var botonLike:Button
        var botonDetalle:Button

        init {
            //persona = listaPersonas[position]
            nombre = view.findViewById(R.id.txtv_nombre) as TextView
            apellido = view.findViewById(R.id.txtv_apellido) as TextView
            cedula = view.findViewById(R.id.txtv_cedula) as TextView
            botonLike = view.findViewById(R.id.botonLike) as Button
            botonDetalle = view.findViewById(R.id.botonDetalle) as Button
            val left = apellido.paddingLeft
            val top = apellido.paddingTop
            Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_layout) as RelativeLayout
            layout.setOnClickListener({ v ->
                val nombreActual = v.findViewById(R.id.txtv_nombre) as TextView

                Log.i("vista-principal", "El nombre actual es: ${nombreActual.text}")
                nombreActual.text = "Otro texto"

            })
            cedula.setOnClickListener { v ->
                val cedulaActual = v.findViewById(R.id.txtv_cedula) as TextView
                val toast = Toast.makeText(v.context, "Hola ${cedulaActual.text}", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(v.context, ActividadLayouts::class.java)
                startActivity(v.context, intent, null)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lista_fila_usuario, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val persona = listaPersonas[position]
        holder.nombre.setText(persona.nombre)
        holder.apellido.setText(persona.apellido)
        holder.cedula.setText(persona.cedula)
        holder.botonDetalle.setText("Detail")
        holder.botonDetalle.setBackgroundColor(Color.GRAY)

        if (persona.like==true){
            holder.botonLike.setText("Like")
            holder.botonLike.setBackgroundColor(Color.BLUE)
        }else{
            holder.botonLike.setText("No Like")
            holder.botonLike.setBackgroundColor(Color.YELLOW)
        }

        holder.botonLike.setOnClickListener{v ->
            if (persona.like==true){
                holder.botonLike.setText("No Like")
                holder.botonLike.setBackgroundColor(Color.YELLOW)
                persona.like = false
            }else{
                holder.botonLike.setText("Like")
                holder.botonLike.setBackgroundColor(Color.BLUE)
                persona.like = true
            }
        }

        holder.botonDetalle.setOnClickListener{v ->
            val like:String
            if (persona.like==true){
                like = "Like"
            }else {
                like = "No Like"
            }
            val intent = Intent(v.context, ActividadLayouts::class.java)
            intent.putExtra("nombre",persona.nombre)
            intent.putExtra("apellido",persona.apellido)
            intent.putExtra("cedula",persona.cedula)
            intent.putExtra("descripcion",persona.descripcion)
            intent.putExtra("fecha",persona.fechaNacimiento)
            intent.putExtra("like",like)
            startActivity(v.context, intent, null)
        }

    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }


}

