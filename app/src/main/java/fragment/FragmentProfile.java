package fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reader.Login_activity;
import com.example.reader.R;
import com.example.reader.Welcome_activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile #newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textView = view.findViewById(R.id.user_details);
        button = view.findViewById(R.id.btn_logout);

        if (user == null) {
            // If user is not logged in, redirect to login activity
            Intent intent = new Intent(requireContext(), Welcome_activity.class);
            startActivity(intent);
        } else {
            // If user is logged in, display user email
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out user and navigate to login activity
                auth.signOut();
                Intent intent = new Intent(requireContext(), Welcome_activity.class);
                startActivity(intent);
                // Since Fragment does not have finish(), you can finish the hosting activity if needed
                requireActivity().finish();
            }
        });

        return view;
    }
}
