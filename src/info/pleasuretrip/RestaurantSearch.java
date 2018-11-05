package info.pleasuretrip;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RestaurantSearch extends Activity {

	Button search;
	EditText location;
	ArrayList<String> id=new ArrayList<String>();
	ArrayList<String> location1=new ArrayList<String>();
	ArrayList<String> h_name=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_search);
		
		search=(Button) findViewById(R.id.restaurantsearch);
        location=(EditText) findViewById(R.id.restaurantlocation);
        
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
					Intent intent=new Intent(RestaurantSearch.this,RestaurantActivity.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
