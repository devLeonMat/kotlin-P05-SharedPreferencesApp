package com.rleon.sharedpreferences

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : AppCompatActivity() {

    var preferencias: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // este es el modo en que se crea el archivo, sino se ncesita multiples preferencias usamos las shared por defecto
        preferencias = PreferenceManager.getDefaultSharedPreferences(this)

        // si se necesitan multiples preferencias
        //preferencias = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)

        // conesto colocamos el toolbar
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        ponerPrefSiExisten()

        btn_ingresar.onClick {
            val email = et_email.text.toString()
            val pass = et_pass.text.toString()

            if (logeo(email, pass)) {
                startActivity(intentFor<MainActivity>().newTask().clearTask())
                guardarPreferencias(email, pass)
            }
        }
    }

    fun validarEmail(email: String): Boolean {
        //return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validarPass(pass: String): Boolean {
        return pass.length >= 5
    }

    fun logeo(email: String, pass: String): Boolean {
        if (!validarEmail(email)) {
            toast("Email No valido, Intenta de Nuevo")
            return false
        } else if (!validarPass(pass)) {
            toast("Debe contener Minimo 5 Caracteres")
            return false
        } else {
            return true
        }
    }

    fun guardarPreferencias(email: String, pass: String) {
        if (switchRecordar.isChecked) {
            preferencias!!.edit()
                    .putString("email", email)
                    .putString("pass", pass)
                    .apply()
        }
    }

    fun ponerPrefSiExisten() {
        val email = preferencias!!.getString("email", "")
        val pass = preferencias!!.getString("pass", "")

        if (!email.isNullOrEmpty() && !pass.isNullOrEmpty()) {
            et_email.setText(email)
            et_pass.setText(pass)
            switchRecordar.isChecked = true
        }


    }


}
