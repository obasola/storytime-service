/*
 * Created on 16 Mar 2017 ( Time 18:41:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.model.jpa;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "PersonAssignmentEntity" ( stored in table "person_assignment" )
 *
 * @author Telosys Tools Generator
 *
 */
 @Embeddable
public class PersonAssignmentEntityKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="Person_idPerson", nullable=false)
    private Integer    personIdperson ;
    
    @Column(name="Requirement_idRequirement", nullable=false)
    private Integer    requirementIdrequirement ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public PersonAssignmentEntityKey() {
        super();
    }

    public PersonAssignmentEntityKey( Integer personIdperson, Integer requirementIdrequirement ) {
        super();
        this.personIdperson = personIdperson ;
        this.requirementIdrequirement = requirementIdrequirement ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setPersonIdperson( Integer value ) {
        this.personIdperson = value;
    }
    public Integer getPersonIdperson() {
        return this.personIdperson;
    }

    public void setRequirementIdrequirement( Integer value ) {
        this.requirementIdrequirement = value;
    }
    public Integer getRequirementIdrequirement() {
        return this.requirementIdrequirement;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		PersonAssignmentEntityKey other = (PersonAssignmentEntityKey) obj; 
		//--- Attribute personIdperson
		if ( personIdperson == null ) { 
			if ( other.personIdperson != null ) 
				return false ; 
		} else if ( ! personIdperson.equals(other.personIdperson) ) 
			return false ; 
		//--- Attribute requirementIdrequirement
		if ( requirementIdrequirement == null ) { 
			if ( other.requirementIdrequirement != null ) 
				return false ; 
		} else if ( ! requirementIdrequirement.equals(other.requirementIdrequirement) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute personIdperson
		result = prime * result + ((personIdperson == null) ? 0 : personIdperson.hashCode() ) ; 
		//--- Attribute requirementIdrequirement
		result = prime * result + ((requirementIdrequirement == null) ? 0 : requirementIdrequirement.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(personIdperson); 
		sb.append("|"); 
		sb.append(requirementIdrequirement); 
        return sb.toString();
    }
}
