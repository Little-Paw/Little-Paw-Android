package com.upb.littlepaw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.upb.littlepaw.loginviewmodel.LoginViewModel
import com.upb.littlepaw.databinding.ActivityLoginBinding
import com.upb.littlepaw.homescreen.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.viewModel = viewModel



        binding.buttonLoginScreen.setOnClickListener {
            /*
            if(viewModel.validate()){
                viewModel.login(viewModel.email.value!!, viewModel.password.value!!, {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }, { error ->
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                })
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

             */
            if(viewModel.validate()) {
                viewModel.loginUser(this, viewModel.email.value!!, viewModel.password.value!!, {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }, { error ->
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                })
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
