//jaskirat Kaur - A00219135
//Kushagra Kurl - A00246944

package com.kushagrakurl.menupreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, EditDataFragment.PlayerData{

    Button incScore;
    Button decScore;
    RadioGroup radioGrp;
    RadioButton pID;
    Slider jumpVal;
    TextView player1, player2;
    TextView set1_1;
    TextView set1_2;
    TextView set2_1;
    TextView set2_2;
    TextView set3_1;
    TextView set3_2;
    AlertDialog.Builder alert;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGrp = (RadioGroup)findViewById(R.id.radioGroup);
        jumpVal = (Slider)findViewById(R.id.jumpValue);

        incScore = (Button)findViewById(R.id.addScore);
        decScore = (Button)findViewById(R.id.removeScore);
        incScore.setOnClickListener(this);
        decScore.setOnClickListener(this);

        set1_1 = (TextView)findViewById(R.id.set1_1);
        set1_2 = (TextView)findViewById(R.id.set1_2);
        set2_1 = (TextView)findViewById(R.id.set2_1);
        set2_2 = (TextView)findViewById(R.id.set2_2);
        set3_1 = (TextView)findViewById(R.id.set3_1);
        set3_2 = (TextView)findViewById(R.id.set3_2);

        player1 = (TextView)findViewById(R.id.p1);
        player2 = (TextView)findViewById(R.id.p2);

        alert = new AlertDialog.Builder(this);

        sharedpreferences = getSharedPreferences("players", Context.MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("players", 0);
        String p1 = preferences.getString("player1","-");
        String p2 = preferences.getString("player2","-");

        player1.setText(p1);
        player2.setText(p2);


    }

    @Override
    public void sendData(HashMap<String,String> data) {
        player1.setText(data.get("player1").toString());
        player2.setText(data.get("player2").toString());
        Toast.makeText(this, "Selected Item: ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int playerID  = (int)radioGrp.getCheckedRadioButtonId();
        if(playerID == -1){
            alert.setMessage("Please select a player first.").setTitle("No player selected!");
            AlertDialog alertBox = alert.create();
            alertBox.setTitle("No player selected!");
            alertBox.show();
        }
        else{
            pID=(RadioButton)findViewById(playerID);
            String selectedPlayerID = pID.getText().toString();//Integer.parseInt(pID.getText().toString());
            int jumpPointBy = (int) jumpVal.getValue();
            int set11 = Integer.parseInt(set1_1.getText().toString());
            int set12 = Integer.parseInt(set1_2.getText().toString());
            int set21 = Integer.parseInt(set2_1.getText().toString());
            int set22 = Integer.parseInt(set2_2.getText().toString());
            int set31 = Integer.parseInt(set3_1.getText().toString());
            int set32 = Integer.parseInt(set3_2.getText().toString());

            switch (view.getId()){
                case R.id.addScore:{
                    if( (set11 < 6 && set12 < 6) || Math.abs(set11 - set12) < 2){

                        if (selectedPlayerID.equals("1")) {
                            set11 = set11 + jumpPointBy;
                            set1_1.setText(String.valueOf(set11));
                        } else if (selectedPlayerID.equals("2")) {
                            set12 = set12 + jumpPointBy;
                            set1_2.setText(String.valueOf(set12));
                        }

                    }
                    else if((set21 < 6 && set22 < 6) || Math.abs(set21 - set22) < 2){

                        if (selectedPlayerID.equals("1")) {
                            set21 = set21 + jumpPointBy;
                            set2_1.setText(String.valueOf(set21));
                        } else if (selectedPlayerID.equals("2")) {
                            set22 = set22 + jumpPointBy;
                            set2_2.setText(String.valueOf(set22));
                        }

                    }
                    else if((set31 < 6 && set32 < 6) || Math.abs(set31 - set32) < 2){

                        if (selectedPlayerID.equals("1")) {
                            set31 = set31 + jumpPointBy;
                            set3_1.setText(String.valueOf(set31));
                        } else if (selectedPlayerID.equals("2")) {
                            set32 = set32 + jumpPointBy;
                            set3_2.setText(String.valueOf(set32));
                        }

                    }
                    else {
                        alert.setMessage("Cannot add score to final score").setTitle("Match Over!");
                        AlertDialog alertBox = alert.create();
                        alertBox.setTitle("Match Over!");
                        alertBox.show();
                    }
                }
                break;
                case R.id.removeScore:{
                    if( set31 > 0 || set32 > 0){
                        if (selectedPlayerID.equals("1") && set31 > 0){
                            set31 = (set31 - jumpPointBy) < 0 ? 0 : (set31 - jumpPointBy);
                            set3_1.setText(String.valueOf(set31));
                        }
                        else if(selectedPlayerID.equals("2") && set32 > 0){
                            set32 = (set32 - jumpPointBy) < 0 ? 0 : (set32 - jumpPointBy);
                            set3_2.setText(String.valueOf(set32));
                        }
                    }
                    else if( set21 > 0 || set22 > 0){
                        if (selectedPlayerID.equals("1") && set21 > 0){
                            set21 = (set21 - jumpPointBy) < 0 ? 0 : (set21 - jumpPointBy);
                            set2_1.setText(String.valueOf(set21));
                        }
                        else if(selectedPlayerID.equals("2") && set22 > 0){
                            set22 = (set22 - jumpPointBy) < 0 ? 0 : (set22 - jumpPointBy);
                            set2_2.setText(String.valueOf(set22));
                        }
                    }
                    else if( set11 > 0 || set12 > 0){
                        if (selectedPlayerID.equals("1") && set11 > 0){
                            set11 = (set11 - jumpPointBy) < 0 ? 0 : (set11 - jumpPointBy);
                            set1_1.setText(String.valueOf(set11));
                        }
                        else if(selectedPlayerID.equals("2") && set12 > 0){
                            set12 = (set12 - jumpPointBy) < 0 ? 0 : (set12 - jumpPointBy);
                            set1_2.setText(String.valueOf(set12));
                        }
                    }
                    else {
                        alert.setMessage("Cannot subtract score from final score").setTitle("Match is about to start!");
                        AlertDialog alertBox = alert.create();
                        alertBox.setTitle("Match is about to start!");
                        alertBox.show();
                    }

                }
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.Edit_item:
                Fragment editFragment = new EditDataFragment();
                Bundle editbundle = new Bundle();
                editbundle.putString("player1", player1.getText().toString());
                editbundle.putString("player2", player2.getText().toString());
                editFragment.setArguments(editbundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmLayout, editFragment,null);
                fragmentTransaction.addToBackStack(editFragment.getClass().getName());
                fragmentTransaction.commit();
                return true;
            case R.id.View_item: {

                FragmentManager myfragmentManager = getSupportFragmentManager();
                FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();

                ViewDataFragment viewfragment = new ViewDataFragment();
                Bundle bundle = new Bundle();
                bundle.putString("player1", player1.getText().toString());
                bundle.putString("player2", player2.getText().toString());
                viewfragment.setArguments(bundle);
                myfragmentTransaction.add(R.id.frmLayout, viewfragment, null);
                myfragmentTransaction.addToBackStack(viewfragment.getClass().getName());

                myfragmentTransaction.commit();

                /*SharedPreferences preferences = getSharedPreferences("players", 0);
                String Name = preferences.getString("keyName","NA");
                String Password = preferences.getString("keyPswrd","NA");
                String E_mail = preferences.getString("keyEmail","NA");
                Toast.makeText(this, "Name:" +Name+" Password:"+Password+" Email:"+E_mail, Toast.LENGTH_SHORT).show();
*/
                return true;
            }
            case R.id.Home_item: {
                SharedPreferences preferences = getSharedPreferences("players", 0);
                preferences.edit().remove("keyName").remove("keyPswrd").remove("keyEmail").commit();
                FragmentManager myfragmentManager = getSupportFragmentManager();
                while (myfragmentManager.getBackStackEntryCount() > 0) {
                    myfragmentManager.popBackStackImmediate();
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
