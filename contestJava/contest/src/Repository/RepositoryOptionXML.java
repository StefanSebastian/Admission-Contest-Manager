package Repository;

import Domain.Option;
import Validator.RepositoryException;
import Validator.ValidatorException;
import Validator.ValidatorOption;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOptionXML extends RepositoryOption {
    /*
    Name of the file
     */
    private String fileName;

    /*
    Constructor
     */
    public RepositoryOptionXML(String fileName, ValidatorOption validatorOption,
                               IRepository repositoryCandidate, IRepository repositoryDepartment){
        super(validatorOption, repositoryCandidate, repositoryDepartment);
        this.fileName = fileName;
        loadData();
    }

    /*
    Loads options from file
     */
    public void loadData(){
        try {
            entities.clear();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(fileName));

            Node root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    Option option = createOptionFromElement(element);
                    try {
                        super.save(option);
                    } catch (ValidatorException | RepositoryException e){
                        e.printStackTrace(); //corrupted data
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace(); //cant open file
        }
    }

    /*
    Create option from element
     */
    private Option createOptionFromElement(Element element){
        Integer id = Integer.parseInt(element.getAttribute("id"));
        Integer idCandidate = Integer.parseInt(element
                .getElementsByTagName("idCandidate")
                .item(0).getTextContent());
        Integer idDepartment = Integer.parseInt(element.
                getElementsByTagName("idDepartment")
                .item(0).getTextContent());
        return new Option(id, idCandidate, idDepartment);
    }

    /*
    Saves options to file
     */
    private void saveData(){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();

            Element rootElement = doc.createElement("options");
            doc.appendChild(rootElement);
            for (Option option : entities){
                Element opt = doc.createElement("option");
                rootElement.appendChild(opt);

                opt.setAttribute("id", option.getId().toString());
                opt.appendChild(createElementFromOption(doc, "idCandidate", option.getIdCandidate().toString()));
                opt.appendChild(createElementFromOption(doc, "idDepartment", option.getIdDepartment().toString()));
            }

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fileName);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /*
    Create element from option
     */
    public Element createElementFromOption(Document doc, String fieldName, String value){
        Element element = doc.createElement(fieldName);
        element.setTextContent(value);
        return element;
    }

    @Override
    public void save(Option option) throws ValidatorException, RepositoryException{
        super.save(option);
        saveData();
    }

    @Override
    public void delete(Integer id) throws RepositoryException{
        super.delete(id);
        saveData();
    }

    @Override
    public void clearAll(){
        super.clearAll();
        saveData();
    }

    @Override
    public void update(Integer id, Option newOption) throws ValidatorException, RepositoryException {
        super.update(id, newOption);
        saveData();
    }

}
