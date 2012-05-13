package com.bcalc.components;

import java.util.ArrayList;
import java.util.List;

import com.bcalc.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CalcAdapter extends ArrayAdapter<Calc> {
	
	Activity context;
	List<Calc> data;
		
//	public CalcAdapter(Activity context, Calc[] data) {
//		super(context, R.layout.listitem_calc, data);
//		this.context = context;
//		this.data = data;
//	}
	
	public CalcAdapter (Activity context, int textViewResourceId, List<Calc> data) {
		super(context, R.layout.listitem_calc, data);
		this.context = context;
        this.data = data;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
    	View item = convertView;
    	ViewHolder holder;
    	
    	if(item == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.listitem_calc, null);
			
			holder = new ViewHolder();
			holder.txtCalcOperation = (CalcWidget)item.findViewById(R.id.txtCalcOperation);
			holder.lblCalcResult = (TextView)item.findViewById(R.id.lblCalcResult);
			
			item.setTag(holder);
    	} else {
    		holder = (ViewHolder)item.getTag();
    	}
		
    	holder.txtCalcOperation.setText(data.get(position).getOperation());
		holder.lblCalcResult.setText(data.get(position).getResult());
		
		return(item);
    }
    
    static class ViewHolder {
        CalcWidget txtCalcOperation;
        TextView lblCalcResult;
    }


}
