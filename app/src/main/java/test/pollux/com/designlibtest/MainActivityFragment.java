package test.pollux.com.designlibtest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private Callback mCallback;
    private boolean mCanEnableError;
    private TextInputLayout mTextInputLayout;
    private Button mReplaceButton;

    public static MainActivityFragment newInstance(boolean enableError) {

        Bundle args = new Bundle();
        args.putBoolean("Error", enableError);
        MainActivityFragment fragment = new MainActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface Callback {
        void replaceMe();
    }


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        mReplaceButton = (Button) view.findViewById(R.id.replace);
        mReplaceButton.setOnClickListener(this);
        mTextInputLayout = (TextInputLayout) view.findViewById(R.id.textInputLayout);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mCanEnableError = arguments.getBoolean("Error", false);
        }


        if (mCanEnableError) {
            mReplaceButton.setText("Go Back");
            mTextInputLayout.setErrorEnabled(true);
            mTextInputLayout.setError("Just enabling Error for testing, now you can go back and see the previous fragment");
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    @Override
    public void onClick(View v) {
        if (mCanEnableError)
            getActivity().onBackPressed();
        else
            mCallback.replaceMe();

    }
}
