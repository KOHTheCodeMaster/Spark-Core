package dev.koh;

import spark.Spark;

public class Main {

    public static void main(String[] args) {

        System.out.println("Begin");

        Main obj = new Main();
        obj.major();

        System.out.println("End");

    }

    private void major() {

        Spark.get("/koh", (req, res) -> "Hola..!! :D");

    }

}

/*
 *  Date Created : 15th Juy 2K19, 03:56 PM..!!
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