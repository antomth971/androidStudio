package com.example.arrondissement;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionReponse extends AppCompatActivity {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static int pStatus = 1;
    private Handler handler = new Handler();
    ProgressBar mProgress;
    TextView time;
    Quizz quizz;

    TextView question;
    CustomerAdapterButton c;
    Vibrator vibreur;
    public Thread t;
    private int arrondissement;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_reponse);
        time=findViewById(R.id.time);
        vibreur = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //Accueil.class.onBackPressed();
        Intent intent = getIntent();
        question = findViewById(R.id.Question);
        ListView listview = findViewById(R.id.liste);
        arrondissement = intent.getIntExtra("arrondissement", 0);
        Toast.makeText(QuestionReponse.this, "arrondissement "+arrondissement, Toast.LENGTH_SHORT).show();



        ArrayList<Button> list_button = new ArrayList<>();


        String URL = "http://10.10.26.94:80/lpiot/index.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @SuppressLint({"ResourceAsColor", "WrongViewCast"})
            @Override
            public void onResponse(String response) {


                try {
                     quizz = GSON.fromJson(response, Quizz.class);

                    for (String element : quizz.getReponse()) {
                        Button button = new Button(getApplicationContext());
                        list_button.add(button);
                    }

                     c = new CustomerAdapterButton(getApplicationContext(),R.layout.activity_list, list_button, quizz.getReponse(), quizz.getGood_reponse());
                    listview.setAdapter(c);






                   // c.notifyDataSetChanged();



                    question.setText(quizz.getQuestion());


                } catch (Exception e) {
                    System.out.println("json " + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error php  2 " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("arrondissement", String.valueOf(arrondissement));
                data.put("verif", "question");
                return data;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


        t = new Thread() {


            @Override
            public void run() {
            // OnOff.setText("Courrez ! ");


            Resources res = getResources();

            mProgress =(ProgressBar)

            findViewById(R.id.circularProgressbar);
                mProgress.setProgress(0);   // Main Progress
                mProgress.setMax(10);
            //   mProgress.setProgressDrawable(drawable);




                while(pStatus <=10)

            {


                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        pStatus++;
                        mProgress.setProgress(pStatus);


                        if(pStatus==10){
                            CustomerAdapterButton.click=true;
                            c.notifyDataSetChanged();
                        }

                        time.setText("Time :\n "+pStatus);

                        System.out.println(pStatus);
                            ArrayList<Button> idButton = new ArrayList<>();
                    }

                });
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(1000); //thread will take approx 1.5 seconds to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        //}
    };

        t.start();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pStatus=0;
        CustomerAdapterButton.click=false;

    }
}