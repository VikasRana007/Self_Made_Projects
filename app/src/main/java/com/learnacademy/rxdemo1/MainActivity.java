package com.learnacademy.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final static String TAG= "myApp";
    private String greeting= "Hello From RxJava";
    private Observable<String> myObservable;
//    private Observer<String> myObserver;
    private DisposableObserver<String> myObserver;


    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.tvGreeting);
        myObservable=Observable.just(greeting);       // To initialise the observer , this is one way to initialization
        myObservable.subscribeOn(Schedulers.io());         // Rx java make a new thread to do this task on IO thread .
        myObservable.observeOn(AndroidSchedulers.mainThread());  // observable data stream will observe on main thread With the help of AndroidSchedulers

        myObserver=new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG," onNext invoked");
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG," onError invoked");
            }


            @Override
            public void onComplete() {
                Log.i(TAG," onComplete invoked");
            }
        };

        myObservable.subscribe(myObserver);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
       myObserver.dispose();
    }

}