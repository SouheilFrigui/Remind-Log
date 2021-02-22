package com.example.remindlog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    private EditText UserName, Email , UserPasswod;
    private Button BtnRegister;
    private TextView LogText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        UserName=view.findViewById(R.id.UserIDSignUp);
        Email=view.findViewById(R.id.Email);
        UserPasswod=view.findViewById(R.id.UserPwdSignUp);
        BtnRegister=view.findViewById(R.id.button);
        BtnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DoperformRegistration();
            }
        });
        LogText=view.findViewById(R.id.SwitchtoLogin);
        LogText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // IF LEAVE THE CONTAINER AS FINAL THE TEXTVIEW SWITCHER WILL WORK ONLY ONCE
         // inflater.inflate(R.layout.fragment_login,container,true);
                FragmentTransaction frag=getFragmentManager().beginTransaction();
                frag.replace(R.id.fragment_container,new LoginFragment());
                frag.commit();
            }
        });
        return view;
    }

    public void DoperformRegistration(){
        String Name= UserName.getText().toString();
        String email=Email.getText().toString();
        String Password=UserPasswod.getText().toString();
        Call<User> call=MainActivity.api_interface.performRegistration(Name,email,Password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                assert response.body() != null;
                switch (response.body().getResponse()) {
                    case "ok":
                        MainActivity.prefConfig.displayToast("Inscription réussite !");
                        break;
                    case "error":
                        MainActivity.prefConfig.displayToast("Échec de l'inscription !");
                        break;
                    case "exist":
                        MainActivity.prefConfig.displayToast("L'utilisateur éxiste déjà  !");
                        break;
                }

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        UserName.setText("");
        Email.setText("");
        UserPasswod.setText("");
    }
}