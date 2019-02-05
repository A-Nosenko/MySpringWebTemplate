package template.controller;

import template.model.User;
import template.service.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import template.model.Order;
import template.model.Photo;
import template.model.Product;
import template.utils.ProductUploader;
import template.utils.UserReport;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ShopController {

    @Autowired
    ProductService productService;

    @Autowired
    PhotoService photoService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

    @Autowired
    private ApplicationContext appContext;

    private static final Logger LOG = Logger.getLogger(ShopController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView shop() {
        return new ModelAndView("shop", "products", productService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buy")
    public ModelAndView buy(@RequestParam(value = "userName") String userName,
                            @RequestParam(value = "productId") int productId) {
        User user = userService.findByLogin(userName);
        Order order = new Order();
        order.setUserId(user.getId());
        order.setProductId(productId);
        order.setOrderDate(new Timestamp(new Date().getTime()));
        orderService.addOrder(order);
        return account(userName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/payOff")
    public ModelAndView payOff(@RequestParam(value = "userName") String userName,
                               @RequestParam(value = "id") int orderId) {
        User user = userService.findByLogin(userName);
        if (accountService.payOff(user.getId(), orderService.getOrder(orderId).getPrice())) {
            orderService.payOffTheOrder(orderId, new Timestamp(new Date().getTime()));
            return account(userName);
        } else {
            ModelAndView account = new ModelAndView("account");
            account.addObject("balance", accountService.current(user.getId()));
            account.addObject("orders", orderService.findOrdersByUserId(user.getId()));
            account.addObject("error", "You don't have enough money.");
            return account;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/disclaim")
    public ModelAndView disclame(@RequestParam(value = "userName") String userName,
                                 @RequestParam(value = "id") int orderId) {
        orderService.remove(orderId);
        return account(userName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/account")
    public ModelAndView account(@RequestParam(value = "userName") String userName) {
        ModelAndView account = new ModelAndView("account");
        User user = userService.findByLogin(userName);
        if (user.getId() == 1) {
            return adminPage();
        }
        account.addObject("balance", accountService.current(user.getId()));
        account.addObject("orders", orderService.findOrdersByUserId(user.getId()));
        return account;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProduct")
    public ModelAndView addProduct(
            @ModelAttribute("productUploader") ProductUploader productUploader) {
        Photo photo = new Photo();
        try {
            photo.setBody(productUploader.getPhoto().getBytes());
        } catch (IOException e) {
            LOG.warning("Can't upload a photo!");
            return new ModelAndView("shop", "products", productService.getAll());
        }
        Product product = new Product();
        product.setTitle(productUploader.getTitle());
        product.setDescription(productUploader.getDescription());
        product.setPrice(productUploader.getPrice());
        int productId = productService.addProduct(product);
        photo.setProductId(productId);
        photoService.addPhoto(photo);
        return new ModelAndView("shop", "products", productService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/removeProduct")
    public ModelAndView removeProduct(@RequestParam(value = "productId") int id) {
        photoService.removePhoto(id);
        orderService.removeAll(id);
        productService.removeProduct(id);
        return new ModelAndView("shop", "products", productService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addInBlackList")
    public ModelAndView addInBlackList(@RequestParam(value = "userId") int id) {
        if (id == 1) {
            return adminPage();
        }
        userService.addInBlackList(id);
        return adminPage();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/removeFromBlackList")
    public ModelAndView removeFromBlackList(@RequestParam(value = "userId") int id) {
        if (id == 1) {
            return adminPage();
        }
        userService.removeFromBlackList(id);
        return adminPage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        List<UserReport> reports = new ArrayList<>();
        userService.getUsersList().forEach((user) -> {
            UserReport report = (UserReport) appContext.getBean("userReport");
            report.setUserId(user.getId());
            report.setLogin(user.getLogin());
            report.setEmail(user.getEmail());
            StringBuilder ordersBuilder = new StringBuilder();
            orderService.findOrdersByUserId(user.getId()).forEach(ordersBuilder::append);
            report.setOrders(ordersBuilder.toString());
            StringBuilder rolesBuilder = new StringBuilder();
            user.getRoles().forEach(rolesBuilder::append);
            report.setRoles(rolesBuilder.toString());
            reports.add(report);
        });
        modelAndView.addObject("reports", reports);
        modelAndView.addObject("productUploader", new ProductUploader());
        return modelAndView;
    }
}
