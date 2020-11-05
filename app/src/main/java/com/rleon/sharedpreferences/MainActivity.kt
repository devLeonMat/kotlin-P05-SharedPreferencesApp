package com.rleon.sharedpreferences

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity() {

    var toolbarColaps: CollapsingToolbarLayout? = null

    var prefencias: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarColaps = find<CollapsingToolbarLayout>(R.id.ctb)
        toolbarColaps!!.title = "Beneficios de usar kotlin" // titulo del toolbar

        // conesto colocamos el toolbar
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        prefencias = PreferenceManager.getDefaultSharedPreferences(this)

        // si se necesitan multiples preferencias
        //preferencias = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item!!.itemId) {
        R.id.MenuSalir -> {
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
            true
        }

        R.id.menuOlvidar -> {
            prefencias!!.edit().clear().apply()
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
            true
        }

        else -> false

    }


}



























