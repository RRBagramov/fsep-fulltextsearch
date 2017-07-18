package ru.fsep.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fsep.models.Comment;
import ru.fsep.repositories.CommentDao;
import ru.fsep.services.CommentService;

import java.util.List;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> getComments(String searchComment) {
        return commentDao.getComments(searchComment);
    }

    @Override
    public List<Comment> getCommentsBySimilarity(String searchComment) {
        return commentDao.getCommentsBySimilarity(searchComment);
    }

//    //language=SQL
//    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE
//            = "SELECT id, text, adding_date, ts_headline('russian', text, plainto_tsquery(:searchQuery),'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') as highlight " +
//            "FROM (SELECT * " +
//            "FROM comment, plainto_tsquery('ru', :searchQuery) query " +
//            "WHERE fts @@ query)" +
//            " AS fts_search";
//
//    //language=SQL
//    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_BY_SIMILARITY_WITH_HEADLINE =
//            "SELECT *, ts_headline('russian', text, plainto_tsquery(:searchQuery), 'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') AS highlight " +
//                    "FROM (SELECT " +
//                    " *" +
//                    " FROM comment" +
//                    " WHERE comment.text % :searchQuery) AS fts_search";
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Override
//    public List<Comment> getCommentsBySearchQuerySimple(String searchComment) {
//        return commentRepository.getCommentsBySearchQuerySimple(searchComment);
//    }
//
//    @Override
//    public List<Comment> getCommentsBySearchQueryWithHeadline(String searchComment, String searchType) {
//        List<Object> list;
//        if (searchType.equals("similarity")) {
//            list = entityManager.createNativeQuery(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_BY_SIMILARITY_WITH_HEADLINE, "viewHighlight")
//                    .setParameter("searchQuery", searchComment)
//                    .getResultList();
//        } else {
//            list = entityManager.createNativeQuery(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE, "viewHighlight")
//                    .setParameter("searchQuery", searchComment)
//                    .getResultList();
//        }
//        return addHighlightFieldToCommentModel(list);
//    }
//
//    @Override
//    public List<Comment> getCommentsBySearchQueryBySimilarity(String searchComment) {
//        return commentRepository.getCommentsBySearchQueryBySimilarity(searchComment);
//    }
//
//    @Override
//    public List<Comment> getCommentsBySearchQueryWithHeadlineAndCorrectedQ(String searchComment) {
//        String newSearchComment = "";
//        String delims = "[ ;:\"<>()+=.,?!-]+";
//        String[] tokens = searchComment.split(delims);
//
//        for (String token : tokens) {
//            newSearchComment += commentRepository.getCorrectedWord("'" + token + "'") + " ";
//        }
//
//        return getCommentsBySearchQueryWithHeadline("'" + newSearchComment + "'", "similarity");
//    }
//
//    private List<Comment> addHighlightFieldToCommentModel(List<Object> list) {
//        ArrayList<Comment> comments = new ArrayList<>();
//        for (Object ob : list) {
//            Object[] array = (Object[]) ob;
//            Comment comment = (Comment) array[0];
//            String highlight = (String) array[1];
//            comment.setHighlight(highlight);
//            comments.add(comment);
//        }
//        return comments;
//    }
}
