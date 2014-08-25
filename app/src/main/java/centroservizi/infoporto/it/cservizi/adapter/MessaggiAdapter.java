package centroservizi.infoporto.it.cservizi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import centroservizi.infoporto.it.cservizi.R;
import centroservizi.infoporto.it.cservizi.model.Messaggi;

public class MessaggiAdapter extends BaseAdapter {

    private Context context;
    private List<Messaggi> messaggi;
    private LayoutInflater mLayoutInflater = null;

    public MessaggiAdapter(Context context, List<Messaggi> messaggi) {
        this.context = context;
        this.messaggi = messaggi;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messaggi.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_messaggi, viewGroup, false);
            holder = new ViewHolder();
            holder.from = (TextView)view.findViewById(R.id.textViewFrom);
            holder.subject = (TextView)view.findViewById(R.id.textViewSubject);
            holder.date = (TextView)view.findViewById(R.id.textViewDate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.from.setText(messaggi.get(i).getFrom_id());
        holder.subject.setText(messaggi.get(i).getSubject());
        holder.date.setText(messaggi.get(i).getCreated_at());
        return view;
    }

    static class ViewHolder {
        TextView from;
        TextView subject;
        TextView date;
    }
}
