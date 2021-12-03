package com.kushagrakurl.menupreferences;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDataFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView player1,player2;
    Button edit;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewDataFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewDataFragment newInstance(String param1, String param2) {
        ViewDataFragment fragment = new ViewDataFragment();
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

        view = inflater.inflate(R.layout.fragment_view_data, container, false);
        player1=(TextView) view.findViewById(R.id.player1);
        player2=(TextView)view.findViewById(R.id.player2);
        edit=(Button)view.findViewById(R.id.Editbutton);

        String p1 = getArguments().getString("player1");
        String p2 = getArguments().getString("player2");

        player1.setText(p1 == null? "NA": p1);
        player2.setText(p2 == null? "NA": p2);
        edit.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;//inflater.inflate(R.layout.fragment_view_data, container, false);
    }

    public void onClick(View v) {
        Toast.makeText(getActivity(), "Moving to edit screen.", Toast.LENGTH_LONG).show();
        Fragment editFragment = new EditDataFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("player1", player1.getText().toString());
        bundle.putString("player2", player2.getText().toString());
        editFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frmLayout, editFragment,null);
        fragmentTransaction.addToBackStack(editFragment.getClass().getName());
        fragmentTransaction.commit();
    }
}