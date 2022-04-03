package com.earth.testomania.skills.general;

import android.content.Context;

import com.earth.testomania.skills.GeneralSkillsMathematicalRepo;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GeneralSkillsMathematicalRepo_Factory implements Factory<GeneralSkillsMathematicalRepo> {
  private final Provider<Context> appContextProvider;

  private final Provider<Moshi> moshiProvider;

  public GeneralSkillsMathematicalRepo_Factory(Provider<Context> appContextProvider,
      Provider<Moshi> moshiProvider) {
    this.appContextProvider = appContextProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public GeneralSkillsMathematicalRepo get() {
    return newInstance(appContextProvider.get(), moshiProvider.get());
  }

  public static GeneralSkillsMathematicalRepo_Factory create(Provider<Context> appContextProvider,
      Provider<Moshi> moshiProvider) {
    return new GeneralSkillsMathematicalRepo_Factory(appContextProvider, moshiProvider);
  }

  public static GeneralSkillsMathematicalRepo newInstance(Context appContext, Moshi moshi) {
    return new GeneralSkillsMathematicalRepo(appContext, moshi);
  }
}
