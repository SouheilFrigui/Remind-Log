package com.example.remindlog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private TextView RegText;
    private EditText UserName, UserPassword;
    private Button BtnLogin;

    OnLoginFormActivityListener loginFormActivityListener;
    public interface OnLoginFormActivityListener {
        public void performRegister();
        public void performLogin(String name);

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        UserName=view.findViewById(R.id.UserIDLogin);
        UserPassword=view.findViewById(R.id.UserPwdLogin);
        BtnLogin=view.findViewById(R.id.button);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
        RegText=view.findViewById(R.id.SwitchtoSignup);
        RegText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginFormActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        loginFormActivityListener= (OnLoginFormActivityListener) activity;
    }


    private void performLogin(){
        final String username=UserName.getText().toString();
        String userpassword=UserPassword.getText().toString();

        Call<User> call=MainActivity.api_interface.performUserLogin(username,userpassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("OK")){
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }
                else if (response.body().getResponse().equals("Failed")){
                    MainActivity.prefConfig.displayToast("L'identification a échoué");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        UserName.setText("");
        UserPassword.setText("");
    }
}