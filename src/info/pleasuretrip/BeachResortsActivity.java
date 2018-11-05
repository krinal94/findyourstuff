package info.pleasuretrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import info.services.custom.HotelAdapter;



@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class BeachResortsActivity extends Activity {
	
	@SuppressLint("NewApi")
	StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	String name[],location[];
	ListView list;
	ArrayList<String> H_name=new ArrayList<String>();
	ArrayList<String> H_location=new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beach_resorts);
		list=(ListView) findViewById(R.id.list);
		StrictMode.setThreadPolicy(policy);
		Intent i=getIntent();
		String search=i.getStringExtra("search");
		new Data1().execute(search);
		
		list.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				String name=((TextView)view.findViewById(R.id.resort_name)).getText().toString();
				String location =((TextView)view.findViewById(R.id.location)).getText().toString();
				String image =((TextView)view.findViewById(R.id.resorts_img)).getText().toString();
				
				Intent i=new Intent(BeachResortsActivity.this, HotelDetails.class);
				i.putExtra("H_name", name);
				i.putExtra("H_location", location);
				startActivity(i);
				
			}

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
	
		});
	}
		class Data1 extends AsyncTask<String, String, String>
		{
			ProgressDialog dialog=new ProgressDialog(BeachResortsActivity.this);
	    	@Override
	    	protected void onPreExecute() {
	    		// TODO Auto-generated method stub
	    		super.onPreExecute();
	    		dialog.setCancelable(true);
	    		dialog.setTitle("Loading..");
	    		dialog.setMessage("Please Wait!!");
	    		dialog.show();
	    	}
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				String url1="http://172.16.3.186/resortlocation.php?location="+params[0];
	    		
	    		try{
	    		URL url=new URL(url1);
	    		
	    		
	    		@SuppressWarnings("deprecation")
				HttpClient client=new DefaultHttpClient();
	    		@SuppressWarnings("deprecation")
				HttpGet get=new HttpGet(url1);
	    		
	    		@SuppressWarnings("deprecation")
				HttpResponse response=client.execute(get);
	    		
	    		if(response==null)
	    		{
	    			return "Connection Problem";
	    		}
	    		else{
	       			
	    		@SuppressWarnings("deprecation")
				HttpEntity entity=response.getEntity();
	    		
	    			if(entity!=null)
	    			{
	    				@SuppressWarnings("deprecation")
						InputStream is=entity.getContent();
	    				 BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    				    StringBuilder sb = new StringBuilder();

	    				    String line = null;
	    				    try {
	    				        while ((line = reader.readLine()) != null) {
	    				            sb.append(line + "\n");
	    				        }
	    				    } catch (IOException e) {
	    				        Log.e("tag", "Exception Occured.");
	    				    }  
	    				    is.close();
	    				    
	    				    String s1=sb.toString();
	    				    Log.d("tagg",s1);
	    				    
	    				    JSONArray array=new JSONArray(s1);
	    				    
	    				    
	    				    JSONObject obj;
	    				    for(int i=0;i<array.length();i++)
	    				    {
	    				    	obj=array.getJSONObject(i);
	    				    	//s[i]=obj.getString("Question");
	    				    	String s2=obj.getString("name");
	    				    	String s3=obj.getString("location");
	    				    	
	    				    	H_name.add(s2);
	    				    	H_location.add(s3);
	    				    	
	    			    }
	    				    Log.d("h_name", H_name.get(0));
	    				    return "Yeah";		    
	    		}
	    		}
	    		
	    		}
	    		catch(Exception e)
	    		{
	    			Log.d("tag", e.toString());
	    			return "Exception";
	    		}
				return null;
	    		

	    		
	    	}
	    	@SuppressWarnings("unused")
			@Override
	    	protected void onPostExecute(String result) {
	    		// TODO Auto-generated method stub
	    		super.onPostExecute(result);
	    		dialog.cancel();
	    		
	    		

	    		if(result.equals("Yeah")){
	    			
	    			
	    			HotelAdapter adapter=new HotelAdapter(getApplicationContext(), H_name ,H_location);
	    			list.setAdapter(adapter);
	    			
	    			
	    		}
	    		if(result.equals("Connection Problem")){
	    			Toast.makeText(getApplicationContext(), "Connection Problem",Toast.LENGTH_LONG).show();
	    			
	    		}
	    		
	    		else if(result.equals("Exception"))
	    		{
	    			Toast.makeText(getApplicationContext(), "Oops!! Exception Occured!",Toast.LENGTH_LONG).show();
	    		}
	    		
	    		else if(result==null)
	    		{
	    			Toast.makeText(getApplicationContext(), "Problem!!!!",Toast.LENGTH_LONG).show();
	    			
	    		}
	    			
	    	}
		}
}
	