package com.bigdata.tweetcovid.controller;

import com.bigdata.tweetcovid.service.StreamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/")
public class StreamController {
	    
    private StreamService streamService;
    
    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }
    
    @RequestMapping("/")
    public String streamTweetAsEvents(){
        return "events";
    }  
    
    @RequestMapping("/tweetLocation")
    public SseEmitter streamTweets() throws InterruptedException{
    	
    	SseEmitter sseEmitter = new SseEmitter();
    	emitters.add(sseEmitter);
    	sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
    	streamService.streamTweetEvent(emitters);

    	return sseEmitter;
    }

}