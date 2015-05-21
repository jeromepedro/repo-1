package form;


import org.springframework.web.multipart.MultipartFile;

public class MiForm {

	private MultipartFile fileName; 
	
	
	public void setFilename(MultipartFile fileName)
	{
		this.fileName = fileName; 
	}
	
	public MultipartFile getFilename()
	{
		return fileName; 
	}
	

}
