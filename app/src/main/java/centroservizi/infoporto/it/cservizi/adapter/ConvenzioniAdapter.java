package centroservizi.infoporto.it.cservizi.adapter;

import centroservizi.infoporto.it.cservizi.R;
import centroservizi.infoporto.it.cservizi.model.Convenzioni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ConvenzioniAdapter extends BaseAdapter{

    private Context context;
    private List<Convenzioni> convenzioni;
    private LayoutInflater mLayoutInflater = null;

    public ConvenzioniAdapter(Context context, List<Convenzioni> convenzioni) {
        this.context = context;
        this.convenzioni = convenzioni;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return convenzioni.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_convenzioni, viewGroup, false);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.textViewTitle);
            holder.date = (TextView) view.findViewById(R.id.textViewDate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(convenzioni.get(i).getTitle());
        holder.date.setText(convenzioni.get(i).getModification_date());
        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView date;
    }
}
