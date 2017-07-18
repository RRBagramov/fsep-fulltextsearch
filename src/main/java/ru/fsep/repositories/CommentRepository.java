package ru.fsep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fsep.models.Comment;

import java.util.List;

/**
 * 13.07.2017
 *
 * @author Robert Bagramov.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //language=SQL
    @Query(value = "SELECT * FROM comment WHERE fts @@ plainto_tsquery('ru', :searchQuery)", nativeQuery = true)
    List<Comment> getComments(@Param("searchQuery") String searchQuery);

    //language=SQL
    @Query(value = "SELECT *" +
            "FROM comment " +
            "WHERE comment.text % :searchQuery order by similarity(text, :searchQuery) desc", nativeQuery = true)
    List<Comment> getCommentsBySimilarity(@Param("searchQuery") String searchQuery);

    //language=SQL
    @Query(value = "SELECT word " +
            "FROM dictionary "+
            "WHERE dictionary.word % :searchToken " +
            "ORDER BY similarity(word, :searchToken) DESC " +
            "LIMIT 1", nativeQuery = true)
    String getCorrectedWord(@Param("searchToken") String token);


}
