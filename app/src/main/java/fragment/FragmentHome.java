package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import com.example.reader.R;
import adapter.ListAdapter;
import adapter.ListItem;

public class FragmentHome extends Fragment {

    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the list items
        List<ListItem> listItems = new ArrayList<>();
        listItems.add(new ListItem(R.drawable.link_icon, "Add URL to database"));
        listItems.add(new ListItem(R.drawable.read_icon, "Read Collections"));
        listItems.add(new ListItem(R.drawable.news_icon, "News Feed"));

        // Initialize the ListView and adapter
        ListView listView = rootView.findViewById(R.id.list_view);
        ListAdapter adapter = new ListAdapter(getContext(), listItems);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        return rootView;
    }
}
