package com.example.ricardo.moviles_deber_05

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ricardo.moviles_deber_5.R
import kotlinx.android.synthetic.main.activity_actividad_layouts.*

class ActividadLayouts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_layouts)


        textViewNombre.text = intent.getStringExtra("nombre")
        txtApellido.text = intent.getStringExtra("apellido")
        txtCedula.text = intent.getStringExtra("cedula")
        tctDescripcion.text = intent.getStringExtra("descripcion")
        txtFecha.text = intent.getStringExtra("fecha")
        txtLike.text = intent.getStringExtra("like")

    }
}
