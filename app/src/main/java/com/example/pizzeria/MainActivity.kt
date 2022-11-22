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
import com.example.pizzeria.clases.Contrasenna
import com.example.pizzeria.clases.Usuario
import com.example.pizzeria.dao.DAOUsuarios
import java.io.File
import java.util.Base64

class MainActivity : PlantillaActivity(), View.OnClickListener {

    private lateinit var txtNombre: EditText
    private lateinit var txtContrasenna: EditText
    private lateinit var chkBoxMantenerSesion: CheckBox
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DBHelper(this)
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
        val nomUsuario: String? = sharedPreferences.getString("mantenerNombreUsuario", "")
        val contraUsuario: String? = sharedPreferences.getString("mantenerContrasenna", "")
        if (nomUsuario != null && nomUsuario != "" && contraUsuario != null && contraUsuario != "") {
            val usuario = Usuario(nomUsuario, contraUsuario)
            if (dbHelper.usuarioExiste(usuario) && dbHelper.getContrasennaFromUsuario(usuario) != null && dbHelper.getContrasennaFromUsuario(usuario) != "") {
                val intent = Intent(this, LoggedInActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            }
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

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnIniciarSesion2 -> {
                if (txtNombre.text.toString() != "" && txtContrasenna.text.toString() != "") {
                    val usuario: Usuario = Usuario(txtNombre.text.toString(), txtContrasenna.text.toString())
                    if (dbHelper.usuarioExiste(usuario)) {
                        if (Contrasenna.cifrar(txtContrasenna.text.toString()) == dbHelper.getContrasennaFromUsuario(usuario)) {
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