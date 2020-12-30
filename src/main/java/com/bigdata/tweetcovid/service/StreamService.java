package com.bigdata.tweetcovid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StreamService {

	private final Logger log = LoggerFactory.getLogger(StreamService.class);

	@Autowired
	private Twitter twitter;

	private Stream userStream;

	public void streamTweetEvent(List<SseEmitter> emitters) throws InterruptedException{

		List<StreamListener> listeners = new ArrayList<StreamListener>();

		StreamListener streamListener = new StreamListener() {
			@Override
			public void onWarning(StreamWarningEvent warningEvent) {
			}

			@Override
			public void onTweet(Tweet tweet) {
				Integer connectedUsers =  emitters.size();
				if (connectedUsers!=0) {
					for (SseEmitter emiter : emitters) {
						try {

							StringBuilder textTweet = new StringBuilder();


								textTweet.append("About covid : "+tweet.getText() + " ");

							emiter.send(SseEmitter.event().name("streamHashtags").data(textTweet));
						} catch (IOException e) {
							System.out.println("Disconnected");
						}
					}
				}else{
					//Close stream
					userStream.close();
					log.info("Closing Stream");
				}

			}

			@Override
			public void onLimit(int numberOfLimitedTweets) {
			}

			@Override
			public void onDelete(StreamDeleteEvent deleteEvent) {
			}
		};
		//Start stream
		if (emitters.size()==1) {
			listeners.add(streamListener);
			FilterStreamParameters parameters = new FilterStreamParameters();
			parameters.track("Covid");
			userStream = twitter.streamingOperations().filter(parameters, listeners);
		}
	}
}