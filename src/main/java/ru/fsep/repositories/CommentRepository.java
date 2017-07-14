package ru.fsep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fsep.models.Comment;

import java.util.List;

/**
 * 13.07.2017
 *
 * @author Robert Bagramov.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //language=SQL
    @Query(value = "SELECT * FROM comment WHERE fts @@ plainto_tsquery('ru', :searchQuery)", nativeQuery = true)
    List<Comment> getCommentsBySearchQuerySimple(@Param("searchQuery") String searchQuery);

    @Query(value = "SELECT *" +
            "FROM comment " +
            "WHERE comment.text % :searchQuery order by similarity(text, :searchQuery) desc", nativeQuery = true)
    List<Comment> getCommentsBySearchQueryBySimilarity(@Param("searchQuery") String searchQuery);

    // Не получилось сделать через аннотации. Закостылили с помощью EntityManager
    //language=SQL
//    @Query(name ="Comment.viewHighlight", value = "SELECT id, text, adding_date, ts_headline('russian', text, to_tsquery(:searchQuery),'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') as highlight " +
//            "FROM (SELECT * " +
//            "FROM comment, to_tsquery('ru', :searchQuery) query " +
//            "WHERE fts @@ query)" +
//            " AS fts_search", nativeQuery = true)
//    List<Comment> findByCommentWithHighlight(@Param("searchQuery")String searchComment);
}
