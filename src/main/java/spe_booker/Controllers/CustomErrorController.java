package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            switch (statusCode) {
                case 400:
                    LOG.info("400: Bad Request Error\n");
                    return "error/error-400";
                case 403:
                    LOG.info("403: Forbidden Error\n");
                    return "error/error-403";
                case 404:
                    LOG.info("404: Page Not Found Error\n");
                    return "error/error-404";
                case 500:
                    LOG.info("500: Internal Server Error\n");
                    return "error/error-500";
                default:
                    LOG.info(statusCode + ": Error\n");
                    return "error/error";
            }
        }
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
