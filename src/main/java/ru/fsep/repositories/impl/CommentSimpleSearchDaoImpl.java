package ru.fsep.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fsep.models.Comment;
import ru.fsep.repositories.CommentDao;
import ru.fsep.repositories.CommentRepository;

import java.util.List;

/**
 * 17.07.2017
 *
 * @author Robert Bagramov.
 */
public class CommentSimpleSearchDaoImpl implements CommentDao {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getComments(String searchQuery) {
        return commentRepository.getComments(searchQuery);
    }

    @Override
    public List<Comment> getCommentsBySimilarity(String searchQuery) {
        return commentRepository.getCommentsBySimilarity(searchQuery);
    }
}

