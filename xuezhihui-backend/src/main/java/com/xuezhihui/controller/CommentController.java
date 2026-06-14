package com.xuezhihui.controller;

import com.xuezhihui.entity.Comment;
import com.xuezhihui.mapper.CommentMapper;
import com.xuezhihui.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resources")
public class CommentController {
    private final CommentMapper commentMapper;
    public CommentController(CommentMapper commentMapper) { this.commentMapper = commentMapper; }

    @PostMapping("/{id}/comments")
    public ResultUtil<?> create(@PathVariable Integer id, @RequestBody Comment comment) {
        comment.setResourceId(id);
        commentMapper.insert(comment);
        return ResultUtil.success(comment);
    }

    @GetMapping("/{id}/comments")
    public ResultUtil<List<Comment>> list(@PathVariable Integer id) {
        return ResultUtil.success(commentMapper.selectByResourceId(id));
    }

    @DeleteMapping("/{resourceId}/comments/{commentId}")
    public ResultUtil<?> delete(@PathVariable Integer resourceId,
                                @PathVariable Integer commentId,
                                @RequestBody Map<String, Object> body) {
        Comment comment = commentMapper.selectById(commentId);
        if (comment == null) return ResultUtil.error("评论不存在");
        int userId = Integer.parseInt(body.get("userId").toString());
        if (!comment.getUserId().equals(userId)) return ResultUtil.error("无权删除");
        commentMapper.deleteById(commentId);
        return ResultUtil.success("删除成功");
    }
}
