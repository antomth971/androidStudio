package com.example.arrondissement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccueilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccueilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GridView simpleGrid;
    int logos[] = {R.drawable.img_district1, R.drawable.img_district2, R.drawable.img_district3, R.drawable.img_district4,
            R.drawable.img_district5, R.drawable.img_district6, R.drawable.img_district7, R.drawable.img_district8, R.drawable.img_district9,
            R.drawable.img_district10, R.drawable.img_district11, R.drawable.img_district12, R.drawable.img_district13,R.drawable.img_district14,R.drawable.img_district15,R.drawable.img_district16
            ,R.drawable.img_district17,R.drawable.img_district18,R.drawable.img_district19,R.drawable.img_district20};

    public AccueilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccueilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccueilFragment newInstance(String param1, String param2) {
        AccueilFragment fragment = new AccueilFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        simpleGrid = (GridView) getView().findViewById(R.id.simpleGridView2); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapterImg customAdapter = new CustomAdapterImg(getActivity().getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(getActivity().getApplication(), QuestionReponse.class);
                intent.putExtra("image", logos[position]); // put image data in Intent
                intent.putExtra("arrondissement",position+1);
                startActivity(intent); // start Intent*/

            }
        });

    }
}