package info.pleasuretrip.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import info.pleasuretrip.*;

public class FeedbackFragment extends Fragment {
	
	public FeedbackFragment(){}
	Button buttonSend;
	TextView textTo;
	EditText textSubject;
	EditText textMessage;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
         
        buttonSend = (Button) rootView.findViewById(R.id.buttonSend);
		textTo = (TextView) rootView.findViewById(R.id.textView1);
		textSubject = (EditText) rootView.findViewById(R.id.editTextSubject);
		textMessage = (EditText) rootView.findViewById(R.id.editTextMessage);
 
		buttonSend.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
			  String to = textTo.getText().toString();
			  String subject = textSubject.getText().toString();
			  String message = textMessage.getText().toString();
 
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);

			  //need this to prompts email client only
			  email.setType("message/rfc822");
			  
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			  
			}
		});
        return rootView;
    }
}
