package park.hyunwoo.releasedj.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import park.hyunwoo.releasedj.R;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_album_image)
    ImageView albumImage;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_date)
    TextView date;
    @BindView(R.id.detail_artists)
    TextView artists;
    @BindView(R.id.detail_tracks)
    TextView tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


}
