package ufrn.eaj.daltonismo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ufrn.eaj.daltonismo.databinding.ActivityTesteBinding

class TesteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding : ActivityTesteBinding

        super.onCreate(savedInstanceState)

        var img = intent.extras?.getInt("IMG")
        setContentView(R.layout.activity_teste)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teste)

        binding.img.setImageResource(img!!)

        binding.btnOK.setOnClickListener {
            val intent = Intent()
            val bundle = Bundle()
            bundle.putInt("VALOR", binding.digite.text.toString().toInt())
            intent.putExtras(bundle)
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.btnCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}