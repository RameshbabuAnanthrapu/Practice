package com.work.collection.mapwithList;

public class MainClass {

	/* Have implemented basic operations of MAP such as put and get with ArrayList.
	 * I am making sure to not to allow duplicates, the existing key value will be overridden with new value.
	 */
	public static void main(String[] args) {
		MapWithList mapWithList = new MapWithList();
		
		mapWithList.put("1", "Ramesh");
		mapWithList.put("2", "sindhu");
		mapWithList.put("2","minnu");
		mapWithList.put(new String("2"),"test");
		mapWithList.getMapList().forEach(l -> System.out.println(l.getKey()+", "+l.getValue()));
		
		System.out.println("item is :"+mapWithList.get("2"));

	}
	
	

}
