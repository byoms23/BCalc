package com.bcalc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import com.bcalc.components.*;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class BCalcHomeActivity extends ListActivity {
	final String CALC_COLLECTIONS_FILE = "calc_collections.dat";
	
	ArrayList<CalcCollection> listCalcCollections = new ArrayList<CalcCollection>();
	ArrayAdapter<CalcCollection> adapter;
	CalcCollection newCollection = new CalcCollection(-1, "New calc collection", null);
	long id_pointer = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(NOTIFICATION_SERVICE, "Home activity created.");
        
        super.onCreate(savedInstanceState);
        final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        
        // Set custom title bar
        if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        }
        
        // Fill listview with the calculation collections 
        loadCalcCollections();
    	adapter = new CalcCollectionAdapter(this, android.R.layout.simple_list_item_1, listCalcCollections);
    	setListAdapter(adapter);
    	
    	// Create the context menu for the items
    	ListView list = (ListView)findViewById(android.R.id.list);
    	registerForContextMenu(list);
    }
    
    public void createNewCalcCollection(View v) {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
    	alert.setTitle("New calc collection");
    	alert.setMessage("Enter the title:");

    	// Set an EditText view to get user input   
    	final EditText input = new EditText(this);
    	input.setSingleLine();
    	alert.setView(input);
    	
    	// Create the new collection
    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
    		public void onClick(DialogInterface dialog, int whichButton) {  
    			String title = input.getText().toString();
    			int index = 1;
    			
    			// Add to the list
    			CalcCollection collection = new CalcCollection(id_pointer++, title, new Date());
    			listCalcCollections.add(index, collection) ;
    			adapter.notifyDataSetChanged();
    			onStop();
    			
    			// Call the new activity
    			editCalcCollection(index);
    			return;                  
    		}  
    	});  

    	// Return without saving
    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			return;   
	    	}
    	});
    	
    	// Show alert
    	alert.show();
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        //Acciones necesarias al hacer click
		Log.i(NOTIFICATION_SERVICE, "Position: " + position);
    	if(position == 0) {
    		Log.i(NOTIFICATION_SERVICE, "Create new calc collection.");
    		createNewCalcCollection(v);
    	} else {
    		editCalcCollection(position);
    	}
    }
    
    public void loadCalcCollections() {
    	try
    	{
    	    ObjectInputStream storage =
    	        new ObjectInputStream(
    	                openFileInput(CALC_COLLECTIONS_FILE));
    	    
    	    id_pointer = (Long)(storage.readObject());
    	    listCalcCollections = ((ArrayList<CalcCollection>) (storage.readObject()));
    	    storage.close();
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    		listCalcCollections.add(newCollection);
    	}
    }
    
    @Override
    protected void onStop() {
		try {
		    ObjectOutputStream storage =
		        new ObjectOutputStream(
		            openFileOutput(CALC_COLLECTIONS_FILE, MODE_PRIVATE));
		 
		    storage.writeObject(new Long(id_pointer));
		    storage.writeObject(listCalcCollections);
		    storage.close();
		} catch (Exception ex) {
		    Log.e(NOTIFICATION_SERVICE, "Error in the internal memory when trying to write.");
		}
		super.onStop();
    }
    
    @Override
    public void  onDestroy() {
    	System.runFinalizersOnExit(true);
    	super.onDestroy();
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
    	if (v.getId()==android.R.id.list) {
    	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	        if(info.position > 0) {
	        	menu.setHeaderTitle(listCalcCollections.get(info.position).getTitle());
	        	inflater.inflate(R.menu.listitem_calc_collection_menu_ctx, menu);
	        }
      }
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
      
      // Look for the right option selected in the menu.
	  switch (item.getItemId()) {
      case R.id.ctxMnEdit:
    	  editCalcCollection(info.position);
    	  break;
      case R.id.ctxMnDelete:
    	  deleteCalcCollection(info.position);
    	  break;
      default:
          return super.onContextItemSelected(item);
      } 
      
      return true;
    }
    
    public void editCalcCollection(int index) {
		Intent intent = new Intent(BCalcHomeActivity.this, BCalcActivity.class);
		intent.putExtra("index", index);
		startActivity(intent);
    }
    
    public void deleteCalcCollection(int index) {
		listCalcCollections.remove(index) ;
		adapter.notifyDataSetChanged();
		onStop();
    }
}
