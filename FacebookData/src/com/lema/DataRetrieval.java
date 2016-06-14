package com.lema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Like;
import facebook4j.Location;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONObject;

public class DataRetrieval {
	
	public static final String appID = "1149964411734062";
	public static final String appSecret = "8e17e97bacd0dd400025da31ca04bb84";
	public static final String accessToken = "EAAQV4vRECC4BAHGYek05cbUW3TXU75GuJ2Fc6o1SiNBxK2N0K0RaDEEgzdCXmUnGeVSmlXH1fEfWh9d1HPj5septMZBjX5rDx98MDLOZC6vcjH0rAzaKNyvZCbXd2JlNgAQyNVCynqEtgjSHAUHeWgLkndh6TIZD";
	
	public static final String accessPermissions ="user_birthday, user_religion_politics, user_relationships, user_relationship_details, user_hometown, user_location, user_likes, user_education_history, user_work_history, user_website, user_photos, user_videos, user_friends, user_about_me, user_status, user_games_activity, user_tagged_places, user_posts, email, publish_actions, public_profile, read_stream" ;
	
	
	public static void main(String[] args) throws FacebookException, IOException {
		
		Facebook facebook = new FacebookFactory().getInstance();
		
		facebook.setOAuthAppId(appID, appSecret);
		//facebook.setOAuthPermissions("email,user_subscriptions,status_update,manage_notifications,publish_stream,user_website,location,user_friends,user_location,manage_pages");
		facebook.setOAuthPermissions(accessPermissions);
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
		
		//ResponseList<Like> results = facebook.getUserLikes();
		
		ResponseList<Post> results1 = facebook.getFeed("sprint");
		
		File file = new File("facebookdata");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		//facebook.postStatusMessage("Setting the post from facebook feeds");
	
	//	ResponseList<Post> results = facebook.searchPosts("sprint");
		
		for(Post pp:results1){
			String lineText = pp.getId()+","+pp.getName()+","+pp.getMessage()+","+pp.getSharesCount()+","+pp.getStatusType();
			PagableList<Comment> comments = pp.getComments();
			if(comments.size() !=0){
			String commentLine = "";
			for(Comment cc:comments){
				commentLine = commentLine+cc.getMessage();
			}
			lineText=lineText +","+ commentLine;
			}else{
				lineText = lineText +","+"";
			}
			System.out.println(lineText);
			bw.write(lineText);
			bw.flush();
		
		}
		bw.close();
	}

}
