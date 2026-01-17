
package org.example.client;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Add_QNAME = new QName("http://service.example.org/", "add");
    private final static QName _AddResponse_QNAME = new QName("http://service.example.org/", "addResponse");
    private final static QName _BlackListCreate_QNAME = new QName("http://service.example.org/", "blackListCreate");
    private final static QName _BlackListCreateResponse_QNAME = new QName("http://service.example.org/", "blackListCreateResponse");
    private final static QName _Delete_QNAME = new QName("http://service.example.org/", "delete");
    private final static QName _DeleteResponse_QNAME = new QName("http://service.example.org/", "deleteResponse");
    private final static QName _Etudiant_QNAME = new QName("http://service.example.org/", "etudiant");
    private final static QName _GetTauxAbsenceByCne_QNAME = new QName("http://service.example.org/", "getTauxAbsenceByCne");
    private final static QName _GetTauxAbsenceByCneResponse_QNAME = new QName("http://service.example.org/", "getTauxAbsenceByCneResponse");
    private final static QName _Read_QNAME = new QName("http://service.example.org/", "read");
    private final static QName _ReadResponse_QNAME = new QName("http://service.example.org/", "readResponse");
    private final static QName _Update_QNAME = new QName("http://service.example.org/", "update");
    private final static QName _UpdateResponse_QNAME = new QName("http://service.example.org/", "updateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link BlackListCreate }
     * 
     */
    public BlackListCreate createBlackListCreate() {
        return new BlackListCreate();
    }

    /**
     * Create an instance of {@link BlackListCreateResponse }
     * 
     */
    public BlackListCreateResponse createBlackListCreateResponse() {
        return new BlackListCreateResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link Etudiant }
     * 
     */
    public Etudiant createEtudiant() {
        return new Etudiant();
    }

    /**
     * Create an instance of {@link GetTauxAbsenceByCne }
     * 
     */
    public GetTauxAbsenceByCne createGetTauxAbsenceByCne() {
        return new GetTauxAbsenceByCne();
    }

    /**
     * Create an instance of {@link GetTauxAbsenceByCneResponse }
     * 
     */
    public GetTauxAbsenceByCneResponse createGetTauxAbsenceByCneResponse() {
        return new GetTauxAbsenceByCneResponse();
    }

    /**
     * Create an instance of {@link Read }
     * 
     */
    public Read createRead() {
        return new Read();
    }

    /**
     * Create an instance of {@link ReadResponse }
     * 
     */
    public ReadResponse createReadResponse() {
        return new ReadResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Add }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BlackListCreate }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BlackListCreate }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "blackListCreate")
    public JAXBElement<BlackListCreate> createBlackListCreate(BlackListCreate value) {
        return new JAXBElement<BlackListCreate>(_BlackListCreate_QNAME, BlackListCreate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BlackListCreateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BlackListCreateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "blackListCreateResponse")
    public JAXBElement<BlackListCreateResponse> createBlackListCreateResponse(BlackListCreateResponse value) {
        return new JAXBElement<BlackListCreateResponse>(_BlackListCreateResponse_QNAME, BlackListCreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Etudiant }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Etudiant }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "etudiant")
    public JAXBElement<Etudiant> createEtudiant(Etudiant value) {
        return new JAXBElement<Etudiant>(_Etudiant_QNAME, Etudiant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTauxAbsenceByCne }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTauxAbsenceByCne }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "getTauxAbsenceByCne")
    public JAXBElement<GetTauxAbsenceByCne> createGetTauxAbsenceByCne(GetTauxAbsenceByCne value) {
        return new JAXBElement<GetTauxAbsenceByCne>(_GetTauxAbsenceByCne_QNAME, GetTauxAbsenceByCne.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTauxAbsenceByCneResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTauxAbsenceByCneResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "getTauxAbsenceByCneResponse")
    public JAXBElement<GetTauxAbsenceByCneResponse> createGetTauxAbsenceByCneResponse(GetTauxAbsenceByCneResponse value) {
        return new JAXBElement<GetTauxAbsenceByCneResponse>(_GetTauxAbsenceByCneResponse_QNAME, GetTauxAbsenceByCneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Read }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Read }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "read")
    public JAXBElement<Read> createRead(Read value) {
        return new JAXBElement<Read>(_Read_QNAME, Read.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReadResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "readResponse")
    public JAXBElement<ReadResponse> createReadResponse(ReadResponse value) {
        return new JAXBElement<ReadResponse>(_ReadResponse_QNAME, ReadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Update }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

}
