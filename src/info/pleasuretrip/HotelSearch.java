package info.pleasuretrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HotelSearch extends Activity {

	Button search;
	EditText location;
	ArrayList<String> id=new ArrayList<String>();
	ArrayList<String> location1=new ArrayList<String>();
	ArrayList<String> h_name=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel_search);
		
		search=(Button) findViewById(R.id.hotelsearch);
        location=(EditText) findViewById(R.id.hotellocation);
        
        search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
				// TODO Auto-generated method stub
					boolean b=turnGPSOn();
					if(b==true)
					{
						if(location.getText().toString().length()==0)
						{
							location.setError("Enter location.");
							return;
						}
					Intent intent=new Intent(HotelSearch.this,HotelActivity.class);
					intent.putExtra("search",location.getText().toString());
					startActivity(intent);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
    }

		
	private boolean turnGPSOn(){
	   		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
		boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	    if(statusOfGPS==false)
	    {
		Toast.makeText(getApplicationContext(), "please turn on your GPS connection", Toast.LENGTH_LONG).show();
		return false;
	    }   //sendBroadcast(poke);
	   return true;
	}

}
