package com.unifam.heartpatrol.model;

/**
 * Created by User on 20/07/2016.
 */
public class ListUser {

    public class Results{
        public String login;
        public String name;
    }

    public class Links
    {
        public String self ;
        public String prev ;
        public String next ;
        public String first ;
        public String last ;
    }

    public class Meta
    {
        public double benchmark ;
        public int current_page ;
        public int total_page ;
        public int limit ;
        public int total_row ;
    }

    public class Server
    {
        public String REQUEST_METHOD ;
        public String CONTENT_TYPE ;
        public String REMOTE_ADDR ;
        public String HTTP_USER_AGENT ;
    }

    public Messages messages ;
    public Results results ;
    public Meta _meta ;
    public Server _server ;
    public Links _links ;
    public String language ;

}
