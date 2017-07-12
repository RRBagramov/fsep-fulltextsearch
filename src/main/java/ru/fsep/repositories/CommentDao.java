package ru.fsep.repositories;

import ru.fsep.models.Comment;

import java.util.List;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
public interface CommentDao {
    List<Comment> findByComment(String searchComment);
    List<Comment> findByCommentWithHighlight(String searchComment);
}
