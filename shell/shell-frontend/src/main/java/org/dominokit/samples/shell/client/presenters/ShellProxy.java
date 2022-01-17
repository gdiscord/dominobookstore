package org.dominokit.samples.shell.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import org.dominokit.samples.shell.client.views.ShellView;

import org.dominokit.samples.shell.shared.Slots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "Shell")
@AutoRoute(routeOnce = true)
@Singleton
@Slot(PredefinedSlots.BODY_SLOT)
@AutoReveal
@RegisterSlots({Slots.LEFT_PANEL, Slots.CONTENT})
public class ShellProxy extends ViewBaseClientPresenter<ShellView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellProxy.class);

    @OnReveal
    public void openHomeByDefault() {
        if(history().currentToken().isEmpty()){
            history().fireState("home");
        }

    }
}