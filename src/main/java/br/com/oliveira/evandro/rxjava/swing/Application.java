package br.com.oliveira.evandro.rxjava.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class Application extends JFrame {
	
	JPanel panel;
	JLabel label;
	JTextField input;
	
	public Application() {
		
		super("RxJava Study");
	}
	
	public void createPanel() {
		
		panel = new JPanel();
		panel.setSize(300, 300);
		
		label = new JLabel("RxJava Study");
		
		input = new JTextField(50);
		
		panel.add(label);
		panel.add(input);
		getContentPane().add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 300);
		
		pack();
		setVisible(true);
		
		input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				Observable.create(new Observable.OnSubscribe<String>() {

					@Override
					public void call(Subscriber<? super String> subscriber) {
						subscriber.onNext(new String(new char[]{e.getKeyChar()}));
					}
				})
				.debounce(5, TimeUnit.SECONDS)
				.subscribe(System.out::print);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
	}
	
	public static void main(String[] args) {
		
		Application app = new Application();
		app.createPanel();
	}

}
