package br.com.oliveira.evandro.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {

			public void call(Subscriber<? super Integer> subscriber) {
				for (int i = 0; i < 100; i++) {
					subscriber.onNext(new Integer(i));
				}
				subscriber.onCompleted();
			}
		});
		
		observable.observeOn(Schedulers.newThread()).subscribe(i -> {
			System.out.println(Thread.currentThread().getName() + ": item " + i);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		observable.subscribe(i -> {
			System.out.println(Thread.currentThread().getName()+ ": item " + i);
			try {
				Thread.sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
}
