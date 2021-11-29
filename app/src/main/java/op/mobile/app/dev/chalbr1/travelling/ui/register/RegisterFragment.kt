package op.mobile.app.dev.chalbr1.travelling.ui.register


import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.ui.login.LoginFragmentDirections

/**
 * Register fragment
 *
 * @constructor Create empty Register fragment
 */
class RegisterFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        auth = FirebaseAuth.getInstance()

        val btnRegister: Button = view.findViewById(R.id.btn_register)
        val etEmailAddress: EditText = view.findViewById(R.id.et_email_address)
        val etPassword: EditText = view.findViewById(R.id.et_password)
        val etConfirmPassword: EditText = view.findViewById(R.id.et_confirm_password)
        val tvSignIn: TextView = view.findViewById(R.id.return_to_login)
        tvSignIn.setOnClickListener {
            val action =
                RegisterFragmentDirections
                    .actionRegisterFragmentToLoginFragment()
            view?.findNavController()?.navigate(action)
        }
        btnRegister.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            when {
                email.isEmpty() ->
                    etEmailAddress.error = getString(R.string.email_required)
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    etEmailAddress.error = getString(R.string.email_formatted_incorrectly)
                password.isEmpty() ->
                    etPassword.error = getString(R.string.password_required)
                password.length < 8 ->
                    etPassword.error = getString(R.string.password_too_short)
                password != confirmPassword ->
                    etConfirmPassword.error = getString(R.string.password_not_match)
                else -> {
                    register(email, password)
                }
            }
        }
        return view
    }

    /**
     * Register
     *
     * @param email
     * @param password
     */
    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        activity,
                        getString(R.string.user_created),
                        Toast.LENGTH_LONG
                    ).show()
                    view?.findNavController()?.navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    )
                } else {
                    Toast.makeText(
                        activity,
                        getString(R.string.user_already_exists),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}