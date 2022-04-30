package com.anna.hero_squad.parameter_resolver;

import com.anna.hero_squad.models.Squad;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.ParameterizedType;

public class SquadParameterResolver implements ParameterResolver {
  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == Squad.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return new Squad(6, "The Avengers", "Stop Thanos from clearing the universe's population");
  }
}
