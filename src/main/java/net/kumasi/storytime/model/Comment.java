/*
 * Created on 16 Mar 2017 ( Time 18:41:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.model;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer idcomment;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 16777215 )
    private String description;

    @NotNull
    private Integer requirementIdrequirement;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdcomment( Integer idcomment ) {
        this.idcomment = idcomment ;
    }

    public Integer getIdcomment() {
        return this.idcomment;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDescription( String description ) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setRequirementIdrequirement( Integer requirementIdrequirement ) {
        this.requirementIdrequirement = requirementIdrequirement;
    }
    public Integer getRequirementIdrequirement() {
        return this.requirementIdrequirement;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idcomment);
        // attribute 'description' not usable (type = String Long Text)
        sb.append("|");
        sb.append(requirementIdrequirement);
        return sb.toString(); 
    } 


}
