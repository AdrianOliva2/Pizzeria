package com.example.pizzeria

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.pizzeria.clases.Usuario
import com.example.pizzeria.dao.DAOUsuarios

class RegisterActivity : Activity(), View.OnClickListener {

    private var txtNombre: EditText? = null
    private var txtContrasenna: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtNombre = findViewById(R.id.txtNombre2)
        txtContrasenna = findViewById(R.id.txtContrasenna2)
        val btnIniciarSesion: Button = findViewById(R.id.btnIniciarSesion2)
        val btnRegistro: Button = findViewById(R.id.btnRegistrarse2)
        btnIniciarSesion.setOnClickListener(this)
        btnRegistro.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnIniciarSesion2 -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.btnRegistrarse2 -> {
                if (txtNombre?.text.toString() != "") {
                    val daoUsuarios: DAOUsuarios? = DAOUsuarios.getInstance()
                    if (daoUsuarios != null && daoUsuarios.insertarUsuario(Usuario(txtNombre?.text.toString(), txtContrasenna?.text.toString()))) {
                        if (txtContrasenna?.text.toString() != "") {
                            val intent: Intent = Intent(this, LoggedInActivity::class.java)
                            intent.putExtra("usuario", Usuario(txtNombre?.text.toString(), txtContrasenna?.text.toString()))
                            startActivity(intent)
                        } else {
                            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
                            alertDialog.setMessage("Introduce la contraseña")
                            alertDialog.setPositiveButton("Ok") {_, _ ->}
                            alertDialog.create()
                            alertDialog.show()
                        }
                    } else {
                        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
                        alertDialog.setMessage("Ya estás registrado, ¿quieres iniciar sesión?")
                        alertDialog.setPositiveButton("Si") {_, _ -> startActivity(Intent(this, MainActivity::class.java))}
                        alertDialog.setNegativeButton("No") {_, _ -> txtNombre?.setText(""); txtContrasenna?.setText("")}
                        alertDialog.create()
                        alertDialog.show()
                    }
                } else {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
                    alertDialog.setMessage("Introduce el nombre de usuario y la contraseña")
                    alertDialog.setPositiveButton("Ok") {_, _ ->}
                    alertDialog.create()
                    alertDialog.show()
                }
            }
        }
    }

    override fun onBackPressed() { }
}