package ru.fsep.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fsep.models.Comment;
import ru.fsep.repositories.CommentDao;
import ru.fsep.repositories.CommentRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * 17.07.2017
 *
 * @author Robert Bagramov.
 */
public class CommentSimpleHeadlineSearchDaoImpl implements CommentDao {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    private List<Comment> addHighlightFieldToCommentModel(List<Object> list) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Object ob : list) {
            Object[] array = (Object[]) ob;
            Comment comment = (Comment) array[0];
            String highlight = (String) array[1];
            comment.setHighlight(highlight);
            comments.add(comment);
        }
        return comments;
    }

    //language=SQL
    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE
            = "SELECT id, text, adding_date, ts_headline('russian', text, plainto_tsquery(:searchQuery),'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') as highlight " +
            "FROM (SELECT * " +
            "FROM comment, plainto_tsquery('ru', :searchQuery) query " +
            "WHERE fts @@ query)" +
            " AS fts_search";

    //language=SQL
    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_BY_SIMILARITY_WITH_HEADLINE =
            "SELECT *, ts_headline('russian', text, plainto_tsquery(:searchQuery), 'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') AS highlight " +
                    "FROM (SELECT " +
                    " *" +
                    " FROM comment" +
                    " WHERE comment.text % :searchQuery) AS fts_search";

    @Override
    public List<Comment> getComments(String searchComment) {
        List<Object> list = entityManager.createNativeQuery(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE, "viewHighlight")
                .setParameter("searchQuery", searchComment)
                .getResultList();
        return addHighlightFieldToCommentModel(list);
    }

    @Override
    public List<Comment> getCommentsBySimilarity(String searchComment) {
        String newSearchComment = "";

        String delims = "[ .,?!]+";
        String[] tokens = searchComment.split(delims);

        for (String token : tokens) {
            newSearchComment += commentRepository.getCorrectedWord("'" + token + "'") + " ";
        }

        List<Comment> comments = this.getComments("'" + newSearchComment + "'");

        if (!comments.isEmpty()) {
            return comments;
        }
        else {
            List<Object> list = entityManager.createNativeQuery(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_BY_SIMILARITY_WITH_HEADLINE, "viewHighlight")
                    .setParameter("searchQuery", newSearchComment)
                    .getResultList();
            return addHighlightFieldToCommentModel(list);
        }
    }
}
