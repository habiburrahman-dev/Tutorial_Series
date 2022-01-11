package dev.habiburrahman.tutorialseries.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import dev.habiburrahman.tutorialseries.databinding.ActivityTarget1Binding
import dev.habiburrahman.tutorialseries.models.ActivityResultModel
import dev.habiburrahman.tutorialseries.models.DataContractModel
import dev.habiburrahman.tutorialseries.utils.Constant.CONTRACT_DATA
import dev.habiburrahman.tutorialseries.utils.Constant.FINISHED

class Target1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTarget1Binding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTarget1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        when {
            intent.hasExtra(CONTRACT_DATA) -> {
                val dataContract = intent.getSerializableExtra(CONTRACT_DATA) as DataContractModel
                binding.tvTarget1.text =
                    "Nama: ${dataContract.user.name}\nEmail: ${dataContract.user.email}"
            }
        }

        binding.mBtnFinish.setOnClickListener {
            setResult(RESULT_OK, intent.putExtra(FINISHED, true))
            finish()
        }

    }

    class Contract : ActivityResultContract<DataContractModel, ActivityResultModel>() {
        override fun createIntent(context: Context, input: DataContractModel?): Intent {
            return Intent(context, Target1Activity::class.java)
                .putExtra(CONTRACT_DATA, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): ActivityResultModel {
            return ActivityResultModel(
                isSuccess = resultCode == RESULT_OK,
                isFinish = intent?.getBooleanExtra(FINISHED, false)
            )
        }

    }

}