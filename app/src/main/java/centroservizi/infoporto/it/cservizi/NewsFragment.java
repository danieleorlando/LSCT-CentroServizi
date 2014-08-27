package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import centroservizi.infoporto.it.cservizi.adapter.NewsAdapter;
import centroservizi.infoporto.it.cservizi.model.News;

public class NewsFragment extends Fragment {

    private List<News> newsList = new ArrayList<News>();

    private ListView listView;
    private TextView textViewNoConnection;
    private NewsAdapter adapter;
    private ProgressBar progressBar;

    private int statusCode;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }
    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new NewsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        textViewNoConnection = (TextView)view.findViewById(R.id.noconnection);
        textViewNoConnection.setVisibility(View.GONE);
        listView = (ListView)view.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),NewsActivity.class);
                intent.putExtra("title",newsList.get(i).getTitle());
                intent.putExtra("description",newsList.get(i).getDescription());
                intent.putExtra("modification_date",newsList.get(i).getModification_date());
                intent.putExtra("text",newsList.get(i).getText());
                intent.putExtra("uid",newsList.get(i).getUid());
                intent.putExtra("likes",newsList.get(i).getLikes());
                startActivity(intent);
            }
        });
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class NewsTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {

            final HttpClient httpClient = new DefaultHttpClient();
            final HttpGet httpGet = new HttpGet(Const.DOMAIN_API + "/@@get_news");

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode==200) {
                    StringBuilder sb = inputStreamToString(httpResponse.getEntity().getContent());
                    JsonParser parser = new JsonParser();
                    JsonElement newsElement = parser.parse(sb.toString());
                    Gson gson = new Gson();
                    if (newsElement.isJsonObject()) {
                        News news = gson.fromJson(newsElement, News.class);
                        newsList.add(news);
                    } else if (newsElement.isJsonArray()) {
                        Type projectListType = new TypeToken<List<News>>() {
                        }.getType();
                        newsList = gson.fromJson(newsElement, projectListType);
                    }
                }
            } catch (Exception e) {
                statusCode = -1;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            progressBar.setVisibility(View.GONE);
            if (statusCode==200) {
                adapter = new NewsAdapter(getActivity(), newsList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                textViewNoConnection.setVisibility(View.VISIBLE);
                textViewNoConnection.setText("Error " + statusCode);
            }
        }
    }

    private StringBuilder inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            //
        }
        return total;
    }
}
