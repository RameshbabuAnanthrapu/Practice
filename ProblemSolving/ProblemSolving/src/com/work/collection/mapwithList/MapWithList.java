package com.work.collection.mapwithList;

import java.util.ArrayList;
import java.util.List;

public class MapWithList {

	List<KeyValuePair> mapList;

	public MapWithList() {
		mapList = new ArrayList<>();

	}

	public void put(String k, String v) {

		KeyValuePair kvPair = new KeyValuePair(k, v);
		
			
		
			KeyValuePair oldKeyValuePair = null;
			for (KeyValuePair keyValuePair : this.mapList) {
				if (k.equals(keyValuePair.getKey())) {
					oldKeyValuePair = keyValuePair;
					
					break;
				}
			}
			this.mapList.add(kvPair);
			this.mapList.remove(oldKeyValuePair);

		}

	

	public List<KeyValuePair> getMapList() {
		return this.mapList;
	}
	
	public KeyValuePair get(String k){
		
		KeyValuePair result =  null;
		if(!k.isEmpty()) {
			
			for (KeyValuePair keyValuePair : this.mapList) {
				if(k.equals(keyValuePair.getKey())){
					
					result = keyValuePair;
				}
			}
		}
		
		return result;
		
	}

}
