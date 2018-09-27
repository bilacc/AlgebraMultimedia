package hr.algebra.algebramultimedia.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hr.algebra.algebramultimedia.fragments.GraphicsFragment;
import hr.algebra.algebramultimedia.fragments.SoundFragment;
import hr.algebra.algebramultimedia.fragments.VideoFragment;

/**
 * Created by online4 on 6.2.2017..
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return SoundFragment.newInstance(position + 1);
        else if (position == 1)
            return VideoFragment.newInstance(position + 1);
        else
            return GraphicsFragment.newInstance(position + 1);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SOUND";
            case 1:
                return "VIDEO";
            case 2:
                return "GRAPHICS";
        }
        return null;
    }
}