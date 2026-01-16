
package org.example.client.ws;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.client.ws package. 
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

    private static final QName _AjouterEtudiant_QNAME = new QName("http://example.org/", "ajouterEtudiant");
    private static final QName _AjouterEtudiantResponse_QNAME = new QName("http://example.org/", "ajouterEtudiantResponse");
    private static final QName _Etudiant_QNAME = new QName("http://example.org/", "etudiant");
    private static final QName _GetEtudiantsTries_QNAME = new QName("http://example.org/", "getEtudiantsTries");
    private static final QName _GetEtudiantsTriesResponse_QNAME = new QName("http://example.org/", "getEtudiantsTriesResponse");
    private static final QName _GetEtudiantsValidant_QNAME = new QName("http://example.org/", "getEtudiantsValidant");
    private static final QName _GetEtudiantsValidantResponse_QNAME = new QName("http://example.org/", "getEtudiantsValidantResponse");
    private static final QName _GetMajorant_QNAME = new QName("http://example.org/", "getMajorant");
    private static final QName _GetMajorantResponse_QNAME = new QName("http://example.org/", "getMajorantResponse");
    private static final QName _GetNote_QNAME = new QName("http://example.org/", "getNote");
    private static final QName _GetNoteFinalAvecAbsence_QNAME = new QName("http://example.org/", "getNoteFinalAvecAbsence");
    private static final QName _GetNoteFinalAvecAbsenceResponse_QNAME = new QName("http://example.org/", "getNoteFinalAvecAbsenceResponse");
    private static final QName _GetNoteResponse_QNAME = new QName("http://example.org/", "getNoteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.client.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AjouterEtudiant }
     * 
     * @return
     *     the new instance of {@link AjouterEtudiant }
     */
    public AjouterEtudiant createAjouterEtudiant() {
        return new AjouterEtudiant();
    }

    /**
     * Create an instance of {@link AjouterEtudiantResponse }
     * 
     * @return
     *     the new instance of {@link AjouterEtudiantResponse }
     */
    public AjouterEtudiantResponse createAjouterEtudiantResponse() {
        return new AjouterEtudiantResponse();
    }

    /**
     * Create an instance of {@link Etudiant }
     * 
     * @return
     *     the new instance of {@link Etudiant }
     */
    public Etudiant createEtudiant() {
        return new Etudiant();
    }

    /**
     * Create an instance of {@link GetEtudiantsTries }
     * 
     * @return
     *     the new instance of {@link GetEtudiantsTries }
     */
    public GetEtudiantsTries createGetEtudiantsTries() {
        return new GetEtudiantsTries();
    }

    /**
     * Create an instance of {@link GetEtudiantsTriesResponse }
     * 
     * @return
     *     the new instance of {@link GetEtudiantsTriesResponse }
     */
    public GetEtudiantsTriesResponse createGetEtudiantsTriesResponse() {
        return new GetEtudiantsTriesResponse();
    }

    /**
     * Create an instance of {@link GetEtudiantsValidant }
     * 
     * @return
     *     the new instance of {@link GetEtudiantsValidant }
     */
    public GetEtudiantsValidant createGetEtudiantsValidant() {
        return new GetEtudiantsValidant();
    }

    /**
     * Create an instance of {@link GetEtudiantsValidantResponse }
     * 
     * @return
     *     the new instance of {@link GetEtudiantsValidantResponse }
     */
    public GetEtudiantsValidantResponse createGetEtudiantsValidantResponse() {
        return new GetEtudiantsValidantResponse();
    }

    /**
     * Create an instance of {@link GetMajorant }
     * 
     * @return
     *     the new instance of {@link GetMajorant }
     */
    public GetMajorant createGetMajorant() {
        return new GetMajorant();
    }

    /**
     * Create an instance of {@link GetMajorantResponse }
     * 
     * @return
     *     the new instance of {@link GetMajorantResponse }
     */
    public GetMajorantResponse createGetMajorantResponse() {
        return new GetMajorantResponse();
    }

    /**
     * Create an instance of {@link GetNote }
     * 
     * @return
     *     the new instance of {@link GetNote }
     */
    public GetNote createGetNote() {
        return new GetNote();
    }

    /**
     * Create an instance of {@link GetNoteFinalAvecAbsence }
     * 
     * @return
     *     the new instance of {@link GetNoteFinalAvecAbsence }
     */
    public GetNoteFinalAvecAbsence createGetNoteFinalAvecAbsence() {
        return new GetNoteFinalAvecAbsence();
    }

    /**
     * Create an instance of {@link GetNoteFinalAvecAbsenceResponse }
     * 
     * @return
     *     the new instance of {@link GetNoteFinalAvecAbsenceResponse }
     */
    public GetNoteFinalAvecAbsenceResponse createGetNoteFinalAvecAbsenceResponse() {
        return new GetNoteFinalAvecAbsenceResponse();
    }

    /**
     * Create an instance of {@link GetNoteResponse }
     * 
     * @return
     *     the new instance of {@link GetNoteResponse }
     */
    public GetNoteResponse createGetNoteResponse() {
        return new GetNoteResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterEtudiant }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AjouterEtudiant }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "ajouterEtudiant")
    public JAXBElement<AjouterEtudiant> createAjouterEtudiant(AjouterEtudiant value) {
        return new JAXBElement<>(_AjouterEtudiant_QNAME, AjouterEtudiant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterEtudiantResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AjouterEtudiantResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "ajouterEtudiantResponse")
    public JAXBElement<AjouterEtudiantResponse> createAjouterEtudiantResponse(AjouterEtudiantResponse value) {
        return new JAXBElement<>(_AjouterEtudiantResponse_QNAME, AjouterEtudiantResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Etudiant }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Etudiant }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "etudiant")
    public JAXBElement<Etudiant> createEtudiant(Etudiant value) {
        return new JAXBElement<>(_Etudiant_QNAME, Etudiant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtudiantsTries }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEtudiantsTries }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getEtudiantsTries")
    public JAXBElement<GetEtudiantsTries> createGetEtudiantsTries(GetEtudiantsTries value) {
        return new JAXBElement<>(_GetEtudiantsTries_QNAME, GetEtudiantsTries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtudiantsTriesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEtudiantsTriesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getEtudiantsTriesResponse")
    public JAXBElement<GetEtudiantsTriesResponse> createGetEtudiantsTriesResponse(GetEtudiantsTriesResponse value) {
        return new JAXBElement<>(_GetEtudiantsTriesResponse_QNAME, GetEtudiantsTriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtudiantsValidant }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEtudiantsValidant }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getEtudiantsValidant")
    public JAXBElement<GetEtudiantsValidant> createGetEtudiantsValidant(GetEtudiantsValidant value) {
        return new JAXBElement<>(_GetEtudiantsValidant_QNAME, GetEtudiantsValidant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtudiantsValidantResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEtudiantsValidantResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getEtudiantsValidantResponse")
    public JAXBElement<GetEtudiantsValidantResponse> createGetEtudiantsValidantResponse(GetEtudiantsValidantResponse value) {
        return new JAXBElement<>(_GetEtudiantsValidantResponse_QNAME, GetEtudiantsValidantResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMajorant }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMajorant }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getMajorant")
    public JAXBElement<GetMajorant> createGetMajorant(GetMajorant value) {
        return new JAXBElement<>(_GetMajorant_QNAME, GetMajorant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMajorantResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMajorantResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getMajorantResponse")
    public JAXBElement<GetMajorantResponse> createGetMajorantResponse(GetMajorantResponse value) {
        return new JAXBElement<>(_GetMajorantResponse_QNAME, GetMajorantResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNote }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getNote")
    public JAXBElement<GetNote> createGetNote(GetNote value) {
        return new JAXBElement<>(_GetNote_QNAME, GetNote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNoteFinalAvecAbsence }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNoteFinalAvecAbsence }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getNoteFinalAvecAbsence")
    public JAXBElement<GetNoteFinalAvecAbsence> createGetNoteFinalAvecAbsence(GetNoteFinalAvecAbsence value) {
        return new JAXBElement<>(_GetNoteFinalAvecAbsence_QNAME, GetNoteFinalAvecAbsence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNoteFinalAvecAbsenceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNoteFinalAvecAbsenceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getNoteFinalAvecAbsenceResponse")
    public JAXBElement<GetNoteFinalAvecAbsenceResponse> createGetNoteFinalAvecAbsenceResponse(GetNoteFinalAvecAbsenceResponse value) {
        return new JAXBElement<>(_GetNoteFinalAvecAbsenceResponse_QNAME, GetNoteFinalAvecAbsenceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNoteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNoteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getNoteResponse")
    public JAXBElement<GetNoteResponse> createGetNoteResponse(GetNoteResponse value) {
        return new JAXBElement<>(_GetNoteResponse_QNAME, GetNoteResponse.class, null, value);
    }

}
