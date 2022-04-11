package co.icesi.edu.facebookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.icesi.edu.facebookapp.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var user1= User("alfa@gmail.com","","Lex" , "aplicacionesmoviles")

        var user2= User("beta@gmail.com", "","Luthor", "aplicacionesmoviles")


        binding.logInBtn.setOnClickListener{
            var email = binding.editTextLgInEmail.text.toString()
            var password = binding.editTextLginPass.text.toString()


            if(email.equals(user1.email) && password.equals(user1.password) || email.equals(user2.email) && password.equals(user2.password)) {
                val i = Intent(applicationContext, Home::class.java).apply {}
                startActivity(i)
            }else{
                Toast.makeText(this, "Error en las credenciales, rectifique las credenciales e intente de nuevo", Toast.LENGTH_SHORT).show()
            }


        }

    }
}