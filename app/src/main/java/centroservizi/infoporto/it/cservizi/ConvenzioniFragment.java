package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
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

import centroservizi.infoporto.it.cservizi.adapter.ConvenzioniAdapter;
import centroservizi.infoporto.it.cservizi.model.Convenzioni;

public class ConvenzioniFragment extends Fragment {

    private List<Convenzioni> convenzioniList = new ArrayList<Convenzioni>();

    private ListView listView;
    private ConvenzioniAdapter adapter;
    private ProgressBar progressBar;

    public static ConvenzioniFragment newInstance() {
        ConvenzioniFragment fragment = new ConvenzioniFragment();
        return fragment;
    }
    public ConvenzioniFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ConvenzioniTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_convenzioni, container, false);
        listView = (ListView)view.findViewById(R.id.list);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(2);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class ConvenzioniTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {

            final HttpClient httpClient = new DefaultHttpClient();
            final HttpGet httpGet = new HttpGet(Const.DOMAIN_API + "/@@get_agreements");

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                StringBuilder sb = inputStreamToString(httpResponse.getEntity().getContent());
                JsonParser parser = new JsonParser();
                //JsonObject rootObejct = parser.parse(sb.toString()).getAsJsonObject();
                //JsonElement zombieElement = rootObejct.get("zombies");
                JsonElement convenzioniElement = parser.parse(sb.toString());
                Gson gson = new Gson();
                if (convenzioniElement.isJsonObject()) {
                    Convenzioni convenzioni = gson.fromJson(convenzioniElement, Convenzioni.class);
                    convenzioniList.add(convenzioni);
                } else if (convenzioniElement.isJsonArray()) {
                    Type projectListType = new TypeToken<List<Convenzioni>>() {
                    }.getType();
                    convenzioniList = gson.fromJson(convenzioniElement, projectListType);
                }
            } catch (Exception e) {
                Log.v("E", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            progressBar.setVisibility(View.GONE);
            adapter = new ConvenzioniAdapter(getActivity(), convenzioniList);
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
