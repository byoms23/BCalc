package com.bcalc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bcalc.components.Calc;
import com.bcalc.components.CalcAdapter;
import com.bcalc.components.CalcCollection;

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