package com.danielacedo.psp;

public class StorageTest {

	public static void main(String[] args) {
		Storage storage = new Storage();
		Withdrawal withdrawal = new Withdrawal(storage);
		Shipment shipment = new Shipment(storage);
		
		withdrawal.start();
		shipment.start();
	}

}
