package com.example.pizzeria

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pizzeria.clases.Usuario

class DBHelper(private val context: Context) : SQLiteOpenHelper(context, dbName, null, version) {

    private companion object {
        private val dbName: String = "PizzeriaDB.db"
        private val version: Int = 1
    }

    private fun createDB(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE \"INGREDIENTE\" (\n" +
                "\t\"NOMBRE_INGREDIENTE\"\tTEXT NOT NULL UNIQUE,\n" +
                "\tPRIMARY KEY(\"NOMBRE_INGREDIENTE\")\n" +
                ");")

        db?.execSQL("CREATE TABLE \"PIZZA\" (\n" +
                "\t\"ID_PIZZA\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"PRECIO\"\tREAL NOT NULL,\n" +
                "\t\"TAMANNO\"\tTEXT NOT NULL,\n" +
                "\t\"SALSA\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID_PIZZA\")\n" +
                ");")

        db?.execSQL("CREATE TABLE \"PIZZA_INGREDIENTE\" (\n" +
                "\t\"ID_PIZZA\"\tINTEGER NOT NULL,\n" +
                "\t\"ID_INGREDIENTE\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID_PIZZA\",\"ID_INGREDIENTE\")\n" +
                ");")

        db?.execSQL("CREATE TABLE \"PIZZA_USUARIO\" (\n" +
                "\t\"ID_PIZZA\"\tINTEGER NOT NULL,\n" +
                "\t\"ID_USUARIO\"\tINTEGER NOT NULL,\n" +
                "\tFOREIGN KEY(\"ID_PIZZA\") REFERENCES \"PIZZA\"(\"ID_PIZZA\"),\n" +
                "\tFOREIGN KEY(\"ID_USUARIO\") REFERENCES \"USUARIO\"(\"ID_USUARIO\"),\n" +
                "\tPRIMARY KEY(\"ID_PIZZA\",\"ID_USUARIO\")\n" +
                ");")

        db?.execSQL("CREATE TABLE \"USUARIO\" (\n" +
                "\t\"ID_USUARIO\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"NOMBRE_USUARIO\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"CONTRASENNA\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID_USUARIO\")\n" +
                ");")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createDB(db)
    }

    private fun getMaxIdFromUsuario(): Int {
        var id = 1
        try {
            val db: SQLiteDatabase = this.readableDatabase
            val cursorUsuario: Cursor = db.rawQuery("SELECT MAX(\"ID_USUARIO\") FROM USUARIO", null)
            if (cursorUsuario.moveToFirst()) {
                id = cursorUsuario.getInt(0)
            }
            cursorUsuario.close()
            println("Id máxima: $id")
        } catch (e: Exception) {
            println("Algo falló")
        }
        return id
    }

    fun registrarUsuario(usuario: Usuario):Long {
        var id: Long = 0
        try {
            var db: SQLiteDatabase = this.writableDatabase
            var contentValues = ContentValues()
            val idUsu: Int = getMaxIdFromUsuario() + 1
            contentValues.put("ID_USUARIO", idUsu)
            contentValues.put("NOMBRE_USUARIO", usuario.nombreUsuario)
            contentValues.put("CONTRASENNA", usuario.contrasenna)
            id = db.insert("USUARIO", null, contentValues)

            println("Se añadio bien")
        } catch (e: Exception) {
            println("Algo falló")
        }

        return id
    }

    fun usuarioExiste(usuario: Usuario):Boolean {
        var existe = false

        try {
            val db: SQLiteDatabase = this.readableDatabase
            val cursorUsuario: Cursor = db.rawQuery("SELECT * FROM USUARIO WHERE NOMBRE_USUARIO = \"${usuario.nombreUsuario}\"", null)
            if (cursorUsuario.moveToFirst()) {
                println("${cursorUsuario.getString(1)} - ${usuario.nombreUsuario}")
                if (cursorUsuario.getString(1).equals(usuario.nombreUsuario, true)) existe = true
            }
            
            println("Usuario existe $existe")
        } catch (e: Exception) {
            println("Algo falló")
        }

        return existe
    }

    fun getContrasennaFromUsuario(usuario: Usuario) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db?.execSQL("DROP TABLE PIZZA_INGREDIENTE")
        db?.execSQL("DROP TABLE PIZZA_USUARIO")
        db?.execSQL("DROP TABLE INGREDIENTE")
        db?.execSQL("DROP TABLE PIZZA")
        db?.execSQL("DROP TABLE USUARIO")

        createDB(db)
    }
}