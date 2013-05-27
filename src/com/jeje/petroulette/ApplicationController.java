package com.jeje.petroulette;

import json.Deserializer;
import model.Pet;
import android.app.Application;

public class ApplicationController extends Application {

	private Pet pet;
	private static Deserializer des=new Deserializer();
	
	public Pet getPet(){
		return pet;
	}
	
	public void setPet(Pet _pet){
		pet=_pet;
	}

	public Deserializer getDes() {
		return des;
	}

	
}
