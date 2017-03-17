/*
 * Created on 16 Mar 2017 ( Time 18:41:35 )
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
 * Persistent class for entity stored in table "specification_type"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="specification_type", catalog="storytime" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="SpecificationTypeEntity.countAll", query="SELECT COUNT(x) FROM SpecificationTypeEntity x" )
} )
public class SpecificationTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idRequirement_Type", nullable=false)
    private Integer    idrequirementType ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="name", length=45)
    private String     name         ;

    @Column(name="description", length=500)
    private String     description  ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToMany(mappedBy="listOfSpecificationType", targetEntity=RequirementEntity.class)
    private List<RequirementEntity> listOfRequirement;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public SpecificationTypeEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdrequirementType( Integer idrequirementType ) {
        this.idrequirementType = idrequirementType ;
    }
    public Integer getIdrequirementType() {
        return this.idrequirementType;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : name ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : description ( VARCHAR ) 
    public void setDescription( String description ) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfRequirement( List<RequirementEntity> listOfRequirement ) {
        this.listOfRequirement = listOfRequirement;
    }
    public List<RequirementEntity> getListOfRequirement() {
        return this.listOfRequirement;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idrequirementType);
        sb.append("]:"); 
        sb.append(name);
        sb.append("|");
        sb.append(description);
        return sb.toString(); 
    } 

}