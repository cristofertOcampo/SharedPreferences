package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sharedpreferences.Result

class MainActivity : AppCompatActivity() {

    var campoUsuario:EditText?=null
    var campoPass:EditText?=null
    var txtUsuario:TextView?=null
    var txtPass:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarComponentes()
    }

    private fun iniciarComponentes() {

        var btnGuardar:Button=findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener { onShared(1) }

        var btnCargar:Button=findViewById(R.id.btnCargar)
        btnCargar.setOnClickListener { onShared(2) }

        campoUsuario=findViewById(R.id.campoUser)
        campoPass=findViewById(R.id.campoPass)
    }

    private fun onShared(btn:Int) {

        when(btn){
            1->{
                var preferences:SharedPreferences=getSharedPreferences("credenciales",Context.MODE_PRIVATE)

                var usuario= campoUsuario?.text.toString()
                var pass= campoPass?.text.toString()

                var editor:SharedPreferences.Editor=preferences.edit()
                editor.putString("user",usuario)
                editor.putString("pass",pass)
                editor.commit()

                Toast.makeText(this,"Se han registrado los datos",Toast.LENGTH_SHORT).show()
            }
            2->{
                var preferences:SharedPreferences=getSharedPreferences("credenciales",Context.MODE_PRIVATE)

                var user: String? =preferences.getString("user","No existe la información")
                var pass: String? =preferences.getString("pass","No existe la información")

                txtUsuario?.text=user
                txtPass?.text=pass
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
            }
        }
    }
}