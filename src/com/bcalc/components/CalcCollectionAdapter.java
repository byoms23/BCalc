package com.bcalc.components;

import com.bcalc.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.List;

public class CalcCollectionAdapter extends ArrayAdapter<CalcCollection> {

	Activity context;
	List<CalcCollection> data;
	 
//	public CalcCollectionAdapter (Activity context, CalcCollection[] data) {
//        super(context, R.layout.listitem_calc_collection, data);
//        this.context = context;
//        this.data = data;
//    }
//	
	public CalcCollectionAdapter (Activity context, int textViewResourceId, List<CalcCollection> data) {
		super(context, R.layout.listitem_calc_collection, data);
		this.context = context;
        this.data = data;
	}

    public View getView(int position, View convertView, ViewGroup parent) {
    	View item = convertView;
    	ViewHolder holder;
    	
    	if(item == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.listitem_calc_collection, null);
			
			holder = new ViewHolder();
			holder.lblCalcCollectionTitle = (TextView)item.findViewById(R.id.lblCalcCollectionTitle);
			holder.lblCalcCollectionDate = (TextView)item.findViewById(R.id.lblCalcCollectionDate);
			
			item.setTag(holder);
    	} else {
    		holder = (ViewHolder)item.getTag();
    	}
		
    	holder.lblCalcCollectionTitle.setText(data.get(position).getTitle());
    	if(data.get(position).getDate() != null) {
			DateFormat dateFormat = 
					android.text.format.DateFormat.getDateFormat(context.getApplicationContext())
					.getDateInstance(DateFormat.LONG);
			holder.lblCalcCollectionDate.setText(dateFormat.format(data.get(position).getDate()));
    	} else {
    		holder.lblCalcCollectionDate.setText("Creates a new calc collection");
    	}
		
		return(item);
    }
    
    static class ViewHolder {
        TextView lblCalcCollectionTitle;
        TextView lblCalcCollectionDate;
    }
}

