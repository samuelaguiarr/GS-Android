package samuelaguiarr.com.github.alunos_rm550212_rm98007

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import samuelaguiarr.com.github.alunos_rm550212_rm98007.viewmodel.ItemsAdapter
import samuelaguiarr.com.github.alunos_rm550212_rm98007.viewmodel.ItemsViewModel
import samuelaguiarr.com.github.alunos_rm550212_rm98007.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        val editText = findViewById<EditText>(R.id.editText)
        val editEvento = findViewById<EditText>(R.id.editEvento)
        val editImpacto = findViewById<EditText>(R.id.editImpacto)
        val editData = findViewById<EditText>(R.id.editData)
        val editAfetadas = findViewById<EditText>(R.id.editAfetadas)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val nome = editText.text.toString()
            val evento = editEvento.text.toString()
            val impacto = editImpacto.text.toString()
            val data = editData.text.toString()
            val afetadasStr = editAfetadas.text.toString()

            if (nome.isEmpty() || evento.isEmpty() || impacto.isEmpty() || data.isEmpty() || afetadasStr.isEmpty()) {
                editText.error = getString(R.string.error_empty_fields)
                return@setOnClickListener
            }

            val numeroAfetadas = afetadasStr.toIntOrNull()
            if (numeroAfetadas == null || numeroAfetadas <= 0) {
                editAfetadas.error = getString(R.string.error_invalid_number)
                return@setOnClickListener
            }

            viewModel.addItem(nome, evento, impacto, data, afetadasStr)
            editText.text.clear()
            editEvento.text.clear()
            editImpacto.text.clear()
            editData.text.clear()
            editAfetadas.text.clear()
        }

        val btnPop = findViewById<Button>(R.id.btnMostrarPopup)
        btnPop.setOnClickListener {
            val mensagem = getString(R.string.integrantes_message)
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.integrantes_title))
            builder.setMessage(mensagem)
            builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}
