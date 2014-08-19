package centroservizi.infoporto.it.cservizi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import centroservizi.infoporto.it.cservizi.R;
import centroservizi.infoporto.it.cservizi.model.Convenzioni;
import centroservizi.infoporto.it.cservizi.model.Eventi;

public class EventiAdapter extends BaseAdapter{

    private Context context;
    private List<Eventi> eventi;
    private LayoutInflater mLayoutInflater = null;

    public EventiAdapter(Context context, List<Eventi> eventi) {
        this.context = context;
        this.eventi = eventi;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventi.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_eventi, viewGroup, false);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.textViewTitle);
            holder.date = (TextView) view.findViewById(R.id.textViewDate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(eventi.get(i).getTitle());
        holder.date.setText(eventi.get(i).getModification_date());
        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView date;
    }
}
