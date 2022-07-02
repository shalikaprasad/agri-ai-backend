package kln.se.agri.ai.dashboard.controller;

import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.News;
import kln.se.agri.ai.commons.model.ResponseWrapper;
import kln.se.agri.ai.commons.model.User;
import kln.se.agri.ai.persistence.dto.NewsDto;
import kln.se.agri.ai.persistence.service.FileService;
import kln.se.agri.ai.persistence.service.NewsService;
import kln.se.agri.ai.persistence.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Prasad on 12/15/19.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/news")
@Api(value = "news", tags = "News Controller")
public class NewsController {

    private final static Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
    public ResponseWrapper createNews(@RequestBody NewsDto newsDto, Authentication authentication) {
        logger.info(">> Creating News");

        User user = userService.getUserByUsername(authentication.getName());
        newsDto.setCreatedUserId(user.getId());
        ModelMapper modelMapper = new ModelMapper();
        News news1 = modelMapper.map(newsDto, News.class);
        news1.setIsDeleted(false);
        News news2 = null;
        if(newsDto.getId() == null){
            news2 = newsService.createNews(news1);
        }else {
            news1.setId(newsDto.getId());
            news2 = newsService.updateNews(news1);
        }

        if(news2 != null){
            return ResponseWrapper.success("OK");
        }else {
            return null;
        }

    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.GET)
    public ResponseWrapper deleteNews(Authentication authentication, @RequestParam("newsId") String id) {
        logger.info(">> Deleting News");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            newsService.deleteNews(Long.valueOf(id));
            return ResponseWrapper.success("OK");
        }
        else {
            return null;
        }
    }

}
