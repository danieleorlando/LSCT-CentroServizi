package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ConvenzioniActivity extends Activity {

    private String title;
    private String modification_date;
    private String description;
    private String contact_email;
    private String contact_name;
    private String contact_phone;
    private String start;
    private String end;
    private String uid;
    private String likes;

    private int statusCode;

    private Button button;
    private HttpClient httpclient;
    private HttpPost httppost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            description = extras.getString("description");
            modification_date = extras.getString("modification_date");
            contact_email = extras.getString("contact_email");
            contact_phone = extras.getString("contact_phone");
            contact_name = extras.getString("contact_name");
            start = extras.getString("start");
            end = extras.getString("end");
            uid = extras.getString("uid");
            likes = extras.getString("likes");
        }
        setContentView(R.layout.activity_convenzioni);

        TextView textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        TextView textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        TextView textViewDate = (TextView)findViewById(R.id.textViewDate);
        TextView textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        TextView textViewPhone = (TextView)findViewById(R.id.textViewPhone);
        TextView textViewName = (TextView)findViewById(R.id.textViewName);
        TextView textViewStart = (TextView)findViewById(R.id.textViewStart);
        TextView textViewEnd = (TextView)findViewById(R.id.textViewEnd);
        TextView textViewLikes = (TextView)findViewById(R.id.textViewLikes);

        button = (Button)findViewById(R.id.use_agreement);

        textViewTitle.setText(title);
        textViewDate.setText(modification_date);
        textViewEmail.setText(contact_email);
        textViewPhone.setText(contact_phone);
        textViewName.setText(contact_name);
        textViewStart.setText(start);
        textViewEnd.setText(end);
        textViewLikes.setText(likes + " likes");

        Spanned spanned = Html.fromHtml(description);
        Spanned spannedHtml = Html.fromHtml(spanned.toString());
        textViewDescription.setText(spannedHtml);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText(getString(R.string.attendere));
                new UseAgreementTask().execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.convenzioni, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class UseAgreementTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... params) {

            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Const.DOMAIN_API+"/set_use_agreement");
            httppost.setHeader("Content-Type","application/json");

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("agreement_id", uid);
                jsonObject.accumulate("user_id", "admin");

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
                    if (jsonResponse.getString("response").equals("Request saved.")) {
                        button.setText(getString(R.string.convenzioneusata));
                    } else {
                        button.setText("Error " + jsonResponse.getString("response"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                button.setText("Error " + statusCode);
            }
        }
    }

}
