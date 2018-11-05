package info.pleasuretrip.slidingmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import info.pleasuretrip.*;


public class LoginSignupFragment extends Fragment {
	
	Button b,login;
	
	Context context;
	EditText uname,pass,cpass;
	public LoginSignupFragment(Context context){
		this.context=context;
	}
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.activity_login_signup, container, false);
         b=(Button) rootView.findViewById(R.id.button1);
         login=(Button) rootView.findViewById(R.id.button2);
         uname=(EditText) rootView.findViewById(R.id.user_sign);
         pass=(EditText) rootView.findViewById(R.id.user_pass);
         cpass=(EditText) rootView.findViewById(R.id.con_pass);
         b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(uname.getText().toString().length()==0)
				{
					uname.setError("Enter E-Mail.");
					return;
					
				}
				else
				{	
					String EMail=uname.getText().toString(); 
					int atpos=EMail.indexOf("@");
					int dotpos=EMail.indexOf(".");
					if (!uname.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
					{
						uname.setError("Invalid E-Mail");
						return;
					}
				}
				
				if(pass.getText().toString().length()==0)
				{
					pass.setError("Enter Password.");
					return;
					
				}
				if(pass.getText().toString().length()<8)
				{
					pass.setError("Password must be 8 characters long.");
					return;
					
				}
				if(cpass.getText().toString().length()==0)
				{
					cpass.setError("Enter Confirm Password.");
					return;
					
				}
				if(!cpass.getText().toString().equals(pass.getText().toString()))
				{
					cpass.setError("Confirm Password does not match to Password.");
					return;
					
				}
				
				
				String params[]={uname.getText().toString(),pass.getText().toString()};
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				new signup().execute(params);
				
				 
			}
		});
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(context,Login.class);
				startActivity(i);
			}
		});
        return rootView;
    }
	
class signup extends AsyncTask<String, String, String>
{

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String url="http://172.16.3.186/signup.php?EmailId="+params[0]+"&Password="+params[1];
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
    				    	return "Login Successful!";
    				    }
    				    
    		
    		}
    		}
    		}
    		catch(Exception e)
    		{
    			Log.e("tag",e.toString());
    		}
			return null;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result==null){
		//	Toast.makeText(null, "Connection Problem!", Toast.LENGTH_LONG).show();
		}
		else if(result.equals("Login Successful!"))
		{
			//Toast.makeText(null, result, Toast.LENGTH_LONG).show();
			Intent i=new Intent(context,Login.class); 
			startActivity(i);
		}
	}	
}
}
