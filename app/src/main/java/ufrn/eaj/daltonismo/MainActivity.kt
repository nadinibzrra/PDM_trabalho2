package ufrn.eaj.daltonismo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import ufrn.eaj.daltonismo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var res1:Int = 0
    var res2:Int = 0
    var res3:Int = 0
    var resposta = Resposta(res1.toString(),res2.toString(),res3.toString())

    val setResposta_1launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            res1 = result.data!!.getIntExtra("VALOR", 0)
            resposta = Resposta(res1.toString(),res2.toString(),res3.toString())
            //binding.resposta = resposta
            resposta.also { binding.resposta = it }
        }else{
            Toast.makeText(this, getString(R.string.cancel), Toast.LENGTH_SHORT).show()
        }
    }
    val setResposta_2launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            res2 = result.data!!.getIntExtra("VALOR", 0)
            resposta = Resposta(res1.toString(),res2.toString(),res3.toString())
            resposta.also { binding.resposta = it }
        }else{
            Toast.makeText(this, getString(R.string.cancel), Toast.LENGTH_SHORT).show()
        }
    }
    val setResposta_3launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            res3 = result.data!!.getIntExtra("VALOR", 0)
            resposta = Resposta(res1.toString(),res2.toString(),res3.toString())
            resposta.also { binding.resposta = it }
        }else{
            Toast.makeText(this, getString(R.string.cancel), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnTeste1.setOnClickListener {
            val intent = Intent(this, TesteActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("IMG", R.drawable.img1)
            intent.putExtras(bundle)
            setResposta_1launcher.launch(intent)
        }

        binding.btnTeste2.setOnClickListener {
            val intent = Intent(this, TesteActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("IMG", R.drawable.img2)
            intent.putExtras(bundle)
            setResposta_2launcher.launch(intent)
        }

        binding.btnTeste3.setOnClickListener {
            val intent = Intent(this, TesteActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("IMG", R.drawable.img3)
            intent.putExtras(bundle)
            setResposta_3launcher.launch(intent)
        }

        binding.btnVerificar.setOnClickListener {
            if(res1 != 0 && res2 != 0 && res3 != 0){
                if(res1 == 74 && res2 == 8 && res3 == 3) {
                    binding.resultadoDoTeste.text = getString(R.string.vc_nao_e_daltonico)
                    Toast.makeText(this, getString(R.string.vc_enxerga_mto_bem), Toast.LENGTH_LONG).show()
                }else{
                    binding.resultadoDoTeste.text = getString(R.string.vc_e_daltonico)
                    Toast.makeText(this, getString(R.string.procure_um_medico), Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, getString(R.string.faca_o_teste), Toast.LENGTH_LONG).show()
            }
        }
    }
}