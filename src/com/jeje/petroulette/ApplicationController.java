package com.jeje.petroulette;

import model.Pet;
import android.app.Application;

public class ApplicationController extends Application {

	private Pet pet;
	
	public Pet getPet(){
		return pet;
	}
	
	public void setPet(Pet _pet){
		pet=_pet;
	}
	
	
}
