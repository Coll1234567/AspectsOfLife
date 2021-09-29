package me.jishuna.aspectsoflife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class AspectRegistry {

	private Map<String, Aspect> registryMap = new HashMap<>();

	public void registerAspect(Aspect aspect) {
		this.registryMap.put(aspect.getKey(), aspect);
	}

	public Aspect getAspect(String key) {
		return this.registryMap.get(key);
	}

	public Optional<Aspect> getAspectOptional(String key) {
		return Optional.ofNullable(getAspect(key));
	}

	public Aspect getRandomAspect() {
		List<Aspect> aspects = new ArrayList<>(this.registryMap.values());
		return aspects.get(ThreadLocalRandom.current().nextInt(aspects.size()));
	}

}
