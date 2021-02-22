package com.example.remindlog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class C_Dialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dialog dialogCur = new Dialog(C_Dialog.this);
        Window windowCur = dialogCur.getWindow();
       // this.setFinishOnTouchOutside(false);
        dialogCur.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCur.setContentView(R.layout.dialog_22);
        dialogCur.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogCur.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogCur.show();
        dialogCur.setCanceledOnTouchOutside(false);
        dialogCur.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                C_Dialog.this.finish();
            }
        });

        dialogCur.findViewById(R.id.button_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent formu= new Intent(getApplicationContext(),form.class);
                formu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                formu.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(formu);
                /*try {
                    wait(500);
                    C_Dialog.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        C_Dialog.this.finish();
                    }
                },500);

            }
        });
    }
}