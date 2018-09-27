package hr.algebra.algebramultimedia.fragments;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.algebra.algebramultimedia.R;
import hr.algebra.algebramultimedia.interfaces.OnProgressChangeListener;
import hr.algebra.algebramultimedia.views.HorizontalSlider;

/**
 * Created by online4 on 6.2.2017..
 */

public class SoundFragment extends Fragment implements OnProgressChangeListener { //polimorfizam

    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.tvProgress)
    TextView tvProgress;
    @BindView(R.id.btnMediaPlayer)
    Button btnMediaPlayer;
    @BindView(R.id.slider)
    HorizontalSlider slider;
    @BindView(R.id.btnRecordStart)
    Button btnRecordStart;
    @BindView(R.id.btnRecordStop)
    Button btnRecordStop;

    //Sve smo bindali s butterknifeom da ne moramo koristiti find View bi Id metodu :)


    /**
     * SoundPool
     */
    private SoundPool soundPool;  //objekt klase soundpool za sviranje kratkih zvukova
    private int soundId;
    private boolean loaded = false;
    /**
     * MediaPlayer
     */
    private MediaPlayer mediaPlayer;
    /**
     * MediaRecorder
     */
    private MediaRecorder mediaRecorder;
    private File audioFile = null;


    public SoundFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SoundFragment newInstance(int sectionNumber) {
        SoundFragment fragment = new SoundFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sound, container, false);

        ButterKnife.bind(this, rootView);

        /** SoundPool */
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0); //0 je vjerojatno max quality
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                loaded = true;
            }
        });

        soundId = soundPool.load(getActivity(), R.raw.thunder_magic, 1); //iščitavamo zvuk iz naših resursa

        /** MediaPlayer */
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.zvuk2);
        slider.setOnProgressChangeListener(this);

        /** MediaRecorder */
        btnRecordStop.setEnabled(false);   //ne možemo zaustaviti snimanje kad ga nismo niti pokrenuli

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.release();
    }

    @OnClick(R.id.btnSoundPool)
    public void btnSoundPool() {
        if (loaded) {
            soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    @OnClick(R.id.btnMediaPlayer)
    public void btnMediaPlayer(Button btn) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btn.setText("Play");
        } else {
            mediaPlayer.start();
            btn.setText("Pause");
            onProgressChanged(null, 0); //ako zakomentiramo ovu liniju možemo pauzirati muziku, inače je Pauza zapravo Stop
                                                 //
        }
    }

    @OnClick(R.id.btnRecordStart)
    public void btnRecordStart() {
        startRecording();
    } //kad pokrenemo snimanje mijenjamo gumbe

    @OnClick(R.id.btnRecordStop)
    public void btnRecordStop() {
        stopRecording();
    }

    private void startRecording() {
        btnRecordStart.setEnabled(false);
        btnRecordStop.setEnabled(true);

        File sampleDir = Environment.getExternalStorageDirectory(); //putanja se nalazi na rootu od uređaja
        try {
            audioFile = File.createTempFile("zvuk", ".3gp", sampleDir); //kreiraj mi neki temp file s prefixom
        } catch (IOException e) {                                                   //zvuk
            e.printStackTrace();
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(audioFile.getAbsolutePath());

        try {
            mediaRecorder.prepare(); //provjera gore navedenih parametara, ako je sve ok krece snimanje zvuka
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    private void stopRecording() {
        btnRecordStart.setEnabled(true);
        btnRecordStop.setEnabled(false);
        mediaRecorder.stop(); //metoda stop zaustava snimanje
        mediaRecorder.release(); //zovemo release, zovemo resurse, osiguravamo da se podaci spreme
        addRecordingToMediaLibrary();
    }

    private void addRecordingToMediaLibrary() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE); //šaljemo intent android sustavu da skenira fajl
        intent.setData(Uri.fromFile(audioFile));        //uri će sadržavati sve informacije koje će se na drugoj strani razlomiti
        getActivity().sendBroadcast(intent);      //bez broadcasta ostali appovi nebi mogli odmah vidjeti naš novi fajl
    }

    @Override
    public void onProgressChanged(View v, int progress) {
        if (mediaPlayer != null && mediaPlayer.isPlaying() == true) {
            mediaPlayer.seekTo(mediaPlayer.getDuration() * progress / 100);
            tvProgress.setText(progress + "%");
        } else {
            tvProgress.setText("Song not playing");
            btnMediaPlayer.setText("Play");
            if (progress != 0) {
                slider.setProgress(0);
            }
        }
    }
}
