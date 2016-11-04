package park.hyunwoo.releasedj.ui.detail;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import park.hyunwoo.releasedj.R;
import park.hyunwoo.releasedj.api.model.DetailedAlbum;

public class DetailActivity extends AppCompatActivity implements DetailContract.View{

    private static final String ID = "id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
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
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("id")) {
            showDetailView(getIntent().getStringExtra("id"));
        }
    }

    @Override
    public void addDetails(DetailedAlbum album) {
        name.setText(album.getName());
        date.setText(album.getReleaseDate());
        album.getArtistsString().subscribe(s -> artists.setText(s));
        album.getTracksString().subscribe(s -> tracks.setText(s));
    }

    @Override
    public void showSnackbarError(Throwable throwable) {
        Snackbar.make(coordinatorLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showDetailView(String id) {

    }
}
