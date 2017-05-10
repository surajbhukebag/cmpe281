package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.Area;

public class AreaListResponse extends CommonResponse {

	private List<Area> areas;

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	
}
