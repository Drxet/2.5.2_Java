package com.example.test2

import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_HINT = "Логин"
        const val PASSWORD_HINT = "Пароль"
        const val CORRECT_LOGIN = "burmalda"

        const val TEXT_CORRECT = "Верно"
        const val TEXT_INCORRECT = "Вы ошиблись в логине или пароле"
    }

    private var correctPassword = "bobik228"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val editTextLogin = findViewById<EditText>(R.id.editTextLogin)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        editTextLogin.hint = LOGIN_HINT
        editTextPassword.hint = PASSWORD_HINT

        buttonLogin.setOnClickListener {
            hideKeyboard()

            val enteredLogin = editTextLogin.text.toString()
            val enteredPassword = editTextPassword.text.toString()

            if (enteredLogin == CORRECT_LOGIN && enteredPassword == correctPassword) {
                textViewResult.text = TEXT_CORRECT
                textViewResult.setTextColor(Color.GREEN)
            } else {
                textViewResult.text = TEXT_INCORRECT
                textViewResult.setTextColor(Color.RED)
                editTextLogin.text.clear()
                editTextPassword.text.clear()
            }
        }
    }

    private fun hideKeyboard() {
        currentFocus?.clearFocus()
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.ime())

        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}
