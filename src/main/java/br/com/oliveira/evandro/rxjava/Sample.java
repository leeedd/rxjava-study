package br.com.oliveira.evandro.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class Sample {

	public static void main(String[] args) {
		
		Observable.create(new Observable.OnSubscribe<Double>() {

			@Override
			public void call(Subscriber<? super Double> subscriber) {
				
				while (true) {
					
					double random = Math.random();
					subscriber.onNext(random * 10);
				}
			}
		})
		.sample(3, TimeUnit.SECONDS)
		.subscribe(i -> System.out.println(i.intValue()));
		
	}
}
