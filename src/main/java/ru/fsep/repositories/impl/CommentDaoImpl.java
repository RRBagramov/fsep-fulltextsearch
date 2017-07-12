package ru.fsep.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fsep.repositories.CommentDao;
import ru.fsep.models.Comment;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    private NamedParameterJdbcTemplate namedParameterTemplate;
    //language=SQL
    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY =
            "SELECT * FROM comment WHERE fts @@ to_tsquery('ru', :searchQuery)";

    //language=SQL
    private final String SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE =
            "SELECT ts_headline('russian', text, to_tsquery(:searchQuery),'StartSel =<mark>,StopSel=</mark>,HighlightAll=True') as highlight " +
                    "FROM (SELECT text, query " +
                    "FROM comment, to_tsquery('ru',:searchQuery) query " +
                    "WHERE fts @@ query" +
                    ") AS fts_search";

    @Autowired
    public CommentDaoImpl(DataSource dataSource) {
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Comment> commentRowMapper = new RowMapper<Comment>() {
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Comment.Builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .videoId(resultSet.getLong("video_id"))
                    .build();
        }
    };

    private RowMapper<Comment> highlightCommentRowMapper = new RowMapper<Comment>() {
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Comment.Builder()
                    .highlightText(resultSet.getString("highlight"))
                    .build();
        }

    };

    @Override
    public List<Comment> findByComment(String searchComment) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchQuery", searchComment);
        return namedParameterTemplate.query(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY, params, commentRowMapper);
    }

    @Override
    public List<Comment> findByCommentWithHighlight(String searchComment) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchQuery", searchComment);
        return namedParameterTemplate.query(SQL_SELECT_COMMENTS_BY_SEARCH_QUERY_WITH_HEADLINE, params, highlightCommentRowMapper);
    }

}
