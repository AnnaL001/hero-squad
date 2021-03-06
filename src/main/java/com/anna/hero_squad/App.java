package com.anna.hero_squad;

import com.anna.hero_squad.models.Hero;
import com.anna.hero_squad.models.Squad;
import com.anna.hero_squad.services.HeroService;
import com.anna.hero_squad.services.SquadService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

public class App {
  static HeroService heroService = new HeroService();
  static SquadService squadService = new SquadService();

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }
  public static void main(String[] args) {
    port(getHerokuAssignedPort());
    staticFileLocation("public/");
    Map<String, Object> model = new HashMap<>();
    setupList();
    get("/", (request, response) -> {
      List<Hero> heroes = heroService.getAll();
      List<Squad> squads = squadService.getAll();
      request.session().attribute("heroes", heroes);
      request.session().attribute("squads", squads);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(new HashMap<>(), "index.hbs")
      );
    });

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
          Boolean.parseBoolean(request.queryParams("hero-gender")));
      heroService.add(hero, request.session().attribute("heroes"));
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

    get("heroes/:id", (request, response) -> {
      Hero hero = heroService.get(parseInt(request.params("id")), request.session().attribute("heroes"));
      Squad squad = squadService.get(hero.getSquadId(), request.session().attribute("squads"));
      model.put("hero", hero);
      model.put("squad", squad);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "hero-profile.hbs")
      );
    });

    // UPDATE HERO
    get("/heroes/:id/update", (request, response) -> {
      Hero hero = heroService.get(parseInt(request.params("id")), request.session().attribute("heroes"));
      model.put("hero", hero);
      model.put("edit", true);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "hero-form.hbs")
      );
    });

    // DELETE HERO
    get("/heroes/:id/delete", (request, response) -> {
      heroService.delete(parseInt(request.params("id")), request.session().attribute("heroes"), request.session().attribute("squads"));
     response.redirect("/heroes");
     return null;
    });

    post("/heroes/:id/update", (request, response) -> {
      Hero hero = new Hero(
          request.queryParams("hero-name"),
          parseInt(request.queryParams("hero-age")),
          request.queryParams("hero-special-power"),
          request.queryParams("hero-weakness"),
          Boolean.parseBoolean(request.queryParams("hero-gender")));
      hero.setId(parseInt(request.params("id")));
      heroService.update(hero, request.session().attribute("heroes"));
      response.redirect("/heroes");
      return null;
    });

    // CREATE SQUAD
    get("/squads/new", (request, response) -> {
      model.put("edit", false);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "squad-form.hbs")
      );
    });

    post("/squads", (request, response) -> {
      Squad squad = new Squad(
          parseInt(request.queryParams("squad-max-size")),
          request.queryParams("squad-name"),
          request.queryParams("squad-cause")
      );
      squadService.add(squad, request.session().attribute("squads"));
      response.redirect("/squads");
      return null;
    });

    // READ SQUAD
    get("/squads", (request, response) -> {
      model.put("squads", request.session().attribute("squads"));
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "squad-list.hbs")
      );
    });

    get("/squads/:id", (request, response) -> {
      Squad squad = squadService.get(parseInt(request.params("id")), request.session().attribute("squads"));
      model.put("squad", squad);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "squad-profile.hbs")
      );
    });

    // UPDATE SQUAD
    get("/squads/:id/update", (request, response) -> {
      Squad squad = squadService.get(parseInt(request.params("id")), request.session().attribute("squads"));
      model.put("squad", squad);
      model.put("edit", true);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "squad-form.hbs")
      );
    });

    post("/squads/:id/update", (request, response) -> {
      Squad squad = new Squad(
          parseInt(request.queryParams("squad-max-size")),
          request.queryParams("squad-name"),
          request.queryParams("squad-cause")
      );
      squad.setId(parseInt(request.params("id")));
      squadService.update(squad, request.session().attribute("squads"));
      response.redirect("/squads");
      return null;
    });

    // DELETE SQUAD
    get("/squads/:id/delete", (request, response) -> {
      squadService.delete(parseInt(request.params("id")), request.session().attribute("squads"), request.session().attribute("heroes"));
      response.redirect("/squads");
      return null;
    });

    // ADD HERO TO SQUAD
    get("/squads/:id/heroes/new", (request, response) -> {
      Squad squad = squadService.get(parseInt(request.params("id")), request.session().attribute("squads"));
      model.put("heroes", request.session().attribute("heroes"));
      model.put("squad", squad);
      return new HandlebarsTemplateEngine().render(
          new ModelAndView(model, "hero-members-form.hbs")
      );
    });

    post("/squads/:id/heroes", (request, response) -> {
      Hero hero = heroService.get(parseInt(request.queryParams("hero-member")), request.session().attribute("heroes"));
      squadService.addHeroToSquad(hero, parseInt(request.params("id")), request.session().attribute("squads"), request.session().attribute("heroes"));
      response.redirect("/squads/" + request.params("id"));
      return null;
    });

    // DELETE HERO FROM SQUAD
    get("/squads/:squad_id/heroes/:hero_id/delete", (request, response) -> {
      Hero hero = heroService.get(parseInt(request.params("hero_id")), request.session().attribute("heroes"));
      squadService.deleteHeroFromSquad(hero, parseInt(request.params("squad_id")), request.session().attribute("squads"), request.session().attribute("heroes"));
      response.redirect("/squads/" + request.params("squad_id"));
      return null;
    });

  }

  public static void setupList(){
    Hero polaris = new Hero("Polaris", 18 , "Magnetokinesis", "Can't use powers without metal in close range", false);
    Hero captainAmerica = new Hero("Captain America", 93, "Enhanced strength, reflexes and speed", "Can get mortally wounded", true);
    heroService.add(polaris, heroService.getAll());
    heroService.add(captainAmerica, heroService.getAll());
    Squad avengers = new Squad(6, "The Avengers", "Stop Thanos from clearing the universe's population");
    Squad innerCircle = new Squad(5, "Inner circle", "Fight for mutants' rights");
    squadService.add(avengers, squadService.getAll());
    squadService.add(innerCircle, squadService.getAll());
    squadService.addHeroToSquad(polaris, innerCircle.getId(), squadService.getAll(), heroService.getAll());
    squadService.addHeroToSquad(captainAmerica, avengers.getId(), squadService.getAll(), heroService.getAll());
    for(Hero hero: heroService.getAll()){
      System.out.println(hero.getSquadId());
      System.out.println(hero.getIsInSquad());
    }
  }
}