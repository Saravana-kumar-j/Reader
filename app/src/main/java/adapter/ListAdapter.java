package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.reader.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<ListItem> listItems;

    public ListAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false);
        }

        ShapeableImageView imageView = convertView.findViewById(R.id.listImage);
        TextView textView = convertView.findViewById(R.id.listName);

        ListItem item = listItems.get(position);

        imageView.setImageResource(item.getImageResId());
        textView.setText(item.getText());

        return convertView;
    }
}
