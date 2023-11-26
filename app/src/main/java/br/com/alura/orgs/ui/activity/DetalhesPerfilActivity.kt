package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.DetalhesDoPerfilBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class DetalhesPerfilActivity : UsuarioBaseActivity() {

    private val binding by lazy {
        DetalhesDoPerfilBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSair()
        preencheCampos()
    }

    private fun preencheCampos() {
        lifecycleScope.launch {
            usuario
                .filterNotNull()
                .collect { usuarioLogado ->
                binding.textView.text = usuarioLogado.id
                binding.textView2.text = usuarioLogado.nome
            }
        }
    }

    private fun configuraBotaoSair() {
        val sair = binding.detalhesDoPerfilSair
        sair.setOnClickListener {
            lifecycleScope.launch {
                deslogaUsuario()
            }
        }
    }


}