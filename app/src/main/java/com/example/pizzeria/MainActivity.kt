package com.example.pizzeria

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Usuario
import com.example.pizzeria.dao.DAOUsuarios
import java.io.File
import java.util.Base64

class MainActivity : PlantillaActivity(), View.OnClickListener {

    private lateinit var txtNombre: EditText
    private lateinit var txtContrasenna: EditText
    private lateinit var chkBoxMantenerSesion: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        super.createFromTemplate()
        val layout: ConstraintLayout = findViewById(R.id.ctrLayout1)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
        }
        txtNombre = findViewById(R.id.txtNombre2)
        txtContrasenna = findViewById(R.id.txtContrasenna2)
        chkBoxMantenerSesion = findViewById(R.id.chkBoxMantenerSesion)
        val btnIniciarSesion: Button = findViewById(R.id.btnIniciarSesion2)
        val btnRegistro: Button = findViewById(R.id.btnRegistrarse2)
        btnIniciarSesion.setOnClickListener(this)
        btnRegistro.setOnClickListener(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences("Config", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("mantenerNombreUsuario", "") != "" && sharedPreferences.getString("mantenerContrasenna", "") != "") {
            val intent = Intent(this, LoggedInActivity::class.java)
            intent.putExtra("usuario", Usuario(sharedPreferences.getString("mantenerNombreUsuario", ""), sharedPreferences.getString("mantenerContrasenna", "")))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val alerta: AlertDialog.Builder = AlertDialog.Builder(this)
        alerta.setMessage("¿Estás seguro que deseas salir?")
        alerta.setPositiveButton("Si") {_, _ -> finish()}
        alerta.setNegativeButton("No") {_, _ ->}
        alerta.create()
        alerta.show()
    }

    fun cifrar(texto: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(texto.toByteArray(Charsets.UTF_8))
        } else {
            texto
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnIniciarSesion2 -> {
                if (txtNombre.text.toString() != "" && txtContrasenna.text.toString() != "") {
                    val usuario: Usuario = Usuario(txtNombre.text.toString(), txtContrasenna.text.toString())
                    val dbHelper = DBHelper(this)
                    if (dbHelper.usuarioExiste(usuario)) {
                        if (cifrar(txtContrasenna.text.toString()) == usuario.getContrasenna()) {
                            if (chkBoxMantenerSesion.isChecked) {
                                val editor: SharedPreferences.Editor = getSharedPreferences("Config", Context.MODE_PRIVATE).edit()
                                editor.putString("mantenerNombreUsuario", txtNombre.text.toString())
                                editor.putString("mantenerContrasenna", txtContrasenna.text.toString())
                                editor.apply()
                            }
                            val intent = Intent(this, LoggedInActivity::class.java)
                            intent.putExtra("usuario", Usuario(txtNombre?.text.toString(), txtContrasenna?.text.toString()))
                            startActivity(intent)
                            finish()
                        } else {
                            val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                            alert.setMessage("Contraseña incorrecta")
                            alert.setPositiveButton("Ok") {_, _ ->}
                            alert.setIcon(0)
                            alert.create()
                            alert.show()
                        }
                    } else {
                        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                        alert.setMessage("Aún no te has registrado, regístrate")
                        alert.setPositiveButton("Ok") {_, _ -> startActivity(Intent(this, RegisterActivity::class.java)); finish()}
                        alert.setIcon(0)
                        alert.create()
                        alert.show()
                    }
                } else run {
                    val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                    alert.setMessage("Rellena todos los campos")
                    alert.setPositiveButton("Ok") {_, _ ->}
                    alert.setIcon(0)
                    alert.create()
                    alert.show()
                }
            }

            R.id.btnRegistrarse2 -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }
    }
}