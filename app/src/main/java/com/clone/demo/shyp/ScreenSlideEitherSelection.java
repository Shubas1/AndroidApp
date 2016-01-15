package com.clone.demo.shyp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 */
public class ScreenSlideEitherSelection extends Fragment {

    private ViewFlipper viewFlipper;

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlideEitherSelection create(int pageNumber) {
        ScreenSlideEitherSelection fragment = new ScreenSlideEitherSelection();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlideEitherSelection() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_either_selection, container, false);


        viewFlipper = (ViewFlipper) rootView.findViewById(R.id.view_flipper);

        // action on sign up button
        Button btnSignup = (Button) rootView.findViewById(R.id.signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Register2.class);
                startActivity(intent);

            }// end void onclick
        });

        // action on sign up button
        Button btnSignin = (Button) rootView.findViewById(R.id.signin);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Login2.class);
                startActivity(intent);

            }// end void onclick
        });

        // action on sign up button
        Button btnUsEmailInstead = (Button) rootView.findViewById(R.id.use_email_instead);
        btnUsEmailInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Use email instead");

                // Show the next Screen
                viewFlipper.showNext();

            }// end void onclick
        });



        // action on sign up button
        Button btnUseFacebookInstead = (Button) rootView.findViewById(R.id.use_facebook_instead);
        btnUseFacebookInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Use facebook instead");

                // Show the next Screen
                viewFlipper.showPrevious();

            }// end void onclick
        });


        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}// end main class
