package info.pleasuretrip.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.pleasuretrip.*;
public class FavouriteFragment extends Fragment {
	
	public FavouriteFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);
         
        return rootView;
    }
}
