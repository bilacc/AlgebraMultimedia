package hr.algebra.algebramultimedia.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.algebra.algebramultimedia.R;

/**
 * Created by online4 on 10.2.2017..
 */

public class DropZoneFragment extends Fragment {

    @BindView(R.id.droptarget)
    View dropTarget;
    @BindView(R.id.dropmessage)
    TextView dropMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dropzone, container, false);

        ButterKnife.bind(this, rootView);

        dropTarget.setOnDragListener(new View.OnDragListener() {
            private int dropCount = 0;

            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int action = dragEvent.getAction();
                boolean result = true;

                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                    case DragEvent.ACTION_DRAG_ENTERED:
                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_LOCATION:
                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                    case DragEvent.ACTION_DROP:
                        String message = (++dropCount) + "";
                        dropMessage.setText(message);
                        break;
                    default:
                        result = false;
                }

                return result;
            }
        });

        return rootView;
    }


}
