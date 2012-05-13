package com.bcalc;

import java.util.ArrayList;

import com.bcalc.components.*;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BCalcActivity extends BCalcHomeActivity {
	
	// ListActivity controles
	CalcCollection calcCollection;
	ArrayAdapter<Calc> adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	// Configure activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Set custom title bar
        int index = getIntent().getExtras().getInt("index");
        calcCollection = listCalcCollections.get(index);
        ((TextView) findViewById(R.id.title)).setText( calcCollection.getTitle() + " - " + getString(R.string.app_name) );
        
        // Fill listview with the calculation collections
        loadCalcCollection();
        adapter = new CalcAdapter(this, android.R.layout.simple_list_item_1, calcCollection);
    	setListAdapter(adapter);    	
    }
    
    public void loadCalcCollection() {
    	if(calcCollection.size() < 1) {
    		calcCollection.add(new Calc("",""));
    	}
    }

}