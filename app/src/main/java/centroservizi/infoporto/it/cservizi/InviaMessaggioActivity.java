package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import centroservizi.infoporto.it.cservizi.R;

public class InviaMessaggioActivity extends Activity {

    private HttpClient httpclient;
    private HttpPost httppost;

    private EditText editTextOggetto;
    private EditText editTextTesto;

    private String oggetto;
    private String testo;

    private int statusCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invia_messaggio);

        editTextOggetto = (EditText)findViewById(R.id.editTextOggetto);
        editTextTesto = (EditText)findViewById(R.id.editTextTesto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.invia_messaggio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.inviamessaggio) {
            oggetto = editTextOggetto.getText().toString();
            testo = editTextTesto.getText().toString();
            if (oggetto != null && !oggetto.isEmpty() && testo != null && !testo.isEmpty()) {
                Toast.makeText(this,getString(R.string.invioincorso),Toast.LENGTH_SHORT).show();
                new SendIMTask().execute();
            } else {
                Toast.makeText(this,getString(R.string.verificadati),Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SendIMTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... params) {

            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Const.DOMAIN_API+"/send_im");
            httppost.setHeader("Content-Type","application/json");

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("from_id", "admin");
                jsonObject.accumulate("to_id", "admin");
                jsonObject.accumulate("subject",oggetto);
                jsonObject.accumulate("message",testo);

                String json = jsonObject.toString();

                StringEntity se = new StringEntity(json);
                httppost.setEntity(se);
                HttpResponse response = httpclient.execute(httppost);
                statusCode = response.getStatusLine().getStatusCode();
                if (statusCode==200) {
                    return EntityUtils.toString(response.getEntity());
                }
            } catch (Exception e) {
                statusCode = -1;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            if (statusCode == 200) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    Log.v("response",jsonResponse.toString());
                    if (jsonResponse.getString("response").equals("Message sent.")) {
                        Toast.makeText(getApplicationContext(),getString(R.string.messaggioinviato),Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),getString(R.string.erroreinvio) + " " + jsonResponse.getString("response"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(),getString(R.string.erroreinvio) + " " + statusCode ,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
