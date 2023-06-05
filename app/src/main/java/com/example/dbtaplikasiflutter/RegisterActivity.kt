package com.example.dbtaplikasiflutter

import WelcomeActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        usernameEditText = view.findViewById(R.id.usernameEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        registerButton = view.findViewById(R.id.registerButton)

        sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidCredentials(username, password)) {
                saveUserLoggedIn(username)
                navigateToWelcomePage()
            } else {
                Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // Validasi bahwa username dan password tidak boleh kosong
        if (username.isEmpty() || password.isEmpty()) {
            return false
        }

        // Validasi panjang username minimal 4 karakter
        if (username.length < 4) {
            return false
        }

        // Validasi panjang password minimal 6 karakter
        if (password.length < 6) {
            return false
        }

        // Validasi bahwa username tidak boleh mengandung spasi
        if (username.contains(" ")) {
            return false
        }

        // Validasi bahwa password harus mengandung angka
        val containsDigit = password.any { it.isDigit() }
        if (!containsDigit) {
            return false
        }

        return true
    }

    private fun saveUserLoggedIn(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    private fun navigateToWelcomePage() {
        val intent = Intent(requireContext(), WelcomeActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
