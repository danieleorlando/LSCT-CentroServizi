package centroservizi.infoporto.it.cservizi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import centroservizi.infoporto.it.cservizi.R;
import centroservizi.infoporto.it.cservizi.model.News;

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<News> news;
    private LayoutInflater mLayoutInflater = null;

    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return news.size();
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
        holder.title.setText(news.get(i).getTitle());
        holder.date.setText(news.get(i).getModification_date());
        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView date;
    }
}
