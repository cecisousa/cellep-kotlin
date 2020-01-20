package br.com.cecilia.estacaohack

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Criando o Shared Preference
        val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)

        //Criando o Editor
        val meuEditor = minhaPreferencia.edit()

        //Criando uma lista paara o Spinner
        val listaSexo = arrayListOf("Selecione o sexo", "Feminino", "Masculino", "Outro")

        // Criando o Adapter
        val adapterSexo = ArrayAdapter(this@CadastroActivity, android.R.layout.simple_spinner_dropdown_item, listaSexo)

        spnGender.adapter = adapterSexo
        
        btnRegister.setOnClickListener {
            val nome = edtName.text.toString().trim()
            val sobrenome = edtLastname.text.toString().trim()
            val email = edtEmail.text.toString().trim().toLowerCase()
            val senha = edtPass.text.toString().trim()

            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this@CadastroActivity, "Por favor, prencha todos os campos vazios!", Toast.LENGTH_LONG).show()
            } else if (spnGender.selectedItem == "Selecione o sexo"){
                Toast.makeText(this@CadastroActivity, "Não esqueça de selecinar o sexo", Toast.LENGTH_LONG).show()
            } else {
                // Gravando as informações dentro do Shared Preference
                meuEditor.putString("nome", nome).apply()
                meuEditor.putString("sobrenome", sobrenome).apply()
                meuEditor.putString("email", email).apply()
                meuEditor.putString("senha", senha).apply()
                meuEditor.putString("sexo", spnGender.selectedItem.toString()).apply()

                // Criando um Alert para o Shared Preference
                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Sucesso")
                    .setMessage("Usuário cadastrado com sucesso!")
                    .setPositiveButton("OK"){_,_ ->
                        onBackPressed()
                    }
                    .create()
                    .show()
            }
        }
    }
}
