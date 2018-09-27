package hr.algebra.algebramultimedia.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.algebra.algebramultimedia.R;

/**
 * Created by online4 on 6.2.2017..
 */

public class VideoFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.videoView)
    VideoView videoView;


    public VideoFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static VideoFragment newInstance(int sectionNumber) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        ButterKnife.bind(this, rootView);

        videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.documentariesandyou));
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.requestFocus();

        return rootView;
    }

}





























