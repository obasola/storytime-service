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
import net.kumasi.storytime.model.Requirement;
import net.kumasi.storytime.business.service.RequirementService;
import net.kumasi.storytime.web.listitem.RequirementListItem;

/**
 * Spring MVC controller for 'Requirement' management.
 */
@Controller
public class RequirementRestController {

	@Resource
	private RequirementService requirementService;
	
	@RequestMapping( value="/items/requirement",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<RequirementListItem> findAllAsListItems() {
		List<Requirement> list = requirementService.findAll();
		List<RequirementListItem> items = new LinkedList<RequirementListItem>();
		for ( Requirement requirement : list ) {
			items.add(new RequirementListItem( requirement ) );
		}
		return items;
	}
	
	@RequestMapping( value="/requirement",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Requirement> findAll() {
		return requirementService.findAll();
	}

	@RequestMapping( value="/requirement/{idrequirement}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Requirement findOne(@PathVariable("idrequirement") Integer idrequirement) {
		return requirementService.findById(idrequirement);
	}
	
	@RequestMapping( value="/requirement",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Requirement create(@RequestBody Requirement requirement) {
		return requirementService.create(requirement);
	}

	@RequestMapping( value="/requirement/{idrequirement}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Requirement update(@PathVariable("idrequirement") Integer idrequirement, @RequestBody Requirement requirement) {
		return requirementService.update(requirement);
	}

	@RequestMapping( value="/requirement/{idrequirement}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("idrequirement") Integer idrequirement) {
		requirementService.delete(idrequirement);
	}
	
}