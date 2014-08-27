package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

import centroservizi.infoporto.it.cservizi.adapter.MessaggiAdapter;
import centroservizi.infoporto.it.cservizi.model.Messaggi;

public class MessaggiFragment extends Fragment {

    private List<Messaggi> messaggiList = new ArrayList<Messaggi>();

    private ListView listView;
    private ProgressBar progressBar;
    private MessaggiAdapter adapter;

    private int statusCode;

    public static MessaggiFragment newInstance() {
        MessaggiFragment fragment = new MessaggiFragment();
        return fragment;
    }

    public MessaggiFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        new MessaggiTask().execute();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.messaggi, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messaggi, container, false);
        listView = (ListView)view.findViewById(R.id.list);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),NewsActivity.class);
                intent.putExtra("title",newsList.get(i).getTitle());
                intent.putExtra("description",newsList.get(i).getDescription());
                intent.putExtra("modification_date",newsList.get(i).getModification_date());
                intent.putExtra("text",newsList.get(i).getText());
                intent.putExtra("uid",newsList.get(i).getUid());
                startActivity(intent);
            }
        });*/
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(4);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class MessaggiTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {

            final HttpClient httpClient = new DefaultHttpClient();
            final HttpGet httpGet = new HttpGet(Const.DOMAIN_API + "/@@get_inbox");

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode==200) {
                    StringBuilder sb = inputStreamToString(httpResponse.getEntity().getContent());
                    //JsonParser parser = new JsonParser();
                    //JsonElement messaggiElement = parser.parse(sb.toString());
                    JsonParser parser = new JsonParser();
                    JsonObject rootObejct = parser.parse(sb.toString()).getAsJsonObject();
                    JsonElement messaggiElement = rootObejct.get("messages");
                    Gson gson = new Gson();
                    if (messaggiElement.isJsonObject()) {
                        Messaggi messaggi = gson.fromJson(messaggiElement, Messaggi.class);
                        messaggiList.add(messaggi);
                    } else if (messaggiElement.isJsonArray()) {
                        Type projectListType = new TypeToken<List<Messaggi>>() {
                        }.getType();
                        messaggiList = gson.fromJson(messaggiElement, projectListType);
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
                adapter = new MessaggiAdapter(getActivity(), messaggiList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                messaggiList.get(0).getSubject();
            } else {
                //textViewNoConnection.setVisibility(View.VISIBLE);
                //textViewNoConnection.setText("Error " + statusCode);
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
