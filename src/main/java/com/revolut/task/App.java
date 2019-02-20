package com.revolut.task;

import com.revolut.task.controller.AccountController;
import com.revolut.task.controller.TransferController;
import org.jooby.Jooby;
import org.jooby.json.Jackson;


/**
 * Main Class (Starting point)
 */

public class App extends Jooby {

    {
        use(new Jackson());
        use(AccountController.class);
        use(TransferController.class);
    }

    public static void main(String[] args) {
        run(App::new, args);

    }

}
