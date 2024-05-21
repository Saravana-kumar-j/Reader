package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.List;
import com.example.reader.R;

import adapter.ListItem;

public class ListAdapter extends ArrayAdapter<ListItem> {

    public Context context;
    public List<ListItem> items;

    public ListAdapter(Context context, List<ListItem> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false);
        }

        ListItem currentItem = items.get(position);

        ShapeableImageView imageView = convertView.findViewById(R.id.listImage);
        TextView textView = convertView.findViewById(R.id.listName);

        imageView.setImageResource(currentItem.getImageResource());
        textView.setText(currentItem.getText());

        return convertView;
    }
}
