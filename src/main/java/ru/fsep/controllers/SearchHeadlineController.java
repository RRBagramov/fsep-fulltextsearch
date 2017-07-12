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
public class SearchHeadlineController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/search/headline", method = RequestMethod.GET)
    public String search(@ModelAttribute("model") ModelMap model, @RequestParam(value = "comment", required = false) String comment) {
        List<Comment> comments = commentService.getCommentsBySearchQueryWithHeadline("'" + comment.replaceAll("\\s+", "&") + "'");
        model.addAttribute("commentModel", comments);
        return "highlightSearchPage";
    }
}
