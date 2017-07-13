package ru.fsep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fsep.models.Comment;
import ru.fsep.repositories.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
@Service
public class CommentServiceImpl implements CommentService {
    //language=SQL
    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE = "SELECT id, text, adding_date, ts_headline('russian', text, to_tsquery(:searchQuery),'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') as highlight " +
            "FROM (SELECT * " +
            "FROM comment, to_tsquery('ru', :searchQuery) query " +
            "WHERE fts @@ query)" +
            " AS fts_search";

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Comment> getCommentsBySearchQuery(String searchComment) {

        return commentRepository.findByComment(searchComment);
    }

    //КОСТЫЛЬ
    //Пришлось сделат так, потому что поле highlight из таблицы не отображается в модель
    @Override
    public List<Comment> getCommentsBySearchQueryWithHeadline(String searchComment) {
        List<Object> list = entityManager.createNativeQuery(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE,"viewHighlight").setParameter("searchQuery",searchComment).getResultList();
        ArrayList<Comment> comments = new ArrayList<>();
        for (Object ob : list) {
            Object[] array = (Object[]) ob;
            Comment comment = (Comment) array[0];
            String highlight = (String) array[1];
            comment.setHighlight(highlight);
            comments.add(comment);
        }
        return comments;
        //return commentRepository.findByCommentWithHighlight(searchComment);
    }


}
