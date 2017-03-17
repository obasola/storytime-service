/*
 * Created on 16 Mar 2017 ( Time 18:41:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.rest.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import net.kumasi.storytime.model.RequirementSpecificationType;
import net.kumasi.storytime.business.service.RequirementSpecificationTypeService;
import net.kumasi.storytime.web.listitem.RequirementSpecificationTypeListItem;

/**
 * Spring MVC controller for 'RequirementSpecificationType' management.
 */
@Controller
public class RequirementSpecificationTypeRestController {

	@Resource
	private RequirementSpecificationTypeService requirementSpecificationTypeService;
	
	@RequestMapping( value="/items/requirementSpecificationType",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<RequirementSpecificationTypeListItem> findAllAsListItems() {
		List<RequirementSpecificationType> list = requirementSpecificationTypeService.findAll();
		List<RequirementSpecificationTypeListItem> items = new LinkedList<RequirementSpecificationTypeListItem>();
		for ( RequirementSpecificationType requirementSpecificationType : list ) {
			items.add(new RequirementSpecificationTypeListItem( requirementSpecificationType ) );
		}
		return items;
	}
	
	@RequestMapping( value="/requirementSpecificationType",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<RequirementSpecificationType> findAll() {
		return requirementSpecificationTypeService.findAll();
	}

	@RequestMapping( value="/requirementSpecificationType/{requirementIdrequirement}/{specificationTypeIdrequirementType}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RequirementSpecificationType findOne(@PathVariable("requirementIdrequirement") Integer requirementIdrequirement, @PathVariable("specificationTypeIdrequirementType") Integer specificationTypeIdrequirementType) {
		return requirementSpecificationTypeService.findById(requirementIdrequirement, specificationTypeIdrequirementType);
	}
	
	@RequestMapping( value="/requirementSpecificationType",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RequirementSpecificationType create(@RequestBody RequirementSpecificationType requirementSpecificationType) {
		return requirementSpecificationTypeService.create(requirementSpecificationType);
	}

	@RequestMapping( value="/requirementSpecificationType/{requirementIdrequirement}/{specificationTypeIdrequirementType}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RequirementSpecificationType update(@PathVariable("requirementIdrequirement") Integer requirementIdrequirement, @PathVariable("specificationTypeIdrequirementType") Integer specificationTypeIdrequirementType, @RequestBody RequirementSpecificationType requirementSpecificationType) {
		return requirementSpecificationTypeService.update(requirementSpecificationType);
	}

	@RequestMapping( value="/requirementSpecificationType/{requirementIdrequirement}/{specificationTypeIdrequirementType}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("requirementIdrequirement") Integer requirementIdrequirement, @PathVariable("specificationTypeIdrequirementType") Integer specificationTypeIdrequirementType) {
		requirementSpecificationTypeService.delete(requirementIdrequirement, specificationTypeIdrequirementType);
	}
	
}