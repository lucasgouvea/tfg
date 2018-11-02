package br.edu.unifei.service;

import java.util.List;

import br.edu.unifei.model.Screenshot;

public interface ScreenshotService {

	
	Screenshot findById(Long id);
	void saveScreenshot(Screenshot screenshot);
	void updateScreenshot(Screenshot screenshot);
	void deleteScreenshot(Screenshot screenshot);
	List<Screenshot> findAllScreenshots();
	void deleteScreenshotById(long id);
	

}
