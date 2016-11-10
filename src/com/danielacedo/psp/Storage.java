package com.danielacedo.psp;

/**
 * Class representing a storage of stock that receives and sends. 
 * @author Daniel Acedo Calderón
 *
 */
public class Storage {
	public static final int MAX_STOCK = 20000;		//Maximum stock 
	public static final int STARTING_STOCK = 8000;	//Starting amount of stock
	
	private static final Object mutexError = new Object();	//Mutex for synchronizing the error attribute
	
	private int stock;	//Amount of current stock
	private int days;	//Current day
	
	private boolean error;	//Flag for reporting whether there are any error
	
	public Storage(){
		stock = STARTING_STOCK;
		error = false;
		days = 1;
	}
	
	/**
	 * Synchronized method that receives a shipment of stock
	 * @param quantity Amount of stock received
	 */
	public synchronized void receiveStock(int quantity){
		
		//If there aren't any errors at this point
		if(!getError()){
			System.out.println("Trying to receive "+quantity);
			
			if (stock+quantity > MAX_STOCK){ //If we try to add more stock that we can store, we flag an error
				System.out.println("Storage is full.");
				setError(true);	
				
			}else{
				stock += quantity;			//If there are no errors, we add the corresponding amount
				System.out.println("Shipment received, Stock is: "+stock);
			}
		}
		
	}
	
	/**
	 * Synchronized method that withdraws an amount of stock
	 * @param quantity Quantity of stock withdrawn
	 */
	public synchronized void withdrawStock(int quantity){
		
		//If there aren't any errors at this point
		if(!getError()){
			System.out.println("Trying to withdraw "+quantity);
			
			if (stock<quantity){ //If we haven't enough stock to withdraw, we flag an error
				System.out.println("There isn't enough stock to withdraw.");
				setError(true);
			}else{				//If there are no errors, we subtract the corresponding amount
				stock -= quantity;
				System.out.println("Withdrawal complete, Stock is: "+stock);
			}
		}
	}
	
	
	public int getDays(){
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
