package pl.borecki.imageUploader.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.borecki.imageUploader.ImageUploader;
import pl.borecki.imageUploader.model.Image;
import pl.borecki.imageUploader.repo.ImageRepo;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

	private ImageRepo imageRepo;

	@Autowired
	public GalleryGui(ImageRepo imageRepo) {
		this.imageRepo = imageRepo;

		List<Image> all = imageRepo.findAll();
		all.stream().forEach(element -> {
			com.vaadin.flow.component.html.Image image =
					new com.vaadin.flow.component.html.Image(element.getImageAddress(), "brak");
			add(image);
		});
	}

}
