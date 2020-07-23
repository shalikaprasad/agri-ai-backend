package kln.se.agri.ai.user.controller;


import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.News;
import kln.se.agri.ai.persistence.service.FileService;
import kln.se.agri.ai.persistence.service.NewsService;
import kln.se.agri.ai.pub.rest.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private NewsService newsService;

    @Autowired
    private FileService fileService;

    @Value("${image.source}")
    private String imageSource;

    @Value("${pathSeparator}")
    private String pathSeparator;


    @GetMapping(value = "/getNewsListByCount/{count}")
    public ResponseWrapper getNewsByCount(HttpServletRequest request, @PathVariable("count") String count) {
        logger.info(">> Get News for home page");
        List<News> newsList = newsService.findNewsByCount(Integer.parseInt(count));
        for (News news: newsList){
            if(news.getPictureId() != null){
                news.setImageFileName(fileService.getImagePath(news.getPictureId()));
            }
        }
        return ResponseWrapper.success(newsList);
    }


}
