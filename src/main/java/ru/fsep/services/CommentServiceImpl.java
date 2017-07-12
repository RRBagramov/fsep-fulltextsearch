package ru.fsep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fsep.repositories.CommentDao;
import ru.fsep.models.Comment;

import java.util.List;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentsBySearchQuery(String searchComment) {
        return commentDao.findByComment(searchComment);
    }

    @Override
    public List<Comment> getCommentsBySearchQueryWithHeadline(String searchComment) {
        return commentDao.findByCommentWithHighlight(searchComment);
    }


}
