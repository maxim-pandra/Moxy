package view;

import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class GenericView$$State<T> extends MvpViewState<GenericView<T>> implements GenericView<T> {

    @Override
    public void testEvent(T param) {
        TestEventCommand testEventCommand = new TestEventCommand(param);
        viewCommands.beforeApply(testEventCommand);

        if (hasNotView()) {
            return;
        }

        for (GenericView<T> view : views) {
            view.testEvent(param);
        }

        viewCommands.afterApply(testEventCommand);
    }

    public class TestEventCommand extends ViewCommand<GenericView<T>> {

        public final T param;

        TestEventCommand(T param) {
            super("testEvent", AddToEndSingleStrategy.class);

            this.param = param;
        }

        @Override
        public void apply(GenericView<T> mvpView) {
            mvpView.testEvent(param);
        }
    }
}