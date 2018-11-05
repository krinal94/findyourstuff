package info.pleasuretrip.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import info.pleasuretrip.GuidelinesActivity;
import info.pleasuretrip.R;
import info.pleasuretrip.TechnicalActivity;

public class HelpCenterFragment extends Fragment{

public HelpCenterFragment(){}
	
	TextView tv;
	Button b1;
	Button b2;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_helpcenter, container, false);
        b1=(Button) rootView.findViewById(R.id.guidelines);
        b2=(Button) rootView.findViewById(R.id.techsup);
        
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getActivity(),GuidelinesActivity.class);
				startActivity(i);
			}
		});
        
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getActivity(),TechnicalActivity.class);
				startActivity(i);
			}
		});
        return rootView;
    
        
        
	}     
}
