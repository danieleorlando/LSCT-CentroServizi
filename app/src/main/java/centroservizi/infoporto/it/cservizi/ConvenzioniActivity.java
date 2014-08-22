package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import centroservizi.infoporto.it.cservizi.R;

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

        textViewTitle.setText(title);
        textViewDate.setText(modification_date);
        textViewEmail.setText(contact_email);
        textViewPhone.setText(contact_phone);
        textViewName.setText(contact_name);
        textViewStart.setText(start);
        textViewEnd.setText(end);

        Spanned spanned = Html.fromHtml(description);
        Spanned spannedHtml = Html.fromHtml(spanned.toString());
        textViewDescription.setText(spannedHtml);
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
}
