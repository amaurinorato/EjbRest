package com.example.ejbrest;

import org.jboss.resteasy.util.Base64;

public class Main {
	
	public static void main(String[] args) {
		System.out.println(Base64.encodeBytes("Amauri:123".getBytes()));
	}

}
