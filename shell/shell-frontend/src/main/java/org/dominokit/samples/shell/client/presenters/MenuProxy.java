package org.dominokit.samples.shell.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;

import org.dominokit.samples.shell.client.views.MenuView;

import org.dominokit.samples.shell.shared.Slots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "Menu", parent = "shell")
@AutoRoute(routeOnce = true)
@Singleton
@Slot(Slots.LEFT_PANEL)
@AutoReveal
public class MenuProxy extends ViewBaseClientPresenter<MenuView> implements MenuView.MenuUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuProxy.class);


    @Override
    public void onMenuSelected(String token) {
        history().fireState(token);
    }
}