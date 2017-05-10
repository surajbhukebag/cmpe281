package com.cmpe281.csn.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.model.Area;
import com.cmpe281.csn.repositories.AreaRepository;
import com.cmpe281.csn.repositories.CityRepository;
import com.cmpe281.csn.response.AreaListResponse;
import com.cmpe281.csn.response.AreaResponse;

@CrossOrigin
@RestController
public class AreaManagement {

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(value = "/area", method = RequestMethod.POST)
	public AreaResponse createArea(@RequestBody Area area) {
		AreaResponse areaResponse = new AreaResponse();
		try {
			area.setCity(cityRepository.findOne(area.getCity().getId()));
			Area savedArea = areaRepository.save(area);

			areaResponse.setArea(savedArea);
			areaResponse.setMsg("Successfully created Area with id : "
					+ savedArea.getId());
			areaResponse.setCode("202");

		} catch (Exception e) {
			areaResponse.setMsg("Something went wrong");
			areaResponse.setCode("400");
		}

		return areaResponse;
	}
	
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public AreaListResponse getArea() {
		AreaListResponse areaResponse = new AreaListResponse();
		try {
			Iterator<Area> areaIterator = areaRepository.findAll().iterator();
			List<Area> areas = new ArrayList<Area>();
			while(areaIterator.hasNext()) {
				areas.add(areaIterator.next());
			}
			areaResponse.setAreas(areas);
			areaResponse.setMsg("Successfully fetched Area list ");
			areaResponse.setCode("202");

		} catch (Exception e) {
			areaResponse.setMsg("Something went wrong");
			areaResponse.setCode("400");
		}

		return areaResponse;
	}
	
	@RequestMapping(value = "/area/{id}", method = RequestMethod.GET)
	public AreaResponse getSpecificArea(@PathVariable("id") Integer id) {
		AreaResponse areaResponse = new AreaResponse();
		try {
			Area area = areaRepository.findOne(id);			
			areaResponse.setArea(area);
			areaResponse.setMsg("Successfully fetched Area  ");
			areaResponse.setCode("202");

		} catch (Exception e) {
			areaResponse.setMsg("Something went wrong");
			areaResponse.setCode("400");
		}

		return areaResponse;
	}
	
	@RequestMapping(value = "/area/{id}", method = RequestMethod.PUT)
	public AreaResponse updateSpecificArea(@PathVariable("id") Integer id, @RequestBody Area area) {
		AreaResponse areaResponse = new AreaResponse();
		try {
			Area oldArea = areaRepository.findOne(id);	
			oldArea.setCity(cityRepository.findOne(area.getCity().getId()));
			oldArea.setName(area.getName());
			oldArea.setPincode(area.getPincode());
			areaRepository.save(oldArea);
			areaResponse.setArea(oldArea);
			areaResponse.setMsg("Successfully updated Area  ");
			areaResponse.setCode("202");

		} catch (Exception e) {
			areaResponse.setMsg("Something went wrong");
			areaResponse.setCode("400");
		}

		return areaResponse;
	}
	
	@RequestMapping(value = "/area/{id}", method = RequestMethod.DELETE)
	public AreaResponse deleteSpecificArea(@PathVariable("id") Integer id) {
		AreaResponse areaResponse = new AreaResponse();
		try {
			Area area = areaRepository.findOne(id);		
			area.setCity(null);
			areaRepository.save(area);
			areaRepository.delete(id);		
			areaResponse.setMsg("Successfully deleted Area  ");
			areaResponse.setCode("202");

		} catch (Exception e) {
			areaResponse.setMsg("Something went wrong");
			areaResponse.setCode("400");
		}

		return areaResponse;
	}
	
}
