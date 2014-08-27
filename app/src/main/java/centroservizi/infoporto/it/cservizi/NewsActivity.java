package centroservizi.infoporto.it.cservizi;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsActivity extends Activity {

    private String title;
    private String description;
    private String modification_date;
    private String text;
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
            text = extras.getString("text");
            uid = extras.getString("uid");
            likes = extras.getString("likes");
        }

        setContentView(R.layout.activity_news);

        TextView textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        TextView textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        TextView textViewDate = (TextView)findViewById(R.id.textViewDate);
        TextView textViewText = (TextView)findViewById(R.id.textViewText);
        TextView textViewLike = (TextView)findViewById(R.id.textViewLikes);

        textViewTitle.setText(title);
        textViewDescription.setText(description);
        textViewDate.setText(modification_date);
        textViewLike.setText(likes + " likes");

        Spanned spanned = Html.fromHtml(text);
        Spanned spannedHtml = Html.fromHtml(spanned.toString());
        textViewText.setText(spannedHtml);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
