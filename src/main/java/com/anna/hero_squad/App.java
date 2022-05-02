package com.anna.hero_squad;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.services.HeroService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("public/");
    HeroService heroService = new HeroService();
    Map<String, Object> model = new HashMap<>();

    get("/", (request, response) -> new HandlebarsTemplateEngine().render(
        new ModelAndView(new HashMap<>(), "index.hbs")
    ));

    // CREATE HERO
    get("/heroes/new", (request, response) -> {
      model.put("edit", false);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "hero-form.hbs")
      );
    });

    post("/heroes", (request, response) -> {
      Hero hero = new Hero(
          request.queryParams("hero-name"),
          parseInt(request.queryParams("hero-age")),
          request.queryParams("hero-special-power"),
          request.queryParams("hero-weakness"),
          request.queryParams("hero-gender").charAt(0));
      heroService.add(hero, request.session().attribute("heroes"));

      List<Hero> heroes = heroService.getAll();
      request.session().attribute("heroes", heroes);
      response.redirect("/heroes");
      return null;
    });

    // READ HERO
    get("/heroes", (request, response) -> {
      model.put("heroes", request.session().attribute("heroes"));
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "hero-list.hbs")
      );
    });
  }
}