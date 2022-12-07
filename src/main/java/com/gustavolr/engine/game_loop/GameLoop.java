package com.gustavolr.engine.game_loop;

public final class GameLoop implements Runnable{

    private static Thread thread;

    private boolean isRunning;

    private GameLoopListener listener;

    public GameLoop(GameLoopListener gameLoopListener) {
        this.listener = gameLoopListener;
        this.start();
    }

    public synchronized void start() {

        thread = new Thread(this);

        isRunning = true; 

        thread.start();
    }

    @Override
    public void run() {

		//Fazendo com que o jogo rode a 60fps
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;
		

		while(isRunning) {
			
			//Calculo do tempo até então decorrido
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			//Ao passar um segundo ou mais
			if ( delta >= 1) {

                this.listener.update();
                this.listener.render();
				
				delta--;
			}
			
		}
		//Parar o programa
		try {
			stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

	public void stop() throws InterruptedException {
		
		isRunning = false;
		thread.join();
	}
    
}
