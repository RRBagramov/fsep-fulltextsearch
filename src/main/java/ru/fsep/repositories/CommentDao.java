package ru.fsep.repositories;

import ru.fsep.models.Comment;

import java.util.List;

/**
 * 18.07.2017
 *
 * @author Robert Bagramov.
 */
public interface CommentDao {
    List<Comment> getComments(String searchComment);

    List<Comment> getCommentsBySimilarity(String searchComment);
}
