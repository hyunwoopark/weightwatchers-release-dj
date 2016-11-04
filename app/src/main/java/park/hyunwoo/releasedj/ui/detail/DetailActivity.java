package park.hyunwoo.releasedj.ui.detail;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import park.hyunwoo.releasedj.R;
import park.hyunwoo.releasedj.ReleaseDJApplication;
import park.hyunwoo.releasedj.api.model.DetailedAlbum;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    private static final String ID = "id";
    private static final String NAME = "name";

    @Inject
    DetailContract.Presenter detailPresenter;

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
        ReleaseDJApplication.getApp().getComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        detailPresenter.setView(this);
        if (getIntent().hasExtra(ID)) {
            detailPresenter.loadAlbum(getIntent().getStringExtra(ID));
        }
    }

    @Override
    public void addDetails(DetailedAlbum album) {
        Glide.with(this)
                .load(album.getImages().get(0).getUrl())
                .error(R.mipmap.ic_launcher)
                .into(albumImage);

        name.append(album.getName());
        date.append(album.getReleaseDate());
        album.getArtistsString().subscribe(s -> artists.append(s));
        album.getTracks().getTracksString().subscribe(s -> tracks.append(s));
    }

    @Override
    public void showSnackbarError(Throwable throwable) {
        Snackbar.make(coordinatorLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
