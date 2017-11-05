package com.ethanhua.domain.model;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class Comment {

    /**
     * dataType : ReplyBeanForClient
     * id : 824471082676256768
     * videoId : 11763
     * videoTitle : 如果在每件事里加一点舞蹈
     * parentReplyId : 824279216425009152
     * rootReplyId : 824279216425009152
     * sequence : 36
     * message : 三十平胸没有郎
     * replyStatus : PUBLISHED
     * createTime : 1485404199000
     * user : {"uid":216600786,"nickname":"毛泽西","avatar":"http://img.wdjimg.com/image/account/6abea1ca9421f8e865b922865533cf39_300_300.png","userType":"NORMAL","ifPgc":false,"description":null,"area":null,"gender":null,"registDate":null,"cover":null,"actionUrl":"eyepetizer://pgc/detail/216600786/?title=%E6%AF%9B%E6%B3%BD%E8%A5%BF&userType=NORMAL&tabIndex=0"}
     * likeCount : 1
     * liked : false
     * hot : false
     * userType : null
     * type : video
     * actionUrl : null
     * parentReply : {"id":824279216425009152,"user":{"uid":196243602,"nickname":"生来平胸不怕狼_","avatar":"http://tva3.sinaimg.cn/crop.0.0.1080.1080.180/006apZ6ejw8ewgtnpk1z7j30u00u0tdw.jpg","userType":"NORMAL","ifPgc":false,"description":null,"area":null,"gender":null,"registDate":1452931445000,"cover":null,"actionUrl":"eyepetizer://pgc/detail/196243602/?title=%E7%94%9F%E6%9D%A5%E5%B9%B3%E8%83%B8%E4%B8%8D%E6%80%95%E7%8B%BC_&userType=NORMAL&tabIndex=0"},"message":"后面越来越鬼畜😛","replyStatus":"PUBLISHED"}
     * showParentReply : true
     * showConversationButton : true
     * sid : null
     * userBlocked : false
     */

    public String dataType;
    public long id;
    public int videoId;
    public String videoTitle;
    public long parentReplyId;
    public long rootReplyId;
    public int sequence;
    public String message;
    public String replyStatus;
    public long createTime;
    public User user;
    public int likeCount;
    public boolean liked;
    public boolean hot;
    public Object userType;
    public String type;
    public String actionUrl;
    public boolean showParentReply;
    public boolean showConversationButton;
    public Object sid;
    public boolean userBlocked;
    public Comment parentReply;
    public String text;

}
