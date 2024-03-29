package spaceshapes.forms.util;

/**
 * Interface to be implemented by classes that process Form data.
 * 
 * Implementing classes typically implement process() by reading data from the
 * supplied form, and performing some action based on the data.
 * 
 * @author Tommy Shi, Ian Warren
 *
 */
public interface FormHandler {
	void processForm(Form form);
}
