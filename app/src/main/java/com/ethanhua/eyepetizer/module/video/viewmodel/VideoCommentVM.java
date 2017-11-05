package com.ethanhua.eyepetizer.module.video.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.util.TimeUtils;
import com.ethanhua.domain.model.Comment;
import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/9/28.
 */

public class VideoCommentVM extends ViewModel{
    public final ObservableField<String> avatar = new ObservableField<>();
    public final ObservableField<String> nickname = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();
    public final ObservableField<String> createTime = new ObservableField<>();
    public final ObservableField<String> actionUrl = new ObservableField<>();
    public final ObservableField<String> relatedCommentUserName = new ObservableField<>();
    public final ObservableField<String> relatedCommentAvatar = new ObservableField<>();
    public final ObservableField<String> relatedCommentContent = new ObservableField<>();

    public static VideoCommentVM mapFrom(Comment comment){
        VideoCommentVM commentVM = new VideoCommentVM();
        commentVM.avatar.set(comment.user.avatar);
        commentVM.nickname.set(comment.user.nickname);
        commentVM.content.set(comment.message);
        commentVM.createTime.set(TimeUtils.getFriendlyTimeSpanByNow(comment.createTime));
        if (comment.parentReply != null) {
            commentVM.relatedCommentAvatar.set(comment.parentReply.user.avatar);
            commentVM.relatedCommentUserName.set(comment.parentReply.user.nickname);
            commentVM.relatedCommentContent.set(comment.parentReply.message);
        }
        return commentVM;
    }
}
