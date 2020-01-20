package br.com.cecilia.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)

        //Criando a ação do clique do botão Entrar

        btnEntrar.setOnClickListener {

            val usuario = edtNome.text.toString().trim()
            val senha = edtSenha.text.toString().trim()
            //Também poderíamos usar: val senha = edtSenha.text as Float

            val recuperarNome = minhaPreferencia.getString("nome", "Erro no SharedPreference")
            val recuperarSenha = minhaPreferencia.getString("senha", "Erro 404")

            //Condição para verificar se usuário e senha estão corretos
            if (usuario.isEmpty()){
                //Verificando se o usuário está vazio
                Toast.makeText(this@LoginActivity, "Usuário vazio", Toast.LENGTH_LONG).show()
            } else if (senha.isEmpty()){
                //Verificando se a senha está vazia
                Toast.makeText(this@LoginActivity, "Senha vazia", Toast.LENGTH_LONG).show()
            } else if (usuario == recuperarNome){
                if (senha ==  recuperarSenha) {
                    //Criando a Intent
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    //Criando um Alert
                    AlertDialog.Builder(this@LoginActivity)
                        .setTitle("Erro de autentificação")
                        .setMessage("Senha incorreta")
                        .setPositiveButton("OK!"){_,_ ->
                        }
                        .create()
                        .show()
                }
            } else {
                AlertDialog.Builder(this@LoginActivity)
                    .setTitle("Erro de autentificação")
                    .setMessage("Usuário incorreto")
                    .setPositiveButton("OK!"){_,_ ->
                    }
                    .create()
                    .show()
            }
        }

        btnCadastrar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }
    }
}
