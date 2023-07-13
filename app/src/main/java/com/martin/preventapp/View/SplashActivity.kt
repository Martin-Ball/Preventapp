package com.martin.preventapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.martin.preventapp.R

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000 // Tiempo en milisegundos (3 segundos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setea el archivo de diseño del splash screen
        setContentView(R.layout.activity_splash)

        // Crea un handler para retrasar el inicio de la siguiente actividad
        Handler().postDelayed({
            // Inicia la siguiente actividad después del tiempo de espera
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            // Cierra la actividad del splash screen
            finish()
        }, splashTimeOut)
    }
}