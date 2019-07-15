package dev.koh;

import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

class Main {

    private static Main mainInstance;

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
        Spark.get("/", (req, res) -> "Hola..!! :D");

        //  Sign-in Page
        Spark.get("/sign-in",
                (req, res) -> new ModelAndView(null, "sign-in.hbs"),
                new HandlebarsTemplateEngine()
        );

    }

    private void initializePostRequestsHandlers() {

        //  Time Stamp : 15th Juy 2K19, 05:10 PM..!!
        Spark.post("/home-page",
                (req, res) -> {

                    String username = "username";
                    Map<String, String> model = new HashMap<>();
                    model.put(username, req.queryParams(username));

                    return new ModelAndView(model, "home-page.hbs");
                },
                new HandlebarsTemplateEngine()
        );

    }

}

/*
 *  Date Created  : 15th Juy 2K19, 03:56 PM..!!
 *  Last Modified : 15th Juy 2K19, 05:28 PM..!!
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