package org.it21902;

import java.io.IOException;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
//        ArrayList<Double> a = new ArrayList();
//        ArrayList<Double> b = new ArrayList();
//        ArrayList<Integer> c = new ArrayList();
//        ArrayList<Integer> d = new ArrayList();
//
//        a.add(37.9795);
//        a.add(23.7162);
//        c.add(1);
//        c.add(2);
//        c.add(3);
//        c.add(4);
//        c.add(5);
//        c.add(6);
//        c.add(0);
//        c.add(7);
//        c.add(8);
//        c.add(10);
//        
//        b.add(37.9795);
//        b.add(23.7162);
//        d.add(3);
//        d.add(4);
//        d.add(7);
//        d.add(8);
//        d.add(10);
//        d.add(0);
//        d.add(1);
//        d.add(2);
//        d.add(3);
//        d.add(4);
//        
//        City ci = new City();
//        ElderTraveller t = new ElderTraveller();
//        
//        ci.setGeodesicVector(a);
//        t.setGeodesicVector(b);
//
//        ci.setTermsVector(c);
//        t.setTermsVector(d);
        
//        System.out.println(t.calculateSimilarity(ci));
        
        try {
			City.RetrieveData("Athens", "Greece", "8268b79c6118c5f5c576506e09e1318d");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
