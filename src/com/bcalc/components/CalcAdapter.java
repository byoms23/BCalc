package com.bcalc.components;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bcalc.R;
import com.bcalc.widget.CalcWidget;
import com.bcalc.widget.OnCalcListener;

public class CalcAdapter extends ArrayAdapter<Calc> {
	
	Activity context;
	final List<Calc> data;
	
	
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
		final int index = position;
//    	View item = convertView;
//    	ViewHolder holder;
//    	
//    	if(item == null) {
//			LayoutInflater inflater = context.getLayoutInflater();
//			item = inflater.inflate(R.layout.listitem_calc, null);
//			
//			holder = new ViewHolder();
//			holder.txtCalcOperation = (CalcWidget)item.findViewById(R.id.txtCalcOperation);
//			holder.lblCalcResult = (TextView)item.findViewById(R.id.lblCalcResult);
//			
//			item.setTag(holder);
//    	} else {
//    		holder = (ViewHolder)item.getTag();
//    	}
//		
//    	holder.txtCalcOperation.setText(data.get(position).getOperation());
//		holder.lblCalcResult.setText(data.get(position).getResult());
//		if(data.get(position).isCalculated()) {
//			holder.txtCalcOperation.setEnabled(false);
//		} else {
//			holder.txtCalcOperation.setEnabled(true);
//			holder.txtCalcOperation.requestFocus();
//		}
//		
//		holder.txtCalcOperation.setCalcListener(new OnCalcListener() {
	
		// TODO Optimizate.
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_calc, null);
 
        final CalcWidget txtCalcOperation = (CalcWidget)item.findViewById(R.id.txtCalcOperation);
        TextView lblCalcResult = (TextView)item.findViewById(R.id.lblCalcResult);
 
    	txtCalcOperation.setText(data.get(position).getOperation());
		lblCalcResult.setText(data.get(position).getResult());
		if(data.get(position).isCalculated()) {
			txtCalcOperation.setFocusable(false);
		} else {
			txtCalcOperation.post(new Runnable() {
				@Override
			    public void run() {
			    	txtCalcOperation.requestFocus();
			    	InputMethodManager keyboard = (InputMethodManager)
			    			context.getSystemService(Context.INPUT_METHOD_SERVICE);
			    	keyboard.showSoftInput(txtCalcOperation, 0);
			    }
			});
			
		}
	
		txtCalcOperation.setCalcListener(new OnCalcListener() {

			@Override
			public void onCalc(CalcWidget widget,String operation, String answer) {
				Log.i("Calc Adapter", "Set calc " + index + " to '" + operation +"' with result '" + answer + "'" );
				data.get(index).setOperation(operation);
				data.get(index).setResult(answer);
				if(index == data.size()-1) {
					data.add(new Calc("", ""));
				}
				widget.setFocusable(false);
				
				notifyDataSetChanged();
			}
		});
		
		return(item);
    }
    
    static class ViewHolder {
        CalcWidget txtCalcOperation;
        TextView lblCalcResult;
    }


}
