package com.jingcheng.musicEducation.system.service;

import java.util.Map;

import com.jingcheng.musicEducation.system.entity.BroadcastImage;
import com.jingcheng.musicEducation.util.page.PagePojo;

public interface BroadcastImageBackgroundService {
    
	void addBroadcastImage(BroadcastImage broadcastImage);
	void updateBroadcastImage(BroadcastImage broadcastImage);
    BroadcastImage findImageById(Integer id);
    PagePojo findBroadcastImageList(Map<String,Object> params);
}
