package com.example.remindlog;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import java.util.Date;

public class Call_ext extends Call_Receiver {
    Context context;

    @Override
    protected void onIncomingCallStarted(final Context ctx, String number, Date start)
    {
        Toast.makeText(ctx, String.format("Appel entrant de %s", number), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        Toast.makeText(ctx,"Appel manqué "+ number,Toast.LENGTH_LONG).show();
        context =   ctx;

        final Intent intent = new Intent(context, C_Dialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("phone_no",number);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                context.startActivity(intent);
            }
        },500);
    }
}