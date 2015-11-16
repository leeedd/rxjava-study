package br.com.oliveira.evandro.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class Debounce {

	public static void main(String[] args) {
		
		Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> subscriber) {
				
				for (int i = 0; i < 100; i++) {
					
					Double random = Math.random() * 5;
					try {
						Thread.sleep(random.intValue() * 1000);
						System.out.print("Generated " + i + " in "+ random.intValue()  + " sec\n");
						subscriber.onNext("Published " + i + "\n");
					} catch (InterruptedException e) {
						subscriber.onError(e);
						e.printStackTrace();
					}
				}
				
				subscriber.onCompleted();
			}
		});
		
		observable.debounce(3, TimeUnit.SECONDS)
		.subscribe(i -> System.out.print(Thread.currentThread().getName() + " " + i));
	}
}
