package pl.aptewicz.allegrochecker.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

@Service
public class GuiDependenciesHolderImpl implements GuiDependenciesHolder {

	private final Map<GuiDependencyKind, Object> guiDependencies = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getDependency(GuiDependencyKind guiDependencyKind) {
		Object guiDependency = guiDependencies.get(guiDependencyKind);
		if (guiDependency != null && guiDependencyKind.getDependencyClass()
				.isInstance(guiDependency)) {
			return (T) guiDependency;
		}
		throw new IllegalStateException("Dependency of kind "
				+ guiDependencyKind.toString() + " does not exist.");
	}

	@Override
	public void putDependency(GuiDependencyKind guiDependencyKind,
			Object guiDependency) {
		if (guiDependencyKind.getDependencyClass().isInstance(guiDependency))
			guiDependencies.put(guiDependencyKind, guiDependency);
		else
			throw new IllegalStateException(
					"Invalid dependency type. You are trying to put dependency of type "
							+ guiDependencyKind.getDeclaringClass()
							+ ", but object that you are trying to put is of type "
							+ guiDependency.getClass());
	}

}
