package ru.fsep.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fsep.models.Comment;
import ru.fsep.services.CommentService;

import java.util.List;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */

@Controller
public class SearchController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute("model") ModelMap model, @RequestParam(value = "q", required = false) String q) {
        List<Comment> comments = commentService.getCommentsBySearchQuerySimple("'" + q + "'");

        if (comments.isEmpty()) {
            String delims = "[ .,?!]+";
            String[] tokens = q.split(delims);
            comments = commentService.getCommentsBySearchQueryBySimilarity("'" + q + "'");
        }

        model.addAttribute("commentModel", comments);
        return "searchPage";
    }
}
