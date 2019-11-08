package pl.borecki.imageUploader.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.borecki.imageUploader.ImageUploader;

@Route("upload")
public class UploadGui extends VerticalLayout {

	private ImageUploader imageUploader;
	
	@Autowired
	public UploadGui(ImageUploader imageUploader) {
		this.imageUploader = imageUploader;
		
		Label label = new Label();
		TextField textField = new TextField();
		Button button = new Button("upload");
		button.addClickListener(ClickEvent -> 
		{
			String uploadedImageUrl = imageUploader.uploadFileAndSaveToDb(textField.getValue());
			Image image = new Image(uploadedImageUrl, "Image");
			label.setText("Image added");
			add(label);
			add(image);
		
		});
		
		add(textField);
		add(button);
	}


	
}
