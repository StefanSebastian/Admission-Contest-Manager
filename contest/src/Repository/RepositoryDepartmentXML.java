package Repository;

import Domain.Department;
import Validator.RepositoryException;
import Validator.ValidatorDepartment;
import Validator.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

/**
 * Created by Sebi on 02-Dec-16.
 */
public class RepositoryDepartmentXML extends RepositoryDepartment {
    /*
    Name of xml file
     */
    private String fileName;

    /*
    Constructor
     */
    public RepositoryDepartmentXML(String fileName, ValidatorDepartment validatorDepartment) {
        super(validatorDepartment);
        this.fileName = fileName;
        loadData();
    }

    /*
    Loads departments from file
     */
    public void loadData(){
        try {
            entities.clear();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);

            Node root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    Department department = createDepartmentFromElement(element);
                    try{
                        super.save(department);
                    } catch (RepositoryException | ValidatorException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Creates department from xml element
     */
    private Department createDepartmentFromElement(Element element){
        Integer id = Integer.parseInt(element.getAttribute("id"));
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        Integer nrPlaces = Integer.parseInt(
                element.getElementsByTagName("numberOfPlaces").item(0).getTextContent());
        return new Department(id, name, nrPlaces);
    }

    /*
    Saves elements to file
     */
    public void saveData(){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("departments");
            document.appendChild(rootElement);
            for (Department department : entities){
                Element dep = document.createElement("department");
                rootElement.appendChild(dep);

                dep.setAttribute("id", department.getId().toString());
                dep.appendChild(createElementFromDepartment(
                        document, "name", department.getName()));
                dep.appendChild(createElementFromDepartment(
                        document, "numberOfPlaces", department.getNumberOfPlaces().toString()));
            }

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileName);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /*
    Creates an element from a department field
     */
    public Element createElementFromDepartment(Document doc, String fieldName, String value){
        Element element = doc.createElement(fieldName);
        element.setTextContent(value);
        return element;
    }

    @Override
    public void save(Department department) throws ValidatorException, RepositoryException {
        super.save(department);
        saveData();
    }

    @Override
    public void delete(Integer id) throws RepositoryException {
        super.delete(id);
        saveData();
    }

    @Override
    public void update(Integer id, Department newDepartment) throws ValidatorException, RepositoryException {
        super.update(id, newDepartment);
        saveData();
    }

    @Override
    public void clearAll(){
        super.clearAll();
        saveData();
    }
}
