package in.fine.artist.home.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;

import in.fine.artist.home.R;
import in.fine.artist.home.ZApplication;
import in.fine.artist.home.utils.VPrefsReader;
import in.fine.artist.home.views.fragments.BaseFragment;
import in.fine.artist.home.views.fragments.NameInputFragment;

/**
 * Created by apoorvarora on 21/08/17.
 */
public class ProfileInputActivity extends BaseActivity {

    private VPrefsReader prefs;
    private ZApplication vapp;
    private Activity mActivity;
    private boolean destroyed = false;

    private Fragment mFragment;
    private static final String FRAGMENT_TAG = "request_fragment_container";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_container);

        prefs = VPrefsReader.getInstance();

        mActivity = this;
        vapp = (ZApplication) getApplication();

        mFragment = new NameInputFragment();
        mFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFragment, FRAGMENT_TAG)
                .commit();
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
        mFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFragment, FRAGMENT_TAG)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        destroyed = true;
        super.onDestroy();
    }
}
