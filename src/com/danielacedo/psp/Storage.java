package com.danielacedo.psp;

public class Storage {
	public static final int MAX_STOCK = 20000;
	public static final int STARTING_STOCK = 8000;
	
	private static final Object mutexError = new Object();
	
	private int stock;
	private int days;
	
	private boolean error;
	
	public Storage(){
		stock = STARTING_STOCK;
		error = false;
	}
	
	public synchronized void receiveStock(int quantity){
		
		if(!getError()){
			System.out.println("Trying to receive "+quantity);
			
			if (stock+quantity > MAX_STOCK){
				System.out.println("Storage is full.");
				setError(true);	
				
			}else{
				stock += quantity;
				System.out.println("Shipment received, Stock is: "+stock);
			}
		}
		
	}
	
	public synchronized void withdrawStock(int quantity){
		if(!getError()){
			System.out.println("Trying to withdraw "+quantity);
			
			if (stock<quantity){
				System.out.println("There isn't enough stock to withdraw.");
				setError(true);
			}else{
				stock -= quantity;
				System.out.println("Withdrawal complete, Stock is: "+stock);
			}
		}
	}
	
	public synchronized int getDays(){
		return days;
	}
	
	public void setDays(int days){
		this.days = days;
	}
	
	public boolean getError(){
		synchronized (mutexError){
			return this.error;
		}
	}
	
	private void setError(boolean value){
		synchronized (mutexError){
			this.error = value;
		}
	}
}
