//jaskirat Kaur - A00219135
//Kushagra Kurl - A00246944

package com.kushagrakurl.menupreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button incScore;
    Button decScore;
    RadioGroup radioGrp;
    RadioButton pID;
    Slider jumpVal;
    TextView set1_1;
    TextView set1_2;
    TextView set2_1;
    TextView set2_2;
    TextView set3_1;
    TextView set3_2;
    AlertDialog.Builder alert;

    SharedPreferences sharedpreferences;
    EditText name,pswrd,email;
    Button save;

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

        alert = new AlertDialog.Builder(this);

        sharedpreferences = getSharedPreferences("players", Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm  = name.getText().toString();
                String psd  = pswrd.getText().toString();
                String mail  = email.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("keyName", nm);
                editor.putString("keyPswrd", psd);
                editor.putString("keyEmail", mail);
                editor.commit();
                Toast.makeText(MainActivity.this,"Data has been saved!",Toast.LENGTH_LONG).show();

            }
        });

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
            case R.id.search_item:
                Toast.makeText(this, "Search" +item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Edit_item:
                //saveDate();
                Intent intent = new Intent(this, EditDataFragment.class);
                startActivity(intent);
                /*Fragment someFragment = new EditDataFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.f, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();*/
                return true;
            case R.id.View_item: {

                FragmentManager myfragmentManager = getSupportFragmentManager();
                FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction ();

                ViewDataFragment viewfragment = new ViewDataFragment();

                myfragmentTransaction.add(R.id.frmLayout, viewfragment, null);

                /*You've to create a frame layout or any other layout with id inside your activity layout and then use that id in java*/

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
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveDate() {
        String nm  = name.getText().toString();
        String psd  = pswrd.getText().toString();
        String mail  = email.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("keyName", nm);
        editor.putString("keyPswrd", psd);
        editor.putString("keyEmail", mail);
        editor.commit();
        Toast.makeText(MainActivity.this,"Data has been saved!",Toast.LENGTH_LONG).show();
    }
}
