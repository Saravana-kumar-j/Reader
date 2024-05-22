package fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

import com.example.reader.Add_url;
import com.example.reader.News_feed;
import com.example.reader.Reader_main;
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
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
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

        // Set up double-click listener
        GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return true;
            }
        });

        listView.setOnTouchListener((v, event) -> {
            if (gestureDetector.onTouchEvent(event)) {
                int position = listView.pointToPosition((int) event.getX(), (int) event.getY());
                if (position != ListView.INVALID_POSITION) {
                    ListItem item = (ListItem) adapter.getItem(position);
                    Intent intent;
                    switch (position) {
                        case 0:
                            intent = new Intent(getContext(), Add_url.class);
                            break;
                        case 1:
                            intent = new Intent(getContext(), Reader_main.class);
                            break;
                        case 2:
                            intent = new Intent(getContext(), News_feed.class);
                            break;
                        default:
                            return false;
                    }
                    startActivity(intent);
                    return true;
                }
            }
            return false;
        });

        return rootView;
    }
}
