package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uw.fragmentdemo.Movie;
import edu.uw.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private static final String IMBD_ARG = "imbid";
    private static final String TITLE_ARG = "title";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Movie movie) {

        Bundle args = new Bundle();
        args.putString(TITLE_ARG, movie.title);
        args.putString(IMBD_ARG, movie.imdbId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_detail, container, false);
        TextView title = (TextView) root.findViewById(R.id.txtMovieTitle);
        TextView imdbView = (TextView) root.findViewById(R.id.txtMovieIMDB);

        Bundle args = getArguments();
        title.setText(args.getString(TITLE_ARG));
        imdbView.setText(imdbView.getText() + args.getString(IMBD_ARG));

        return root;
    }

}
