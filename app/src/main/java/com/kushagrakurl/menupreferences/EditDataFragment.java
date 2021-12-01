package com.kushagrakurl.menupreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "player 1";
    private static final String ARG_PARAM2 = "player 2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SharedPreferences sharedpreferences;
    EditText player1,player2;
    Button save;
    View view;

    public EditDataFragment() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static EditDataFragment newInstance(String param1, String param2) {
        EditDataFragment fragment = new EditDataFragment();
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
        String p1 = getArguments().getString("player1");
        String p2 = getArguments().getString("player2");

        player1.setText(p1);
        player2.setText(p2);

        view = inflater.inflate(R.layout.fragment_edit_data, container, false);
        player1=(EditText)view.findViewById(R.id.editplayer1);
        player2=(EditText)view.findViewById(R.id.editplayer2);


        save=(Button)view.findViewById(R.id.Savebutton);

        sharedpreferences = this.getActivity().getSharedPreferences("players", Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1  = player1.getText().toString();
                String p2  = player2.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("player1", p1);
                editor.putString("player2", p2);
                editor.commit();
                Toast.makeText(getActivity(), "Second Fragment", Toast.LENGTH_LONG).show();
            }
        });

        return inflater.inflate(R.layout.fragment_edit_data, container, false);

    }
}