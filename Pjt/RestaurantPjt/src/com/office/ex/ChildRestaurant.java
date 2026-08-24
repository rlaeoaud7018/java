package com.office.ex;

public class ChildRestaurant extends PapaRestaurant {

	
	static final int PAPA = 1;
	static final int ME = 2;
	
	@Override
	public void makeBokkeumbap(int family) {
		
//		super.makeBokkeumbap();
		
		switch (family) {
		case PAPA:
			super.makeBokkeumbap();
			break;
			
		case ME: 
			System.out.println("[ChildRestaurant] makeBokkeumbap");
			break;
		}
		
		
	}
	
	/*
	@Override
	public void makeBokkeumbap() {
		System.out.println("[ChildRestaurant] makeBokkeumbap");
	}
	*/
	
	
	
	/*
	 * 
	 */
	
	
}
