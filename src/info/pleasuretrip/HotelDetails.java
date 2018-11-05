package info.pleasuretrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HotelDetails extends Activity {

	TextView name,hotelinfo,pricetag,amount,roomtag,address,con;
	/*ImageView img1,img2,img3;*/
	String hname,hamt,hroom,hadd,hcon;
	int s1,params;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_details);
		
		/*img1=(ImageView)findViewById(R.id.imageView1);*/
		hotelinfo=(TextView)findViewById(R.id.hotel_info);
		name=(TextView)findViewById(R.id.hotel_name);
		pricetag=(TextView)findViewById(R.id.price);
		amount=(TextView)findViewById(R.id.amt);
		roomtag=(TextView)findViewById(R.id.rooms);
		address=(TextView)findViewById(R.id.add);
		con=(TextView)findViewById(R.id.contact);
		/*img2=(ImageView)findViewById(R.id.icon1);
		img3=(ImageView)findViewById(R.id.icon2);*/
		Button book=(Button)findViewById(R.id.book);
		
		Intent i = getIntent();
		s1 = i.getIntExtra("id",0);
		params=s1;
		new data2().execute(s1);
		
		
	}
	class data2 extends AsyncTask<Integer, String, String> {
        ProgressDialog dialog;

        @Override
        protected String doInBackground(Integer... params) {
           // SharedPreferences pref = getSharedPreferences("name", Context.MODE_PRIVATE);
           // String a = pref.getString("name","");
            String url1 = "http://172.16.3.186/hoteldetail.php?Id="+ params[0];
            try {

                //192.168.56.1
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url1);
                HttpResponse response = client.execute(get);

                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Connection Problem", Toast.LENGTH_LONG).show();
                } else {

                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
                        InputStream is = entity.getContent();
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

                        JSONArray array = new JSONArray(sb.toString());
                        JSONObject obj = array.getJSONObject(0);
                        hname= obj.getString("name");
                        hamt = obj.getString("price");
                        hroom = obj.getString("room");
                        hadd = obj.getString("address");
                        hcon = obj.getString("mobile no");
                        

                        return "Login Successful!";

                    }
                }
            } catch (Exception e) {
                Log.e("tag", e.toString());
                e.printStackTrace();
            }
            return null;


        }




        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog=new ProgressDialog(getApplicationContext());
            dialog.setTitle("Loading..");
            dialog.setMessage("Please Wait!!");
        }


        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();
            if(result==null){
                //	Toast.makeText(null, "Connection Problem!", Toast.LENGTH_LONG).show();
            }
            else if(result.equals("Login Successful!")){

               /*name.setText(name);
                con.setText(con);
                price.setText(price);
                rooms.setText(rooms);
                add.setText(add);*/
                /*Seatsavailable.setText(seatsavailable+"");
                Carno.setText(carno);
                Facility.setText(facility);*/

               // Intent i_ok=new Intent(DetailDriver.this,DrawerMenu.class);
                //startActivity(i_ok);

            }
            else if(result.equals("Connection Problem."))
            {
                Toast.makeText(getApplicationContext(), "Oops!! Connection Problem!",Toast.LENGTH_LONG).show();
            }
            else if(result.equals("Invalid Login Details"))
            {
                Toast.makeText(getApplicationContext(), "Invalid Login Details!! 0~0",Toast.LENGTH_LONG).show();
            }
            else if(result==null)
            {
                Toast.makeText(getApplicationContext(), "Exception occured",Toast.LENGTH_LONG).show();
            }

        }
    }

    


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotel_details, menu);
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
