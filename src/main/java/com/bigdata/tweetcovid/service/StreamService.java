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
							StringBuilder dateTweet = new StringBuilder();

							String day;
							String month;

								textTweet.append(tweet.getText() + " ");

							switch(tweet.getCreatedAt().getDay()) {
								case 1:
									day = "Monday";
									break;
								case 2:
									day = "Tuesday";
									break;
								case 3:
									day = "Wednesday";
									break;
								case 4:
									day = "Thursday";
									break;
								case 5:
									day = "Friday";
									break;
								case 6:
									day = "Saturday";
									break;
								case 7:
									day = "Sunday";
									break;
								default:
									day = "undefined";
									// code block
							}

							switch(tweet.getCreatedAt().getMonth()) {
								case 0:
									month = "january";
									break;
								case 1:
									month = "february";
									break;
								case 2:
									month = "march";
									break;
								case 3:
									month = "april";
									break;
								case 4:
									month = "may";
									break;
								case 5:
									month = "june";
									break;
								case 6:
									month = "july";
									break;
								case 7:
									month = "august";
									break;
								case 8:
									month = "september";
									break;
								case 9:
									month = "october";
									break;
								case 10:
									month = "november";
									break;
								case 11:
									month = "december";
									break;
								default:
									month = "undefined";
									// code block
							}

							int year = tweet.getCreatedAt().getYear()+1900;

							dateTweet.append(""+day+" "+tweet.getCreatedAt().getDate()+" "+month+" "+year+" "+tweet.getCreatedAt().getHours()+":"+tweet.getCreatedAt().getMinutes()+":"+tweet.getCreatedAt().getSeconds()+"");
							//dateTweet.append(tweet.getCreatedAt());
							emiter.send(SseEmitter.event().name("streamHashtags").data(textTweet));
							emiter.send(SseEmitter.event().name("streamDate").data(dateTweet));
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
			parameters.track("covid");

			userStream = twitter.streamingOperations().filter(parameters, listeners);
		}
	}
}


/*Float west=4.47f;
			Float south=42.19f;
			Float east=8.13f;
			Float north = 51.05f;*/
//parameters.addLocation(west, south, east, north);