package com.example.arrondissement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreFragment newInstance(String param1, String param2) {
        ScoreFragment fragment = new ScoreFragment();
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
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String URL = "http://10.10.26.94:80/lpiot/index.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                    try {
                        JSONArray obj = new JSONArray(response);
                        String [] activite=new String[obj.length()];
                     /*  System.out.println("aaa "+obj.get(1));
                       ArrayList<Object> test = new ArrayList<>();
                       test.add(obj.get(1));
                       for (Object truc:test){
                           System.out.println("truc : "+truc);
                       }*/

                        for (int i =0;i< obj.length();i++){
                            System.out.println("obj get "+obj.getString(i));
                            activite[i] =obj.getString(i);
                            activite[i] = deleteChar(activite[i]);
                        }


                        System.out.println("qcm "+activite[1]);


                        /*for (int i =0;i< obj.length();i++){
                            activite = activite + obj.getString(i);
                        }*/
                        //Toast.makeText(getActivity().getApplicationContext(), activite, Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        System.out.println(e);
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error "+error);
            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("verif","score");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public String deleteChar(String activite){
        activite = activite.replaceAll("[ +^\" ]","");
        activite = activite.replaceAll("\\\\", "");
        activite = activite.replaceAll("\\[","");
        activite = activite.replaceAll("]",",");
        activite = activite.replaceAll(","," ");

        return activite;
    }
}