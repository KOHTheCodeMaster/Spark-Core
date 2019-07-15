package dev.koh;

import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

class Main {

    private static Main mainInstance;
    private final String KEY_USERNAME = "username";

    private Main() {
    }

    static Main getInstance() {

        if (mainInstance == null) {
            synchronized (Main.class) {
                if (mainInstance == null)
                    mainInstance = new Main();
            }
        }
        return mainInstance;
    }

    void major() {

        init();

    }

    private void init() {

        initializeGetRequestsHandlers();
        initializePostRequestsHandlers();

    }

    private void initializeGetRequestsHandlers() {

        //  Home Page
//        Spark.get("/", (req, res) -> "Hola..!! :D");
        Spark.get("/",
                (req, res) -> {
                    Map<String, String> model = new HashMap<>();
                    String username = req.cookie(KEY_USERNAME);

                    model.put(KEY_USERNAME, username);
                    return new ModelAndView(model, "home-page.hbs");
                },
                new HandlebarsTemplateEngine()
        );

        //  Sign-in Page
        Spark.post("/sign-in",
                (req, res) -> {
                    Map<String, String> model = new HashMap<>();
                    String username = req.cookie(KEY_USERNAME);

                    model.put(KEY_USERNAME, username);
                    return new ModelAndView(model, "sign-in.hbs");
                },
                new HandlebarsTemplateEngine()
        );

    }

    private void initializePostRequestsHandlers() {

        //  Time Stamp : 15th Juy 2K19, 05:10 PM..!!
        Spark.post("/home-page",
                (req, res) -> {

                    String username = req.queryParams(KEY_USERNAME);
                    res.cookie(KEY_USERNAME, username);

                    Map<String, String> model = new HashMap<>();
                    model.put(KEY_USERNAME, username);

                    return new ModelAndView(model, "home-page.hbs");
                },
                new HandlebarsTemplateEngine()
        );

    }

}

/*
 *  Date Created  : 15th Juy 2K19, 03:56 PM..!!
 *  Last Modified : 15th Juy 2K19, 06:09 PM..!!
 *
 *  <| ================================================================ |>
 *
 *  3rd Commit - [Cookies]
 *  1. Using Cookies to store values at client-side &
 *     retain session information avoiding stateless of HTTP.
 *
 *  <| ================================================================ |>
 *
 *  2nd Commit - [Spark.post() method]
 *
 *  1. When a button is clicked in form element of HTML, it redirects to the
 *     resource mentioned in its action attribute & forwards the request to its method
 *     (get/post) according to the one mentioned in its method attribute.
 *  2. {{ variable_name }} syntax is used to represent variable along with the content in HBS
 *     E.g. <p>Hola {{ name }}..!!</p>  |   [Let name = "KOH"]
 *     Output:  Hola KOH..!!
 *  3. sign-in Page
 *          Get Request -> Display Form with a Text Input & a Button which
 *                         when clicked redirects to home-page res with post method
 *     home-page Page
 *          Post Request -> Displays the Username that was received as an additional
 *                          argument from sign-in get request
 *
 *  <| ================================================================ |>
 *
 *  Init Commit - [Spark Intro.]
 *  1. Set-up WebServer & Get request at "/koh" resource @localhost:4567/koh
 *
 *  <| ================================================================ |>
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */