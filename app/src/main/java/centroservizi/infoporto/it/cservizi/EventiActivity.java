package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import centroservizi.infoporto.it.cservizi.R;

public class EventiActivity extends Activity {

    private String event_url;
    private String end_date;
    private String modification_date;
    private String title;
    private String description;
    private String contact_email;
    private String contact_name;
    private String contact_phone;
    private String start_date;
    private String uid;
    private String likes;

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
            start_date = extras.getString("start");
            end_date = extras.getString("end");
            event_url = extras.getString("event_url");
            uid = extras.getString("uid");
            likes = extras.getString("likes");
        }
        setContentView(R.layout.activity_eventi);

        TextView textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        TextView textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        TextView textViewDate = (TextView)findViewById(R.id.textViewDate);
        TextView textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        TextView textViewPhone = (TextView)findViewById(R.id.textViewPhone);
        TextView textViewName = (TextView)findViewById(R.id.textViewName);
        TextView textViewStart = (TextView)findViewById(R.id.textViewStart);
        TextView textViewEnd = (TextView)findViewById(R.id.textViewEnd);
        TextView textViewUrl = (TextView)findViewById(R.id.textViewUrl);
        TextView textViewLikes = (TextView)findViewById(R.id.textViewLikes);

        textViewTitle.setText(title);
        textViewDate.setText(modification_date);
        textViewEmail.setText(contact_email);
        textViewPhone.setText(contact_phone);
        textViewName.setText(contact_name);
        textViewStart.setText(start_date);
        textViewEnd.setText(end_date);
        textViewUrl.setText(event_url);
        textViewLikes.setText(likes + " likes");

        Spanned spanned = Html.fromHtml(description);
        Spanned spannedHtml = Html.fromHtml(spanned.toString());
        textViewDescription.setText(spannedHtml);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.eventi, menu);
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
}
