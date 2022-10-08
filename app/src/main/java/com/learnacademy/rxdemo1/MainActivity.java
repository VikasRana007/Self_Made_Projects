package com.learnacademy.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final static String TAG= "myApp";
//    private String[] greetings = {"Hello A", "Hello B", "Hello C"};
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myObservable = Observable.just("Hello A", "Hello B", "Hello C");
        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
                        );
}

private DisposableObserver getObserver(){

    myObserver = new DisposableObserver<String>() {
        @Override
        public void onNext(String s) {
            Log.i(TAG," onNext invoked  : "+s);
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG," onError invoked");
        }
        @Override
        public void onComplete() {
            Log.i(TAG," onComplete invoked  : ");
        }
    };
        return myObserver;
}
}