package br.com.cecilia.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)

        val name = minhaPreferencia.getString("nome", "Erro no Shared Preference")
        val lastname = minhaPreferencia.getString("sobrenome", "Erro 404")
        val emailmessage = minhaPreferencia.getString("email", "Erro no Shared Preference")
        val gender = minhaPreferencia.getString("sexo", "Erro no Shared Preference")

        txvNomeUsuario.text = "$name $lastname"
        txvEmail.text = emailmessage
        txvSexo.text = gender

        btnSair.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        btnSite.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebActivity::class.java))
        }
    }
}
