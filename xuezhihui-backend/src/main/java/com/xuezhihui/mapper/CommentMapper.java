package com.xuezhihui.mapper;

import com.xuezhihui.entity.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CommentMapper {
    int insert(Comment comment);
    List<Comment> selectByResourceId(@Param("resourceId") Integer resourceId);
    Comment selectById(@Param("id") Integer id);
    int deleteById(@Param("id") Integer id);
}
