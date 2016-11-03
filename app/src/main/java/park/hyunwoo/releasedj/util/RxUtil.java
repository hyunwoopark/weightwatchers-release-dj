package park.hyunwoo.releasedj.util;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Hyunwoo Park on 10/30/2016.
 */

public class RxUtil {

    public static final int TIMEOUT = 500;

    public static <T> Observable.Transformer<T, T> ioThreadToMainThread() {
        return observable -> observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public static Subscription debouncedOnClick(View view, Action1<Void> action) {
        return RxView.clicks(view)
                .debounce(TIMEOUT, TimeUnit.MILLISECONDS)
                .subscribe(action, error -> Timber.e(error, error.getMessage()));
    }
}