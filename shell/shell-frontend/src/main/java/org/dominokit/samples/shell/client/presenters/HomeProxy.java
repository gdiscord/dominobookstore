package org.dominokit.samples.shell.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import org.dominokit.samples.shell.client.views.HomeView;

import org.dominokit.samples.shell.shared.Slots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "Home", parent = "shell")
@AutoRoute(token = "home")
@Slot(Slots.CONTENT)
@AutoReveal
public class HomeProxy extends ViewBaseClientPresenter<HomeView>  {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeProxy.class);


}