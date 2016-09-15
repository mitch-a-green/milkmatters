package com.milkmatters.honoursproject.milkmatters.fragments;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.milkmatters.honoursproject.milkmatters.R;


public class ResultFragment extends Fragment{
    // Context
    private Context context;
    // Data
    private int score;
    private View view;

    /**
     * Required empty public constructor
     */
    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AboutFragment.
     */
    public static ResultFragment newInstance(int score) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt("score", score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this.getActivity().getApplicationContext();
        if (getArguments() != null) {
            this.score = getArguments().getInt("score");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_become_donor_result, container, false);

        hideFAB();

        //get rating bar object
        RatingBar bar=(RatingBar)this.view.findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)this.view.findViewById(R.id.textResult);
        //display score
        bar.setRating(score);
        switch (score)
        {
            case 0: t.setText("Unfortunately you are ineligible to become a donor. \n" +
                    "However, please consider donating equipment or money (which can be done through myschool/myvillage).");
                break;
            case 1:
            case 2: t.setText("Unfortunately you are ineligible to become a donor. \n" +
                    "However, please consider donating equipment or money (which can be done through myschool/myvillage).");
                break;
            case 3:
            case 4:t.setText("Unfortunately you are ineligible to become a donor. \n" +
                    "However, please consider donating equipment or money (which can be done through myschool/myvillage).");
                break;
            case 5:t.setText("Congratulations, you are eligible to be a potential donor. \n" +
                    "Please get in contact with us if you are willing to make a difference.");
                break;
        }

        return this.view;
    }

    /**
     * Method to show the floating action button
     */
    public void showFAB()
    {
        FloatingActionButton fab = (FloatingActionButton) this.getActivity().findViewById(R.id.fab);
        fab.show();
    }

    /**
     * Method to hide the floating action button
     */
    public void hideFAB()
    {
        FloatingActionButton fab = (FloatingActionButton) this.getActivity().findViewById(R.id.fab);
        fab.hide();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
