package com.milkmatters.honoursproject.milkmatters.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.milkmatters.honoursproject.milkmatters.R;
import com.milkmatters.honoursproject.milkmatters.database.BecomeDonorTableHelper;
import com.milkmatters.honoursproject.milkmatters.model.Question;

import java.util.ArrayList;

/**
 * Fragment that provides users with a 'Become a Donor' quiz.
 */
public class BecomeDonorFragment extends Fragment {
    private BecomeDonorFragment.OnFormCompleteListener mListener;

    // Context
    private Context context;
    // Data
    private int score=5;
    private int qid=0;
    private Question currentQ;
    private ArrayList<Question> quesList = new ArrayList<Question>();
    // Helper
    private BecomeDonorTableHelper becomeDonorTableHelper;
    //view
    private View view;
    private TextView txtQuestion;
    private RadioButton rda, rdb, rdc;
    private Button butNext;

    // Container Activity must implement this interface
    public interface OnFormComplete {
        void onReturnScore(int score);
    }


    public BecomeDonorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BecomeDonorFragment.
     */
    public static BecomeDonorFragment newInstance(String param1, String param2) {
        BecomeDonorFragment fragment = new BecomeDonorFragment();
        return fragment;
    }

    /**
     * Overridden onCreate method.
     * Gets the questions from the database.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this.getContext();
        this.becomeDonorTableHelper = new BecomeDonorTableHelper(this.context);
        this.quesList = this.becomeDonorTableHelper.getAllQuestions();
        this.becomeDonorTableHelper.closeDB();
    }

    /**
     * Overridden onCreateView method.
     * Sets up the view and questions.
     * @param inflater the layout inflater
     * @param container the container
     * @param savedInstanceState the saved instance state
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_become_donor, container, false);

        hideFAB();

        this.becomeDonorTableHelper = new BecomeDonorTableHelper(this.getContext());
        quesList=this.becomeDonorTableHelper.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)this.view.findViewById(R.id.textView1);
        rda=(RadioButton)this.view.findViewById(R.id.radio0);
        rdb=(RadioButton)this.view.findViewById(R.id.radio1);
        rdc=(RadioButton)this.view.findViewById(R.id.radio2);
        butNext=(Button)this.view.findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup) view.findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton) view.findViewById(grp.getCheckedRadioButtonId());
                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    score--;
                }
                if(qid<5){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    onFormCompletePressed(score);
                }
            }
        });

        return this.view;
    }

    /**
     * Method to show the floating action button
     */
    public void showFAB()
    {
        Button fab = (Button) this.getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
    }

    /**
     * Method to hide the floating action button
     */
    public void hideFAB()
    {
        Button fab = (Button) this.getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
    }

    /**
     * Method to set the question view
     */
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }

    /**
     * Interface call: used when the user has completed all the questions.
     * Displays the result fragment.
     * @param score the user's score
     */
    public void onFormCompletePressed(int score) {
        if (mListener != null) {
            mListener.onFormComplete(score);
        }
    }

    /**
     * Overridden onAttach method
     * @param context the context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BecomeDonorFragment.OnFormCompleteListener) {
            mListener = (BecomeDonorFragment.OnFormCompleteListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Callback interface.
     * Used when the user has answered all the questions.
     */
    public interface OnFormCompleteListener {
        void onFormComplete(int score);
    }
}
