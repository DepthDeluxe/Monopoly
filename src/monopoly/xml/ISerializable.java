package monopoly.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface ISerializable {
	Element serialize(Document doc);
	
	void deSerialize(Element rootNode);
}