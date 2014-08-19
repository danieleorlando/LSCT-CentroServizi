package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

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

import centroservizi.infoporto.it.cservizi.adapter.EventiAdapter;
import centroservizi.infoporto.it.cservizi.model.Eventi;

public class EventiFragment extends Fragment {

    private List<Eventi> eventiList = new ArrayList<Eventi>();

    private ListView listView;
    private EventiAdapter adapter;
    private ProgressBar progressBar;

    public static EventiFragment newInstance() {
        EventiFragment fragment = new EventiFragment();
        return fragment;
    }
    public EventiFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new EventiTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventi, container, false);
        listView = (ListView)view.findViewById(R.id.list);
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

    public class EventiTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {

            final HttpClient httpClient = new DefaultHttpClient();
            final HttpGet httpGet = new HttpGet(Const.DOMAIN_API + "/@@get_events");

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                StringBuilder sb = inputStreamToString(httpResponse.getEntity().getContent());
                JsonParser parser = new JsonParser();
                JsonElement eventiElement = parser.parse(sb.toString());
                Gson gson = new Gson();
                if (eventiElement.isJsonObject()) {
                    Eventi eventi = gson.fromJson(eventiElement, Eventi.class);
                    eventiList.add(eventi);
                } else if (eventiElement.isJsonArray()) {
                    Type projectListType = new TypeToken<List<Eventi>>() {
                    }.getType();
                    eventiList = gson.fromJson(eventiElement, projectListType);
                }
            } catch (Exception e) {
                Log.v("E", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            //Log.v("finito","finito");
            //Log.v("news title", newsList.get(0).getTitle());
            progressBar.setVisibility(View.GONE);
            adapter = new EventiAdapter(getActivity(), eventiList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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
