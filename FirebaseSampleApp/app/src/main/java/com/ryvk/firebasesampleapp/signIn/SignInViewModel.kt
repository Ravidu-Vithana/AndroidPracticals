package com.ryvk.firebasesampleapp.signIn

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.ViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.ryvk.firebasesampleapp.R

class SignInViewModel : ViewModel() {
    private val TAG = "SignInViewModel"

    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    suspend fun signInWithGoogleButton(context: Context){

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(context)

        if (auth.currentUser != null) {
            Log.d(TAG, "User already signed in")
            return
        }

        val signInWithGoogleOption = GetSignInWithGoogleOption
            .Builder(serverClientId = context.getString(R.string.default_web_client_id))
            .setNonce("FirebaseSampleAppGoogleLogin")
            .build()


        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()
        getCredential(context, request, false)
    }

    suspend fun signInWithGoogleBottomSheet(context: Context, hasFilter: Boolean = true){
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(hasFilter)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .setNonce("FirebaseSampleAppGoogleLogin")
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        getCredential(context, request, true)
    }

    suspend fun getCredential(context: Context, request: GetCredentialRequest, isBottomSheet: Boolean){
        try {
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )
            handleSignIn(result.credential)
        } catch (e: NoCredentialException){
            Log.d(TAG, "No saved credentials, user needs to select account")
            if(isBottomSheet){
                signInWithGoogleBottomSheet(context,false)
            }else{
                Toast.makeText(context, "No saved accounts available. Please sign in from the device.", Toast.LENGTH_LONG).show()
            }
        } catch (e: GetCredentialException) {
            Log.e(TAG, "Sign-in failed: ${e.message}", e)
        }
    }

    private fun handleSignIn(credential: Credential) {
        when (credential) {
            is CustomCredential -> {
                if (credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                }
                else {
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                }else{
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
    fun updateUI(user: FirebaseUser?){
        Log.d(TAG, "updateUI")

    }
}