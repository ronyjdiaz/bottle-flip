package com.slashmobility.bottleflip_android.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.fragments.MyChallengeFragment;
import com.slashmobility.bottleflip_android.fragments.ReviewTutorialFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengeReviewActivity extends BaseActivity {

    @BindView(R.id.frame_pager)
    ViewPager viewpagerContainer;
    @BindView(R.id.frame_tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbarTitle)
    TextView mtoolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_review);
        ButterKnife.bind(this);
        configViews();
        setupViewPager(viewpagerContainer);
        viewpagerContainer.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(viewpagerContainer);
    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = getIntent().getExtras();
        int mpositionChallenge = bundle.getInt("positionChallenge", -1);
        ChallengeReviewActivity.ViewPagerAdapter adapter = new ChallengeReviewActivity.ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(ReviewTutorialFragment.newInstance(mpositionChallenge),getString(R.string.tutorial));
        adapter.addFragment(MyChallengeFragment.newInstance(mpositionChallenge), getString(R.string.my_challenge));
        adapter.addFragment(ReviewTutorialFragment.newInstance(mpositionChallenge), getString(R.string.tutorial));
        viewPager.setAdapter(adapter);
       /* adapter.addFragment(LoanListFragment.newInstance(getApi().getUser().getActiveLoans(), R.layout.item_loan_active_list), getString(R.string.tab_active_loan_text) + " (" + getApi().getUser().getActiveLoans().size() + ")");
        adapter.addFragment(LoanListFragment.newInstance(getApi().getUser().getHistoricLoans(), R.layout.item_loan_list_history), getString(R.string.tab_history_text) + " (" + getApi().getUser().getHistoricLoans().size() + ")");
       */
    }

    private void configViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtoolbarTitle.setText(getString(R.string.friends));//Challenge name
        mToolbar.setBackgroundColor(getResources().getColor(R.color.light_green_challenges));
        changeColorBarNotification(R.color.light_green_challenges);

    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}
