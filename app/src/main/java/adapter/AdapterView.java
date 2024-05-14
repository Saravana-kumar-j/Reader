package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class AdapterView extends FragmentStateAdapter {
    ArrayList<Fragment> array;
    public AdapterView(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> array) {
        super(fragmentActivity);
        this.array = array;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return array.get(position);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}
