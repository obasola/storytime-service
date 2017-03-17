/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package net.kumasi.storytime.model.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "person"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="person", catalog="storytime" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PersonEntity.countAll", query="SELECT COUNT(x) FROM PersonEntity x" )
} )
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idPerson", nullable=false)
    private Integer    idperson     ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="first_name", nullable=false, length=45)
    private String     firstName    ;

    @Column(name="last_name", nullable=false, length=45)
    private String     lastName     ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="person", targetEntity=PersonAssignmentEntity.class)
    private List<PersonAssignmentEntity> listOfPersonAssignment;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PersonEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdperson( Integer idperson ) {
        this.idperson = idperson ;
    }
    public Integer getIdperson() {
        return this.idperson;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : first_name ( VARCHAR ) 
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    //--- DATABASE MAPPING : last_name ( VARCHAR ) 
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfPersonAssignment( List<PersonAssignmentEntity> listOfPersonAssignment ) {
        this.listOfPersonAssignment = listOfPersonAssignment;
    }
    public List<PersonAssignmentEntity> getListOfPersonAssignment() {
        return this.listOfPersonAssignment;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idperson);
        sb.append("]:"); 
        sb.append(firstName);
        sb.append("|");
        sb.append(lastName);
        return sb.toString(); 
    } 

}