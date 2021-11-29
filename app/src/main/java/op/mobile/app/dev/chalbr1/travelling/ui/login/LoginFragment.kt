package op.mobile.app.dev.chalbr1.travelling.ui.login

import android.os.Bundle
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

/**
 * Login fragment
 *
 * @constructor Create empty Login fragment
 */
class LoginFragment : Fragment() {
    /**
     * firebase auth var
     */
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
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        /**
         * gets the firebase auth instance
         */
        auth = FirebaseAuth.getInstance()

        val btnLogin: Button = view.findViewById(R.id.btn_login)
        val etEmailAddress: EditText = view.findViewById(R.id.et_email_address)
        val etPassword: EditText = view.findViewById(R.id.et_password)
        val tvSignUp: TextView = view.findViewById(R.id.btn_sign_up)

        /**
         * checks if the user entered has been added to the user
         */
        btnLogin.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()

            when {
                email.isEmpty() ->
                    etEmailAddress.error = getString(R.string.email_required)
                password.isEmpty() ->
                    etPassword.error = getString(R.string.password_required)
                else -> {
                    firebaseAuthSignInWithEmailAndPassword(email, password)
                }
            }
        }
        /**
         * sets an onclick listener to redirect to the register fragment
         */
        tvSignUp.setOnClickListener {
            val action =
                LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }

    /**
     * Firebase auth sign in with email and password
     *
     * function to check if user exists in the firebase authentication
     * @param email
     * @param password
     */
    private fun firebaseAuthSignInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToHomeFragment()
                    view?.findNavController()?.navigate(action)
                } else {
                    Toast.makeText(
                        activity,
                        getString(R.string.sign_in_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}