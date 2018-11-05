package info.pleasuretrip.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import info.pleasuretrip.BeachResortsSearch;
import info.pleasuretrip.CabSearch;
import info.pleasuretrip.HotelSearch;
import info.pleasuretrip.MainActivity;
import info.pleasuretrip.R;
import info.pleasuretrip.RestaurantSearch;
import info.pleasuretrip.SplashActivity;
import android.widget.AdapterView.OnItemClickListener;

public class HomeListFragment extends ListFragment implements OnItemClickListener {
	
	String[] menutitles;
	TypedArray menuIcons;

	CustomAdapter adapter;
	private List<RowItem> rowItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.homelistfragment, null, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		menutitles = getResources().getStringArray(R.array.titles);
		menuIcons = getResources().obtainTypedArray(R.array.icons);

		rowItems = new ArrayList<RowItem>();

		for (int i = 0; i < menutitles.length; i++) {
			RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
					i, -1));

			rowItems.add(items);
		}

		adapter = new CustomAdapter(getActivity(), rowItems);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), menutitles[position], Toast.LENGTH_SHORT)
		.show();
		
		switch( position )
        {
           case 0:  Intent newActivity = new Intent(getActivity(), HotelSearch.class);     
           			startActivityForResult(newActivity, 0);
                    break;
           case 1:  Intent newActivity1 = new Intent(getActivity(), RestaurantSearch.class);     
  					startActivityForResult(newActivity1, 0);
                    break;
           case 2:  Intent newActivity2= new Intent(getActivity(), CabSearch.class);     
  					startActivityForResult(newActivity2, 0);
           			break;
           case 3:  Intent newActivity3= new Intent(getActivity(), BeachResortsSearch.class);     
					startActivityForResult(newActivity3, 0);
					break;
        }
	}
}
