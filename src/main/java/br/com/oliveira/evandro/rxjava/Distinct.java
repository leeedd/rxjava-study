package br.com.oliveira.evandro.rxjava;

import rx.Observable;

public class Distinct {
	
	public static void main(String[] args) {
		
		Observable<Integer> observable = Observable.just(1, 1, 2, 2, 1, 2, 3, 4, 5, 1);
		//prints 12345
		observable.distinct().subscribe(System.out::print);
		
		System.out.println();
		
		//prints 12123451
		observable.distinctUntilChanged().subscribe(System.out::print);
	}

}
