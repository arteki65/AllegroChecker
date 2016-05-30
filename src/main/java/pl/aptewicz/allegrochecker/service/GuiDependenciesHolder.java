package pl.aptewicz.allegrochecker.service;

import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

public interface GuiDependenciesHolder {
	public <T> T getDependency(GuiDependencyKind guiDependencyKind);

	public void putDependency(GuiDependencyKind guiDependencyKind,
			Object guiDependency);
}
