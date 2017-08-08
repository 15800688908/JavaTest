package test;

/**
 * 
 * 继承抽象类和实现接口
 *
 */
interface Player {
	int flag = 1;

	void play();

	void pause();

	void stop();
}

abstract class Playing {
	public void display(Object oPara) {
		System.out.println(oPara);
	}

	abstract void winRun();
}

public class NewPlay extends Playing implements Player {

	@Override
	public void play() {
		display("NewPlay.play()");

	}

	@Override
	public void pause() {
		display("NewPlay.pause()");

	}

	@Override
	public void stop() {
		display("NewPlay.stop()");

	}

	@Override
	void winRun() {
		display("NewPlay.winRun()");

	}

	public static void main(String[] args) {
		NewPlay p = new NewPlay();
		p.play();
		p.pause();
		p.stop();
		p.winRun();
	}
}
