package info.pleasuretrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import info.pleasuretrip.R;
import info.pleasuretrip.R.id;
import info.pleasuretrip.R.layout;
import info.pleasuretrip.R.string;
import info.pleasuretrip.slidingmenu.HomeListFragment;

public class Login extends Activity {

	EditText upass,usign;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login=(Button) findViewById(R.id.button2);
        usign=(EditText) findViewById(R.id.user_sign);
        upass=(EditText) findViewById(R.id.user_pass);
        
       login.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(usign.getText().toString().length()==0)
			{
				usign.setError("Enter E-Mail.");
				return;
				
			}
			else
			{	
				String EMail=usign.getText().toString(); 
				int atpos=EMail.indexOf("@");
				int dotpos=EMail.indexOf(".");
				if (!usign.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
				{
					usign.setError("Invalid E-Mail");
					return;
				}
			}
			
			if(upass.getText().toString().length()==0)
			{
				upass.setError("Enter Password.");
				return;
				
			}
			if(upass.getText().toString().length()<8)
			{
				upass.setError("Password must be 8 characters long.");
				return;
				
			}
			String params[]={usign.getText().toString(),upass.getText().toString()};
			StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			new login1().execute(params);
			
			
		}
	});
        
		
	}

	class login1 extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url="http://172.16.3.186/login.php?EmailId="+params[0]+"&Password="+params[1];
			try{
	    		URL url1=new URL(url);
	    		
	    		
	    		HttpClient client=new DefaultHttpClient();
	    		HttpGet get=new HttpGet(url);
	    		
	    		HttpResponse response=client.execute(get);
	    		
	    		if(response==null)
	    		{
	    			return null;
	    		}
	    		else{
	    			
	    		
	    		
	    			
	    		HttpEntity entity=response.getEntity();
	    			
	    			if(entity!=null)
	    			{
	    				InputStream is=entity.getContent();
	    				 BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    				    StringBuilder sb = new StringBuilder();

	    				    String line = null;
	    				    try {
	    				        while ((line = reader.readLine()) != null) {
	    				            sb.append(line + "\n");
	    				        }
	    				    } catch (IOException e) {
	    				        e.printStackTrace();
	    				    }  
	    				    is.close();
	    				    
	    				    
	    				    JSONObject obj=new JSONObject(sb.toString());
	    				    
	    				    int i1=obj.getInt("value");
	    				    if(i1==1)
	    				    {
	    				    	String UserId=obj.getString("UserID");
	    				    	int uid=Integer.parseInt(UserId);
	    				    	SharedPreferences pref=getSharedPreferences("UserId",Context.MODE_PRIVATE);
	    				    	SharedPreferences.Editor edit=pref.edit();
	    				    	edit.putInt("UserId", uid);
	    				    	edit.commit();
	    				    			
	    				    	return "Login Successful!";
	    				    }
	    				    if(i1==0)
	    				    {
	    				    	return "Invalid Login!";
	    				    }
	    				    
	    				    
	    		}
	    		}
	    				return "Exception";
	    		}
	    		catch(Exception e)
	    		{
	    			Log.e("tag",e.toString());
	    			return null;
	    		}
				
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result==null){
			  Toast.makeText(Login.this, "Connection Problem!", Toast.LENGTH_LONG).show();
			}
			else if(result.equals("Login Successful!"))
			{
				Toast.makeText(Login.this, result, Toast.LENGTH_LONG).show();
				Intent i=new Intent(getBaseContext(),HomeListFragment.class); 
				startActivity(i);
			}
			else if(result.equals("Invalid Login!"))
			{
				Toast.makeText(Login.this, result, Toast.LENGTH_LONG).show();
			}
		}
		
	}}