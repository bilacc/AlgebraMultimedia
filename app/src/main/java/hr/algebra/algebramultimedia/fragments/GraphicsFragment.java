package hr.algebra.algebramultimedia.fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import hr.algebra.algebramultimedia.R;
import hr.algebra.algebramultimedia.activities.MainActivity;
import hr.algebra.algebramultimedia.interfaces.Transformation;
import hr.algebra.algebramultimedia.views.HelloAndroidTextDrawable;
import hr.algebra.algebramultimedia.views.TransformedViewWidget;

/**
 * Created by online4 on 6.2.2017..
 */

public class GraphicsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public GraphicsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static GraphicsFragment newInstance(int sectionNumber) {
        GraphicsFragment fragment = new GraphicsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//      View rootView = inflater.inflate(R.layout.fragment_measure, container, false); // Demo for onMeasure method
//      View rootView = inflater.inflate(R.layout.fragment_graphics, container, false); // Demo for shape views from drawable folder
//      View rootView = new SimpleRectView(getActivity()); // Demo for Canvas with shape
//      View rootView = new CustomTextView(getActivity()); // Demo for text draw
        /** DotView **/
//      Dots dots = new Dots();
//      dots.addDot(100, 100, Color.BLACK, 75);
//      View rootView = new DotView(getActivity(), dots);
        /** Transformation **/
//      View rootView = inflater.inflate(R.layout.fragment_transform, container, false); // Demo for transform matrix
//      addLeftTransformedViews(rootView);
//      addRightTransformedViews(rootView);
        /** Transformation with Drawable**/
//      View rootView = inflater.inflate(R.layout.fragment_transform, container, false); // Demo for transform matrix
//      addLeftTransformedViewsDrawable(rootView);
//      addRightTransformedViewsDrawable(rootView);
        /** Bitmap **/
//      View rootView = new CachingWidget(getActivity()); // Demo for text draw
        /** Drag & Drop**/
        View rootView = inflater.inflate(R.layout.fragment_drag_drop, container, false);
        MainActivity.counterLayout = (LinearLayout) rootView.findViewById(R.id.counters);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    private void addLeftTransformedViews(View v) {
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.ll_left);

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
            }

            @Override
            public String describe() {
                return "identity";
            }
        }));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.rotate(-30.0F);
            }

            @Override
            public String describe() {
                return "rotate(-30)";
            }
        }));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.scale(0.5F, 0.8F);
            }

            @Override
            public String describe() {
                return "scale(.5, .8)";
            }
        }));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.skew(0.1F, 0.3F);
            }

            @Override
            public String describe() {
                return "skew(.1, .3)";
            }
        }));
    }

    private void addRightTransformedViews(View v) {
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.ll_right);

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.translate(30F, 10F);
            }

            @Override
            public String describe() {
                return "translate(30F, 10F)";
            }
        }));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.translate(110F, -20F);
                canvas.rotate(85.0F);
            }

            @Override
            public String describe() {
                return "translate(110F, -20F),rotate(85)";
            }
        }));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.translate(-50F, -20F);
                canvas.scale(2F, 1.2F);
            }

            @Override
            public String describe() {
                return "canvas.translate(-50F, -20F),canvas.scale(2F, 1.2F);";
            }
        }));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.skew(0.1F, 0.3F);
                canvas.translate(-100F, -100F);
                canvas.scale(2.5F, 2F);
            }

            @Override
            public String describe() {
                return "complex";
            }
        }));
    }

    private void addLeftTransformedViewsDrawable(View v) {
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.ll_left);

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
            }

            @Override
            public String describe() {
                return "identity";
            }
        }, new HelloAndroidTextDrawable()));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.rotate(-30.0F);
            }

            @Override
            public String describe() {
                return "rotate(-30)";
            }
        }, new HelloAndroidTextDrawable()));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.scale(0.5F, 0.8F);
            }

            @Override
            public String describe() {
                return "scale(.5, .8)";
            }
        }, new HelloAndroidTextDrawable()));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.skew(0.1F, 0.3F);
            }

            @Override
            public String describe() {
                return "skew(.1, .3)";
            }
        }, new HelloAndroidTextDrawable()));
    }

    private void addRightTransformedViewsDrawable(View v) {
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.ll_right);

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.translate(30F, 10F);
            }

            @Override
            public String describe() {
                return "translate(30F, 10F)";
            }
        }, new HelloAndroidTextDrawable()));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.translate(110F, -20F);
                canvas.rotate(85.0F);
            }

            @Override
            public String describe() {
                return "translate(110F, -20F),rotate(85)";
            }
        }, new HelloAndroidTextDrawable()));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.translate(-50F, -20F);
                canvas.scale(2F, 1.2F);
            }

            @Override
            public String describe() {
                return "canvas.translate(-50F, -20F),canvas.scale(2F, 1.2F);";
            }
        }, new HelloAndroidTextDrawable()));

        layout.addView(new TransformedViewWidget(getActivity(), new Transformation() {
            @Override
            public void transform(Canvas canvas) {
                canvas.skew(0.1F, 0.3F);
                canvas.translate(-100F, -100F);
                canvas.scale(2.5F, 2F);
            }

            @Override
            public String describe() {
                return "complex";
            }
        }, new HelloAndroidTextDrawable()));
    }


}





























