package info.services.custom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable.Creator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import info.pleasuretrip.R;

public class RestaurantAdapter extends BaseAdapter{
	
	ArrayList<String> H_name;
	ArrayList<String> H_location;
	
	Context context;
	
	public RestaurantAdapter(Context c,ArrayList<String> list,ArrayList<String> list1) {
		// TODO Auto-generated constructor stub
		context=c;
		this.H_name=list;
		this.H_location=list1;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return H_name.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return H_name.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflater.inflate(R.layout.restaurant_list,null);
		TextView tv1=(TextView) view.findViewById(R.id.restaurant_name);
		TextView tv2=(TextView) view.findViewById(R.id.location);
		
		Button btn=(Button) view.findViewById(R.id.View_details);
		Log.d("Adapter", "Adapter class");
		
		/*try {
			Field f=R.drawable.class.getDeclaredField(s);
			int a=f.getInt(this);
			img.setImageDrawable(f);
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		tv1.setText((String)getItem(position));
		tv2.setText(H_location.get(position));
		return view;
		
	}
	
}