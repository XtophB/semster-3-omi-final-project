package com.amoremio.ingredients;

import com.amoremio.ingredients.cheeses.Feta;
import com.amoremio.ingredients.cheeses.Mozzarella;
import com.amoremio.ingredients.cheeses.Parmesan;
import com.amoremio.ingredients.hams.Cotto;
import com.amoremio.ingredients.hams.Crudo;
import com.amoremio.ingredients.meats.Carpaccio;
import com.amoremio.ingredients.meats.Chicken;
import com.amoremio.ingredients.misc.ChilliFlake;
import com.amoremio.ingredients.misc.Corn;
import com.amoremio.ingredients.misc.Jalapeno;
import com.amoremio.ingredients.misc.Mushroom;
import com.amoremio.ingredients.misc.Onion;
import com.amoremio.ingredients.misc.Salami;

/**
 * A simple factory implementation. This is the only factory for ingredients, interface is redundant
 * here.
 */
public class IngredientFactory {

  /**
   * The factory method creating an ingredient based on the request. This is used by the {@link
   * com.amoremio.store.Supplier} class.
   *
   * @param name the ingredient name enum
   * @param expiryDayOffset the offset for expiry date from today's date
   * @return an instance of a specified ingredient
   */
  public Ingredient create(IngredientName name, int expiryDayOffset) {
    return switch (name) {
      case MOZZARELLA -> new Mozzarella(expiryDayOffset);
      case PARMESAN -> new Parmesan(expiryDayOffset);
      case FETA -> new Feta(expiryDayOffset);
      case CRUDO -> new Crudo(expiryDayOffset);
      case COTTO -> new Cotto(expiryDayOffset);
      case CHICKEN -> new Chicken(expiryDayOffset);
      case CARPACCIO -> new Carpaccio(expiryDayOffset);
      case MUSHROOM -> new Mushroom(expiryDayOffset);
      case JALAPENO -> new Jalapeno(expiryDayOffset);
      case SALAMI -> new Salami(expiryDayOffset);
      case ONION -> new Onion(expiryDayOffset);
      case CORN -> new Corn(expiryDayOffset);
      case CHILLI_FLAKE -> new ChilliFlake(expiryDayOffset);
      case DOUGH -> new Dough(expiryDayOffset);
      case SAUCE ->  new Sauce(expiryDayOffset);
    };
  }
}
