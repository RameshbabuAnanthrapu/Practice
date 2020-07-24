package com.work.cache;

import java.util.Arrays;
import java.util.Optional;

public class CacheImpl<K, V> implements ICache<K, V> {

	
	

	int INITIAL_CAP = 16;
	int index = 0;
	CacheHolder<K, V>[] cacheEntries = new CacheHolder[INITIAL_CAP];
	
	
	

	@Override
	public void put(K key, V value) {

		if (index == cacheEntries.length) {
			int newSize = cacheEntries.length * 2;
			cacheEntries = Arrays.copyOf(cacheEntries, newSize);
		}

		CacheHolder<K, V> entry = new CacheHolder<>(key, value);
		cacheEntries[index++] = entry;

	}

	@Override
	public V get(K key) {
		Optional<CacheHolder<K, V>> requiredElement = Arrays.stream(cacheEntries)
				.filter(e -> e.getKey().hashCode() == key.hashCode()).findFirst();
		return requiredElement.isPresent() ? requiredElement.get().getValue() : null;
	}
	
	
	
	
	

	

}
