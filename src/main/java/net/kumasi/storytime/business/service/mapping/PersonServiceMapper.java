/*
 * Created on 16 Mar 2017 ( Time 18:41:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import net.kumasi.storytime.model.Person;
import net.kumasi.storytime.model.jpa.PersonEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class PersonServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public PersonServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'PersonEntity' to 'Person'
	 * @param personEntity
	 */
	public Person mapPersonEntityToPerson(PersonEntity personEntity) {
		if(personEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Person person = map(personEntity, Person.class);

		return person;
	}
	
	/**
	 * Mapping from 'Person' to 'PersonEntity'
	 * @param person
	 * @param personEntity
	 */
	public void mapPersonToPersonEntity(Person person, PersonEntity personEntity) {
		if(person == null) {
			return;
		}

		//--- Generic mapping 
		map(person, personEntity);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}