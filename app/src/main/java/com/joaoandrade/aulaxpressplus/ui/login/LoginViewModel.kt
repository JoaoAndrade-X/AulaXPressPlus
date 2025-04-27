package com.joaoandrade.aulaxpressplus.ui.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.joaoandrade.aulaxpressplus.R
import com.joaoandrade.aulaxpressplus.shared.bases.BaseViewModel
import com.joaoandrade.aulaxpressplus.shared.enums.Destination
import com.joaoandrade.aulaxpressplus.shared.enums.NavigationMode
import com.joaoandrade.aulaxpressplus.shared.providers.NavigationProvider
import com.joaoandrade.aulaxpressplus.shared.providers.StringResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel
    @Inject
    constructor(
        private val stringResourceProvider: StringResourceProvider,
        override val navigationProvider: NavigationProvider,
    ) : ViewModel(), BaseViewModel<LoginUiState>, LoginCommandReceiver {
        private val _uiState = MutableStateFlow(LoginUiState())
        override val uiState = _uiState.asStateFlow()

        private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

        override fun initGoogleSignInLauncher(launcher: ActivityResultLauncher<Intent>) {
            this.googleSignInLauncher = launcher
        }

        override fun signInWithGoogle(context: Context) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(stringResourceProvider.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            googleSignInLauncher.launch(googleSignInClient.signInIntent)
        }

        override fun handleGoogleSignInResult(data: Intent?) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let { idToken ->
                    val credential = GoogleAuthProvider.getCredential(idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Login bem-sucedido
                                _uiState.update { it.copy(errorMessage = "Login com Google realizado!") }
                            } else {
                                // Tratar erro
                                _uiState.update { it.copy(errorMessage = "Erro ao fazer login com Google.") }
                            }
                        }
                }
            } catch (e: ApiException) {
                _uiState.update { it.copy(errorMessage = "Erro ao autenticar com Google.") }
            }
        }

        override fun goToRegister() {
            navigationProvider.navigate(Destination.REGISTER_DESTINATION)
        }

        override fun changePasswordVisibility() {
            val visible = uiState.value.passwordVisible
            _uiState.update { it.copy(passwordVisible = !visible) }
        }

        override fun onEmailValueChanged(email: String) {
            _uiState.update { it.copy(email = email) }
        }

        override fun onPasswordValueChanged(password: String) {
            _uiState.update { it.copy(password = password) }
        }
}