package nsit.app.com.nsitapp;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ListView lv;
    private ActionBarDrawerToggle mDrawerToggle;
    static final String[] sideitems = new String[] { "Home" , "My Feed","Video","Calendar" , "Professors","Locations" };	//items on navigation drawer
    SwipeRefreshLayout swipeLayout;
    Integer[] imageId = {
            R.drawable.ic_action_star_10,
            R.drawable.ic_action_star_10,
            R.drawable.ic_action_star_10,
            R.drawable.ic_action_star_10,
            R.drawable.ic_action_star_10,
            R.drawable.ic_action_star_10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment f = new Home();
        ft.replace(R.id.content_frame, f);
        getSupportActionBar().setTitle("Home");
        ft.commit();

        lv = (ListView) findViewById(R.id.list);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        //All for navigation drawer
        CustomList2 adapter2 = new CustomList2(this, sideitems, imageId);
        mDrawerList.addHeaderView(new View(this));
        mDrawerList.addFooterView(new View(this));
        mDrawerList.setAdapter(adapter2);
        mDrawerToggle = new ActionBarDrawerToggle(

                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* navigation drawer icon to replace 'Up' caret */
                R.string.app_name,  /* "open drawer" description */
                R.string.app_name  /* "close drawer" description */
        ) ;

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "Position : " + position + " Clicked ", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                changeItem(position);
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void changeItem(int position){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Locate Position
        switch (position) {
            case 1:
                Fragment f = new Home();
                ft.replace(R.id.content_frame, f);
                getSupportActionBar().setTitle("Home");
                break;

            case 2:

                Fragment f1 = new Feed();
                ft.replace(R.id.content_frame, f1);
                getSupportActionBar().setTitle("My Feed");
                break;


            case 3:
                Fragment f2 = new Video();
                ft.replace(R.id.content_frame, f2);
                getSupportActionBar().setTitle("Video");
                break;

            case  4:
                Fragment f3 = new Calender();
                ft.replace(R.id.content_frame, f3);
                getSupportActionBar().setTitle("Time Table");
                break;

            case 5 :
                Fragment f4 = new Professors();
                ft.replace(R.id.content_frame, f4);
                getSupportActionBar().setTitle("Professors List");
                break;

            case 6 :
                Fragment f5 = new Locations();
                ft.replace(R.id.content_frame, f5);
                getSupportActionBar().setTitle("Locations");
                break;

        }
        ft.commit();
    }
}
