package park.hyunwoo.releasedj.ui.album;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import park.hyunwoo.releasedj.R;
import park.hyunwoo.releasedj.adapter.AlbumAdapter;
import park.hyunwoo.releasedj.api.model.Albums;

public class AlbumActivity extends AppCompatActivity implements AlbumContract.View {

    @Inject
    AlbumContract.Presenter albumPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private int visibleThreshold = 5;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private int previousTotal = 0;
    private boolean loading;
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ReleaseDJApplication.getApp().getComponent().inject(this);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        albumPresenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        albumPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        albumPresenter.unsubscribe();
    }

    @Override
    public void addImages(Albums albums) {
        if (albumAdapter == null) {
            albumAdapter = new AlbumAdapter(albums.getAlbums(), this::showDetailView);
        } else {
            albumAdapter.addAll(albums.getAlbums());
        }
    }

    @Override
    public void setRecyclerAdapter(AlbumAdapter albumAdapter) {
        int spanCount = getResources().getInteger(R.integer.span_count);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    albumPresenter.loadAlbum();
                    loading = true;
                }
            }
        });
        recyclerView.setAdapter(albumAdapter);
    }

    @Override
    public void showDetailView(String id) {
    }

    @Override
    public void showSnackbarError(Throwable throwable) {
        Snackbar.make(coordinatorLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}