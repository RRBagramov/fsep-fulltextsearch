package ru.fsep.services;

import ru.fsep.models.Comment;

import java.util.List;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */

public interface CommentService {
    List<Comment> getCommentsBySearchQuery(String searchComment);
    List<Comment> getCommentsBySearchQueryWithHeadline(String searchComment);
}
