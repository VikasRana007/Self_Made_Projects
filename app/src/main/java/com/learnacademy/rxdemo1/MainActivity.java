package com.learnacademy.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
    private final String greeting= "Hello From RxJava";
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;
    private DisposableObserver<String> myObserver2;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.tvGreeting);
        myObservable=Observable.just(greeting);       // To initialise the observer , this is one way to initialization
        myObservable.subscribeOn(Schedulers.io());         // Rx java make a new thread to do this task on IO thread .
        myObservable.observeOn(AndroidSchedulers.mainThread());  // observable data stream will observe on main thread With the help of AndroidSchedulers

        myObserver = new DisposableObserver<String>() {
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
        compositeDisposable.add(myObserver);
        myObservable.subscribe(myObserver);


    myObserver2 = new DisposableObserver<String>() {
        @Override
        public void onNext(String s) {
            Log.i(TAG," onNext invoked");
            Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();
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
        compositeDisposable.add(myObserver2);
        myObservable.subscribe(myObserver2);

}

    @Override
    public void onDestroy(){
        super.onDestroy();
//       myObserver.dispose();
//       myObserver2.dispose();
        compositeDisposable.clear();
    }

}