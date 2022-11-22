package com.example.pizzeria

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pizzeria.clases.Contrasenna
import com.example.pizzeria.clases.Pizza
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
                "\t\"NOMBRE_INGREDIENTE\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID_PIZZA\",\"NOMBRE_INGREDIENTE\")\n" +
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

    private fun getIdFromUsuario(usuario: Usuario?): Int {
        var id = -1
        if (usuario != null) {
            try {
                val db: SQLiteDatabase = this.readableDatabase
                val cursorUsuario: Cursor = db.rawQuery(
                    "SELECT (\"ID_USUARIO\") FROM USUARIO WHERE NOMBRE_USUARIO = \"${usuario.nombreUsuario.lowercase()}\"",
                    null
                )
                if (cursorUsuario.moveToFirst()) {
                    id = cursorUsuario.getInt(0)
                }
                cursorUsuario.close()
                println("Id: $id")
            } catch (e: Exception) {
                println("Algo falló")
            }
        }
        return id
    }

    private fun getMaxIdFromUsuario(): Int {
        var id = 0
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
            contentValues.put("NOMBRE_USUARIO", usuario.nombreUsuario.lowercase())
            contentValues.put("CONTRASENNA", Contrasenna.cifrar(usuario.contrasenna))
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
            val cursorUsuario: Cursor = db.rawQuery("SELECT * FROM USUARIO WHERE NOMBRE_USUARIO = \"${usuario.nombreUsuario.lowercase()}\"", null)
            if (cursorUsuario.moveToFirst()) {
                if (cursorUsuario.getString(1).equals(usuario.nombreUsuario.lowercase(), true)) existe = true
            }
            
            println("Usuario existe $existe")
        } catch (e: Exception) {
            println("Algo falló")
        }

        return existe
    }

    fun getContrasennaFromUsuario(usuario: Usuario):String? {
        var contrasenna: String? = null

        try {
            val db: SQLiteDatabase = this.readableDatabase
            val cursorUsuario: Cursor = db.rawQuery("SELECT (\"CONTRASENNA\") FROM USUARIO WHERE NOMBRE_USUARIO = \"${usuario.nombreUsuario.lowercase()}\"", null)
            if (cursorUsuario.moveToFirst()) {
                contrasenna = cursorUsuario.getString(0)
            }
            cursorUsuario.close()
        } catch (e: Exception) {
            println("Algo falló")
        }

        return contrasenna
    }

    private fun insertarIngredientes(ingredientes: List<String>):Long {
        var id: Long = 0
        try {
            var db: SQLiteDatabase = this.writableDatabase
            for (ingrediente: String in ingredientes) {
                var contentValues = ContentValues()
                contentValues.put("NOMBRE_INGREDIENTE", ingrediente)
                id = db.insert("INGREDIENTE", null, contentValues)

                println("Se añadio bien")
            }
        } catch (e: Exception) {
            println("Algo falló")
        }
        return id
    }

    private fun getMaxIdFromPizza(): Int {
        var id = 1
        try {
            val db: SQLiteDatabase = this.readableDatabase
            val cursorPizza: Cursor = db.rawQuery("SELECT MAX(\"ID_PIZZA\") FROM PIZZA", null)
            if (cursorPizza.moveToFirst()) {
                id = cursorPizza.getInt(0)
            }
            cursorPizza.close()
            println("Id máxima: $id")
        } catch (e: Exception) {
            println("Algo falló")
        }
        return id
    }

    fun insertarPizza(pizza: Pizza):Long {
        insertarIngredientes(pizza.ingredientes)

        var id: Long = 0
        try {
            var db: SQLiteDatabase = this.writableDatabase
            var contentValues = ContentValues()
            val idPizza: Int = getMaxIdFromPizza() + 1
            contentValues.put("ID_PIZZA", idPizza)
            contentValues.put("PRECIO", pizza.calcularPrecio())
            contentValues.put("TAMANNO", pizza.tamanno.toString())
            contentValues.put("SALSA", pizza.salsas[0])
            id = db.insert("PIZZA", null, contentValues)

            println("Se añadio bien")
        } catch (e: Exception) {
            println("Algo falló")
        }

        insertarPizzaIngredientes(id, pizza.ingredientes)
        return id
    }

    private fun insertarPizzaIngredientes(idPizza: Long, ingredientes: List<String>): Long {
        var id: Long = 0
        try {
            var db: SQLiteDatabase = this.writableDatabase
            for (ingrediente: String in ingredientes) {
                var contentValues = ContentValues()
                contentValues.put("ID_PIZZA", idPizza)
                contentValues.put("NOMBRE_INGREDIENTE", ingrediente)
                id = db.insert("PIZZA_INGREDIENTE", null, contentValues)

                println("Se añadio bien")
            }
        } catch (e: Exception) {
            println("Algo falló")
        }
        return id
    }

    fun insertarPizzaUsuario(idPizza: Long, usuario: Usuario?):Long {
        var id: Long = 0
        try {
            var db: SQLiteDatabase = this.writableDatabase
            var contentValues = ContentValues()
            contentValues.put("ID_PIZZA", idPizza)
            contentValues.put("ID_USUARIO", getIdFromUsuario(usuario))
            id = db.insert("PIZZA_USUARIO", null, contentValues)

            println("Se añadio bien")
        } catch (e: Exception) {
            println("Algo falló")
        }
        return id
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