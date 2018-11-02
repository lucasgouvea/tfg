package br.edu.unifei.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unifei.model.Screenshot;
import br.edu.unifei.repositories.ScreenshotRepository;

@Service("screnshotService")
@Transactional
public class ScreenshotServiceImpl implements ScreenshotService{
	
	@Autowired
	private ScreenshotRepository screenshotRepository;

	@Override
	public Screenshot findById(Long id) {
		return screenshotRepository.findById(id);
	}

	@Override
	public void saveScreenshot(Screenshot screenshot) {
		screenshotRepository.save(screenshot);
	}

	@Override
	public void updateScreenshot(Screenshot screenshot) {
		saveScreenshot(screenshot);
	}

	@Override
	public void deleteScreenshot(Screenshot screenshot) {
		screenshotRepository.delete(screenshot);
	}

	@Override
	public List<Screenshot> findAllScreenshots() {
		return screenshotRepository.findAll();
	
	}

	@Override
	public void deleteScreenshotById(long id) {
		screenshotRepository.delete(id);
	}
	
	
	
}
