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

public class BeachResortsSearch extends Activity {

	Button search;
	EditText location;
	ArrayList<String> id=new ArrayList<String>();
	ArrayList<String> location1=new ArrayList<String>();
	ArrayList<String> h_name=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beach_resorts_search);
		
		search=(Button) findViewById(R.id.resortsearch);
        location=(EditText) findViewById(R.id.resortlocation);
        
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
					Intent intent=new Intent(BeachResortsSearch.this,BeachResortsActivity.class);
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
