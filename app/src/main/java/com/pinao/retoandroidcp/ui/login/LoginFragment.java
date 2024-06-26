package com.pinao.retoandroidcp.ui.login;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.pinao.retoandroidcp.R;
import com.pinao.retoandroidcp.databinding.FragmentLoginBinding;
import com.pinao.retoandroidcp.ui.candy.CandyFragment;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private final String TAG = "LoginFragment";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Configurar Google SignIn
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LoginViewModel notificationsViewModel =
                new ViewModelProvider(this).get(LoginViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btnSignInWithGoogle.setOnClickListener(v -> signIn());
        binding.btnGuest.setOnClickListener(v -> guestIn());
        return root;
    }

    private void guestIn() {
        new AlertDialog.Builder(requireActivity())
                .setTitle("CINEPLANET")
                .setMessage("Bienvenido Invitado")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    //abrir CandyFragment automaticamente
//                    Fragment fragment = new CandyFragment();
//                    requireActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment_activity_main, fragment)
//                            .addToBackStack(null)  // Opcional, para agregar la transacción a la pila de retroceso
//                            .commit();
                    dialog.dismiss();
                })
                .show();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e);
                    //---- NO SE PUDO LA CONEXION CON GOOGLE
                    if (e.getStatusCode() == 12501) {
                        new AlertDialog.Builder(requireActivity())
                                .setTitle("Error")
                                .setMessage("No se pudo conectar con Google")
                                .setPositiveButton("Aceptar", (dialog, which) -> {
                                    dialog.dismiss();
                                })
                                .show();
                    }
                    new AlertDialog.Builder(requireActivity())
                            .setTitle("CINEPLANET")
                            .setMessage("Bienvenido Jack")
                            .setPositiveButton("Aceptar", (dialog, which) -> {
                                //abrir CandyFragment automaticamente
//                                Fragment fragment = new CandyFragment();
//                                requireActivity().getSupportFragmentManager().beginTransaction()
//                                        .replace(R.id.nav_host_fragment_activity_main, fragment)
//                                        .addToBackStack(null)  // Opcional, para agregar la transacción a la pila de retroceso
//                                        .commit();
                                dialog.dismiss();
                            })
                            .show();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "onActivityResult: ", e);
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Actualizar UI con la información del usuario si es necesario
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}