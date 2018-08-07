package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Templates;

public class MainActivity extends AppCompatActivity {
    Spinner spnCat;
    Spinner spnSubCat;
    Button btnGo;
    ArrayList<String> alCategory;
    ArrayAdapter<String> aaCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCat = findViewById(R.id.spinnerCategory);
        spnSubCat = findViewById(R.id.spinnerSubCategory);
        btnGo = findViewById(R.id.button);

        //Initialise the ArrayList
        alCategory = new ArrayList<>();

        //Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,alCategory);

        //Bind the ArrayAdapter to the Spinner
        spnSubCat.setAdapter(aaCategory);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Category));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCat.setAdapter(spinnerArrayAdapter);


        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                switch (i) {
                    case 0:
                        final String[] strNumbers = getResources().getStringArray(R.array.SubCategoryRP);
                        alCategory.clear();
                        alCategory.addAll(Arrays.asList(strNumbers));
                        aaCategory.notifyDataSetChanged();

                        break;
                    case 1:
                        final String[] strNumbers1 = getResources().getStringArray(R.array.SubCategorySOI);
                        alCategory.clear();
                        alCategory.addAll(Arrays.asList(strNumbers1));
                        aaCategory.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Main2Activity.class);
                intent.putExtra("Category", spnCat.getSelectedItemPosition());
                intent.putExtra("Site", spnSubCat.getSelectedItemPosition());
                startActivity(intent);
            }
        });
    }
    //onPause
    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Obtain an instance of the Shared Preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Step 1b: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        // Step 1c: Add the key-value pair
        prefEdit.putInt("Category", spnCat.getSelectedItemPosition());
        prefEdit.putInt("Site", spnSubCat.getSelectedItemPosition());
        // Step 1d: Call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }


    //onResume
    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreferences object
        int Category = prefs.getInt("Category", 0);
        int Site = prefs.getInt("Site", 0);


        spnCat.setSelection(Category);
        if (Category == 0) {
            final String[] strNumbers = getResources().getStringArray(R.array.SubCategoryRP);
            alCategory.clear();
            alCategory.addAll(Arrays.asList(strNumbers));
            spnSubCat.setSelection(Site);
            aaCategory.notifyDataSetChanged();
        }
        else {
            final String[] strNumbers = getResources().getStringArray(R.array.SubCategorySOI);
            alCategory.clear();
            alCategory.addAll(Arrays.asList(strNumbers));
            spnSubCat.setSelection(Site);
            aaCategory.notifyDataSetChanged();

        }


    }



}


