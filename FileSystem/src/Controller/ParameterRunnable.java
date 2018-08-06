package Controller;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ParameterRunnable<T1,T2,T3> implements Runnable{

	private ThreeConsumer<T1,T2,T3> threeFunc;
	private BiConsumer<T1,T2> twoFunc;
	private Consumer<T1> func;
	private T1 t1;
	private T2 t2;
	private T3 t3;
	
	public ParameterRunnable(ThreeConsumer<T1,T2,T3> threeFunc,T1 t1,T2 t2,T3 t3) {
		this.threeFunc = threeFunc;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
	
	public ParameterRunnable(BiConsumer<T1,T2> twoFunc,T1 t1,T2 t2) {
		this.twoFunc = twoFunc;
		this.t1 = t1;
		this.t2 = t2;
	}
	public ParameterRunnable(Consumer<T1> func,T1 t1) {
		this.func = func;
		this.t1 = t1;
	}
	
	@Override
	public void run() {
		if(this.threeFunc!=null)
			this.threeFunc.function(t1, t2, t3);
		if(this.twoFunc!=null)
			this.twoFunc.accept(t1,t2);;
		if(this.func!=null)
			this.func.accept(t1);
	}

}
