package dev.habiburrahman.tutorialseries

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.habiburrahman.tutorialseries.activities.Target1Activity
import dev.habiburrahman.tutorialseries.databinding.ActivityMainBinding
import dev.habiburrahman.tutorialseries.models.DataContractModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var targetActivity1Launcher = registerForActivityResult(Target1Activity.Contract()){
        // do something
        when(it.isSuccess){
            true -> {
                when (it.isFinish){
                    true -> {/*
                        Toast.makeText(this, "Do Something", Toast.LENGTH_LONG)
                            .show()*/
                        binding.tvMain.text = "Do Something"
                    }
                    else -> {
                        // do something
                    }
                }
            }
            else -> {
                // do something
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMain.text = "This is First Launch"

        binding.mBtnMain.setOnClickListener {
        val dataContract = DataContractModel("target_activity_1")
            dataContract.user.name = "Jhon"
            dataContract.user.email = "example@mail.com"
            targetActivity1Launcher.launch(dataContract)
        }

    }

}